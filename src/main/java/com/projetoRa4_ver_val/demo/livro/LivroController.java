package com.projetoRa4_ver_val.demo.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Livro> cadastro(@RequestBody LivroCadastroDto dto){
        var livro = service.cadastro(dto);
        return ResponseEntity.status(201).body(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(new LivroDeletarDto(id));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idLivro}/emprestar/{idUsuario}")
    public ResponseEntity<Livro> emprestar(
            @PathVariable Long idLivro,
            @PathVariable Long idUsuario
    ) {
        var livro = service.emprestarLivro(idLivro, idUsuario);
        return ResponseEntity.ok(livro);
    }

    @PostMapping("/{idLivro}/devolver")
    public ResponseEntity<Livro> devolver(@PathVariable Long idLivro) {
        var livro = service.devolverLivro(idLivro);
        return ResponseEntity.ok(livro);
    }
}
