<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#tclClasificacionId").alphanumeric();
    	$("#tclDescripcionId").alphanumeric({allow:" ,.;()-"});
  });
</script>

<div>
  <html:form action="${_accion}" method="post" focus="tclClasificacion" styleId="formId">
      <table border="0">
        <tr>
          <td><label><bean:message key="lbl.tipoClasificacion.tclClasificacion" />:</label></td>
          <td>
          	<html:text property="tclClasificacion" styleId="tclClasificacionId" styleClass="obligatorio" size="20" maxlength="2"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
        </tr>
      	<tr>
          <td><label><bean:message key="lbl.tipoClasificacion.tclDescripcion" />:</label></td>
          <td>
          	<html:textarea property="tclDescripcion" styleId="tclDescripcionId" styleClass="obligatorio" rows="2" cols="25"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
          	<input type="hidden" name="page" id="pageId" value="3"/>
   			<html:submit property="accion">
		    	<bean:message key="cmd.tipoClasificacion.guardar" />
		    </html:submit>
          </td>
        </tr>
      </table>
      <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
      <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
    <input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
	<input class="exclude" type="hidden" name="tclId" id="tclId" value=""/>
</html:form>

<script type="text/javascript">
var deleteButton = 1;
	function handlerDeleteButton(id){
	$('#tclId').val(id);
	var msgText = '<h3 style="color: red;">!!Eliminacion de tipo de Clasificacion!!</h3>'+
   				 '<p style="font-size: 1.4em; font-weight: bold;">Esta completamente seguro que desea eliminar este tipo de Clasificacion ?';	
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