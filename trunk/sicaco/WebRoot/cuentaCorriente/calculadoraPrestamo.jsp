<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
<!--
			$(document).ready(function(){
			$("#preMontoSolicitado").numeric({allow:"."});
 		 });

	function calcularCuota(){
		ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do','accion=calcularCuota&preMontoSolicitado='+$('#preMontoSolicitado').val()+'&interesSolicitado='+$('#interesSolicitadoId').val()+'&mesesSolicitados='+$('#mesesSolicitadosId').val(),'resultado');
	}
//-->
</script>

<html:form action="${_accion}" method="post" styleId="formId">
	<table border="0" align="center">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.pre.montoSolicitado" />
				</label>
			</td>
			<td>
				<html:text property="preMontoSolicitado" styleClass="obligatorio"
					 styleId="preMontoSolicitado" onkeyup="dosDecimales($('#preMontoSolicitado').val(),'preMontoSolicitado');"/>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.pre.plmDuracion"/>
				</label>
			</td>
			<td>
				<html:text property="mesesSolicitados"  styleClass="obligatorio"  styleId="mesesSolicitadosId" />
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.pre.tasaInteres" />
				</label>
			</td>
			<td>
				<html:text property="interesSolicitado" styleClass="obligatorio"
					 styleId="interesSolicitadoId" onkeyup="dosDecimales($('#interesSolicitadoId').val(),'interesSolicitado');"/>
			</td>
		</tr>
	</table>
	<div id="resultado">
	</div>	
	<table align="center">
		<tr>
			<td>
				<input type="button" value="Calcular Cuota" onclick="calcularCuota()"> 
			</td>
			<td>
					<input type="button" value="Limpiar"
						onclick="cleanFields('formId');">
			</td>
		</tr>
	</table>
</html:form>
