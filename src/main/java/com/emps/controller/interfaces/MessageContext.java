package com.emps.controller.interfaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public interface MessageContext {

	public default void MensagemErro(String mensagem) {
	    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null);
	    addMessage(fm);
	}

	public default void MensagemErro(String titulo, String mensagem) {
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, titulo);
		addMessage(fm);
	}

	public default void MensagemSucesso(String mensagem) {
	    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null);
	    addMessage(fm);
	}

	public default void MensagemSucesso(String titulo, String mensagem) {
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, titulo);
		addMessage(fm);
	}

	public default void MensagemPerigo(String mensagem) {
	    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null);
	    addMessage(fm);
	}

	public default void MensagemPerigo(String titulo, String mensagem) {
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, titulo);
		addMessage(fm);
	}
	  
	public default void addMessage(FacesMessage fm) {
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
}