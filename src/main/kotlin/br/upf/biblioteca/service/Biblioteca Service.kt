package br.upf.biblioteca.service

import br.upf.biblioteca.converters.EventoConverter
import br.upf.biblioteca.dtos.EventoDTO
import br.upf.biblioteca.dtos.EventoResponseDTO
import br.upf.biblioteca.exceptions.NotFoundException
import br.upf.biblioteca.repository.EventoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BibliotecaService(private val repository: BibliotecaRepository,
                    val converter: BibliotecaConverter) {

    fun listar(nomeBiblioteca: String?, paginacao: Pageable): Page<BibliotecaResponseDTO> {
        val biblioteca = if (nomeBiblioteca == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByNome(nomeBiblioteca, paginacao)
        }
        return biblioteca
            .map { converter.toBibliotecaResponseDTO(it) }
    }

    fun buscarPorId(id: Long): BibliotecaResponseDTO {
        val biblioteca = repository.findById(id)
            .orElseThrow { NotFoundException("Biblioteca não encontrado") }
        return converter.toBibliotecaResponseDTO(biblioteca)
    }

    fun cadastrar(dto: BibliotecaDTO): BibliotecaResponseDTO {
        val biblioteca = repository.save(
            converter.toBiblioteca(dto))
        return converter.toBibliotecaResponseDTO(biblioteca)
    }

    fun atualizar(id: Long, dto: BibliotecaDTO): BibliotecaResponseDTO {
        val biblioteca = repository.findById(id)
            .orElseThrow { NotFoundException("Biblioteca não encontrado") }
            .copy(
                nome = dto.nome,
                data = dto.data,
                descricao = dto.descricao,
                status = dto.status
            )
        return converter.toBibliotecaResponseDTO(
            repository.save(biblioteca))
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }