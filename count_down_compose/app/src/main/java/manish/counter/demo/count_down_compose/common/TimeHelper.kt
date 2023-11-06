package manish.counter.demo.count_down_compose.common

fun format(milliSecs: Long): String {
    val minutes = (milliSecs / 1000) / 60
    val seconds = (milliSecs / 1000) % 60
    val milliseconds = milliSecs % 1000

    return String.format("%02d:%02d:%03d", minutes, seconds, milliseconds)
}

fun rangedSecondFromMilliSecond(value: Long): Float {
    // value between 0 to 1
    return value.toFloat() / (60 * 1000)
}