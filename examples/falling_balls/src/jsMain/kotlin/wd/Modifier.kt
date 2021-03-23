package org.jetbrains.compose.wd.ui

import androidx.compose.js.MppModifier
import androidx.compose.js.css
import androidx.compose.js.cssProps
import androidx.compose.js.events.event
import androidx.compose.js.events.onClick
import androidx.compose.js.onresize
import kotlinx.browser.window
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener

class JsModifier : MppModifier.Element {}

actual fun MppModifier.padding(all: Int) = this

actual fun MppModifier.offset(x: Int, y: Int) = offset(x.toFloat(), y.toFloat())

actual fun MppModifier.offset(x: Float, y: Float): MppModifier {
    return cssProps(mapOf("margin-left" to "${x}px", "margin-top" to "${y}px"))
}

actual fun MppModifier.width(width: Int) = cssProps(mapOf("width" to "${width}px"))

actual fun MppModifier.shadow(elevation: Int) = this

actual fun MppModifier.size(width: Int, height: Int) = cssProps(mapOf("width" to "${width}px", "height" to "${height}px"))

actual fun MppModifier.background(color: MyColor): MppModifier {
    return cssProps(mapOf("background-color" to "rgb(${color.red}, ${color.green}, ${color.blue})"))
}

actual fun MppModifier.clickable(onClick: () -> Unit) = event("click", object : EventListener {
    override fun handleEvent(event: Event) {
        onClick()
    }
})
fun MppModifier.onChange(onChange: (String) -> Unit) = event("change", object : EventListener {
    override fun handleEvent(event: Event) {
        onChange(event.asDynamic().srcElement.value)
    }
})

actual fun MppModifier.fillMaxWidth() = cssProps(mapOf("width" to "100%"))

actual fun MppModifier.fillMaxHeight(fraction: Float) = cssProps(mapOf("width" to "${100 * fraction}%"))

actual fun MppModifier.onSizeChanged(sizeChanged: (Size) -> Unit): MppModifier {
    sizeChanged(Size(755, 330))
    return onresize { width, height ->
        console.log("ON SIZE??", width, height)
        //sizeChanged(Size(width, height))
    }
}
actual val WithModifier: MppModifier
    get() = JsModifier()

fun MppModifier.color(color: MyColor) = then(cssProps(mapOf("color" to "rgb(${color.red}, ${color.green}, ${color.blue})")))
fun MppModifier.fontSize(size: Int) = then(cssProps(mapOf("font-size" to "${size}px")))
