package edu.eci.pdsw.validator;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;
import edu.eci.pdsw.validator.ErrorType;
import org.junit.Test;

import java.util.Optional;

/**
 * Test class for {@linkplain SalaryValidator} class
 */
public class SalaryValidatorTest {

	/**
	 * The class under test.
	 */
	private SalaryValidator validator = new SalaryValidator();

	/**
	 * {@inheritDoc}}
	 * 
	 */
	@Test
	public void validateTest() {
		qt()
		.forAll(EmployeeGenerator.empleados())
		.check( empleado -> {
			Optional<ErrorType> validar =validator.validate(empleado);
			if (validar.equals(Optional.empty())){
				return true;
			} else {
				boolean result = false;
				switch (validar.get()){
					case INVALID_SISBEN_AFFILIATION:
						result= !(empleado.getSalary()>=100 && empleado.getSalary()<1500);
						break;
					case INVALID_EPS_AFFILIATION:
						result= !(empleado.getSalary()>=1500 && empleado.getSalary()<10000);
						break;
					case INVALID_PREPAID_AFFILIATION:
						result= !(empleado.getSalary()>=10000 && empleado.getSalary()<=50000);
						break;
					case INVALID_SALARY:
						result= !(empleado.getSalary()<100 && empleado.getSalary()>50000);
						break;
				}
				return result;
			}

		});

		//validator.validate(null);
	}
}
