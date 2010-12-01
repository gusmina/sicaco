<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post" styleId="formId">
	<table align="center">
		<tr>
			<td><label><bean:message key="lbl.proveedorOrden.lineaPrestamo"/>:</label></td>
			<td>
				<html:select property="lprId" styleClass="obligatorio" styleId="lprId"
					onchange="ajaxCallNormal('${pageContext.request.contextPath}/inventario${_accion}.do',
						'accion=cargarTipos&lprId='+$('#lprId').val(),'tipos')">
					<html:option value="-1">-Seleccione una linea de prestamo-</html:option>
					<html:optionsCollection name="lineas" label="lprNombre" value="lprId"/>
				</html:select>
			</td> 
		</tr>
		<tr>
			<td><label><bean:message key="lbl.proveedorOrden.tipoPrestamo"/>:</label></td>
			<td>
				<div id="tipos">
					<html:select property="iucTutTarTprH.ctaTprTipoPrestamo.tprId" styleClass="obligatorio">
						<html:option value="-1">-Seleccione un tipo de prestamo-</html:option>
						<html:optionsCollection name="tipoPrestamo" label="tprNombre" value="tprId"/>
					</html:select>
				</div>
			</td> 
		</tr>
	</table>
	<table align="center">
			<tr>
			<td colspan="2">
				<html:submit property="accion">
	  				<bean:message key="cmd.articuloPrestamo.guardar" />
	  			</html:submit>
	  			<html:submit property="accion">
	  				<bean:message key="cmd.articuloPrestamo.Regresar" />
	  			</html:submit>
	  		</td>
  		</tr>
	</table>
	<html:hidden property="tarId2"/>
	<html:hidden property="tutId"/>
</html:form>