package com.emps.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.emps.model.User;

//@Component
//@Order(1)
//@WebFilter(urlPatterns = "/emps/*") // vai filtrar tudo o que estiver dentro da pasta expecificada
public class Filtro /*implements Filter*/ {

	//@Override
	//public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	//		throws IOException, ServletException {
		// TODO Auto-generated method stub
		//User user = null;

		//recupera a sessão, caso exista
		//HttpSession sesison = ((HttpServletRequest) request).getSession(false);

		// se houver uma sessão válida, reucpera da sessão o objeto usuário-logado
		// que, pode ter sido setado no loginbean, se ouver uma sessão válida.
		//if (sesison != null)
		//	user = (User) sesison.getAttribute("usuario-logado");

		// se o usuário for nulo, o mesmo será redirécionado para login
		//if (user == null) {
		//	String contextPath = ((HttpServletRequest) request).getContextPath();
		//	((HttpServletResponse) response).sendRedirect(contextPath + "/login.xhtml");
		//}
		//chain.doFilter(request, response);
	//}

}
