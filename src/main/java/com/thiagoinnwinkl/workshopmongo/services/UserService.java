package com.thiagoinnwinkl.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagoinnwinkl.workshopmongo.domain.User;
import com.thiagoinnwinkl.workshopmongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
		private UserRepository repo;

		public List<User> findAll(){
			return repo.findAll();
	}
}
