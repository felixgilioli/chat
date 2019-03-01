package br.com.felix.chat

import org.springframework.stereotype.Service

@Service
class MensagemServiceImpl(private val repository: MensagemRepository) : MensagemService {

    override fun save(mensagem: Mensagem) = repository.save(mensagem)

    override fun findAll() = repository.findAll()
}