package com.livraria.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livraria.exception.CriptoExistException;
import com.livraria.exception.EmailExistException;
import com.livraria.exception.UsuarioServiceException;
import com.livraria.model.Usuario;
import com.livraria.repository.UsuarioRepository;
import com.livraria.util.Util;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void cadastrarUsuario(Usuario usuario) throws Exception {
		
		try {
			if(usuarioRepository.findByEmail(usuario.getEmail()) !=null) {
				throw new EmailExistException("Já existe um email cadastro em nossa "
						+ "base de dados" + usuario.getEmail());
				} 
			usuario.setSenha(Util.md5(usuario.getSenha()));
			}
		
			catch (NoSuchAlgorithmException e) {
				
				throw new CriptoExistException("Erro na criptografia da senha");
			}	
			
			usuarioRepository.save(usuario);
	}
	
	public void deletarUsuario(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
	
	public List<Usuario> listarUsuario(){
		return usuarioRepository.findAll();
	}
	
	public Usuario idUsuario(Long id) {
		return usuarioRepository.findById(id).get();
	}
	
	public Usuario loginUsuario(String usuario, String senha) throws UsuarioServiceException{
		Usuario usuarioLogin = usuarioRepository.findByLogin(usuario, senha);
		return usuarioLogin;
	}
}
