
package com.albares.restaurant.api;

import com.albares.restaurant.db.Dish;
import com.albares.restaurant.utils.Db;
import com.albares.restaurant.utils.Response;
import com.albares.restaurant.utils.ResponseCodes;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/admin")
public class addDish {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addDish")
    public Response addDish(Dish dish){  
        try{
            Db myDb = new Db();

            myDb.connect();
            dish.insert_db(myDb);
            myDb.disconnect();
            
            Response r = new Response();
            r.setResponseCode(ResponseCodes.OK);
            return r;   
        }catch(Exception e){
            Response r = new Response();
            r.setResponseCode(ResponseCodes.ERROR);
            return r;   
        }
    }    
}

