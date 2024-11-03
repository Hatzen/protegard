package org.example.controller

import org.example.model.interfaces.Identifieable

interface IView {

    fun start()

    fun addText(text: String, source: Identifieable)
    fun getChoice(): Int
    fun addText(text: String)
}