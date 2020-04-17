package tw.eis.model;

import java.util.List;

public interface IUsersService {
	public List<Users> findUsers(String userName, String userPassword);
	public List<Users> findUsersByID(int userID);
	public boolean updateUsersPassword(String userName, String userPassword);
}
