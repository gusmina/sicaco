<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.plm.planNombre"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="50" size="10" property="plmNombre" value="${form.plmNombre}" styleId="plmNombre"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.plm.planDescripcion" /></label>:
		</td>
		<td>
			<html:textarea styleClass="obligatorio" rows="3" cols="25" property="plmDescripcion" value="${form.plmDescripcion}" styleId="plmDescripcion"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.plm.planDuracion" /></label>:
			
		</td>
		<td>
			<html:text styleClass="obligatorio" style="integer" maxlength="4" size="5" property="plmDuracion" value="${form.plmDuracion}" styleId="plmDuracion"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	
	<tr>
		<td>
			<html:submit property="accion" >
				<bean:message key="cmd.plm.guardar" />
			</html:submit> 
		</td>
		<td>
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="plmId" value="${form.plmId}"/>
<html:hidden property="mdf" value="${form.mdf}"/> 
</html:form>