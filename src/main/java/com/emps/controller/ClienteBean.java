package com.emps.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import com.emps.controller.interfaces.CrudBean;
import com.emps.controller.interfaces.MessageContext;
import com.emps.model.Cliente;
import com.emps.repository.ClienteRepository;

@Named
@SessionScope
public class ClienteBean implements CrudBean, MessageContext {


	@Autowired
	private ClienteRepository repository;
	
	private Cliente entity, selected;
	private List<Cliente> list, filtred;
	private double valor;
	private double valorDebito;
	
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
		repository.save(entity);
		MensagemSucesso("Salvo com sucesso.");
		entity = new Cliente();
		list();
	}

	@Override
	public void delete() {
		if (entity == null) {
			MensagemErro("Nenhum cliente foi selecionado.");
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
			MensagemErro("Nenhum cliente encontrado.");
		}
	}

	//reduz o saldo devido pelo cliente selecionado na tabela a 0 e salva
//	public void quitarDivida(){
//		entity.setDebito(0.0);
//		save();
//	}

	//
	public void quitarDebito(){
		if(valorDebito > 0 && valorDebito <= entity.getDebito() && entity.getDebito() > 0){
			entity.setDebito( entity.getDebito() - valorDebito);
			save();
			MensagemSucesso("Sucesso!","Débito modificado.");
			return;
		}MensagemPerigo("Informe um valor válido.");
	}
	
	public void itemSelecionado(SelectEvent event) {
		entity = (Cliente) event.getObject();
	}
	
	public void selecaoRemovida(UnselectEvent event) {}

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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(double valorDebito) {
		this.valorDebito = valorDebito;
	}
}
