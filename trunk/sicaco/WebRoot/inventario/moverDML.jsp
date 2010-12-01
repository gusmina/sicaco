<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#cantMoverId").numeric();
  });
</script>

<html:form action="${_accion}" method="post" styleId="formId">
	<table>
		<tr>
			<td>
				<label><bean:message key="lbl.mover.bodega2" />:</label>
			</td>
			<td>
				<html:select property="bodega2" styleId="bodega2"
					styleClass="obligatorio" value="${form.bodega2}"
					onchange="ajaxCallNormal('${pageContext.request.contextPath}/inventario${_accion}.do','accion=cargarListaEstantes&bodega2='+$('#bodega2').val(),'estantes')" >
					<html:optionsCollection label="bodNombre" name="listaBodegas" value="bodId" />
				</html:select>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="estantes"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="celdas"></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><bean:message key="lbl.mover.existencia" />:</label>
			</td>
			<td>
				<html:text property="eboCantidadProducto" value="${form.eboCantidadProducto}" disabled="true"/>
			</td>
		</tr>
		<tr>
			<td>
				<label><bean:message key="lbl.mover.saldoActual" />:</label>
			</td>
			<td>
				<html:text property="eboSaldo" value="${form.eboSaldo}" disabled="true"/>
			</td>
		</tr>
		<tr>
			<td>
				<label><bean:message key="lbl.mover.cantMover" />:</label>
			</td>
			<td>
				<html:text property="cantMover" styleId="cantMoverId" size="15" maxlength="15" value="${form.cantMover}" />
			</td>
		</tr>
		<tr>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.mover.move"/>
				</html:submit>
			</td>
		</tr>
	</table>
	<html:hidden property="bodega" value="${form.bodega}"/>
	<html:hidden property="artCod" value="${form.artCod}"/>
</html:form>
