package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoCompanionObjectDeclaration

fun Sequence<KoCompanionObjectDeclaration>.withName() = filter { it.hasName() }

fun Sequence<KoCompanionObjectDeclaration>.withoutName() = filterNot { it.hasName() }