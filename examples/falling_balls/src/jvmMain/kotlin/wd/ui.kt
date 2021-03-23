package org.jetbrains.compose.wd.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.js.MppModifier
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.demo.falling.wd.implementation

fun MppModifier.resolveAsModifier(): Modifier? {
    return (this as? ActualJvmModifier)?.modifier
}

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
    Text(text, color = color.implementation, fontSize = fontSize?.sp ?: TextUnit.Unspecified)
}

@Composable
actual fun WebSlider(value: Float, onValueChange: (Float) -> Unit, modifier: MppModifier) {
    modifier.resolveAsModifier()?.let { modifierImpl ->
        Slider(value, onValueChange = onValueChange, modifier = modifierImpl)
    }
}

@Composable
actual fun WebRow(modifier: MppModifier, content:  @Composable () -> Unit) {
    modifier.resolveAsModifier()?.let { modifierImpl ->
        Row(modifierImpl) {
            content()
        }
    }
}

@Composable
actual fun WebColumn(modifier: MppModifier, content: @Composable () -> Unit) {
    modifier.resolveAsModifier()?.let { modifierImpl ->
        Column(modifierImpl) { content() }
    }
}

@Composable actual fun WebButton(modifier: MppModifier, onClick: () -> Unit, content: @Composable () -> Unit) {
    modifier.resolveAsModifier()?.let { modifierImpl ->
        Button(onClick, modifierImpl) {
            content()
        }
    }
}

@Composable
actual fun WebBox(
    modifier: MppModifier,
    content: @Composable () -> Unit
) {
    modifier.resolveAsModifier()?.let { modifierImpl ->
        Box(modifierImpl, Alignment.TopStart, false) {
            content()
        }
    }
}

private fun WebAlignment.convertToAlignmentImplementation() = Alignment.TopStart