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

import com.emps.model.Pedido;
import com.emps.repository.PedidoRepository;
import com.emps.util.MessageUtil;

@Named
@RequestScope
public class PedidoBean implements Serializable, CrudBean {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PedidoRepository repository;

	private Pedido entity, selected;
	private List<Pedido> list;

	public PedidoBean() {
	}

	@Override
	public void save() {
		if (isValidField(entity.getDescricao()) && isValidField(entity.getTelefone())
				&& isValidField(entity.getNomeUsuario())) {
			MessageUtil.MensagemErro("Por favor, preencha todos os campos corretamente.");
			return;
		}

		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		entity.setHora(dateFormat.format(new Date()));

		entity.setData(new Date());

		repository.save(entity);
		MessageUtil.MensagemSucesso("Pedido realizado com sucesso.");
		entity = new Pedido();
	}

	@Override
	public void delete() {
		if (entity == null) {
			MessageUtil.MensagemErro("Nenhum pedido foi selecionado.");
			return;
		}
		repository.delete(entity);
		list.remove(entity);
	}

	@Override
	public void list() {
		list = repository.findAll();

		if (list == null) {
			MessageUtil.MensagemPerigo("Nenhum pedido encontrado.");
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
