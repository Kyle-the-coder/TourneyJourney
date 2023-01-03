package com.nightcrew.tourneyjourney.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nightcrew.tourneyjourney.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
