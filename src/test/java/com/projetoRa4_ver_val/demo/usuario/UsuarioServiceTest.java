package com.projetoRa4_ver_val.demo.usuario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @Mock
    private UsuarioRepository repository;
    @InjectMocks
    private UsuarioService service;
    @Test
    void DeveCadastrarUsuario(){
        // give
        UsuarioCadastroDto usuarioCadastroDto = new UsuarioCadastroDto("test","test@test.com",
                "test");
        Usuario usuario = service.cadastro(usuarioCadastroDto);
        //when
        when(repository.save(any(Usuario.class))).thenReturn(usuario);
        //then
        Usuario usuarioCadastrado = service.cadastro(usuarioCadastroDto);
        assertEquals("test", usuarioCadastrado.getNome());
    }

    @Test
    void DeveEditarUsuario() {
        // given
        Usuario usuarioExistente = new Usuario(1L, "Eduardo", "eduardo@gmail.com", "senha");
        UsuarioEditarDto usuarioEditarDto = new UsuarioEditarDto(1L, "Dudu", "eduardo@gmail.com", "senha");

        // when
        when(repository.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(repository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // then
        Usuario usuarioEditado = service.editar(usuarioEditarDto);

        assertEquals("Dudu", usuarioEditado.getNome());
    }

}
