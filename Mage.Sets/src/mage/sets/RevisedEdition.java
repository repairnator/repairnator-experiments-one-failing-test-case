package mage.sets;

import mage.cards.ExpansionSet;
import mage.constants.Rarity;
import mage.constants.SetType;

/**
 *
 * @author North
 */
public class RevisedEdition extends ExpansionSet {

    private static final RevisedEdition instance = new RevisedEdition();

    public static RevisedEdition getInstance() {
        return instance;
    }

    private RevisedEdition() {
        super("Revised Edition", "3ED", ExpansionSet.buildDate(1994, 3, 1), SetType.CORE);
        this.hasBoosters = true;
        this.numBoosterLands = 0;
        this.numBoosterCommon = 11;
        this.numBoosterUncommon = 3;
        this.numBoosterRare = 1;
        this.ratioBoosterMythic = 0;
        cards.add(new SetCardInfo("Air Elemental", 47, Rarity.UNCOMMON, mage.cards.a.AirElemental.class));
        cards.add(new SetCardInfo("Aladdin's Lamp", 231, Rarity.RARE, mage.cards.a.AladdinsLamp.class));
        cards.add(new SetCardInfo("Aladdin's Ring", 232, Rarity.RARE, mage.cards.a.AladdinsRing.class));
        cards.add(new SetCardInfo("Animate Artifact", 48, Rarity.UNCOMMON, mage.cards.a.AnimateArtifact.class));
        cards.add(new SetCardInfo("Animate Dead", 93, Rarity.UNCOMMON, mage.cards.a.AnimateDead.class));
        cards.add(new SetCardInfo("Animate Wall", 1, Rarity.RARE, mage.cards.a.AnimateWall.class));
        cards.add(new SetCardInfo("Ankh of Mishra", 233, Rarity.RARE, mage.cards.a.AnkhOfMishra.class));
        cards.add(new SetCardInfo("Armageddon Clock", 234, Rarity.RARE, mage.cards.a.ArmageddonClock.class));
        cards.add(new SetCardInfo("Armageddon", 2, Rarity.RARE, mage.cards.a.Armageddon.class));
        cards.add(new SetCardInfo("Aspect of Wolf", 186, Rarity.RARE, mage.cards.a.AspectOfWolf.class));
        cards.add(new SetCardInfo("Atog", 139, Rarity.COMMON, mage.cards.a.Atog.class));
        cards.add(new SetCardInfo("Bad Moon", 94, Rarity.RARE, mage.cards.b.BadMoon.class));
        cards.add(new SetCardInfo("Badlands", 282, Rarity.RARE, mage.cards.b.Badlands.class));
        cards.add(new SetCardInfo("Balance", 3, Rarity.RARE, mage.cards.b.Balance.class));
        cards.add(new SetCardInfo("Basalt Monolith", 235, Rarity.UNCOMMON, mage.cards.b.BasaltMonolith.class));
        cards.add(new SetCardInfo("Bayou", 283, Rarity.RARE, mage.cards.b.Bayou.class));
        cards.add(new SetCardInfo("Benalish Hero", 4, Rarity.COMMON, mage.cards.b.BenalishHero.class));
        cards.add(new SetCardInfo("Birds of Paradise", 187, Rarity.RARE, mage.cards.b.BirdsOfParadise.class));
        cards.add(new SetCardInfo("Black Knight", 95, Rarity.UNCOMMON, mage.cards.b.BlackKnight.class));
        cards.add(new SetCardInfo("Black Vise", 236, Rarity.UNCOMMON, mage.cards.b.BlackVise.class));
        cards.add(new SetCardInfo("Black Ward", 5, Rarity.UNCOMMON, mage.cards.b.BlackWard.class));
        cards.add(new SetCardInfo("Blessing", 6, Rarity.RARE, mage.cards.b.Blessing.class));
        cards.add(new SetCardInfo("Blue Elemental Blast", 49, Rarity.COMMON, mage.cards.b.BlueElementalBlast.class));
        cards.add(new SetCardInfo("Blue Ward", 7, Rarity.UNCOMMON, mage.cards.b.BlueWard.class));
        cards.add(new SetCardInfo("Bog Wraith", 96, Rarity.UNCOMMON, mage.cards.b.BogWraith.class));
        cards.add(new SetCardInfo("Bottle of Suleiman", 237, Rarity.RARE, mage.cards.b.BottleOfSuleiman.class));
        cards.add(new SetCardInfo("Braingeyser", 50, Rarity.RARE, mage.cards.b.Braingeyser.class));
        cards.add(new SetCardInfo("Brass Man", 238, Rarity.UNCOMMON, mage.cards.b.BrassMan.class));
        cards.add(new SetCardInfo("Burrowing", 140, Rarity.UNCOMMON, mage.cards.b.Burrowing.class));
        cards.add(new SetCardInfo("Castle", 8, Rarity.UNCOMMON, mage.cards.c.Castle.class));
        cards.add(new SetCardInfo("Celestial Prism", 239, Rarity.UNCOMMON, mage.cards.c.CelestialPrism.class));
        cards.add(new SetCardInfo("Channel", 188, Rarity.UNCOMMON, mage.cards.c.Channel.class));
        cards.add(new SetCardInfo("Chaoslace", 141, Rarity.RARE, mage.cards.c.Chaoslace.class));
        cards.add(new SetCardInfo("Circle of Protection: Black", 9, Rarity.COMMON, mage.cards.c.CircleOfProtectionBlack.class));
        cards.add(new SetCardInfo("Circle of Protection: Blue", 10, Rarity.COMMON, mage.cards.c.CircleOfProtectionBlue.class));
        cards.add(new SetCardInfo("Circle of Protection: Green", 11, Rarity.COMMON, mage.cards.c.CircleOfProtectionGreen.class));
        cards.add(new SetCardInfo("Circle of Protection: Red", 12, Rarity.COMMON, mage.cards.c.CircleOfProtectionRed.class));
        cards.add(new SetCardInfo("Circle of Protection: White", 13, Rarity.COMMON, mage.cards.c.CircleOfProtectionWhite.class));
        cards.add(new SetCardInfo("Clockwork Beast", 240, Rarity.RARE, mage.cards.c.ClockworkBeast.class));
        cards.add(new SetCardInfo("Clone", 51, Rarity.UNCOMMON, mage.cards.c.Clone.class));
        cards.add(new SetCardInfo("Cockatrice", 189, Rarity.RARE, mage.cards.c.Cockatrice.class));
        cards.add(new SetCardInfo("Conservator", 241, Rarity.UNCOMMON, mage.cards.c.Conservator.class));
        cards.add(new SetCardInfo("Control Magic", 52, Rarity.UNCOMMON, mage.cards.c.ControlMagic.class));
        cards.add(new SetCardInfo("Conversion", 14, Rarity.UNCOMMON, mage.cards.c.Conversion.class));
        cards.add(new SetCardInfo("Copy Artifact", 53, Rarity.RARE, mage.cards.c.CopyArtifact.class));
        cards.add(new SetCardInfo("Counterspell", 54, Rarity.UNCOMMON, mage.cards.c.Counterspell.class));
        cards.add(new SetCardInfo("Craw Wurm", 190, Rarity.COMMON, mage.cards.c.CrawWurm.class));
        cards.add(new SetCardInfo("Creature Bond", 55, Rarity.COMMON, mage.cards.c.CreatureBond.class));
        cards.add(new SetCardInfo("Crumble", 191, Rarity.UNCOMMON, mage.cards.c.Crumble.class));
        cards.add(new SetCardInfo("Crusade", 15, Rarity.RARE, mage.cards.c.Crusade.class));
        cards.add(new SetCardInfo("Crystal Rod", 242, Rarity.UNCOMMON, mage.cards.c.CrystalRod.class));
        cards.add(new SetCardInfo("Cursed Land", 98, Rarity.UNCOMMON, mage.cards.c.CursedLand.class));
        cards.add(new SetCardInfo("Dancing Scimitar", 243, Rarity.RARE, mage.cards.d.DancingScimitar.class));
        cards.add(new SetCardInfo("Dark Ritual", 99, Rarity.COMMON, mage.cards.d.DarkRitual.class));
        cards.add(new SetCardInfo("Death Ward", 16, Rarity.COMMON, mage.cards.d.DeathWard.class));
        cards.add(new SetCardInfo("Deathgrip", 101, Rarity.UNCOMMON, mage.cards.d.Deathgrip.class));
        cards.add(new SetCardInfo("Deathlace", 102, Rarity.RARE, mage.cards.d.Deathlace.class));
        cards.add(new SetCardInfo("Demonic Hordes", 104, Rarity.RARE, mage.cards.d.DemonicHordes.class));
        cards.add(new SetCardInfo("Demonic Tutor", 105, Rarity.UNCOMMON, mage.cards.d.DemonicTutor.class));
        cards.add(new SetCardInfo("Desert Twister", 192, Rarity.UNCOMMON, mage.cards.d.DesertTwister.class));
        cards.add(new SetCardInfo("Dingus Egg", 244, Rarity.RARE, mage.cards.d.DingusEgg.class));
        cards.add(new SetCardInfo("Disenchant", 17, Rarity.COMMON, mage.cards.d.Disenchant.class));
        cards.add(new SetCardInfo("Disintegrate", 142, Rarity.COMMON, mage.cards.d.Disintegrate.class));
        cards.add(new SetCardInfo("Disrupting Scepter", 245, Rarity.RARE, mage.cards.d.DisruptingScepter.class));
        cards.add(new SetCardInfo("Dragon Engine", 246, Rarity.RARE, mage.cards.d.DragonEngine.class));
        cards.add(new SetCardInfo("Dragon Whelp", 143, Rarity.UNCOMMON, mage.cards.d.DragonWhelp.class));
        cards.add(new SetCardInfo("Drain Life", 106, Rarity.COMMON, mage.cards.d.DrainLife.class));
        cards.add(new SetCardInfo("Drain Power", 56, Rarity.RARE, mage.cards.d.DrainPower.class));
        cards.add(new SetCardInfo("Drudge Skeletons", 107, Rarity.COMMON, mage.cards.d.DrudgeSkeletons.class));
        cards.add(new SetCardInfo("Dwarven Warriors", 144, Rarity.COMMON, mage.cards.d.DwarvenWarriors.class));
        cards.add(new SetCardInfo("Dwarven Weaponsmith", 145, Rarity.UNCOMMON, mage.cards.d.DwarvenWeaponsmith.class));
        cards.add(new SetCardInfo("Earth Elemental", 146, Rarity.UNCOMMON, mage.cards.e.EarthElemental.class));
        cards.add(new SetCardInfo("Earthbind", 147, Rarity.COMMON, mage.cards.e.Earthbind.class));
        cards.add(new SetCardInfo("Earthquake", 148, Rarity.RARE, mage.cards.e.Earthquake.class));
        cards.add(new SetCardInfo("Ebony Horse", 247, Rarity.RARE, mage.cards.e.EbonyHorse.class));
        cards.add(new SetCardInfo("El-Hajjaj", 108, Rarity.RARE, mage.cards.e.ElHajjaj.class));
        cards.add(new SetCardInfo("Elvish Archers", 193, Rarity.RARE, mage.cards.e.ElvishArchers.class));
        cards.add(new SetCardInfo("Energy Flux", 57, Rarity.UNCOMMON, mage.cards.e.EnergyFlux.class));
        cards.add(new SetCardInfo("Erg Raiders", 109, Rarity.COMMON, mage.cards.e.ErgRaiders.class));
        cards.add(new SetCardInfo("Evil Presence", 110, Rarity.UNCOMMON, mage.cards.e.EvilPresence.class));
        cards.add(new SetCardInfo("Eye for an Eye", 18, Rarity.RARE, mage.cards.e.EyeForAnEye.class));
        cards.add(new SetCardInfo("Farmstead", 19, Rarity.RARE, mage.cards.f.Farmstead.class));
        cards.add(new SetCardInfo("Fastbond", 194, Rarity.RARE, mage.cards.f.Fastbond.class));
        cards.add(new SetCardInfo("Fear", 111, Rarity.COMMON, mage.cards.f.Fear.class));
        cards.add(new SetCardInfo("Feedback", 58, Rarity.UNCOMMON, mage.cards.f.Feedback.class));
        cards.add(new SetCardInfo("Fire Elemental", 149, Rarity.UNCOMMON, mage.cards.f.FireElemental.class));
        cards.add(new SetCardInfo("Fireball", 150, Rarity.COMMON, mage.cards.f.Fireball.class));
        cards.add(new SetCardInfo("Firebreathing", 151, Rarity.COMMON, mage.cards.f.Firebreathing.class));
        cards.add(new SetCardInfo("Flashfires", 152, Rarity.UNCOMMON, mage.cards.f.Flashfires.class));
        cards.add(new SetCardInfo("Flight", 59, Rarity.COMMON, mage.cards.f.Flight.class));
        cards.add(new SetCardInfo("Flying Carpet", 248, Rarity.RARE, mage.cards.f.FlyingCarpet.class));
        cards.add(new SetCardInfo("Fog", 195, Rarity.COMMON, mage.cards.f.Fog.class));
        cards.add(new SetCardInfo("Force of Nature", 196, Rarity.RARE, mage.cards.f.ForceOfNature.class));
        cards.add(new SetCardInfo("Forest", 304, Rarity.LAND, mage.cards.basiclands.Forest.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("Forest", 305, Rarity.LAND, mage.cards.basiclands.Forest.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("Forest", 306, Rarity.LAND, mage.cards.basiclands.Forest.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("Fork", 153, Rarity.RARE, mage.cards.f.Fork.class));
        cards.add(new SetCardInfo("Frozen Shade", 112, Rarity.COMMON, mage.cards.f.FrozenShade.class));
        cards.add(new SetCardInfo("Fungusaur", 197, Rarity.RARE, mage.cards.f.Fungusaur.class));
        cards.add(new SetCardInfo("Gaea's Liege", 198, Rarity.RARE, mage.cards.g.GaeasLiege.class));
        cards.add(new SetCardInfo("Giant Growth", 199, Rarity.COMMON, mage.cards.g.GiantGrowth.class));
        cards.add(new SetCardInfo("Giant Spider", 200, Rarity.COMMON, mage.cards.g.GiantSpider.class));
        cards.add(new SetCardInfo("Glasses of Urza", 249, Rarity.UNCOMMON, mage.cards.g.GlassesOfUrza.class));
        cards.add(new SetCardInfo("Gloom", 113, Rarity.UNCOMMON, mage.cards.g.Gloom.class));
        cards.add(new SetCardInfo("Goblin Balloon Brigade", 154, Rarity.UNCOMMON, mage.cards.g.GoblinBalloonBrigade.class));
        cards.add(new SetCardInfo("Goblin King", 155, Rarity.RARE, mage.cards.g.GoblinKing.class));
        cards.add(new SetCardInfo("Granite Gargoyle", 156, Rarity.RARE, mage.cards.g.GraniteGargoyle.class));
        cards.add(new SetCardInfo("Gray Ogre", 157, Rarity.COMMON, mage.cards.g.GrayOgre.class));
        cards.add(new SetCardInfo("Green Ward", 20, Rarity.UNCOMMON, mage.cards.g.GreenWard.class));
        cards.add(new SetCardInfo("Grizzly Bears", 201, Rarity.COMMON, mage.cards.g.GrizzlyBears.class));
        cards.add(new SetCardInfo("Guardian Angel", 21, Rarity.COMMON, mage.cards.g.GuardianAngel.class));
        cards.add(new SetCardInfo("Healing Salve", 22, Rarity.COMMON, mage.cards.h.HealingSalve.class));
        cards.add(new SetCardInfo("Helm of Chatzuk", 250, Rarity.RARE, mage.cards.h.HelmOfChatzuk.class));
        cards.add(new SetCardInfo("Hill Giant", 158, Rarity.COMMON, mage.cards.h.HillGiant.class));
        cards.add(new SetCardInfo("Holy Armor", 23, Rarity.COMMON, mage.cards.h.HolyArmor.class));
        cards.add(new SetCardInfo("Holy Strength", 24, Rarity.COMMON, mage.cards.h.HolyStrength.class));
        cards.add(new SetCardInfo("Howl from Beyond", 114, Rarity.COMMON, mage.cards.h.HowlFromBeyond.class));
        cards.add(new SetCardInfo("Howling Mine", 251, Rarity.RARE, mage.cards.h.HowlingMine.class));
        cards.add(new SetCardInfo("Hurkyl's Recall", 60, Rarity.RARE, mage.cards.h.HurkylsRecall.class));
        cards.add(new SetCardInfo("Hurloon Minotaur", 159, Rarity.COMMON, mage.cards.h.HurloonMinotaur.class));
        cards.add(new SetCardInfo("Hurricane", 202, Rarity.UNCOMMON, mage.cards.h.Hurricane.class));
        cards.add(new SetCardInfo("Hypnotic Specter", 115, Rarity.UNCOMMON, mage.cards.h.HypnoticSpecter.class));
        cards.add(new SetCardInfo("Instill Energy", 203, Rarity.UNCOMMON, mage.cards.i.InstillEnergy.class));
        cards.add(new SetCardInfo("Iron Star", 252, Rarity.UNCOMMON, mage.cards.i.IronStar.class));
        cards.add(new SetCardInfo("Ironroot Treefolk", 204, Rarity.COMMON, mage.cards.i.IronrootTreefolk.class));
        cards.add(new SetCardInfo("Island Fish Jasconius", 61, Rarity.RARE, mage.cards.i.IslandFishJasconius.class));
        cards.add(new SetCardInfo("Island Sanctuary", 25, Rarity.RARE, mage.cards.i.IslandSanctuary.class));
        cards.add(new SetCardInfo("Island", 295, Rarity.LAND, mage.cards.basiclands.Island.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("Island", 296, Rarity.LAND, mage.cards.basiclands.Island.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("Island", 297, Rarity.LAND, mage.cards.basiclands.Island.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("Ivory Cup", 253, Rarity.UNCOMMON, mage.cards.i.IvoryCup.class));
        cards.add(new SetCardInfo("Ivory Tower", 254, Rarity.RARE, mage.cards.i.IvoryTower.class));
        cards.add(new SetCardInfo("Jade Monolith", 255, Rarity.RARE, mage.cards.j.JadeMonolith.class));
        cards.add(new SetCardInfo("Jandor's Ring", 256, Rarity.RARE, mage.cards.j.JandorsRing.class));
        cards.add(new SetCardInfo("Jandor's Saddlebags", 257, Rarity.RARE, mage.cards.j.JandorsSaddlebags.class));
        cards.add(new SetCardInfo("Jayemdae Tome", 258, Rarity.RARE, mage.cards.j.JayemdaeTome.class));
        cards.add(new SetCardInfo("Juggernaut", 259, Rarity.UNCOMMON, mage.cards.j.Juggernaut.class));
        cards.add(new SetCardInfo("Jump", 62, Rarity.COMMON, mage.cards.j.Jump.class));
        cards.add(new SetCardInfo("Karma", 26, Rarity.UNCOMMON, mage.cards.k.Karma.class));
        cards.add(new SetCardInfo("Keldon Warlord", 160, Rarity.UNCOMMON, mage.cards.k.KeldonWarlord.class));
        cards.add(new SetCardInfo("Kird Ape", 161, Rarity.COMMON, mage.cards.k.KirdApe.class));
        cards.add(new SetCardInfo("Kormus Bell", 260, Rarity.RARE, mage.cards.k.KormusBell.class));
        cards.add(new SetCardInfo("Kudzu", 205, Rarity.RARE, mage.cards.k.Kudzu.class));
        cards.add(new SetCardInfo("Lance", 27, Rarity.UNCOMMON, mage.cards.l.Lance.class));
        cards.add(new SetCardInfo("Ley Druid", 206, Rarity.UNCOMMON, mage.cards.l.LeyDruid.class));
        cards.add(new SetCardInfo("Library of Leng", 261, Rarity.UNCOMMON, mage.cards.l.LibraryOfLeng.class));
        cards.add(new SetCardInfo("Lifeforce", 207, Rarity.UNCOMMON, mage.cards.l.Lifeforce.class));
        cards.add(new SetCardInfo("Lifelace", 208, Rarity.RARE, mage.cards.l.Lifelace.class));
        cards.add(new SetCardInfo("Lifetap", 63, Rarity.UNCOMMON, mage.cards.l.Lifetap.class));
        cards.add(new SetCardInfo("Lightning Bolt", 162, Rarity.COMMON, mage.cards.l.LightningBolt.class));
        cards.add(new SetCardInfo("Living Artifact", 209, Rarity.RARE, mage.cards.l.LivingArtifact.class));
        cards.add(new SetCardInfo("Living Lands", 210, Rarity.RARE, mage.cards.l.LivingLands.class));
        cards.add(new SetCardInfo("Living Wall", 262, Rarity.UNCOMMON, mage.cards.l.LivingWall.class));
        cards.add(new SetCardInfo("Llanowar Elves", 211, Rarity.COMMON, mage.cards.l.LlanowarElves.class));
        cards.add(new SetCardInfo("Lord of Atlantis", 64, Rarity.RARE, mage.cards.l.LordOfAtlantis.class));
        cards.add(new SetCardInfo("Lord of the Pit", 116, Rarity.RARE, mage.cards.l.LordOfThePit.class));
        cards.add(new SetCardInfo("Lure", 212, Rarity.UNCOMMON, mage.cards.l.Lure.class));
        cards.add(new SetCardInfo("Magnetic Mountain", 163, Rarity.RARE, mage.cards.m.MagneticMountain.class));
        cards.add(new SetCardInfo("Mahamoti Djinn", 66, Rarity.RARE, mage.cards.m.MahamotiDjinn.class));
        cards.add(new SetCardInfo("Mana Flare", 164, Rarity.RARE, mage.cards.m.ManaFlare.class));
        cards.add(new SetCardInfo("Mana Short", 67, Rarity.RARE, mage.cards.m.ManaShort.class));
        cards.add(new SetCardInfo("Mana Vault", 263, Rarity.RARE, mage.cards.m.ManaVault.class));
        cards.add(new SetCardInfo("Manabarbs", 165, Rarity.RARE, mage.cards.m.Manabarbs.class));
        cards.add(new SetCardInfo("Meekstone", 264, Rarity.RARE, mage.cards.m.Meekstone.class));
        cards.add(new SetCardInfo("Merfolk of the Pearl Trident", 68, Rarity.COMMON, mage.cards.m.MerfolkOfThePearlTrident.class));
        cards.add(new SetCardInfo("Mesa Pegasus", 28, Rarity.COMMON, mage.cards.m.MesaPegasus.class));
        cards.add(new SetCardInfo("Mijae Djinn", 166, Rarity.RARE, mage.cards.m.MijaeDjinn.class));
        cards.add(new SetCardInfo("Millstone", 265, Rarity.RARE, mage.cards.m.Millstone.class));
        cards.add(new SetCardInfo("Mind Twist", 117, Rarity.RARE, mage.cards.m.MindTwist.class));
        cards.add(new SetCardInfo("Mishra's War Machine", 266, Rarity.RARE, mage.cards.m.MishrasWarMachine.class));
        cards.add(new SetCardInfo("Mons's Goblin Raiders", 167, Rarity.COMMON, mage.cards.m.MonssGoblinRaiders.class));
        cards.add(new SetCardInfo("Mountain", 301, Rarity.LAND, mage.cards.basiclands.Mountain.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("Mountain", 302, Rarity.LAND, mage.cards.basiclands.Mountain.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("Mountain", 303, Rarity.LAND, mage.cards.basiclands.Mountain.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("Nether Shadow", 118, Rarity.RARE, mage.cards.n.NetherShadow.class));
        cards.add(new SetCardInfo("Nettling Imp", 119, Rarity.UNCOMMON, mage.cards.n.NettlingImp.class));
        cards.add(new SetCardInfo("Nevinyrral's Disk", 267, Rarity.RARE, mage.cards.n.NevinyrralsDisk.class));
        cards.add(new SetCardInfo("Nightmare", 120, Rarity.RARE, mage.cards.n.Nightmare.class));
        cards.add(new SetCardInfo("Northern Paladin", 29, Rarity.RARE, mage.cards.n.NorthernPaladin.class));
        cards.add(new SetCardInfo("Obsianus Golem", 268, Rarity.UNCOMMON, mage.cards.o.ObsianusGolem.class));
        cards.add(new SetCardInfo("Onulet", 269, Rarity.RARE, mage.cards.o.Onulet.class));
        cards.add(new SetCardInfo("Orcish Artillery", 168, Rarity.UNCOMMON, mage.cards.o.OrcishArtillery.class));
        cards.add(new SetCardInfo("Orcish Oriflamme", 169, Rarity.UNCOMMON, mage.cards.o.OrcishOriflamme.class));
        cards.add(new SetCardInfo("Ornithopter", 270, Rarity.UNCOMMON, mage.cards.o.Ornithopter.class));
        cards.add(new SetCardInfo("Paralyze", 121, Rarity.COMMON, mage.cards.p.Paralyze.class));
        cards.add(new SetCardInfo("Pearled Unicorn", 30, Rarity.COMMON, mage.cards.p.PearledUnicorn.class));
        cards.add(new SetCardInfo("Personal Incarnation", 31, Rarity.RARE, mage.cards.p.PersonalIncarnation.class));
        cards.add(new SetCardInfo("Pestilence", 122, Rarity.COMMON, mage.cards.p.Pestilence.class));
        cards.add(new SetCardInfo("Phantasmal Forces", 69, Rarity.UNCOMMON, mage.cards.p.PhantasmalForces.class));
        cards.add(new SetCardInfo("Phantasmal Terrain", 70, Rarity.COMMON, mage.cards.p.PhantasmalTerrain.class));
        cards.add(new SetCardInfo("Phantom Monster", 71, Rarity.UNCOMMON, mage.cards.p.PhantomMonster.class));
        cards.add(new SetCardInfo("Pirate Ship", 72, Rarity.RARE, mage.cards.p.PirateShip.class));
        cards.add(new SetCardInfo("Plague Rats", 123, Rarity.COMMON, mage.cards.p.PlagueRats.class));
        cards.add(new SetCardInfo("Plains", 292, Rarity.LAND, mage.cards.basiclands.Plains.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("Plains", 293, Rarity.LAND, mage.cards.basiclands.Plains.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("Plains", 294, Rarity.LAND, mage.cards.basiclands.Plains.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("Plateau", 284, Rarity.RARE, mage.cards.p.Plateau.class));
        cards.add(new SetCardInfo("Power Leak", 73, Rarity.COMMON, mage.cards.p.PowerLeak.class));
        cards.add(new SetCardInfo("Power Sink", 74, Rarity.COMMON, mage.cards.p.PowerSink.class));
        cards.add(new SetCardInfo("Power Surge", 170, Rarity.RARE, mage.cards.p.PowerSurge.class));
        cards.add(new SetCardInfo("Primal Clay", 271, Rarity.RARE, mage.cards.p.PrimalClay.class));
        cards.add(new SetCardInfo("Prodigal Sorcerer", 75, Rarity.COMMON, mage.cards.p.ProdigalSorcerer.class));
        cards.add(new SetCardInfo("Psychic Venom", 76, Rarity.COMMON, mage.cards.p.PsychicVenom.class));
        cards.add(new SetCardInfo("Purelace", 32, Rarity.RARE, mage.cards.p.Purelace.class));
        cards.add(new SetCardInfo("Raise Dead", 124, Rarity.COMMON, mage.cards.r.RaiseDead.class));
        cards.add(new SetCardInfo("Reconstruction", 77, Rarity.COMMON, mage.cards.r.Reconstruction.class));
        cards.add(new SetCardInfo("Red Elemental Blast", 171, Rarity.COMMON, mage.cards.r.RedElementalBlast.class));
        cards.add(new SetCardInfo("Red Ward", 33, Rarity.UNCOMMON, mage.cards.r.RedWard.class));
        cards.add(new SetCardInfo("Regeneration", 213, Rarity.COMMON, mage.cards.r.Regeneration.class));
        cards.add(new SetCardInfo("Regrowth", 214, Rarity.UNCOMMON, mage.cards.r.Regrowth.class));
        cards.add(new SetCardInfo("Resurrection", 34, Rarity.UNCOMMON, mage.cards.r.Resurrection.class));
        cards.add(new SetCardInfo("Reverse Damage", 35, Rarity.RARE, mage.cards.r.ReverseDamage.class));
        cards.add(new SetCardInfo("Reverse Polarity", 36, Rarity.UNCOMMON, mage.cards.r.ReversePolarity.class));
        cards.add(new SetCardInfo("Righteousness", 37, Rarity.RARE, mage.cards.r.Righteousness.class));
        cards.add(new SetCardInfo("Roc of Kher Ridges", 172, Rarity.RARE, mage.cards.r.RocOfKherRidges.class));
        cards.add(new SetCardInfo("Rock Hydra", 173, Rarity.RARE, mage.cards.r.RockHydra.class));
        cards.add(new SetCardInfo("Rocket Launcher", 272, Rarity.RARE, mage.cards.r.RocketLauncher.class));
        cards.add(new SetCardInfo("Rod of Ruin", 273, Rarity.UNCOMMON, mage.cards.r.RodOfRuin.class));
        cards.add(new SetCardInfo("Royal Assassin", 125, Rarity.RARE, mage.cards.r.RoyalAssassin.class));
        cards.add(new SetCardInfo("Sacrifice", 126, Rarity.UNCOMMON, mage.cards.s.Sacrifice.class));
        cards.add(new SetCardInfo("Samite Healer", 38, Rarity.COMMON, mage.cards.s.SamiteHealer.class));
        cards.add(new SetCardInfo("Savannah Lions", 39, Rarity.RARE, mage.cards.s.SavannahLions.class));
        cards.add(new SetCardInfo("Savannah", 285, Rarity.RARE, mage.cards.s.Savannah.class));
        cards.add(new SetCardInfo("Scathe Zombies", 127, Rarity.COMMON, mage.cards.s.ScatheZombies.class));
        cards.add(new SetCardInfo("Scavenging Ghoul", 128, Rarity.UNCOMMON, mage.cards.s.ScavengingGhoul.class));
        cards.add(new SetCardInfo("Scrubland", 286, Rarity.RARE, mage.cards.s.Scrubland.class));
        cards.add(new SetCardInfo("Scryb Sprites", 215, Rarity.COMMON, mage.cards.s.ScrybSprites.class));
        cards.add(new SetCardInfo("Sea Serpent", 78, Rarity.COMMON, mage.cards.s.SeaSerpent.class));
        cards.add(new SetCardInfo("Sedge Troll", 174, Rarity.RARE, mage.cards.s.SedgeTroll.class));
        cards.add(new SetCardInfo("Sengir Vampire", 129, Rarity.UNCOMMON, mage.cards.s.SengirVampire.class));
        cards.add(new SetCardInfo("Serendib Efreet", 79, Rarity.RARE, mage.cards.s.SerendibEfreet.class));
        cards.add(new SetCardInfo("Serra Angel", 40, Rarity.UNCOMMON, mage.cards.s.SerraAngel.class));
        cards.add(new SetCardInfo("Shanodin Dryads", 216, Rarity.COMMON, mage.cards.s.ShanodinDryads.class));
        cards.add(new SetCardInfo("Shatter", 175, Rarity.COMMON, mage.cards.s.Shatter.class));
        cards.add(new SetCardInfo("Shatterstorm", 176, Rarity.UNCOMMON, mage.cards.s.Shatterstorm.class));
        cards.add(new SetCardInfo("Shivan Dragon", 177, Rarity.RARE, mage.cards.s.ShivanDragon.class));
        cards.add(new SetCardInfo("Simulacrum", 130, Rarity.UNCOMMON, mage.cards.s.Simulacrum.class));
        cards.add(new SetCardInfo("Siren's Call", 80, Rarity.UNCOMMON, mage.cards.s.SirensCall.class));
        cards.add(new SetCardInfo("Smoke", 178, Rarity.RARE, mage.cards.s.Smoke.class));
        cards.add(new SetCardInfo("Sol Ring", 274, Rarity.UNCOMMON, mage.cards.s.SolRing.class));
        cards.add(new SetCardInfo("Sorceress Queen", 131, Rarity.RARE, mage.cards.s.SorceressQueen.class));
        cards.add(new SetCardInfo("Soul Net", 275, Rarity.UNCOMMON, mage.cards.s.SoulNet.class));
        cards.add(new SetCardInfo("Spell Blast", 82, Rarity.COMMON, mage.cards.s.SpellBlast.class));
        cards.add(new SetCardInfo("Stasis", 83, Rarity.RARE, mage.cards.s.Stasis.class));
        cards.add(new SetCardInfo("Steal Artifact", 84, Rarity.UNCOMMON, mage.cards.s.StealArtifact.class));
        cards.add(new SetCardInfo("Stone Giant", 179, Rarity.UNCOMMON, mage.cards.s.StoneGiant.class));
        cards.add(new SetCardInfo("Stone Rain", 180, Rarity.COMMON, mage.cards.s.StoneRain.class));
        cards.add(new SetCardInfo("Stream of Life", 217, Rarity.COMMON, mage.cards.s.StreamOfLife.class));
        cards.add(new SetCardInfo("Sunglasses of Urza", 276, Rarity.RARE, mage.cards.s.SunglassesOfUrza.class));
        cards.add(new SetCardInfo("Swamp", 298, Rarity.LAND, mage.cards.basiclands.Swamp.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("Swamp", 299, Rarity.LAND, mage.cards.basiclands.Swamp.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("Swamp", 300, Rarity.LAND, mage.cards.basiclands.Swamp.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("Swords to Plowshares", 41, Rarity.UNCOMMON, mage.cards.s.SwordsToPlowshares.class));
        cards.add(new SetCardInfo("Taiga", 287, Rarity.RARE, mage.cards.t.Taiga.class));
        cards.add(new SetCardInfo("Terror", 132, Rarity.COMMON, mage.cards.t.Terror.class));
        cards.add(new SetCardInfo("The Hive", 277, Rarity.RARE, mage.cards.t.TheHive.class));
        cards.add(new SetCardInfo("The Rack", 278, Rarity.UNCOMMON, mage.cards.t.TheRack.class));
        cards.add(new SetCardInfo("Thicket Basilisk", 218, Rarity.UNCOMMON, mage.cards.t.ThicketBasilisk.class));
        cards.add(new SetCardInfo("Thoughtlace", 85, Rarity.RARE, mage.cards.t.Thoughtlace.class));
        cards.add(new SetCardInfo("Throne of Bone", 279, Rarity.UNCOMMON, mage.cards.t.ThroneOfBone.class));
        cards.add(new SetCardInfo("Timber Wolves", 219, Rarity.RARE, mage.cards.t.TimberWolves.class));
        cards.add(new SetCardInfo("Titania's Song", 220, Rarity.RARE, mage.cards.t.TitaniasSong.class));
        cards.add(new SetCardInfo("Tranquility", 221, Rarity.COMMON, mage.cards.t.Tranquility.class));
        cards.add(new SetCardInfo("Tropical Island", 288, Rarity.RARE, mage.cards.t.TropicalIsland.class));
        cards.add(new SetCardInfo("Tsunami", 222, Rarity.UNCOMMON, mage.cards.t.Tsunami.class));
        cards.add(new SetCardInfo("Tundra", 289, Rarity.RARE, mage.cards.t.Tundra.class));
        cards.add(new SetCardInfo("Tunnel", 181, Rarity.UNCOMMON, mage.cards.t.Tunnel.class));
        cards.add(new SetCardInfo("Underground Sea", 290, Rarity.RARE, mage.cards.u.UndergroundSea.class));
        cards.add(new SetCardInfo("Unholy Strength", 133, Rarity.COMMON, mage.cards.u.UnholyStrength.class));
        cards.add(new SetCardInfo("Unstable Mutation", 86, Rarity.COMMON, mage.cards.u.UnstableMutation.class));
        cards.add(new SetCardInfo("Unsummon", 87, Rarity.COMMON, mage.cards.u.Unsummon.class));
        cards.add(new SetCardInfo("Uthden Troll", 182, Rarity.UNCOMMON, mage.cards.u.UthdenTroll.class));
        cards.add(new SetCardInfo("Verduran Enchantress", 223, Rarity.RARE, mage.cards.v.VerduranEnchantress.class));
        cards.add(new SetCardInfo("Vesuvan Doppelganger", 88, Rarity.RARE, mage.cards.v.VesuvanDoppelganger.class));
        cards.add(new SetCardInfo("Veteran Bodyguard", 42, Rarity.RARE, mage.cards.v.VeteranBodyguard.class));
        cards.add(new SetCardInfo("Volcanic Eruption", 89, Rarity.RARE, mage.cards.v.VolcanicEruption.class));
        cards.add(new SetCardInfo("Volcanic Island", 291, Rarity.RARE, mage.cards.v.VolcanicIsland.class));
        cards.add(new SetCardInfo("Wall of Air", 90, Rarity.UNCOMMON, mage.cards.w.WallOfAir.class));
        cards.add(new SetCardInfo("Wall of Bone", 134, Rarity.UNCOMMON, mage.cards.w.WallOfBone.class));
        cards.add(new SetCardInfo("Wall of Brambles", 224, Rarity.UNCOMMON, mage.cards.w.WallOfBrambles.class));
        cards.add(new SetCardInfo("Wall of Fire", 183, Rarity.UNCOMMON, mage.cards.w.WallOfFire.class));
        cards.add(new SetCardInfo("Wall of Ice", 225, Rarity.UNCOMMON, mage.cards.w.WallOfIce.class));
        cards.add(new SetCardInfo("Wall of Stone", 184, Rarity.UNCOMMON, mage.cards.w.WallOfStone.class));
        cards.add(new SetCardInfo("Wall of Swords", 43, Rarity.UNCOMMON, mage.cards.w.WallOfSwords.class));
        cards.add(new SetCardInfo("Wall of Water", 91, Rarity.UNCOMMON, mage.cards.w.WallOfWater.class));
        cards.add(new SetCardInfo("Wall of Wood", 226, Rarity.COMMON, mage.cards.w.WallOfWood.class));
        cards.add(new SetCardInfo("Wanderlust", 227, Rarity.UNCOMMON, mage.cards.w.Wanderlust.class));
        cards.add(new SetCardInfo("War Mammoth", 228, Rarity.COMMON, mage.cards.w.WarMammoth.class));
        cards.add(new SetCardInfo("Warp Artifact", 135, Rarity.RARE, mage.cards.w.WarpArtifact.class));
        cards.add(new SetCardInfo("Water Elemental", 92, Rarity.UNCOMMON, mage.cards.w.WaterElemental.class));
        cards.add(new SetCardInfo("Weakness", 136, Rarity.COMMON, mage.cards.w.Weakness.class));
        cards.add(new SetCardInfo("Web", 229, Rarity.RARE, mage.cards.w.Web.class));
        cards.add(new SetCardInfo("Wheel of Fortune", 185, Rarity.RARE, mage.cards.w.WheelOfFortune.class));
        cards.add(new SetCardInfo("White Knight", 44, Rarity.UNCOMMON, mage.cards.w.WhiteKnight.class));
        cards.add(new SetCardInfo("White Ward", 45, Rarity.UNCOMMON, mage.cards.w.WhiteWard.class));
        cards.add(new SetCardInfo("Wild Growth", 230, Rarity.COMMON, mage.cards.w.WildGrowth.class));
        cards.add(new SetCardInfo("Will-o'-the-Wisp", 137, Rarity.RARE, mage.cards.w.WillOTheWisp.class));
        cards.add(new SetCardInfo("Winter Orb", 280, Rarity.RARE, mage.cards.w.WinterOrb.class));
        cards.add(new SetCardInfo("Wooden Sphere", 281, Rarity.UNCOMMON, mage.cards.w.WoodenSphere.class));
        cards.add(new SetCardInfo("Wrath of God", 46, Rarity.RARE, mage.cards.w.WrathOfGod.class));
        cards.add(new SetCardInfo("Zombie Master", 138, Rarity.RARE, mage.cards.z.ZombieMaster.class));
    }
}
