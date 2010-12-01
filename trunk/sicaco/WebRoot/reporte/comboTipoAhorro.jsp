<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>

<%@page import="com.cetia.sicaco.hibernate.CtaTahTipoAhorroDAO"%>
<%@page import="com.cetia.sicaco.hibernate.CtaTahTipoAhorro"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.mad.utilidades.filtros.FiltroOpenSession"%>
<%
	try {
		
		String idLah = (String)request.getParameter("id");
		CtaTahTipoAhorroDAO tipoAhorroDAO  = new CtaTahTipoAhorroDAO((Session) request.getAttribute(FiltroOpenSession.KEY_SESSION));
		
		List <CtaTahTipoAhorro> tiposAhorros = tipoAhorroDAO.findByLinea(new Integer(idLah));  
		
		int i=0;	 
		out.print("<option value='"+"-1"+"'>"+"- - Todos los tipos - - "+"</option>");
		while(i< tiposAhorros.size()){
			CtaTahTipoAhorro ctta = (CtaTahTipoAhorro)tiposAhorros.get(i);
			out.print("<option value='"+ctta.getTahId()+"'>"+ctta.getTahNombre()+"</option>");
			i++;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
