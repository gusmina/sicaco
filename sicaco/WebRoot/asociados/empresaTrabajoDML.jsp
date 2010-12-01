<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
$(document).ready(function(){
    	$("#etrNombreId").alphanumeric({allow:" ,.&"});
    	$("#etrDescripcionId").alphanumeric({allow:" .,#&"});
  });
</script>
<html:form action="${_accion}" method="post"  focus="zonCodigo" styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.etr.etrNombre" /></label>
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="50" size="10" property="etrNombre" value="${form.etrNombre}"
				styleId="etrNombreId"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.etr.etrDescripcion" /></label>
		</td>
		<td>
			<html:textarea styleClass="obligatorio" rows="3" cols="25" property="etrDescripcion" value="${form.etrDescripcion}"
				styleId="etrDescripcionId"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<logic:present name="filtro">
		<logic:equal value="1" name="filtro">
			<tr>
				<td>
					
					<html:submit property="accion" >
						<bean:message key="cmd.etr.guardar" />
					</html:submit> 
				</td>
				<td>
					<input type="button" value="Limpiar" onclick="cleanFields('formId');">
				</td>
			</tr>
		</logic:equal>
		<logic:equal value="2" name="filtro">
			<tr>
				<td>
					<html:submit property="accion" >
						<bean:message key="cmd.etr.salvar" />
					</html:submit> 
				</td>
				<td>
					<html:submit property="accion" >
						<bean:message key="cmd.etr.cancelar" />
					</html:submit> 
				</td>
			</tr>
		</logic:equal>
	</logic:present>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<input class="exclude" type="hidden" name="etrId2" id="etrId2" value=""/>
<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
<html:hidden property="etrId" value="${form.etrId}"/>
<html:hidden property="etrEstado" value="${form.etrEstado}"/>
</html:form>

<script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerDeleteButton(id){
		$('#etrId2').val(id);
		var msgText = '<h3 style="color: red;">!!Activaci&oacute;n/Inactivaci&oacute;n de Empresas!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea activar/inactivar esta Empresa ?'+'<br>'+'</p>';	
			$.prompt(msgText,{
		    buttons:{Ok:true,Cancel:false}, 
		    prefix:'brownJqi',
		    callback : function(v,m){
		    	if(v == true){
		    		$('#accionId').val('inactivar');
		    		$('#pageId').val('0');
					$('#formId')[0].submit();
		    	}
		    }
		});
	}
	
</script>