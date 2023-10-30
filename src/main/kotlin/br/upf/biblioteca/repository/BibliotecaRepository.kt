package br.upf.biblioteca.repository

import br.upf.biblioteca.model.Biblioteca
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BibliotecaRepository: JpaRepository<Biblioteca, Long> {

}