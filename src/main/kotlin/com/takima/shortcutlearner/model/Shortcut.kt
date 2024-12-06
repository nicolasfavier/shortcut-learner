package com.takima.shortcutlearner.model

private const val REFACTOR_CAT = "Refactoring"
private const val NAVIGATION_CAT = "Navigation"
private const val SELECTION_CAT = "Selection"
private const val FORMAT_CAT = "Formatting"
private const val EDIT_CAT = "Editor"

enum class Shortcut(val displayName: String, val category: String, val actionId: String) {
    EXTRACT_VARIABLE("Extract a variable", REFACTOR_CAT, "IntroduceVariable"),
    EXTRACT_CONST("Extract a constant", REFACTOR_CAT, "IntroduceConstant"),
    EXTRACT_METHOD("Extract a method", REFACTOR_CAT, "ExtractMethod"),
    EXTRACT_PARAM("Extract as method parameter", REFACTOR_CAT, "IntroduceParameter"),
    RENAME("Rename", REFACTOR_CAT, "RenameElement"),
    DUPLICATE("Duplicate", REFACTOR_CAT, "EditorDuplicate"),
    FIND_ACTION("Find actions, shortcuts, or documents : Shift + Shift", NAVIGATION_CAT, "SearchEverywhere"),
    NAVIGATE_RECENT_FILE("Open recently used files", NAVIGATION_CAT, "RecentFiles"),
    NAVIGATE_TO_PREVIOUS_LOCATION("Navigate to previous location", NAVIGATION_CAT, "Back"),
    NAVIGATE_TO_NEXT_LOCATION("Navigate to next location", NAVIGATION_CAT, "Forward"),
    SELECT_NEXT_OCCURRENCE("Add the next occurrence in the selection", SELECTION_CAT, "SelectNextOccurrence"),
    EXTEND_SELECTION("Extend selection to surrounding elements", SELECTION_CAT, "EditorSelectWord"),
    FORMAT("Reformat the file", FORMAT_CAT, "ReformatCode"),
    OPTIMISE_IMPORT("Optimize file imports", FORMAT_CAT, "OptimizeImports"),
    FULL_SEARCH("Search the project", EDIT_CAT, "FindInPath"),
    COPY_HISTORY("Paste one of the last 10 copied items", EDIT_CAT, "PasteMultiple"),
    COMMENT("Comment a line", EDIT_CAT, "CommentByLineComment");
}
