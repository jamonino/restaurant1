
package com.albares.restaurant.api;

import com.albares.restaurant.db.Dish;
import com.albares.restaurant.utils.Db;
import com.albares.restaurant.utils.Response;
import com.albares.restaurant.utils.ResponseCodes;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("")
public class getDishes {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{QR}")
    public Response getMenu(@PathParam("QR") String QR){  
        try{
            Db myDb = new Db();

            myDb.connect();
            List<Dish> dishesResponse = Dish.selectDishes_db(myDb, QR);
            myDb.disconnect();
            
            Response r = new Response();
            r.setDishes(dishesResponse);
            r.setResponseCode(ResponseCodes.OK);
            return r;   
        }catch(Exception e){
            Response r = new Response();
            r.setResponseCode(ResponseCodes.ERROR);
            return r;   
        }
    }    
}

