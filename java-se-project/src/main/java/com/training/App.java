package com.training;

import java.sql.Connection;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;


import com.example.demo.utils.ConnectionFactory;
import com.training.exceptions.ElementNotFoundException;

import com.training.ifaces.CrudRepository;
import com.training.model.EmployeeDetails;

import com.training.repos.EmployeeDetailsRepository;
import com.training.services.EmployeeService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	EmployeeService service = new EmployeeService();
    	Scanner scan = new Scanner(System.in);
    	String firstName;
    	String lastName;
    	String address;
    	String emailAddress;
    	long phoneNumber;
    	LocalDate dateOfBirth;
    	LocalDate weddingDate;
    	System.out.println("Select the Required Functionality");
    	while(true){
			System.out.println("1.Add Employee Details");
			System.out.println("2. List of employees by their First Name");
			System.out.println("3. List of employees by their First Name and Phone Number");
			System.out.println("4. Update the email and phoneNumber of a particular employee.");
			System.out.println("5. Delete Details of a Particular employee by firstName");
			System.out.println("6. Get a list of employees with their firstName and emailAddress  whose Birthday falls on the given date");
			System.out.println("7. Get the list of employees with their firstName and phone Number whose Wedding Anniversary falls on the given date");
			System.out.println("8. Exit the menu");
			System.out.println("Enter your choice:");
			int choice = Integer.parseInt(scan.nextLine());
			switch(choice){
			case 1:
				System.out.println("Enter employee First Name:");
				firstName = scan.nextLine();
				System.out.println("Enter employee Last Name:");
				lastName = scan.nextLine();
				System.out.println("Enter employee Address:");
				address = scan.nextLine();
				System.out.println("Enter employee Email Address:");
				emailAddress = scan.nextLine();
				System.out.println("Enter employee Phone Number:");
				phoneNumber = Long.parseLong(scan.nextLine());
				System.out.println("Enter employee Date Of Birth:");
				System.out.println("Enter in this format: yyyy-mm-dd");
				String birthDate = scan.nextLine();
				dateOfBirth= LocalDate.parse(birthDate);
				System.out.println("Enter employee Wedding Date:");
				System.out.println("Enter in this format: yyyy-mm-dd");
				String wedding = scan.nextLine();
				weddingDate=LocalDate.parse(wedding);
				EmployeeDetails employee = new EmployeeDetails(firstName, lastName, address, emailAddress, phoneNumber, dateOfBirth, weddingDate);
				System.out.println();
				System.out.println(service.add(employee));
				System.out.println();
				
			break;
			case 2:
				System.out.println("Enter the First Name:");
				firstName = scan.nextLine();
				System.out.println();
				try {
					service.findByfirstName(firstName);
				} catch (ElementNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println();
			break;
			case 3:
				System.out.println();
				try {
					service.findByfirstNameAndPhoneNumber();
				} catch (ElementNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println();
			break;
			case 4:
				System.out.println("Enter the First Name:");
				firstName = scan.nextLine();
				System.out.println("Enter the Phone Number:");
				phoneNumber = Long.parseLong(scan.nextLine());
				System.out.println("Enter the Email Address:");
				emailAddress = scan.nextLine();
				System.out.println();
				try {
					service.updateEmailAddressAndPhoneNumber(emailAddress,phoneNumber,firstName);
				} catch (ElementNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println();
			break;
			case 5:
				System.out.println("Enter the First Name:");
				firstName = scan.nextLine();
				System.out.println();
				try {
					System.out.println(service.deleteByFirstName(firstName));
				} catch (ElementNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println();
			break;
			case 6:
				System.out.println("Enter the Date Of Birth:");
				String dob = scan.nextLine();
				dateOfBirth=LocalDate.parse(dob);
				System.out.println();
				try {
					service.findByDateOfBirth(dateOfBirth);
				} catch (ElementNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println();
			break;
			case 7:
				System.out.println("Enter the Date of Birth:");
				String wedDate = scan.nextLine();
				weddingDate=LocalDate.parse(wedDate);
				System.out.println();
				try {
					service.findByWeddingDate(weddingDate);
				} catch (ElementNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println();
			break;
			case 8:
				System.out.println("Exited from Menu");
				System.exit(0);
			default: System.out.println("Incorrect input!!! Please re-enter choice from our menu");
			}
		}
    	
    }
}
