package task.tracker;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class EmbeddedStart {

	public static void main(String[] args) {
		try {
			Server server = new Server(8080);
			WebAppContext context = new WebAppContext();
			context.setDescriptor(EmbeddedStart.class.getResource("/WEB-INF/web.xml").toString());
			context.setResourceBase(".");
			context.setContextPath("/");
			
			System.out.println(String.format("Init: [%s] [%s] [%s]" , context.getDescriptor(), context.getResourceBase(), context.getContextPath()));
			
			server.setHandler(context);

			server.start();
			server.join();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
