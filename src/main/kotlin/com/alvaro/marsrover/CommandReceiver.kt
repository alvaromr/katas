package com.alvaro.marsrover

class CommandReceiver(
        private val commands: Map<Char, Command>,
) : Receiver {
    override fun receive(commands: String) {
        commands.onEach(this::receive)
    }

    private fun receive(id: Char) {
        commands[id]?.execute()
    }
}