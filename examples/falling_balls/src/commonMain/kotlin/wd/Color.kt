package org.jetbrains.compose.wd.ui

@OptIn(kotlin.ExperimentalUnsignedTypes::class)
data class MyColor(val color: ULong) {
    companion object {
        val Gray = MyColor(0xFF888888)
        val Red = MyColor(0xFFFF0000)
        val Blue = MyColor(0xFF0000FF)
        val Cyan = MyColor(0xFF00FFFF)
        val Magenta = MyColor(0xFFFF00FF)
        val Yellow = MyColor(0xFFFFFF00)
        val Black = MyColor(0xFF000000)

        val Unspecified = MyColor(0xFFCCCCCC)
    }

    val red: UInt
        get() = ((color shr 48) and 0xffUL).toUInt()

    val green: UInt
        get() = ((color shr 40) and 0xffUL).toUInt()

    val blue: UInt
        get() = ((color shr 32) and 0xffUL).toUInt()
}


fun MyColor(color: Long): MyColor {
    return MyColor(color = (color.toULong() and 0xffffffffUL) shl 32)
}

fun MyColor(color: Int) = MyColor(color.toLong())


fun MyColor(
    red: Int,
    green: Int,
    blue: Int,
    alpha: Int = 0xFF
): MyColor {
    val color = ((alpha and 0xFF) shl 24) or
            ((red and 0xFF) shl 16) or
            ((green and 0xFF) shl 8) or
            (blue and 0xFF)
    return MyColor(color)
}