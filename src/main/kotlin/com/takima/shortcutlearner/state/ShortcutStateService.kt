package com.takima.shortcutlearner.state

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.takima.shortcutlearner.model.Shortcut

@Service(Service.Level.PROJECT)
@State(
    name = "ShortcutStateService",
    storages = [Storage("ShortcutLearner.xml")]
)
class ShortcutStateService : PersistentStateComponent<ShortcutStateService.State> {

    data class State(
        var shortcutCounts: MutableMap<String, Int> = mutableMapOf()
    )

    private var myState: State = State()

    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state
    }

    fun incrementShortcutCount(shortcut: Shortcut): Int {
        val shortcutName = shortcut.name
        val count = myState.shortcutCounts.getOrDefault(shortcutName, 0) + 1
        myState.shortcutCounts[shortcutName] = count
        return count
    }

    fun getShortcutCount(shortcut: Shortcut): Int {
        val shortcutName = shortcut.name
        return myState.shortcutCounts.getOrDefault(shortcutName, 0)
    }

    companion object {
        fun getInstance(project: com.intellij.openapi.project.Project): ShortcutStateService {
            return project.getService(ShortcutStateService::class.java)
        }
    }
}
