<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<script type="text/javascript">
	function saveSeleccionA(valor) {
		valores = valor.split(';')
		$('#ascCodigoId').val(valores[0])
		$('#ascNombreId').val(valores[1])
		$('#ascId').val(valores[2])
		$('#resultadoAsc').hide('slow')
	} 
	
	function selected(){
		var chk = $("#chkbx")[0];
		if(chk.checked == true){
			$("#ascEmpId").val(2);
		}else{
			$("#ascEmpId").val(1);
		}
	}
	
</script>

<html:form action="${_accion}" method="post" styleId="formId">

	<table border="0" align="center">
		<tr>
			<td colspan="2">
				<label>
					<bean:message key="lbl.ordcom.asociado" />
					:
				</label>
				<input type="checkbox" name="chkbx" id="chkbx" onclick="selected();" />
				<label>
					<bean:message key="lbl.ordcom.codEmp" />
				</label>
				<html:hidden property="ascEmp" styleId="ascEmpId" value="1" />
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.ordcom.secAscAsociado.Id.ascCodigo" />
					:
				</label>
			</td>
			<td>
				<html:text property="ascCodigo3" size="15" maxlength="12"
					styleClass="obligatorio" value="" styleId="ascCodigoId" />
				<span><bean:message key="msg.obligatorio" /> </span> &nbsp;
			</td>
			<td>
				<label>
					<bean:message key="lbl.ordcom.ascNombre" />
					:
				</label>
			</td>
			<td>
				<html:text property="ascNombre" size="40" maxlength="100"
					styleClass="obligatorio" value=""
					styleId="ascNombreId" />
				<input type="button" value="..."
					onclick="ajaxCallNormal('${pageContext.request.contextPath}/control/peticionesCompra.do',
							'accion=cargarListaAsociados&ascCodigo='+$('#ascCodigoId').val()+
							'&ascNombre='+$('#ascNombreId').val() +
							'&ascEmp='+$('#ascEmpId').val(),'asociados')" />
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<div id="asociados">
				</div>
			</td>
		</tr>		
		<tr>
			<td>
				<label>
					<bean:message key="cmd.peticionesCompra.entrega" bundle="cliente"/>
					:
				</label>
			</td>
			<td>
				<html:select property="sucId">
				<html:option value="-1">-Seleccion una sucursal-</html:option>
					<html:optionsCollection name="sucs" value="sucId"
						label="sucNombre" />
				</html:select>
			</td>
			<td>
				<label>
					<bean:message key="cmd.ordcom.cargaPro" />
					:
				</label>
			</td>
			<td>
				<html:select property="proId">
				<html:option value="-1">-Seleccion un Proveedor-</html:option>
					<html:optionsCollection name="provs" value="proId"
						label="proNombre" />
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<label><bean:message key="lbl.peticionesCompra.fecha" bundle="cliente"/>:</label>
			</td>
			<td>
				<html:text style="float:left;" styleId="fechaSolicitud"
					styleClass="obligatorio" onkeyup="mask(this);"
					property="fechaSolicitud" maxlength="10" size="10" />
				<input style="height: 18px;" id="button_fechaSolicitud" type="button"
					value="..." />
				<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
				<script type="text/javascript">
		         		 Calendar.setup({
		           			inputField    : "fechaSolicitud",
		             		button        : "button_fechaSolicitud",
		            		align         : "Br"
		            	 });
		    		</script>

			</td>
			<td>
			</td>
			<td>
			</td>
		</tr>						
	</table>
	<table align="center">
		<tr>
			<td>
				<html:submit property="accion" onclick="$('#pageId').val('0');">
					<bean:message key="cmd.peticionesCompra.buscar" bundle="cliente"/>
				</html:submit>
			</td>
			<td>
				<input type="button" value="Limpiar"
					onclick="cleanFields('formId');$('#ascId').val('')">
			</td>
		</tr>	
	</table>
	
	
	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
	<input class="exclude" type="hidden" name="pecId" id="pecId"/>
	<html:hidden property="ascCodigo" styleId="ascId" />		
</html:form>
<script type="text/javascript">
	function handlerDeleteButton(pecId){
		$('#pecId').val(pecId);
		var msgText = '<h3 style="color: red;">Eliminar Petici&oacute;n</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Est&aacute; completamente seguro que desea eliminar esta petici&oacute;n ?'+'<br>'+'</p>';	
			$.prompt(msgText,{
		    buttons:{Ok:true,Cancel:false}, 
		    prefix:'brownJqi',
		    callback : function(v,m){
		    	if(v == true){
		    		$('#accionId').val('eliminarPeticion');
					$('#formId')[0].submit();
		    	}
		    }
		});
	}
		
</script>
