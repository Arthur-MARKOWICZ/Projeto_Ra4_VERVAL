package com.projetoRa4_ver_val.demo.livro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Livro> cadastro(LivroCadastroDto dto){
        var livro = service.cadastro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(livro);
    }
}
