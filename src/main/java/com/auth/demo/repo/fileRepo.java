package com.auth.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.demo.entity.UserFile;

public interface fileRepo extends JpaRepository<UserFile, Long>{

}
