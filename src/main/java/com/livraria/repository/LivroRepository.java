package com.livraria.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.livraria.model.Livro;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Integer> {

}
