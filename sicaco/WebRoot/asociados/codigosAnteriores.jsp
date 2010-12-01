<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<html:form action="${_accion}" method="post" styleId="formId">
	<table align="center" >
		<tr>
			<td>
				<label class="obligatorio">
					<bean:message key="lbl.cnt.codigoActual" />:
				</label>
				<label>
					<bean:write name="asociado" property="ascCodigo" />
				</label>
			</td>
			<td>
				<label class="obligatorio">
					<bean:message key="lbl.ina.asociado" />:
				</label>
				<label>
					<bean:write name="asociado" property="secPerPersona.perPrimerNombre" />
					&nbsp;
					<bean:write name="asociado" property="secPerPersona.perSegundoNombre" />
					&nbsp;
					<bean:write name="asociado" property="secPerPersona.perPrimerApellido" />
				</label>
			</td>
		</tr>
	</table>
		<table border="0" align="center">
		<html:submit property="accion">
					<bean:message key="cmd.cnt.regresar" />
		</html:submit>
	</table>
</html:form>