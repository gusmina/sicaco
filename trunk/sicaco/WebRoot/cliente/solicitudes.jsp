<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<link rel="stylesheet" type="text/css" href="../css/zebra.css" >
<script type="text/javascript">
	$(document).ready(function(){
    	$("#txaMonto").numeric({allow:"."});
	});
  
  
	function cargarCuentas(){
		ajaxCallSincrono('${pageContext.request.contextPath}/cliente${_accion}.do',
			'accion=htmlBancarias&banIdD='+$('#banIdD').val(),'bancarias');
	};	
	
	function cargarDisponible(){
		ajaxCallSincrono('${pageContext.request.contextPath}/cliente${_accion}.do',
			'accion=disponibilidad&cuenta='+$('#cuenta').val(),'disp');
	}
	
</script>


<html:form action="${_accion}" method="post" styleId="formId">

	<div>
	Estimados asociados: <br/>
	Las transacciones que se realicen despu&eacute;s de las 3:30 P.M. <br/> 
	ser&aacute;n procesadas hasta el siguiente d&iacute;a h&aacute;bil.
	</div>

	<table align="center" style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px; 
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 800px;
		border-collapse: collapse;
		padding: 10px 10px 10px 10px;" id="hor-zebra">
		<tr>
			<th colspan="2" align="center">Solicitudes en l&iacute;nea </th>
		</tr>
		<tr>
			<td align="right"> <div></div>
				<label> Tipo de solicitud :</label>
			</td>
			<td align="center">
				<html:select property="opSolicitud" styleId="opSolicitudId" value="${form.opSolicitud}" onchange="ajaxCallSincrono('${pageContext.request.contextPath}/cliente${_accion}.do','accion=cargarSolicitudes&opSolicitud='+$('#opSolicitudId').val(),'cuentas');">
					<html:option value="-1">...</html:option>
					<html:option value="1"> Retiro de ahorros</html:option>
					<html:option value="2">Solicitud de orden de compra</html:option>
				</html:select>
			</td>
		</tr>
		</table>
		<table align="center">
		<tr>
			<td align="center">
				<div id="cuentas"></div>
			</td>
		</tr>
		
			<tr>
				<td colspan ="2" align="center">
					<html:submit property="accion">
						<bean:message key = "cmd.solicitudes.realizar"/>
					</html:submit>
				</td>
			</tr>		
	</table>
</html:form>
