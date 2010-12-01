<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post" styleId="formId" >
  <table border="0" align="center">
  	<logic:present name="filtro">
  		<logic:equal name="filtro" value="1">
  		<tr>
  			<td><label><bean:message key="lbl.ordpago.opaTotal" />:</label></td>
		    <td>
		    	<html:text property="opaTotal" styleClass="obligatorio" disabled="${form.dis}"/>
		    </td>
		</tr>
		<tr>
  			<td><label><bean:message key="lbl.ordpago.totalDescuento" />:</label></td>
		    <td>
		    	<html:text property="totalDescuento" styleClass="obligatorio" disabled="${form.dis}"/>
		    </td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion" >
					<bean:message key="cmd.ordpago.calcular"/>
				</html:submit>
				&nbsp;&nbsp;&nbsp;
				<html:submit property="accion" >
					<bean:message key="cmd.ordpago.guardar"/>
				</html:submit>
				&nbsp;&nbsp;&nbsp;
				<html:submit property="accion" onclick="cleanFields('formId');">
					<bean:message key="cmd.ordpago.cancelar" />
				</html:submit>
			</td>
		</tr>
		</logic:equal>
	</logic:present>
  </table>
  <html:hidden property="totalDescuento" value="${form.totalDescuento}" />
  <html:hidden property="opaTotal" value="${form.opaTotal}" />
</html:form>