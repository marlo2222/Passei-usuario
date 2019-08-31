package com.api.usuario.controller;

import com.api.usuario.model.Usuario;
import com.api.usuario.repository.UsuarioRepository;
import com.api.usuario.service.ServiceUsuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "API de usuarios")
@CrossOrigin("*/")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ServiceUsuario serviceUsuario;

    @GetMapping(value = "/")
    public String home(){
        return "Acesse seu endereço local /swagger-ui.html#!/";
    }

    @GetMapping(value = "/usuario/listar/")
    @ApiOperation(value = "Retorna uma lista de usuarios")
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping(value = "/usuario/listar/{id}")
    @ApiOperation(value = "Retorna um usuario pelo id")
    public Usuario listarUsuarioId(@PathVariable("id") long id){
        return usuarioRepository.findById(id);
    }

    @PostMapping(value = "/usuario/salvar")
    @ApiOperation(value = "Salva um usuario")
    public Usuario salvarUsuario(@RequestBody Usuario usuario){
        return serviceUsuario.salvar(usuario);
    }

    @DeleteMapping(value = "/usuario/deletar/{id}")
    @ApiOperation(value = "Deleta um usuario pelo id")
    public ResponseEntity<String> deletarUsuario(@PathVariable("id") long id){
        usuarioRepository.deleteById(id);
        return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK);
    }

    @PutMapping(value = "/usuario/atualizar")
    @ApiOperation(value = "Retorna uma lista de atualiza um usuario")
    public Usuario atualizarUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

}
