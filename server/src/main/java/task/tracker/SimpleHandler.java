package task.tracker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class SimpleHandler extends AbstractHandler {

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        
        PrintWriter writer = response.getWriter();
    	if ("/emperor".equals(target)) {
        	writer.println("<h1>Hello Emperor of catkind!</h1>");
        }
        else {
        	response.getWriter().println("<ul>");
        	Arrays.asList("write simple server", "add stupid handler", "add conditional to handler")
        		.stream().forEach(todo -> writer.println("\t<li>" + todo + "</li>"));
        	
        	writer.println("</ul>");
        }
	}
	
}
