package com.emps.controller;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.emps.controller.interfaces.MessageContext;
import com.emps.repository.UserRepository;

@Named
public class UserBean implements Serializable, MessageContext {
	
	
	@Autowired
	private UserRepository repository;
	
	
	
}