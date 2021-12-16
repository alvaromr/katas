package com.alvaro.marsrover

sealed interface Direction {
    val left: Direction
    val right: Direction
    val dx: Int
    val dy: Int

    object North : Direction {
        override val left = West
        override val right = East
        override val dx = 0
        override val dy = 1
    }

    object East : Direction {
        override val left: Direction = North
        override val right: Direction = South
        override val dx = 1
        override val dy = 0
    }

    object South : Direction {
        override val left: Direction = East
        override val right: Direction = West
        override val dx = 0
        override val dy = -1
    }

    object West : Direction {
        override val left: Direction = South
        override val right: Direction = North
        override val dx = -1
        override val dy = 0
    }
}