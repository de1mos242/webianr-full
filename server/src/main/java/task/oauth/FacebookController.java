package task.oauth;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author Ryan Heaton
 * @author Dave Syer
 */
@RestController
public class FacebookController {

	private RestOperations facebookRestTemplate;
	
	@RequestMapping("/facebook/login")
	public void login(HttpServletResponse response) throws Exception {
	    facebookRestTemplate
				.getForObject("https://graph.facebook.com/me", ObjectNode.class);
	    response.sendRedirect("/server/#/login");
	}

	@RequestMapping("/facebook/info")
	public FacebookFriendsResponseDTO getInfo() throws Exception {
		ObjectNode result = facebookRestTemplate
				.getForObject("https://graph.facebook.com/me/invitable_friends", ObjectNode.class);
		ArrayNode data = (ArrayNode) result.get("data");
		ArrayList<String> friends = new ArrayList<String>();
		for (JsonNode dataNode : data) {
			friends.add(dataNode.get("name").asText());
		}
		return new FacebookFriendsResponseDTO(friends);
	}
	
	@RequestMapping("/facebook/me")
	public UserDTO whoAmI() throws Exception {
		ObjectNode result = facebookRestTemplate
				.getForObject("https://graph.facebook.com/me", ObjectNode.class);
		return new UserDTO(result.get("name").asText());
	}

	public void setFacebookRestTemplate(RestOperations facebookRestTemplate) {
		this.facebookRestTemplate = facebookRestTemplate;
	}

}
