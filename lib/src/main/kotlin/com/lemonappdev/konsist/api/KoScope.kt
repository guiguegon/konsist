package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoCompanionObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration

/**
 * Represents a scope of Kotlin declarations.
 */
interface KoScope {
    /**
     * The files present in the scope.
     */
    fun files(): Sequence<KoFileDeclaration>

    /**
     * The classes present in the scope.
     */
    fun classes(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoClassDeclaration>

    /**
     * The interfaces present in the scope.
     */
    fun interfaces(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoInterfaceDeclaration>

    /**
     * The objects present in the scope.
     */
    fun objects(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoObjectDeclaration>

    /**
     * The companion objects present in the scope.
     */
    fun companionObjects(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoCompanionObjectDeclaration>

    /**
     * The functions present in the scope.
     */
    fun functions(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoFunctionDeclaration>

    /**
     * The declarations present in the scope.
     */
    fun declarations(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoDeclaration>

    /**
     * The properties present in the scope.
     */
    fun properties(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoPropertyDeclaration>

    /**
     * The imports present in the scope.
     */
    fun imports(): Sequence<KoImportDeclaration>

    /**
     * The packages present in the scope.
     */
    fun packages(): Sequence<KoPackageDeclaration>

    /**
     * The type aliases present in the scope.
     */
    fun typeAliases(): Sequence<KoTypeAliasDeclaration>

    /**
     * Add a scope files to this scope.
     */
    operator fun plus(scope: KoScope): KoScope

    /**
     * Subtract scope files from this scope.
     */
    operator fun minus(scope: KoScope): KoScope

    /**
     * Add a scope files and create a new scope.
     */
    operator fun plusAssign(scope: KoScope): Unit

    /**
     * Subtract a scope files and create a new scope.
     */
    operator fun minusAssign(scope: KoScope): Unit

    /**
     * String representing the scope.
     */
    override fun toString(): String

    /**
     * Print the scope.
     */
    fun print(): Unit
}