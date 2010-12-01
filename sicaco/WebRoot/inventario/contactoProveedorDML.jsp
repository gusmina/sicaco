<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#cprNombreCompletoId").alpha({allow:" "});
    	$("#cprNumeroTelOficinaId").numeric();
    	$("#cprNumeroTelCelularId").numeric();
    	$("#cprEmpresaCelularId").alphanumeric({allow:" ()"});
    	$("#cprNumeroFaxId").numeric();
    	$("#cprEmailId").alphanumeric({allow:"@."});
  });
</script>

<div>
  <html:form action="${_accion}" method="post" focus="cprNombreCompleto" styleId="formId">
      <table border="0">
      <logic:present name="filtro">
      <logic:equal name="filtro" value="0">
        <tr>
          <td><label><bean:message key="lbl.contactoProveedor.cprNombreCompleto" />:</label></td>
          <td>
          	<html:text property="cprNombreCompleto" styleId="cprNombreCompletoId" size="15" maxlength="150" styleClass="obligatorio"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
        </tr>
      	<tr>
          <td><label><bean:message key="lbl.contactoProveedor.cprNumeroTelOficina" />:</label></td>
          <td>
          	<html:text property="cprNumeroTelOficina" styleId="cprNumeroTelOficinaId" size="15" maxlength="15" styleClass="obligatorio"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
        </tr>
        <tr>
          <td><label><bean:message key="lbl.contactoProveedor.cprNumeroTelCelular" />:</label></td>
          <td>
          	<html:text property="cprNumeroTelCelular" styleId="cprNumeroTelCelularId" size="15" maxlength="15" styleClass="obligatorio"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
        </tr>
        <tr>
          <td><label><bean:message key="lbl.contactoProveedor.cprEmpresaCelular" />:</label></td>
          <td>
          	<html:text property="cprEmpresaCelular" styleId="cprEmpresaCelularId" size="15" maxlength="150" styleClass="obligatorio"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
        </tr>
        <tr>
          <td><label><bean:message key="lbl.contactoProveedor.cprNumeroFax" />:</label></td>
          <td>
          	<html:text property="cprNumeroFax" styleId="cprNumeroFaxId" size="15" maxlength="15" styleClass="obligatorio"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
        </tr>
        <tr>
          <td><label><bean:message key="lbl.contactoProveedor.cprEmail" />:</label></td>
          <td>
          	<html:text property="cprEmail" styleId="cprEmailId" size="15" maxlength="150" styleClass="obligatorio"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
        </tr>
       </logic:equal>
      </logic:present>
        <tr>
          <td colspan="2" align="center">
          	<logic:present name="filtro">
          		<%-- Cuando no tiene filtro van a ser acciones de DML --%>
          		<logic:equal name="filtro" value="0">
          			<input id="pageId" type="hidden" name="page" value="3">
          			<logic:present name="boton">
          				<%-- Si el parametro es igual a cero servira para agregar un nuevo usuario --%>
          				<logic:equal name="boton" value="0">
          					<html:submit property="accion">
				          		<bean:message key="cmd.contactoProveedor.guardar" />
				          	</html:submit>
				          	<html:submit property="accion" onclick="$('#pageId').val('0');">
						          <bean:message key="cmd.contactoProveedor.return" />
						    </html:submit>
						    <input type="button" value="Limpiar" onclick="cleanFields('formId');">
				        </logic:equal>
          				<%-- Si el parametro es uno indiciara que se va a editar el usuario --%>
          				<logic:equal name="boton" value="1">
          					<html:submit property="accion">
				          		<bean:message key="cmd.contactoProveedor.salvar" />
				          	</html:submit>
	           				<input type="button" id="deleteButtonId" onclick="handlerDeleteButton();" value='<bean:message key="lbl.contactoProveedor.eliminar"/>'> 
	           				
	          				<html:submit property="accion" onclick="$('#pageId').val('0');">
				          		<bean:message key="cmd.contactoProveedor.cancelar" />
				          	</html:submit>
          				</logic:equal>
          				
          			</logic:present>
          			
          		</logic:equal>
          		<%-- Solo acciones de busqueda un boton nada mas --%>
          		<logic:equal name="filtro" value="1">
          			<html:submit property="accion">
				    	<bean:message key="cmd.contactoProveedor.agregar" />
				    </html:submit>
				    <html:submit property="accion">
				          <bean:message key="cmd.contactoProveedor.return" />
				    </html:submit>
          		</logic:equal>
          	</logic:present>
          </td>
        </tr>
      </table>
      <html:hidden property="proId" value="${form.proId}"/>
      <html:hidden property="cprId" value="${form.cprId}"/>
      <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
      <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
      <input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
    </html:form>
</div>

<script type="text/javascript">
var deleteButton = 1;
	function handlerDeleteButton(){
	var msgText = '<h3 style="color: red;">!!Eliminacion de Contacto!!</h3>'+
   				 '<p style="font-size: 1.4em; font-weight: bold;">Esta completamente seguro que desea eliminar este contacto ?';	
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