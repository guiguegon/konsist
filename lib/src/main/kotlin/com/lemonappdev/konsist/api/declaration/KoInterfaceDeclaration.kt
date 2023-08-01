package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.api.provider.KoInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoObjectProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoActualModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoExpectModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoFunModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoVisibilityModifierProvider

/**
 * Represents a Kotlin import declaration.
 */
interface KoInterfaceDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoAnnotationProvider,
    KoClassProvider,
    KoContainingFileProvider,
    KoDeclarationProvider,
    KoFullyQualifiedNameProvider,
    KoFunctionProvider,
    KoInterfaceProvider,
    KoKDocProvider,
    KoLocationProvider,
    KoModifierProvider,
    KoNameProvider,
    KoObjectProvider,
    KoPackageProvider,
    KoParentInterfaceProvider,
    KoParentProvider,
    KoPathProvider,
    KoPropertyProvider,
    KoRepresentsTypeProvider,
    KoResideInOrOutsidePackageProvider,
    KoTextProvider,
    KoTopLevelProvider,
    KoVisibilityModifierProvider,
    KoActualModifierProvider,
    KoExpectModifierProvider,
    KoFunModifierProvider{
    /**
     * String representing the interface.
     *
     * @return a string representing the interface.
     */
    override fun toString(): String
}
