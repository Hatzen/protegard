package de.hartz.software.protegard.model.scenario

import de.hartz.software.protegard.model.scenario.characters.main.*
import de.hartz.software.protegard.model.scenario.characters.side.*
import de.hartz.software.protegard.model.scenario.characters.side.village.*

object Characters {
    val MAIN_CHARACTER = JonasLindgrenTheMainCharacter()
    val NARRATOR = Narrator()

    // Main Characters.
    val AKSEL_BRANDT_THE_GUARD = AkselBrandtTheGuard()
    val ARVID_HOLM_A_GEOLOGIST = ArvidHolmAGeologist()
    val INGRID_STJERNBERG_A_HISTORIAN = IngridStjernbergAHistorian()
    val JONAS_LINDGREN_THE_MAIN_CHARACTER = JonasLindgrenTheMainCharacter()
    val RAGNHILD_ERIKSDOTTIR_A_NATIVE_SHAMAN = RagnhildEriksdottirANativeShaman()

    // Side characters.
    val ASTRID_HANSEN_LADY_OF_THE_CASTLE = AstridHansenLadyOfTheCastle()
    val KARIN_SVENSSON_A_GOUVERNEUR = KarinSvenssonAGouverneur()
    val KNUT_JORGENSEN_A_JANITOR = KnutJorgensenAJanitor()
    val LARS_FREDRIKSSON_A_PRIEST = LarsFredrikssonAPriest()
    val OLAF_HAKON_A_HUNTSMAN = OlafHakonAHuntsman()
    val ROLF_JOHANSEN_AN_A_DVENTURER = RolfJohansenAnAdventurer()
    val SIGNE_DAHL_AN_ARTIST = SigneDahlAnArtist()
    val TOVE_NILSSON_A_MAID = ToveNilssonAMaid()

    // Side chars village
    val TAXI_DRIVER = TaxiDriver()
    val BRITA_HOLMQVIST_A_DOCTOR = BritaHolmqvistADoctor()
    val DAGMAR_VIK_A_PHARMACIST = DagmarVikAPharmacist()
    val EINAR_OLSEN_THE_VILLAGE_ELDERY = EinarOlsenTheVillageEldery()
    val ERIK_LUND_A_FISHER = ErikLundAFisher()
    val HELGE_STROM_A_FOREIGNER = HelgeStromAForeigner()


}