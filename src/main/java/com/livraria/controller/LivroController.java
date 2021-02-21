package com.livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.livraria.model.Autor;
import com.livraria.model.Livro;
import com.livraria.service.AutorService;
import com.livraria.service.LivroService;

@Controller
public class LivroController {
	
	//-> Livro
	
	
	@Autowired
	private AutorService autorService;
	
	@Autowired
	private LivroService livroService;
	

	@RequestMapping(value="/livro", method = RequestMethod.GET)
	public ModelAndView getCadastrarLivro() {
		ModelAndView model = new ModelAndView("livro/livro");
		model.addObject("livro", new Livro());
		model.addObject("livros", livroService.listarLivros());
		model.addObject("autores", autorService.listarAutor());
		return model;
	}
	

	@PostMapping("/livro")
	public String postCadastrarLivro(@ModelAttribute Livro livro) {
		livroService.criarLivro(livro);
		return "redirect:/exibir-livro";
	}
	
	@RequestMapping(value = "/exibir-livro")
	public ModelAndView exibirLivro() {
		ModelAndView model = new ModelAndView("livro/exibir");
		Iterable<Livro> livro = livroService.listarLivros();
		model.addObject("livro", livro);
		return model;
	}
	
	
	@GetMapping("/deletar-livro")
	public String  deleteDeletarLivro(@RequestParam Integer cod){
		Livro livro = livroService.idLivro(cod);
        livroService.deletarLivro(livro);
        return "redirect:/exibir-livro";
    }
	
	  	

	@GetMapping("/editar-livro")
    public ModelAndView updateEditarLivro(@RequestParam Integer cod){
        
        ModelAndView model = new ModelAndView("editar");
        Livro livro = livroService.idLivro(cod);
        model.addObject("livro",  livro);
        model.addObject("autores", autorService.listarAutor());
        return model;
    }
	

	@GetMapping("/editar-livro/{cod}")
	public ModelAndView editarLivro(@PathVariable("cod") Integer cod) {
		ModelAndView model = new ModelAndView("livro/editar");
		Livro livro = livroService.idLivro(cod);
        model.addObject("livro",  livro);
        model.addObject("autores", autorService.listarAutor());
		return model;
	}

}
