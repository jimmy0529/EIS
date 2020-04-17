package tw.eis.model;

import java.util.List;

public interface IEmployeeService {
	public List<Employee> findEmployeeByEmail(String email);
}
