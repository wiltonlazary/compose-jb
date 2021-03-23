package org.jetbrains.compose.demo.falling.views

import androidx.compose.runtime.Composable
import org.jetbrains.compose.demo.falling.PieceData
import org.jetbrains.compose.wd.ui.MyColor
import org.jetbrains.compose.wd.ui.WebBox
import org.jetbrains.compose.wd.ui.WebText
import org.jetbrains.compose.wd.ui.WithModifier
import org.jetbrains.compose.wd.ui.background
import org.jetbrains.compose.wd.ui.clickable
import org.jetbrains.compose.wd.ui.offset
import org.jetbrains.compose.wd.ui.shadow
import org.jetbrains.compose.wd.ui.size

@Composable
expect fun piece(index: Int, piece: PieceData)
