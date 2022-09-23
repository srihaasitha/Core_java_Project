package com.training.ifaces;
import java.time.LocalDate;
import java.util.*;

import com.training.exceptions.ElementNotFoundException;
import com.training.model.EmployeeDetails;

public interface CrudRepository<T> {

	public boolean save(T obj);

	public Collection<T> findAll();
}
