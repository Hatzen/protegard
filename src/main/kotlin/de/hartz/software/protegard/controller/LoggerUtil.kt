package de.hartz.software.protegard.controller

import mu.KotlinLogging

object LoggerUtil {
    val logger = KotlinLogging.logger {}

    fun init() {
        System.setProperty("slf4j.internal.verbosity", "WARN")
        logger.info("application started")
    }
}