package com.emps.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Clientes")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private long idCliente;

    @NotEmpty(message = "O nome do cliente não pode ser nulo.")
    @Size(min = 3, max = 25, message = "O nome deve conter entre 3 e 25 caracteres.") 
    private String nome;

    @NotEmpty(message = "O CPF não pode ser nulo.")
    @Size(min = 14, max = 14, message = "O CPF deve conter 11 caracteres.") 
    @Column(unique = true)
    private String cpf;
    
    @Column
    private double debito;
    
    @Column
    private String observacao;
    
    @Column
    private String telefone;
    
    @Column
    private String email;
    
    @Column
    private String cidade;
    
    @Column
    private String endereco;
    
    @Column
    private String bairro;
    
    @Column
    private Integer numeroCasa;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, double debito, String observacao, String telefone, String email,
            String endereco, String cidade, String bairro, Integer numeroCasa) {
        this.nome = nome;
        this.cpf = cpf;
        this.debito = debito;
        this.observacao = observacao;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numeroCasa = numeroCasa;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(Integer numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idCliente ^ (idCliente >>> 32));
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		long temp;
		temp = Double.doubleToLongBits(debito);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Cliente other = (Cliente) obj;
		if (idCliente != other.idCliente)
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (Double.doubleToLongBits(debito) != Double.doubleToLongBits(other.debito))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [codigo=" + idCliente + ", nome=" + nome + ", cpf=" + cpf + ", debito=" + debito + ", observacao="
				+ observacao + ", telefone=" + telefone + ", email=" + email + ", cidade=" + cidade + ", endereco="
				+ endereco + ", bairro=" + bairro + ", numeroCasa=" + numeroCasa + "]";
	}

}
