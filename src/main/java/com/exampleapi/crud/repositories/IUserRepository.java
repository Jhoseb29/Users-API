package com.exampleapi.crud.repositories;

import com.exampleapi.crud.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends MongoRepository<UserModel, String> {

}
