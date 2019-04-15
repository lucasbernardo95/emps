package com.emps.controller;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.emps.model.User;
import com.emps.repository.UserRepository;
import com.emps.util.SessionUtil;

@Named
public class UserBean implements Serializable {
	
	
	@Autowired
	private UserRepository repository;
	
	
	
}