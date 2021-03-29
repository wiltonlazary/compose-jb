package androidx.compose.js

import androidx.compose.runtime.Composable
import org.jetbrains.compose.wd.ui.WithModifier
import androidx.compose.web.MppModifier
import dom.Element

@Composable
fun button(modifier: MppModifier = WithModifier, content: @Composable () -> Unit) {
    Element(tagName = "button", modifier = modifier, content = content)
}

@Composable
fun input(modifier: MppModifier = WithModifier, content: @Composable () -> Unit) {
    Element(tagName = "input", modifier = modifier, content = content)
}

@Composable
fun span(modifier: MppModifier = WithModifier, content: @Composable () -> Unit) {
    Element(tagName = "span", modifier = modifier, content = content)
}




