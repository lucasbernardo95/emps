package com.emps.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "Produtos")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduto")
    private long idProduto;

    @NotEmpty(message = "O nome não pode ser nulo.")
    @Size(min = 3, max = 30, message = "O nome deve conter entre 3 e 30 caracteres.") 
    private String nome;
    
    @NotEmpty(message = "Informe a descrição do produto")
    private String descricao;
    
    @NotNull(message = "O campo valor de revenda é obrigatório")
    private double valorVenda;
    
    @NotNull(message = "O campo valor da compra é obrigatório")
    @Range(min=1, message="O valor da compra não pode ser menor que 0")
    private double valorCompra;
    
    @NotNull(message = "Informe a quantidade em estoque do produto")
    private int quantidadeEstoque;
    
    //está variávei só é usada no momento em que o produto está sendo adicionado ou removido do carrinho, mas não é salva na tabela produto
    private int quantidadeVenda;

    public Produto() {
    }

    public Produto(String nome, String descricao, double valorVenda, double valorCompra,
            int quantidadeEstoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorVenda = valorVenda;
        this.valorCompra = valorCompra;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idProduto ^ (idProduto >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + quantidadeEstoque;
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
		Produto other = (Produto) obj;
		if (idProduto != other.idProduto)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (quantidadeEstoque != other.quantidadeEstoque)
			return false;
		return true;
	}
	
	public int getQuantidadeVenda() {
		return quantidadeVenda;
	}

	public void setQuantidadeVenda(int quantidadeVenda) {
		this.quantidadeVenda = quantidadeVenda;
	}
	
	public void incrementaQuantidadeVenda(int quantidadeVenda) {
		
		this.quantidadeVenda += quantidadeVenda;
		if(this.quantidadeVenda > this.quantidadeEstoque)
			this.quantidadeVenda = this.quantidadeEstoque;
	}
	
	public void decrementaQuantidadeVenda(int quantidadeVenda) {
		this.quantidadeVenda -= quantidadeVenda;
		if(this.quantidadeVenda < 0)
			this.quantidadeVenda = 0;

	}
}