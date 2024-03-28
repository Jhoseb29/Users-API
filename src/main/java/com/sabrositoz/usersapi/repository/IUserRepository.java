package com.sabrositoz.usersapi.repository;

import com.sabrositoz.usersapi.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * The interface IUserRepository defines the methods that will be used to
 * interact with the database.
 */
@Repository
public interface IUserRepository extends JpaRepository<User, String> {

}
