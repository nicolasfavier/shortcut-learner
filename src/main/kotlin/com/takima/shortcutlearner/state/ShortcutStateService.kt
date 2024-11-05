package com.takima.shortcutlearner.state

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@Service(Service.Level.PROJECT)
@State(
    name = "ShortcutStateService",
    storages = [Storage("ShortcutLearner.xml")]
)
class ShortcutStateService : PersistentStateComponent<ShortcutStateService.State> {

    data class State(
        var completedShortcuts: MutableSet<String> = mutableSetOf()
    )

    private var myState: State = State()

    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state
    }

    fun markShortcutAsCompleted(shortcut: String) {
        myState.completedShortcuts.add(shortcut)
    }

    fun isShortcutCompleted(shortcut: String): Boolean {
        return myState.completedShortcuts.contains(shortcut)
    }

    companion object {
        fun getInstance(project: com.intellij.openapi.project.Project): ShortcutStateService {
            return project.getService(ShortcutStateService::class.java)
        }
    }
}
