package com.takima.shortcutlearner

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware

class MethodExtractActionListener : AnAction(), DumbAware {
    override fun actionPerformed(e: AnActionEvent) {
        // Logique lorsque l'utilisateur extrait une méthode
        println("Extraction de méthode utilisée !")

        // Changer l'état ou l'apparence du plugin (ex: passer au vert)
        // Vous pouvez appeler une méthode pour mettre à jour l'interface utilisateur ici.
    }
}