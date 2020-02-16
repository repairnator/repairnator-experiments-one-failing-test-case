/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.cards.a;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.Cost;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.OneShotEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.Zone;
import static mage.filter.StaticFilters.FILTER_CONTROLLED_CREATURE_SHORT_TEXT;
import mage.game.Game;
import mage.players.Player;
import mage.target.TargetPlayer;
import mage.target.common.TargetControlledPermanent;

/**
 *
 * @author jeffwadsworth
 */
public class AltarOfDementia extends CardImpl {

    public AltarOfDementia(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}");

        // Sacrifice a creature: Target player puts a number of cards equal to the sacrificed creature's power from the top of their library into their graveyard.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new AltarOfDementiaEffect(), new SacrificeTargetCost(new TargetControlledPermanent(FILTER_CONTROLLED_CREATURE_SHORT_TEXT)));
        ability.addTarget(new TargetPlayer());
        this.addAbility(ability);

    }

    public AltarOfDementia(final AltarOfDementia card) {
        super(card);
    }

    @Override
    public AltarOfDementia copy() {
        return new AltarOfDementia(this);
    }
}

class AltarOfDementiaEffect extends OneShotEffect {

    public AltarOfDementiaEffect() {
        super(Outcome.Damage);
        staticText = "Target player puts a number of cards equal to the sacrificed creature's power from the top of their library into their graveyard";
    }

    public AltarOfDementiaEffect(final AltarOfDementiaEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getFirstTarget());
        if (player != null) {
            int amount = 0;
            for (Cost cost : source.getCosts()) {
                if (cost instanceof SacrificeTargetCost && !((SacrificeTargetCost) cost).getPermanents().isEmpty()) {
                    amount = ((SacrificeTargetCost) cost).getPermanents().get(0).getPower().getValue();
                    break;
                }
            }
            if (amount > 0) {
                player.moveCards(player.getLibrary().getTopCards(game, amount), Zone.GRAVEYARD, source, game);
            }
            return true;
        }
        return false;
    }

    @Override
    public AltarOfDementiaEffect copy() {
        return new AltarOfDementiaEffect(this);
    }
}
