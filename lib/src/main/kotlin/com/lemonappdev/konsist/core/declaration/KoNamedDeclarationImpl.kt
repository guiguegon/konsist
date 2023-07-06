package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.psi.KtElement

internal abstract class KoNamedDeclarationImpl(private val element: KtElement) :
    KoNamedDeclaration,
    KoPathProviderCore,
    KoKDocProviderCore,
    KoLocationProviderCore,
    KoTextProviderCore,
    KoContainingFileProviderCore {
    override val psiElement: PsiElement
        get() = ktElement

    override val ktElement: KtElement
        get() = element

    override val name: String by lazy { ktElement.name ?: "" }

    override fun hasNameStartingWith(prefix: String): Boolean = name.startsWith(prefix)

    override fun hasNameEndingWith(suffix: String): Boolean = name.endsWith(suffix)

    override fun hasNameContaining(text: String): Boolean = name.contains(text)

    override fun hasNameMatching(regex: Regex): Boolean = name.matches(regex)
}
