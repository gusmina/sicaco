<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<link rel="stylesheet" type="text/css" href="../css/zebra.css" >

<logic:present name="listaChequesPendientes">
	<center>
	<html:errors property="indicesSeleccionados"/>
	<html:form action="/impresionCheques" method="POST">
	<div id="tabla">
	<table id="hor-zebra" style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;font-size: 13px;margin-bottom: 20px;margin-top:20px;margin-left: auto;margin-right: auto;text-align: left;border-collapse: collapse;padding: 20px 20px 20px 20px; width:80%;">
		<thead>
			<tr>
				<th >Imprimir</th>
				<th >Nº Cheque</th>
				<th >Emitido a</th>
				<th >Monto</th>
				<th >Fecha Creaci&oacute;n</th>
			</tr>
		</thead>
		<tbody>
		<logic:iterate id="cuenta" name="listaChequesPendientes" indexId="i" >
			<tr>
				<td>
					<input type="checkbox" name="indicesSeleccionados" value="${i}" />
				</td>
				<td>
					<bean:write name="cuenta" property="ctaChkChequePrestamo.chkCorrelativoCheque" format="00000000" />
					<input type="hidden" name="pcoIds" value="${cuenta.pcoId}" />
				</td>
				<td>
					<bean:write name="cuenta" property="ctaChkChequePrestamo.chkEmitidoA" />
				</td>
				<td>
					<bean:write name="cuenta" property="ctaChkChequePrestamo.chkMontoEmitido" format="$#,000.00" />
				</td>
				<td>
					<bean:write name="cuenta" property="audFechaCreacion" format="dd-MM-yyyy HH:mm:ss"/>
				</td>
			</tr>
		</logic:iterate>
		</tbody>
	</table>
	</div>
	<br>
	<html:submit property="accion">
		<bean:message key="cmd.impche.imprimir"/>
	</html:submit>
	</html:form>
	</center>
</logic:present>