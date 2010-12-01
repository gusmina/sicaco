<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<link rel="stylesheet" type="text/css" href="../css/zebra.css" >

<script type="text/javascript">
<!--
	$(document).ready(function(){
	
		$("#cuentaId").change(function() {
					cargaSaldo();
		 		}); 
		
		$("#banId").change(function() {
					ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/desembolsoDeFondos.do','accion=cargarListaCuentas&banId='+$(this).val(),'cuentas');
					$("#saldoCue").val('0.00');
		 		});
		 
		$("#idBancoC").change(function() {
					ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/desembolsoDeFondos.do','accion=actualizarPrestamos&idBancoC='+$(this).val(),'prestamosId');
					ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/desembolsoDeFondos.do','accion=actualizarAhorros&idBancoC='+$(this).val(),'ahorrosId');
					ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/desembolsoDeFondos.do','accion=actualizarProveedores&idBancoC='+$(this).val(),'proveedoresId');
					uncheckAll(document.desembolsoDeFondosForm.list);
					$("#transferir").val('0.00');
		 		});
		
		$("#eliminarDesembolsos").click(function() {
				var jpos="";
			$(".posicionDesembolsos").each(function(){
					if(this.checked){
						jpos+= "&posicionDesembolsos[]=" + this.value;
						var rem = document.getElementById(this.value).value;
					}
			});
			ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/desembolsoDeFondos.do','accion=eliminarDesembolso'+jpos,'listaDesembolsos');
			ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/desembolsoDeFondos.do','accion=actualizarPrestamos&idBancoC='+$('#idBancoC').val(),'prestamosId');
			ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/desembolsoDeFondos.do','accion=actualizarAhorros&idBancoC='+$('#idBancoC').val(),'ahorrosId');
			ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/desembolsoDeFondos.do','accion=actualizarProveedores&idBancoC='+$('#idBancoC').val(),'proveedoresId');
			ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/desembolsoDeFondos.do','accion=actualizarSaldo','saldoOfDiv');
			uncheckAll(document.desembolsoDeFondosForm.list);
			$("#transferir").val('0.00');
			
		});
		$("#remesar").numeric({allow:"."});
	});

	function actualizarTransferir(){
	var transferir= 0.00/1;
		if ($("#chkPrestamos").is(':checked')){
			transferir = parseFloat($("#prestamos").val());
			$("#chkPrestamosB").val(true);
		}else{
			$("#chkPrestamosB").val(false);
		}
		if ($("#chkAhorros").is(':checked')){
			transferir = transferir +parseFloat($("#ahorros").val());
			$("#chkAhorrosB").val(true);
		}else{
			$("#chkAhorrosB").val(false);
		}
		if ($("#chkProveedores").is(':checked')){
			transferir = transferir +parseFloat($("#proveedores").val());
			$("#chkProveedoresB").val(true);
		}else{
			$("#chkProveedoresB").val(false);
		}
		$("#transferir").val(parseFloat(transferir));
	} 
	
	function uncheckAll(field){
		for (i = 0; i < field.length; i++)
			field[i].checked = false ;
	}
	function checkAll(field){
		for (i = 0; i < field.length; i++)
			field[i].checked = true ;
	}
	
	function cargaSaldo(valor){
		ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/desembolsoDeFondos.do','accion=cargarSaldoCuenta&cueId='+$("#cuentaId").val(),'saldoCueId');
	}
	
	function agregar(){
		var sel = document.desembolsoDeFondosForm.banId;
		var strValue = sel.options[sel.selectedIndex].value;
		var strText = sel.options[sel.selectedIndex].text;
		
		var sel2 = document.desembolsoDeFondosForm.idBancoC;
		var strValue2 = sel2.options[sel2.selectedIndex].value;
		var strText2 = sel2.options[sel2.selectedIndex].text;
		
		ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/desembolsoDeFondos.do',
						'accion=guardarDesembolso&cueId='+$('#cuentaId').val()+
						'&transferir='+$('#transferir').val()+'&saldoCue='+$('#saldoCue').val()+
						'&idBancoC='+$('#idBancoC').val()+'&chkPrestamos='+$('#chkPrestamosB').val()+
						'&chkAhorros='+$('#chkAhorrosB').val()+'&chkProveedores='+$('#chkProveedoresB').val()+
						'&ahorros='+$('#ahorros').val()+'&prestamos='+$('#prestamos').val()+
						'&proveedores='+$('#proveedores').val()+'&bancoF='+strText+'&bancoD='+strText2,
						'listaDesembolsos');
		ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/desembolsoDeFondos.do','accion=actualizarPrestamos&idBancoC='+$('#idBancoC').val(),'prestamosId');
		ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/desembolsoDeFondos.do','accion=actualizarAhorros&idBancoC='+$('#idBancoC').val(),'ahorrosId');
		ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/desembolsoDeFondos.do','accion=actualizarProveedores&idBancoC='+$('#idBancoC').val(),'proveedoresId');
		ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad/desembolsoDeFondos.do','accion=actualizarSaldo','saldoOfDiv');		
		uncheckAll(document.desembolsoDeFondosForm.list);
		$("#transferir").val('0.00');
	}
//-->
</script>
<style>
<!--
label {
	font-size: 13px;
	text-align: left;
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
	<table  align="center"
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		width: 80%;
		text-align: left;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;"
	>
	
		<tr>
			<td style="padding-bottom: 10px">
			<label><em>Depositos bancarios en transito:</em></label>
				
			</td>
			<td style="padding-bottom: 10px">
			<div id="saldoOfDiv">
				<input  id="saldoOf" type="text" name="saldoActual" readonly="readonly" value="${saldoOf}" style="width: 100px; "/>
			</div>
				 
			</td>
			
		</tr>
	</table>	
	<table  align="center"
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		width: 40%;
		text-align: left;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;"
	>
	<tr><td colspan="2" style="text-align: left; padding-bottom: 10px"><label><b>1. PROCEDENCIA DESDE PAGO ELECTRONICO: </b> </label> </td></tr>
	<tr><td colspan="2" style="text-align: left; padding-bottom: 10px"><label><hr/> </label> </td></tr>
		<tr>
			<td style="padding-bottom: 10px; padding-top: 10px">
				<label>Banco:</label>
			</td>
			<td style="padding-bottom: 10px">
			<html:select property="idBancoC" styleId="idBancoC"  style="width: 350px; font-family: 'Lucida Sans Unicode','Lucida Grande',Sans-Serif; font-size: 13px;">
				<html:option value="000">- - Seleccione un banco - -</html:option>
				<html:options collection="bancosC" property="banId" labelProperty ="banNombre" />
			</html:select>
			</td>
		</tr>
	</table>
	<div id="rubros"> 
	<table  id="newspaper-a" style="width: 25%; margin: auto;" >
		<thead>
	    	<tr>
	        	<th scope="col">&nbsp;</th>
	            <th scope="col">Rubro</th>
	            <th scope="col">Valor</th>
	        </tr>
	    </thead>
  		<tr>
			<td>
				<input type="checkbox" id="chkPrestamos" onclick="actualizarTransferir();" name="list" />
				<html:hidden property="chkPrestamos"  styleId="chkPrestamosB" />
			</td>
			<td>
				<label>Pr&eacute;stamos :</label>
			</td>
			<td style="padding-bottom: 10px">
				<div id="prestamosId">
					<input  id="prestamos" name = "prestamos" type="text" readonly="readonly" value="0.00" style="width: 100px; "/>
				</div>
				 
			</td>
		</tr>
		<tr>
			<td>
				<input type="checkbox" id="chkAhorros" onclick="actualizarTransferir();" name="list"/>
				<html:hidden property="chkAhorros"  styleId="chkAhorrosB"/>
			</td>
			<td>
				 <label>Ahorros :</label>
			</td>
			<td style="padding-bottom: 10px">
				<div id="ahorrosId">
					<input  id="ahorros" type="text" name = "ahorros" readonly="readonly" value="0.00" style="width: 100px; "/>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<input type="checkbox" id="chkProveedores" onclick="actualizarTransferir();" name="list"/>
				<html:hidden property="chkProveedores" styleId="chkProveedoresB"/>
			</td>
			<td>
				 <label>Proveedores: </label>
			</td>
			<td style="padding-bottom: 10px">
				<div id="proveedoresId">
					<input  id="proveedores" type="text" name = "proveedores" readonly="readonly" value="0.00" style="width: 100px; "/>
				</div>
			</td>
		</tr>
	<tfoot>
    	<tr>
        	<td colspan="2"><em>Valor a transferir:</em></td>
        	<td style="padding-bottom: 10px">
				<div id="transferirId">
					<input  id="transferir" type="text" name="transferir" readonly="readonly" value="0.00" style="width: 100px; "/>
				</div>
			</td>
        </tr>
    </tfoot>
			
	</table>
		
	</div>
	

	
	
	<table  align="center"
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		width: 40%;
		text-align: left;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;"
	>
		<tr><td colspan="2" style="text-align: left; padding-bottom: 10px"><label>&nbsp;</label> </td></tr>
		<tr><td colspan="2" style="text-align: left; padding-bottom: 10px"><label><b>2. CUENTA CONTABLE A AFECTAR: </b> </label> </td></tr>
		<tr><td colspan="2" style="text-align: left; padding-bottom: 10px"><label><hr/> </label> </td></tr>
		<tr>
			<td style="padding-bottom: 10px; padding-top: 10px">
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
				<select style="width: 350px" name="cuentaId" id="cuentaId" onchange="cargaSaldo(this.value);">
				 	<option value="-1">- - - - - - - - - - - -  </option>
				 </select>
			</div>
			</td>
		</tr>
		<tr>
			<td style="padding-bottom: 10px">
				<label>Saldo:</label>
			</td>
			<td style="padding-bottom: 10px">
				<div id="saldoCueId">
						<input type="text" value="0.00"  style="width: 100px" id="saldoCue" name="saldoCue" readonly="readonly"/>
				</div>
				
					 
			</td>	
		</tr>
		<tr>
			<td colspan="2" style="text-align: right;">
				<img src="../imagenes/opcionesTabla/down.png" style="cursor: pointer;" alt="Guardar" 
						onclick="agregar()" />
				<img src="../imagenes/opcionesTabla/cross.png" style="cursor: pointer;" alt="Eliminar" id="eliminarDesembolsos"/>
			</td>
		</tr>
	</table>
	<div id="listaDesembolsos" align="center">
		<table  id="hor-zebra" style="margin: auto;" align="center">
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
			    	<tr class="foot">
			        	<td colspan="2"><em>Total a transferir:</em></td>
			        	<td style="padding-bottom: 10px; text-align: center; font-size: 12px;">
							<div id="totalTransferir" >
								0.00
							</div>
						</td>
			        </tr>
				</tbody>
				
		</table>
	</div>
	
	
	
	<table  align="center"
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 0px;
		margin-top: 10px;
		margin-left: auto;
		margin-right: auto;
		width: 60%;
		text-align: center;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;"
	>
	<tr><td colspan="2">&nbsp;</td></tr>
		<tr>
			<td>
					<html:submit property="accion" style="width: 180px">
					<bean:message key="cmd.desembolsoDeFondos.aceptar" />
				</html:submit>
			</td>
			<td>
					<html:submit property="accion" style="width: 180px">
					<bean:message key="cmd.fondosOficina.cancelar" />
				</html:submit>
			</td>
		</tr>
	</table>
</html:form>