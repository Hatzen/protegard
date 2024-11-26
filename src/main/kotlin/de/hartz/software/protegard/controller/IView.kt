package de.hartz.software.protegard.controller

import de.hartz.software.protegard.model.interfaces.Identifieable

interface IView {

    fun startChapter(i: Int)
    fun listenForUserInput()

    fun addText(text: String, source: Identifieable)
    fun getChoice(): Int
    fun <T> getMultipleChoice(choice: List<T>): List<T>
    fun addText(text: String)
}