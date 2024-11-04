package com.takima.shortcutlearner

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(
    name = "ShortcutStateService",
    storages = [Storage("ShortcutLearner.xml")]
)
class ShortcutStateService : PersistentStateComponent<ShortcutStateService.State> {

    // L'état à sauvegarder, ici les raccourcis complétés
    data class State(
        var completedShortcuts: MutableSet<String> = mutableSetOf()
    )

    private var myState: State = State()

    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state
    }

    // Marque un raccourci comme complété
    fun markShortcutAsCompleted(shortcut: String) {
        myState.completedShortcuts.add(shortcut)
    }

    // Vérifie si un raccourci est complété
    fun isShortcutCompleted(shortcut: String): Boolean {
        return myState.completedShortcuts.contains(shortcut)
    }

    companion object {
        // Permet d'obtenir le service dans un projet
        fun getInstance(project: com.intellij.openapi.project.Project): ShortcutStateService {
            return project.getService(ShortcutStateService::class.java)
        }
    }
}
