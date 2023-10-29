package br.upf.biblioteca.controller

import br.upf.biblioteca.dtos.EventoDTO
import br.upf.biblioteca.dtos.EventoResponseDTO
import br.upf.biblioteca.service.BibliotecaService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/eventos")
class BibliotecaController(private val service: BibliotecaService) {

    @GetMapping
    fun listar(@RequestParam(required = false) nomeEvento: String?,
               @PageableDefault(size = 10) paginacao: Pageable): Page<BibliotecaResponseDTO> {
        return service.listar(nomeEvento, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long) : BibliotecaResponseDTO {
        return service.buscarPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid evento: BibliotecaDTO,
                  uriBuilder: UriComponentsBuilder)
            : ResponseEntity<BibliotecaResponseDTO> {
        val eventoResponse = service.cadastrar(evento)
        val uri = uriBuilder.path("/eventos/${eventoResponse.id}")
            .build().toUri()
        return ResponseEntity.created(uri).body(eventoResponse)
    }

    @PutMapping("/{id}")
    fun atualizar (@PathVariable id: Long,
                   @RequestBody @Valid dto: BibliotecaDTO)
            : BibliotecaResponseDTO{
        return service.atualizar(id, dto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }

}