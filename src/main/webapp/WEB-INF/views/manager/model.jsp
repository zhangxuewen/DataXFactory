<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	String basePath2 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script type="text/javascript" src="<%=basePath2%>resources/jquery.js"></script>
    	<script type="text/javascript" src="<%=basePath2%>resources/jquery-2.0.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath2%>resources/echarts/echarts.js"></script>
		<script type="text/javascript" src="<%=basePath2%>resources/echarts/echarts-all.js"></script>
		<script type="text/javascript" src="<%=basePath2%>resources/websocket/websocket.js"></script>
		<script type="text/javascript" src="<%=basePath2%>resources/jquery.shCircleLoader.js"></script>
</head>
<body>
    <script src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
     <script type="text/javascript" src="<%=basePath2%>resources/app.js"></script>
	<script>
		jn.loaderShow();
		jn.open({
			path : "/DataXFactory",
			onopen : onopen,
			onmessage : onmessage,
			onerror : onerror,
			onclose : onclose
		});
		function onopen(event) {
			jn.loaderHide();
			console.log("WebSocket:已连接");
			console.log(event);
		};
		function onmessage(event) {
			var data = JSON.parse(event.data);
			console.log("WebSocket:收到一条消息", data);
			
		};
		function onerror(event) {
			console.log("WebSocket:发生错误 ");
			console.log(event);
		};
		function onclose(event) {
			console.log("WebSocket:已关闭");
			console.log(event);
		}
        

</script>
</body>
</html>