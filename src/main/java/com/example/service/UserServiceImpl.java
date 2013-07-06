package com.example.service;

import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.model.TimeClock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import java.util.List;

//@Service
public class UserServiceImpl implements UserService {

    private DataSource dataSource;

    public void setDataSource(DataSource source) {
        dataSource = source;
    }
        
    public void addUser(User user) {
         String sql = "INSERT INTO users (email, passhash) VALUES (?,?)";
         Connection conn = null;

         try {
             conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setString(1, user.getEmail());
             ps.setString(2, user.getPassHash());
             ps.executeUpdate();
             ps.close();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         } finally {
             if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
             }
         }
    }

    public List<User> listUsers() {
  //      CriteriaQuery<User> c = em.getCriteriaBuilder().createQuery(User.class);
    //    c.from(User.class);
      //  return em.createQuery(c).getResultList();
return null;
    }

    public void removeUser(Integer id) {
        //User user = em.find(User.class, id);
        //if (null != user) {
        //    em.remove(user);
       // }
    }

    public User getUser(Integer id, long timeMin, long timeMax) {
        //User user = em.find(User.class, id);
	//return user;
        return null;
    }

    public void clockTime(Integer userId, TimeClock clock) {
	
	//em.persist(clock);
    }
    
}
