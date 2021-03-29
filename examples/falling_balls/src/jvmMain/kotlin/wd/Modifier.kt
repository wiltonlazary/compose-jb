package org.jetbrains.compose.wd.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.web.MppModifier
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.demo.falling.wd.implementation

class SimpleModifier : Modifier.Element

class ActualJvmModifier : MppModifier.Element {
    var modifier: Modifier = SimpleModifier()
}

actual fun MppModifier.padding(all: Int) = when(this) {
    is ActualJvmModifier -> padding(all)
    else -> this
}
fun ActualJvmModifier.padding(all: Int): MppModifier {
    modifier = modifier.padding(all.dp)
    return this
}

actual fun MppModifier.offset(x: Int, y: Int) = when(this) {
    is ActualJvmModifier -> offset(x, y)
    else -> this
}
fun ActualJvmModifier.offset(x: Int, y: Int): MppModifier {
    modifier = modifier.offset(x.dp, y.dp)
    return this
}

actual fun MppModifier.offset(x: Float, y: Float) = when(this) {
    is ActualJvmModifier -> offset(x, y)
    else -> this
}
fun ActualJvmModifier.offset(x: Float, y: Float): MppModifier {
    modifier = modifier.offset(x.dp, y.dp)
    return this
}

actual fun MppModifier.width(width: Int) = when(this) {
    is ActualJvmModifier -> width(width)
    else -> this
}
fun ActualJvmModifier.width(width: Int): MppModifier {
    modifier = modifier.width(width.dp)
    return this
}

actual fun MppModifier.shadow(elevation: Int) = when(this) {
    is ActualJvmModifier -> shadow(elevation)
    else -> this
}
fun ActualJvmModifier.shadow(elevation: Int): MppModifier {
    modifier = modifier.shadow(elevation.dp)
    return this
}

actual fun MppModifier.size(width: Int, height: Int) = when(this) {
    is ActualJvmModifier -> size(width, height)
    else -> this
}
fun ActualJvmModifier.size(width: Int, height: Int): MppModifier {
    modifier = modifier.size(width.dp, height.dp)
    return this
}

actual fun MppModifier.background(color: MyColor) = when(this) {
    is ActualJvmModifier -> background(color)
    else -> this
}
fun ActualJvmModifier.background(color: MyColor): MppModifier {
    modifier = modifier.background(color.implementation)
    return this
}

actual fun MppModifier.clickable(onClick: () -> Unit) = when(this) {
    is ActualJvmModifier -> clickable(onClick)
    else -> this
}
fun ActualJvmModifier.clickable(onClick: () -> Unit): MppModifier {
    modifier = modifier.clickable { onClick() }
    return this
}

actual fun MppModifier.fillMaxWidth() = when(this) {
    is ActualJvmModifier -> fillMaxWidth()
    else -> this
}
fun ActualJvmModifier.fillMaxWidth(): MppModifier {
    modifier = modifier.fillMaxWidth()
    return this
}

actual fun MppModifier.fillMaxHeight(fraction: Float) = when(this) {
    is ActualJvmModifier -> fillMaxHeight(fraction)
    else -> this
}
fun ActualJvmModifier.fillMaxHeight(fraction: Float): MppModifier {
    modifier = modifier.fillMaxHeight(fraction)
    return this
}

actual fun MppModifier.onSizeChanged(sizeChanged: (Size) -> Unit) = when(this) {
    is ActualJvmModifier -> onSizeChanged(sizeChanged)
    else -> this
}
fun ActualJvmModifier.onSizeChanged(sizeChanged: (Size) -> Unit): MppModifier {
    modifier = modifier.onSizeChanged { sizeChanged(Size(it.width, it.height)) }
    return this
}


fun MppModifier.clip() = when(this) {
    is ActualJvmModifier -> clip()
    else -> this
}
fun ActualJvmModifier.clip(): MppModifier {
    modifier = modifier.clip(CircleShape)
    return this
}

actual val WithModifier: MppModifier
    get() = ActualJvmModifier()
