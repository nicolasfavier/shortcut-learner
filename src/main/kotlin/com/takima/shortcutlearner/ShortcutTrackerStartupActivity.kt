package com.takima.shortcutlearner

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class ShortcutTrackerStartupActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        project.getService(ShortcutTrackerService::class.java).initService(project)
    }
}