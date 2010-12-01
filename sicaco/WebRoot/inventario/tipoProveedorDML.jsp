<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#tprNombreId").alphanumeric({allow:" "});
  });
</script>

<html:form action="${_accion}" method="post" focus="tprNombre" styleId="formId" >
  <table border="0">
  	<tr>
      <td><label><bean:message key="lbl.tipoProveedor.tprNombre" />:</label></td>
      <td>
       	<html:text styleId="tprNombreId" size="15" maxlength="150" property="tprNombre" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      		
      </td>
    </tr>
       
    <tr>
      <td colspan="2" align="center">
      	<logic:present name="filtro">
      		<%-- Solo acciones de busqueda un boton nada mas --%>
      		<logic:equal name="filtro" value="1">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" >
	        		<bean:message key="cmd.tipoProveedor.guardar"/>
	        	</html:submit>
      		</logic:equal>
      		
      	</logic:present>
      </td>
    </tr>
  </table>
</html:form>