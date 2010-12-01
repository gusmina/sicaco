<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<html:form action="${_accion}" method="post" styleId="formId">
	<table align="center">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.tic.ticNombre" />
				</label>
			</td>
			<td>
				<html:text property="ticNombre" styleClass="obligatorio"  styleId="ascCodigo"/>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.tci.ticAcreedora" />
				</label>
			</td>
			<td>
				<html:select property="ticAcreeDeudo" styleClass="obligatorio">
					<html:option value="0">Si</html:option> 
					<html:option value="1">No</html:option>
				</html:select>
			</td>
		</tr>		
	</table>
	<table align="center">
		<tr>
		<logic:equal value="false" name="form" property="mdf">
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.tic.guardar" />
				</html:submit>
			</td>
			</logic:equal>
			<logic:equal value="true" name="form" property="mdf">
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.tic.modificar" />
				</html:submit>
			</td>
			</logic:equal>
			<td>
				<input type="button" value="Limpiar"
					onclick="cleanFields('formId');$('#id').val('');$('#mdf').val('false');">
			</td>
		<tr>
	</table>
	<html:hidden property="ticId" styleId="id"/>
	<input id="pageId" type="hidden" name="page" value="3">
	<html:hidden property="mdf" styleId="mdf"/>
</html:form>