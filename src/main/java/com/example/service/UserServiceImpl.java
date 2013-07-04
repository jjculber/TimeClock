package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    EntityManager em;
        
    @Transactional
    public void addUser(User user) {
        em.persist(user);
    }

    @Transactional
    public List<User> listUsers() {
        CriteriaQuery<User> c = em.getCriteriaBuilder().createQuery(User.class);
        c.from(User.class);
        return em.createQuery(c).getResultList();
    }

    @Transactional
    public void removeUser(Integer id) {
        User user = em.find(User.class, id);
        if (null != user) {
            em.remove(user);
        }
    }
    
}
