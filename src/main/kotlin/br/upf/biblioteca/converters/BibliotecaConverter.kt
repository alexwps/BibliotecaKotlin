package br.upf.biblioteca.converters

import br.upf.biblioteca.dto.BibliotecaDTO
import br.upf.biblioteca.dto.BibliotecaResponseDTO
import br.upf.biblioteca.model.Biblioteca
import org.springframework.stereotype.Component

@Component
class BibliotecaConverter {
    fun toBibliotecaResponseDTO(biblioteca: Biblioteca): BibliotecaResponseDTO {
        return BibliotecaResponseDTO(
            id = biblioteca.id,
            nome = biblioteca.nome,
            endereco = biblioteca.endereco,
            funcionamento = biblioteca.funcionamento,
            observacao = biblioteca.observacao
        )
    }

    fun toBiblioteca(dto: BibliotecaDTO): Biblioteca {

        return Biblioteca(
            nome = dto.nome,
            endereco = dto.endereco,
            funcionamento = dto.funcionamento,
            observacao = dto.observacao,
        )
    }
}