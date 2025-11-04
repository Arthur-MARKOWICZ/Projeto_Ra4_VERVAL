package com.projetoRa4_ver_val.demo.usuario;

import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final   UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public Usuario cadastro(UsuarioCadastroDto dto){
        var usuario = new Usuario(dto);
        usuarioRepository.save(usuario);
        return usuario;
    }
}
