<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#dptNombreId").alphanumeric({allow:" "});
    	$("#dptUbicacionId").alphanumeric({allow:" .,#"});
    	$("#dptDescripcionId").alphanumeric({allow:" .,#"});
    	$("#dptCentroCostoId").alphanumeric();
  });
</script>
<html:form action="${_accion}" method="post"  focus="dptNombre" styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.dpt.dptNombre" /></label>
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="50" size="10" property="dptNombre" value="${form.dptNombre}"
				styleId="dptNombreId"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.dpt.dptCentroCosto" /></label>
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="15" size="10" property="dptCentroCosto" value="${form.dptCentroCosto}"
				styleId="dptCentroCostoId"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.dpt.dptUbicacion" /></label>
		</td>
		<td>
			<html:textarea styleClass="obligatorio" rows="3" cols="25" property="dptUbicacion" value="${form.dptUbicacion}"
				styleId="dptUbicacionId"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.dpt.dptDescripcion" /></label>
		</td>
		<td>
			<html:textarea styleClass="obligatorio" rows="3" cols="25" property="dptDescripcion" value="${form.dptDescripcion}"
				styleId="dptDescripcionId"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<logic:present name="filtro">
		<logic:equal value="1" name="filtro">
			<tr>
				<td>
					<html:submit property="accion" >
						<bean:message key="cmd.dpt.guardar" />
					</html:submit> 
				</td>
				<td>
					<html:submit property="accion" onclick="$('#pageId').val('0');">
						<bean:message key="cmd.dpt.regresar" />
					</html:submit> 
					<input type="button" value="Limpiar" onclick="cleanFields('formId');">
				</td>
			</tr>
		</logic:equal>
		<logic:equal value="2" name="filtro">
			<tr>
				<td>
					<html:submit property="accion" >
						<bean:message key="cmd.dpt.salvar" />
					</html:submit> 
				</td>
				<td>
					<html:submit property="accion" >
						<bean:message key="cmd.dpt.cancelar" />
					</html:submit> 
				</td>
			</tr>
		</logic:equal>
	</logic:present>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<input class="exclude" type="hidden" name="dptId" id="dptId" value="${form.dptId}"/>
<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
<input class="exclude" type="hidden" name="dptId2" id="dptId2" value="${form.dptId2}"/>
<html:hidden property="etrId" value="${form.etrId}"/>
<html:hidden property="dptEstado" value="${form.dptEstado}"/>
</html:form>

<script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerDeleteButton(id){
		$('#dptId2').val(id);
		var msgText = '<h3 style="color: red;">!!Activaci&oacute;n/Inactivaci&oacute;n de Departamentos de Trabajo!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea activar/inactivar este Departamento ?'+'<br>'+'</p>';	
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