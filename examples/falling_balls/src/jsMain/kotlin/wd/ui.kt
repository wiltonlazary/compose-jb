package org.jetbrains.compose.wd.ui

import androidx.compose.js.button
import androidx.compose.js.input
import androidx.compose.js.span
import androidx.compose.web.MppModifier
import androidx.compose.web.elements.Text
import androidx.compose.web.attr
import androidx.compose.web.cssProps
import androidx.compose.web.div
import androidx.compose.runtime.Composable
import androidx.compose.web.classes

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
actual fun WebSlider(value: Float, onValueChange: (Float) -> Unit, modifier: MppModifier) = input(
    modifier = MppModifier
        .attr("type", "range")
        .attr("min", "0.25")
        .attr("max", "12")
        .attr("value", value.toString())
        .classes("web-slider")
        .onChange { newValue -> onValueChange(newValue.toFloat()) }
) {}

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
