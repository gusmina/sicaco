<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<link rel="stylesheet" type="text/css" href="../css/zebra.css" >

<script type="text/javascript">
<!--
	$(document).ready(function(){
	$("#reportControls").hide();
	 $('a[rel*=facebox]').facebox();
		$("#banId").change(function() {
					ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/fondosOficina.do','accion=cargarListaCuentas&banId='+$(this).val(),'cuentas');
		 		});
		$("#eliminarRemesas").click(function() {
				var jpos="";
			$(".posicionRemesas").each(function(){
					if(this.checked){
						jpos+= "&posicionRemesas[]=" + this.value;
						var rem = document.getElementById(this.value).value;
						saldoMasRem(parseFloat(rem));
					}
			});
			ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/fondosOficina.do','accion=eliminarRemesa'+jpos,'listaRemesas');
			
		});
		$("#remesar").numeric({allow:"."});
	});
	function saldoMenosRem(){
		var cuenta = $("#cueId").val();
		var saldo = $("#saldoOf").val();
		var remesa = $("#remesar").val();
		var saldoF = parseFloat(saldo);
		var remesaF = parseFloat(remesa)
		if(remesa>0 && remesa<=saldoF && cuenta != -1){
			saldoF = saldoF - remesaF;
			$("#saldoOf").val(round_number(saldoF,2));
			$("#remesar").val('');
		}
	}
	
	function saldoMasRem(remesa){
		var saldo = $("#saldoOf").val();
		var saldoF = parseFloat(saldo);
		var remesaF = parseFloat(remesa);
		saldoF = saldoF + remesaF;
		$("#saldoOf").val(saldoF);
	}

	function showOrHideReportControls(){		
		var showing = $("#showing").val();
		if (showing == "false" ){
			showing= "true";
			$("#reportControls").slideDown("slow");
			$("#controles").slideUp("slow");
			$("#showOrHideButton").val("Reporte (-)");
		}else{
			showing= "false";
			$("#reportControls").slideUp("slow");
			$("#controles").slideDown("slow");
			
			$("#showOrHideButton").val("Reporte (+)");			
		}
		$("#showing").val(showing);
	}
//-->
</script>
<style>
<!--
label {
	font-size: 13px;
	text-align: right;
	color: #039;
	text-transform: none;
}

input {
	font-size: 13px;
}

select {
	font-size: 13px;
}
-->
</style>
<html:form action="${_accion}" method="post" styleId="formId">
	<hr/>
	<input id="showing" type="hidden" value="false"/>	
	
<div id="controles">
	<table  align="center"
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		width: 75%;
		text-align: left;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;"
	>
	
		<tr >
			<td style="padding-bottom: 10px">
			<label>Saldo en fondos de oficina:</label>
				
			</td>
			<td style="padding-bottom: 10px">
			<div id="saldoOfDiv">
				<input  id="saldoOf" type="text" name="saldoActual" readonly="readonly" value="${saldoOf}" style="width: 100px; "/>
			</div>
				 
			</td>
			
		</tr>
		<tr>
			<td style="padding-bottom: 10px">
				<label>Banco:</label>
			</td>
			<td style="padding-bottom: 10px">
			<html:select property="banId" styleId="banId" style="width: 350px; font-family: 'Lucida Sans Unicode','Lucida Grande',Sans-Serif; font-size: 13px;">
				<html:option value="000">- - Seleccione un banco - -</html:option>
				<html:options collection="bancos" property="idBanco" labelProperty ="nombre" />
			</html:select>
			</td>
			</tr>
			<tr>
			<td style="padding-bottom: 10px">
				<label>Cuenta:</label>
			</td>
			<td style="padding-bottom: 10px">
			<div id="cuentas">
				<select style="width: 350px" name="cueId" id="cueId" >
				 	<option value="-1">- - - - - - - - - - - -  </option>
				 </select>
			</div>
			</td>
			</tr>
			<tr>
			<td style="padding-bottom: 10px">
				<label>Cantidad a remesar:</label>
			</td>
				<td style="padding-bottom: 10px">
						<input type="text" value="0.00"  style="width: 100px" id="remesar" name="remesar" onkeyup="dosDecimales($('#remesar').val(),'remesar');"/>
						<img src="../imagenes/opcionesTabla/down.png" style="cursor: pointer;" alt="Guardar" 
							onclick="ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/fondosOficina.do','accion=guardarRemesa&cueId='+$('#cueId').val()+'&remesar='+$('#remesar').val(),'listaRemesas');saldoMenosRem();" />
						<img src="../imagenes/opcionesTabla/cross.png" style="cursor: pointer;" alt="Eliminar" id="eliminarRemesas"/> 
				</td>	
			 
			
			
			<tr>

			</tr>
	</table>
	<div id="listaRemesas" align="center">
		<table  id="hor-zebra" style="width: 50%; margin: auto;" align="center">
					<thead>
						<tr>
							<th scope="col" style="text-align: left;">&nbsp;</th>		
							<th scope="col" style="text-align: left;">Banco y cuenta bancaria</th>
							<th scope="col" style="text-align: left;" class="th1">Valor</th>
							
						</tr>
					</thead>
					<tbody>
						<tr class="odd"><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
						<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
					</tbody>
			</table>
	</div>

</div>
	
	<table  align="center"
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 0px;
		margin-top: 10px;
		margin-left: auto;
		margin-right: auto;
		width: 60%;
		text-align: left;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;"
	>
		<tr>
			<td>
					<html:submit property="accion" style="width: 180px">
					<bean:message key="cmd.fondosOficina.actualizarCierre" />
				</html:submit>
			</td>
			<td>
					<html:submit property="accion" style="width: 180px">
					<bean:message key="cmd.fondosOficina.cancelar" />
				</html:submit>
			</td>
			<td>
				<input id="showOrHideButton" style="width: 180px"  onclick="showOrHideReportControls();" type="button"  value="Reporte..."/>
			</td>
		</tr>

	</table>
	



	<div id="reportControls" >
	
		<table align="center"
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 45%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra">
		<tr>
			<th colspan="2">Datos del reporte</th>
		</tr>
		<tr>
		<td>
			<label>Solicitado por:</label>	
		</td>
		<td>
			<input type="text"  name="solicita" id="solicitaId"/>
		</td>
		</tr>
		<tr>
		<td align="right">
			<label>Autorizado por:</label>
			
		</td>
		<td align="left">
			<input type="text"  id="autorizaId" name="autoriza"/>
		</td>
		</tr>
				<tr>
					<td>
						<label>
							Fecha:
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fecha"
							onkeyup="JavaScript:mask(this);" id="fechaZ" />
						<input id="calendarZ" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fechaZ",
				              button        : "calendarZ",
				              align         : "Br"
				            });
				         </script>
					</td>
				</tr>
		<tr>
		<td  colspan="2" align="center">
				<html:submit property="accion" styleId="imprime" style="width: 180px">
					<bean:message key="cmd.fondosOficina.imprimirReporte" />
				</html:submit>
		</td>
			</tr>
		</table>
	</div>
	
</html:form>
