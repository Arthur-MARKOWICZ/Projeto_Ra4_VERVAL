package com.projetoRa4_ver_val.demo.livro;

import com.projetoRa4_ver_val.demo.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter

@NoArgsConstructor
public class Livro {
    @ManyToOne
    private Usuario usuarioEmprestado;
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

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getNumeroDePagina() {
        return numeroDePagina;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public Livro(Long id, String titulo, int numeroDePagina, String genero, boolean emprestado) {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNumeroDePagina(int numeroDePagina) {
        this.numeroDePagina = numeroDePagina;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }
}
