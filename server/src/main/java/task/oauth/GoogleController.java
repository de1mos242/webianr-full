package task.oauth;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author Ryan Heaton
 * @author Dave Syer
 */
@RestController
public class GoogleController {

	private RestOperations googleRestTemplate;
	
	@RequestMapping("/google/login")
	public void login(HttpServletResponse response) throws Exception {
	    googleRestTemplate
				.getForObject("https://www.googleapis.com/oauth2/v1/userinfo?alt=json", ObjectNode.class);
	    response.sendRedirect("/server/#/login");
	}

	@RequestMapping("/google/me")
	public UserDTO whoAmI() throws Exception {
		ObjectNode result = googleRestTemplate
				.getForObject("https://www.googleapis.com/oauth2/v1/userinfo?alt=json", ObjectNode.class);
		return new UserDTO(result.get("name").asText());
	}

	public void setGoogleRestTemplate(RestOperations googleRestTemplate) {
		this.googleRestTemplate = googleRestTemplate;
	}

}
