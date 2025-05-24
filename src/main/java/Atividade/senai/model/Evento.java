package Atividade.senai.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 300, message = "O título deve ter no máximo 300 caracteres")
    @Column(length = 300, nullable = false)
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 1500, message = "A descrição deve ter no máximo 1500 caracteres")
    @Column(length = 1500, nullable = false)
    private String descricao;

    private LocalDate data;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
}
