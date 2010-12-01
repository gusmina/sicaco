<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<html:form action="${_accion}" method="post" styleId="formId">

	<table border="0" align="center">
		<tr>
			<td>
				<label>
					Numero de D&iacute;as para Proyecci&oacute;n:
				</label>
			</td>
			<td>
				<html:text property="mesesProyeccion" styleClass="obligatorio"  styleId="mesesId"/>
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.liq.proyectar" />
				</html:submit>
			</td>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.liq.regresar" />
				</html:submit>
			</td>
		<tr>
	</table>
	<html:hidden property="ascId" styleId="ascId" />
	<html:hidden property="mesesProyeccion" styleId="mesesId" />
</html:form>