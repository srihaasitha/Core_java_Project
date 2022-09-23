package com.training.repos;

import static java.util.stream.Collectors.toList;

import static java.util.stream.Collectors.toMap;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.*;

import com.example.demo.utils.ConnectionFactory;
import com.training.exceptions.ElementNotFoundException;
import com.training.ifaces.CrudRepository;
import com.training.ifaces.EmployeeRepository;
import com.training.model.EmployeeDetails;




public class EmployeeDetailsRepository implements EmployeeRepository<EmployeeDetails>{
	private Connection con;
	private ArrayList list;
	private static final Logger logger=LogManager.getRootLogger();
	private List<EmployeeDetails> employeeList;
	public EmployeeDetailsRepository() {
		// TODO Auto-generated constructor stub
		super();
		this.con = ConnectionFactory.getMySqlConnection();
		this.employeeList = findAll();
	}
	public List<EmployeeDetails> findAll(){
		List<EmployeeDetails> list=new ArrayList<>();
		String sql = "select * from employee_personal_details";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String address = rs.getString("address");
				String emailAddress = rs.getString("emailAddress");
				long phoneNumber = rs.getLong("phoneNumber");
				LocalDate dateOfBirth = rs.getDate("dateOfBirth").toLocalDate();
				LocalDate weddingDate = rs.getDate("weddingDate").toLocalDate();
				
				EmployeeDetails obj = new EmployeeDetails(firstName, lastName, address, emailAddress, phoneNumber, dateOfBirth, weddingDate);
				list.add(obj);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean save(EmployeeDetails obj) {
		String sql = "insert into employee_personal_details values(?,?,?,?,?,?,?)";
		int rowUpdated=0;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, obj.getFirstName());
			pstmt.setString(2, obj.getLastName());
			pstmt.setString(3, obj.getAddress());
			pstmt.setString(4, obj.getEmailAddress());
			pstmt.setLong(5, obj.getPhoneNumber());
			pstmt.setDate(6, java.sql.Date.valueOf(obj.getDateOfBirth()));
     		pstmt.setDate(7, java.sql.Date.valueOf(obj.getWeddingDate()));
			rowUpdated = pstmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.employeeList=findAll();
		return rowUpdated==1?true:false;
	}
	@Override
	public void findByFirstName(String firstName) throws ElementNotFoundException {
		List<Object> list = this.employeeList.stream().filter(e->e.getFirstName().contentEquals(firstName)).collect(toList());
		if(list.size()==0) {
			logger.error("No employee with first Name:"+firstName);
			throw new ElementNotFoundException("ERR-102","No employee with first Name:"+firstName);
		}
		else {
			list.forEach(e->logger.info(e));
		}
	}
	@Override
	public void findByFirstNameAndPhoneNumber() throws ElementNotFoundException {
		// TODO Auto-generated method stub
		Map<String, Long> map=this.employeeList.stream().collect(Collectors.toMap(EmployeeDetails::getFirstName,EmployeeDetails::getPhoneNumber ));
		Collection<String> list=new ArrayList<>();
	  	map.forEach((x,y)->list.add("FirstName:"+x+" PhoneNumber:"+y));
	  	if(list.size()==0) {
	  		logger.error("No employee found");
	  		throw new ElementNotFoundException("ERR-102","No employee found");
	  	}
	  	else {
	  		list.forEach(e->logger.info(e));
	  	}
	}

	@Override
	public boolean update(String emailAddress, long phoneNumber,String firstName) {
		String sql = "update employee_personal_details set emailAddress = ? , phoneNumber = ? where firstName = ?";
		int rowUpdated=0;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, emailAddress);
			pstmt.setLong(2, phoneNumber);
			pstmt.setString(3, firstName);
			rowUpdated = pstmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.employeeList=findAll();
		return rowUpdated==1?true:false;
	}

	@Override
	public boolean delete(String firstName) {
		String sql = "delete from employee_personal_details where firstName = ?";
		int rowUpdated=0;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, firstName);
			rowUpdated = pstmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.employeeList=findAll();
		return rowUpdated==1?true:false;
	}
	@Override
	public void findByDateOfBirth(LocalDate dob) throws ElementNotFoundException {
		Map<String, String> map= this.employeeList.stream().filter(e->e.getDateOfBirth().equals(dob)).collect(Collectors.toMap(EmployeeDetails::getFirstName,EmployeeDetails::getEmailAddress ));
	  	Collection<String> list=new ArrayList<>();
	  	map.forEach((x,y)->list.add("FirstName:"+x+" Email:"+y));
	  	if(list.size()==0) {
	  		logger.error("No employee with first Name:"+dob);
	  		throw new ElementNotFoundException("ERR-102","No employee with date of Birth "+dob);
	  	}
	  	else {
	  		list.forEach(e->logger.info(e));
	  	}
		
	}
	@Override
	public void findByWeddingDate(LocalDate weddingDate) throws ElementNotFoundException {
		Map<String, Long> map=this.employeeList.stream().filter(e->e.getWeddingDate().equals(weddingDate))
  			  .collect(Collectors.toMap(EmployeeDetails::getFirstName,EmployeeDetails::getPhoneNumber ));
	  	Collection<String> list=new ArrayList<>();
	  	map.forEach((x,y)->list.add("FirstName:"+x+" PhoneNumber:"+y));
	  	if(list.size()==0) {
	  		logger.error("No employee with first Name:"+weddingDate);
	  		throw new ElementNotFoundException("ERR-102","No employee With wedding date "+weddingDate);
	  	}
	  	else {
	  		list.forEach(e->logger.info(e));
	  	}
		
	}
	
		
	
}
