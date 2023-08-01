package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoDefaultValueProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTypeProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoCrossInlineModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoNoInlineModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoValModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoVarArgModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoVarModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoVisibilityModifierProvider

/**
 * Represents a Kotlin parameter declaration.
 */
interface KoParameterDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoAnnotationProvider,
    KoContainingFileProvider,
    KoDefaultValueProvider,
    KoFullyQualifiedNameProvider,
    KoLocationProvider,
    KoModifierProvider,
    KoNameProvider,
    KoPackageProvider,
    KoParentProvider,
    KoPathProvider,
    KoRepresentsTypeProvider,
    KoResideInOrOutsidePackageProvider,
    KoTextProvider,
    KoTypeProvider,
    KoVisibilityModifierProvider,
    KoVarModifierProvider,
    KoValModifierProvider,
    KoVarArgModifierProvider,
    KoNoInlineModifierProvider,
    KoCrossInlineModifierProvider {
    /**
     * String representing the parameter.
     *
     * @return a string representing the parameter.
     */
    override fun toString(): String
}
