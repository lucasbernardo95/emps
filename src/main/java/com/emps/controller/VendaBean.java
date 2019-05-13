package com.emps.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.ApplicationScope;

import com.emps.controller.interfaces.CrudBean;
import com.emps.controller.interfaces.MessageContext;
import com.emps.model.Cliente;
import com.emps.model.Produto;
import com.emps.model.Venda;
import com.emps.repository.ClienteRepository;
import com.emps.repository.ProdutoRepository;
import com.emps.repository.VendaRepository;

@Named
@ApplicationScope
public class VendaBean implements CrudBean, MessageContext {

	@Autowired
	private VendaRepository repository;
	private Venda entity, selected, vendaNegociada; // vendaNegociada é a venda que está sendo efetivada
	private List<Venda> list, filtred;
	
	
	private List<SelectItem> formasPagamento;  
	private String formaSelecionada;

	@Autowired
	private ClienteRepository cr;
	private List<Cliente> listClientes, clientesFiltrados;
	private Cliente clienteSelecionado;

	@Autowired
	private ProdutoRepository pr;
	private List<Produto> listProdutos, produtosFiltrados, carrinho;
	private Produto produtoSelecionado;
	private int quantidade;
	private boolean menuVenda;

	public VendaBean() {
	}

	public void novo() {
		entity = new Venda();
	}

	public void listProductosAndClients() {
		listProdutos = pr.findAll();
		listClientes = cr.findAll();
	}

	@PostConstruct
	@Override
	public void init() {
		novo();
		list();
		listProductosAndClients();
		carrinho = new ArrayList<Produto>();
		vendaNegociada = new Venda();
		
		carregaFormas();
		menuVenda = false;//indica que o menu do carrinho (venda) ficará oculto
		formaSelecionada = null;
	}
	
	private void carregaFormas() {
		formasPagamento = new  ArrayList<SelectItem>();
		
		SelectItemGroup avista = new SelectItemGroup("A vista");
        avista.setSelectItems( new SelectItem[]{new SelectItem("Espécie"), new SelectItem("Débito")});
		
        SelectItemGroup parcelado = new SelectItemGroup("Parcelado");
        parcelado.setSelectItems( 
        		new SelectItem[]{
        				new SelectItem("2"),
        				new SelectItem("3"), new SelectItem("4"),
        				new SelectItem("5"), new SelectItem("6"),
        				new SelectItem("7"), new SelectItem("8"),
        				new SelectItem("9"), new SelectItem("10"),
        				new SelectItem("11"), new SelectItem("12")
        		});
        formasPagamento.add(avista);
        formasPagamento.add(parcelado);
	}

	//reseta todas as listas e objeto venda para evitar bugs
	public void cancelarCompra() {
		listProductosAndClients();
		carrinho = new ArrayList<Produto>();
		vendaNegociada = new Venda();
		clienteSelecionado = null;
		//oculta o menu do carrinho
		menuVenda = false;
		formaSelecionada = null;
	}
	
	/**
	 * atualiza a quantidade em estoque do produto, seta a forma de pagamento da venda e finaliza a compra
	 * chamando o método salvar.
	 */
	public void validaCompra() {
		String detalhe = "";
		for (Produto produto : carrinho) {
			//calcula o total (é calculado sempre que adicionar um novo produto ao carrinho)
			//vendaNegociada.setTotal( vendaNegociada.getTotal() + (produto.getValorVenda() * produto.getQuantidadeVenda() ) );
			
			detalhe += produto.getNome() + "\nQtd: " + produto.getQuantidadeVenda() + "x\nValor por unidade: " +produto.getValorVenda()+ "R$\nValor final: " + (produto.getValorVenda() * produto.getQuantidadeVenda())+"R$.\n";
			//zera o valor da quantidade vendida do produto para evitar bugs em vendas futuras
			produto.setQuantidadeVenda(0);
			//atualiza os produtos no banco
			pr.save(produto);
		}
		
		
		//verifica o tipo de pagamento e adiciona no detalhe
		if(formaSelecionada.equals("Débito") || formaSelecionada.equals("Espécie"))
			detalhe += "\nForma de pagamento: " + formaSelecionada;
		else {
			detalhe += "\nForma de pagamento: " + verificaParcelasPagamento();
			clienteSelecionado.setDebito(vendaNegociada.getTotal());
		}
		
		//informa os detalhes da compra
		vendaNegociada.setDetalheVenda(detalhe);
		//adiciona os itens do carrinho á venda
		vendaNegociada.setProdutos(carrinho);
		//Seta a data da compra
		vendaNegociada.setDataVenda(new Date());
		
		
		//se o tipo de pagamento for parcelado, adiciona o total da venda como débito do cliente
		clienteSelecionado.setDebito( clienteSelecionado.getDebito() + vendaNegociada.getTotal());

		//adiciona o comprador ao objeto venda
		List<Cliente> compradores = new ArrayList<Cliente>();
		compradores.add(clienteSelecionado);
		vendaNegociada.setCompradores(compradores);
		
		//finaliza a compra
		save();
		init();
	}
	
	private String verificaParcelasPagamento() {
		String parcelas = "";
		switch(formaSelecionada) {
		case "2":
			parcelas = "2x " + vendaNegociada.getTotal() / 2 + " R$";
			break;
		case "3":
			parcelas = "3x " + vendaNegociada.getTotal() / 3 + " R$";
			break;
		case "4":
			parcelas = "4x " + vendaNegociada.getTotal() / 4 + " R$";
			break;
		case "5":
			parcelas = "5x " + vendaNegociada.getTotal() / 5 + " R$";
			break;
		case "6":
			parcelas = "6x " + vendaNegociada.getTotal() / 6 + " R$";
			break;
		case "7":
			parcelas = "7x " + vendaNegociada.getTotal() / 7 + " R$";
			break;
		case "8":
			parcelas = "8x " + vendaNegociada.getTotal() / 8 + " R$";
			break;
		case "9":
			parcelas = "9x " + vendaNegociada.getTotal() / 9 + " R$";
			break;
		case "10":
			parcelas = "10x " + vendaNegociada.getTotal() / 10 + " R$";
			break;
		case "11":
			parcelas = "11x " + vendaNegociada.getTotal() / 11 + " R$";
			break;
		case "12":
			parcelas = "12x " + vendaNegociada.getTotal() / 12 + " R$";
			break;
		}
		
		return parcelas;
	}
	
	@Override
	public void save() {
		repository.save(vendaNegociada);
	}

	@Override
	public void delete() {
		repository.delete(entity);
	}

	@Override
	public void list() {
		list = repository.findAll();
	}

	public void addProduto(Produto produto) {

		if (quantidade <= produto.getQuantidadeEstoque() && quantidade > 0) {
			if (!carrinho.contains(produto)) {
				//decrementa a quantidade do estoque
				produto.setQuantidadeEstoque( produto.getQuantidadeEstoque() - quantidade );
				produto.setQuantidadeVenda(quantidade);
				carrinho.add(produto);
			} else { // Se o produto já foi adicionado ao carrinho anteriormente, apenas atualiza a
						// quantidade
				// passa a quantidade a ser incrementada
				int index = carrinho.indexOf(produto);
				Produto validador = carrinho.get(index);
				//se o valor a ser incrementado for maior que a quantidade em estoque, informa o erro
				if ( (validador.getQuantidadeVenda() + quantidade) > produto.getQuantidadeEstoque()) {
					MensagemPerigo("Estoque baixo! Quantidade disponível: " + produto.getQuantidadeEstoque());
					return;
				} else {
					produto.setQuantidadeEstoque( produto.getQuantidadeEstoque() - quantidade );
					carrinho.get(index).incrementaQuantidadeVenda(quantidade);
				}
			}
			quantidade = 1;
			//se ocorreu tudo corretamente, informa uma mensagem na tela do usuário
			MensagemSucesso(produto.getNome() + " foi inserido no carrinho!");

			//calcula o valor total atual da venda
			vendaNegociada.setTotal( vendaNegociada.getTotal() + (produto.getValorVenda() * produto.getQuantidadeVenda() ) );
			return;
		} else
			MensagemErro("Estoque insuficiente.");
		quantidade = 1;
	}

	public void removeProduto(Produto produto) {
		System.out.println("total: " + vendaNegociada.getTotal());
		if ( quantidade > 0 && quantidade <= produto.getQuantidadeVenda()) {
			if( quantidade == produto.getQuantidadeVenda()) {
				//repõe a quantidade removida do estoque
				produto.setQuantidadeEstoque( produto.getQuantidadeEstoque() + quantidade );
				carrinho.remove(produto);
			} else if( quantidade < produto.getQuantidadeVenda()) {
				carrinho.get(carrinho.indexOf(produto)).decrementaQuantidadeVenda(quantidade);
				produto.setQuantidadeEstoque( produto.getQuantidadeEstoque() + quantidade );
				if(produto.getQuantidadeVenda() <= 0) {
					carrinho.remove(produto);
				}
			}
			//atualiza o valor total atual da venda subtraindo o valor do produto * quantidade removida do carrinho
			vendaNegociada.setTotal( vendaNegociada.getTotal() - (produto.getValorVenda() * quantidade ) );
			return;
		}
		//caso seja informada uma quantidade inválida
		MensagemPerigo("Quantidade inválida!", "Verifique a quantidade informada e tente novamente.");
		quantidade = 1;
		System.out.println("total: " + vendaNegociada.getTotal() + " subtraído: " + (produto.getValorVenda() * quantidade));
	}

	public void addComprador(Cliente comprador) {
		MensagemSucesso(clienteSelecionado.getNome() + " foi adicionado como comprador!");
	}

	public void removeComprador(Cliente comprador) {
		MensagemSucesso(comprador.getNome() + " foi removido da compra.");
		clienteSelecionado = null;
	}

	public Venda getEntity() {
		return entity;
	}

	public void setEntity(Venda entity) {
		this.entity = entity;
	}

	public Venda getSelected() {
		return selected;
	}

	public void setSelected(Venda selected) {
		this.selected = selected;
	}

	public List<Venda> getList() {
		return list;
	}

	public void setList(List<Venda> list) {
		this.list = list;
	}

	public List<Venda> getFiltred() {
		return filtred;
	}

	public void setFiltred(List<Venda> filtred) {
		this.filtred = filtred;
	}

	public List<Produto> getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(List<Produto> carrinho) {
		this.carrinho = carrinho;
	}

	public List<Cliente> getListClientes() {
		return listClientes;
	}

	public void setListClientes(List<Cliente> listClientes) {
		this.listClientes = listClientes;
	}

	public List<Produto> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<Produto> listProdutos) {
		this.listProdutos = listProdutos;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(List<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}

	public Venda getVendaNegociada() {
		return vendaNegociada;
	}

	public void setVendaNegociada(Venda vendaNegociada) {
		this.vendaNegociada = vendaNegociada;
	}

	public List<SelectItem> getFormasPagamento() {
		return formasPagamento;
	}

	public void setFormasPagamento(List<SelectItem> formasPagamento) {
		this.formasPagamento = formasPagamento;
	}

	public String getFormaSelecionada() {
		return formaSelecionada;
	}

	public void setFormaSelecionada(String formaSelecionada) {
		this.formaSelecionada = formaSelecionada;
	}

	public boolean isMenuVenda() {
		return menuVenda;
	}

	public void setMenuVenda(boolean menuVenda) {
		this.menuVenda = menuVenda;
	}
}
