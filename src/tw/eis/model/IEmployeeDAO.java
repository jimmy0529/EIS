package tw.eis.model;

import java.util.List;

public interface IEmployeeDAO {
	public List<Employee> findEmployeeByEmail(String email);
}
