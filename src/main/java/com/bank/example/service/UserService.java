package com.bank.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.example.entity.UserEntity;
import com.bank.example.exception.RecordNotFoundException;
import com.bank.example.repository.UserRepository;

@Service
public class UserService {
     
    @Autowired
    UserRepository repository;
     
    public List<UserEntity> getAllUsers()
    {
        List<UserEntity> UserList = repository.findAll();
         
        if(UserList.size() > 0) {
            return UserList;
        } else {
            return new ArrayList<UserEntity>();
        }
    }
     
    public UserEntity getUserById(Long id) throws RecordNotFoundException
    {
        Optional<UserEntity> User = repository.findById(id);
         
        if(User.isPresent()) {
            return User.get();
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }
     
    public UserEntity createOrUpdateUser(UserEntity entity) throws RecordNotFoundException
    {
        Optional<UserEntity> user = repository.findById(entity.getId());
         
        if(user.isPresent())
        {
        	UserEntity newEntity = user.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    }
     
    public void deleteUserById(Long id) throws RecordNotFoundException
    {
        Optional<UserEntity> User = repository.findById(id);
         
        if(User.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No User record exist for given id");
        }
    }
}