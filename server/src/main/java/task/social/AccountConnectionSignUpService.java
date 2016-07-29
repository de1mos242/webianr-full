package task.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;

import task.tracker.dao.SocialUserDAO;

@Service
public class AccountConnectionSignUpService implements ConnectionSignUp {

	@Autowired
    private SocialUserDAO usersDao;

    public String execute(Connection<?> connection) {
        UserProfile profile = connection.fetchUserProfile();
        return usersDao.createUser(profile);
    }
}
