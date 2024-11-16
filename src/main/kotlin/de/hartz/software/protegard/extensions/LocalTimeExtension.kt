package de.hartz.software.protegard.extensions

import java.time.LocalTime

fun LocalTime.format(): String {
    return this.toString()
}