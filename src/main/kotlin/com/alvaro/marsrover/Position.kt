package com.alvaro.marsrover

class Position(
        override var x: Int,
        override var y: Int,
        override var facing: Direction
) : Movable, Placeable {

    override fun goForward() {
        x += facing.dx
        y += facing.dy
    }

    override fun goBackward() {
        x -= facing.dx
        y -= facing.dy
    }

    override fun turnRight() {
        facing = facing.right
    }

    override fun turnLeft() {
        facing = facing.left
    }
}