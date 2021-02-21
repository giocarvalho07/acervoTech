package com.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livraria.model.Autor;
import com.livraria.repository.AutorRepository;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;
	
	public void criarAutor(Autor autor) {
		autorRepository.save(autor);
	}
	
	public void deletarAutor(Autor autor) {
		autorRepository.delete(autor);
	}
	
	public List<Autor> listarAutor(){
		return autorRepository.findAll();
	}
	
	public Autor idAutor(Long id) {
		return autorRepository.findById(id).get();
	}
}
