<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	function saveSeleccionA(valor) {
		valores = valor.split(';')
		$('#ascCodigo3Id').val(valores[0])
		$('#ascNombreId').val(valores[1])
		$('#codice').val(valores[2])
		$('#creditoId').val(valores[3])
		//$('#idPrestamo').val(valores[4])
		$('#resultadoAsc').hide('slow')
	}
	
</script>
<script type="text/javascript">
	$(document).ready(function(){   	
    	$("#proCodId").val('${compra.invProProveedor.proCodigo}');
    	$("#proNombreId").val('${compra.invProProveedor.proNombre}');
    	$("#refCuentaId").val('${ordRefCuentaReferencia.refCuenta}');
    	$("#ascCodigoId").val('${asociado.ascCodigoAsociado}');
    	$("#ascNombreId").val('${asociado.ascNombreNit}');
    	$("#creditoId").val('${credito}');  
    	$("#ocoMontoId").val('${compra.ocoMonto}');        	
    	$("#ocoSaldoId").val('${compra.ocoSaldo}');
    	$("#ocoEmisionId").val('${ocoEmision}');
    	$("#ocoVencimientoId").val('${ocoVencimiento}');
    	$("#ocoElaboradoId").val('${compra.ocoElaborado}');
    	
    	$("#ocoId").val('${compra.ocoId}');
    	
  });
</script>

<html:form action="/peticionesCompra" method="post" styleId="formId" >
  <table border="0">
    <tr>
		<td>
			<label><bean:message key="lbl.encabezado.invProProveedor.proCodigo" />:2</label>
		</td>
		<td><input type="text" readonly="readonly" value="" id="proCodId" class="obligatorio"/></td>
		<td>
			<label><bean:message key="lbl.encabezado.nombre" />:</label>
		</td>
		<td><input type="text" readonly="readonly" value="" id="proNombreId" class="obligatorio"/></td>
	</tr>
	<tr>
      <td><label><bean:message key="lbl.ordcom.ordRefCuentaReferencia.refId" />:</label></td>
      <td><input type="text" readonly="readonly" value="" id="refCuentaId" class="obligatorio"/></td>
    </tr>
	<tr>
		<td>
			<label><bean:message key="lbl.ordcom.secAscAsociado.Id.ascCodigo" />:</label>
		</td>
		<td><input type="text" readonly="readonly" value="" id="ascCodigoId" class="obligatorio"/></td>
		<td>
			<label><bean:message key="lbl.ordcom.ascNombre" />:</label>
		</td>
		<td><input type="text" readonly="readonly" value="" id="ascNombreId" class="obligatorio"/></td>
	</tr>
	<tr>
      <td><label><bean:message key="lbl.ordcom.credito" />:</label></td>
      <td><input type="text" readonly="readonly" value="" id="creditoId" class="obligatorio"/></td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.ordcom.ocoMonto" />:</label></td>
      <td><input type="text" readonly="readonly" value="" id="ocoMontoId" class="obligatorio" /></td> 
    </tr>
  
    <tr>
      <td><label><bean:message key="lbl.ordcom.ocoSaldo" />:</label></td>
      <td><input type="text" readonly="readonly" value="" id="ocoSaldoId" class="obligatorio"/></td>
    </tr>
  		<tr>
      <td><label><bean:message key="lbl.ordcom.ocoEmision" />:</label></td>
      <td><input type="text" readonly="readonly" value="" id="ocoEmisionId" class="obligatorio"/></td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.ordcom.ocoVencimiento" />:</label></td>
      <td><input type="text" readonly="readonly" value="" id="ocoVencimientoId" class="obligatorio"/></td>
    </tr>
 	<tr>
      <td><label><bean:message key="lbl.ordcom.ocoElaborado" />:</label></td>
      <td><input type="text" readonly="readonly" value="" id="ocoElaboradoId" class="obligatorio"/>     	
      </td>
    </tr>
    <tr> 
      <td colspan="4" align="center">
			<html:submit property="accion"><bean:message key="cmd.peticionesCompra.imprimeOrden" bundle="cliente"/>
			</html:submit>
			<html:submit property="accion"><bean:message key="cmd.peticionesCompra.cancelar" bundle="cliente"/>
			</html:submit>
			<html:submit property="accion"><bean:message key="cmd.peticionesCompra.ordenes" bundle="cliente"/>
			</html:submit>			
      </td>
    </tr>
  </table>
  <html:hidden property="ocoId" styleId="ocoId"/>
</html:form>