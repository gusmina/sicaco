<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<div>
	<html:form action="/tipoNota" method="post" styleId="formId">
		<table>
			<tr>
				<td><label><bean:message key="lbl.tipoNota.tntNombre" /></label></td>
				<td>
					<html:text property="tntNombre" size="25" maxlength="100" styleClass="obligatorio"  value="${form.tntNombre}" />
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr>
			<tr>
				<td><label><bean:message key="lbl.tipoNota.tntDescripcion" /></label></td>
				<td>
					<html:textarea property="tntDescripcion" rows="5" cols="20" styleClass="obligatorio" value="${form.tntDescripcion}" />
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<%-- Acciones sin seleccionar un elemento de la lista --%>
					<logic:empty name="tipoNotaForm" property="tntId">
						<html:submit property="accion">
							<bean:message key="cmd.tipoNota.buscar"/>
						</html:submit>
						
						<html:submit property="accion">
							<bean:message key="cmd.tipoNota.insertar"/>
						</html:submit>
						
						<html:submit property="accion" disabled="true">
							<bean:message key="cmd.tipoNota.actualizar"/>
						</html:submit>
						
						<html:submit property="accion" disabled="true">
							<bean:message key="cmd.tipoNota.eliminar"/>
						</html:submit>
						
						<html:submit property="accion" disabled="true">
							<bean:message key="cmd.tipoNota.cancelar"/>
						</html:submit>
						
					</logic:empty>
					<%-- Acciones seleccionando un elemento de la lista --%>
					<logic:notEmpty name="tipoNotaForm" property="tntId">
						<logic:equal name="tipoNotaForm" property="tntId" value="0">
							<html:submit property="accion">
								<bean:message key="cmd.tipoNota.buscar"/>
							</html:submit>
							
							<html:submit property="accion">
								<bean:message key="cmd.tipoNota.insertar"/>
							</html:submit>
							
							<html:submit property="accion" disabled="true">
								<bean:message key="cmd.tipoNota.actualizar"/>
							</html:submit>
							
							<html:submit property="accion" disabled="true">
								<bean:message key="cmd.tipoNota.eliminar"/>
							</html:submit>
							
							<html:submit property="accion" disabled="true">
								<bean:message key="cmd.tipoNota.cancelar"/>
							</html:submit>
						</logic:equal>
						
						<logic:notEqual name="tipoNotaForm" property="tntId" value="0">
							<html:submit property="accion" disabled="true">
								<bean:message key="cmd.tipoNota.buscar"/>
							</html:submit>
							
							<html:submit property="accion" disabled="true">
								<bean:message key="cmd.tipoNota.insertar"/>
							</html:submit>
							
							<html:submit property="accion">
								<bean:message key="cmd.tipoNota.actualizar"/>
							</html:submit>
							
							<html:submit property="accion">
								<bean:message key="cmd.tipoNota.eliminar"/>
							</html:submit>
							
							<html:submit property="accion">
								<bean:message key="cmd.tipoNota.cancelar"/>
							</html:submit>
						</logic:notEqual>
						
					</logic:notEmpty>
				</td>
			</tr>
		</table>
		<%-- Campos ocultos --%>
		<input id="pageId" type="hidden" name="page" value="3"/>
		<html:hidden property="tntId" value="${form.tfiId}"/>
	</html:form>
</div>