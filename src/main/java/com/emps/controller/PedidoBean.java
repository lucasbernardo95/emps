package com.emps.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.RequestScope;

import com.emps.controller.interfaces.CrudBean;
import com.emps.controller.interfaces.MessageContext;
import com.emps.model.Pedido;
import com.emps.repository.PedidoRepository;

@Named
@RequestScope
public class PedidoBean implements Serializable, CrudBean, MessageContext {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PedidoRepository repository;

	private Pedido entity, selected;
	private List<Pedido> list;

	public PedidoBean() {
	}

	@Override
	public void save() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		entity.setHora(dateFormat.format(new Date()));

		entity.setData(new Date());

		repository.save(entity);
		MensagemSucesso("Pedido realizado com sucesso.");
		entity = new Pedido();
	}

	@Override
	public void delete() {
		if (entity == null) {
			MensagemErro("Nenhum pedido foi selecionado.");
			return;
		}
		repository.delete(entity);
		list.remove(entity);
	}

	@Override
	public void list() {
		list = repository.findAll();

		if (list == null) {
			MensagemPerigo("Nenhum pedido encontrado.");
		}
	}
	
	public void itemSelecionado(SelectEvent event) {
		entity = (Pedido) event.getObject();
	}
	
	public void selecaoRemovida(UnselectEvent event) {
		
	}

	@PostConstruct
	@Override
	public void init() {
		entity = new Pedido();
		selected = new Pedido();
		this.list = new ArrayList<Pedido>();
		list();
	}

	public Pedido getEntity() {
		return entity;
	}

	public void setEntity(Pedido entity) {
		this.entity = entity;
	}

	public Pedido getSelected() {
		return selected;
	}

	public void setSelected(Pedido selected) {
		this.selected = selected;
	}

	public List<Pedido> getList() {
		return list;
	}

	public void setList(List<Pedido> list) {
		this.list = list;
	}

}
