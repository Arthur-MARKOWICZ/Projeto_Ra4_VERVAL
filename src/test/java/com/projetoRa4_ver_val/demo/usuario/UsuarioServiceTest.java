package com.projetoRa4_ver_val.demo.usuario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        when(repository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario usuarioCadastrado = service.cadastro(usuarioCadastroDto);
        assertEquals("test", usuarioCadastrado.getNome());
    }

}
