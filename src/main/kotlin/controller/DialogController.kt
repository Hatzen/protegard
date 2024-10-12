package org.example.controller

import org.example.model.Dialog

class DialogController(private var dialog: Dialog, private val view: IView) {

    private var previousDialog: Dialog? = null

    init {
        while (true) {
            val text = dialog.text
            // TODO: This is not correct, probably we need both, source and target. Or we just pass it down from first target, but it will switch..
            val sourceUser = dialog.target
            view.addText(text, sourceUser)
            val answers = dialog.answers ?: break
            val validAnswers = answers.filter { it.precondition() }.toMutableList()
            validAnswers.forEachIndexed { index, dialog ->
                val answer = dialog.text
                view.addText("$index. $answer")
            }
            getAnswer(validAnswers)
        }

    }

    private fun getAnswer(answers: MutableList<Dialog>) {
        while (true) {
            val choice = view.getChoice()
            if (choice in 0..answers.size) {
                if (dialog.onlyOnce) {
                    val previousDialog = previousDialog
                    // It is the initial characters dialog.
                    if (previousDialog == null) {
                        dialog.target.dialogs = null
                    } else {
                        previousDialog.answers!!.remove(dialog)
                    }
                }
                previousDialog = dialog
                dialog = answers[choice]
                break
            }
        }
    }

}