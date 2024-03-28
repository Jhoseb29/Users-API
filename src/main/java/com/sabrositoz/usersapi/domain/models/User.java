package com.sabrositoz.usersapi.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * User entity class.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

  /**
   * The unique ID of the user.
   */
  @Id
  private String id;

  /**
   * The name of the user.
   */
  @Column
  private String name;

  /**
   * The login of the user.
   */
  @Column
  private String login;

  /**
   * The password of the user.
   */
  @Column
  private String password;

  /**
   * Default constructor for User.
   */
  public User() {
    id = UUID.randomUUID().toString();
  }
}
