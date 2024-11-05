package com.takima.shortcutlearner.model

private const val REFACTOR_CAT = "Refactorisation"
private const val NAVIGATION_CAT = "Navigation"
private const val SELECTION_CAT = "Sélection"
private const val FORMAT_CAT = "Formatage"
private const val EDIT_CAT = "Éditeur"

enum class Shortcut(val displayName: String, val shortcut: String, val cat: String) {
    EXTRACT_VARIABLE("Extraire une variable", "Ctrl + Alt + V ", REFACTOR_CAT),
    EXTRACT_CONST("Extraire une constante", "Ctrl + Alt + C ", REFACTOR_CAT),
    EXTRACT_METHOD("Extraire une méthode", "Ctrl + Alt + M ", REFACTOR_CAT),
    EXTRACT_PARAM("Extraire en paramètre de méthode", "Ctrl + Alt + P ", REFACTOR_CAT),
    RENAME("Renommer", "Shift + F6 ", REFACTOR_CAT),
    DUPLICATE("Dupliquer", "Ctrl + D ", REFACTOR_CAT),
    FIND_ACTION("Rechercher des actions, raccourcis ou documents", "Shift + Shift ", NAVIGATION_CAT),
    NAVIGATE_RECENT_FILE("Ouvrir les documents récemment utilisés", "Ctrl + Tab ", NAVIGATION_CAT),
    NAVIGATE_TO_PREVIOUS_LOCATION("Naviguer vers la position précédente", "Ctrl + Tab + <- ", NAVIGATION_CAT),
    NAVIGATE_TO_NEXT_LOCATION("Naviguer vers la position suivante", "Ctrl + Tab + -> ", NAVIGATION_CAT),
    SELECT_NEXT_OCCURRENCE("Sélection la prochaine occurrence de la selection", "Ctrl + G ", SELECTION_CAT),
    CLOSE("Fermer un fichier", "Ctrl + W ", NAVIGATION_CAT),
    EXTEND_SELECTION("Étendre la sélection aux éléments environnants", "Ctrl + W ", SELECTION_CAT),
    FORMAT("Formater le fichier", "Ctrl + Alt + L ", FORMAT_CAT),
    OPTIMISE_IMPORT("Optimiser les imports du fichier", "Ctrl + Alt + O ", FORMAT_CAT),
    FULL_SEARCH("Rechercher dans le projet", "Ctrl + Shift + F ", EDIT_CAT),
    COPY_HISTORY("Coller une des 10 dernières copies", "Ctrl + Shift + V ", EDIT_CAT),
    COMMENT("Commenter une ligne", "Ctrl + / ", EDIT_CAT),
}