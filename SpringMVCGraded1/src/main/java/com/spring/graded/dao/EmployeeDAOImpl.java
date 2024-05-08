package com.spring.graded.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.spring.graded.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addEmployee(Employee e) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(e);
		System.out.println("Employee saved successfully, Employee Details=" + e);
//		logger.info("Person saved successfully, Person Details="+p);
	}

	@Override
	public void updateEmployee(Employee e) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(e);
		System.out.println("Employee updated successfully,Employee Details=" + e);
//		logger.info("Person updated successfully, Person Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> listEmployees() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Employee> employeeList = session.createQuery("from Employee").list();
		for (Employee e : employeeList) {
			System.out.println("Employee List::" + e);
//			logger.info("Person List::"+p);
		}
		return employeeList;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("removal")
		Employee e = (Employee) session.load(Employee.class, new Integer(id));
		System.out.println("Employee loaded successfully, Employee details=" + e);
//		logger.info("Person loaded successfully, Person details="+p);
		return e;
	}

	@Override
	public void removeEmployee(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("removal")
		Employee e = (Employee) session.load(Employee.class, new Integer(id));
		if (null != e) {
			session.delete(e);
		}
		System.out.println("Employee deleted successfully, Employee details=" + e);
//		logger.info("Person deleted successfully, person details="+p);
	}

}
