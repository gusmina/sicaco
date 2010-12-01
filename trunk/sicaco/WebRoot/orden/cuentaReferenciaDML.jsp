<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#refCuentaId").alphanumeric({allow:"-"});
    	$("#refDescripcionId").alphanumeric({allow:" .,;():"});
  });
</script>

<html:form action="${_accion}" method="post" focus="refCuenta" styleId="formId" >
  <table border="0">
    <tr>
      <td><label><bean:message key="lbl.cref.refCuenta" />:</label></td>
      <td>
       	<html:text property="refCuenta" styleId="refCuentaId" size="15" maxlength="200" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.cref.refDescripcion" />:</label></td>
      <td>
       	<html:textarea cols="30" rows="3" styleId="refDescripcionId" property="refDescripcion" styleClass="obligatorio"/>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
      	<logic:present name="filtro">
      		<%-- Solo acciones de busqueda un boton nada mas --%>
      		<logic:equal name="filtro" value="1">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" >
	        		<bean:message key="cmd.cref.salvar"/>
	        	</html:submit>
	        	<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.cref.cancelar" />
	        	</html:submit>
        	</logic:equal>
        	<logic:equal name="filtro" value="0">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" >
	        		<bean:message key="cmd.cref.guardar"/>
	        	</html:submit>
	        	<html:submit property="accion" onclick="$('#pageId').val('0');" >
	        		<bean:message key="cmd.cref.redirectProveedor" />
	        	</html:submit>
<%--       	&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" id="deleteButtonId" onclick="handlerDeleteButton();" value='<bean:message key="lbl.cref.eliminar"/>'>
	        	<input type="button" value="Limpiar" onclick="cleanFields('formId');">	--%>
	        </logic:equal>
        </logic:present>
      </td>
    </tr>
  </table>
  <html:hidden property="invProProveedor.proId" value="${form.invProProveedor.proId}"/>
  <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
  <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
  <html:hidden property="provId" value="${form.provId}"/>
  <html:hidden property="refEstado" value="${form.refEstado}"/>
  <html:hidden property="refId" value="${form.refId}"/>
	<input class="exclude" type="hidden" name="refId" id="refId" value=""/>
	<input class="exclude" type="hidden" name="reffId" id="reffId" value=""/>
	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>

<script type="text/javascript">
var deleteButton = 1;
	function handlerDeleteButton(id){
	$('#reffId').val(id);
	var msgText = '<h3 style="color: red;">!!Inactivacion de Cuenta!!</h3>'+
   				 '<p style="font-size: 1.4em; font-weight: bold;">Esta completamente seguro que desea inactivar esta Cuenta de Referencia ?';	
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

