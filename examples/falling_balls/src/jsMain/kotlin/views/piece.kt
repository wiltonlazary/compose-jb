package org.jetbrains.compose.demo.falling.views

import androidx.compose.web.div
import androidx.compose.runtime.Composable
import androidx.compose.web.classes
import org.jetbrains.compose.demo.falling.PieceData
import org.jetbrains.compose.wd.ui.MyColor
import org.jetbrains.compose.wd.ui.WithModifier
import org.jetbrains.compose.wd.ui.background
import org.jetbrains.compose.wd.ui.clickable
import org.jetbrains.compose.wd.ui.offset

import org.jetbrains.compose.wd.ui.size

@Composable
actual fun piece(index: Int, piece: PieceData) {
    val boxSize = 40
    div( WithModifier
        .classes("piece")
        .offset((boxSize * index * 5 / 3).toFloat(), piece.position)
        .size(boxSize, boxSize)
        .background(if (piece.picked) MyColor.Gray else piece.color)
        .clickable{ piece.pick() }
    ) {}
}
