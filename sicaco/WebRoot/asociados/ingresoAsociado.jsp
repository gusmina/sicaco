<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<html:form action="${_accion}" method="post" styleId="formId">
	<table align="center">
		<tr>
			<td>
				<label class="obligatorio">
					<bean:message key="lbl.ina.codigo" />:
				</label>
				<label>
					<bean:write name="asociado" property="ascCodigoAsociado" />
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
		<tr>
			<td>
				<label>
					<bean:message key="lbl.ina.fechaIngreso" />
				</label>
				:
			</td>
			<td>
				<html:text styleClass="obligatorio" 
					property="inaFechaIngreso" value="${form.inaFechaIngreso}" readonly="true"/>
			</td>
		</tr>
				<tr>
			<td>
				<label>
					<bean:message key="lbl.ina.fechaSalida" />
				</label>
				:
			</td>
			<td>
				<html:text styleClass="obligatorio" 
					property="inaFechaSalida" value="${form.inaFechaSalida}" readonly="true"/>
			</td>
		</tr>
						<tr>
			<td>
				<label>
					<bean:message key="lbl.ina.nota" />
				</label>
				:
			</td>
			<td>
				<html:textarea styleClass="obligatorio" 
					property="ctaNotNotas.notNota" value="${form.ctaNotNotas.notNota}" readonly="true"/>
			</td>
		</tr>
	</table>
	<table border="0" align="center">
		<tr>
			<td>
						<html:submit property="accion">
					<bean:message key="cmd.ina.regresar" />
		</html:submit>
			</td>
		</tr>
	</table>
	<html:hidden property="ascId" value="${form.ascId}" />
</html:form>