package tw.eis.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements IUsersService {
	private UsersDAO uDAO;
	
	@Autowired
	public UsersService(UsersDAO uDAO) {
		this.uDAO = uDAO;
	}
	
	public List<Users> findUsers(String userName, String userPassword) {
		return uDAO.findUsers(userName, userPassword);
	}
	
	public List<Users> findUsersByID(int userID){
		return uDAO.findUsersByID(userID);
	}
	
	public boolean updateUsersPassword(String userName, String userPassword) {
		return uDAO.updateUsersPassword(userName, userPassword);
	}

}
