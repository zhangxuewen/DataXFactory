package com.hz.dxf.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/***
 * Socket建立连接（握手）和断开
 * 
 * @author aly
 *
 */
public class HandShake implements HandshakeInterceptor {

	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			// 标记终端
			String uid = servletRequest.getServletRequest().getParameter("cid");
			if ((uid == null ? "" : uid).equals("")) {
				HttpSession session = servletRequest.getServletRequest().getSession(false);
				uid = session.getAttribute("cid").toString();
			}
			System.out.println("Websocket:终端[ID:" + uid + "]已经建立连接");
			if (!(uid == null ? "" : uid).equals("")) {
				attributes.put("cid", uid);
			} else {
				return false;
			}
		}
		return true;
	}

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
	}

}
