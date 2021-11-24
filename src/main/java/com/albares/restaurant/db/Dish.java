package com.albares.restaurant.db;

import com.albares.restaurant.utils.Db;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dish {

    private String name;
    private Integer price;
    private Integer type;
    private Integer id;
    
    private static final int TYPE_LUNCH = 1;
    private static final int TYPE_DINNER = 2;
    
    public Dish() {
    }

    public Dish(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    public Integer insert_db(Db myDb) throws SQLException{
        
        PreparedStatement ps = myDb.prepareStatement(
                    "INSERT INTO dishes (name, price, type) VALUES (?, ?, ?) returning id;"
            );
        ps.setString(1, this.getName());
        ps.setInt(2, this.getPrice());
        ps.setInt(3, this.getType());
        ResultSet rs = myDb.executeQuery(ps);
        rs.next();
        this.setId(rs.getInt(1));
        return this.getId();
    }
    
    public static List selectDishes_db(Db myDb, String QR) throws SQLException, Exception{
        
        PreparedStatement ps = myDb.prepareStatement(
                    "SELECT name, price FROM dishes WHERE type = ?;"
            );
        
        /*if(QR.equals("lunch")){
            ps.setInt(1, Dish.TYPE_LUNCH);
        }else if(QR.equals("dinner")){
            ps.setInt(1, Dish.TYPE_DINNER);
        }else{
            throw new Exception();
        }*/
        switch (QR) {
            case "lunch":
                ps.setInt(1, Dish.TYPE_LUNCH);
                break;
            case "dinner":
                ps.setInt(1, Dish.TYPE_DINNER);
                break;
            default:
                throw new Exception();
        }
        
        ResultSet rs = myDb.executeQuery(ps);
        
        List<Dish> dishes = new ArrayList();
        
        while(rs.next()){
            Dish dish = new Dish(rs.getString(1),rs.getInt(2));
            dishes.add(dish);
        }
        return dishes;
        
    }
    
    
}
