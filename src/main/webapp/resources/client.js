


// 初始化WebSocket
function initWebSocket() {
    if (window.WebSocket) {
            var webServer = "www.jnoos.com";
            var _url = "ws://"+ webServer + "/DataXFactory";
            var ws = new WebSocket(_url);
            // 连接成功
            ws.onopen = function () {
                console.log("连接成功")
            };
            // 消息接收
            ws.onmessage = function (eve) {
                var msg = JSON.parse(eve);
                console.log("收到消息："+ msg)
            };
            // 连接失败
            ws.onerror = function (){
                console.log("连接成功")
            };
            // 连接断开
            ws.onclose = function () {
                console.log("连接成功")
            };

    } else {
        console.log("你的浏览器不支持 ws");
    }
};
initWebSocket();




