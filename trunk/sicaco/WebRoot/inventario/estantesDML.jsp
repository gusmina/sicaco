<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#stnCodigoId").alphanumeric({allow:"-"});
    	$("#stnCantFilasId").numeric();
    	$("#stnCantColumnasId").numeric();
    	$("#stnPosicionId").alphanumeric({allow:"- .,;#"});
  });
</script>

<html:form action="${_accion}" method="post" focus="invBodBodegas.bodNombre" styleId="formId" >
  <table border="0">
  	<tr>
      <td><label><bean:message key="lbl.estantes.invBodBodegas.bodNombre" />:</label></td>
      <td>
       	<html:select property="invBodBodegas.bodId" styleClass="obligatorio">
			<html:optionsCollection  label="bodNombre" name="bodega" value="bodId"/>     					
		</html:select>
		<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.estantes.stnCodigo" />:</label></td>
      <td>
       	<html:text property="stnCodigo" styleId="stnCodigoId" size="15" maxlength="20" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.estantes.stnCantFilas" />:</label></td>
      <td>
       	<html:text property="stnCantFilas" styleId="stnCantFilasId" size="15" maxlength="15" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.estantes.stnCantColumnas" />:</label></td>
      <td>
       	<html:text property="stnCantColumnas" styleId="stnCantColumnasId" size="15" maxlength="15" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
  	<tr>
      <td><label><bean:message key="lbl.estantes.stnEstado" />:</label></td>
      <td>
      	<html:select property="stnEstado">
      		<html:option value="A"><bean:message key="lbl.estantes.stnEstadoA"/></html:option>
      		<html:option value="I"><bean:message key="lbl.estantes.stnEstadoI"/></html:option>
      		<html:option value="F"><bean:message key="lbl.estantes.stnEstadoF"/></html:option>
      	</html:select>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.estantes.stnPosicion" />:</label></td>
      <td>
       	<html:text property="stnPosicion" styleId="stnPosicionId" size="15" maxlength="100" styleClass="obligatorio"/>
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
	        		<bean:message key="cmd.estantes.salvar"/>
	        	</html:submit>
	        	<input type="button" id="deleteButtonId" onclick="handlerDeleteButton();" value='<bean:message key="lbl.bodegas.eliminar"/>'>
	        	<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.estantes.cancelar" />
	        	</html:submit>
        	</logic:equal>
        	<logic:equal name="filtro" value="0">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" >
	        		<bean:message key="cmd.estantes.guardar"/>
	        	</html:submit>
	        	&nbsp;&nbsp;&nbsp;&nbsp;
	        	<input type="button" value="Limpiar" onclick="cleanFields('formId');">
	        </logic:equal>
        </logic:present>
      </td>
    </tr>
  </table>
  <html:hidden property="invBodBodegas.bodId" value="${form.invBodBodegas.bodId}"/>
  <html:hidden property="stnId" value="${form.stnId}"/>
  <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
  <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
  <input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>

<script type="text/javascript">
var deleteButton = 1;
	function handlerDeleteButton(){
	var msgText = '<h3 style="color: red;">!!Eliminacion de Estante!!</h3>'+
   				 '<p style="font-size: 1.4em; font-weight: bold;">Esta completamente seguro que desea eliminar este Estante ?';	
				$.prompt(msgText,{
      				buttons:{Ok:true,Cancel:false}, 
      				prefix:'brownJqi',
      				callback : function(v,m){
      					if(v == true){
      						$('#accionId').val('eliminar');
      						$('#pageId').val('0');
							$('#formId')[0].submit();
      					}
      				}
				});
		
	}
	
</script>