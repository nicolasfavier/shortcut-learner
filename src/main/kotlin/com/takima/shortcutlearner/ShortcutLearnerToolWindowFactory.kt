package com.takima.shortcutlearner

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.Colors
import com.intellij.ui.components.JBCheckBox
import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.JPanel

class ShortcutLearnerToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val shortcutPanel = ShortcutLearnerPanel()
        toolWindow.contentManager.addContent(toolWindow.contentManager.factory.createContent(shortcutPanel, "Toto", false))
    }
}

class ShortcutLearnerPanel : JPanel() {
    private val shortcuts = mapOf(
        "Extract Method" to JBCheckBox("Extract Method"),
        "Rename" to JBCheckBox("Rename"),
        "Reformat Code" to JBCheckBox("Reformat Code")
    )

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        add(JLabel("Complete the following shortcuts:"))

        // Ajouter chaque raccourci comme une checkbox dans le panneau
        for ((_, checkBox) in shortcuts) {
            add(checkBox)
        }
    }

    // Méthode pour marquer le raccourci comme complété
    fun markShortcutCompleted(shortcut: String) {
        shortcuts[shortcut]?.apply {
            isSelected = true
            foreground = Colors.DARK_GREEN
        }
    }
}
