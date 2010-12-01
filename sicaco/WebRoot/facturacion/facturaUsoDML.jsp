<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post" focus="fusNombre" styleId="formId" >
  <table border="0">
  	<tr>
      <td><label><bean:message key="lbl.facturaUso.fusNombre" />:</label></td>
      <td>
       	<html:text property="fusNombre" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>  
      <td><label><bean:message key="lbl.facturaUso.fusDescripcion" />:</label></td>
      <td>
       	<html:text property="fusDescripcion" styleClass="opcional"/>
      </td>
    </tr>
       
    <tr>
      <td>
   		<input type="hidden" id="pageId" name="page" value="3" />
   		<html:submit property="accion" >
      		<bean:message key="cmd.facturaUso.guardar"/>
      	</html:submit>
   	  <td>
   	  	<input type="button" value="Limpiar" onclick="cleanFields('formId');">
	  </td>
    </tr>
  </table>
	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
	<input class="exclude" type="hidden" name="fusId" id="fusId" value=""/>
</html:form>

<script type="text/javascript">
var deleteButton = 1;
	function handlerDeleteButton(id){
	$('#fusId').val(id);
	var msgText = '<h3 style="color: red;">!!Eliminacion de tipo de uso!!</h3>'+
   				 '<p style="font-size: 1.4em; font-weight: bold;">Esta completamente seguro que desea eliminar este tipo de uso del sistema ?';	
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