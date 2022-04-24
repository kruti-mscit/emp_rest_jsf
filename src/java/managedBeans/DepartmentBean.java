/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.EmpBeanLocal;
import entity.Department;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 *
 * @author kruti
 */
@Named(value = "departmentBean")
@ApplicationScoped
public class DepartmentBean {

    @EJB
    private EmpBeanLocal empBean;
    
    Department dept=new Department();
    private Integer deptId;
    private String deptName;

    /**
     * Creates a new instance of DepartmentBean
     */
    public DepartmentBean() {
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    
    //Validation functions
    
    public void validateAge(FacesContext context,UIComponent comp,Object value){
        
        String empAge=(String) value;
        
        if(empAge.length()>2 || empAge.length()<=1){
            
            ((UIInput)comp).setValid(false);
            
            FacesMessage message=new FacesMessage("Please enter valid age");
            context.addMessage(comp.getClientId(context), message);
        }
    }
    
    
    public List<Department> findAll(){
      //  return (List<Departmenttb>) dataBean.getAllDepartments();
      return (List<Department>) this.empBean.getAllDepartments();
    }
    public String insertDepartment()
    {
        empBean.addDepartment(deptName);
        return "Display";
        //Move to the "Display" Page!!
    }
    
    public void deleteDepartment(int deptid){
       empBean.deleteDepartment(deptid);
    }

    public String edit(Department d)
    {
        this.dept=d;
        this.deptId=d.getDeptId();
        this.deptName=d.getDeptName();
        return "Update";    
    }
    
    public String edit()
    {
        empBean.updateDepartment(deptId,deptName);
        return "Display";
        
    }
    
}
