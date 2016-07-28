package task.oauth;

import java.util.List;

public class FacebookFriendsResponseDTO {
	
	public List<String> friends;
	
	public FacebookFriendsResponseDTO(List<String> friends) {
		this.friends = friends;
	}
}
