/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.EmpBeanLocal;
import client.EmpClient;
import entity.Department;
import entity.Employee;
import entity.Project;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.Min;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kruti
 */
@Named(value = "reBean")
@ApplicationScoped
public class RestEmployeeBean {
    
    @EJB EmpBeanLocal empBean;
    EmpClient ec;
    Response res;

    Employee emp=new Employee();
    Department dept=new Department();
    
    private Integer empId;
    
    private String fname;
    private String lname;
    
    //@Size(max=2, message = "Age must contain 2 digit number")  
    @Min(18)
    private int age;
    private int salary;
    private Integer deptId;
    
    //private Department dept;
    private Collection<Department> departments;
    private GenericType<Collection<Department>> gdepartments;
    private Collection<Project> projectCollection;
    private Collection<String> deptNms;
    private GenericType<Collection<Employee>> gEmps;
    private String deptName;
    
    public RestEmployeeBean() {
    }

    @PostConstruct          //IMPORTANT
    public void init(){
        ec=new EmpClient();
        
        gdepartments=new GenericType<Collection<Department>>(){};
        
        res=ec.getAllDepartments(Response.class);
        this.departments=(Collection<Department>) res.readEntity(gdepartments);
        this.deptNms=new ArrayList<String>();
        this.deptName="";
        gEmps=new GenericType<Collection<Employee>>(){};
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Collection<String> getDeptNms() {
        return deptNms;
    }

    public void setDeptNms(Collection<String> deptNms) {
        this.deptNms = deptNms;
    }

    
    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
    
    public List<Employee> findAll(){
        
        if(deptNms.isEmpty()){
            res=ec.getAllEmployees(Response.class);
            return (List<Employee>) res.readEntity(gEmps);
            //return (List<Employee>) this.empBean.getAllEmployees();
        }else{
            res=ec.getEmpByDeptNames(deptNms,Response.class);
            return (List<Employee>) res.readEntity(gEmps);
            //return (List<Employee>) this.empBean.getEmpByDeptNames(deptNms);
        }
    
    }
    
    public List<Employee> filterEmpList(){
        System.out.println(deptName);
        
        if((deptName=="" && salary==0) || (deptName.equals("All")&& salary==0)){
            
            return (List<Employee>) this.empBean.getAllEmployees();
        }else if(deptName.equals("All")&& salary!=0){
            
            return (List<Employee>) this.empBean.getEmpBySalary(salary);
        }else{
            return (List<Employee>) this.empBean.getEmpByDeptAndSalary(deptName,salary);
        }
    }
    
    public String addEmployee(){
        
        //empBean.addEmployee(fname, lname, age, salary, deptId);
        ec.addEmployee(fname, lname, String.valueOf(age), String.valueOf(salary), String.valueOf(deptId));
        return "index";
    }
    
    public String edit(Employee e){
        this.emp=e;
        this.empId=e.getEmpId();
        this.dept=e.getDept();
        this.fname=e.getFname();
        this.lname=e.getLname();
        this.age=e.getAge();
        this.salary=e.getSalary();
        this.deptId=e.getDept().getDeptId();
        
        return "edit";
    }

    public Collection<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Collection<Department> departments) {
        this.departments = departments;
    }
    
    
    
    public String edit(){
        
        ec.updateEmployee(String.valueOf(empId), fname, lname, String.valueOf(age), String.valueOf(salary), String.valueOf(deptId));
        //empBean.updateEmployee(empId, fname, lname, age, salary, deptId);
        this.emp=null;
        this.empId=0;
        this.dept=null;
        this.fname="";
        this.lname="";
        this.age=0;
        this.salary=0;
        this.deptId=0;
        return "index";
    }
    
    public void deleteEmp(Integer id){
        //empBean.deleteEmployee(id);
    
        ec.deleteEmployee(String.valueOf(id));
    }
    
}
