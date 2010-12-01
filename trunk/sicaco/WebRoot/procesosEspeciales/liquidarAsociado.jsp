<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<link rel="stylesheet" type="text/css" href="../css/zebra.css" >
<script type="text/javascript">
	$(document).ready(function(){
		//esconde o muestra el contenido del div
	$(".msg_head").click(function(){
		$(this).next(".msg_body").slideToggle(425);
	$("#liquidarA").numeric({allow:"."});
	$("#liquidarP").numeric({allow:"."});
	$("#liquidarS").numeric({allow:"."});
	});
		ajaxCallSincrono('${pageContext.request.contextPath}/procesosEspeciales/liquidacionAsociado.do','accion=cargarDependientes&ascId='+$('#ascId').val(),'deps');
		cargarCuentas();
	});
	
	function cargarCuentas(){
		ajaxCallSincrono('${pageContext.request.contextPath}/procesosEspeciales/liquidacionAsociado.do','accion=cargarCuentasYPrestamos&ascId='+$('#ascId').val(),'resps');
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
}

.msg_body {
	padding: 5px 10px 15px;
	background-color: auto;
}
</style>
<html:form action="${_accion}" method="post" styleId="formId">
	<p class="msg_head" align="center">
		<bean:message key="lbl.asc.ascInformacion" />
	</p>
	<div class="msg_body">
	<table class="obligatorio" align="center">
		<tr>
			<td>
			<label>
					<bean:message key="lbl.asc.ascCodigo" />
					:
				</label>
			</td>
			<td>
			<a href="javascript:void(0)" onclick="cargarCuentas()">
					<label><bean:write name="asociado" property="ascCodigoAsociado" /><label>
			</a>
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascNit" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociado" property="secPerPersona.perNit" />
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascDui" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociado" property="secPerPersona.perDui" />
				</label>
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascPrimerNombre" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociado"
						property="secPerPersona.perPrimerNombre" />
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascSegundoNombre" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociado"
						property="secPerPersona.perSegundoNombre" />
				</label>
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascPrimerApellido" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociado"
						property="secPerPersona.perPrimerApellido" />
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascSegundoApellido" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociado"
						property="secPerPersona.perSegundoApellido" />
				</label>
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascApellidoCasada" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociado"
						property="secPerPersona.perApellidoCasada" />
				</label>
			</td>
		</tr>
	</table>
	</div>
	<p class="msg_head" align="center"> 
		Dependientes
	</p>
	<div class="msg_body">
	<div id="deps" align="center">
	</div>
	</div>
	<div id="resps" align="center">
	</div>
	<table align="center">
		<tr>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.liq.generarReporteLiquidacion" />
				</html:submit>
				&nbsp;
			</td>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.liq.liquidar" />
				</html:submit>
				&nbsp;
			</td>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.liq.regresar" />
				</html:submit>
			<td>
			</td>
		</tr>
	</table>
	<html:hidden property="ascId" styleId="ascId" />
</html:form>