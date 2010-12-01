<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
 

<script type="text/javascript">
<!--
	$(document).ready(function(){	
	$("#ascCodigoAsociado").alphanumeric();
	$("#preId").alphanumeric();
	$("#ascCodigo").alphanumeric();
	$("#refCodigo").alphanumeric();
	$("#casFechaApertura").numeric();
	$("#primerNombre").alpha();
	$("#segundoNombre").alpha();
	$("#primerApellido").alpha();
	$("#segundoApellido").alpha();
  });
  
//-->
</script>

<html:form action="${_accion}" method="post" styleId="formId">
	<table border="0" align="center">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascCodigo" />
				</label>
			</td>
			<td>
				<html:text property="ctaAscAsociado.ascCodigoAsociado" styleClass="obligatorio"
					 styleId="ascCodigoAsociado" />
			</td>
						<td>
				<label>
					<bean:message key="lbl.asc.ascCodigoTrabajo" />
				</label>
			</td>
			<td>
				<html:text property="ctaAscAsociado.ascCodigo" styleClass="obligatorio" styleId="ascCodigo"/>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascPrimerNombre" />
				</label>
			</td>
			<td>
				<html:text property="ctaAscAsociado.secPerPersona.perPrimerNombre"
					styleClass="obligatorio"
					
					styleId="primerNombre" />
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascSegundoNombre" />
				</label>
			</td>
			<td>
				<html:text property="ctaAscAsociado.secPerPersona.perSegundoNombre"
					styleClass="obligatorio"
					
					styleId="segundoNombre" />
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascPrimerApellido" />
				</label>
			</td>
			<td>
				<html:text property="ctaAscAsociado.secPerPersona.perPrimerApellido"
					styleClass="obligatorio"
					
					styleId="primerApellido" />
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascSegundoApellido" />
				</label>
			</td>
			<td>
				<html:text property="ctaAscAsociado.secPerPersona.perSegundoApellido"
					styleClass="obligatorio"
					
					styleId="segundoApellido" />
			</td>
		</tr>
		<tr>
			<td>
			<label>
					<bean:message key="lbl.pre.referencia" />
				</label>:
			</td>
			<td>
				<html:text property="ctaPrePrestamo.preId" styleClass="obligatorio"
					 styleId="preId" />
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.fxp.buscar" />
				</html:submit>
			</td>
			<td>
				<input type="button" value="Limpiar"
					onclick="cleanFields('formId');">
			</td>
		<tr>
	</table>
</html:form>
