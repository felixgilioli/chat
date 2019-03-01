package br.com.felix.chat

interface MensagemService {

    fun save(mensagem: Mensagem): Mensagem

    fun findAll(): List<Mensagem>
}