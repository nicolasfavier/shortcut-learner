package com.takima.shortcutlearner.view

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory

private const val SHORTCUTS_WINDOW_NAME = "Shortcuts"

class ShortcutLearnerToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val shortcutPanel = ShortcutLearnerPanel(project)
        toolWindow.contentManager.addContent(
            toolWindow.contentManager.factory.createContent(
                shortcutPanel,
                SHORTCUTS_WINDOW_NAME,
                false
            )
        )
    }
}