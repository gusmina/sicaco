<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>reporteLiquidacion.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <html:form action="${_accion}"  method="post" styleId="formId2">
		<table align="center"
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;">
		<tr>
			<td colspan="2"><h3>Datos del reporte</h3></td>
		</tr>
		<tr>
		<td>
			<label>Solicitado por:</label>	
		</td>
		<td>
			<input type="text"  name="solicita"/>
		</td>
		</tr>
		<tr><td colspan="2">&nbsp;</td></tr>
		<tr>
		<td align="right">
			<label>Autorizado por:</label>
			
		</td>
		<td align="left">
			<input type="text"  name="autoriza"/>
		</td>
		</tr>
		<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td>
						<label>
							Fecha:
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fecha"
							onkeyup="JavaScript:mask(this);" id="fechaZ" />
						<input id="calendarZ" type="button" value="cal" onclick="calendario();"/>
						<script type="text/javascript">
				            
				         </script>
					</td>
				</tr>
		<tr><td colspan="2">&nbsp;</td></tr>
		<tr>
		<td  colspan="2" align="center">
				<html:submit property="accion" style="width: 180px">
					<bean:message key="cmd.fondosOficina.imprimirReporte" />
				</html:submit>
			
		</td>
			</tr>
		</table>
	</html:form>
  </body>
</html:html>
