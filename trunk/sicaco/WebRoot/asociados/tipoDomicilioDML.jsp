<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript" >
	$(document).ready(function(){
		$("#domNombre").alpha({allow:", "});
		$("#domDescripcion").alpha({allow:", "});
	});
</script>

<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.dom.domNombre"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="50" size="10" property="domNombre" value="${form.domNombre}" styleId="domNombre"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.dom.domDescripcion" /></label>:
			<a href="" ></a>
		</td>
		<td>
			<html:textarea styleClass="obligatorio" rows="3" cols="25" property="domDescripcion" value="${form.domDescripcion}" styleId="domDescripcion"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<html:submit property="accion" >
				<bean:message key="cmd.dom.guardar" />
			</html:submit> 
		</td>
		<td>
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="domId" value="${form.domId}"/>
<html:hidden property="mdf" value="${form.mdf}"/> 
</html:form>