package com.alvaro.marsrover

class History {
    private val done = mutableListOf<Command.UndoableCommand>()
    private val undone = mutableListOf<Command.UndoableCommand>()

    fun store(command: Command) {
        if (command is Command.UndoableCommand) {
            done.add(0, command)
        }
    }

    fun extract() {
        if (done.isNotEmpty()) {
            done.removeAt(0).also {
                undone.add(0, it)
                it.undo()
            }
        }
    }

    fun restore() {
        if (undone.isNotEmpty()) {
            undone.removeAt(0).also {
                done.add(0, it)
                it.execute()
            }
        }
    }
}