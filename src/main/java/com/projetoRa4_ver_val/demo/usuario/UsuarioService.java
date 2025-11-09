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

    public Usuario editar(UsuarioEditarDto usuarioEditarDto) {


        Usuario usuarioExistente = usuarioRepository.findById(usuarioEditarDto.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioExistente.setNome(usuarioEditarDto.nome());
        usuarioExistente.setEmail(usuarioEditarDto.email());
        usuarioExistente.setSenha(usuarioEditarDto.senha());

        return usuarioRepository.save(usuarioExistente);
    }
    public void deletar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.deleteById(id);
    }

}


