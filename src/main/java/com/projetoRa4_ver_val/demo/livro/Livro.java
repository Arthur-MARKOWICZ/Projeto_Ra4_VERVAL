package com.projetoRa4_ver_val.demo.livro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private int numeroDePagina;
    private String genero;
    private boolean emprestado;
    public Livro(LivroCadastroDto dto) {
        this.titulo = dto.titulo();
        this.numeroDePagina = dto.numeroDePagina();
        this.genero = dto.genero();
        this.emprestado = false;
    }
}
