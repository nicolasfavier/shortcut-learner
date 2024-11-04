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
        val shortcutPanel = ShortcutLearnerPanel(project)
        toolWindow.contentManager.addContent(toolWindow.contentManager.factory.createContent(shortcutPanel, "Shortcuts", false))
    }
}

class ShortcutLearnerPanel(project: Project) : JPanel() {
    private val shortcutStateService = ShortcutStateService.getInstance(project)
    private val checkboxes = Shortcut.values().associateWith {
        JBCheckBox("<html>${it.displayName} : <i>${it.shortcut}</i></html>")
    }


    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        val titleLabel = JLabel("Complete the following shortcuts:")
        titleLabel.toolTipText = "This section helps you learn and practice keyboard shortcuts."
        add(titleLabel)

        for ((shortcut, checkBox) in checkboxes) {
            val isSelected = shortcutStateService.isShortcutCompleted(shortcut.displayName)
            checkBox.isSelected = isSelected
            checkBox.isFocusable = false
            checkBox.isOpaque = false
            checkBox.addActionListener { checkBox.isSelected = isSelected }


            if (checkBox.isSelected) {
                checkBox.foreground = Colors.DARK_GREEN
            }

            add(checkBox)
        }
    }

    fun markShortcutCompleted(shortcut: Shortcut) {
        shortcut.let {
            checkboxes[it]?.apply {
                isSelected = true
                foreground = Colors.DARK_GREEN
                shortcutStateService.markShortcutAsCompleted(it.displayName) // Mettre à jour l'état persistant
            }
        }
    }
}