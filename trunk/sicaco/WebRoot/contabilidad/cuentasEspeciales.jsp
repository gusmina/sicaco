<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<html:form action="${_accion}" method="post" styleId="formId">
	<table>
		<tr>
			<td>
				<label>
				<bean:message key="lbl.cueEsp.excedentes" />:
				</label>
			</td>
			<td>
				<html:select property="cuenta[0]"  title="Seleccione una cuenta" styleClass="obligatorio">
							<html:optionsCollection name="cuentas" label="cueNombre" value="cueId" />
						</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<label>
				<bean:message key="lbl.cueEsp.gastos" />:
				</label>
			</td>
			<td>
				<html:select property="cuenta[1]"  title="Seleccione una cuenta" styleClass="obligatorio">
							<html:optionsCollection name="cuentas" label="cueNombre" value="cueId" />
						</html:select>
			</td>
		</tr>
	</table>
	<table align="center">
	<tr>
				<td>
					<html:submit property="accion">
					<bean:message key="cmd.conf.guardar" />
				</html:submit>
			</td>
		</tr>
	</table>
</html:form>