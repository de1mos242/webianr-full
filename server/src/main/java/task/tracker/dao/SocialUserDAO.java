package task.tracker.dao;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Repository;

import task.tracker.entities.SocialAuthority;
import task.tracker.entities.SocialUser;
import task.tracker.entities.SocialUserProfile;

@Repository
public class SocialUserDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public String createUser(UserProfile profile) {
		SocialUser user = new SocialUser();
		user.setPassword(RandomStringUtils.randomAlphanumeric(8));
		user.setEnabled(true);
		sessionFactory.getCurrentSession().save(user);
		
		SocialAuthority authority = new SocialAuthority();
		authority.setUserId(user.getId());
		authority.setAuthority("USER");
		sessionFactory.getCurrentSession().save(authority);
		
		SocialUserProfile socialProfile = new SocialUserProfile();
		socialProfile.setUserId(user.getId());
		socialProfile.setEmail(profile.getEmail());
		socialProfile.setFirstName(profile.getFirstName());
		socialProfile.setLastName(profile.getLastName());
		socialProfile.setName(profile.getName());
		socialProfile.setUsername(profile.getUsername());
		sessionFactory.getCurrentSession().save(socialProfile);
		
		return user.getId().toString();
    }
}
