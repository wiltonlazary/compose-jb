package org.jetbrains.compose.wd.ui

import androidx.compose.web.MppModifier

expect fun MppModifier.padding(all: Int): MppModifier
expect fun MppModifier.offset(x: Int, y: Int): MppModifier
expect fun MppModifier.offset(x: Float, y: Float): MppModifier
expect fun MppModifier.width(width: Int): MppModifier
expect fun MppModifier.shadow(elevation: Int): MppModifier
expect fun MppModifier.size(width: Int, height: Int): MppModifier
expect fun MppModifier.background(color: MyColor): MppModifier
expect fun MppModifier.clickable(onClick: () -> Unit): MppModifier
expect fun MppModifier.fillMaxWidth(): MppModifier
expect fun MppModifier.fillMaxHeight(fraction: Float): MppModifier
expect fun MppModifier.onSizeChanged(sizeChanged: (Size) -> Unit): MppModifier

expect val WithModifier: MppModifier
