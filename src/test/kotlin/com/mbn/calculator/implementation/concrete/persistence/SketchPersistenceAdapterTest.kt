package com.mbn.calculator.implementation.concrete.persistence

import com.mbn.calculator.implementation.concrete.domain.business.Client
import com.mbn.calculator.implementation.concrete.domain.business.Installment
import com.mbn.calculator.implementation.concrete.domain.business.PriceTable
import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import com.mbn.calculator.implementation.concrete.exceptions.PersistenceException
import com.mbn.calculator.implementation.concrete.exceptions.SketchNotFoundException
import com.mbn.calculator.interfaces.SketchRepositoryInterface
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import org.springframework.dao.DataAccessResourceFailureException
import java.math.BigDecimal
import java.util.Optional

class SketchPersistenceAdapterTest {
    @Test
    fun `saveSketch - success`() = runBlocking {
        val repository1 = mock<SketchRepositoryInterface>()
        val repository2 = mock<SketchRepositoryInterface>()

        val adapter = SketchPersistenceAdapter(listOf(repository1, repository2))

        val mockSketch = mock<Sketch>()

        adapter.saveSketch(mockSketch)

        verify(repository1).saveSketch(mockSketch)
        verify(repository2).saveSketch(mockSketch)
    }

    @Test
    fun `getSketch - success`() {
        val repository1 = mock<SketchRepositoryInterface>()
        val repository2 = mock<SketchRepositoryInterface>()

        val adapter = SketchPersistenceAdapter(listOf(repository1, repository2))

        val mockSketch = mock<Sketch>()
        val sketchId = "123"

        val optionalSketch = Optional.of(mockSketch)
        doReturn(optionalSketch).`when`(repository1).getSketch(sketchId)

        val result = adapter.getSketch(sketchId)

        verify(repository1).getSketch(sketchId)

        Assertions.assertEquals(mockSketch, result)
    }

    @Test
    fun `getSketch - SketchNotFoundException`() {
        val repository1 = mock<SketchRepositoryInterface>()
        val repository2 = mock<SketchRepositoryInterface>()

        val adapter = SketchPersistenceAdapter(listOf(repository1, repository2))

        val sketchId = "123"

        val emptyOptional = Optional.empty<Sketch>()
        doReturn(emptyOptional).`when`(repository1).getSketch(sketchId)

        assertThrows<SketchNotFoundException> {
            adapter.getSketch(sketchId)
        }
    }
}