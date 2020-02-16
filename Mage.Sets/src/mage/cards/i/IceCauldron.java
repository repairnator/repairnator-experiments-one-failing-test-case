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
package mage.cards.i;

import java.util.UUID;
import mage.ConditionalMana;
import mage.MageObjectReference;
import mage.Mana;
import mage.abilities.Ability;
import mage.abilities.SpellAbility;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.SourceHasCounterCondition;
import mage.abilities.costs.common.RemoveCountersSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.decorator.ConditionalActivatedAbility;
import mage.abilities.effects.AsThoughEffect;
import mage.abilities.effects.AsThoughEffectImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.ManaEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.mana.SimpleManaAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.AsThoughEffectType;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Outcome;
import mage.constants.Zone;
import mage.counters.CounterType;
import mage.filter.FilterCard;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.CardTypePredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.TargetCard;
import mage.target.targetpointer.FixedTarget;
import mage.util.CardUtil;

/**
 *
 * @author L_J (based on jeffwadsworth)
 */
public class IceCauldron extends CardImpl {

    public IceCauldron(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{4}");

        // {X}, {T}: Put a charge counter on Ice Cauldron and exile a nonland card from your hand. You may cast that card for as long as it remains exiled. Note the type and amount of mana spent to pay this activation cost. Activate this ability only if there are no charge counters on Ice Cauldron.
        ConditionalActivatedAbility ability = new ConditionalActivatedAbility(
                Zone.BATTLEFIELD, new AddCountersSourceEffect(CounterType.CHARGE.createInstance(), true), new ManaCostsImpl("{X}"), new SourceHasCounterCondition(CounterType.CHARGE, 0, 0));
        ability.addEffect(new IceCauldronExileEffect());
        ability.addEffect(new IceCauldronNoteManaEffect());
        ability.addCost(new TapSourceCost());
        this.addAbility(ability);

        // {T}, Remove a charge counter from Ice Cauldron: Add Ice Cauldron's last noted type and amount of mana to your mana pool. Spend this mana only to cast the last card exiled with Ice Cauldron.
        Ability ability2 = new SimpleManaAbility(Zone.BATTLEFIELD, new IceCauldronAddManaEffect(), new TapSourceCost());
        ability2.addCost(new RemoveCountersSourceCost(CounterType.CHARGE.createInstance()));
        this.addAbility(ability2);
    }

    public IceCauldron(final IceCauldron card) {
        super(card);
    }

    @Override
    public IceCauldron copy() {
        return new IceCauldron(this);
    }
}

class IceCauldronExileEffect extends OneShotEffect {

    private static final FilterCard filter = new FilterCard("nonland card");

    static {
        filter.add(Predicates.not(new CardTypePredicate(CardType.LAND)));
    }

    public IceCauldronExileEffect() {
        super(Outcome.Benefit);
        this.staticText = "and exile a nonland card from your hand. You may cast that card for as long as it remains exiled";
    }

    public IceCauldronExileEffect(final IceCauldronExileEffect effect) {
        super(effect);
    }

    @Override
    public IceCauldronExileEffect copy() {
        return new IceCauldronExileEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        Permanent sourcePermanent = game.getPermanentOrLKIBattlefield(source.getSourceId());
        if (controller != null && sourcePermanent != null) {
            if (controller.getHand().isEmpty()) {
                return true;
            }
            TargetCard target = new TargetCard(Zone.HAND, filter);
            target.setNotTarget(true);
            Card chosenCard = null;
            if (controller.choose(Outcome.Benefit, target, source.getSourceId(), game)) {
                chosenCard = controller.getHand().get(target.getFirstTarget(), game);
            }
            if (chosenCard != null) {
                controller.moveCardToExileWithInfo(chosenCard, source.getSourceId(), sourcePermanent.getIdName(), source.getSourceId(), game, Zone.HAND, true);
                AsThoughEffect effect = new IceCauldronCastFromExileEffect();
                effect.setTargetPointer(new FixedTarget(chosenCard.getId()));
                game.addEffect(effect, source);
                game.getState().setValue("IceCauldronCard" + source.getSourceId().toString(), new MageObjectReference(chosenCard.getId(), game)); //store the exiled card
                return true;
            }
        }
        return false;
    }
}

class IceCauldronCastFromExileEffect extends AsThoughEffectImpl {

    IceCauldronCastFromExileEffect() {
        super(AsThoughEffectType.PLAY_FROM_NOT_OWN_HAND_ZONE, Duration.Custom, Outcome.Benefit);
        staticText = "You may cast that card for as long as it remains exiled";
    }

    IceCauldronCastFromExileEffect(final IceCauldronCastFromExileEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public IceCauldronCastFromExileEffect copy() {
        return new IceCauldronCastFromExileEffect(this);
    }

    @Override
    public boolean applies(UUID objectId, Ability source, UUID affectedControllerId, Game game) {
        if (targetPointer.getTargets(game, source).contains(objectId)
                && game.getState().getZone(objectId) == Zone.EXILED) {
            Player player = game.getPlayer(source.getControllerId());
            Card card = game.getCard(objectId);
            if (player != null
                    && card != null) {
                return true;
            }
        }
        return false;
    }
}

class IceCauldronNoteManaEffect extends OneShotEffect {

    private static String manaUsedString;

    public IceCauldronNoteManaEffect() {
        super(Outcome.Benefit);
        this.staticText = "Note the type and amount of mana spent to pay this activation cost";
    }

    public IceCauldronNoteManaEffect(final IceCauldronNoteManaEffect effect) {
        super(effect);
    }

    @Override
    public IceCauldronNoteManaEffect copy() {
        return new IceCauldronNoteManaEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        Permanent iceCauldron = game.getPermanent(source.getSourceId());
        if (controller != null && iceCauldron != null) {
            game.getState().setValue("IceCauldronMana" + source.getSourceId().toString(), source.getManaCostsToPay().getUsedManaToPay()); //store the mana used to pay
            manaUsedString = source.getManaCostsToPay().getUsedManaToPay().toString();
            iceCauldron.addInfo("MANA USED", CardUtil.addToolTipMarkTags("Mana used last: " + manaUsedString), game);
            return true;
        }
        return false;
    }
}

class IceCauldronAddManaEffect extends ManaEffect {

    private static Mana storedMana;
    private static MageObjectReference exiledCardMor;

    IceCauldronAddManaEffect() {
        super();
        staticText = "Add {this}'s last noted type and amount of mana to your mana pool. Spend this mana only to cast the last card exiled with {this}";
    }

    IceCauldronAddManaEffect(IceCauldronAddManaEffect effect) {
        super(effect);
    }

    @Override
    public IceCauldronAddManaEffect copy() {
        return new IceCauldronAddManaEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            checkToFirePossibleEvents(getMana(game, source), game, source);
            controller.getManaPool().addMana(getMana(game, source), game, source);
            return true;
        }
        return false;
    }

    @Override
    public Mana produceMana(boolean netMana, Game game, Ability source) {
        Permanent iceCauldron = game.getPermanent(source.getSourceId());
        Player controller = game.getPlayer(source.getControllerId());
        if (iceCauldron != null && controller != null) {
            storedMana = (Mana) game.getState().getValue("IceCauldronMana" + source.getSourceId().toString());
            exiledCardMor = (MageObjectReference) game.getState().getValue("IceCauldronCard" + source.getSourceId().toString());
            if (storedMana != null) { // should be adding the mana even if exiled card is null
                Card card = exiledCardMor.getCard(game);
                if (card == null) {
                    card = game.getCard(exiledCardMor.getSourceId());
                    if (card != null && !(card.getZoneChangeCounter(game) == exiledCardMor.getZoneChangeCounter() + 1 && game.getState().getZone(card.getId()) == Zone.STACK)) {
                        card = null;
                    }
                }
                if (card != null) {
                    return new IceCauldronConditionalMana(storedMana, card);
                }
            }
        }
        return null;

    }

}

class IceCauldronConditionalMana extends ConditionalMana {

    public IceCauldronConditionalMana(Mana mana, Card exiledCard) {
        super(mana);
        staticText = "Spend this mana only to cast the last card exiled with {this}";
        addCondition(new IceCauldronManaCondition(exiledCard));
    }
}

class IceCauldronManaCondition implements Condition {

    private final Card exiledCard;

    public IceCauldronManaCondition(Card exiledCard) {
        this.exiledCard = exiledCard;
    }

    @Override
    public boolean apply(Game game, Ability source) {
        if (source instanceof SpellAbility) {
            Card card = game.getCard(source.getSourceId());
            if (card != null && exiledCard != null && card.equals(exiledCard)) {
                return true;
            }
        }
        return false;
    }
}
