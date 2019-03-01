package br.com.felix.chat

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Mensagem (

        var nome: String? = null,
        var mensagem: String? = null,

        @Id
        @GeneratedValue
        var id: Long? = null
)