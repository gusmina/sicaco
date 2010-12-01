<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<script type="text/javascript">
$(document).ready(function(){
	$("#parDescripcion").alpha({allow:""});
	});  			
</script>
<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.par.parDescripcion" /></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" property="parDescripcion" value="${form.parDescripcion}" styleId="parDescripcion"/>
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<html:submit property="accion" >
				<bean:message key="cmd.par.guardar" />
			</html:submit> 
		</td>
		<td>
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="parId" value="${form.parId}"/>
<html:hidden property="mdf" value="${form.mdf}"/> 
</html:form>