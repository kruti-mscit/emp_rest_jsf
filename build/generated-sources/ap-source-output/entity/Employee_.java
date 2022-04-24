package entity;

import entity.Department;
import entity.Project;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-04-24T22:09:18", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile SingularAttribute<Employee, Integer> empId;
    public static volatile SingularAttribute<Employee, String> fname;
    public static volatile SingularAttribute<Employee, String> lname;
    public static volatile SingularAttribute<Employee, Department> dept;
    public static volatile SingularAttribute<Employee, Integer> salary;
    public static volatile CollectionAttribute<Employee, Project> projectCollection;
    public static volatile SingularAttribute<Employee, Integer> age;

}