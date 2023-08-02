package com.lemonappdev.konsist.core.declaration.koprimaryconstructordeclaration.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPrimaryConstructorDeclarationForKoVisibilityModifierProviderTest {
    @Test
    fun `primary-constructor-has-no-visibility-modifiers`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-no-visibility-modifiers")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.hasPublicModifier shouldBeEqualTo false
            it?.isPublicOrDefault shouldBeEqualTo true
            it?.hasPrivateModifier shouldBeEqualTo false
            it?.hasProtectedModifier shouldBeEqualTo false
            it?.hasInternalModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `primary-constructor-has-public-modifier`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-public-modifier")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.hasPublicModifier shouldBeEqualTo true
    }

    @Test
    fun `primary-constructor-is-public-by-default`() {
        // given
        val sut = getSnippetFile("primary-constructor-is-public-by-default")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.isPublicOrDefault shouldBeEqualTo true
            it?.hasPublicModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `primary-constructor-has-private-modifier`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-private-modifier")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.hasPrivateModifier shouldBeEqualTo true
    }

    @Test
    fun `primary-constructor-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-protected-modifier")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.hasProtectedModifier shouldBeEqualTo true
    }

    @Test
    fun `primary-constructor-has-internal-modifier`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-internal-modifier")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.hasInternalModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/koprimaryconstructordeclaration/forkomodifierprovider/snippet/forkovisibilitymodifierprovider/",
            fileName,
        )
}