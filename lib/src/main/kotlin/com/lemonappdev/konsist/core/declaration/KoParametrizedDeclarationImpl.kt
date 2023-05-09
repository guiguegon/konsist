package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoParametrizedDeclaration
import org.jetbrains.kotlin.psi.KtFunction

internal abstract class KoParametrizedDeclarationImpl(
    private val ktFunction: KtFunction,
    parent: KoBaseDeclaration?,
) : KoDeclarationImpl(ktFunction, parent), KoParametrizedDeclaration {

    override val parameters by lazy {
        ktFunction
            .valueParameters
            .map { KoParameterDeclarationImpl.getInstance(it, this) }
            .ifEmpty { null }
    }

    override fun hasParameterNamed(name: String?) = when (name) {
        null -> parameters != null
        else -> parameters?.firstOrNull()?.name == name
    }
}