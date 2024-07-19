package com.example.service;

import com.example.model.Column;
import com.example.repository.ColumnRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ColumnServiceTest {

    @Mock
    private ColumnRepository columnRepository;

    @InjectMocks
    private ColumnService columnService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Column column = new Column();
        when(columnRepository.save(column)).thenReturn(column);

        Column savedColumn = columnService.save(column);

        assertNotNull(savedColumn);
        verify(columnRepository).save(column);
    }

    @Test
    public void testFindById_Found() {
        Column column = new Column();
        when(columnRepository.findById(1L)).thenReturn(Optional.of(column));

        Column foundColumn = columnService.findById(1L);

        assertNotNull(foundColumn);
        assertEquals(column, foundColumn);
        verify(columnRepository).findById(1L);
    }

    @Test
    public void testFindById_NotFound() {
        when(columnRepository.findById(1L)).thenReturn(Optional.empty());

        Column foundColumn = columnService.findById(1L);

        assertNull(foundColumn);
        verify(columnRepository).findById(1L);
    }

    @Test
    public void testFindAll() {
        List<Column> columns = new ArrayList<>();
        columns.add(new Column());
        columns.add(new Column());
        when(columnRepository.findAll()).thenReturn(columns);

        List<Column> foundColumns = columnService.findAll();

        assertNotNull(foundColumns);
        assertEquals(2, foundColumns.size());
        verify(columnRepository).findAll();
    }

    @Test
    public void testDeleteById() {
        doNothing().when(columnRepository).deleteById(1L);

        columnService.deleteById(1L);

        verify(columnRepository).deleteById(1L);
    }
}
