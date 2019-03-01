package br.com.felix.chat

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.util.HtmlUtils

@Controller
class MensagemController(private val service: MensagemService) {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    fun mensagem(message: Mensagem): Mensagem {
        service.save(message)

        return Mensagem(message.nome, HtmlUtils.htmlEscape(message.mensagem!!))
    }

    @ResponseBody
    @GetMapping("all")
    fun findAll() = service.findAll()
            .onEach { it.mensagem = HtmlUtils.htmlEscape(it.mensagem!!)}

}