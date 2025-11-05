package com.projetoRa4_ver_val.demo.livro;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    @Mock
    private LivroRepository repository;

    @InjectMocks
    private LivroService service;


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
        LivroEditarDto livroEditarDto = new LivroEditarDto(1L, "Teste", 40, "Fabula", false);

        // when
        when(repository.findById(1L)).thenReturn(Optional.of(livroExistente));
        when(repository.save(any(Livro.class))).thenAnswer(inv -> inv.getArgument(0));

        // then
        Livro livroEditado = service.editar(livroEditarDto);
        assertEquals(40, livroEditado.getNumeroDePagina());
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

}

