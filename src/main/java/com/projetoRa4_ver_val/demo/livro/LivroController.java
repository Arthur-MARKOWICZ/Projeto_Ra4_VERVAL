package com.projetoRa4_ver_val.demo.livro;

import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(livro);
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
    public ResponseEntity<Livro> devolverLivro(@PathVariable Long idLivro) {
        var livro = service.devolverLivro(idLivro);
        return ResponseEntity.ok(livro);
    }
}

