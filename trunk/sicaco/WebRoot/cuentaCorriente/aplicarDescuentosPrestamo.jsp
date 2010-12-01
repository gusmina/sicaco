<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<link rel="stylesheet" type="text/css" href="../css/zebra.css" >

<script type="text/javascript">
	$(document).ready(function(){
		$(".msg_body").toggle();
		
		//esconde o muestra el contenido del div
		$(".msg_head").click(function(){
			$(this).next(".msg_body").slideToggle(425);
		});

		$("#eliminarRetenciones").click(function() {
				var jpos="";
			$(".posicionRetenciones").each(function(){
					if(this.checked){
						jpos+= "&posicionRetenciones[]=" + this.value;
						var ret = document.getElementById(this.value).value;					
						liquidoMasRet(parseFloat(ret));
					}
			});
			ajaxCallSincrono('${pageContext.request.contextPath}/cuentaCorriente/retencion.do','accion=eliminarRetencion'+jpos,'listaRetenciones');
			
		});
		
		//Que las aportaciones solo permitan numeros
		$("#aportacionesId").numeric({allow:"."});
		var refinanciando = ${refinanciando};
		$("#refinanciando").val(round_number(refinanciando,2));
		
		$("#casCuentaId").val(${cuentaAsociado.casCuenta});
		ajaxCallSincrono('${pageContext.request.contextPath}/cuentaCorriente/aplicarDescuentos.do','accion=cargarLineasDePrestamo&casCuenta='+${cuentaAsociado.casCuenta},'listaPrestamos');
		ajaxCallSincrono('${pageContext.request.contextPath}/cuentaCorriente/aplicarDescuentos.do','accion=cargarTiposDeSeguro&casCuenta='+${cuentaAsociado.casCuenta},'listaSeguros');
		ajaxCallSincrono('${pageContext.request.contextPath}/cuentaCorriente/retencion.do','accion=cargarRetenciones&casCuenta='+${cuentaAsociado.casCuenta},'listaRetenciones');		
		ajaxCallSincrono('${pageContext.request.contextPath}/cuentaCorriente/aplicarDescuentos.do','accion=cargarLineasDeAhorro&casCuenta='+${cuentaAsociado.casCuenta},'listaAhorros');
		
	});

	function calculaIva(){
		var ivap=${iva}/100;
		var deducciones = $("#deduccionesId").val();
		deducciones = dosDecimales(deducciones, "deduccionesId");
		var iva = deducciones*ivap;
		$("#ivaId").val(round_number(iva,2));		
	}
	
	function calculaLiquidoARecibir(){
		var solicitado = ${cuentaAsociado.ctaPrePrestamo.preMontoSolicitado};
		var aportaciones = $("#aportacionesId").val();
		var deducciones = ${cuentaAsociado.ctaPrePrestamo.preOtrasDeducciones};
		var iva = $("#ivaId").val();
		
		//Restando las otras retenciones
		var montoRetenciones = document.getElementsByName("montoRetencion");
		var i=0,montoTotalRet=0;
		while(i<montoRetenciones.length){
			montoTotalRet+=parseFloat(montoRetenciones[i].value);
			i++;
		}
		//Restando los abonos a prestamos
		var montoAbonoPrest = document.getElementsByName("montoAbonoPrest");
		var montoTotalPrest=0;
		$(".posicionPre").each(function(){
			if(this.checked){
				var a = document.getElementById(this.value).value;				
				if(isNaN(a)) a='0.00';
				
				if(a == null || a.length<1){
					var saldoPrestamo = document.getElementsByName(this.value);
					a=saldoPrestamo[0].value;
					//$("#" + this.value).val(saldoPrestamo[0].value);
				}
			
				montoTotalPrest+=parseFloat(a);
			}
		});
		
		//Restando los abonos a los ahorros
		var montoTotalAho=0;
		$(".posicionAho").each(function(){
			if(this.checked){
				var a = document.getElementById(this.value).value;				
				if(a == null || a.length<1 || isNaN(a)) a='0.00';
				montoTotalAho+=parseFloat(a);
			}
		});

		//Restando los abonos a los seguros
		var montoTotalSeg=0;
		$(".posicionSeg").each(function(){
			if(this.checked){
				var a = document.getElementById(this.value).value;				
				if(isNaN(a)) a='0.00';

				if(a == null || a.length<1){
					var saldoSeguro = document.getElementsByName(this.value);
					a=saldoSeguro[0].value;
					//$("#" + this.value).val(saldoSeguro[0].value);			
				}
				montoTotalSeg+=parseFloat(a);
			}
		});					
		
		//Si existe un refinanciamiento resto el monto del prestamo original
		var montoRefinanciamiento = ${refinanciando};  
	
		var liquido = solicitado - aportaciones - deducciones - iva - montoTotalRet - montoTotalPrest - montoTotalAho - montoTotalSeg - montoRefinanciamiento;

		$("#liquidoId").val(roundNumber(liquido,2));

	}
	function liquidoMenosRet(){
		var liquido = $("#liquidoId").val();
		var retencion = $("#montoRet").val();
		var nombreRet = $("#nombreRet").val();
		var liquidoF = parseFloat(liquido);
		var retencionF = parseFloat(retencion)
		if(retencion>0 && retencion<=liquidoF && nombreRet!=''){
			liquidoF = liquidoF - retencionF;
			$("#liquidoId").val(round_number(liquidoF,2));
			$("#nombreRet").val('');
			$("#montoRet").val('');
		}
	}
	function liquidoMasRet(retencion){
		var liquido = $("#liquidoId").val();
		liquido = parseFloat(liquido) + retencion;
		$("#liquidoId").val(liquido);
	}
	function validarLiquido(){
		var liquido = $("#liquidoId").val();
		if(liquido<0){//Si el líquido es negativo muestra el error.
			document.getElementById('errorLiquido').style.display='';
		}else{
			document.getElementById('errorLiquido').style.display='none';//Ya no muestro el error de liquido negativo.
			//Verificando que no hayan errores en prestamos.
			$('#errorPre').val(false);
			$(".posicionPre").each(function(){
				if(this.checked){	
					var a = document.getElementById(this.value).value;				
					if(a == null || a.length<1 || isNaN(a)) a='0.00';
					
					var saldoPrestamo = document.getElementsByName(this.value);
					if(saldoPrestamo[0].value < parseFloat(a)) $('#errorPre').val(true);
				}
			});	
		
			var errorPre = $('#errorPre').val();
			
			if(errorPre == 'true'){
				document.getElementById('errorPrestamo').style.display='';
			}else{				
				document.getElementById('errorPrestamo').style.display='none';
				
				//Verificando que no hayan errores en seguros
				$('#errorSeg').val(false);
				$(".posicionSeg").each(function(){
					if(this.checked){
						var a = document.getElementById(this.value).value;				
						if(a == null || a.length<1 || isNaN(a)) a='0.00';

						var saldoSeguro = document.getElementsByName(this.value);
						if(saldoSeguro[0].value < parseFloat(a)) $('#errorSeg').val(true);						
					}
				});					
				var errorSeg = $('#errorSeg').val();
				if(errorSeg == 'true'){
					document.getElementById('errorSeguro').style.display='';
				}else{				
					document.getElementById('errorSeguro').style.display='none';
					verificarPrestamos();									
				}								
			}
		}
	}
	function verificarPrestamos(){
		var pos="";
		var pre="";
		var digPre="";
		//Enviando los abonos a prestamos
		$(".posicionPre").each(function(){
			if(this.checked){
				pos+= "&posicionPre[]=" + this.value;
				var a = document.getElementById(this.value).value;
				
				if(a == null || a.length<1){
					//var saldoPrestamo = document.getElementsByName(this.value);
					//a=saldoPrestamo[0].value;
					digPre+="&digitadoPre[]=0";
				}else digPre+="&digitadoPre[]=1";
				
				pre+= "&prestamos[]=" + a					
			}
		});
		
		var posA="";
		var aho="";
		//Enviando los abonos de ahorros
		$(".posicionAho").each(function(){
			if(this.checked){
				posA+= "&posicionAho[]=" + this.value;
				aho+= "&ahorros[]=" + document.getElementById(this.value).value;	
			}
		});	
		var posSeg="";
		var seg="";
		var digSeg="";
		//Enviando los abonos de seguros
		$(".posicionSeg").each(function(){
			if(this.checked){
				posSeg+= "&posicionSeg[]=" + this.value;
				var a = document.getElementById(this.value).value;
				
				if(a == null || a.length<1){
					//var saldoSeguro = document.getElementsByName(this.value);
					//a=saldoSeguro[0].value;
					digSeg+="&digitadoSeg[]=0";
				}else digSeg+="&digitadoSeg[]=1";
				
				seg+= "&seguros[]=" + a
			}
		});	
		
		$('#aplicarDescFormId')[0].action='${pageContext.request.contextPath}/cuentaCorriente/aplicarDescuentos.do?accion=<bean:message key="cmd.desc.aceptar" bundle="aux"/>'+pos+pre+digPre+posA+aho+posSeg+seg+digSeg;
		$('#aplicarDescFormId')[0].submit();
	}
	
	function soloNumeros(valor,id){
		if(isNaN(valor)) $("#" + id).val('0.00');
		else{
			$("#" + id).val(valor);
			var saldoPrestamo = document.getElementsByName(id);
			var a = valor; 
			if(saldoPrestamo[0].value < parseFloat(a)){
				a=saldoPrestamo[0].value;
				$("#" + id).val(saldoPrestamo[0].value);
			}			
		}
	}
	function soloNumerosAho(valor,id){
		if(isNaN(valor)) $("#" + id).val('0.00');
		else{
			$("#" + id).val(valor);		
		}
	}
	function soloNumerosSeg(valor,id){
		if(isNaN(valor)) $("#" + id).val('0.00');
		else{
			$("#" + id).val(valor);
			var saldoSeguro = document.getElementsByName(id);
			var a = valor; 
			if(saldoSeguro[0].value < parseFloat(a)){
				a=saldoSeguro[0].value;
				$("#" + id).val(saldoSeguro[0].value);
			}
		}
	}
	function soloNumerosRet(valor,id){
		if(isNaN(valor)) $("#" + id).val('0.00');
		else{
			$("#" + id).val(valor);		
		}
	}	
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
	clear: both;
	font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
}

.msg_body {
	padding: 5px 10px 15px;
	background-color: auto;
}
label {
	font-size: 13px;
	text-align: right;
	color: #039;
	text-transform: none;
}
span {
	font-size: 12px;
	text-align: right;
	text-transform: none;
}
</style>
<html:form action="${_accion}" method="post" styleId="aplicarDescFormId">
	<table>
		<tr>
			<td><label><bean:message key="lbl.desc.codAsoc" bundle="aux"/></label></td>
			<td>
				<span><bean:write name="cuentaAsociado" property="ctaAscAsociado.ascCodigoAsociado"/></span>
			</td>
			<td style="padding-left: 20px;"><label><bean:message key="lbl.desc.nombreAsoc" bundle="aux"/></label></td>
			<td>
				<span><bean:write name="nombreCompleto"/></span>
			</td>			
		</tr>
		<tr>
			<td><label><bean:message key="lbl.desc.montoSolicitado" bundle="aux"/></label></td>
			<td><span><bean:write name="cuentaAsociado" property="ctaPrePrestamo.preMontoSolicitado"/></span></td>
			<td><label><bean:message key="lbl.desc.referencia" bundle="aux"/></label></td>
			<td><span><bean:write name="cuentaAsociado" property="ctaPrePrestamo.preReferencia"/></span></td>
		</tr>		
	</table>
	<div style="padding-top: 10px;padding-bottom: 15px;text-align: center;"><label style="text-transform: uppercase;font-size: 14px;font-weight: bold;"><bean:message key="lbl.desc.aplicarDesc" bundle="aux"/></label></div>
	<table style="padding-bottom: 10px;">
		<tr>
			<td><label><bean:message key="lbl.desc.aportaciones" bundle="aux"/></label></td>
			<td><input type="text" name="aportaciones" value="<bean:write name="cuentaAsociado" property="ctaPrePrestamo.preAportaciones"/>" size="10" id="aportacionesId" onkeyup="dosDecimales($('#aportacionesId').val(),'aportacionesId');calculaLiquidoARecibir();"/></td>			
			<td><label><bean:message key="lbl.desc.deducciones" bundle="aux"/></label></td>
			<td><input type="text" name="deducciones" value="<bean:write name="cuentaAsociado" property="ctaPrePrestamo.preOtrasDeducciones"/>" size="10" id="deduccionesId" readonly="readonly"></td>
			<td><label><bean:message key="lbl.desc.iva" bundle="aux"/></label></td>
			<td><input type="text" name="iva" value="<bean:write name="cuentaAsociado" property="ctaPrePrestamo.preIvaDeducciones"/>" size="10" id="ivaId" readonly="readonly"/></td>			
		</tr>
		<logic:present name="ref">
			<tr>
				<td><label><bean:message key="lbl.desc.refinanciando" bundle="aux"/></label></td>
				<td><input type="text" size="10" readonly="readonly" id="refinanciando" ></td>
				<td colspan="4"><span><bean:write name="cuentaRef" property="ctaPrePrestamo.preReferencia"/></span></td>
			</tr>			 
		</logic:present>		
	</table>
	<p class="msg_head"><bean:message key="lbl.desc.lineasPre" bundle="aux"/>
	</p>
	<div class="msg_body">
		<div id="listaPrestamos">			
		</div>	
	</div>
	<p class="msg_head"><bean:message key="lbl.desc.tipoSeg" bundle="aux"/>
	</p>
	<div class="msg_body">
		<div id="listaSeguros">			
		</div>
	</div>
	<p class="msg_head"><bean:message key="lbl.desc.retenciones" bundle="aux"/>
	</p>
	<div class="msg_body">	
		<table align="center" style="padding-bottom: 10px;">
		<tr>
			<td><label><bean:message key="lbl.desc.nombreRetencion" bundle="aux"/>:</label></td>
			<td><input type="text" size="50" name="nombreRet" id="nombreRet"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label><bean:message key="lbl.desc.montoRetencion" bundle="aux"/>:</label></td>
			<td>
					<input type="text" size="10" name="montoRet" id="montoRet"
						onkeyup="dosDecimales($('#montoRet').val(),'montoRet');soloNumerosRet($('#montoRet').val(),'montoRet');" />

					<img src="../imagenes/opcionesTabla/down.png" style="cursor: pointer;" alt="Guardar" 
				onclick="ajaxCallSincrono('${pageContext.request.contextPath}/cuentaCorriente/retencion.do','accion=guardarRetencion&nombre='+$('#nombreRet').val()+'&monto='+$('#montoRet').val()+'&liquido='+$('#liquidoId').val(),'listaRetenciones');liquidoMenosRet();" />
				<img src="../imagenes/opcionesTabla/cross.png" style="cursor: pointer;" alt="Eliminar" id="eliminarRetenciones"/>							
			</td>
		</tr>
		</table>
		<div id="listaRetenciones">
			<div class="scrollPane">
				<table id="hor-zebra">
					<thead>
						<tr>
							<th scope="col" style="text-align: left;">&nbsp;</th>		
							<th scope="col" style="text-align: left;"><bean:message key="lbl.desc.nombreRetencion" bundle="aux"/></th>
							<th scope="col" style="text-align: left;" class="th1"><bean:message key="lbl.desc.montoRetencion" bundle="aux"/></th>
						</tr>
					</thead>
					<tbody>
						<tr class="odd"><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
						<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
					</tbody>
				</table>				
			</div>			
		</div>
	</div>
	<p class="msg_head"><bean:message key="lbl.desc.lineasAho" bundle="aux"/>
	</p>
	<div class="msg_body">
		<div id="listaAhorros">			
		</div>
	</div>
	<div align="center" style="padding-bottom: 20px;clear: both;padding-top: 20px;">
		<label>L&iacute;quido a Recibir:</label>
		<input type="text" id="liquidoId" size="10" value="<bean:write name="cuentaAsociado" property="ctaPrePrestamo.preLiquidoARecibir"/>" readonly="readonly" onmouseover="cambioColor();">
		<div style="padding-top: 10px;display: none;" id="errorLiquido"><label style="color: red;text-transform: none"><bean:message key="error.desc.liquidoNegativo" bundle="aux"/> </label></div>
		<div style="padding-top: 10px;display: none;" id="errorPrestamo"><label style="color: red;text-transform: none"><bean:message key="error.desc.errorPre" bundle="aux"/> </label></div>
		<div style="padding-top: 10px;display: none;" id="errorSeguro"><label style="color: red;text-transform: none"><bean:message key="error.desc.errorSeg" bundle="aux"/> </label></div>
		<div style="padding-top: 10px;display: none;" id="divErrorGeneral"></div>
	</div>	
	<div style="text-align: center;">
		<input type="button" onclick="calculaLiquidoARecibir();validarLiquido();" value="<bean:message key='cmd.desc.aceptar' bundle='aux'/>">
		<html:submit property="accion">
			<bean:message key="cmd.desc.cancelar" bundle="aux"/>
		</html:submit>
		<!-- <input type="button" onclick="history.go(-1)" value="<bean:message key='cmd.desc.cancelar' bundle='aux'/>"> -->
	</div>

	<input type="hidden" name="accion" id="accionId" value="<bean:message key='cmd.desc.aceptar' bundle='aux'/>"/>
	<html:hidden property="casCuenta" styleId="casCuentaId"/>
</html:form>