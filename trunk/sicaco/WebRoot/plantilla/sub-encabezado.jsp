
<%@page import="com.cetia.sicaco.struts.Constantes"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<logic:present name="<%=Constantes.USUARIO_KEY%>" scope="session">
	<div style="color: white;">
	<bean:message key="msg.dia"/>
	<bean:write formatKey="frt.fecha" name="<%=Constantes.FECHA_KEY%>" />	
	<bean:message key="msg.usuario"/>: <bean:write  name="<%=Constantes.USUARIO_KEY%>" property="nombreUsuario"/> |
	<html:link action="/cerrarSesion" module="/seguridad">
		<span style="color:#B7C3C9;"><bean:message key="msg.cerrar.session" /></span>
	</html:link>
	</div>
</logic:present>
