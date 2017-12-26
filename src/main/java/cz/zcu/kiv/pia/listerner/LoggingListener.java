package cz.zcu.kiv.pia.listerner;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class LoggingListener implements ServletRequestListener
{

	@Override
	public void requestDestroyed(ServletRequestEvent requestEvent) 
	{
	}

	@Override
	public void requestInitialized(ServletRequestEvent requestEvent) 
	{
		ServletContext context = requestEvent.getServletContext();
		HttpServletRequest request = (HttpServletRequest) requestEvent.getServletRequest();
		String method = request.getMethod();
		StringBuffer url = request.getRequestURL();
		url.append(request.getQueryString() != null ? "?" + request.getQueryString() : "");
		context.log(method + " ~ " + url.toString());
	}

}
