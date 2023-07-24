package com.lemonappdev.konsist.core.declaration.koimportdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportDeclarationForKoContainingFileProviderTest {
    @Test
    fun `import-containing-file`() {
        // given
        val sut = getSnippetFile("import-containing-file")
            .imports
            .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koimportdeclaration/snippet/forkocontainingfileprovider/", fileName)
}
