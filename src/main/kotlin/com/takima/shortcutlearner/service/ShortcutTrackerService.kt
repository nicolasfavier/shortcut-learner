package com.takima.shortcutlearner.service

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ex.AnActionListener
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.util.messages.MessageBusConnection
import com.takima.shortcutlearner.model.Shortcut
import com.takima.shortcutlearner.view.ShortcutLearnerPanel

private const val SHORT_CUT_LEARNER_WINDOW_ID = "ShortcutLearner"

@Service(Service.Level.PROJECT)
class ShortcutTrackerService {

    fun initService(project: Project) {
        val toolWindow = ToolWindowManager.getInstance(project).getToolWindow(SHORT_CUT_LEARNER_WINDOW_ID)
        val shortcutPanel = toolWindow?.contentManager?.getContent(0)?.component as? ShortcutLearnerPanel
        val connection: MessageBusConnection = project.messageBus.connect()

        connection.subscribe(AnActionListener.TOPIC, object : AnActionListener {
            override fun beforeActionPerformed(action: AnAction, event: AnActionEvent) {
                super.beforeActionPerformed(action, event)

                when (event.actionManager.getId(action)) {
                    "IntroduceVariable" -> shortcutPanel?.markShortcutCompleted(Shortcut.EXTRACT_VARIABLE)
                    "IntroduceConstant" -> shortcutPanel?.markShortcutCompleted(Shortcut.EXTRACT_CONST)
                    "ExtractMethod" -> shortcutPanel?.markShortcutCompleted(Shortcut.EXTRACT_METHOD)
                    "IntroduceParameter" -> shortcutPanel?.markShortcutCompleted(Shortcut.EXTRACT_PARAM)
                    "RenameElement" -> shortcutPanel?.markShortcutCompleted(Shortcut.RENAME)
                    "EditorDuplicate" -> shortcutPanel?.markShortcutCompleted(Shortcut.DUPLICATE)
                    "SearchEverywhere" -> shortcutPanel?.markShortcutCompleted(Shortcut.FIND_ACTION)
                    "RecentFiles" -> shortcutPanel?.markShortcutCompleted(Shortcut.NAVIGATE_RECENT_FILE)
                    "Back" -> shortcutPanel?.markShortcutCompleted(Shortcut.NAVIGATE_TO_PREVIOUS_LOCATION)
                    "Forward" -> shortcutPanel?.markShortcutCompleted(Shortcut.NAVIGATE_TO_NEXT_LOCATION)
                    "SelectNextOccurrence" -> shortcutPanel?.markShortcutCompleted(Shortcut.SELECT_NEXT_OCCURRENCE)
                    "EditorSelectWord" -> shortcutPanel?.markShortcutCompleted(Shortcut.EXTEND_SELECTION)
                    "CloseEditor" -> shortcutPanel?.markShortcutCompleted(Shortcut.CLOSE)
                    "ReformatCode" -> shortcutPanel?.markShortcutCompleted(Shortcut.FORMAT)
                    "OptimizeImports" -> shortcutPanel?.markShortcutCompleted(Shortcut.OPTIMISE_IMPORT)
                    "FindInPath" -> shortcutPanel?.markShortcutCompleted(Shortcut.FULL_SEARCH)
                    "PasteMultiple" -> shortcutPanel?.markShortcutCompleted(Shortcut.COPY_HISTORY)
                    "CommentByLineComment" -> shortcutPanel?.markShortcutCompleted(Shortcut.COMMENT)
                }
                if (action.templateText.equals("Close Tab")) {
                    shortcutPanel?.markShortcutCompleted(Shortcut.CLOSE)
                }
            }
        })
    }
}