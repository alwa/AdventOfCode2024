package org.example.util

fun interface Parser<T, V> {

    fun parse(input: T): V

}