package org.jetbrains.compose.demo.falling

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.jetbrains.compose.wd.ui.MyColor
import org.jetbrains.compose.wd.ui.Size
import kotlin.random.Random

private fun MyColor.Companion.random() = MyColor((0..255).random(), (0..255).random(), (0..255).random())

abstract class Game {
    internal var previousTime: Long = Long.MAX_VALUE
    private var startTime = 0L

    var size by mutableStateOf(Size(0, 0))

    var pieces = mutableStateListOf<PieceData>()
        private set

    var elapsed by mutableStateOf(0L)
    var score by mutableStateOf(0)
    var clicked by mutableStateOf(0)

    var started by mutableStateOf(false)
    var paused by mutableStateOf(false)
    var finished by mutableStateOf(false)

    var numBlocks by mutableStateOf(5)

    fun isInBoundaries(pieceData: PieceData): Boolean = pieceData.position < size.height

    abstract fun saveTime()

    fun togglePause() {
        paused = !paused
        saveTime()
    }

    fun start() {
        saveTime()
        startTime = previousTime
        clicked = 0
        started = true
        finished = false
        paused = false
        pieces.clear()
        repeat(numBlocks) { index ->
            pieces.add(PieceData(this, index * 1.5f + 5f, MyColor.random()).also { piece ->
                piece.position = Random.nextDouble(0.0, 100.0).toFloat()
            })
        }
    }

    fun update(nanos: Long) {
        val dt = (nanos - previousTime).coerceAtLeast(0)
        previousTime = nanos
        elapsed = nanos - startTime
        pieces.forEach { it.update(dt) }
    }
}
