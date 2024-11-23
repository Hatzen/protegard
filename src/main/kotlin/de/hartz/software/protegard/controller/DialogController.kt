package de.hartz.software.protegard.controller

import de.hartz.software.protegard.model.common.dialog.Dialog

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
        val sourceUser = dialog.source
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
        dialog = nextDialog
        dialog.callback()
    }

    private fun getAnswer(answers: List<Dialog>) {
        while (true) {
            val choice = view.getChoice()
            if (choice in 0 until answers.size) {
                if (dialog.onlyOnce) {
                    handleOnlyOnceDialogs()
                }
                val nextDialog = answers[choice]
                continueWithDialog(nextDialog)
                break
            }
            view.addText("Thats not a valid choice.")
        }
    }

    private fun handleOnlyOnceDialogs() {
        val previousDialog = previousDialog
        // It is the initial characters dialog.
        if (previousDialog == null) {
            // Only once for root dialog.
            if (dialog.target.dialogs == dialog) {
                dialog.target.removeRootDialog()
            } else if (dialog.source.dialogs == dialog) {
                dialog.source.removeRootDialog()
            } else {
                throw RuntimeException("Dialog did not belong to anyone, could not remove it..")
            }
        } else {
            previousDialog.answers!!.remove(dialog)
        }
    }

}