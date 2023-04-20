package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoBaseDeclaration
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoBaseDeclarationSequenceExtTest {
    @Test
    fun `withFilePaths(String) returns baseDeclaration1 with path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val baseDeclaration1: KoBaseDeclaration = mockk {
            every { hasFilePath(path1) } returns true
            every { hasFilePath(path2) } returns true
        }
        val baseDeclaration2: KoBaseDeclaration = mockk {
            every { hasFilePath(path1) } returns false
            every { hasFilePath(path2) } returns true
        }
        val baseDeclaration3: KoBaseDeclaration = mockk {
            every { hasFilePath(path1) } returns false
            every { hasFilePath(path2) } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2, baseDeclaration3)

        // when
        val sut = baseDeclarations.withFilePaths(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration1)
    }

    @Test
    fun `withoutFilePaths(String) returns baseDeclaration3 without path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val baseDeclaration1: KoBaseDeclaration = mockk {
            every { hasFilePath(path1) } returns true
            every { hasFilePath(path2) } returns true
        }
        val baseDeclaration2: KoBaseDeclaration = mockk {
            every { hasFilePath(path1) } returns false
            every { hasFilePath(path2) } returns true
        }
        val baseDeclaration3: KoBaseDeclaration = mockk {
            every { hasFilePath(path1) } returns false
            every { hasFilePath(path2) } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2, baseDeclaration3)

        // when
        val sut = baseDeclarations.withoutFilePaths(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration3)
    }

    @Test
    fun `withSomeFilePaths(String) returns baseDeclaration1 and baseDeclaration2 which have at least one of given path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val baseDeclaration1: KoBaseDeclaration = mockk {
            every { hasFilePath(path1) } returns true
            every { hasFilePath(path2) } returns true
        }
        val baseDeclaration2: KoBaseDeclaration = mockk {
            every { hasFilePath(path1) } returns false
            every { hasFilePath(path2) } returns true
        }
        val baseDeclaration3: KoBaseDeclaration = mockk {
            every { hasFilePath(path1) } returns false
            every { hasFilePath(path2) } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2, baseDeclaration3)

        // when
        val sut = baseDeclarations.withSomeFilePaths(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration1, baseDeclaration2)
    }
}