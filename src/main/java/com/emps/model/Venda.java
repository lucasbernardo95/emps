package com.emps.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Vendas")
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVenda")
	private long codigo;

	private double total;

	@Column
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Brazil/East")
	private Date dataVenda;

	@Column
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Brazil/East")
	private Date dataVencimento;

	@ManyToMany
	@JoinTable(
			name = "vendas_clientes", joinColumns = {
			@JoinColumn(name = "FK_idVenda", referencedColumnName = "idVenda") }, 
			inverseJoinColumns = { @JoinColumn(name = "FK_idCliente ") })
	private List<Cliente> compradores = new ArrayList<Cliente>();

	private String observacao;

	private String detalheVenda;

	@ManyToMany
	@JoinTable(name = "vendas_produtos", joinColumns = {
			@JoinColumn(name = "FK_idVenda", referencedColumnName = "idVenda") }, inverseJoinColumns = {
					@JoinColumn(name = "FK_idProduto") })
	private List<Produto> produtos = new ArrayList<Produto>();

	public Venda() {
	}

	public Venda(long codigo, double total, Date dataVenda, Date dataVencimento, List<Cliente> compradores,
			List<Produto> produtos, String observacao, String detalheVenda) {
		super();
		this.codigo = codigo;
		this.total = total;
		this.dataVenda = dataVenda;
		this.dataVencimento = dataVencimento;
		this.compradores = compradores;
		this.produtos = produtos;
		this.observacao = observacao;
		this.detalheVenda = detalheVenda;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public List<Cliente> getCompradores() {
		return compradores;
	}

	public void setCompradores(List<Cliente> compradores) {
		this.compradores = compradores;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDetalheVenda() {
		return detalheVenda;
	}

	public void setDetalheVenda(String detalheVenda) {
		this.detalheVenda = detalheVenda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		result = prime * result + ((compradores == null) ? 0 : compradores.hashCode());
		result = prime * result + ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result + ((dataVenda == null) ? 0 : dataVenda.hashCode());
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
		long temp;
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (codigo != other.codigo)
			return false;
		if (compradores == null) {
			if (other.compradores != null)
				return false;
		} else if (!compradores.equals(other.compradores))
			return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		if (dataVenda == null) {
			if (other.dataVenda != null)
				return false;
		} else if (!dataVenda.equals(other.dataVenda))
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total))
			return false;
		return true;
	}

}