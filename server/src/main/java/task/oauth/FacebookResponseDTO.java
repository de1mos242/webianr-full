package task.oauth;

import java.util.List;

public class FacebookResponseDTO {
	
	public List<String> friends;
	
	public FacebookResponseDTO(List<String> friends) {
		this.friends = friends;
	}
}
