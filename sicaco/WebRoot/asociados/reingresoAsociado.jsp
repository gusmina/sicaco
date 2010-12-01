<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<html:form action="${_accion}" method="post" styleId="formId">
	<logic:present name="asociadoPadre">
		<label>
			<bean:message key="lbl.asociado.dependeDe" />
		</label>
		<hr>
		<table align="center" class="obligatorio">
<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascCodigo" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociadoPadre" property="ascCodigoAsociado" />
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascPrimerNombre" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociadoPadre"
						property="secPerPersona.perPrimerNombre" />
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascSegundoNombre" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociadoPadre"
						property="secPerPersona.perSegundoNombre" />
				</label>
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascPrimerApellido" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociadoPadre"
						property="secPerPersona.perPrimerApellido" />
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascSegundoApellido" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociadoPadre"
						property="secPerPersona.perSegundoApellido" />
				</label>
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascApellidoCasada" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociadoPadre"
						property="secPerPersona.perApellidoCasada" />
				</label>
			</td>
		</tr>
				<tr>
				<td colspan="4">
				<html:checkbox property="ascPagaraPadre" value="S" styleId="checkboxPadrePaga"><label><bean:message key="lbl.dependiente.pagaPadre" /></label></html:checkbox>
				</td>
			</tr>
		</table>
	</logic:present>
	<label>
		Informacion de Asociado
	</label>
	<hr>
	<table class="obligatorio" align="center">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascCodigo" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociado" property="ascCodigoAsociado" />
				</label>
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascNit" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociado" property="secPerPersona.perNit" />
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascDui" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociado" property="secPerPersona.perDui" />
				</label>
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascPrimerNombre" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociado"
						property="secPerPersona.perPrimerNombre" />
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascSegundoNombre" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociado"
						property="secPerPersona.perSegundoNombre" />
				</label>
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascPrimerApellido" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociado"
						property="secPerPersona.perPrimerApellido" />
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascSegundoApellido" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociado"
						property="secPerPersona.perSegundoApellido" />
				</label>
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascApellidoCasada" />
					:
				</label>
			</td>
			<td>
				<label style="color: black;">
					<bean:write name="asociado"
						property="secPerPersona.perApellidoCasada" />
				</label>
			</td>
		</tr>
	</table>
	<label>
		<bean:message key="lbl.asc.mensajeAfiliacion" />
		:</label>
		<label class="obligatorio"><bean:write name="cuotaAfiliacion" />
	</label>
	<hr>
	<table align="center">
		<tr>
			<td>
				<label class="obligatorio">
					<bean:message key="lbl.asc.cuotaAfiliacionCancelada" />
				</label>
			</td>
			<td>
				<html:select property="ascPagoIngreso" styleClass="obligatorio">
					<html:option value="N">NO</html:option>
					<html:option value="S">SI</html:option>
				</html:select>
			</td>
		</tr>		
	</table>
	<table align="center">
		<tr>
			<td>
				<html:submit property="accion" disabled="${form.comp}">
					<bean:message key="cmd.asc.reactivarAsociado" />
				</html:submit>
			</td>
			<td>
				<html:submit property="accion"
					onclick="$('#comp').val('false');$('#papa').val('');$('#ascId').val('');">
					<bean:message key="cmd.asc.regresarReactivacion" />
				</html:submit>
			</td>
		<tr>
	</table>
	<html:hidden property="ascId" style="id" />
	<html:hidden property="inactive" style="inac" />
	<html:hidden property="comp" styleId="comp" />
	<html:hidden property="ascAsociadoPadre" style="papa" />
	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>