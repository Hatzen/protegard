package de.hartz.software.protegard.model.common.environment

import kotlin.concurrent.timer

object CountDownCallbackIds {
    val WATCH = 1
    val RING_THE_BELLS_GUARD_ANNOYED = 2
    val WELL_CLEANING = 3
}

object CountDownTimer {

    val callbacks: MutableSet<CountDownCallback> = mutableSetOf()

    init {
        timer(initialDelay = 1000L, period = 1000L) {
            callbacks.forEach { it.checkAndCallCallback() }
        }
    }


}

class CountDownCallback(val callback: CountDownCallback.() -> Unit, val seconds: Int = 1, val id: Int = -1) {
    var timer = seconds

    fun checkAndCallCallback() {
        timer--
        if (timer <= 0) {
            callback()
            reset()
        }
    }

    fun reset() {
        timer = seconds
    }

    override fun equals(other: Any?): Boolean {
        if (other is CountDownCallback) {
            return other.id == id
        }
        return false
    }

    override fun hashCode(): Int {
        return id
    }
}