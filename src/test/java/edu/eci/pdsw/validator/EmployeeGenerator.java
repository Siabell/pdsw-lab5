package edu.eci.pdsw.validator;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

import org.quicktheories.core.Gen;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.SocialSecurityType;

import org.quicktheories.generators.Generate;
import org.quicktheories.generators.LongsDSL;

public class EmployeeGenerator{

    
    public static Gen<Employee> empleados(){
        return identificacion().zip(salario (), seguro (),
        (identificacion, salario, seguro) -> new Employee(identificacion, salario, seguro));
    }

    private static Gen<Integer> identificacion() {
        return integers().from(1000).upToAndIncluding(100000);
    }

    private static Gen<Long> salario () {
        return (longs()).between(100,50000);
    }

    private static Gen<SocialSecurityType> seguro () {
        return Generate.enumValues(SocialSecurityType.class);
    }

}