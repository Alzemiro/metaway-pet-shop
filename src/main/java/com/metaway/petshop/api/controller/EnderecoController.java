package com.metaway.petshop.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metaway.petshop.api.dao.request.CadastroEnderecoRequest;
import com.metaway.petshop.api.dao.response.EnderecoResponse;
import com.metaway.petshop.api.entities.Endereco;
import com.metaway.petshop.api.entities.Usuario;
import com.metaway.petshop.api.enums.EnumPerfilUsuario;
import com.metaway.petshop.api.service.endereco.IEnderecoService;
import com.metaway.petshop.api.service.usuario.IUsuarioService;

@RestController
@RequestMapping("/v1/api/endereco")
public class EnderecoController {

    @Autowired
    private IEnderecoService enderecoService;
    
    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<EnderecoResponse> createEndereco(@RequestBody CadastroEnderecoRequest request) {
        String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.findUsuarioByCPF(cpf);
        
        if(usuario.getPerfil() != EnumPerfilUsuario.ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        
        Endereco endereco = Endereco.builder()
            .logradouro(request.getLogradouro())
            .cidade(request.getCidade())
            .bairro(request.getBairro())
            .complemento(request.getComplemento())
            .tag(request.getTag())
            .build();
        
        Endereco entity = enderecoService.create(endereco);
        
        EnderecoResponse response =  EnderecoResponse.builder()
        .logradouro(entity.getLogradouro())
        .cidade(entity.getCidade())
        .bairro(entity.getBairro())
        .complemento(entity.getComplemento())
        .tag(entity.getTag())
        .build();
    
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponse> updateEndereco(@PathVariable Integer id, @RequestBody CadastroEnderecoRequest request) {
        String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.findUsuarioByCPF(cpf);
        
        if(usuario.getPerfil() == EnumPerfilUsuario.CLIENTE && !request.getIdCliente().equals(usuario.getCpf())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        Endereco endereco = Endereco.builder()
            .id(id)
            .logradouro(request.getLogradouro())
            .cidade(request.getCidade())
            .bairro(request.getBairro())
            .complemento(request.getComplemento())
            .tag(request.getTag())
            .build();
            
        Endereco updatedEndereco = enderecoService.update(endereco);
        
        EnderecoResponse response = EnderecoResponse.builder()
            .clienteId(updatedEndereco.getCliente().getId())
            .enderecoId(updatedEndereco.getId())
            .logradouro(updatedEndereco.getLogradouro())
            .cidade(updatedEndereco.getCidade())
            .bairro(updatedEndereco.getBairro())
            .complemento(updatedEndereco.getComplemento())
            .tag(updatedEndereco.getTag())
            .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Integer id) {
        String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.findUsuarioByCPF(cpf);
        
        if(usuario.getPerfil() != EnumPerfilUsuario.ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        enderecoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponse> getEnderecoById(@PathVariable Integer id) {
        String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.findUsuarioByCPF(cpf);
        
        return enderecoService.findById(id)
                .map(endereco -> {
                    if(usuario.getPerfil() == EnumPerfilUsuario.CLIENTE && !endereco.getCliente().getId().equals(usuario.getCpf())) {
                        return new ResponseEntity<EnderecoResponse>(HttpStatus.FORBIDDEN);
                    }
                    
                    return new ResponseEntity<>(EnderecoResponse.builder()
                            .clienteId(endereco.getCliente().getId())
                            .enderecoId(endereco.getId())
                            .logradouro(endereco.getLogradouro())
                            .cidade(endereco.getCidade())
                            .bairro(endereco.getBairro())
                            .complemento(endereco.getComplemento())
                            .tag(endereco.getTag())
                            .build(), HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> getAllEnderecos() {
        String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.findUsuarioByCPF(cpf);
        
        if(usuario.getPerfil() == EnumPerfilUsuario.CLIENTE) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        List<Endereco> enderecos = enderecoService.findAll();
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }
}