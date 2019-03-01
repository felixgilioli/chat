package br.com.felix.chat

import org.springframework.data.jpa.repository.JpaRepository

interface MensagemRepository : JpaRepository<Mensagem, Long> {
}