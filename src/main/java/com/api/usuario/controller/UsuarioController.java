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

    @GetMapping(value = "/usuario/listar/")
    @ApiOperation(value = "Retorna uma lista de usuarios")
    public ResponseEntity<?> listarUsuarios(){
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/usuario/listar/{id}")
    @ApiOperation(value = "Retorna um usuario pelo id")
    public ResponseEntity<?> listarUsuarioId(@PathVariable("id") long id){
        return new ResponseEntity<>(usuarioRepository.findById(id),HttpStatus.OK);
    }

    @PostMapping(value = "/usuario/salvar")
    @ApiOperation(value = "Salva um usuario")
    public ResponseEntity<?> salvarUsuario(@RequestBody Usuario usuario){
        serviceUsuario.salvar(usuario)
        return new ResponseEntity<>("Salvo Com Sucesso", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/usuario/deletar/{id}")
    @ApiOperation(value = "Deleta um usuario pelo id")
    public ResponseEntity<?> deletarUsuario(@PathVariable("id") long id){
        usuarioRepository.deleteById(id);
        return new ResponseEntity<>("Usuario deletado com sucesso", HttpStatus.OK);
    }

    @PutMapping(value = "/usuario/atualizar")
    @ApiOperation(value = "Retorna uma lista de atualiza um usuario")
    public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
        return new ResponseEntity<>("Usuario atualizado com sucesso", HttpStatus.OK);
    }

}
