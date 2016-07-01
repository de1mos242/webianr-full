package task.tracker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class TodoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        
		PrintWriter writer = resp.getWriter();
		resp.getWriter().println("<ul>");
		Arrays.asList("write simple server", 
						"add stupid handler", 
						"add conditional to handler", 
						"build with gradle")
				.stream()
				.forEach(todo -> writer.println("\t<li>" + todo + "</li>"));
		writer.println("<li>"+req.getAttribute("filterAttr")+"</li>");
		writer.println("</ul>");
	}
}
