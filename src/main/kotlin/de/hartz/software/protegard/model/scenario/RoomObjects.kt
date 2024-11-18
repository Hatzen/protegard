package de.hartz.software.protegard.model.scenario

import de.hartz.software.protegard.model.scenario.roomobjects.castle.attic.*
import de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry.CastleIvy
import de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry.CastleTower
import de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry.StonePlate
import de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry.Well
import de.hartz.software.protegard.model.scenario.roomobjects.castle.utilityroom.Broom
import de.hartz.software.protegard.model.scenario.roomobjects.castle.utilityroom.Ladder
import de.hartz.software.protegard.model.scenario.roomobjects.castle.utilityroom.SaltBucket
import de.hartz.software.protegard.model.scenario.roomobjects.castle.utilityroom.Workbench
import de.hartz.software.protegard.model.scenario.roomobjects.village.villageentry.Bench
import de.hartz.software.protegard.model.scenario.roomobjects.village.villageentry.NewspaperStore
import de.hartz.software.protegard.model.scenario.roomobjects.village.villageentry.RareCoin

// TODO: Do we need this?
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
    val ladder = Ladder()
    val saltBucket = SaltBucket()
    val workBench = Workbench()

}