<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<link rel="stylesheet" type="text/css" href="../css/zebra.css" >

<script type="text/javascript">
	$(document).ready(function(){
    	$("#tisNombreId").alphanumeric({allow:" "});
    	$("#tisPolizaId").alphanumeric({allow:" -"});
    	$("#tisDescripcionId").alphanumeric({allow:" -.;,()"});
  });
  
</script>

<html:form action="${_accion}" method="post"  styleId="formId"  enctype="multipart/form-data">
<table  id="hor-zebra" style=" width:auto; margin: auto;" align="center">
	<thead>
	 <tr>
	 <th align="center" colspan="2">Datos de Seguro y Archivo</th>
	 </tr>
	</thead>
	<tbody>
	<tr class="odd">
		<td >
			<label class="obligatorio"><bean:message key="lbl.cAPre.empleadoOAsociado"/></label>:
		</td>
		<td>
			<html:select property="empOAsoc" style="width: 150px;">
				<html:option value="e">Empleado</html:option>
				<html:option value="a">Asociado</html:option>						
			</html:select>
			<span>*</span>
		</td>
	</tr>
	<tr>
		<td >
			<label><bean:message key="lbl.autoSeguros.tisId" /></label>:
		</td>
		<td>
			<html:select property="tisId" value="${form.tisId}" styleClass="obligatorio" styleId="tipoId" size="1" style="width: 150px;">
				<html:option value="">...</html:option>
				<html:optionsCollection  label="tisNombre" name="lstTis" value="tisId"/>     					
			</html:select>
		</td>
	</tr>
	<tr class="odd">
		<td >
			<label class="obligatorio"><bean:message key="lbl.autoSeguros.tinId" /></label>:
		</td>
		<td>
			<html:select property="tinId" value="${form.tinId}" styleClass="obligatorio" styleId="tipoId" size="1" style="width: 150px;">
				<html:option value="">...</html:option>
				<html:optionsCollection  label="tinNombre" name="lstTin" value="tinId"/>     					
			</html:select>
		</td>
	</tr>
	<tr>
		<td>
			<label class="obligatorio"><bean:message key="lbl.planilla.cargarArchivo" /></label>:
		</td>
	
		<td>
			<html:file property="archivo" styleId="archivoId" />
		</td>
	</tr>
	<tr >
		<td>
			<label class="obligatorio" >No. Coutas</label>:
		</td>
	
		<td>
			<select name="noCuotas">
				<option value="1"> 1 </option>
				<option value="2"> 2 </option>
				<option value="3"> 3 </option>
				<option value="4"> 4 </option>
				<option value="5"> 5 </option>
				<option value="6"> 6 </option>
				<option value="7"> 7 </option>
				<option value="8"> 8 </option>
				<option value="9"> 9 </option>
				<option value="10"> 10 </option>
				<option value="11"> 11 </option>
				<option value="12" selected="selected"> 12 </option>
			</select>
		</td>
	</tr>
	</tbody>
	<tfoot>
	<tr class="odd foot">
		<td colspan="2" align="right">
			<input type="button" value="<bean:message key="lbl.autoSeguros.cargar" />" onclick="handlerCambiarButton();">
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
	</tfoot>
	
</table> 
<input id="pageId" type="hidden" name="page" value="3">

	<input class="exclude" type="hidden" name="tisId2" id="tisId2" value=""/>
  	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>

 <script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerCambiarButton(){
		var msgText = '<h3 style="color: red;">!! Cargo Automatico de Seguros !!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea realizar el ingreso automatico de Seguros ?'+'<br>'+'</p>';	
			$.prompt(msgText,{
		    buttons:{Ok:true,Cancel:false}, 
		    prefix:'brownJqi',
		    callback : function(v,m){
		    	if(v == true){
		    		$('#accionId').val('cargar');
		    		$('#pageId').val('0');
					$('#formId')[0].submit();
		    	}
		    }
		});
	}
	
</script>
