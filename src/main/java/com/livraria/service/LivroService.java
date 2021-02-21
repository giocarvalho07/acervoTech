package com.livraria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livraria.model.Livro;
import com.livraria.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	
	public void criarLivro(Livro livro) {
		livroRepository.save(livro);
	}
	
	public void deletarLivro(Livro livro) {
		livroRepository.delete(livro);
	}
	
	public Iterable<Livro> listarLivros(){
		return livroRepository.findAll();
	}
	
	public Livro idLivro(Integer cod) {
		return livroRepository.findById(cod).get();
	}
}
