/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import beans.EmpBeanLocal;
import entity.Department;
import entity.Employee;
import entity.Project;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author kruti
 */
@Path("rest")
@RequestScoped
public class EmpResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmpResource
     */
    public EmpResource() {
    }

    @EJB EmpBeanLocal ebl;

    @Path("getAllEmployees")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Employee> getAllEmployees() {
        return ebl.getAllEmployees();
    }
   
    
    @Path("getEmpByDeptNames")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Employee> getEmpByDeptNames(Collection<String> deptNames) {
        
        return ebl.getEmpByDeptNames(deptNames);
    }
    
    @Path("getAllDepartments")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Department> getAllDepartments() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        return ebl.getAllDepartments();
    }

    @Path("addEmployee/{fname}/{lname}/{age}/{salary}/{deptId}")
    @POST
    public void addEmployee(@PathParam("fname") String fname,@PathParam("lname") String lname,@PathParam("age") int age,@PathParam("salary") int salary,@PathParam("deptId") Integer deptId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        ebl.addEmployee(fname, lname, age, salary, deptId);
    }
    
    @Path("updateEmployee/{empId}/{fname}/{lname}/{age}/{salary}/{deptId}")
    @PUT
    public void updateEmployee(@PathParam("empId") Integer empId,@PathParam("fname") String fname,@PathParam("lname") String lname,@PathParam("age") int age,@PathParam("salary") int salary,@PathParam("deptId") Integer deptId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        ebl.updateEmployee(empId, fname, lname, age, salary, deptId);
    }

    @Path("deleteEmployee/{empId}")
    @DELETE
    public void deleteEmployee(@PathParam("empId") Integer empId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        ebl.deleteEmployee(empId);
    }

}
