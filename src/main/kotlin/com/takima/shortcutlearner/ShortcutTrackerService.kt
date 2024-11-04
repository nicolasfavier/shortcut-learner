package com.takima.shortcutlearner

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.refactoring.listeners.RefactoringEventData
import com.intellij.refactoring.listeners.RefactoringEventListener
import com.intellij.util.messages.MessageBusConnection
import javax.swing.SwingUtilities

class ShortcutTrackerService() {


    private var toolWindowPanel: ShortcutLearnerPanel? = null


    fun initService(project: Project) {

        val toolWindow = ToolWindowManager.getInstance(project).getToolWindow("ShortcutLearner")
        toolWindowPanel = toolWindow?.contentManager?.getContent(0)?.component as? ShortcutLearnerPanel


        val connection: MessageBusConnection = project.messageBus.connect()
        connection.subscribe(MyTopic.TOPIC, MyListener())


        connection.subscribe(RefactoringEventListener.REFACTORING_EVENT_TOPIC, object : RefactoringEventListener {
            override fun refactoringStarted(refactoringId: String, beforeData: RefactoringEventData?) {
                val actionId = "ExtractMethod"
                when (actionId) {
                    "ExtractMethod" -> markAsCompleted("Extract Method")
                    "RenameElement" -> markAsCompleted("Rename")
                    "ReformatCode" -> markAsCompleted("Reformat Code")
                }
            }

            override fun refactoringDone(refactoringId: String, afterData: RefactoringEventData?) {
                // Optionnellement, traitez les actions après l'exécution de l'extraction de méthode
            }
        })
    }

    private fun markAsCompleted(shortcut: String) {
        // Met à jour l'UI dans le thread Swing
        SwingUtilities.invokeLater {
           toolWindowPanel?.markShortcutCompleted(shortcut)
        }
    }

    private class MyListener : MyTopic {
        override fun methodExtracted() {
            // Logique lorsque l'action d'extraction de méthode est détectée
            println("Méthode extraite !")
            // Mettez à jour l'interface utilisateur pour indiquer que l'action a été utilisée
        }
    }
}