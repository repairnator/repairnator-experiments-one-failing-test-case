/* LanguageTool, a natural language style checker 
 * Copyright (C) 2005 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.rules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.languagetool.AnalyzedSentence;
import org.languagetool.AnalyzedToken;
import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.rules.Categories;
import org.languagetool.rules.ITSIssueType;
import org.languagetool.rules.RuleMatch;
import org.languagetool.rules.TextLevelRule;

/**
 * An abstract rule checks the appearance of same words in a sentence or in two consecutive sentences.
 * The isTokenToCheck method can be used to check only specific words (e.g. substantive, verbs and adjectives).
 * This rule detects no grammar error but a stylistic problem (default off)
 * @author Fred Kruse
 */

public abstract class AbstractStyleRepeatedWordRule  extends TextLevelRule {
  
  private static final int MAX_TOKEN_TO_CHECK = 5;
  private static int maxDistanceOfSentences = 1;

  public AbstractStyleRepeatedWordRule(ResourceBundle messages) {
    super(messages);
    super.setCategory(Categories.STYLE.getCategory(messages));
    setLocQualityIssueType(ITSIssueType.Style);
    setDefaultOff();
  }

  @Override
  public String getId() {
    return "STYLE_REPEATED_WORD_RULE";
  }

  @Override
  public String getDescription() {
    return "Repeated words in consecutive sentences";
  }
  
  /*
   * Message for repeated word in same sentence
   */
  abstract protected String messageSameSentence();
  
  /*
   * Message for repeated word in sentence before
   */
  abstract protected String messageSentenceBefore();
  
  /*
   * Message for repeated word in sentence after
   */
  abstract protected String messageSentenceAfter();
  
  /*
   * set maximal Distance of words in number of sentences
   * @since 4.1
   */
  @Override
  public void setDefaultValue(int numSentences) {
    maxDistanceOfSentences = numSentences;
  }
  
  /*
   * get maximal Distance of words in number of sentences
   * @since 4.1
   */
  @Override
  public int getDefaultValue() {
    return maxDistanceOfSentences;
  }
  
  /*
   * Check only special words (e.g substantive, verbs, adjectives)
   * (German example: return (token.matchesPosTagRegex("(SUB|EIG|VER|ADJ):.*") 
   *              && !token.matchesPosTagRegex("ART:.*|ADV:.*|VER:(AUX|MOD):.*"));
   */
  protected abstract boolean isTokenToCheck(AnalyzedTokenReadings token);
    
  /*
   * Is checked word part of pairs like "arm in arm", "side by side", etc. (exclude such pairs)
   */
  protected abstract boolean isTokenPair(AnalyzedTokenReadings[] tokens, int n, boolean before);
  
  /*
   * listings are excluded
   */
  private static boolean hasBreakToken(AnalyzedTokenReadings[] tokens) {
    for(int i = 0; i < tokens.length && i < MAX_TOKEN_TO_CHECK; i++) {
      if(tokens[i].getToken().equals("-") || tokens[i].getToken().equals("—") || tokens[i].getToken().equals("–")) {
        return true;
      }
    }
    return false;
  }
  
  private boolean isTokenInSentence(AnalyzedTokenReadings testToken, AnalyzedTokenReadings[] tokens) {
    return isTokenInSentence(testToken, tokens, -1);
  }
  
  /* 
   *  true if token is found in sentence
   */
  private boolean isTokenInSentence(AnalyzedTokenReadings testToken, AnalyzedTokenReadings[] tokens, int notCheck) {
    if (testToken != null && tokens != null) {
      List<AnalyzedToken> readings = testToken.getReadings();
      if (readings.size() < 1) {
        return false;
      }
      String testBase = readings.get(0).getLemma();
      if (testBase == null) {
        return false;
      }
      for (int i = 0; i < tokens.length; i++) {
        if (i != notCheck && isTokenToCheck(tokens[i])) {
          readings = tokens[i].getReadings();
          if (readings.size() > 0) {
            String base = readings.get(0).getLemma();
            if (base != null) {
              if(testBase.equals(base)) {
                if(notCheck >= 0) {
                  if(notCheck == i - 2) {
                    return !isTokenPair(tokens, i, true);
                  } else if(notCheck == i + 2) {
                    return !isTokenPair(tokens, i, false);
                  } else if((notCheck == i + 1 || notCheck == i - 1) 
                      && testToken.getToken().equals(tokens[i].getToken())) {
                    return false;
                  }
                }
                return true;
              }
            }
          }
        }
      }
    }
    return false;
  }

  @Override
  public RuleMatch[] match(List<AnalyzedSentence> sentences) throws IOException {
    List<RuleMatch> ruleMatches = new ArrayList<>();
    List<AnalyzedTokenReadings[]> tokenList = new ArrayList<>();
    int pos = 0;
    for (int n = 0; n < maxDistanceOfSentences && n < sentences.size(); n++) {
      tokenList.add(sentences.get(n).getTokensWithoutWhitespace());
    }
    for (int n = 0; n < sentences.size(); n++) {
      if(n + maxDistanceOfSentences < sentences.size()) {
        tokenList.add(sentences.get(n + maxDistanceOfSentences).getTokensWithoutWhitespace());
      }
      if(tokenList.size() > 2 * maxDistanceOfSentences + 1) {
        tokenList.remove(0);
      }
      int nTok = maxDistanceOfSentences;
      if(n < maxDistanceOfSentences) {
        nTok = n;
      } else if(n >= sentences.size() - maxDistanceOfSentences) {
        nTok = tokenList.size() - (sentences.size() - n);
      }
      if(!hasBreakToken(tokenList.get(nTok))) {
        for (int i = 0; i < tokenList.get(nTok).length; i++) {
          AnalyzedTokenReadings token = tokenList.get(nTok)[i];
          if (isTokenToCheck(token)) {
            int isRepeated = 0;
            if (isTokenInSentence(token, tokenList.get(nTok), i)) {
              isRepeated = 1;
            }
            for(int j = nTok - 1; isRepeated == 0 && j >= 0 && j >= nTok - maxDistanceOfSentences; j--) {
              if (isTokenInSentence(token, tokenList.get(j))) {
                isRepeated = 2;
              }
            }
            for(int j = nTok + 1; isRepeated == 0 && j < tokenList.size() && j <= nTok + maxDistanceOfSentences; j++) {
              if (isTokenInSentence(token, tokenList.get(j))) {
                isRepeated = 3;
              }
            }
            if (isRepeated != 0) {
              String msg;
              if (isRepeated == 1) {
                msg = messageSameSentence();
              } else if (isRepeated == 2) {
                msg = messageSentenceBefore();
              } else {
                msg = messageSentenceAfter();
              }
              int startPos = pos + token.getStartPos();
              int endPos = pos + token.getEndPos();
              RuleMatch ruleMatch = new RuleMatch(this, startPos, endPos, msg);
              ruleMatches.add(ruleMatch);
            }
          } 
        }
      }
      pos += sentences.get(n).getText().length();
    }
    return toRuleMatchArray(ruleMatches);
  }

}
