package com.projetoRa4_ver_val.demo.livro;

import com.projetoRa4_ver_val.demo.usuario.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    @Mock
    private LivroRepository repository;

    @InjectMocks
    private LivroService service;

    @Mock
    private UsuarioService service_user;

    @Mock
    private UsuarioRepository repository_user;


    @Test
    void DeveCadastrarLivro() {
        // given
        LivroCadastroDto livroCadastroDto = new LivroCadastroDto("test", 100, "test");
        Livro livro = new Livro(null, "test", 100, "test", false);

        // when
        when(repository.save(any(Livro.class))).thenReturn(livro);

        // then
        Livro livroCadastrado = service.cadastro(livroCadastroDto);
        assertEquals("test", livroCadastrado.getTitulo());
    }

    @Test
    void DeveEditarLivro() {
        // given
        Livro livroExistente = new Livro(1L, "Teste", 34, "Fabula", false);
        LivroEditarDto livroEditarDto = new LivroEditarDto("Teste", 40, "Fabula", false);

        // when
        when(repository.findById(1L)).thenReturn(Optional.of(livroExistente));
        when(repository.save(any(Livro.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Livro livroEditado = service.editar(1L, livroEditarDto);

        // then
        assertEquals(40, livroEditado.getNumeroDePagina());
        assertEquals("Teste", livroEditado.getTitulo());
    }


    @Test
    void DeveDeletarLivro() {
        // given
        Livro livro = new Livro(1L, "Teste", 40, "Fabula", false);
        LivroDeletarDto dto = new LivroDeletarDto(1L);

        when(repository.findById(dto.id())).thenReturn(Optional.of(livro));

        // when
        service.deletar(dto);

        // then
        verify(repository).delete(livro);
    }

    @Test
    void DeveEmprestarLivro() {
        // given
        Long idLivro = 1L;
        Long idUsuario = 1L;

        Livro livro = new Livro(1L, "Teste", 40, "Fabula", false);
        Usuario usuario = new Usuario(1L, "Teste", "teste@email.com", "123");

        when(repository.findById(idLivro)).thenReturn(Optional.of(livro));
        when(repository_user.findById(idUsuario)).thenReturn(Optional.of(usuario));

        // when
        service.emprestarLivro(idLivro, idUsuario);

        // then
        verify(repository).save(livro);
    }

    @Test
    void DeveDevolverLivro() {
        // given
        Livro livro = new Livro(1L, "Teste", 40, "Fabula", true);
        when(repository.findById(1L)).thenReturn(Optional.of(livro));
        // when
        service.devolverLivro(1L);
        // then
        assertFalse(livro.isEmprestado());
        verify(repository).save(livro);
    }
}
