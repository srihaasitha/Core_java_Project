package com.training.services;

import java.sql.Connection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.*;

import com.example.demo.utils.ConnectionFactory;
import com.training.exceptions.ElementNotFoundException;
import com.training.model.EmployeeDetails;
import com.training.repos.EmployeeDetailsRepository;
import static java.util.stream.Collectors.toList;

import static java.util.stream.Collectors.toMap;
public class EmployeeService {
	private static final Logger logger=LogManager.getRootLogger();
	EmployeeDetailsRepository repo = new EmployeeDetailsRepository();
	public EmployeeService() {
		super();
		this.repo = repo;
	}

	
	Collection<EmployeeDetails> result= new ArrayList<>();
	
	public String add(EmployeeDetails obj) {
		boolean result = repo.save(obj);
		return result?"One record added":"Please enter all the details";
	}
	public void findByfirstName(String firstName) throws ElementNotFoundException {
		repo.findByFirstName(firstName);
	}
	public void findByfirstNameAndPhoneNumber() throws ElementNotFoundException {
		repo.findByFirstNameAndPhoneNumber();
	}
	public String updateEmailAddressAndPhoneNumber(String emailAddress, long phoneNumber, String firstName)throws ElementNotFoundException {
		boolean result = repo.update(emailAddress,phoneNumber,firstName);
		if(!result) {
			logger.error("Employee Not deleted");
			throw new ElementNotFoundException("ERR-102","Employee not found"+firstName);
		}
		else {
			logger.info("Employee updated "+firstName);
			return result?"One record deleted":"Please enter all the details";
		}
	}
	public String deleteByFirstName(String firstName) throws ElementNotFoundException{
		boolean result = repo.delete(firstName);
		if(!result) {
			logger.error("Employee Not deleted");
			throw new ElementNotFoundException("ERR-102","Employee not found"+firstName);
		}
		else {
			logger.info("Employee deleted "+firstName);
			return result?"One record deleted":"Please enter all the details";
		}
		
	}
	public void findByDateOfBirth(LocalDate dateOfBirth) throws ElementNotFoundException {
		repo.findByDateOfBirth(dateOfBirth);
	}
	public void findByWeddingDate(LocalDate weddingDate) throws ElementNotFoundException {
		repo.findByWeddingDate(weddingDate);
	}
	
	
}
