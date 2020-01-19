package com.api.usuario.service;

import com.api.usuario.model.Usuario;
import com.api.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Cacheable(cacheNames = "Usuario", key = "#matricula")
    public ResponseEntity<?> buscarUSuarioMatricula(String matricula){
        Usuario usuario = usuarioRepository.findByMatricula(matricula);
        if(usuario == null)
            return new ResponseEntity<>("Nao ha matricula cadastrada", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(usuario,HttpStatus.OK);
    }

    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        return usuario;
    }
    
}
