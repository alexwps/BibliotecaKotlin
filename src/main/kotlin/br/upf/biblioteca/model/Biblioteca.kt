package br.upf.biblioteca.model

import jakarta.persistence.*

@Entity
data class Biblioteca(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    val endereco: String,
    val funcionamento: String,
    val observacao: String,
)