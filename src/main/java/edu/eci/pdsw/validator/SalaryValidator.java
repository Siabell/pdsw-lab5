package edu.eci.pdsw.validator;

import java.util.Optional;

import javax.servlet.jsp.jstl.sql.Result;

import edu.eci.pdsw.model.*;

/**
 * Utility class to validate an employee's salary
 */
public class SalaryValidator implements EmployeeValidator {

	/**
	 * {@inheritDoc}}
	 */
	public Optional<ErrorType> validate(Employee employee) {

		long salario = employee.getSalary();
		Optional<ErrorType> result = Optional.empty();
		if (salario >= 100 && salario <= 50000){
			SocialSecurityType afiliacion = employee.getSocialSecurityType();
			switch (afiliacion){
				case SISBEN:
					if (!(salario >= 100 && salario <1500)){
						result = Optional.of(ErrorType.INVALID_SISBEN_AFFILIATION);
					}
					break;
				case EPS:
					if (!(salario >= 1500 && salario <10000)){
						result = Optional.of(ErrorType.INVALID_EPS_AFFILIATION);
					}
					break;
				case PREPAID:
					if (!(salario >= 10000 && salario <= 50000)){
						result = Optional.of(ErrorType.INVALID_PREPAID_AFFILIATION);
					}	
					break;
			}

		} else {
			result = Optional.of(ErrorType.INVALID_SALARY);
		}

		return result;
	}
}
