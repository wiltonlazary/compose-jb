package dom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.web.DomApplier
import androidx.compose.web.DomNodeWrapper
import androidx.compose.web.MppModifier
import kotlinx.browser.document
import org.w3c.dom.Text

@Composable
fun Text(value: String) {
    ComposeNode<DomNodeWrapper, DomApplier>(
        factory = { DomNodeWrapper(document.createTextNode("")) },
        update = {
            set(value) { value -> (node as Text).data = value }
        },
    )
}

@Composable
fun Element(tagName: String, modifier: MppModifier = MppModifier, content: @Composable () -> Unit) {
    ComposeNode<DomNodeWrapper, DomApplier>(
        factory = { DomNodeWrapper(document.createElement(tagName)) },
        update = {
            set(modifier) { value -> updateModifier(value) }
        },
        content = content
    )
}

@Composable
fun div(modifier: MppModifier = MppModifier, content: @Composable () -> Unit) {
    Element(tagName = "div", modifier = modifier, content = content)
}