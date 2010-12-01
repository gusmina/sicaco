 <%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<link rel="stylesheet" type="text/css" href="../css/zebra.css" >

<script type="text/javascript">
$(document).ready(function(){
	$("#pcoValorDebeId").val(round_number( $("#pcoValorDebeId").val(),2));
	$("#pcoValorHaberId").val(round_number( $("#pcoValorHaberId").val(),2));
	$("#pcoValorDebeId").numeric({allow:"."});
	$("#pcoValorHaberId").numeric({allow:"."});
	//esconde o muestra el contenido del div
	$(".msg_head").click(function(){
		$(this).next(".msg_body").slideToggle(425);
	});
	
	if($("#tipoPartidaH").val()==2){
		$("#div2").show();
		$("#divListaBanco").show();
		$("#trChequeNeg").show();
		
	}else{
		$("#div2").hide();
		$("#divCorrelativoCheque").hide();
		$("#divListaBanco").hide();
		$("#trChequeNeg").hide();
	}
	
	if($("#conceptoPartidaId").val()==-1){
		$("#divOtroConcepto").show();
	}else{
		$("#divOtroConcepto").hide();
	}
	var valorLstCde =	$("#cpaConceptoDetalleId").val();
	if(valorLstCde==-1){
		$("#divOtroConceptoD").show();
	}else{
		$("#divOtroConceptoD").hide();
	}
	
	if($('#nuevoComprobante').val()!=0){
		$('#div1').remove();
		$('#div2').remove();
		$('#div3').remove();
		$('#trChequeNeg').remove();
	}
});

function ocultarListaBanco(){
	var valorLstCpa =	$("#tipoPartidaId").val();
	if(valorLstCpa==2){
		$("#div2").show();
		$("#divListaBanco").show();
		$("#trChequeNeg").show();
		
	}else{
		$("#div2").hide();
		$("#divCorrelativoCheque").hide();
		$("#divListaBanco").hide();
		$("#trChequeNeg").hide();
	}
}
function ocultar(){
	var valorLstCpa =	$("#conceptoPartidaId").val();
	if(valorLstCpa==-1){
		$("#divOtroConcepto").show();
	}else{
		$("#divOtroConcepto").hide();
	}
}
function ocultarD(){
	var valorLstCde =	$("#cpaConceptoDetalleId").val();
	if(valorLstCde==-1){
		$("#divOtroConceptoD").show();
	}else{
		$("#divOtroConceptoD").hide();
	}
}

function toggleValores(habilitado,inhabilitado){
	inhabilitado.val("");
	inhabilitado.attr('readonly','readonly');
	habilitado.removeAttr('readonly','readonly');
}
<!--
	$(document).ready(function() {
	$("#cpaConcepto").removeAttr("disabled");
	$("#combo").attr("disabled","disabled");
		$("#ttr").click(function() {
 				$("#combo").removeAttr("disabled");
       			$("#cpaConcepto").attr("disabled","disabled");
       	});	
		
		$("#concept").click(function() {
				$("#cpaConcepto").removeAttr("disabled");
       			$("#combo").attr("disabled","disabled");
		});
		
	});
//-->
</script>
<style type="text/css">
p {
	padding: 0 0 1em;
}

.msg_head {
	padding: 5px 10px;
	cursor: pointer;
	position: relative;
	background-color: #CACBDF;
	margin: 1px;
}

.msg_body {
	padding: 5px 10px 15px;
	background-color: auto;
}
</style>

<html:form action="${_accion}" method="post" styleId="formId">
	<p class="msg_head">
		<bean:message key="lbl.pco.datosPartida" />
	</p>
	<div class="msg_body">
		<table border="0" align="center">
			<tr>
				<td>
					<label>		<bean:message key="lbl.pco.comprobantePartida" />	</label>
					<label style="color: green;"> ${form.pcoComprobantePartida}		</label>
				</td>
				<td>
					<logic:equal name="partidaContableForm" value="true" property="mdf">
					  	<label>	<bean:message key="lbl.pco.nuevoComprobantePartida" /></label>
						<html:text  maxlength="4" size="5" property="nuevoComprobante" value="${form.nuevoComprobante}" styleId="nuevoComprobanteId"/>
					</logic:equal>
				</td>
			</tr>
			
			<tr>
				<td>
					<label><bean:message key="lbl.pco.fechaIngreso" /></label>:
				</td>
				<td>
					<html:text style="float:left;" styleId="fechaIngresoId" onkeyup="mask(this);" value="${form.pcoFechaIngresoPartida}" property="pcoFechaIngresoPartida" maxlength="10" size="10"/>
		        	<input  style="height: 18px;" id="button_fechaIngresoId" type="button"  value="..."/>
      	    			<script type="text/javascript">
			        	    Calendar.setup({
			            	  inputField    : "fechaIngresoId",
			              	button        : "button_fechaIngresoId",
			            	  align         : "Br"
			           		});
			    		</script>
				</td>
			</tr>
			<tr>
				<td colspan="2"><label>
					<bean:message key="lbl.pco.listaConceptos" /></label>
				
					<html:select property="cpaID"  value="${form.cpaID}"
								styleId="conceptoPartidaId"
						 		styleClass="obligatorio"
								onchange="ocultar()">
	   					<html:option value="-1">Otro Concepto</html:option>
	   					<html:optionsCollection  label="cpaConcepto" name="conCpaConceptoPartida" value="cpaId"/>     					
					</html:select>
				</td>
			</tr>
			
			<tr>	
					<td colspan="2" align="right"> 
						<div id="divOtroConcepto">
							<label>	<bean:message key="lbl.pco.otroConcepto" /></label>	
							<html:text  maxlength="200" size="60" property="pcoOtroConcepto" value="${form.pcoOtroConcepto}" styleId="pcoOtroConceptoId"/>
						</div>
					</td>
			</tr>
			<tr id="div1">
				<td>	<label>	<bean:message key="lbl.pco.tipoPartida" />	</label>	</td>
				<logic:equal name="partidaContableForm" value="false" property="mdf">
					<td>
						<html:select property="conTpaTipoPartida.tpaId" value="${form.conTpaTipoPartida.tpaId}" styleId="tipoPartidaId" onchange="ocultarListaBanco()" >
	   						<html:optionsCollection  label="tpaNombre" name="conTpaTipoPartida" value="tpaId"/>     					
						</html:select>
					</td>
				</logic:equal>
				<logic:equal name="partidaContableForm" value="true" property="mdf">
					<td>
						<html:select property="conTpaTipoPartida.tpaId" disabled="true" value="${form.conTpaTipoPartida.tpaId}" styleId="tipoPartidaId" onchange="ocultarListaBanco()" >
	   						<html:optionsCollection  label="tpaNombre" name="conTpaTipoPartida" value="tpaId"/>     					
						</html:select>
					</td>
				</logic:equal>		
			</tr>
			<tr id="div2">
				<td colspan="2">
				<table 
				align="center"
				style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
				font-size: 13px;
				margin-bottom: 20px;
				margin-top:20px;
				margin-left: auto;
				margin-right: auto;
				text-align: left;
				border-collapse: collapse;
				padding: 10px 10px 10px 10px;" id="hor-zebra"
				>
					<tr>
						<th colspan="2"><label>	<bean:message key="lbl.pco.datosCheque" /></label></th>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<div id="divListaBanco">
								<label><bean:message key="lbl.pco.bancoCheque" />:</label>
					
								<logic:equal name="partidaContableForm" value="true" property="mdf">
										<html:select property="repositorioCheques.rckId" disabled="true" value="${form.repositorioCheques.rckId}" styleId="listaDeBancosId"
													onchange="ajaxCallNormal('${pageContext.request.contextPath}/contabilidad${_accion}.do','accion=cargarCorrelativoCheque&repositorioCheques.rckId='+$('#listaDeBancosId').val(),'divCorrelativoCheque')">
			   										<html:option value="-1">-Seleccione una Cuenta-</html:option>
			   										<html:optionsCollection  label="rckNombre" name="ctrBanBancoList" value="rckId"/>     					
										</html:select>
								</logic:equal>
								<logic:equal name="partidaContableForm" value="false" property="mdf">
										<html:select property="repositorioCheques.rckId"  value="${form.repositorioCheques.rckId}" styleId="listaDeBancosId"
													onchange="ajaxCallNormal('${pageContext.request.contextPath}/contabilidad${_accion}.do','accion=cargarCorrelativoCheque&repositorioCheques.rckId='+$('#listaDeBancosId').val(),'divCorrelativoCheque')">
			   										<html:option value="-1">-Seleccione una Cuenta-</html:option>
			   										<html:optionsCollection  label="rckNombre" name="ctrBanBancoList" value="rckId"/>     					
										</html:select>
									
								</logic:equal>
								</div>
								</td>
							
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">
							<div id="divCorrelativoCheque">
								<label ><bean:message key="lbl.pco.correlativoCheque" property="chkCorrelativoCheque"/>
								${form.chkCorrelativoCheque}</label>
							</div>
						</td>
					</tr>
					<tr>
						<td>	<label><bean:message key="lbl.pco.chkEmitidoA"/>:	</label>	</td>
						<td><html:text styleId="emitidoAId" value="${form.chkEmitidoA}" property="chkEmitidoA" size="40"/>	</td>
					</tr>
					<tr>
						<td>	<label><bean:message key="lbl.pco.chkFecha"/>:</label>		</td>
						<td>
							<html:text style="float:left;"  styleId="chkFechaId" onkeyup="mask(this);" value="${form.chkFecha}" property="chkFecha" maxlength="10" size="10"/>
				        	<input  style="height: 18px;"  id="button_chkFechaId" type="button"  value="..."/>
			           		<script type="text/javascript">
					        	    Calendar.setup({
					            	  inputField    : "chkFechaId",
					              	button        : "button_chkFechaId",
					            	  align         : "Br"
					           		});
					    	</script>
						</td>
					</tr>
					<tr>
						<td><label><bean:message key="lbl.pco.chkLugar"/>:</label>		</td>
						<td><html:text styleId="chkLugarId"  value="${form.chkLugar}" property="chkLugar" size="40"/>	</td>		
			        </tr>
					<tr>
						<td>	<label><bean:message key="lbl.pco.chkMontoEmitido"/>:</label>	</td>
						<td>	<html:text styleId="chkMontoEmitidoId" value="${form.chkMontoEmitido}" property="chkMontoEmitido" size="15"/>
		        		</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">
							<html:checkbox property="pcoChequeNegociable" value="1"/>
								<label><bean:message key="lbl.pco.chequeNegociable"/></label>
							
						</td>
					</tr>	
				</table>
				</td>
			</tr>
		<logic:equal name="partidaContableForm" value="true" property="mdf">
			<tr>
				<td colspan="2">
					<html:checkbox property="pcoEstado" value="A" style="font-family: Arial,Helvetica,sans-serif;	font-size: 12px;">
						<label><bean:message key="lbl.pco.anularPartida"/></label>
					</html:checkbox>
				</td>
			</tr>
		</logic:equal>
		</table>
					
	</div>
	
	<%-- Datos Detalle Partida --%>
	<p class="msg_head">
		<bean:message key="lbl.pco.datosDetallePartida" />
	</p>
	<div class="msg_body">
		<table align="center">
			<tr>
				<td>
					<label>
						<bean:message key="lbl.cpo.cuentaContable" />
					</label>
				</td>
				<td>
					<html:select property="conCueCuenta.cueId"
						styleClass="obligatorio" styleId="cuentaContableId">
						<html:optionsCollection name="lstConCueCuenta" value="cueId"
							label="cueNombre" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td><label>
					<bean:message key="lbl.pco.listaConceptosDetalle" /></label>
				</td>
				<td>
					<html:select property="conCpaConceptoPartidaD.cpaId" value="${form.conCpaConceptoPartidaD.cpaId}"								
								styleId="cpaConceptoDetalleId"
						 		styleClass="obligatorio"
								onchange="ocultarD()">
	   					<html:option value="-1">Otro Concepto</html:option>
	   					<html:optionsCollection  label="cpaConcepto" name="conCpaDetallePartida" value="cpaId"/>     					
					</html:select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="divOtroConceptoD">
						<label>	<bean:message key="lbl.pco.otroConcepto" /></label>
						<html:text  maxlength="200" size="60" property="dpaOtroConcepto" value="${form.dpaOtroConcepto}" styleId="dpaOtroConceptoId"/>
						<div id="divOtroConceptoD">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><label>Tipo de Operacion:</label>
				</td>
			</tr>
			<tr>
				<td>
					<html:radio property="boolCargoAbono" value="true" styleId="cargo" onclick="toggleValores($('#pcoValorDebeId'),$('#pcoValorHaberId'));">
						<label>Cargo</label>
					</html:radio>
				</td>
				<td id="valorDebe">
					<label>
						<bean:message key="lbl.cpo.valorDebe" />
					</label>
					<html:text style="numeric" maxlength="10" size="7" property="dpaValorDebe" onkeyup="dosDecimales($('#pcoValorDebeId').val(),'pcoValorDebeId');"
								value="${form.dpaValorDebe}" styleId="pcoValorDebeId"/>
				</td>
			</tr>
			<tr>
				<td>
					<html:radio property="boolCargoAbono" value="false" styleId="abono" onclick="toggleValores($('#pcoValorHaberId'),$('#pcoValorDebeId'));" >
						<label>Abono</label>
					</html:radio>
				</td>
				<td id="valorHaber">
					<label>
						<bean:message key="lbl.cpo.valorHaber" />
					</label>
					<html:text style="numeric" maxlength="10" size="7" property="dpaValorHaber" onkeyup="dosDecimales($('#pcoValorHaberId').val(),'pcoValorHaberId');"
								value="${form.dpaValorHaber}" styleId="pcoValorHaberId"/>
				</td>
			</tr>

			<tr>				
				<logic:equal name="partidaContableForm" value="P" property="pcoEstado">
					<td>
						<html:submit property="accion" >
							<bean:message key="cmd.pco.agregarRegistro" />
						</html:submit>
					</td>
					<td>
						<html:submit property="accion">
							<bean:message key="cmd.pco.eliminarDetPartida" />
						</html:submit>
					</td>
				</logic:equal>
				<logic:notEqual name="partidaContableForm" value="P" property="pcoEstado">
					<td>
						<html:submit property="accion" disabled="true">
							<bean:message key="cmd.pco.agregarRegistro" />
						</html:submit>
					</td>
					<td>
						<html:submit property="accion" disabled="true">
							<bean:message key="cmd.pco.eliminarDetPartida" />
						</html:submit>
					</td>
				</logic:notEqual>
			</tr>
		</table>
	</div>
	
	
	<%-- Division de totales en detalle partida, total debe, total haber, diferencia --%>
	<p class="msg_head">
		<bean:message key="lbl.pco.totalesDetallePartida" />
	</p>
	<div class="msg_body" align="center">
	
	<div style="width: 430px;">
	<table align="center" id="hor-zebra" style="text-align: center;">
			<tr align="center" >
				<th><label>Total Debe</label></th>
				<th><label>Total Haber</label></th>
				<th><label>Total Diferencia</label></th>
			</tr>
			<tr align="center" >
				<td><label>${form.dpaTotalDebe}</label></td>
				<td><label>${form.dpaTotalHaber}</label></td>
				<td>
					<logic:present name="diferenciaDetalle" scope="request">
						<logic:equal value="0" name="diferenciaDetalle">
								<label style="color: blue;">${form.dpaDiferenciaDetalle}</label>			
						</logic:equal>
						<logic:greaterThan value="0" name="diferenciaDetalle">
								<label style="color: red;">${form.dpaDiferenciaDetalle}</label>			
						</logic:greaterThan>
					</logic:present>
					
				</td>
			</tr>
		</table>
	</div>
		
	</div>
	
	
	<%-- Division de acciones en Partida (guardar, regresar, cancelar) --%>
	<p class="msg_head">
		<bean:message key="lbl.pco.accionesEnPartida" />
	</p>
	<div class="msg_body">
		<table align="center">
			<tr>
				<td>
					<logic:equal name="partidaContableForm" value="false" property="mdf">
						<html:submit property="accion">
							<bean:message key="cmd.pco.guardarPartida" />
						</html:submit>					
					</logic:equal>
					<logic:notEqual name="partidaContableForm" value="false" property="mdf">
						<logic:equal name="partidaContableForm" value="P" property="pcoEstado">
							<html:submit property="accion">
							 	<bean:message key="cmd.pco.modificarPartida" />
							</html:submit>
						</logic:equal>
						<logic:notEqual name="partidaContableForm" value="P" property="pcoEstado">
							<html:submit property="accion" disabled="true">
								<bean:message key="cmd.pco.modificarPartida"/>
							</html:submit>
						</logic:notEqual>
					</logic:notEqual>
					
					<html:submit property="accion">
						<bean:message key="cmd.pco.regresarBusqueda" />
					</html:submit>					
				</td>
			</tr>
		</table>
	</div>
	
	
	<input id="pageId" type="hidden" name="page" value="3">
	<html:hidden property="pcoId" value="${form.pcoId}"/>
	<html:hidden property="mdf" value="${form.mdf}"/>
	<html:hidden property="numeroRegistro" value="${form.numeroRegistro}"/>
	<html:hidden property="pcoComprobantePartida" value="${form.pcoComprobantePartida}"/>
	<html:hidden property="pcoFechaIngresoPartida" value="${form.pcoFechaIngresoPartida}"/>
	<html:hidden name="partidaContableForm" property="conTpaTipoPartida.tpaId" value="${form.conTpaTipoPartida.tpaId}" styleId="tipoPartidaH"/>
	
	<html:hidden property="form.chkCorrelativoCheque" value="${form.chkCorrelativoCheque}"/>
	<html:hidden property="pcoChequeNegociable" value="${form.pcoChequeNegociable}"/>
	<html:hidden property="pcoEstado" value="${form.pcoEstado}"/>
	
	<html:hidden property="chkEmitidoA" value="${form.chkEmitidoA}"/>
	<html:hidden property="chkMontoEmitido" value="${form.chkMontoEmitido}"/>
	<html:hidden property="chkCorrelativoCheque" value="${form.chkCorrelativoCheque}"/>
	<html:hidden property="chkLugar" value="${form.chkLugar}"/>
	<html:hidden property="chkFecha" value="${form.chkFecha}"/>
	
	
	
	<html:hidden property="cpaID" value="${form.cpaID}" styleId="conceptoPartidaId"/>
	<html:hidden property="conCpaConceptoPartidaD.cpaId" value="${form.conCpaConceptoPartidaD.cpaId}"/>
	<html:hidden property="pcoOtroConcepto"  value="${form.pcoOtroConcepto}"/>
	
	<html:hidden property="boolCargoAbono" value="${form.boolCargoAbono}"/>
	<html:hidden property="dpaOtroConcepto"  value="${form.dpaOtroConcepto}"/>
	<html:hidden property="formDeRedireccion"  value="${form.formDeRedireccion}"/>
	<html:hidden property="conCueCuenta.cueId"  value="${form.conCueCuenta.cueId}" />
	<html:hidden property="dpaValorHaber" value="${form.dpaValorHaber}" />
	<html:hidden property="dpaValorDebe"  value="${form.dpaValorDebe}" />
	<html:hidden property="nuevoComprobante"  value="${form.nuevoComprobante}" styleId="nuevoComprobante"/>	
</html:form>