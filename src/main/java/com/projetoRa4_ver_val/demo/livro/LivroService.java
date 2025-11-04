package com.projetoRa4_ver_val.demo.livro;

import com.projetoRa4_ver_val.demo.usuario.Usuario;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }
    public Livro cadastro(LivroCadastroDto dto){
        Livro livro = new Livro(dto);
        repository.save(livro);
        return livro;
    }

    public Livro editar(LivroEditarDto livroEditarDto) {
        return null;
    }
}
