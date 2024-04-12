

var username= ""


function sendMessage() {
    let messageContent = $("#message-value").val();

    if (messageContent.trim() === "") {
        // Do not send empty messages
        return;
    }

    let jsonOb = {
        name: username,
        content: messageContent
    };

    stompClient.send("/app/message2", {}, JSON.stringify(jsonOb));

    // Save the message to the database
    saveMessageToDatabase(jsonOb);

    // Scroll to the bottom after sending a message
    scrollMessageContainerToBottom();
}



function connect() {
	
    let socket = new SockJS("/server1");
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {
        console.log("Connected: " + frame);

        // subscribe
        stompClient.subscribe("/topic/return-to2", function(response) {
            showMessage(JSON.parse(response.body));
        });
    });
}




function showMessage(message) {
    // Prepend the message to the message container
    $("#message-container-table").append(`
   
     <div class="row">
            <div class="col-md-12">
                <div class="typing text-success"></div>
                <ul class="comment__box">
                     <li class="comment">
                        <div class="card border-light mb-3">
                            <div class="card-body">
                          <h6>${message.name}</h6>
                                <p> ${message.content}</p>
                              
                            </div>
                        </div>
                    </li> `);
 //  <tr><td><b>${message.name}:</b> ${message.content}</td></tr>

    // Maintain scroll position at the bottom:
    scrollMessageContainerToBottom();
}




function scrollMessageContainerToBottom() {
    var messageContainer = $("#message-container-table");
    messageContainer.scrollTop(messageContainer[0].scrollHeight);
}



$(document).ready(() => {
    // Fetch user information when the chatroom is loaded
    connect();
    
    username = $("input[name='fullname']").val();
    
    $("#send-btn").click(() => {
        sendMessage();
    });

    $("#logout").click(() => {
        if (stompClient !== null) {
            stompClient.disconnect();
            console.log(stompClient);
        }
    });
});