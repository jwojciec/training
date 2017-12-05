package com.example.training.repository;

import com.example.training.model.User;
import org.springframework.data.repository.CrudRepository;

public interface IH2Repository extends CrudRepository<User, String> {

}
