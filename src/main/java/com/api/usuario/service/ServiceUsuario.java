package com.api.usuario.service;
import com.api.usuario.model.Usuario;
import com.api.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ServiceUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        return usuario;
    }

}
