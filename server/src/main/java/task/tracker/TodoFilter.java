package task.tracker;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TodoFilter implements Filter {
	
	FilterConfig config;

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Hello from filter " + config.getInitParameter("filter greeting name"));
		request.setAttribute("filterAttr", "42");
		chain.doFilter(request, response);
		System.out.println(config.getInitParameter("good bye string"));
		
		System.out.println(config.getInitParameter("empty param"));
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	
}
