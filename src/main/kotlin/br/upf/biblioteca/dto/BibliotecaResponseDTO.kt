package br.upf.biblioteca.dto

data class BibliotecaResponseDTO(
    val id: Long? = null,
    val nome: String,
    val endereco: String,
    val funcionamento: String,
    val observacao: String
)