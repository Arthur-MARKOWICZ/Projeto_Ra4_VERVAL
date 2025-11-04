package com.projetoRa4_ver_val.demo.livro;

import com.projetoRa4_ver_val.demo.usuario.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {
    @Mock
    private LivroRepository repository;
    @InjectMocks
    private LivroService service;
    @Test
    void DeveCadastrarLivro(){
        // give
        LivroCadastroDto livroCadastroDto = new LivroCadastroDto("test",100,"test");
        Livro livro = service.cadastro(livroCadastroDto);
        //when
        when(repository.save(any(Livro.class))).thenReturn(livro);
        //then
        Livro livroCadastrado = service.cadastro(livroCadastroDto);
        assertEquals("test", livroCadastrado.getTitulo());
    }

    @Test
    void DeveEditarLivro() {
        // given
        Livro livroExistente = new Livro(1l, "Teste", 34, "Fabula", false );
        LivroEditarDto livroEditarDto = new LivroEditarDto(1l, "Teste", 40, "Fabula", false);

        // when
        Livro livro = null;
        when(repository.save(any(Livro.class))).thenReturn(livro);

        // then
        Livro livroEditado = service.editar(livroEditarDto);
        assertEquals("40", livroEditado.getNumeroDePagina());

    }
}
