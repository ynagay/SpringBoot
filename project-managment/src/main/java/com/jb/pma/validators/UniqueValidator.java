package com.jb.pma.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.jb.pma.dao.EmployeeRepository;
import com.jb.pma.entities.Employee;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

	@Autowired
	EmployeeRepository empRepo;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		System.out.println("Entered the validation method..");
		Employee emp = empRepo.findByEmail(value);
		
		if (emp != null) {
		return false;
		} else {
			return true;
		}
	}

}
