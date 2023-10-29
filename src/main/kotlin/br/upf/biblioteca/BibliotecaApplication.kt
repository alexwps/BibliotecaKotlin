package br.upf.biblioteca

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BibliotecaApplication

fun main(args: Array<String>) {
	runApplication<BibliotecaApplication>(*args)
}
