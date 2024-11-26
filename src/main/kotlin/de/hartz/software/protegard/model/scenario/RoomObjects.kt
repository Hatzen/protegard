package de.hartz.software.protegard.model.scenario

import de.hartz.software.protegard.model.scenario.roomobjects.castle.attic.*
import de.hartz.software.protegard.model.scenario.roomobjects.castle.basement.BigBoiler
import de.hartz.software.protegard.model.scenario.roomobjects.castle.basement.CoalPile
import de.hartz.software.protegard.model.scenario.roomobjects.castle.basement.Pump
import de.hartz.software.protegard.model.scenario.roomobjects.castle.basement.SpiderNet
import de.hartz.software.protegard.model.scenario.roomobjects.castle.bath.*
import de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry.CastleIvy
import de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry.CastleTower
import de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry.StonePlate
import de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry.Well
import de.hartz.software.protegard.model.scenario.roomobjects.castle.chapel.*
import de.hartz.software.protegard.model.scenario.roomobjects.castle.diningroom.Barometer
import de.hartz.software.protegard.model.scenario.roomobjects.castle.diningroom.DiningTable
import de.hartz.software.protegard.model.scenario.roomobjects.castle.diningroom.ServingBell
import de.hartz.software.protegard.model.scenario.roomobjects.castle.diningroom.Sideboard
import de.hartz.software.protegard.model.scenario.roomobjects.castle.hall.Chandelier
import de.hartz.software.protegard.model.scenario.roomobjects.castle.hall.HallPainting
import de.hartz.software.protegard.model.scenario.roomobjects.castle.hall.Sofa
import de.hartz.software.protegard.model.scenario.roomobjects.castle.hall.Tapestries
import de.hartz.software.protegard.model.scenario.roomobjects.castle.kitchen.*
import de.hartz.software.protegard.model.scenario.roomobjects.castle.library.*
import de.hartz.software.protegard.model.scenario.roomobjects.castle.lobby.Chesstable
import de.hartz.software.protegard.model.scenario.roomobjects.castle.lobby.CoatRack
import de.hartz.software.protegard.model.scenario.roomobjects.castle.lobby.OrnamentalClock
import de.hartz.software.protegard.model.scenario.roomobjects.castle.lobby.StoneStatue
import de.hartz.software.protegard.model.scenario.roomobjects.castle.pantry.*
import de.hartz.software.protegard.model.scenario.roomobjects.castle.secretstudyroom.BunchOfManusscripts
import de.hartz.software.protegard.model.scenario.roomobjects.castle.secretstudyroom.TinyGlowingArtifact
import de.hartz.software.protegard.model.scenario.roomobjects.castle.sleepingroom.Bed
import de.hartz.software.protegard.model.scenario.roomobjects.castle.sleepingroom.BedSideTable
import de.hartz.software.protegard.model.scenario.roomobjects.castle.sleepingroom.PersonalChest
import de.hartz.software.protegard.model.scenario.roomobjects.castle.studyroom.Desk
import de.hartz.software.protegard.model.scenario.roomobjects.castle.studyroom.MetalTray
import de.hartz.software.protegard.model.scenario.roomobjects.castle.tower.PigeonCoop
import de.hartz.software.protegard.model.scenario.roomobjects.castle.tower.WindChimes
import de.hartz.software.protegard.model.scenario.roomobjects.castle.utilityroom.*
import de.hartz.software.protegard.model.scenario.roomobjects.village.villageentry.Bench
import de.hartz.software.protegard.model.scenario.roomobjects.village.villageentry.NewspaperStore
import de.hartz.software.protegard.model.scenario.roomobjects.village.villageentry.RareCoin

object RoomObjects {
    // Village entry.
    val bench = Bench()
    val newspaperStore = NewspaperStore()
    val rareCoin = RareCoin()

    // attic
    val painting = Painting()
    val piano = Piano()
    val pileOfNewspaper = PileOfNewspaper()
    val storageRack = StorageRack()
    val woodenBoxes = WoodenBoxes()
    val woodenChest = WoodenChest()

    // Castleentry.
    val well = Well()
    val castleIvy = CastleIvy()
    val stonePlate = StonePlate()
    val castleTower = CastleTower()

    // Utilityroom
    val broom = Broom()
    val brush = Brush()
    val ladder = Ladder()
    val saltBucket = SaltBucket()
    val workBench = Workbench()

    // Basement
    val bigBoiler = BigBoiler()
    val coalPile = CoalPile()
    val pump = Pump()
    val spiderNet = SpiderNet()

    // bath
    val bathtub = Bathtub()
    val boiler = Boiler()
    val mirror = Mirror()
    val radiator = Radiator()
    val sink = Sink()
    val toilet = Toilet()

    // chapel
    val altar = Altar()
    val bunchOfMetalRods = BunchOfMetalRods()
    val rope = Rope()
    val stainedGlassWindow = StainedGlassWindow()
    val woodenObelisk = WoodenObelisk()

    // diningroom
    val barometer = Barometer()
    val diningTable = DiningTable()
    val servingBell = ServingBell()
    val sideboard = Sideboard()

    // hall
    val chandelier = Chandelier()
    val hallPaintingStars = HallPainting("Dark night sky")
    val hallPaintingDarkforest = HallPainting("Dark forest with a evil silouhette with red eyes.")
    val hallPaintingSirHansen = HallPainting("Sir Hansen the lord of the castle")
    val sofa = Sofa()
    val tapestries = Tapestries()

    // kitchen
    val chimney = Chimney()
    val fireStoven = FireStoven()
    val icebox = Icebox()
    val ironSink = IronSink()
    val workingTable = WorkingTable()

    // library
    val bookShelve = BookShelve()
    val globe = Globe()
    val knightArmor = KnightArmor()
    val readingNook = ReadingNook()
    val writingDesk = WritingDesk()

    // lobby
    val coatRack = CoatRack()
    val ornamentalClock = OrnamentalClock()
    val stoneStatue = StoneStatue()
    val chesstable = Chesstable()

    // pantry
    val cheeseWheel = CheeseWheel()
    val flour = Flour()
    val glassJars = GlassJars()
    val weighingScale = WeighingScale()
    val weights = Weights()
    val woodenBarrel = WoodenBarrel()

    // secretstudyroom
    val bunchOfManusscripts = BunchOfManusscripts()
    val tinyGlowingArtifact = TinyGlowingArtifact()

    // sleepingroom
    val bed = Bed()
    val bedSideTable = BedSideTable()
    val personalChest = PersonalChest()

    // studyroom
    val desk = Desk()
    val metalTray = MetalTray()

    // tower
    val pigeonCoop = PigeonCoop()
    val windChimes = WindChimes()


}