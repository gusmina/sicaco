<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#bodNombreId").alphanumeric({allow:" "});
    	$("#cprCantidadMinimaId").numeric({allow:"."});
    	$("#cprCantidadMaximaId").numeric({allow:"."});
  });
</script>

<html:form action="${_accion}" method="post" styleId="formId">
   
    <table border="0">
    <logic:present name="filtro">
    <logic:equal name="filtro" value="0">
    <tr>
    	<td></td>
    </tr>
    </logic:equal>
    <logic:equal name="filtro" value="1">
    	<tr>
    		<td>
    			<label><bean:message key="lbl.capacidad.id.invBodBodegas.bodNombre"/></label>
    		</td>
    		<td>
  				<html:text property="id.invBodBodegas.bodNombre" disabled="true" styleId="bodNombreId" size="15" maxlength="250"/>  				
    		</td>
    	</tr>
    	<tr>
    		<td>
    			<label><bean:message key="lbl.capacidad.cprCantidadMinima"/></label>
    		</td>
    		<td>
  				<html:text property="cprCantidadMinima" styleId="cprCantidadMinimaId" size="15" maxlength="15" /> 				
    		</td>
    	</tr>
    	<tr>
    		<td>
    			<label><bean:message key="lbl.capacidad.cprCantidadMaxima"/></label>
    		</td>
    		<td>
  				<html:text property="cprCantidadMaxima" styleId="cprCantidadMaximaId" size="15" maxlength="15" /> 				
    		</td>
    	</tr>
    	
    </logic:equal>
 	  
    </logic:present>
<tr>
	<td colspan="2" align="center">
    	<logic:present name="filtro">
	    	<logic:equal name="filtro" value="0">
	    		<html:submit property="accion">
	    			<bean:message key="cmd.capacidad.regresar"/>
	    		</html:submit>
	    	</logic:equal>
     		<%-- Cuando no tiene filtro van a ser acciones de DML --%>
     		<logic:equal name="filtro" value="1">
     				
			<html:submit property="accion" onclick="$('#pageId').val('4');">
         		<bean:message key="cmd.capacidad.salvar" />
         	</html:submit>
         	<html:submit property="accion">
         		<bean:message key="cmd.capacidad.reset" />
         	</html:submit>
         	<html:submit property="accion">
         		<bean:message key="cmd.capacidad.cancelar" />
         	</html:submit>
     		</logic:equal>
     	</logic:present>
     			
     		
     </td>
   </tr>
 </table>
 <html:hidden property="id.invArtArticulo.artCodigo"/>
 <html:hidden property="id.invBodBodegas.bodId"/>
 <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
 <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
</html:form>