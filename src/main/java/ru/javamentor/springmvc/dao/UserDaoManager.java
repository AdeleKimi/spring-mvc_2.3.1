package ru.javamentor.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.javamentor.springmvc.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class UserDaoManager {
    @PersistenceContext
    private EntityManager entityManager;


    public List<User> getAll(){
        return entityManager.createQuery("select u from User u",User.class).getResultList();
    }

    public User getUserById(long id) {
        return null;
    }

    public void saveUser(User user) {

    }

    public void updateUser(long id, User updateUser){

    }

    public void deleteUser(long id) {

    }

}
