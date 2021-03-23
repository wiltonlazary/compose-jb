package org.jetbrains.compose.demo.falling.views

import androidx.compose.js.classList
import androidx.compose.js.div
import androidx.compose.runtime.Composable
import org.jetbrains.compose.demo.falling.PieceData
import org.jetbrains.compose.wd.ui.MyColor
import org.jetbrains.compose.wd.ui.WebBox
import org.jetbrains.compose.wd.ui.WebText
import org.jetbrains.compose.wd.ui.WithModifier
import org.jetbrains.compose.wd.ui.background
import org.jetbrains.compose.wd.ui.clickable
import org.jetbrains.compose.wd.ui.color
import org.jetbrains.compose.wd.ui.offset
import org.jetbrains.compose.wd.ui.shadow
import org.jetbrains.compose.wd.ui.size

@Composable
actual fun piece(index: Int, piece: PieceData) {
    val boxSize = 40
    div( WithModifier
        .classList("piece")
        .offset((boxSize * index * 5 / 3).toFloat(), piece.position)
        .size(boxSize, boxSize)
        .background(if (piece.picked) MyColor.Gray else piece.color)
        .clickable{ piece.pick() }
    ) {}
}
