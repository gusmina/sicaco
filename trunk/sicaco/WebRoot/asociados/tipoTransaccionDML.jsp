<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript" >
	$(document).ready(function(){
		$("#ttrNombre").alpha({allow:", "});
		$("#ttrDescripcion").alpha({allow:", "});
	});
</script>

<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.ttr.tTranNombre"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="50" size="10" property="ttrNombre" value="${form.ttrNombre}" styleId="ttrNombre"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.ttr.tTranDescripcion" /></label>:
			<a href="" ></a>
		</td>
		<td>
			<html:textarea styleClass="obligatorio" rows="3" cols="25" property="ttrDescripcion" value="${form.ttrDescripcion}" styleId="ttrDescripcion"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
		<tr>
		<td>
			<label><bean:message key="lbl.ttr.tTranUso" /></label>:
		</td>
		<td>
			<html:select property="ttrUso" value="${form.ttrUso}" styleClass="obligatorio"  disabled="${form.mdf}">
				<html:option value="A" ><bean:message key="lbl.ttr.tAbono" /></html:option>
				<html:option value="C" ><bean:message key="lbl.ttr.tCargo" /></html:option>
			</html:select>
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<html:submit property="accion" >
				<bean:message key="cmd.ttr.guardar" />
			</html:submit> 
		</td>
		<td>
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="ttrId" value="${form.ttrId}"/>
<html:hidden property="ttrUso" value="${form.ttrUso}"/>
<html:hidden property="mdf" value="${form.mdf}"/> 
</html:form>