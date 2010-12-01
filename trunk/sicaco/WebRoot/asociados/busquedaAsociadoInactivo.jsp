<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
<!--
	$(document).ready(function(){
	$("#ascCodigoAsociado").alphanumeric();
	$("#ascCodigo").alphanumeric();
	$("#primerNombre").alpha();
	$("#segundoNombre").alpha();
	$("#primerApellido").alpha();
	$("#segundoApellido").alpha();
  });
//-->
</script>

<html:form action="${_accion}" method="post" styleId="formId">
	      <script>
      		var patternDui = new Array(${tamDui}-1,1);
      </script>
	<table border="0" align="center">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascCodigo" />
				</label>
			</td>
			<td>
				<html:text property="ascCodigoAsociado" styleClass="obligatorio" styleId="ascCodigoAsociado"/>
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascCodigoTrabajo" />
				</label>
			</td>
			<td>
				<html:text property="ascCodigo" styleClass="obligatorio" styleId="ascCodigo"/>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascNit" />
				</label>
			</td>
			<td>
				<html:text property="secPerPersona.perNit" styleClass="obligatorio" onkeyup="maskNit(this);" value="${form.secPerPersona.perNit}"/>
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascDui" />
				</label>
			</td>
			<td>
				<html:text property="secPerPersona.perDui" styleClass="obligatorio" size="${tamDui}" onkeyup="maskDui(this);" value="${form.secPerPersona.perDui}"/>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascPrimerNombre" />
				</label>
			</td>
			<td>
				<html:text property="secPerPersona.perPrimerNombre" styleClass="obligatorio" value="${form.secPerPersona.perPrimerNombre}" styleId="primerNombre"/>
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascSegundoNombre" />
				</label>
			</td>
			<td>
				<html:text property="secPerPersona.perSegundoNombre" styleClass="obligatorio" value="${form.secPerPersona.perSegundoNombre}" styleId="segundoNombre"/>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascPrimerApellido" />
				</label>
			</td>
			<td>
				<html:text property="secPerPersona.perPrimerApellido" styleClass="obligatorio" value="${form.secPerPersona.perPrimerApellido}" styleId="primerApellido"/>
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascSegundoApellido" />
				</label>
			</td>
			<td>
				<html:text property="secPerPersona.perSegundoApellido" styleClass="obligatorio" value="${form.secPerPersona.perSegundoApellido}" styleId="segundoApellido"/>
			</td>
		</tr>
	</table>
	
	<table align="center">
		<tr>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.asc.buscarInactivo" />
				</html:submit>
			</td>
			<td>
				<input type="button" value="Limpiar"
					onclick="cleanFields('formId');">
			</td>
		<tr>
	</table>
	<html:hidden property="inactive"/>
</html:form>