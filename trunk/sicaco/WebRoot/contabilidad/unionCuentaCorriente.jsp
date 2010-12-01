<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript">
<!--
	$(document).ready(function(){
			$('#empTrab').hide()
	});
	
	function mostrar_empresa(){
		if($('#ttr').val()==12){
			$('#empTrab').fadeIn()
		}else{
			$('#empTrab').fadeOut()
		}
	}
	
	function obtenerTipos(){
		ajaxCallNormal('${pageContext.request.contextPath}/contabilidad/relacionCuentaCorriente.do','accion=findData&parametro[1]='+$("#tipoCuenta").val(),'respuesta');
	}
	
	function comprobarTipos(){
		if($('#tipoCuenta').val()!= 2 && $('#tipoCuenta').val()!= 3){
			$('#par4').val(0)
		}
	}

//-->
</script>
<html:form action="${_accion}" method="post" styleId="formId">
<label><bean:message key="lbl.mxcc.cuentaCorr" /></label>
	<hr>
	<table align="center">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.mxcc.tipoCuenta" />
					:
				</label>
			</td>
			<td>
				<html:select property="parametro[1]" styleClass="obligatorio"
					styleId="tipoCuenta" onchange="obtenerTipos();comprobarTipos();">
					<html:option value="0">-- Seleccione el Tipo de Cuenta Corriente --</html:option>
					<html:option value="1">Aportacion</html:option>
					<html:option value="2">Ahorro</html:option>
					<html:option value="3">Prestamo</html:option>
					<html:option value="4">Seguro</html:option>
					<html:option value="5">Bancos</html:option>
				</html:select>
			</td>
		</tr>
	</table>
	<div id="respuesta">
	</div>
	<table align="center">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.mxcc.tipoTransaccion" />
				</label>
			</td>
			<td>
				<html:select property="parametro[3]" styleClass="obligatorio" styleId="ttr" onchange="mostrar_empresa()">
					<html:option value="0">-- Seleccione un tipo de transaccion --</html:option>
					<html:optionsCollection name="ttrList" value="ttrId"
						label="ttrNombre" />
				</html:select>
			</td>
		</tr>
		<tr id="empTrab"> 
			<td>
				<label>
					<bean:message key="lbl.mxcc.empresa" />
				</label>
			</td>
			<td>
				<html:select property="parametro[5]" styleClass="obligatorio">
					<html:option value="-1">-- Seleccione una empresa de trabajo --</html:option>
					<html:optionsCollection name="empList" value="etrId"
						label="etrNombre" />
				</html:select>
			</td>
		</tr>
		</table>
		<label><bean:message key="lbl.mxcc.Contabilidad" /></label>
		<hr>
		<table align="center">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.mxcc.cuentaContable" />
				</label>
			</td>
			<td>
				<html:select property="conCueCuenta.cueId" styleClass="obligatorio">
				<html:option value="-1" >--Seleccione una cuenta contable--</html:option>
					<html:optionsCollection name="cuentasContables" value="cueId"
						label="cueNombre" />
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.mxcc.accion" />
				</label>
			</td>
			<td>
				<html:select property="cxcCargoAbono" styleClass="obligatorio">
					<html:option value="-1">-- Seleccione una Accion --</html:option>
					<html:option value="1">Cargo</html:option>
					<html:option value="0">Abono</html:option>
				</html:select>
			</td>
		</tr>
	</table>
	<table align="center">
	<tr>
	<td>
		<html:submit property="accion">
			<bean:message key="cmd.mxcc.guardar" />
		</html:submit>
		<input type="button" onclick="cleanFields('formId');" value="Limpiar">
		</td>
		</tr>
	</table>
	<input class="exclude" type="hidden" name="accion" id="accionId"/>
	<html:hidden property="cxcId" styleId="cxcId"/>
	<html:hidden property="parametro[4]" styleId="par4"/>
</html:form>
<script type="text/javascript">
	 var deleteButton = 1;
	function handlerDeleteButton(cxcId){
		$('#cxcId').val(cxcId);
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n de Relacion Cuenta Corriente - Contabilidad!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea eliminar esta relaci&oacute;n ?'+'<br>'+'</p>';	
			$.prompt(msgText,{
		    buttons:{Ok:true,Cancelar:false}, 
		    prefix:'brownJqi',
		    callback : function(v,m){
		    	if(v == true){
		    	$('#accionId').val('eliminar')
		    		$('#pageId').val('0');
					$('#formId')[0].submit();
		    	}
		    }
		});
	}
</script>