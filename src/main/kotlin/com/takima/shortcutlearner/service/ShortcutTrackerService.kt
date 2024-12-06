package com.takima.shortcutlearner.service

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ex.AnActionListener
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.util.messages.MessageBusConnection
import com.takima.shortcutlearner.model.Shortcut
import com.takima.shortcutlearner.state.ShortcutStateService
import com.takima.shortcutlearner.view.ShortcutLearnerPanel

private const val SHORT_CUT_LEARNER_WINDOW_ID = "ShortcutLearner"

@Service(Service.Level.PROJECT)
class ShortcutTrackerService {

    fun initService(project: Project) {
        val toolWindow = ToolWindowManager.getInstance(project).getToolWindow(SHORT_CUT_LEARNER_WINDOW_ID)
        val shortcutPanel = toolWindow?.contentManager?.getContent(0)?.component as? ShortcutLearnerPanel
        val connection: MessageBusConnection = project.messageBus.connect()
        val shortcutStateService = ShortcutStateService.getInstance(project)

        connection.subscribe(AnActionListener.TOPIC, object : AnActionListener {
            override fun beforeActionPerformed(action: AnAction, event: AnActionEvent) {
                super.beforeActionPerformed(action, event)
                val actionId = event.actionManager.getId(action)
                val shortcut = Shortcut.entries.find { it.actionId == actionId }
                if (shortcut != null) {
                    updateShortcutCount(shortcut, shortcutPanel)
                }
            }

            private fun updateShortcutCount(
                shortcut: Shortcut,
                shortcutPanel: ShortcutLearnerPanel?
            ) {
                val count = shortcutStateService.incrementShortcutCount(shortcut)
                shortcutPanel?.updateShortcutCountDisplay(shortcut, count)
            }
        })
    }
}
