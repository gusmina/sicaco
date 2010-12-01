<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#zonCodigoId").alphanumeric();
    	$("#zonNombreId").alphanumeric();
  });
</script>
<html:form action="${_accion}" method="post"  focus="zonCodigo" styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.zon.zonCodigo" /></label>
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="100" size="10" property="zonCodigo" value="${form.zonCodigo}" styleId="zonCodigoId"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.zon.zonNombre" /></label>
		</td>
		<td>
			<html:text styleId="zonNombreId" styleClass="obligatorio" maxlength="100" size="10" property="zonNombre" value="${form.zonNombre}"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.zon.zonDescripcion" /></label>
		</td>
		<td>
			<html:textarea styleClass="opcional" cols="25" rows="4" property="zonDescripcion" value="${form.zonDescripcion}"></html:textarea> 
		</td>
	</tr>
	<tr>
		<td>
			
			<html:submit property="accion" >
				<bean:message key="cmd.zon.guardar" />
			</html:submit> 
		</td>
		<td>
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<input class="exclude" type="hidden" name="zonId" id="zonId" value=""/>
<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>

<script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerDeleteButton(id){
		$('#zonId').val(id);
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n de Zonas!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea eliminar esta Zona ?'+'<br>'+'</p>';	
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