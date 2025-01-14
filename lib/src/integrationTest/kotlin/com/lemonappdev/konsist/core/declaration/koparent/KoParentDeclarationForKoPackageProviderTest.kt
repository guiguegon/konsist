package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoPackageProviderTest {
    @Test
    fun `parent-of-class-is-not-in-package`() {
        // given
        val sut = getSnippetFile("parent-of-class-is-not-in-package")
            .classes()
            .parents
            .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `parent-of-class-is-in-package`() {
        // given
        val sut = getSnippetFile("parent-of-class-is-in-package")
            .classes()
            .parents
            .first()

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `parent-of-interface-is-not-in-package`() {
        // given
        val sut = getSnippetFile("parent-of-interface-is-not-in-package")
            .interfaces()
            .parents
            .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `parent-of-interface-is-in-package`() {
        // given
        val sut = getSnippetFile("parent-of-interface-is-in-package")
            .interfaces()
            .parents
            .first()

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `parent-of-object-is-not-in-package`() {
        // given
        val sut = getSnippetFile("parent-of-object-is-not-in-package")
            .objects()
            .parents
            .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `parent-of-object-is-in-package`() {
        // given
        val sut = getSnippetFile("parent-of-object-is-in-package")
            .objects()
            .parents
            .first()

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparent/snippet/forkopackageprovider/", fileName)
}
