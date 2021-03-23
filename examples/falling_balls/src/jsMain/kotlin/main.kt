import androidx.compose.js.renderComposable
import androidx.compose.runtime.remember
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.demo.falling.Game
import org.jetbrains.compose.demo.falling.views.fallingBalls

class JsGame : Game() {
    override fun saveTime() {
        previousTime = window.performance.now().toLong()
    }
}

fun main() {
    renderComposable(document.getElementById("root")!!) {
        fallingBalls(remember { JsGame() })
    }
}