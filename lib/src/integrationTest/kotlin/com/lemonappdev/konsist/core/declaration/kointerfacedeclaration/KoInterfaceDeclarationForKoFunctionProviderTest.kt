package com.lemonappdev.konsist.core.declaration.kointerfacedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoFunctionProviderTest {
    @Test
    fun `interface-contains-no-functions`() {
        // given
        val sut = getSnippetFile("interface-contains-no-functions")
            .interfaces()
            .first()

        // then
        sut.functions(includeNested = true, includeLocal = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `interface-contains-nested-and-local-functions includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("interface-contains-nested-and-local-functions")
            .interfaces()
            .first()

        // then
        val expected = listOf("sampleFunction", "sampleLocalFunction", "sampleNestedFunction")

        sut.functions(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-and-local-functions includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("interface-contains-nested-and-local-functions")
            .interfaces()
            .first()

        // then
        val expected = listOf("sampleFunction", "sampleNestedFunction")

        sut.functions(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-and-local-functions includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("interface-contains-nested-and-local-functions")
            .interfaces()
            .first()

        // then
        val expected = listOf("sampleFunction", "sampleLocalFunction")

        sut.functions(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-and-local-functions includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("interface-contains-nested-and-local-functions")
            .interfaces()
            .first()

        // then
        val expected = listOf("sampleFunction")

        sut.functions(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-functions`() {
        // given
        val sut = getSnippetFile("contains-functions")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            numFunctions(includeNested = true, includeLocal = true) shouldBeEqualTo 3
            numFunctions(includeNested = true, includeLocal = false) shouldBeEqualTo 2
            numFunctions(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numFunctions(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            containsFunction("sampleFunction", includeNested = false, includeLocal = false) shouldBeEqualTo true
            containsFunction("sampleLocalFunction", includeNested = false, includeLocal = true) shouldBeEqualTo true
            containsFunction("sampleLocalFunction", includeNested = false, includeLocal = false) shouldBeEqualTo false
            containsFunction("sampleNestedFunction", includeNested = true, includeLocal = false) shouldBeEqualTo true
            containsFunction("sampleNestedFunction", includeNested = false, includeLocal = false) shouldBeEqualTo false
            containsFunction("NonExisting") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerfacedeclaration/snippet/forkofunctionprovider/", fileName)
}