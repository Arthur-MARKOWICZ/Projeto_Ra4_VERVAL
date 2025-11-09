package com.projetoRa4_ver_val.demo.livro;

import com.projetoRa4_ver_val.demo.usuario.*;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    private final LivroRepository repository;
    private final UsuarioRepository repository_user;

    public LivroService(LivroRepository repository, UsuarioRepository repository_user) {
        this.repository = repository;
        this.repository_user = repository_user;
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

    public Livro emprestarLivro(Long idLivro, Long idUsuario) {

        Livro livro = repository.findById(idLivro)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Usuario usuario = repository_user.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (livro.isEmprestado()) {
            throw new RuntimeException("Livro já está emprestado");
        }

        livro.setEmprestado(true);
        return repository.save(livro);
    }

    public Livro devolverLivro(long idLivro) {

        Livro livro = repository.findById(idLivro)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livro.setEmprestado(false);
        return repository.save(livro);
    }
}
