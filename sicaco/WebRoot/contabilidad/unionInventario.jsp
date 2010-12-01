<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
<!--
		$(document).ready(function(){
			$("#otro").hide();
			$("#excento").hide();
			$("#cliente").hide();
	});
	
	function habilitarConcepto(){
		if($("#otroConcepto").val()==-1){
			$("#otro").fadeIn();
		}else{
			$("#otro").fadeOut();
		}
	}
	
	function obtenerTipos(){
		var id1=0
		var id2=0
		var valor = $('#tipoFactura').val();
		switch(valor){
			case 'CO': id1=2;id2=4;/*$("#excento").fadeOut();$("#cliente").fadeOut();*/break;
			case 'VC': id1=2;id2=4;/*$("#excento").fadeIn();$("#cliente").fadeIn();*/break;
			case 'CR': id1=1;id2=3;
						/*$("#excento").fadeOut();$("#cliente").fadeOut();*/	
						break;
			case 'CR': id1=1;id2=3;/*$("#excento").fadeOut();$("#cliente").fadeOut()*/;break;
			case 'NC': id1=7;id2=7;/*$("#excento").fadeOut();$("#cliente").fadeOut()*/;break;
			case 'ND': id1=8;id2=8;/*$("#excento").fadeOut();$("#cliente").fadeOut()*/;break;
		}
		ajaxCallNormal('${pageContext.request.contextPath}/contabilidad/relacionInventario.do','accion=findData&id1='+id1+'&id2='+id2,'respuesta');
	}
//-->
</script>

<html:form action="${_accion}" method="post" styleId="formId">
	<label><bean:message key="lbl.mxinv.facturacion" /></label>
	<hr>
	<table align="center">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.mxinv.tipoFactura" />
					:
				</label>
			</td>
			<td>
				<html:select property="parametro[1]" styleClass="obligatorio" styleId="tipoFactura" onchange="obtenerTipos()" style="width: 350px">
					<option value="-1">--Seleccione un Tipo de Factura</option>
					<html:option value="CO"><bean:message key="lbl.mxinv.consumidorFinal" /></html:option>
					<html:option value="CR"><bean:message key="lbl.mxinv.creditoFiscal" /></html:option>
					<html:option value="VC"><bean:message key="lbl.mxinv.ventaContribuyentes" /></html:option>
					<html:option value="ND"><bean:message key="lbl.mxinv.notaDebito" /></html:option>
					<html:option value="NC"><bean:message key="lbl.mxinv.notaCredito" /></html:option>
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<label class="obligatorio">
					<bean:message key="lbl.mxinv.usoFactura" />
					:
				</label>
			</td>
			<td>
				<div id="respuesta">
				<html:select property="parametro[2]" styleClass="obligatorio" style="width: 350px">
					<option value="-1">--Seleccione un Tipo de Factura</option>
				</html:select>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.mxinv.estadoFactura" />
					:
				</label>
			</td>
			<td>
				<html:select property="parametro[3]" styleClass="obligatorio" styleId="estadoFactura" style="width: 350px">
					<option value="-1">--Seleccione un estado de factura--</option>
					<html:option value="1">Impresa</html:option>
					<html:option value="2">Anulada</html:option>
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.mxinv.tipoDePago" />
					:
				</label>
			</td>
			<td>
				<html:select property="parametro[5]" styleClass="obligatorio" styleId="tipoPago" style="width: 350px">
					<option value="-1">--Seleccione un tipo de pago--</option>
					<html:option value="E">Efectivo</html:option>
					<html:option value="C">Credito</html:option>
					<!-- <html:option value="N">Ninguno</html:option> -->
				</html:select>
			</td>
		</tr>
		<tr><td></td></tr>
		<tr id="cliente">
			<td>
				<label style="color: gray">
					Cliente
					:
				</label>
			</td>
			<td>
				<html:select property="parametro[8]" styleClass="obligatorio" styleId="relacionar" disabled="true"  style="width: 350px" >
					<html:option value="0"> Varios </html:option>
					<html:option value="1">Si</html:option>
				</html:select>
			</td>
		</tr>
		 
		<tr  id="excento">
			<td>
				<label>
					Excento
					:
				</label>
			</td>
			<td>
				<html:select property="parametro[7]" styleClass="obligatorio" styleId="excentos" style="width: 350px">
					<html:option value="0">No</html:option>
					<html:option value="1">Si</html:option>
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.mxinv.relacionValor" />
					:
				</label>
			</td>
			<td>
				<html:select property="parametro[4]" styleClass="obligatorio" styleId="relacionar">
					<html:option value="0">-- Seleccione una valor de factura --</html:option>
					<html:option value="1">Valor Total de Factura</html:option>
					<html:option value="2">Valor Sin Iva de Factura</html:option>
					<html:option value="3">Valor Iva de Factura </html:option>
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.mxinv.relacionarCosto" />
					:
				</label>
			</td>
			<td>
				<html:select property="parametro[6]" styleClass="obligatorio" styleId="relacionar">
					<html:option value="0">No</html:option>
					<html:option value="1">Si</html:option>
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
					<bean:message key="lbl.mxinv.conceptoCuenta" />
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
					<bean:message key="lbl.mxinv.otroConceptoCuenta" />
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
			<bean:message key="cmd.mxinv.guardar" />
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
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n de Relacion Inventario - Contabilidad!!</h3>'+
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