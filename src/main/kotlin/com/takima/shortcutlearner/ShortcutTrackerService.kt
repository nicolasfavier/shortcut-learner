package com.takima.shortcutlearner

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.refactoring.listeners.RefactoringEventData
import com.intellij.refactoring.listeners.RefactoringEventListener
import com.intellij.util.messages.MessageBusConnection
import javax.swing.SwingUtilities

class ShortcutTrackerService() {

    fun initService(project: Project) {
        val toolWindow = ToolWindowManager.getInstance(project).getToolWindow("ShortcutLearner")
        val shortcutPanel = toolWindow?.contentManager?.getContent(0)?.component as? ShortcutLearnerPanel

        val connection: MessageBusConnection = project.messageBus.connect()


        connection.subscribe(RefactoringEventListener.REFACTORING_EVENT_TOPIC, object : RefactoringEventListener {
            override fun refactoringStarted(refactoringId: String, beforeData: RefactoringEventData?) {
                val actionId = "ExtractMethod"
                if (actionId == "ExtractMethod") {
                    shortcutPanel?.markShortcutCompleted(Shortcut.EXTRACT_METHOD)
                }
            }

            override fun refactoringDone(refactoringId: String, afterData: RefactoringEventData?) {
                // Optionnel : après le refactoring
            }
        })
    }
}