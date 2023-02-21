package br.ufpr.tads.mobile.pokedex.controller;

import br.ufpr.tads.mobile.pokedex.model.Login;
import br.ufpr.tads.mobile.pokedex.model.Usuario;
import br.ufpr.tads.mobile.pokedex.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/login")
    public ResponseEntity<Usuario> efetuarLogin(@RequestBody Login login) {
        Usuario usuario = repository.findUsuarioByLoginEqualsAndSenhaEquals(login.getLogin(), login.getSenha());
        HttpStatus httpStatus = usuario == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;

        if (usuario != null) usuario.setSenha("");
        return new ResponseEntity<>(usuario, httpStatus);
    }

    @GetMapping("/usuarios")
    public List<Usuario> recuperarTodosUsuarios() {
        return repository.findAll();
    }

    @PostMapping("/usuarios")
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario atualizarUsuario(@PathVariable long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return repository.save(usuario);
    }

    @DeleteMapping("/usuarios/{id}")
    public Usuario removerUsuario(@PathVariable long id) {
        Optional<Usuario> retorno = repository.findById(id);

        if (retorno.isPresent()) {
            repository.deleteById(id);
        }

        return retorno.orElse(null);
    }
}
