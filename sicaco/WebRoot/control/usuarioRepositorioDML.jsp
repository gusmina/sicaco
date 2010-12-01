<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<html:form action="${_accion}" method="post" styleId="formId">
	<table border="0">
		
			
			<logic:present name="filtro">
			<logic:equal name="filtro" value="1">
			<tr>
				<td>
					<label>
						<bean:message
							key="lbl.usuarioRepositorio.id.ctrRfcRepositorioFacturas.rfcNombre" />
					</label>
				</td>
				<td>
					<html:select property="ctrRfcRepositorioFacturas.rfcId"
						styleClass="obligatorio">
						<html:optionsCollection name="listRepositorios" value="rfcId"
							label="rfcNombre" />
					</html:select>
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
				<tr>
					<td>
						<label>
							<bean:message
								key="lbl.usuarioRepositorio.id.secPerPersona.perNombre" />
						</label>
					</td>
					<td>
						<html:select property="secPerPersona.perId"
							styleClass="obligatorio">
							<html:optionsCollection name="listUsuarios" value="id"
								label="nombresU" />
						</html:select>
						<span><bean:message key="msg.obligatorio" /> </span>
					</td>
				</tr>
				<tr>
					<td>
						<html:submit property="accion">
							<bean:message key="cmd.usuarioRepositorio.agregar" />
						</html:submit>
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="filtro" value="2">
				<tr>
				<td>
					<label>
						<bean:message
							key="lbl.usuarioRepositorio.id.ctrRfcRepositorioFacturas.rfcNombre" />
					</label>
				</td>
				<td>
					<html:select property="ctrRfcRepositorioFacturas.rfcId"
						styleClass="obligatorio">
						<html:optionsCollection name="listRepositorios" value="rfcId"
							label="rfcNombre" />
					</html:select>
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
				<tr>
					<td><label><bean:message key="lbl.usuarioRepositorio.sucursal" /></label></td>
					<td>
						<html:select property="sucId">
							<html:optionsCollection  label="sucNombre" name="sucursales" value="sucId"/>     
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<html:submit property="accion">
							<bean:message key="cmd.usuarioRepositorio.guardar2" />
						</html:submit>
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="filtro" value="0">
				<%--<tr>
					<td>
						<label>
							<bean:message key="lbl.persona.perPrimerNombre" />
							:
						</label>
					</td>
					<td>
						<html:text property="secPerPersona.perPrimerNombre"
							styleClass="obligatorio" />

					</td>
					<td>
						<label>
							<bean:message key="lbl.persona.perSegundoNombre" />
							:
						</label>
					</td>
					<td>
						<html:text property="secPerPersona.perSegundoNombre"
							styleClass="obligatorio" />

					</td>
				</tr>
				<tr>
					<td>
						<label>
							<bean:message key="lbl.persona.perPrimerApellido" />
							:
						</label>
					</td>
					<td>
						<html:text property="secPerPersona.perPrimerApellido"
							styleClass="obligatorio" />
					</td>
					<td>
						<label>
							<bean:message key="lbl.persona.perSegundoApellido" />
							:
						</label>
					</td>
					<td>
						<html:text property="secPerPersona.perSegundoApellido"
							styleClass="obligatorio" />

					</td>
				</tr>
				  <tr>
					<td>
						<label>
							<bean:message key="lbl.rfc.rfcNombre" />
							:
						</label>
					</td>
					<td>
						<html:text property="ctrRfcRepositorioFacturas.rfcNombre"
							styleClass="obligatorio" />

					</td>
					<td>
						<label>
							<bean:message key="lbl.rfc.ctrCfcControlFacturacion.cfcSerie" />
							:
						</label>
					</td>
					<td>
						<html:text property="ctrRfcRepositorioFacturas.ctrCfcControlFacturacion.cfcSerie"
							styleClass="obligatorio" />

					</td>
				</tr> 
				<tr>
					<td>
						<html:submit property="accion">
							<bean:message key="cmd.usuarioRepositorio.buscar" />
						</html:submit>
					</td>
				</tr>--%>
			</logic:equal>
		</logic:present>
	</table>
	<html:hidden property="secPerPersona.perPrimerNombre" value="${form.secPerPersona.perPrimerNombre}" />
</html:form>