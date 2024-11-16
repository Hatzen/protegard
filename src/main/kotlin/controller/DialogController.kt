package org.example.controller

import org.example.model.common.Dialog

class DialogController(private var dialog: Dialog, private val view: IView) {

    private var previousDialog: Dialog? = null

    private var dialogFinished = false

    init {
        while (!dialogFinished) {
            handleDialog()
        }
    }

    private fun handleDialog() {
        val text = dialog.text
        // TODO: This is not correct, probably we need both, source and target. Or we just pass it down from first target, but it will switch..
        val sourceUser = dialog.target
        view.addText(text, sourceUser)
        val answers = dialog.answers
        if (answers == null) {
            endDialog()
            return
        }
        val validAnswers = answers.filter { it.precondition() }.toMutableList()
        if (validAnswers.size == 1) {
            val nextDialog = validAnswers[0]
            continueWithDialog(nextDialog)
            return
        }
        validAnswers.forEachIndexed { index, dialog ->
            val answer = dialog.text
            view.addText("$index. $answer")
        }
        getAnswer(validAnswers)
    }

    private fun endDialog() {
        dialogFinished = true
    }

    private fun continueWithDialog(nextDialog: Dialog) {
        previousDialog = dialog
        dialog.callback()
        dialog = nextDialog
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
                val nextDialog = answers[choice]
                continueWithDialog(nextDialog)
                break
            }
        }
    }

}