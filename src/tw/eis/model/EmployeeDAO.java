package tw.eis.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO implements IEmployeeDAO {
	private SessionFactory sessionFactory;

	@Autowired
	public EmployeeDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Employee> findEmployeeByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query<Employee> query = session.createQuery("from Employee where email = :email", Employee.class);
		query.setParameter("email", email);
		List<Employee> list = query.list();
		return list;
	}


}
