package com.googlecode.test.toolkit.services.remotepool;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;



/**
 *
 * @author jiafu
 *
 */
public class CommonRemotePoolClient {

    private static final String DEFAULT_BORROWER_NAME = "anonymous";

    /**
     * http://10.224.38.166:8080/common-remote-pool/
     */
    private String url;

    /**
     * @param url such as http://10.224.38.166/common-remote-pool/
     */
    public CommonRemotePoolClient(String url) {
        super();
        if(!url.endsWith("/"))
            url+="/";
        this.url = url;

    }


    /**
     * @param classType
     * @return return null if now object can be borrowed.
     */
    public <T> T borrowObject(Class<T> classType) {
    	return   borrowObject(classType, DEFAULT_BORROWER_NAME); 

    }
     
    /**
     * @param classType
     * @return return null if now object can be borrowed.
     */
    public <T> T borrowObject(Class<T> classType, String borrowerName) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        try {
            ResteasyWebTarget target = client.target(url + "service/object/borrow");
            Response response = target.request().header("borrower", borrowerName).get();
            if (response.getStatus() == 404)
                return null;

            T readEntity = response.readEntity(classType);
            return readEntity;
         } finally {
            client.close();
        }

    }
    
    
    public <T> T borrowObject(Class<T> classType, List<T> scopeList) {
    	 return borrowObject(classType, scopeList, DEFAULT_BORROWER_NAME) ;
    }

    /**
     * @param classType
     * @param scopeList  only return the object in scope.
     * @return return null if now object can be borrowed.
     */
    public <T> T borrowObject(Class<T> classType, List<T> scopeList, String borrowerName) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        try {
            ResteasyWebTarget target = client.target(url + "service/object/borrow");
            String string = JSONArray.fromObject(scopeList).toString();
            Response response =  target.request().header("borrower", borrowerName).post(Entity.form(new Form("jsonContent", string)));

            if (response.getStatus() == 404)
                return null;

            T readEntity = response.readEntity(classType);
            return readEntity;
         } finally {
            client.close();
        }

    }
    
    public <T> boolean returnObject(Object object) {
    	return returnObject( object, DEFAULT_BORROWER_NAME);
    }

    public <T> boolean returnObject(Object object,String borrowerName) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        try {
            ResteasyWebTarget target = client.target(url + "service/object/return");
            Response response = target.request().header("borrower", borrowerName).post(Entity.json(object));

            return response.getStatus() <= 204 && response.getStatus()>=200;
         } finally {
            client.close();
        }
    }

    /**
     * list all objects be added into pools
     * @param classType
     * @return
     */
    public <T> List<T> listAdded(Class<T> classType) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        try {
            ResteasyWebTarget target = client.target(url + "service/object/listAdd");
            Response response =  target.request().get();

            if (response.getStatus() == 404)
                return null;

           List<T> added=new ArrayList<T>();
          	String readEntity = response.readEntity(String.class);
          	if(!readEntity.startsWith("["))
          		return added;

            JSONArray array=JSONArray.fromObject(readEntity);
            @SuppressWarnings("unchecked")
			T[] products=(T[]) JSONArray.toArray(array,classType);
            for(T t:products){
                   added.add(t);
            }

            return added;
         } finally {
            client.close();
        }

    }

 

    public <T> boolean addObject(Object... objects) {
         ResteasyClient client = new ResteasyClientBuilder().build();
        try {
        	for (Object object : objects) {
                ResteasyWebTarget target = client.target(url + "service/object/add");
                Response response =  target.request().post(Entity.form(new Form("jsonContent", JSONObject.fromObject(object).toString())));
                 if(response.getStatus() >204)
                	return false;
			}
              return true;
         } finally {
            client.close();
        }

    }

}
