package com.takima.shortcutlearner.model

private const val REFACTOR_CAT = "Refactoring"
private const val NAVIGATION_CAT = "Navigation"
private const val SELECTION_CAT = "Selection"
private const val FORMAT_CAT = "Formatting"
private const val EDIT_CAT = "Editor"

enum class Shortcut(val displayName: String, val shortcut: String, val shortcutMac: String, val cat: String) {
    EXTRACT_VARIABLE("Extract a variable", "Ctrl + Alt + V", "⌥ + ⌘ + V", REFACTOR_CAT),
    EXTRACT_CONST("Extract a constant", "Ctrl + Alt + C", "⌥ + ⌘ + C", REFACTOR_CAT),
    EXTRACT_METHOD("Extract a method", "Ctrl + Alt + M", "⌥ + ⌘ + M", REFACTOR_CAT),
    EXTRACT_PARAM("Extract as method parameter", "Ctrl + Alt + P", "⌥ + ⌘ + P", REFACTOR_CAT),
    RENAME("Rename", "Shift + F6", "Shift + F6", REFACTOR_CAT),
    DUPLICATE("Duplicate", "Ctrl + D", "⌘ + D", REFACTOR_CAT),
    FIND_ACTION("Find actions, shortcuts, or documents", "Shift + Shift", "Shift + Shift", NAVIGATION_CAT),
    NAVIGATE_RECENT_FILE("Open recently used files", "Ctrl + Tab", "⌘ + E", NAVIGATION_CAT),
    NAVIGATE_TO_PREVIOUS_LOCATION("Navigate to previous location", "Ctrl + Tab + ←-", "⌥ + ⌘ + ←", NAVIGATION_CAT),
    NAVIGATE_TO_NEXT_LOCATION("Navigate to next location", "Ctrl + Tab + →", "⌘ + Tab + →", NAVIGATION_CAT),
    SELECT_NEXT_OCCURRENCE("Add the next occurrence in the selection", "Ctrl + G", "Ctrl + G", SELECTION_CAT),
    CLOSE("Close a file", "Ctrl + W", "⌘ + W", NAVIGATION_CAT),
    EXTEND_SELECTION("Extend selection to surrounding elements", "Ctrl + W", "⌥ + ⬆", SELECTION_CAT),
    FORMAT("Reformat the file", "Ctrl + Alt + L", "⌥ + ⌘ + L", FORMAT_CAT),
    OPTIMISE_IMPORT("Optimize file imports", "Ctrl + Alt + O", "Ctrl + ⌘ + O", FORMAT_CAT),
    FULL_SEARCH("Search the project", "Ctrl + Shift + F", "⌘ + Shift + F", EDIT_CAT),
    COPY_HISTORY("Paste one of the last 10 copied items", "Ctrl + Shift + V", "⌘ + Shift + V", EDIT_CAT),
    COMMENT("Comment a line", "Ctrl + /", "⌘ + /", EDIT_CAT),
}
