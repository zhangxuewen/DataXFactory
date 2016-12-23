package com.hz.dxf.druid;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.druid.support.http.StatViewServlet;
import com.hz.dxf.tool.Utils;

public class CustomStatViewServlet extends StatViewServlet {

	private static final long serialVersionUID = -7474784683735874255L;

	@Override
	public boolean isPermittedRequest(HttpServletRequest request) {
		String remoteAddress = Utils.getIpAddr(request);
		return isPermittedRequest(remoteAddress);
	}
}
