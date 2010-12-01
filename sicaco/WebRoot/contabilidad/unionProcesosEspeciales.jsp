<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
<!--
		$(document).ready(function(){
			$("#proceso").val("-1")
			$("#otro").hide()
			$("#tah").hide()
			$("#empTrab").hide()
			$("#pagoCuota").hide()
			$("#conceptoExtra").val('')
	});
	
	function habilitarConcepto(){
		if($("#otroConcepto").val()==-1){
			$("#otro").fadeIn()
		}else{
			$("#otro").fadeOut()
		}
	}
	
		function habilitar2(){
		if($("#proceso").val()== 7 || $("#proceso").val()== 8){
			$("#conceptoUno").fadeOut()
			$("#otro").fadeIn()
			$("#otroConcepto").val("-1")
		}else{
			$("#conceptoUno").fadeIn()
			$("#otro").fadeOut()
			$("#otroConcepto").val("0")
		}
		if($("#proceso").val()== 9){
			$("#tah").fadeIn()
		}else{
			$("#tah").fadeOut();
		}
		if($('#proceso').val()==8){
			$('#empTrab').fadeIn()
		}else{
			$('#empTrab').fadeOut()
		}
		if($('#proceso').val()==5){
			$('#pagoCuota').fadeIn()
		}else{
			$('#pagoCuota').fadeOut()
		}		
	}

//-->
</script>
<html:form action="${_accion}" method="post" styleId="formId">
	<label>
		<bean:message key="lbl.proCon.procesoEspecial" />
	</label>
	<hr>
	<table align="center">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.proCon.procesoEspecial" />
				</label>
			</td>
			<td>
				<html:select property="parametro[0]" styleClass="obligatorio" onchange="habilitar2()" styleId="proceso">
					<html:option value="-1" >--Seleccione un proceso especial--</html:option>
					<html:option value="5" ><bean:message key="lbl.proCon.ingresoAsociados" /></html:option>
					<html:option value="6" ><bean:message key="lbl.proCon.distroDividendos" /></html:option>
					<html:option value="7" ><bean:message key="lbl.proCon.liquidAsociado" /></html:option>
					<html:option value="8" ><bean:message key="lbl.proCon.planillaRetDeMas" /></html:option>
					<html:option value="9" ><bean:message key="lbl.proCon.multaAhorros" /></html:option>
					<html:option value="11" ><bean:message key="lbl.proCon.cuotaIngreso" /></html:option>
				</html:select>
			</td>
		</tr>

		<tr id="pagoCuota"> 
			<td>
				<label>
					<bean:message key="lbl.proCon.pagoCuota" />
				</label>
			</td>
			<td>
				<html:select property="parametro[2]" styleClass="obligatorio">
					<html:option value="1">S&iacute;</html:option>
					<html:option value="2">No</html:option>
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
				<html:select property="parametro[2]" styleClass="obligatorio">
					<html:option value="-1">-- Seleccione una empresa de trabajo --</html:option>
					<html:optionsCollection name="empList" value="etrId"
						label="etrNombre" />
				</html:select>
			</td>
		</tr>
		
		<tr id="tah">
			<td>
				<label>
					<bean:message key="lbl.proCon.tipoAhorro" />
				</label>
			</td>
			<td>
				<html:select property="parametro[1]" styleClass="obligatorio">
					<html:option value="-1" >--Seleccione un tipo de ahorro--</html:option>
					<html:optionsCollection name="lahorros" label="tahNombre" value="tahId"/>
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
		<tr id="conceptoUno">
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
				<html:text property="cxaConceptoExtra" styleClass="obligatorio" styleId="conceptoExtra"/>
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
			<bean:message key="cmd.proCon.guardar" />
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
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n de Relacion Inventario - Procesos Especiales!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea eliminar esta relaci&oacute;n ?'+'<br>'+'</p>';	
			$.prompt(msgText,{
		    buttons:{Ok:true,Cancel:false}, 
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