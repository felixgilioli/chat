
var nome = prompt("Qual seu nome?");


window.onload = setConnected(true);

var stompClient = null;

function setConnected() {
    $.get( "http://192.168.89.30:8080/all", function( data ) {


        for (var i = 0; i < data.length; i++) {
            showGreeting(data[i])
        }
    });


    console.log(nome);
    $("#conversation").show();
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            var json = JSON.parse(greeting.body);
            showGreeting(json);
            var msg = new SpeechSynthesisUtterance(json.mensagem);
            window.speechSynthesis.speak(msg);
        });
    });

}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    var mensagem = $("#mensagem").val();

    if (mensagem.trim()) {
        stompClient.send("/app/hello", {}, JSON.stringify({'nome': nome, 'mensagem': mensagem}));
        $("#mensagem").val("");
    } else {
        alert("Digite uma mensagem válida!");
    }

}

function showGreeting(message) {
    $("#greetings").prepend("<tr><td>" + message.nome  + "</td><td>" + message.mensagem + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    connect();
    $( "#send" ).click(function() { sendName(); });
});
