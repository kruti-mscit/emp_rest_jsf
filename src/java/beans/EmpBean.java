/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Department;
import entity.Employee;
import entity.Project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kruti
 */
@Stateless
public class EmpBean implements EmpBeanLocal {

    @PersistenceContext(unitName="EmpFinalMixPU")
    EntityManager em;

    @Override
    public void addDepartment(String deptName) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Department d=new Department(deptName);
        em.persist(d);
    
    }

    @Override
    public void updateDepartment(Integer deptId, String deptName) {
        
        Department d=(Department) em.find(Department.class,deptId);
        d.setDeptName(deptName);
        em.merge(d);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteDepartment(Integer deptId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Department d=(Department) em.find(Department.class,deptId);
        em.remove(d);
    }

    @Override
    public Collection<Department> getAllDepartments() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        return em.createNamedQuery("Department.findAll").getResultList();
    }

    @Override
    public void addEmployee(String fname, String lname, int age, int salary, Integer deptId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Employee e=new Employee(fname,lname,age,salary);
        Department d=(Department) em.find(Department.class,deptId);
        e.setDept(d);
        
        
        Collection<Employee> empCol=d.getEmployeeCollection();
        empCol.add(e);
        d.setEmployeeCollection(empCol);
        
        em.persist(e);
        em.merge(d);
    }
    

    @Override
    public void updateEmployee(Integer empId, String fname, String lname, int age, int salary, Integer deptId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Employee e=(Employee) em.find(Employee.class,empId);
        
        Integer oldDept=e.getDept().getDeptId();
        
        e.setFname(fname);
        e.setLname(lname);
        e.setAge(age);
        e.setSalary(salary);
        
        Department d=(Department) em.find(Department.class,deptId);
        e.setDept(d);
        
        
        Collection<Employee> eCol;
        Collection<Employee> oeCol;
        if(oldDept!=d.getDeptId()){
            eCol=d.getEmployeeCollection();
            eCol.add(e);
            d.setEmployeeCollection(eCol);
            
            Department od=(Department) em.find(Department.class,oldDept);
            oeCol=od.getEmployeeCollection();
            oeCol.remove(e);
            od.setEmployeeCollection(oeCol);
            em.merge(od);
        }
        
        em.merge(e);
        em.merge(d);
    }

    @Override
    public void deleteEmployee(Integer empId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Employee e=(Employee) em.find(Employee.class, empId);
        Department d=e.getDept();
        
        
        
        Collection eCol=d.getEmployeeCollection();
        eCol.remove(e);
        d.setEmployeeCollection(eCol);
        
        em.remove(e);
        em.merge(d);
    
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        return em.createNamedQuery("Employee.findAll").getResultList();
    }

    @Override
    public void addProject(String projectName, int cost, String startDate) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Project p=new Project(projectName,cost,startDate);
        em.persist(p);
    }

    @Override
    public void updateProject(Integer projectId, String projectName, int cost, String startDate) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Project p=(Project) em.find(Project.class,projectId);
        p.setProjectName(projectName);
        p.setCost(cost);
        p.setStartDate(startDate);
        em.merge(p);
    }

    @Override
    public void deleteProject(Integer projectId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Project p=(Project) em.find(Project.class,projectId);
        em.remove(p);
    }

    @Override
    public Collection<Project> getAllProjects() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        return em.createNamedQuery("Project.findAll").getResultList();
    }

    @Override
    public void addEmployeesToProject(Integer projectId, Collection<Integer> empIds) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Project p=(Project) em.find(Project.class, projectId);
        
        //Check if employee already added ?
        
        Collection<Employee> eCol=p.getEmployeeCollection();
        
        for(Integer eId:empIds){
            
            Employee e=(Employee) em.find(Employee.class,eId);
            if(!eCol.contains(e)){
                
               
                eCol.add(e);
                p.setEmployeeCollection(eCol);
                
                Collection<Project> pCol=e.getProjectCollection();
                pCol.add(p);
                e.setProjectCollection(pCol);
                em.merge(p);
                
            }
        }
        
    }

    @Override
    public void removeEmployeesToProject(Integer projectId, Collection<Integer> empIds) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Project p=(Project) em.find(Project.class, projectId);
        
        //Check if employee already added ?
        
        Collection<Employee> eCol=p.getEmployeeCollection();
        for(Integer eId:empIds){
            
            Employee e=(Employee) em.find(Employee.class,eId);
            if(eCol.contains(e)){
                
                eCol.remove(e);
                p.setEmployeeCollection(eCol);
                
                Collection<Project> pCol=e.getProjectCollection();
                pCol.remove(p);
                e.setProjectCollection(pCol);
                em.merge(p);    //IMPORTANT
                
            }
        }
    
    }

    @Override
    public Collection<Project> getEmpProjects(Integer EmpId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Employee e=(Employee) em.find(Employee.class, EmpId);
        return e.getProjectCollection();
    }

    @Override
    public Collection<Employee> getProjectEmps(Integer ProjectId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Project p=(Project) em.find(Project.class, ProjectId);
        return p.getEmployeeCollection();
    }

    @Override
    public Collection<Employee> getEmpByDeptName(String deptName) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Collection<Department> depts=em.createNamedQuery("Department.findByDeptName")
                .setParameter("deptName", deptName)
                .getResultList();
        
        Collection<Employee> emps=new ArrayList<Employee>();
        
        for(Department d:depts){
            emps.addAll(d.getEmployeeCollection());
        }
        return emps;
    }
    
    @Override
    public Collection<Employee> getEmpByDeptNames(Collection<String> deptNames) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        
        Collection<Department> depts=em.createNamedQuery("Department.findByDeptNames")
                .setParameter("deptNames", deptNames)
                .getResultList();
        
        Collection<Employee> emps=new ArrayList<Employee>();
        
        for(Department d:depts){
            emps.addAll(d.getEmployeeCollection());
        }
        return emps;
    }

    @Override
    public Collection<Employee> getEmpByDeptAndSalary(String deptName, int salary) {
        Collection<Department> depts=em.createNamedQuery("Department.findByDeptName")
                .setParameter("deptName", deptName)
                .getResultList();
        
        Collection<Employee> emps=new ArrayList<Employee>();
        Collection<Employee> empsFinal=new ArrayList<Employee>();
        
        for(Department d:depts){
            emps.addAll(d.getEmployeeCollection());
        }
        
        for(Employee e:emps){
            if(e.getSalary()>=salary){
                empsFinal.add(e);
            }
        }
        
        return empsFinal;
    }

    @Override
    public Collection<Employee> getEmpBySalary(int salary) {
        return em.createNamedQuery("Employee.findBySalary").setParameter("salary", salary).getResultList();
    }

    @Override
    public Collection<Employee> getEmpByDeptId(Integer deptId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Department d=(Department) em.find(Department.class, deptId);
        return d.getEmployeeCollection();
    }

    @Override
    public Collection<Project> getProjectByName(String projectName) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Collection<Project> pCol=em.createNamedQuery("Project.findByProjectName")
                .setParameter("projectName", projectName)
                .getResultList();
        
        return pCol;
    }

    @Override
    public Collection<Project> getProjectWithinCostRange(int startCost, int endCost) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        
        Collection<Project> pCol=em.createNamedQuery("Project.findByCostRange")
                .setParameter("startRange", startCost)
                .setParameter("endRange",endCost)
                .getResultList();
        
        return pCol;
    }
}
