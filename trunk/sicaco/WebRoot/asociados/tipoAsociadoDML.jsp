<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#tasNombre").alphanumeric({allow:" "});
    	$("#tasDescripcion").alphanumeric({allow:" .,#;"});
  });
</script>

<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.tas.tAscNombre"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="50" size="10" property="tasNombre" value="${form.tasNombre}" styleId="tasNombre"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.tas.tAscDescripcion" /></label>:
			<a href="" ></a>
		</td>
		<td>
			<html:textarea styleClass="obligatorio" rows="3" cols="25" property="tasDescripcion" value="${form.tasDescripcion}" styleId="tasDescripcion"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<html:submit property="accion" >
				<bean:message key="cmd.tas.guardar" />
			</html:submit> 
		</td>
		<td>
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="tasId" value="${form.tasId}"/>
<html:hidden property="mdf" value="${form.mdf}"/> 
</html:form>