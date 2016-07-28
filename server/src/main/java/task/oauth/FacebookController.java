package task.oauth;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
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
	public FacebookUserDTO whoAmI() throws Exception {
		ObjectNode result = facebookRestTemplate
				.getForObject("https://graph.facebook.com/me", ObjectNode.class);
		return new FacebookUserDTO(result.get("name").asText());
	}

	public void setFacebookRestTemplate(RestOperations facebookRestTemplate) {
		this.facebookRestTemplate = facebookRestTemplate;
	}

}
