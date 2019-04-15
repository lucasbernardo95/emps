package com.emps.controller;

public interface CrudBean {

	public void init();
	
	public void save();

	public void delete();

	public void list();

	public default boolean isValidField(String value) {
		// verifica se o valor passado é nulo ou se contém apenas um 'espaço' digitado
		return (value.isEmpty() || value.trim().isEmpty());
	}

}
