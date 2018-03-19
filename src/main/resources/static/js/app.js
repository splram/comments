var stompClient = null;
var saveComment = function(data, success, usersArray, putFlag) {
	// Convert pings to human readable format
	$(data.pings).each(function(index, id) {
		var user = usersArray.filter(function(user){return user.id == id})[0];
		data.content = data.content.replace('@' + id, '@' + user.fullname);
	});
	data.userId = localStorage["userId"];
	data.topic = localStorage["topic"];;

	var postData = {};
	postData.content = data.content;
	postData.userId = data.userId;
	postData.topic = data.topic;
	if (data.parent) {
		postData.parent = data.parent;
	}
	var url = host + '/comments';
	var type = 'POST'
	if (putFlag) {
		url = url + "/" + data.id;
		type = 'PUT';
	}
	$.ajax({
            type: type,
            url: url,
            data:JSON.stringify(postData),
            dataType: "text",
            contentType: 'application/json',
            success: function(savedData, textStatus, jQxhr ){
            	var parsedData = JSON.parse(savedData);
            	if (putFlag) {
	            	data.id =  parsedData.id;
            	} else {
	            	data.id = 'c' + parsedData.id;
            	}
            	success(data);
            },
            error: function( jqXhr, textStatus, errorThrown ){
                console.log( errorThrown );
            }
        });
	 
	return data;
}
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient.subscribe('/topic/comments', function () {
        	//console.log('xxxxxxxxxxxxx');
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
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function publish() {
	stompClient.send("/app/publish", {});
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});