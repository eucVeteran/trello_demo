package com.example.repository;

import com.example.model.Books;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Azizbek Toshpulatov
 */
//repository that extends CrudRepository
public interface BooksRepository extends CrudRepository<Books, Integer>
{
}
