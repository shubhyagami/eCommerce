let stompClient = null;
let chatRoomId = null;

function connectWebSocket() {
    chatRoomId = document.getElementById('roomId')?.value;
    if (!chatRoomId) return;

    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        stompClient.subscribe('/topic/chat/' + chatRoomId, function(message) {
            const msg = JSON.parse(message.body);
            displayMessage(msg);
        });
    });
}

function displayMessage(msg) {
    const container = document.getElementById('chatMessages');
    const div = document.createElement('div');
    div.className = 'chat-msg mb-3' + (msg.sender === 'ADMIN' ? ' text-end' : '');
    div.innerHTML = '<div class="d-inline-block p-3 rounded-3 ' + (msg.sender === 'ADMIN' ? 'bg-primary text-white' : 'bg-light') + '">' +
        '<small class="d-block">' + msg.content + '</small>' +
        '<small class="opacity-75" style="font-size:0.7rem;">' + (msg.sentAt || 'just now') + '</small></div>';
    container.appendChild(div);
    container.scrollTop = container.scrollHeight;
}

function sendChatMessage() {
    const input = document.getElementById('chatInput');
    const content = input.value.trim();
    if (!content || !stompClient) return false;

    const msg = {
        roomId: parseInt(chatRoomId),
        sender: 'USER',
        content: content,
        sentAt: new Date().toISOString()
    };

    stompClient.send('/app/chat.send', {}, JSON.stringify(msg));
    input.value = '';
    return false;
}

function sendAdminChatMessage() {
    const input = document.getElementById('chatInput');
    const content = input.value.trim();
    if (!content || !stompClient) return false;

    const msg = {
        roomId: parseInt(chatRoomId),
        sender: 'ADMIN',
        content: content,
        sentAt: new Date().toISOString()
    };

    stompClient.send('/app/chat.send', {}, JSON.stringify(msg));
    input.value = '';
    return false;
}

$(document).ready(function() {
    connectWebSocket();
});
