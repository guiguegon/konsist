package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration
import com.lemonappdev.konsist.core.declaration.KoDeclarationImpl
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

fun <E : KoBaseDeclaration> Sequence<E>.assert(function: (E) -> Boolean?) {
    assert(function, true)
}

fun <E : KoBaseDeclaration> Sequence<E>.assertNot(function: (E) -> Boolean?) {
    assert(function, false)
}

@Suppress("detekt.ThrowsCount")
private fun <E : KoBaseDeclaration> Sequence<E>.assert(function: (E) -> Boolean?, positiveCheck: Boolean) {
    var lastDeclaration: KoBaseDeclaration? = null

    try {
        val localList = this.toList()

        if (localList.isEmpty()) {
            val checkMethodName = Thread.currentThread().stackTrace[2].methodName
            throw KoPreconditionFailedException(
                "Declaration list is empty. Please make sure that list of declarations contain items " +
                    "before calling the '$checkMethodName' method.",
            )
        }

        val notSuppressedDeclarations = checkIfAnnotatedWithSuppress(localList)

        val result = notSuppressedDeclarations.groupBy {
            lastDeclaration = it
            function(it)
        }

        val allChecksPassed = (result[positiveCheck]?.size ?: 0) == notSuppressedDeclarations.size

        if (!allChecksPassed) {
            val failedDeclarations = result[!positiveCheck] ?: emptyList()
            throw KoCheckFailedException(getCheckFailedMessage(failedDeclarations))
        }
    } catch (e: KoException) {
        throw e
    } catch (@Suppress("detekt.TooGenericExceptionCaught") e: Exception) {
        throw KoInternalException(e.message.orEmpty(), e, lastDeclaration)
    }
}

private fun <E : KoBaseDeclaration> getCheckFailedMessage(failedDeclarations: List<E>): String {
    val failedDeclarationsMessage = failedDeclarations.joinToString("\n") {
        val name = if (it is KoNamedDeclaration) it.name else ""
        val konsistDeclarationClassNamePrefix = "Ko"
        val declarationType = it::class.simpleName?.substringAfter(konsistDeclarationClassNamePrefix)

        "${it.location} ($name $declarationType)"
    }

    /**
     * In this call stack hierarchy test name is at index 5.
     */
    val index = 5

    return "Assert '${getTestMethodName(index)}' has failed. Invalid declarations (${failedDeclarations.size}):\n$failedDeclarationsMessage"
}

private fun <E : KoBaseDeclaration> checkIfAnnotatedWithSuppress(localList: List<E>): List<E> {
    // In this declarations structure test name is at index 4
    // We pass this name to checkIfSuppressed() because when declarations are nested, this index is changing
    val testMethodName = Thread.currentThread().stackTrace[4].methodName
    val declarations: MutableMap<E, Boolean> = mutableMapOf()

    // First we need to exclude (if exist) file suppress test annotation
    localList
        .filterNot {
            it is KoAnnotationDeclaration &&
                (
                    it.text.endsWith("Suppress(\"konsist.$testMethodName\")") ||
                        it.text.endsWith("Suppress(\"$testMethodName\")")
                    )
        }
        .forEach {
            if (it is KoDeclaration) {
                declarations[it] = checkIfSuppressed(it as KoDeclaration, testMethodName)
            } else {
                declarations[it] = false
            }
        }

    val withoutSuppress = mutableListOf<E>()

    declarations.forEach { if (!it.value) withoutSuppress.add(it.key) }

    return withoutSuppress
}

private fun checkIfSuppressed(declaration: KoDeclaration, testMethodName: String): Boolean {
    val annotationParameter = (declaration as KoDeclarationImpl)
        .annotations
        .firstOrNull { it.name == "Suppress" }
        ?.text
        ?.removePrefix("@Suppress(\"")
        ?.removeSuffix("\")")

    return when {
        annotationParameter == testMethodName || annotationParameter == "konsist.$testMethodName" -> true
        declaration.parentDeclaration != null -> checkIfSuppressed(declaration.parentDeclaration as KoDeclarationImpl, testMethodName)
        fileAnnotationParameter(declaration.containingFile) == testMethodName -> true
        fileAnnotationParameter(declaration.containingFile) == "konsist.$testMethodName" -> true
        else -> false
    }
}

private fun fileAnnotationParameter(file: KoFile) = file
    .annotations
    .firstOrNull { it.name == "Suppress" }
    ?.text
    ?.removePrefix("@file:Suppress(\"")
    ?.removeSuffix("\")")