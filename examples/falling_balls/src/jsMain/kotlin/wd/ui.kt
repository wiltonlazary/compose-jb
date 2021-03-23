package org.jetbrains.compose.wd.ui

import androidx.compose.js.MppModifier
import androidx.compose.js.Text
import androidx.compose.js.attr
import androidx.compose.js.button
import androidx.compose.js.classList
import androidx.compose.js.cssProps
import androidx.compose.js.div
import androidx.compose.js.input
import androidx.compose.js.span
import androidx.compose.runtime.Composable

@Composable
actual fun WebText(text: String, fontSize: Int?) {
    WebText(text, color = MyColor.Unspecified, fontSize = fontSize)
}
@Composable
actual fun WebText(text: String, color: MyColor) {
    WebText(text, color = color, fontSize = null)
}
@Composable
actual fun WebText(text: String, color: MyColor, fontSize: Int?) {
    span(
        modifier = MppModifier
            .fontSize(fontSize ?: 16)
            .color(color)
    ) { Text(text) }
}

@Composable
actual fun WebSlider(value: Float, onValueChange: (Float) -> Unit, modifier: MppModifier) {
    input(
            modifier = MppModifier
                            .attr("type", "range")
                            .attr("min", "0.25")
                            .attr("max", "12")
                            .attr("value", value.toString())
                            .classList("web-slider")
                            .onChange { newValue -> onValueChange(newValue.toFloat()) }
        ) {}
}

@Composable
actual fun WebRow(modifier: MppModifier, content:  @Composable () -> Unit) {
    div(modifier = modifier, content = content)
}

@Composable
actual fun WebColumn(modifier: MppModifier, content: @Composable () -> Unit) {
    div(modifier = modifier, content = content)
}

@Composable actual fun WebButton(modifier: MppModifier, onClick: () -> Unit, content: @Composable () -> Unit) {
    button(content = content, modifier = modifier.clickable(onClick))
}


@Composable
actual fun WebBox(
    modifier: MppModifier,
    content: @Composable () -> Unit
) {
    div(modifier = modifier, content = content)
}