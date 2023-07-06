package com.lemonappdev.konsist.core.provider

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.provider.KoTextProvider

internal interface KoTextProviderCore: KoTextProvider {
    val psiElement: PsiElement

    override val text: String
    get() = psiElement.text

     override fun toString(): String {
       return text
    }

    override fun print() {
        print(toString())
    }
}
