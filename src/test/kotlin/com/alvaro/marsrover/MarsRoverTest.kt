package com.alvaro.marsrover

import com.alvaro.marsrover.Direction.*
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MarsRoverTest {

    private lateinit var marsRover: MarsRover

    @BeforeTest
    fun setUp() {
        marsRover = MarsRover(0, 0, North)
    }

    @Test
    fun `it can create rovers`() {
        marsRover.assert(0, 0, North)
        MarsRover(2, 1, East).apply { assert(2, 1, East) }
    }

    @Test
    fun `it can go forward once`() {
        marsRover.receive("F")
        marsRover.assert(0, 1, North)
    }

    @Test
    fun `it can go forward twice`() {
        marsRover.receive("FF")
        marsRover.assert(0, 2, North)
    }

    @Test
    fun `it can turn right once`() {
        marsRover.receive("R")
        marsRover.assert(0, 0, East)
    }

    @Test
    fun `it can turn right twice`() {
        marsRover.receive("RR")
        marsRover.assert(0, 0, South)
    }

    @Test
    fun `it can turn right and go forward`() {
        marsRover.receive("RF")
        marsRover.assert(1, 0, East)
    }

    @Test
    fun `it can turn right once and go forward twice`() {
        marsRover.receive("RFF")
        marsRover.assert(2, 0, East)
    }

    @Test
    fun `it can turn left once`() {
        marsRover.receive("L")
        marsRover.assert(0, 0, West)
    }

    @Test
    fun `it can turn left once and go forward`() {
        marsRover.receive("LF")
        marsRover.assert(-1, 0, West)
    }

    @Test
    fun `it can turn left once and go forward twice`() {
        marsRover.receive("LFF")
        marsRover.assert(-2, 0, West)
    }

    @Test
    fun `it can turn left twice`() {
        marsRover.receive("LL")
        marsRover.assert(0, 0, South)
    }

    @Test
    fun `it can turn left three times`() {
        marsRover.receive("LLL")
        marsRover.assert(0, 0, East)
    }

    @Test
    fun `it can turn left four times`() {
        marsRover.receive("LLLL")
        marsRover.assert(0, 0, North)
    }

    @Test
    fun `it can turn left and right`() {
        marsRover.receive("LR")
        marsRover.assert(0, 0, North)
    }

    @Test
    fun `it can go back`() {
        marsRover.receive("B")
        marsRover.assert(0, -1, North)
    }

    @Test
    fun `it can go back 3 times`() {
        marsRover.receive("BBB")
        marsRover.assert(0, -3, North)
    }

    @Test
    fun `it can turn left and go back`() {
        marsRover.receive("LB")
        marsRover.assert(1, 0, West)
    }

    @Test
    fun `it can turn left twice and go back`() {
        marsRover.receive("LLB")
        marsRover.assert(0, 1, South)
    }

    @Test
    fun `it can turn left three times and go back`() {
        marsRover.receive("LLLB")
        marsRover.assert(-1, 0, East)
    }

    @Test
    fun `it ignores unknown command`() {
        marsRover.receive("XYZ")
        marsRover.assert(0, 0, North)
    }

    @Test
    fun `it undoes forward command`() {
        marsRover.receive("FZ")
        marsRover.assert(0, 0, North)
    }

    @Test
    fun `it undoes backward command`() {
        marsRover.receive("BZ")
        marsRover.assert(0, 0, North)
    }

    @Test
    fun `it undoes turn right command`() {
        marsRover.receive("RZ")
        marsRover.assert(0, 0, North)
    }

    @Test
    fun `it undoes turn left command`() {
        marsRover.receive("LZ")
        marsRover.assert(0, 0, North)
    }

    @Test
    fun `it undoes two forward commands`() {
        marsRover.receive("FFZZ")
        marsRover.assert(0, 0, North)
    }

    @Test
    fun `it undoes two backward commands`() {
        marsRover.receive("BBZZ")
        marsRover.assert(0, 0, North)
    }

    @Test
    fun `it undoes turn left and go foward commands`() {
        marsRover.receive("LFZZ")
        marsRover.assert(0, 0, North)
    }

    @Test
    fun `it undoes turn right and go foward commands`() {
        marsRover.receive("RFZZ")
        marsRover.assert(0, 0, North)
    }

    @Test
    fun `it undoes turn right twice and go foward commands`() {
        marsRover.receive("RRFZZZ")
        marsRover.assert(0, 0, North)
    }

    @Test
    fun `it undoes and redoes a command`() {
        marsRover.receive("FZY")
        marsRover.assert(0, 1, North)
    }

    @Test
    fun `it undoes and redoes multiple commands`() {
        marsRover.receive("FFFZZZYYY")
        marsRover.assert(0, 3, North)
    }

    @Test
    fun `it undoes and redoes multiple commands multiple times`() {
        marsRover.receive("FZYFZYZ")
        marsRover.assert(0, 1, North)
    }

    private fun MarsRover.assert(x: Int, y: Int, facing: Direction) {
        assertEquals(
                expected = x,
                actual = this.x,
                "wrong x"
        )
        assertEquals(
                expected = y,
                actual = this.y,
                "wrong y"
        )
        assertEquals(
                expected = facing,
                actual = this.facing,
                "wrong direction"
        )
    }
}