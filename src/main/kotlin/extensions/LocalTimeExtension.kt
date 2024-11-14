package org.example.extensions

import java.time.LocalTime

fun LocalTime.format(): String {
    return this.toString()
}