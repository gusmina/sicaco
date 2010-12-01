<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#tisNombreId").alphanumeric({allow:" "});
    	$("#tisPolizaId").alphanumeric({allow:" -"});
    	$("#tisDescripcionId").alphanumeric({allow:" -.;,()"});
  });
  
  function cambiarEstado(stb){
    $('#stbId').val(stb);
  	$('#accionId').val('cambiarEstado');
	$('#formId')[0].submit();
  };
  
  function cambiarLista(){
  	$('#accionId').val('cambiarLista');
	$('#formId')[0].submit();
  };
  
  function saveSeleccionA(valor) {
		valores = valor.split(';')
		$('#ascCodigoAsociadoId').val(valores[0])
		$('#ascNombreId').val(valores[1])
		$('#codice').val(valores[2])
		$('#resultadoAsc0').hide('slow')
		var chk = $("#transAsocId")[0];
		if(chk.checked != true){
			$('#ascFuenteId').val(valores[2]);
		}
	};
	
	function saveSeleccionB(valor) {
		valores = valor.split(';')
		$('#ascCodigoAsociadoBId').val(valores[0])
		$('#ascNombreBId').val(valores[1])
		$('#codiceB').val(valores[2])
		$('#resultadoAsc1').hide('slow')
		$('#ascFuenteId').val(valores[2]);
	}; 
	
	function habilitaAsociado(){
		var chk = $("#transAsocId")[0];
		if(chk.checked == true){
			if($("#codiceB").val()!=null && $('#codiceB').val()!=""){
				$('#ascFuenteId').val($('#codiceB').val());
			}
			$("#ascCodigoAsociadoBId").removeAttr("disabled");
			$("#ascNombreBId").removeAttr("disabled");
		}else{
			$('#ascFuenteId').val($('#codice').val());
			$("#ascCodigoAsociadoBId").attr("disabled","disabled");
			$("#ascNombreBId").attr("disabled","disabled");
		}
	};
	
	function loadCont(fuente){
		ajaxCallNormal('${pageContext.request.contextPath}/procesosEspeciales${_accion}.do',
							'accion=disponibleCuentaCont&cuentaCont='+fuente,'cantidadDisponible')
	};
	
	function cargarDisponible(){
		ajaxCallNormal('${pageContext.request.contextPath}/procesosEspeciales${_accion}.do',
			'accion=htmlCampoDisponibilidad&cuentaDestino='+$('#cuentaDestino').val()+
			'&destino='+$('#destinoId').val(),'saldoActual');
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

<html:form action="${_accion}" method="post"  styleId="formId" >
	<table align="center" border="0">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.txc.transferenciaElectronica" />
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.txc.comprobante" />
				</label>
			</td>
			<td>
				<html:text property="comprobante" value="${form.comprobante}" 
					styleId="comprobId" styleClass="obligatorio" readonly="true"
					size="5" maxlength="15"/>
			</td>
		</tr>
		<tr>
			<td>
				<label><bean:message key="lbl.txc.fechaTrans" /></label>:
			</td>
			<td>
	          	<html:text style="float:left;" styleId="fechaTransId" onkeyup="mask(this);" value="${form.fechaTrans}" property="fechaTrans" maxlength="10" size="10"/>
	        	<input  style="height: 18px;" id="button_fechaTransId" type="button"  value="..."/>
	        	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d">
	        	<script type="text/javascript">
			            Calendar.setup({
			              inputField    : "fechaTransId",
			              button        : "button_fechaTransId",
			              align         : "Br"
			            });
			    </script>
			</td>
		</tr>
		<tr>
	    	<td><label>
				<bean:message key="lbl.ordcom.asociado" />:</label>
			</td>
			<td colspan="2">
				<input type="checkbox" name="chkbx" id="chkbx" onclick="selected();" /> 
				<label><bean:message key="lbl.ordcom.codEmp" /></label>
				<html:hidden property="ascEmp" styleId="ascEmpId" value="1" />
			</td>
	   	</tr>
		<tr>
			<td><label>
				<bean:message key="lbl.ordcom.secAscAsociado.Id.ascCodigo" />:</label>
			</td>
			<td>
				<html:text property="ascCodigoAsociado" size="15" maxlength="12" styleClass="obligatorio" value="${form.ascCodigoAsociado}" styleId="ascCodigoAsociadoId"/>
				<span><bean:message key="msg.obligatorio" /> </span></td>
			<td><label>
				<bean:message key="lbl.ordcom.ascNombre" />:</label>
			</td>
			<td>
				<html:hidden property="codigo" styleId="codice" value="${form.codigo}"/>
				<html:text property="ascNombre" size="15" maxlength="100" styleClass="obligatorio" value="${form.ascNombre}" styleId="ascNombreId"/>
				<input type="button" value="..." 
					onclick="ajaxCallNormal('${pageContext.request.contextPath}/procesosEspeciales${_accion}.do',
						'accion=cargarListaAsociados&ascCodigoAsociado='+$('#ascCodigoAsociadoId').val()+
						'&ascNombre='+$('#ascNombreId').val()+'&ascEmp='+$('#ascEmpId').val(),'asociados')"/>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<div id="asociados">
				</div>
		    </td>
		</tr>
		<tr>
			<td >
				<label><bean:message key="lbl.txc.bancos" />:</label>
			</td>
			<td>
				<html:select property="banId" styleId="banId">
					<html:optionsCollection label="banNombre" name="bancos" value="banId"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td >
				<label><bean:message key="lbl.txc.txaMonto" />:</label>
			</td>
			<td>
				<html:text property="txaMonto" styleId="txaMontoId"/>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.txc.destinos" />
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.txc.cuenta" />
				</label>
			</td>
			<td>
				<html:select property="destino"  value="${form.destino}" styleId="destinoId"
					onchange="ajaxCallNormal('${pageContext.request.contextPath}/procesosEspeciales${_accion}.do',
						'accion=cargarHtml2&destino='+$('#destinoId').val()+'&codigo='+$('#codice').val(),
						'valoresDestino','vdestino');">
					<html:option value="-1">...</html:option>
					<html:option value="1">Aportaciones</html:option>
					<html:option value="2">Ahorros</html:option>
					<html:option value="3">Prestamos</html:option>
					<html:option value="4">Seguros</html:option>
				</html:select>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				<div id="valoresDestino">
					<html:hidden property="b" styleId="vdestino"/>
				</div>
			</td>
		</tr>
	</table>
	<table align="center" border="0">
		<tr>
			<td colspan="4">
				<html:submit property="accion">
					<bean:message key="cmd.txa.transferirFecha" />
				</html:submit>
				<html:submit property="accion">
					<bean:message key="cmd.txa.cancelarFecha" />
				</html:submit>
				<logic:present name="botonImprimir">
					<html:submit property="accion">
						<bean:message key="cmd.txc.imprimirReporte2" />
					</html:submit>
					<logic:equal value="2" name="botonImprimir">
						<html:submit property="accion">
							<bean:message key="cmd.txc.imprimirReporte" />
						</html:submit>
					</logic:equal>
				</logic:present>
			</td>
		</tr>
	</table>
	
	<html:hidden property="ascId" value="${form.ascId}" styleId="ascId"/>
	<html:hidden property="ascId2" value="${form.ascId2}" styleId="ascId2"/>
</html:form>

 <script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerCambiarButton(){
		var msgText = '<h3 style="color: red;">!! Cambio de Codigos de Empleados !!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea realizar el cambio de los Codigos de Empleado ?'+'<br>'+'</p>';	
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
