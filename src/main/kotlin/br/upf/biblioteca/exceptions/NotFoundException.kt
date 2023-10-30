package br.upf.biblioteca.exceptions

import java.lang.RuntimeException
class NotFoundException(override val message: String)
    : RuntimeException()