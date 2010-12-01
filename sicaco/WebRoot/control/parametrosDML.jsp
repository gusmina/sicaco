<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#parValorNumberId").numeric({allow:"."});
    	$("#parDescripcionId").alphanumeric({allow:" .,;()"});
  });
</script>

<html:form action="${_accion}"  method="post" focus="parNombre" styleId="formId">
<table border="0" cellspacing="7">
<logic:present name="filtro"> 
<logic:equal name="filtro" value="0">   	
   	<tr>
   		<td>
   			<label><bean:message key="lbl.parametros.parNombre"/></label>
   		</td>
   		<td>
   			<html:text maxlength="20" size="20" disabled="true" property="parNombre" value="${form.parNombre}"/>
   		</td>
   	</tr>
   	<tr>
   		<td>
   			<label><bean:message key="lbl.parametros.parValor"/></label>
   		</td>
   		<logic:present name="valor">
   		<logic:equal name="valor" value="0">  
	   		<td>
	   			<html:text maxlength="20" size="20" property="parValorNumber" styleId="parValorNumberId" styleClass="obligatorio" value="${form.parValorNumber}"/>
	   			<input type="hidden"  id="tipoDatoId" name="tipoDato" value="n">
	   		</td>
	   	</logic:equal>
	   	<logic:equal name="valor" value="1">  
	   		<td>
	   			<html:textarea rows="5" cols="25" property="parValorString" styleClass="obligatorio" styleId="parValorStringId" value="${form.parValorString}"/>
	   			<input id="pageId" type="hidden" name="page" value="5"/>
	   			<input type="hidden"  id="tipoDatoId" name="tipoDato" value="s">
	   		</td>
	   	</logic:equal>
	   	<logic:equal name="valor" value="2">  
	   		<td>
	   			<html:select property="parValorBoolean" value="${form.parValorBoolean}">
					<html:option value="1"><bean:message key="lbl.parametros.parValorBooleanV"/></html:option>
					<html:option value="0"><bean:message key="lbl.parametros.parValorBooleanF"/></html:option>
	   			</html:select>
	   			<input type="hidden"  id="tipoDatoId" name="tipoDato" value="b">
	   		</td>
	   	</logic:equal>
	   	<logic:equal name="valor" value="3">  
          <td>
          	<html:text style="float:left;" styleId="parValorDateId" onkeyup="mask(this);" value="${form.parValorDate}" property="parValorDate" maxlength="10" size="10"/>
        	<input  style="height: 18px;" id="button_parValorDateId" type="button"  value="..."/>
        	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d">
        	<script type="text/javascript">
		            Calendar.setup({
		              inputField    : "parValorDateId",
		              button        : "button_parValorDateId",
		              align         : "Br"
		            });
		    </script>
		  </td>
	   	</logic:equal>
   		</logic:present>
   	</tr>
   	<tr>
   		<td>
   			<label><bean:message key="lbl.parametros.parDescripcion"/></label>
   		</td>
   		<td>
   			<html:textarea rows="4" cols="40" property="parDescripcion" styleId="parDescripcionId" value="${form.parDescripcion}" styleClass="obligatorio"/>
   		</td>
   	</tr>
   	<tr>
        <td colspan="4" style="padding: 5">
        	
        		<%-- Cuando no tiene filtro van a ser acciones de DML --%>
        		
    				<%-- Si el parametro es uno indiciara que se va a editar el usuario --%>
				<table align="center" >
					<tr>
						<td>
							<html:submit property="accion">
          						<bean:message key="cmd.parametros.modificar" />
          					</html:submit>
    								</td>	
    								<td>
							<html:submit property="accion" onclick="$('#tipoDatoId').val('');">
						       		<bean:message key="cmd.parametros.cancelar" />
						    </html:submit>						
						</td>
					</tr>
				</table>
	        </td>
      </tr>
	
</logic:equal>
        		<%-- Solo acciones de busqueda un boton nada mas --%>
<logic:equal name="filtro" value="1">
	<html:submit property="accion">
   		<bean:message key="cmd.parametros.buscar" />
   	</html:submit>
</logic:equal>


</logic:present>
</table>
    <html:hidden property="parNombre" value="${form.parNombre}"/>
    <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
    <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
    <html:hidden property="parValorDate"  value="${form.parValorDate}"/>
    
  </html:form>
     
