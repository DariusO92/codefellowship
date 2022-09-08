package com.codefellowship.lab16.repositories;

import com.codefellowship.lab16.models.appUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepo extends JpaRepository<appUser,Long> {
    appUser findByUsername(String username);

}
