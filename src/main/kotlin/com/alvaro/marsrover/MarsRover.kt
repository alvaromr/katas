package com.alvaro.marsrover

import com.alvaro.marsrover.Command.Redo
import com.alvaro.marsrover.Command.Undo
import com.alvaro.marsrover.Command.UndoableCommand.*

class MarsRover private constructor(
        position: Position,
        history: History = History()
) : Placeable by position,
        Receiver by CommandReceiver(
                mapOf(
                        'F' to Forward(history, position),
                        'B' to Backward(history, position),
                        'R' to Right(history, position),
                        'L' to Left(history, position),
                        'Z' to Undo(history),
                        'Y' to Redo(history),
                )
        ) {
    constructor(
            x: Int,
            y: Int,
            facing: Direction
    ) : this(Position(x, y, facing))
}
