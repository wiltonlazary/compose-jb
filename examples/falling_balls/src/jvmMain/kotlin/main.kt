package org.jetbrains.compose.demo.falling

import androidx.compose.desktop.Window
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.IntSize
import org.jetbrains.compose.demo.falling.views.fallingBalls

class JvmGame : Game() {
    override fun saveTime() {
        previousTime = System.nanoTime()
    }
}

fun main() {
    Window(title = "Falling Balls", size = IntSize(800, 800)) {
        fallingBalls(remember { JvmGame() })
    }
}

