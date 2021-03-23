package org.jetbrains.compose.demo.falling.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.withFrameNanos
import org.jetbrains.compose.demo.falling.Game
import org.jetbrains.compose.wd.ui.MyColor
import org.jetbrains.compose.wd.ui.WebBox
import org.jetbrains.compose.wd.ui.WebButton
import org.jetbrains.compose.wd.ui.WebColumn
import org.jetbrains.compose.wd.ui.WebRow
import org.jetbrains.compose.wd.ui.WebSlider
import org.jetbrains.compose.wd.ui.WebText
import org.jetbrains.compose.wd.ui.WithModifier
import org.jetbrains.compose.wd.ui.fillMaxHeight
import org.jetbrains.compose.wd.ui.fillMaxWidth
import org.jetbrains.compose.wd.ui.onSizeChanged
import org.jetbrains.compose.wd.ui.padding
import org.jetbrains.compose.wd.ui.width
import org.jetbrains.compose.wd.ui.offset

@Composable
fun fallingBalls(game: Game) {
    WebColumn(WithModifier) {
        WebBox(WithModifier) {
            WebText(
                "Catch balls!${if (game.finished) " Game over!" else ""}",
                fontSize = 50,
                color = MyColor(218, 120, 91)
            )
        }
        WebBox(WithModifier) {
            WebText("Score ${game.score} Time ${game.elapsed / 1_000_000} Blocks ${game.numBlocks}", fontSize = 35)
        }
        WebRow(WithModifier) {
            if (!game.started) {
                WebSlider(
                    value = game.numBlocks / 20f,
                    onValueChange = { game.numBlocks = (it * 20f).toInt().coerceAtLeast(1) },
                    modifier = WithModifier.width(100)
                )
            }
            WebButton(WithModifier, onClick = {
                game.started = !game.started
                if (game.started) {
                    game.start()
                }
            }) {
                WebText(if (game.started) "Stop" else "Start", fontSize = 40)
            }
            if (game.started) {
//                WebBox(modifier = WithModifier.offset(5, 0)) {
                    WebButton(WithModifier.offset(10, 0), onClick = {
                        game.togglePause()
                    }) {
                        WebText(if (game.paused) "Resume" else "Pause", fontSize = 40)
                    }
//                }
            }
        }

        if (game.started) {
            WebBox(WithModifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .onSizeChanged {
                    game.size = it
                }
            ) {
                game.pieces.forEachIndexed { index, piece -> piece(index, piece) }
            }
        }

        LaunchedEffect(Unit) {
            while (true) {
                withFrameNanos {
                    if (game.started && !game.paused && !game.finished)
                        game.update(it)
                }
            }
        }
    }
}
