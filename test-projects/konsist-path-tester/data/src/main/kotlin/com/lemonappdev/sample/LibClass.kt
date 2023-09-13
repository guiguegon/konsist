package com.lemonappdev.sample

import com.lemonappdev.sample.LibClass

/**
 * Lib KDoc
 */
@Suppress("lib_argument")
class LibClass(libParameter: String) : LibInterface {
    constructor(otherParameter: Int) : this(otherParameter.toString())

    init {
        println("Lib init block")
    }
}

interface LibInterface

val libProperty: LibClass = LibClass("lib value")

object LibObject

enum class LibEnumClass {
    APP_CONSTANT
}

fun libFunction() {}

typealias libTypeAlias = String
