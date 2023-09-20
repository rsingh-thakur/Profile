package com.auth.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.demo.entity.Profile;

public interface ProfileRepo extends JpaRepository<Profile, Integer> {

	Profile findByEmail(String email);

}
