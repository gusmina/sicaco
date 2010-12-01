<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#artCodId").numeric();
    	$("#artNameId").alphanumeric({allow:" "});
  });
</script>

<html:form action="${_accion}" method="post" styleId="formId">
	<table>
		<tr>
			<td><label><bean:message key="lbl.abo.artCod"/>:</label></td>
			<td>
				<html:text property="artCod" value="${form.artCod}" styleId="artCodId" size="15" maxlength="7"/>
			</td> 
		</tr>
		<tr>
			<td><label><bean:message key="lbl.abo.artName"/>:</label></td>
			<td>
				<html:text property="artName" value="${form.artName}" styleId="artNameId" size="15" maxlength="150"/>
			</td> 
		</tr>
		<tr>
			<td colspan="2">
				<html:submit property="accion">
	  				<bean:message key="cmd.abo.buscar" />
	  			</html:submit>
	  			<html:submit property="accion">
	  				<bean:message key="cmd.abo.return" />
	  			</html:submit>
	  		</td>
  		</tr>
	</table>
	<html:hidden property="bodega" styleId="bodega" value="${form.bodega}"/>
</html:form>
