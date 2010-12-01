<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
		$("#perDuiId").numeric();
    	$("#perPrimerNombreId").alpha();
    	$("#perSegundoNombreId").alpha();
    	$("#perPrimerApellidoId").alpha();
    	$("#perSegundoApellidoId").alpha();
  });
</script>

<table align="center">
      	<tr> 
      		<td> 
      			<label style=""><bean:message key="lbl.usuario.perDui" /></label> 
      		</td> 
      		<td> 
      			<html:text styleId="perDuiId"  styleClass="condicional" maxlength="${tamDui}" value="${form.perDui}" size="${tamDui}" property="perDui" styleClass="condicional"></html:text> 
      		</td> 
      		<td> 
      			<label><bean:message key="lbl.usuario.perNit" /></label> 
      		</td> 
      		<td> 
      			<html:text  styleClass="condicional" onkeyup="maskNit(this);" maxlength="17" size="17" value="${form.perNit}" property="perNit" styleClass="condicional"></html:text> 
      		</td> 
      	</tr>
      	<tr> 
          <td><label><bean:message key="lbl.persona.perPrimerNombre" />:</label></td> 
          <td><html:text property="perPrimerNombre" value="${form.perPrimerNombre}"></html:text>
          </td> 
          <td> 
          	<label><bean:message key="lbl.persona.perSegundoNombre" />:</label> 
          </td> 
          <td> 
          		<html:text property="perSegundoNombre" value="${form.perSegundoNombre}"></html:text> 
          </td> 
        </tr> 
         
        <tr> 
          <td> 
          	<label><bean:message key="lbl.persona.perPrimerApellido" />:</label> 
          </td> 
          <td> 
          	<html:text property="perPrimerApellido" value="${form.perPrimerApellido}" ></html:text> 
     
          </td> 
          <td> 
          	<label><bean:message key="lbl.persona.perSegundoApellido" />:</label> 
          </td> 
          <td> 
          	<html:text  property="perSegundoApellido" value="${form.perSegundoApellido}"></html:text> 
          	<html:hidden property="perId" value="${form.perId}"/>
      		<input type="hidden" name="page" id="pageId" value="3">
      		<input type="hidden" name="accion" id="accionId" value=""/>
          </td> 
        </tr>
</table>