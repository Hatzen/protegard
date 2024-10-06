package org.example.controller

import org.example.model.interfaces.Identifieable

interface IView {

    fun addDialog(text: String, source: Identifieable)
}