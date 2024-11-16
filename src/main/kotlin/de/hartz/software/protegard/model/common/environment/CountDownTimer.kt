package de.hartz.software.protegard.model.common.environment

import kotlin.concurrent.timer

object CountDownTimer {

    val callbacks: MutableSet<Callback> = mutableSetOf()

    init {
        timer(initialDelay = 1000L, period = 1000L) {
            callbacks.forEach { it.callback() }
        }
    }


}

class Callback(val callback: Callback.() -> Unit, val seconds: Int = 1, val id: Int = -1) {
    var timer = seconds

    fun callback() {
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
        if (other is Callback) {
            return other.id == id
        }
        return false
    }

    override fun hashCode(): Int {
        return id
    }
}