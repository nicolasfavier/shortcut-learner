package com.takima.shortcutlearner.view

import com.intellij.openapi.project.Project
import com.intellij.ui.Colors
import com.intellij.ui.components.JBCheckBox
import com.takima.shortcutlearner.model.Shortcut
import com.takima.shortcutlearner.state.ShortcutStateService
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.JPanel

class ShortcutLearnerPanel(project: Project) : JPanel() {
    private val shortcutStateService = ShortcutStateService.getInstance(project)
    private val checkboxes = mutableMapOf<Shortcut, JBCheckBox>()

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        val shortcutsByCategory = Shortcut.values().groupBy { it.cat }
        for ((category, shortcuts) in shortcutsByCategory) {
            val categoryPanel = createCategoryPanel(category, shortcuts)
            add(categoryPanel)
        }
    }

    private fun createCategoryPanel(category: String, shortcuts: List<Shortcut>): JPanel {
        val categoryPanel = JPanel()
        categoryPanel.layout = BoxLayout(categoryPanel, BoxLayout.Y_AXIS)

        shortcuts.forEach { shortcut ->
            val checkBox = createCheckbox(shortcut)
            add(checkBox)
            categoryPanel.add(checkBox)
            checkboxes[shortcut] = checkBox
        }

        val titlePanel = JPanel(FlowLayout(FlowLayout.LEFT))
        categoryPanel.border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), category)
        val titleLabel = JLabel(category)
        titleLabel.font = Font("Arial", Font.BOLD, 14)
        categoryPanel.add(titlePanel)

        return categoryPanel
    }

    private fun createCheckbox(shortcut: Shortcut): JBCheckBox {
        val checkBox = JBCheckBox("<html>${shortcut.displayName} : <i>${shortcut.shortcut}</i></html>")
        val isSelected = shortcutStateService.isShortcutCompleted(shortcut.displayName)
        checkBox.isSelected = isSelected
        checkBox.isFocusable = false
        checkBox.isOpaque = false
        checkBox.addActionListener { checkBox.isSelected = isSelected }

        if (checkBox.isSelected) {
            checkBox.foreground = Colors.DARK_GREEN
        }
        return checkBox
    }

    fun markShortcutCompleted(shortcut: Shortcut) {
        shortcut.let {
            checkboxes[it]?.apply {
                isSelected = true
                foreground = Colors.DARK_GREEN
                shortcutStateService.markShortcutAsCompleted(it.displayName)
            }
        }
    }
}