package com.api.usuario.controller;

import com.api.usuario.model.Usuario;
import com.api.usuario.repository.UsuarioRepository;
import com.api.usuario.service.ServiceUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "API de usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ServiceUsuario serviceUsuario;

    @GetMapping(value = "/")
    public String home(){
        return "Acesse seu endereço local /swagger-ui.html#!/";
    }

    @GetMapping(value = "/usuario")
    @ApiOperation(value = "Retorna uma lista de usuarios")
    public ResponseEntity<?> listarUsuarios(){
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/usuario/{email}")
    @ApiOperation(value = "Retorna um usuario pelo email")
    public ResponseEntity<?> listarUsuarioId(@PathVariable("email") String email){
        Usuario usuario = usuarioRepository.findByEmail(email);
        if(usuario == null)
            return new ResponseEntity<>("Nao ha email cadastrado",HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(usuario,HttpStatus.OK);
    }

    @GetMapping(value = "/usuario/{id}")
    @ApiOperation(value = "Retorna um usuario pelo id")
    public ResponseEntity<?> listarUsuarioId(@PathVariable("id") long id){
        return new ResponseEntity<>(usuarioRepository.findById(id),HttpStatus.OK);
    }
    @PostMapping(value = "/usuario")
    @ApiOperation(value = "Salva um usuario")
    public Usuario salvarUsuario(@RequestBody Usuario usuario){
        serviceUsuario.salvar(usuario);
        return usuario;
    }

    @DeleteMapping(value = "/usuario/{id}")
    @ApiOperation(value = "Deleta um usuario pelo id")
    public ResponseEntity<?> deletarUsuario(@PathVariable("id") long id){
        usuarioRepository.deleteById(id);
        return new ResponseEntity<>("Usuario deletado com sucesso", HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/usuario")
    @ApiOperation(value = "Retorna uma lista de atualiza um usuario")
    public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
        return new ResponseEntity<>("Usuario atualizado com sucesso", HttpStatus.OK);
    }

}
