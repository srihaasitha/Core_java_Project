package com.training.model;

import java.sql.Date;
import java.time.LocalDate;

public class EmployeeDetails {
	private String firstName;
	private String lastName;
	private String address;
	private String emailAddress;
	private long phoneNumber;
	private LocalDate dateOfBirth;
	private LocalDate weddingDate;
	

	public EmployeeDetails(String firstName, String lastName, String address, String emailAddress, long phoneNumber,LocalDate dateOfBirth, LocalDate weddingDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.weddingDate = weddingDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getWeddingDate() {
		return weddingDate;
	}

	public void setWeddingDate(LocalDate weddingDate) {
		this.weddingDate = weddingDate;
	}

	@Override
	public String toString() {
		return "EmployeeDetails [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", emailAddress=" + emailAddress + ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth
				+ ", weddingDate=" + weddingDate + "]";
	}
	
}
