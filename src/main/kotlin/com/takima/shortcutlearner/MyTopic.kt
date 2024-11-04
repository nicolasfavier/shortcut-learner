package com.takima.shortcutlearner

import com.intellij.refactoring.listeners.RefactoringEventListener
import com.intellij.util.messages.Topic

interface MyTopic {
    fun methodExtracted()

    companion object {
        val TOPIC: Topic<MyTopic> = Topic.create(RefactoringEventListener.REFACTORING_EVENT_TOPIC.displayName, MyTopic::class.java)
    }
}