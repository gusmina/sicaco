<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.asc.id.ascCodigo" /></label>
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="100" size="10" property="ascCodigo" /> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.asc.secEtrEmpresaTrabajo.empId" /></label>
		</td>
		<td>
	       	<html:select property="secEtrEmpresaTrabajo.empId" styleClass="obligatorio">
				<html:optionsCollection  label="empNombre" name="emp" value="empId"/>     					
			</html:select>
      	</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.asc.secTasTipoAsociado.tasId" /></label>
		</td>
		<td>
	       	<html:select property="secTasTipoAsociado.tasId" styleClass="obligatorio">
				<html:optionsCollection  label="tasNombre" name="tas" value="tasId"/>     					
			</html:select>
      	</td>
	</tr>
	<logic:present name="filtro">
	<logic:equal name="filtro" value="1">
	<tr>
		<td>
			<label><bean:message key="lbl.asc.ascFechaIngreso" /></label>
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="100" size="10" property="ascFechaIngreso" /> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.asc.ascFechaRetiro" /></label>
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="100" size="10" property="ascFechaRetiro" /> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	</logic:equal>
	</logic:present>
	<tr>
	<logic:present name="filtro">
	<logic:equal name="filtro" value="0">
		<td>
			<input id="pageId" type="hidden" name="page" value="3">
			<html:submit property="accion" >
				<bean:message key="cmd.asc.guardar" />
			</html:submit> 
		</td>
		<td>
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
		<td>
			<html:submit property="accion" >
				<bean:message key="cmd.asc.return" />
			</html:submit> 
		</td>
	</logic:equal>
	</logic:present>
	</tr>
</table> 
<html:hidden property="perId" value="${form.perId}"/>
<html:hidden property="ascCodigo" value="${form.ascCodigo}"/>
<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
<input class="exclude" type="hidden" name="ascCod" id="ascCod" value=""/>
</html:form>

<script type="text/javascript">
var deleteButton = 1;
	function handlerDeleteButton(id){
	$('#ascCod').val(id);
	var msgText = '<h3 style="color: red;">!!Anulacion de Asociado!!</h3>'+
   				 '<p style="font-size: 1.4em; font-weight: bold;">Esta completamente seguro que desea este asociado ?';	
				$.prompt(msgText,{
      				buttons:{Ok:true,Cancel:false}, 
      				prefix:'brownJqi',
      				callback : function(v,m){
      					if(v == true){
      						$('#accionId').val('anular');
      						$('#pageId').val('0');
							$('#formId')[0].submit();
      					}
      				}
				});
		
	}
	
</script>