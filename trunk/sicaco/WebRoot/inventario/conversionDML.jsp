<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#cnvNuevaMedidaId").alphanumeric({allow:"."});
    	$("#cnvFactorId").numeric({allow:"."});
    	$("#cnvNombreMedidaId").alphanumeric({allow:" -"});
    	$("#cnvComentarioId").alphanumeric({allow:" (),.;-"});
  });
</script>

<html:form action="${_accion}" method="post"  focus="cnvNuevaMedida" styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.conversion.cnvNuevaMedida" /></label>
		</td>
		<td>
			<html:text styleClass="obligatorio" styleId="cnvNuevaMedidaId" maxlength="20" size="20" property="cnvNuevaMedida" ></html:text> 
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.conversion.cnvFactor" /></label>
		</td>
		<td>
			<html:text styleClass="obligatorio" styleId="cnvFactor" maxlength="20" size="20" property="cnvFactor" ></html:text> 
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.conversion.cnvNombreMedida" /></label>
		</td>
		<td>
			<html:text styleClass="obligatorio" styleId="cnvNombreMedidaId" maxlength="80" size="25" property="cnvNombreMedida" ></html:text> 
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.conversion.cnvComentario" /></label>
		</td>
		<td>
			<html:textarea styleClass="obligatorio" styleId="cnvComentarioId" rows="3" cols="30" property="cnvComentario" ></html:textarea> 
		</td>
	</tr>
	<tr>
		<td>
			<input type="hidden" name="page" id="pageId" value="3"/>
			<html:submit property="accion" >
				<bean:message key="cmd.conversion.guardar" />
			</html:submit> 
		</td>
		<td>
			<html:submit property="accion" onclick="$('#pageId').val('0');">
				<bean:message key="cmd.conversion.redirectMedida" />
			</html:submit> 
		</td>
		<td>
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 

<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
<input class="exclude" type="hidden" name="cnvId" id="cnvId" value=""/>
<html:hidden property="medId" value="${form.medId}"/>
</html:form>

<script type="text/javascript">
var deleteButton = 1;
	function handlerDeleteButton(id){
	$('#cnvId').val(id);
	var msgText = '<h3 style="color: red;">!!Eliminacion de Linea!!</h3>'+
   				 '<p style="font-size: 1.4em; font-weight: bold;">Esta completamente seguro que desea eliminar esta conversion ?';	
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