package tads.ufrn.provapw2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate deleted;
    @Column


    private String imagemUri;


    @NotBlank (message = "Nome é uma informação obrigatória.")
    @Size(max = 100)
    private String nome;

    @NotNull (message = "Preco é uma informação obrigatória.")
    private BigDecimal preco;
    @NotBlank(message = "Categoria é uma informação obrigatória.")
    @Size(max = 50)
    private String categoria; // classifica a fruta como: "frutas cítricas", "frutas tropicais", "fruta vermelhas"
    @NotNull
    private int qtdEstoque;

}
