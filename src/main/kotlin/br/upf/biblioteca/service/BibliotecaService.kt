package br.upf.biblioteca.service

import br.upf.biblioteca.converters.BibliotecaConverter
import br.upf.biblioteca.dto.BibliotecaDTO
import br.upf.biblioteca.exceptions.NotFoundException
import br.upf.biblioteca.dto.BibliotecaResponseDTO
import br.upf.biblioteca.repository.BibliotecaRepository
import org.springframework.stereotype.Service

private const val BIBLIOTECA_NOT_FOUND_MESSAGE = "Biblioteca não encontrado!"

@Service
class BibliotecaService(
    private val repository: BibliotecaRepository,
    private val converter: BibliotecaConverter
) {
    fun listar(): List<BibliotecaResponseDTO> {
        return repository.findAll()
            .map(converter::toBibliotecaResponseDTO)
    }

    fun buscarPorId(id: Long): BibliotecaResponseDTO {
        val biblioteca = repository.findById(id)
            .orElseThrow { NotFoundException(BIBLIOTECA_NOT_FOUND_MESSAGE) }
        return converter.toBibliotecaResponseDTO(biblioteca)
    }

    fun cadastrar(dto: BibliotecaDTO): BibliotecaResponseDTO {
        return converter.toBibliotecaResponseDTO(
            repository.save(converter.toBiblioteca(dto)))
    }

    fun atualizar(id: Long, dto: BibliotecaDTO): BibliotecaResponseDTO {
        val biblioteca = repository.findById(id)
            .orElseThrow { NotFoundException(BIBLIOTECA_NOT_FOUND_MESSAGE) }
            .copy(
                nome = dto.nome,
                endereco = dto.endereco,
                funcionamento = dto.funcionamento,
                observacao = dto.observacao
            )
        return converter.toBibliotecaResponseDTO(repository.save(biblioteca))
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

}