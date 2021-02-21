package com.livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.livraria.model.Autor;
import com.livraria.service.AutorService;

@Controller
public class AutorController {
	
	@Autowired
	private AutorService autorService;
	
	
	
	@GetMapping("/autor")
	public String autor() {
		return "autor/cadastrarAutor";
	}
	
	@PostMapping("/salvar-autor")
    public String postCadastrarAutor(@ModelAttribute Autor autor){
        autorService.criarAutor(autor);
        return "redirect:/exibir-autor";
    }
	
	
	@GetMapping("/exibir-autor")
	public ModelAndView getListarAutor() {
		ModelAndView model = new ModelAndView("autor/exibirAutor");
		model.addObject("autores", autorService.listarAutor());
		return model;
	}
	
	
	@GetMapping("/deletar-autor")
	public String  deleteDeletarAutor(@RequestParam Long id){
		Autor autor = autorService.idAutor(id);
        autorService.deletarAutor(autor);
        return "redirect:/exibir-autor";
    }
	

	@GetMapping("/editar-autor")
    public ModelAndView updateEditarAuto(@RequestParam Long id){
        ModelAndView model = new ModelAndView("autor/editarAutor");
        Autor autor = autorService.idAutor(id);
        model.addObject("autor",  autor);
        return model;
    }
	


}
