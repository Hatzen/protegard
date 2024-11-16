package de.hartz.software.protegard.model.scenario

import de.hartz.software.protegard.model.scenario.characters.main.*
import de.hartz.software.protegard.model.scenario.characters.side.*
import de.hartz.software.protegard.model.scenario.characters.side.village.*

object Characters {
    val MAIN_CHARACTER = JonasLindgrenTheMainCharacter()
    val NARRATOR = Narrator()

    // Main Characters.
    val AKSEL_BRANDT_THE_GUARD = AkselBrandtTheGuard()
    val ARVID_HOLM_AGEOLOGIST = ArvidHolmAGeologist()
    val INGRID_STJERNBERG_AHISTORIAN = IngridStjernbergAHistorian()
    val JONAS_LINDGREN_THE_MAIN_CHARACTER = JonasLindgrenTheMainCharacter()
    val RAGNHILD_ERIKSDOTTIR_ANATIVE_SHAMAN = RagnhildEriksdottirANativeShaman()

    // Side characters.
    val ASTRID_HANSEN_LADY_OF_THE_CASTLE = AstridHansenLadyOfTheCastle()
    val KARIN_SVENSSON_AGOUVERNEUR = KarinSvenssonAGouverneur()
    val KNUT_JORGENSEN_AJANITOR = KnutJorgensenAJanitor()
    val LARS_FREDRIKSSON_APRIEST = LarsFredrikssonAPriest()
    val OLAF_HAKON_AHUNTSMAN = OlafHakonAHuntsman()
    val ROLF_JOHANSEN_AN_ADVENTURER = RolfJohansenAnAdventurer()
    val SIGNE_DAHL_AN_ARTIST = SigneDahlAnArtist()
    val TOVE_NILSSON_AMAID = ToveNilssonAMaid()

    // Side chars village
    val TAXI_DRIVER = TaxiDriver()
    val BRITA_HOLMQVIST_ADOCTOR = BritaHolmqvistADoctor()
    val DAGMAR_VIK_APHARMACIST = DagmarVikAPharmacist()
    val EINAR_OLSEN_THE_VILLAGE_ELDERY = EinarOlsenTheVillageEldery()
    val ERIK_LUND_AFISHER = ErikLundAFisher()
    val HELGE_STROM_AFOREIGNER = HelgeStromAForeigner()


}