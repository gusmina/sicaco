<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#sucNombreId").alphanumeric({allow:" "});
    	$("#sucDireccionId").alphanumeric({allow:" (),;.#"});
  });
</script>
<html:form action="${_accion}" method="post"  focus="sucNombre" styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.suc.sucNombre" /></label>
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="100" size="10" property="sucNombre" styleId="sucNombreId"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.suc.sucDireccion" /></label>
		</td>
		<td>
			<html:textarea styleClass="opcional" cols="30" rows="4" property="sucDireccion" styleId="sucDireccionId"></html:textarea> 
		</td>
	</tr>
	<tr>
		<td>
			<input id="pageId" type="hidden" name="page" value="3">
			<html:submit property="accion" >
				<bean:message key="cmd.suc.guardar" />
			</html:submit> 
		</td>
		<td>
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 

<input class="exclude" type="hidden" name="sucId" id="sucId" value=""/>
<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>

<script type="text/javascript">
	
	function handlerDeleteButton(id){
		$('#sucId').val(id);
		var msgText = '<h3 style="color: red;">!!Activaci&oacute;n e Inactivaci&oacute;n de sucursales!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Est&aacute; completamente seguro de ejecutar la acci&oacute;n ?'+'<br>'+'</p>';	
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
	
	function handlerDeleteButton2(id){
		$('#sucId').val(id);
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n de sucursales!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Est&aacute; completamente seguro que desea eliminar la sucursal ?'+'<br>'+'</p>';	
			$.prompt(msgText,{
		    buttons:{Ok:true,Cancel:false}, 
		    prefix:'brownJqi',
		    callback : function(v,m){
		    	if(v == true){
		    		$('#accionId').val('eliminar2');
		    		$('#pageId').val('0');
					$('#formId')[0].submit();
		    	}
		    }
		});
	}
	
</script>
