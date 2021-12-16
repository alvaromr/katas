package com.alvaro.marsrover

sealed interface Command {
    fun execute()

    sealed interface UndoableCommand : Command {
        fun undo()

        class Forward(
                private val history: History,
                private val movable: Movable
        ) : UndoableCommand {
            override fun execute() {
                history.store(this)
                movable.goForward()
            }

            override fun undo() {
                movable.goBackward()
            }
        }

        class Backward(
                private val history: History,
                private val movable: Movable
        ) : UndoableCommand {
            override fun execute() {
                history.store(this)
                movable.goBackward()
            }

            override fun undo() {
                movable.goForward()
            }
        }

        class Left(
                private val history: History,
                private val movable: Movable
        ) : UndoableCommand {
            override fun execute() {
                history.store(this)
                movable.turnLeft()
            }

            override fun undo() {
                movable.turnRight()
            }
        }

        class Right(
                private val history: History,
                private val movable: Movable
        ) : UndoableCommand {
            override fun execute() {
                history.store(this)
                movable.turnRight()
            }

            override fun undo() {
                movable.turnLeft()
            }
        }
    }

    class Undo(private val history: History) : Command {
        override fun execute() {
            history.extract()
        }
    }

    class Redo(private val history: History) : Command {
        override fun execute() {
            history.restore()
        }
    }
}