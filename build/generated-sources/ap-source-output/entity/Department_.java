package entity;

import entity.Employee;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-04-24T22:09:18", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Department.class)
public class Department_ { 

    public static volatile SingularAttribute<Department, String> deptName;
    public static volatile SingularAttribute<Department, Integer> deptId;
    public static volatile CollectionAttribute<Department, Employee> employeeCollection;

}