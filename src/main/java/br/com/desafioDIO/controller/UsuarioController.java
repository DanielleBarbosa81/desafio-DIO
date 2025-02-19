package br.com.desafioDIO.controller;

import br.com.desafioDIO.dtos.LoginDTO;
import br.com.desafioDIO.dtos.UsuarioDTO;
import br.com.desafioDIO.dtos.UsuarioResponseDTO;
import br.com.desafioDIO.models.Usuario;
import br.com.desafioDIO.service.AuthService;
import br.com.desafioDIO.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final AuthService authService;

    public UsuarioController(UsuarioService usuarioService, AuthService authService) {
        this.usuarioService = usuarioService;
        this.authService = authService;

    }
    @GetMapping
    public List<UsuarioResponseDTO> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }
    @PostMapping
    public UsuarioResponseDTO adicionarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.adicionarUsuario(usuarioDTO);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String token = authService.autenticar(loginDTO);
        return token != null ? ResponseEntity.ok(token) : ResponseEntity.status(401).build();
    }
}
