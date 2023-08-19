package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoConstructorProvider

/**
 * List containing elements with constructor.
 *
 * @return A list containing elements with the constructor.
 */
fun <T : KoConstructorProvider> List<T>.withConstructor(): List<T> = filterNot { it.numConstructors == 0 }

/**
 * List containing elements without constructor.
 *
 * @return A list containing elements without the constructor.
 */
fun <T : KoConstructorProvider> List<T>.withoutConstructor(): List<T> = filter { it.numConstructors == 0 }