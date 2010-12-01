<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
<!--
		$(document).ready(function(){
			$("#otro").hide();
	});
	
	function habilitarConcepto(){
		if($("#otroConcepto").val()==-1){
			$("#otro").fadeIn();
		}else{
			$("#otro").fadeOut();
		}
	}

	function getProveedores(){
			ajaxCallNormal('${pageContext.request.contextPath}/contabilidad/relacionOrdenPago.do','accion=findData&parametro[3]='+$("#tipoProveedor").val(),'prov');
	}
//-->
</script>

<html:form action="${_accion}" method="post" styleId="formId">
	<label>
		<bean:message key="lbl.mxPag.orden" />
	</label>
	<hr>
	<table align="center">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.mxPag.tipoProveedor" />
				</label>
			</td>
			<td>
				<html:select property="parametro[3]" styleClass="obligatorio"
					styleId="tipoProveedor" onchange="getProveedores();">
					<html:option value="-1" >--Seleccione un tipo--</html:option>
					<html:optionsCollection name="tiposProv" value="tprId"
						label="tprNombre" />
				</html:select>
			</td>
		</tr>
		<tr>
		<td>
			<label class="obligatorio"><bean:message key="lbl.mxPag.proveedor" /></label>
		</td>
			<td>
				<div id="prov">
				</div>
			</td>
		<tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.mxPag.estadoOrden" />
				</label>
			</td>
			<td>
				<html:select property="parametro[1]" styleClass="obligatorio"
					styleId="estadoOrden">
					<html:option value="-1">-- Seleccione un estado de orden --</html:option>
					<html:option value="P">Pagada</html:option>
					<!-- <html:option value="A">Anulada</html:option> -->
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.mxPag.relacionDescuento" />
				</label>
			</td>
			<td>
				<html:select property="parametro[2]" styleClass="obligatorio"
					styleId="tipoPago">
					<html:option value="0">No</html:option>
					<html:option value="1">Si</html:option>
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.mxPag.tipoPago" />
				</label>
			</td>
			<td>
				<html:select property="parametro[5]" styleClass="obligatorio"
					styleId="tipoPago">
					<html:option value="E">Efectivo</html:option>
					<html:option value="C">Cheque</html:option>
					<html:option value="N">Pago Electr&oacute;nico</html:option>
				</html:select>
			</td>
		</tr>
	</table>
	<label>
		<bean:message key="lbl.mxcc.Contabilidad" />
	</label>
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
					<bean:message key="lbl.mxPag.conceptoCuenta" />
				</label>
			</td>
			<td>
				<html:select property="conCpaConceptoPartida.cpaId" styleClass="obligatorio" styleId="otroConcepto" onchange="habilitarConcepto();">
					<option value="-2">--Seleccione un concepto--</option>
					<html:optionsCollection name="conceptos" value="cpaId" label="cpaConcepto" />
						<html:option value="-1">Otro</html:option>
				</html:select>
			</td>
		</tr>
		<tr id="otro">
			<td>
				<label>
					<bean:message key="lbl.mxPag.otroConceptoCuenta" />
				</label>
			</td>
			<td>
				<html:text property="cxaConceptoExtra" styleClass="obligatorio"/>
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
			<bean:message key="cmd.mxPag.guardar" />
		</html:submit>
		<input type="button" onclick="$('#otro').hide();cleanFields('formId');" value="Limpiar">
		</td>
		</tr>
	</table>
			<input class="exclude" type="hidden" name="accion" id="accionId"/>
	<html:hidden property="cxcId" styleId="cxcId"/>
</html:form>
<script type="text/javascript">
	 var deleteButton = 1;
	function handlerDeleteButton(cxcId){
		$('#cxcId').val(cxcId);
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n de Relacion Orden de Pago - Contabilidad!!</h3>'+
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