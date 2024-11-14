package model.common.environment

enum class Heat(val value: Int) {
    WARM(10),
    OK(0),
    COLD(-10),
    TOO_COLD(-100)
    ;
}