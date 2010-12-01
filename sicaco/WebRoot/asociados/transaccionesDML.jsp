<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">

	$(document).ready(function() {
		$("#chequeNum").numeric();
		$("#chequeNumD").numeric();
	
		$("#eliminarRpe").click(function() {
				var jpos="";
			$(".posicionMov").each(function(){
					if(this.checked){
						jpos+= "&posicionMov[]=" + this.value;
					}
			});
			ajaxCallSincrono('${pageContext.request.contextPath}/asociados${_accion}.do','accion=eliminarMovimientos'+jpos,'listaTransacciones');
			cargarDisponible();
		});	
	});
	
	function guardar(){
		ajaxCallSincrono('${pageContext.request.contextPath}/asociados${_accion}.do',
			'accion=guardarMovimiento&fuente='+$('#fuenteId').val()+'&a='+$('#vfuente').val()
			+'&destino='+$('#destinoId').val()+'&b='+$('#vdestino').val()+'&txaMonto='+$('#txaMonto').val()
			+'&txaMontoD='+$('#txaMontoD').val()+'&banId='+$('#banId').val()
			+'&chequeNum='+$('#chequeNum').val()+'&cuentaFuente='+$('#cuentaFuente').val()
			+'&banIdD='+$('#banIdD').val()+'&chequeNumD='+$('#chequeNumD').val()
			+'&cuentaDestino='+$('#cuentaDestino').val()
			+'&rckId='+$('#rckId').val()
			+'&dispo='+$('#dispo').val()
			+'&ascId='+$('#ascId').val()
			+'&cuentaBan='+$('#cuentaBanId').val()
			+'&cant='+$('#cantId').val(),'listaTransacciones');
		
		setTimeout("cargarDisponible()",150);
	};
	
	function cargarDisponible(){
		ajaxCallSincrono('${pageContext.request.contextPath}/asociados${_accion}.do',
			'accion=htmlCampoDisponibilidad&cuentaFuente='+$('#cuentaFuente').val() + 
			'&cant=' + $('#cantId').val(),'cantDispId');
	};
	
	function cargarCuentas(){
		ajaxCallSincrono('${pageContext.request.contextPath}/asociados${_accion}.do',
			'accion=htmlBancarias&banIdD='+$('#banIdD').val() +
			'&ascId2='+$('#ascId2').val(),'bancarias');
	};
	
	function cargarCorrelativo(){
		ajaxCallSincrono('${pageContext.request.contextPath}/asociados/transaccionAsociado.do',
			'accion=cargarNumCheque&rckId='+$('#rckId').val() +
			'&banIdD='+$('#banIdD').val(),'valoresCheque');
	};
	
	function cargarRepositorioYCorrelativo(){
		ajaxCallSincrono('${pageContext.request.contextPath}/asociados/transaccionAsociado.do',
			'accion=cargarRepositorio&banIdD='+$('#banIdD').val(),'valoresBanco');
		ajaxCallSincrono('${pageContext.request.contextPath}/asociados/transaccionAsociado.do',
			'accion=cargarNumCheque&banIdD='+$('#banIdD').val(),'valoresCheque');
	};
	
	//valor 3 = prestamo, 4 = seguro
	function cargarDisponibleDestino(valor){
		ajaxCallSincrono('${pageContext.request.contextPath}/asociados/transaccionAsociado.do',
			'accion=cargarDispDestino' +
			'&cuentaDestino='+$('#cuentaDestino').val() +
			'&pos='+valor,'dispDest','dispo');
	};
</script>

<html:form action="${_accion}" method="post" styleId="formId">
	<table border="0">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.txc.comprobante" />
				</label>
			</td>
			<td>
				<html:text property="txaComprobante" value="${form.txaComprobante}" 
					styleId="comprobId" styleClass="obligatorio" readonly="true"
					size="5" maxlength="15"/>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.txc.fuente" />:
				</label>
			</td>
			<td colspan="2">
				<logic:present name="setFuente" >
					<logic:notEqual value="1" name="setFuente">
						<html:select property="fuente"  value="${form.fuente}" styleId="fuenteId"  
							onchange="ajaxCallSincrono('${pageContext.request.contextPath}/asociados${_accion}.do','accion=cargarHtml&fuente='+$('#fuenteId').val()+'&ascId2='+$('#ascId2').val(),'valoresFuente','vfuente');">
							<html:option value="-1">...</html:option>
							<html:option value="1">Abono  -  Efectivo</html:option>
							<html:option value="2">Abono  -  Cheque</html:option>
							<html:option value="3">Retiro -  Ahorros</html:option>
							<html:option value="4">Abono  -  Transacci&oacute;n electr&oacute;nica</html:option>				
						</html:select>
					</logic:notEqual>
					<logic:equal value="1" name="setFuente">
						<html:hidden property="cuentaFuente" styleId="cuentaFuente" value="${form.cuentaFuente}"/>
						<html:text property="source" value="${form.source}" styleId="sourceId" readonly="true"/>
					</logic:equal>
				</logic:present>
			</td>
		</tr>
		<tr>
			<td>
				<logic:present name="setFuente">
					<logic:equal value="1" name="setFuente">
						<label>
							<bean:message key="lbl.txc.disponible" />:
						</label>
					</logic:equal>
				</logic:present>
			</td>
			<td colspan="2">
				<logic:present name="setFuente">
					<logic:equal value="1" name="setFuente">
						<div id="cantDispId" > 
							<html:text property="cantidadDisponible" value="${form.cantidadDisponible}" styleId="cantDisp" readonly="true"/>
					 	</div> 
					</logic:equal>
				</logic:present>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				<div id="valoresFuente">
				</div>
			</td>
		</tr>
		<logic:present name="setFuente" >
			<logic:equal value="1" name="setFuente">
				<tr>
					<td>
						<label>
							<bean:message key="lbl.txc.destinos" />
						</label>
					</td>
				</tr>
				<tr>
					<td>
						<label><bean:message key="lbl.txc.monto" /> </label>
					</td>
					<td>
						<html:text property="txaMonto" styleId="txaMonto" size="15" maxlength="15" />
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
							onchange="ajaxCallSincrono('${pageContext.request.contextPath}/asociados${_accion}.do','accion=cargarHtml2&destino='+$('#destinoId').val()+'&ascId2='+$('#ascId2').val(),'valoresDestino','vdestino');">
							<html:option value="-1">...</html:option>
							<html:option value="1">Efectivo</html:option>
							<html:option value="2">Cheque</html:option>
							<html:option value="3">Aportaciones</html:option>
							<html:option value="4">Ahorros</html:option>
							<html:option value="5">Prestamos</html:option>
							<html:option value="6">Seguros</html:option>
							<html:option value="7">Transacci&oacute;n electr&oacute;nica</html:option>				
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="5"><br>
						<div id="valoresDestino">
							<html:hidden property="b" styleId="vdestino"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Guardar" onclick="guardar();"/>
					</td>
					<td>
						<input type="button" value="Eliminar" id="eliminarRpe" />
					</td>
				</tr>
			</logic:equal>
		</logic:present>
	</table>
	<div id="listaTransacciones">
		${listaT}
	</div>
	<table border="0">
		<logic:present name="setFuente" >
			<logic:notEqual value="1" name="setFuente">
				<html:submit property="accion">
					<bean:message key="cmd.txc.guardarFuente" />
				</html:submit>
			</logic:notEqual>
			<logic:equal value="1" name="setFuente">
				<html:submit property="accion">
					<bean:message key="cmd.txc.realizarTransaccion" />
				</html:submit>			
			</logic:equal>
		</logic:present>
		<html:submit property="accion">
			<bean:message key="cmd.txc.cancelar" />
		</html:submit>
	</table>
	<html:hidden property="ascId" value="${form.ascId}" styleId="ascId"/>
	<html:hidden property="ascId2" value="${form.ascId2}" styleId="ascId2"/>
	<html:hidden property="idMapaAhorro" value="${form.idMapaAhorro}" styleId="idMapa"/>
	<html:hidden property="fuente" value="${form.fuente}" styleId="fuenteId" />
	<html:hidden property="cant" value="${form.cant}" styleId="cantId"/>
	<html:hidden property="banId" value="${form.banId}" styleId="banId"/>
</html:form>