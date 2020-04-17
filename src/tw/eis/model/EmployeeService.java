package tw.eis.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	private EmployeeDAO eDAO;
	
	@Autowired
	public EmployeeService(EmployeeDAO eDAO) {
		this.eDAO = eDAO;
	}
	
	public List<Employee> findEmployeeByEmail(String email){
		return eDAO.findEmployeeByEmail(email);
	}
}
