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

                when (event.actionManager.getId(action)) {
                    "IntroduceVariable" -> updateShortcutCount(Shortcut.EXTRACT_VARIABLE, shortcutPanel)
                    "IntroduceConstant" -> updateShortcutCount(Shortcut.EXTRACT_CONST, shortcutPanel)
                    "ExtractMethod" -> updateShortcutCount(Shortcut.EXTRACT_METHOD, shortcutPanel)
                    "IntroduceParameter" -> updateShortcutCount(Shortcut.EXTRACT_PARAM, shortcutPanel)
                    "RenameElement" -> updateShortcutCount(Shortcut.RENAME, shortcutPanel)
                    "EditorDuplicate" -> updateShortcutCount(Shortcut.DUPLICATE, shortcutPanel)
                    "SearchEverywhere" -> updateShortcutCount(Shortcut.FIND_ACTION, shortcutPanel)
                    "RecentFiles" -> updateShortcutCount(Shortcut.NAVIGATE_RECENT_FILE, shortcutPanel)
                    "Back" -> updateShortcutCount(Shortcut.NAVIGATE_TO_PREVIOUS_LOCATION, shortcutPanel)
                    "Forward" -> updateShortcutCount(Shortcut.NAVIGATE_TO_NEXT_LOCATION, shortcutPanel)
                    "SelectNextOccurrence" -> updateShortcutCount(Shortcut.SELECT_NEXT_OCCURRENCE, shortcutPanel)
                    "EditorSelectWord" -> updateShortcutCount(Shortcut.EXTEND_SELECTION, shortcutPanel)
                    "CloseEditor" -> updateShortcutCount(Shortcut.CLOSE, shortcutPanel)
                    "ReformatCode" -> updateShortcutCount(Shortcut.FORMAT, shortcutPanel)
                    "OptimizeImports" -> updateShortcutCount(Shortcut.OPTIMISE_IMPORT, shortcutPanel)
                    "FindInPath" -> updateShortcutCount(Shortcut.FULL_SEARCH, shortcutPanel)
                    "PasteMultiple" -> updateShortcutCount(Shortcut.COPY_HISTORY, shortcutPanel)
                    "CommentByLineComment" -> updateShortcutCount(Shortcut.COMMENT, shortcutPanel)
                }
                if (action.templateText.equals("Close Tab")) {
                    updateShortcutCount(Shortcut.CLOSE, shortcutPanel)
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
