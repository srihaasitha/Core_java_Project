package com.training.ifaces;

import java.time.LocalDate;

import com.training.exceptions.ElementNotFoundException;

public interface EmployeeRepository<T> extends CrudRepository<T> {
	  
	public boolean update(String emailAddress, long phoneNumber, String firstName);
	
	public boolean delete(String firstName);
	
	public void findByFirstName(String firstName) throws ElementNotFoundException;

	public void findByFirstNameAndPhoneNumber() throws ElementNotFoundException;
	
	public void findByDateOfBirth(LocalDate dob) throws ElementNotFoundException;
	
	public void findByWeddingDate(LocalDate weddingDate) throws ElementNotFoundException;

}
