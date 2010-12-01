<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post"  focus="nivEstado" styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.nivel.id.nivFilaId" /></label>
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="100" size="25" property="id.nivFilaId" disabled="true"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.nivel.id.nivColId" /></label>
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="100" size="25" property="id.nivColId" disabled="true"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.nivel.nivEstado" /></label>
		</td>
		<td>
			<html:select property="nivEstado">
				<html:option value="A"><bean:message key="lbl.nivel.nivEstadoA" /></html:option>
				<html:option value="I"><bean:message key="lbl.nivel.nivEstadoI" /></html:option>
				<html:option value="F"><bean:message key="lbl.nivel.nivEstadoF" /></html:option>
			</html:select>
		</td>
	</tr>
	<tr>
		<td>
			<input id="pageId" type="hidden" name="page" value="3">
			<logic:present name="filtro">
			<logic:equal name="filtro" value="2">
			<html:submit property="accion" disabled="false" >
				<bean:message key="cmd.nivel.salvar" />
			</html:submit>
			</logic:equal>
			<logic:equal name="filtro" value="1">
			<html:submit property="accion" disabled="true">
				<bean:message key="cmd.nivel.salvar" />
			</html:submit>
			</logic:equal>
			</logic:present>
		</td>
		<td>
			<html:submit property="accion" >
				<bean:message key="cmd.nivel.regresar" />
			</html:submit> 
		</td>
	</tr>
</table> 

<html:hidden property="id.nivFilaId" value="${form.id.nivFilaId}"/>
<html:hidden property="id.nivColId" value="${form.id.nivColId}"/>
<html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
<html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
<html:hidden property="stnId"/>
</html:form>