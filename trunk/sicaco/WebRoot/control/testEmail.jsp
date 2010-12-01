<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html> 
	<head>
		<title>JSP for TestEmailForm form</title>
	</head>
	<body>
		<html:form action="${_accion}" method="post">
			puerto : <html:text property="puerto"/><br/>
			destino : <html:text property="destino"/><br/>
			remitente : <html:text property="remitente"/><br/>
			server : <html:text property="server"/><br/>
			mensaje : <html:text property="mensaje"/><br/>
			subject : <html:text property="subject"/><br/>
			<html:submit property="accion">
				<bean:message key="cmd.test.envia"/>
			</html:submit>
		</html:form>
	</body>
</html>

