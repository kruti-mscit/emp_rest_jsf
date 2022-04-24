/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Department;
import entity.Employee;
import entity.Project;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author kruti
 */
@Local
public interface EmpBeanLocal {
    
    //Department
    void addDepartment(String deptName);
    void updateDepartment(Integer deptId, String deptName);
    void deleteDepartment(Integer deptId);
    Collection<Department> getAllDepartments();
    
    
    //Employee
    void addEmployee(String fname, String lname, int age, int salary, Integer deptId);
    void updateEmployee(Integer empId, String fname, String lname, int age, int salary, Integer deptId);
    void deleteEmployee(Integer empId);
    Collection<Employee> getAllEmployees();
    
    //Project
    void addProject(String projectName, int cost, String startDate);
    void updateProject(Integer projectId,String projectName, int cost, String startDate);
    void deleteProject(Integer projectId);
    Collection<Project> getAllProjects();
    
    void addEmployeesToProject(Integer projectId,Collection<Integer> empIds);
    void removeEmployeesToProject(Integer projectId,Collection<Integer> empIds);
    
    //Searching methods
    
    Collection<Project> getEmpProjects(Integer EmpId);
    Collection<Employee> getProjectEmps(Integer ProjectId);
    Collection<Employee> getEmpByDeptName(String deptName);
    Collection<Employee> getEmpByDeptNames(Collection<String> deptNames);
    Collection<Employee> getEmpByDeptAndSalary(String deptName,int salary);
    Collection<Employee> getEmpBySalary(int salary);
    Collection<Employee> getEmpByDeptId(Integer deptId);
    Collection<Project> getProjectByName(String projectName);
    Collection<Project> getProjectWithinCostRange(int startCost,int endCost);
    
}
