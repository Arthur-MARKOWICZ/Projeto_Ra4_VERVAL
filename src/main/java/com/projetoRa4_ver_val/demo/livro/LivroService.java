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

    public Livro editar(LivroEditarDto dto) {
        Livro livroExistente = repository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livroExistente.setTitulo(dto.titulo());
        livroExistente.setNumeroDePagina(dto.numeroDePagina());
        livroExistente.setGenero(dto.genero());
        livroExistente.setEmprestado(dto.emprestado());

        return repository.save(livroExistente);
    }
    public Livro deletar(LivroDeletarDto dto){
        Livro livro = repository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        repository.delete(livro);

        return livro;
    }


}
