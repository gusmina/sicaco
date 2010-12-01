<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#cantidadId").numeric();
  	});
  	
  	function saveSeleccionA(valor,medida,arti) {
		valores = valor.split(';');
		$('#artCodigoId').val(valores[0]);
		$('#artNombreId').val(valores[1]);
		$('#resultadoArt').hide('slow');
	}
	function mover(){
	    $('#accionId').val('Mover');
		$('#formId')[0].submit();	
		$('#moverId').attr('disabled','disabled');
	}
</script>

<html:form action="${_accion}" method="post" styleId="formId">
	<table>
		<tr>
			<td colspan="2">
				<label><bean:message key="lbl.mi.moverA" />:</label>
			</td>
		</tr>
		<tr>
			<td>
				<label><bean:message key="lbl.mi.bodega1" />:</label>
			</td>
			<td>
				<html:select property="bodId1" styleId="bodId1"
					styleClass="obligatorio" > 
					<html:optionsCollection label="bodNombre" name="lBod" value="bodId" />
				</html:select>
			</td>
			<td>
				<label><bean:message key="lbl.mi.bodega2" />:</label>
			</td>
			<td>
				<html:select property="bodId2" styleId="bodId2"
					styleClass="obligatorio" > 
					<html:optionsCollection label="bodNombre" name="lBod" value="bodId" />
				</html:select>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label><bean:message key="lbl.mi.articulo" />:</label>
			</td>
		</tr>
		<tr>
			<td><label>
				<bean:message key="lbl.mi.artCodigo" />:</label>
			</td>
			<td>
				<html:text property="artCodigo" styleClass="obligatorio"
					size="15" styleId="artCodigoId" maxlength="80" />
			</td>
			<td><label>
				<bean:message key="lbl.mi.artNombre" />:</label>
			</td>
			<td>
				<html:text property="artNombre" styleClass="obligatorio"
					styleId="artNombreId" size="15" maxlength="150"  value="${form.artNombre}" />
				<input type="button" value="..."
					onclick="ajaxCallNormal('${pageContext.request.contextPath}/inventario${_accion}.do',
						'accion=cargarListaArticulos&artNombre='+$('#artNombreId').val()+
						'&artCodigo='+$('#artCodigoId').val()+
						'&bodId1='+$('#bodId1').val(),'articulos')" />
			</td>
		</tr>
		<tr>
			<td><label>
				<bean:message key="lbl.mi.cantidad" />:</label>
			</td>
			<td>
				<html:text property="cantidad" styleClass="obligatorio"
					size="15" styleId="cantidadId" maxlength="15" />
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<div id ="articulos">
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				<html:submit property="accion" onclick="mover();" styleId="moverId">
					<bean:message key="cmd.mi.mover"/>
				</html:submit>
				<input type="button" value="Limpiar" onclick="cleanFields('formId');">
			</td>
		</tr>
	</table>
<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>	
</html:form>
