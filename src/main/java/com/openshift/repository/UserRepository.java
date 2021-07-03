package com.openshift.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.openshift.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
 
}
