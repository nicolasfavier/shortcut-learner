package com.takima.shortcutlearner.view

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.keymap.KeymapManager
import com.intellij.openapi.keymap.KeymapUtil
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBScrollPane
import com.takima.shortcutlearner.model.Shortcut
import com.takima.shortcutlearner.state.ShortcutStateService
import java.awt.Color
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane

private const val GREEN = "#39B25A"

class ShortcutLearnerPanel(project: Project) : JPanel() {
    private val shortcutStateService = ShortcutStateService.getInstance(project)
    private val checkboxes = mutableMapOf<Shortcut, JBCheckBox>()

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        val contentPanel = JPanel()
        contentPanel.layout = BoxLayout(contentPanel, BoxLayout.Y_AXIS)

        val shortcutsByCategory = Shortcut.entries.groupBy { it.category }
        for ((category, shortcuts) in shortcutsByCategory) {
            val categoryPanel = createCategoryPanel(category, shortcuts)
            contentPanel.add(categoryPanel)
        }

        val scrollPane = JBScrollPane(contentPanel)
        scrollPane.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        scrollPane.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER

        add(scrollPane)
    }

    private fun createCategoryPanel(category: String, shortcuts: List<Shortcut>): JPanel {
        val categoryPanel = JPanel()
        categoryPanel.layout = BoxLayout(categoryPanel, BoxLayout.Y_AXIS)

        shortcuts.forEach { shortcut ->
            val checkBox = createCheckbox(shortcut)
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
        val shortcutCount = shortcutStateService.getShortcutCount(shortcut)
        val label = getCheckboxLabel(shortcut, shortcutCount)
        val checkBox = JBCheckBox(label)
        val isSelected = shortcutCount > 0
        checkBox.isSelected = isSelected
        checkBox.isFocusable = false
        checkBox.isOpaque = false
        checkBox.addActionListener { checkBox.isSelected = isSelected }

        if (checkBox.isSelected) {
            checkBox.foreground = Color.decode(GREEN)
        }
        return checkBox
    }

    fun updateShortcutCountDisplay(shortcut: Shortcut, count: Int) {
        ApplicationManager.getApplication().invokeLater {
            checkboxes[shortcut]?.apply {
                isSelected = true
                foreground = Color.decode(GREEN)
                text = getCheckboxLabel(shortcut, count)
            }
        }
    }

    private fun getCheckboxLabel(shortcut: Shortcut, shortcutCount: Int): String {
        val countStr = if (shortcutCount > 0) "(<b>$shortcutCount times</b>)" else ""
        val keymap = KeymapManager.getInstance().activeKeymap

        val shortcuts = keymap.getShortcuts(shortcut.actionId).joinToString(" or ") {
            val shortcutText = KeymapUtil.getShortcutText(it)
            formatShortcutText(shortcutText)
        }

        return "<html>${shortcut.displayName} : <i>$shortcuts</i> $countStr</html>"
    }

    private fun formatShortcutText(shortcutText: String): String {
        return if (shortcutText.contains("Button")) {
            shortcutText.replace("Button", "Mousse Btn").replace("Click", "")
        } else {
            shortcutText
        }
    }
}
