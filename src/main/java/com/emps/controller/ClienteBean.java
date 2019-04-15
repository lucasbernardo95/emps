package com.emps.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import com.emps.model.Cliente;
import com.emps.repository.ClienteRepository;
import com.emps.util.MessageUtil;

@Named
@SessionScope
public class ClienteBean implements CrudBean {


	@Autowired
	private ClienteRepository repository;
	
	private Cliente entity, selected;
	private List<Cliente> list, filtred;
	
	public ClienteBean() {}
	
	public void novo() {
		entity = new Cliente();
	}
	
	@PostConstruct
	@Override
	public void init() {
		System.out.println("criou os objetos");
		entity = new Cliente();
		this.list = new ArrayList<Cliente>();
		list();
	}

	@Override
	public void save() {
		
		if(isValidField(entity.getNome()) && isValidField(entity.getTelefone())) {
			MessageUtil.MensagemErro("Os campos nome e telefone são obrigatórios.");
			return;
		}
		
		repository.save(entity);
		MessageUtil.MensagemSucesso("Salvo com sucesso.");
		entity = new Cliente();
		list();
	}

	@Override
	public void delete() {
		if (entity == null) {
			MessageUtil.MensagemErro("Nenhum cliente foi selecionado.");
			return;
		}
		repository.delete(entity);
		list();
		entity = new Cliente();
	}

	@Override
	public void list() {
		list = repository.findAll();

		if (list == null) {
			MessageUtil.MensagemErro("Nenhum cliente encontrado.");
		}
	}
	
	public void itemSelecionado(SelectEvent event) {
		entity = (Cliente) event.getObject();
	}
	
	public void selecaoRemovida(UnselectEvent event) {
		
	}

	public Cliente getEntity() {
		return entity;
	}

	public void setEntity(Cliente entity) {
		this.entity = entity;
	}

	public List<Cliente> getList() {
		return list;
	}

	public void setList(List<Cliente> list) {
		this.list = list;
	}

	public List<Cliente> getFiltred() {
		return filtred;
	}

	public void setFiltred(List<Cliente> filtred) {
		this.filtred = filtred;
	}

	public Cliente getSelected() {
		return selected;
	}

	public void setSelected(Cliente selected) {
		this.selected = selected;
	}

}
