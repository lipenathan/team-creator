package com.github.lipenathan.teamcreator.model

fun<T> List<T>.getNext(index: Int): T {
    var value = index + 1
    while (value > count()) {
        value = value - count()
    }
    return get(value - 1)
}

fun<T> Array<T>.getNext(index: Int): T {
    var value = index + 1
    while (value > count()) {
        value = value - count()
    }
    return get(value - 1)
}