var webSocket = null;
var tryTime = 0;
function initSocket(wsurl){
	/*if(!window.WebSocket){
		alert("浏览器不支持webscoket！");
		return false;
	}*/
	if (wsurl.indexOf("sockjs")) {
		//wsurl = http://localhost:8080/Origami/sockjs/webSocketServer
		WebSocket = new SockJS(wsurl);
	}else {
		if ("WebSocket" in window) {
			//wsurl = ws://localhost:8080/Origami/webSocketServer
			webSocket = new WebSocket(wsurl);
			//wsurl = ws://localhost:8080/Origami/webSocketServer
		} else if("MozWebSocket" in window){
			webSocket = new MozWebSocket(wsurl);
		}
	}
	/*if ("WebSocket" in window) {
		//ws = ws://localhost:8080/Origami/webSocketServer
		webSocket = new WebSocket(wsurl);
		//ws = ws://localhost:8080/Origami/webSocketServer
	} else if("MozWebSocket" in window){
		webSocket = new MozWebSocket(wsurl);
	}else {
		//wsurl = http://localhost:8080/Origami/sockjs/webSocketServer
		WebSocket = new SockJS(wsurl);
	}*/

	//异常
	webSocket.onerror = function(event){
		onError(event);
	};
	
	//建立连接
	webSocket.onopen = function(event){
		onOpen(event);
	};
	
	//收到服务端信息
	webSocket.onmessage = function(event){
		onMessage(event);
	};
	
	//关闭连接
	webSocket.onclose = function(){
		onClose();
	}
}



function onMessage(event){
	document.getElementById("message").innerHTML += "<br />" +event.data;
}

function onOpen(event){
	webSocket.send(event);  //向服务器发送消息
	document.getElementById("message").innerHTML = "Connection established";
}

function onError(event){
	alert(event.data);
}

function onClose(){
	webSocket.close();	
}

/*function start(){
	webSocket.send("hello");
	return false;
}*/
