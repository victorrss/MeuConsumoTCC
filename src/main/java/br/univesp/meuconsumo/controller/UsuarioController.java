package br.univesp.meuconsumo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univesp.meuconsumo.model.Usuario;
import br.univesp.meuconsumo.model.responses.UsuarioResponse;
import br.univesp.meuconsumo.repository.UsuarioRepository;

@RestController
@RequestMapping({ "/usuarios" })
public class UsuarioController {
    @Autowired
    private ModelMapper mapper;

    private UsuarioRepository repo;

    UsuarioController(UsuarioRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<UsuarioResponse> findAll() {
        return repo.findAll().stream().map(usuario -> mapper.map(usuario, UsuarioResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping(path = { "/{id}" })
    public ResponseEntity<UsuarioResponse> findById(@PathVariable long id) {
        return repo.findById(id)
                .map(usuario -> ResponseEntity.ok().body(mapper.map(usuario, UsuarioResponse.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UsuarioResponse create(@RequestBody Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        Usuario usuarioNovo = repo.save(usuario);
        return mapper.map(usuarioNovo, UsuarioResponse.class);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponse> update(@PathVariable("id") long id, @RequestBody Usuario usuario) {
        return repo.findById(id).map(record -> {
            record.setNome(usuario.getNome());
            record.setEmail(usuario.getEmail());
            record.setUsuario(usuario.getUsuario());
            record.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            Usuario updated = repo.save(record);
            return ResponseEntity.ok().body(mapper.map(updated, UsuarioResponse.class));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity<?> delete(@PathVariable long id) {
        return repo.findById(id).map(record -> {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}