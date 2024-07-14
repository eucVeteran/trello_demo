package com.example.service

import com.example.model.Board
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class BoardServiceTestKotlin {
    @Test
    fun save() {
    }

    @Test
    fun findById() {
        val board = Board()

        val subject = BoardService(mockk{
            every { findById(1L) } returns Optional.of(board)
        })

        val result = subject.findById(1L)

        assertEquals(board, result)
    }

    @Test
    fun findAll() {
    }

    @Test
    fun deleteById() {
    }
}
