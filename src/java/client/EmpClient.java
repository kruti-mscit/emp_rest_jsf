/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:EmpResource [rest]<br>
 * USAGE:
 * <pre>
 *        EmpClient client = new EmpClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author kruti
 */
public class EmpClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/EmpFinalMix/webresources";

    public EmpClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("rest");
    }

//    public <T> T getAllEmployees(Class<T> responseType) throws ClientErrorException {
//        WebTarget resource = webTarget;
//        resource = resource.path("getAllEmployees");
//        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
//    }
//    
//    public <T> T getEmpByDeptNames(Object requestEntity,Class<T> responseType) throws ClientErrorException {
//        WebTarget resource = webTarget;
//        resource = resource.path("getEmpByDeptNames");
//        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON),responseType);
//    }
    
    
    public void updateEmployee(String empId, String fname, String lname, String age, String salary, String deptId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateEmployee/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{empId, fname, lname, age, salary, deptId})).request().put(Entity.json(""));
    }
    
    public void deleteEmployee(String empId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteEmployee/{0}", new Object[]{empId})).request().delete();
    }

    public <T> T getEmpByDeptNames(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("getEmpByDeptNames").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T getAllDepartments(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllDepartments");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllEmployees(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllEmployees");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addEmployee(String fname, String lname, String age, String salary, String deptId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addEmployee/{0}/{1}/{2}/{3}/{4}", new Object[]{fname, lname, age, salary, deptId})).request().post(null);
        
    }

    public void close() {
        client.close();
    }
    
}
