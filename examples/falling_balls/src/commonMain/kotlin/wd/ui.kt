package org.jetbrains.compose.wd.ui

import androidx.compose.runtime.Composable
import androidx.compose.js.MppModifier

@Composable
fun WebText(text: String) {
    WebText(text, color = MyColor.Unspecified, fontSize = null)
}
@Composable expect fun WebText(text: String, fontSize: Int?)
@Composable expect fun WebText(text: String, color: MyColor)
@Composable expect fun WebText(text: String, color: MyColor, fontSize: Int?)


@Composable expect fun WebSlider(value: Float, onValueChange: (Float) -> Unit, modifier: MppModifier)

@Composable expect fun WebRow(modifier: MppModifier, content:  @Composable () -> Unit)

@Composable expect fun WebColumn(modifier: MppModifier, content: @Composable () -> Unit)

@Composable expect fun WebButton(modifier: MppModifier, onClick: () -> Unit, content: @Composable () -> Unit)

@Composable expect fun WebBox(modifier: MppModifier, content: @Composable () -> Unit)