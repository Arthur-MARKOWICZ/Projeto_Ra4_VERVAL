package com.projetoRa4_ver_val.demo.usuario;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastro(UsuarioCadastroDto dto){
        var usuario = new Usuario(dto);
        usuarioRepository.save(usuario);
        return usuario;
    }

    public Usuario editar(Long id, UsuarioEditarDto dto) {

        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioExistente.setNome(dto.nome());
        usuarioExistente.setEmail(dto.email());
        usuarioExistente.setSenha(dto.senha());

        return usuarioRepository.save(usuarioExistente);
    }

    public void deletar(Long id) {
        usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.deleteById(id);
    }
}
