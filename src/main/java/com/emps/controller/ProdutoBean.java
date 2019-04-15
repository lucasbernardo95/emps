package com.emps.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import com.emps.model.Produto;
import com.emps.repository.ProdutoRepository;
import com.emps.util.MessageUtil;

@Named
@SessionScope
public class ProdutoBean implements CrudBean {

	private Produto entity, selected;
	private List<Produto> list, filtred;

	@Autowired
	private ProdutoRepository repository;

	public ProdutoBean() {
	}

	public void novo() {
		System.out.println("chamou o novo");
		entity = new Produto();
	}

	@PostConstruct
	@Override
	public void init() {
		System.out.println("iniciou");
		entity = new Produto();
		list();
	}

	@Override
	public void save() {
		if (isValidField(entity.getNome()) && entity.getQuantidadeEstoque() <= 0 && entity.getValorCompra() <= 0
				&& entity.getValorVenda() <= 0) {
			MessageUtil.MensagemErro("Os campos nome, estoque, valor de compra e revenda são obrigatórios.");
			return;
		}

		repository.save(entity);
		MessageUtil.MensagemSucesso("Salvo com sucesso.");
		novo();
		list();
	}

	@Override
	public void delete() {
		if (entity == null) {
			MessageUtil.MensagemErro("Nenhum produto foi selecionado.");
			return;
		}
		repository.delete(entity);
		list();
		novo();
	}

	@Override
	public void list() {
		list = repository.findAll();

		if (list == null) {
			MessageUtil.MensagemErro("Nenhum produto encontrado.");
		}
	}
	
	public void itemSelecionado(SelectEvent event) {
		entity = (Produto) event.getObject();
	}
	
	public void selecaoRemovida(UnselectEvent event) {
		
	}

	public Produto getEntity() {
		return entity;
	}

	public void setEntity(Produto entity) {
		this.entity = entity;
	}

	public List<Produto> getList() {
		return list;
	}

	public void setList(List<Produto> list) {
		this.list = list;
	}

	public List<Produto> getFiltred() {
		return filtred;
	}

	public void setFiltred(List<Produto> filtred) {
		this.filtred = filtred;
	}

	public Produto getSelected() {
		return selected;
	}

	public void setSelected(Produto selected) {
		this.selected = selected;
	}

}