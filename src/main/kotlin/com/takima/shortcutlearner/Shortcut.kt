package com.takima.shortcutlearner

enum class Shortcut(val displayName: String, val shortcut: String, val categ: String) {
    EXTRACT_METHOD("Extract Method", "Ctrl+Alt+M", "Manipulation"),
    RENAME("Rename", "Shift+F6", "Manipulation"),
    REFORMAT_CODE("Reformat Code", "Ctrl+Alt+L", "Manipulation"),
    GO_BACK("Go back", "Ctrl+Alt+<-", "Navigation"),
}