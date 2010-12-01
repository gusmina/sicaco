<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
 
	function finalizar(){
	    $('#accionId').val('finalizar');
		$('#formId')[0].submit();
	};
	
	function generarReporte(){
	    $('#accionId').val('generarReporte');
		$('#formId')[0].submit();
		//$('#generarReporteId').attr('disabled','disabled');
	};
  
</script>

<html:form action="${_accion}" method="post"  styleId="formId">

	<logic:notEmpty name="errores" scope="request">
		<table border="0" align="left">
			<tr>
				<td style="text-align: left;"><label style="text-transform: none;font-size: 12px;">Los siguientes pr&eacute;stamos no fueron desembolsados:</label></td>
			</tr>
			<logic:iterate id="cuentaAsociado" name="errores" scope="request">
 				<tr align="left"><td><div style="text-transform: none;font-size: 12px;text-align: left;">* <bean:write name="cuentaAsociado" property="ctaPrePrestamo.preId" /></div></td></tr>
			</logic:iterate>
			<tr><td style="padding-top: 20px;text-align: left;"><label style="text-transform: none;font-size: 12px;">Verifique lo siguiente:</label></td></tr>
			<tr>
				<td>
					<ul>
						<li><label style="text-transform: none;font-size: 12px;">Si realiz&oacute; abonos a aportaciones, que el asociado tenga una cuenta de aportaciones.</label></li>
						<li><label style="text-transform: none;font-size: 12px;">Si el l&iacute;quido a recibir del pr&eacute;stamo es positivo, que el asociado tenga una cuenta bancaria a la cual se le realizar&aacute; el desembolso.</label></li>
						<li><label style="text-transform: none;font-size: 12px;">Si existen abonos a seguros y pr&eacute;stamos, que la cantidad del abono no sea mayor que la que se encuentra como deuda.</label></li>						
						<li><label style="text-transform: none;font-size: 12px;">Que el l&iacute;quido a recibir no sea negativo.</label></li>
					</ul>
				</td>
			</tr>			
		</table>
		<br></br><br></br>
	</logic:notEmpty>
	<logic:notEmpty name="exito" scope="session">
	<div style="clear: both;">
		<label style="text-transform: none;font-size: 12px;color: green">Puede descargar los reportes de desembolsos de préstamos exitosos a continuaci&oacute;n.</label>
	</div>
	<div style="clear: both;padding-top: 20px;">
		<input type="button" value="Generar Reportes de Liquidaci&oacute;n" onclick="generarReporte();" id="generarReporteId">
		<!--  <a href="${pageContext.request.contextPath}/cuentaCorriente/desembolsoPrestamos.do&accion=generarReporte" ><img alt="" src="${pageContext.request.contextPath}/imagenes/download_icon1.gif"/></a>
		<div style="clear: both;">
			<label style="text-transform: none;font-size: 12px;">Descargar</label>
		</div>-->
	</div>
	</logic:notEmpty>
	<div style="clear: both;padding-top: 20px;">
		<input type="button" value="Finalizar" onclick="finalizar();">
	</div>
	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>