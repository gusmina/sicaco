<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript">
	$(document).ready(function(){
		var variable = $("#estadoPrestamo").val();
		if(variable != 14){
			$("#guardar").attr("disabled","disabled");
			$("#estadoValidez").attr("disabled","disabled");
			$("#parentesco").attr("disabled","disabled");
		}
	});
</script>
<html:form action="${_accion}" method="post" styleId="formId">
	<logic:present name="asociado">
		<table align="center">
			<tr>
				<td>
					<label class="obligatorio">
						<bean:message key="lbl.pre.asocInfo" />
						:
					</label>
				</td>
				<td>
					<label>
						<bean:write name="asociado"
							property="secPerPersona.perPrimerNombre" />
					</label>
					&nbsp;
					<label>
						<bean:write name="asociado"
							property="secPerPersona.perSegundoNombre" />
					</label>
					&nbsp;
					<label>
						<bean:write name="asociado"
							property="secPerPersona.perPrimerApellido" />
					</label>
				</td>
				<td>
					<label class="obligatorio">
						<bean:message key="lbl.asc.ascCodigoAsociado" />
						:
					</label>
				</td>
				<td>
					&nbsp;
					<label>
						<bean:write name="asociado" property="ascCodigoAsociado" />
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<label class="obligatorio">
						<bean:message key="lbl.pre.referencia" />
						:
					</label>
				</td>
				<td>
					&nbsp;
					<label>
						<bean:write name="prestamo" property="preId" />
					</label>
				</td>
				<td>
					<label class="obligatorio">
						<bean:message key="lbl.pre.montoSolicitado" />
						:
					</label>
				</td>
				<td>
					&nbsp;
					<label>
						<bean:write name="prestamo" property="preMontoSolicitado"  format="'$'#,###,###.00" />
					</label>
				</td>
			</tr>
		</table>
	</logic:present>
	<table align="center">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.rpe.nombres" />
				</label>
				:
			</td>
			<td>
				<html:text maxlength="100" size="20" property="rpeNombres"
					styleClass="obligatorio" 
					styleId="rpeNombres" readonly="true" />
				<span><bean:message key="msg.obligatorio" /> </span>
			</td>
			<td>
				<label>
					<bean:message key="lbl.rpe.apellidos" />
				</label>
				:
			</td>
			<td>
				<html:text maxlength="100" size="20" property="rpeApellidos"
					styleClass="obligatorio" 
					styleId="rpeApellidos" readonly="true" />
				<span><bean:message key="msg.obligatorio" /> </span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.rpe.parentesco" />
				</label>
				:
			</td>
			<td>
				<html:select property="parId" styleClass="obligatorio" styleId="parentesco">
					<html:optionsCollection name="parList" label="parDescripcion"
						value="parId"  />
				</html:select>
				<span><bean:message key="msg.obligatorio" /> </span>
			</td>
			<td>
				<label>
					<bean:message key="lbl.rpe.direccion" />
				</label>
				:
			</td>
			<td>
				<html:textarea property="rpeDireccion" styleClass="obligatorio"
					 styleId="rpeDireccion" readonly="true"/>
				<span><bean:message key="msg.obligatorio" /> </span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.rpe.telefonos" />
				</label>
				:
			</td>
			<td>
				<html:text maxlength="15" size="20" property="rpeTelefono"
					styleClass="obligatorio" 
					styleId="rpeTelefono" readonly="true" />
				<span><bean:message key="msg.obligatorio" /> </span>
			</td>
			<td>
				<label>
					<bean:message key="lbl.rpe.estadoInvalides" />
				</label>
				:
			</td>
			<td>
				<html:select property="rpeEstadoValidez" styleClass="obligatorio" styleId="estadoValidez">
					<html:option value="I"><bean:message key="lbl.rpe.invalido" /></html:option>
					<html:option value="V"><bean:message key="lbl.rpe.valido" /></html:option>
				</html:select>
				<span><bean:message key="msg.obligatorio" /> </span>
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td>
				<html:submit property="accion" disabled="${!referenciaPersonalForm.mdf}" styleId="guardar">
					<bean:message key="cmd.rpe.modificar" />
				</html:submit>
			</td>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.rpe.regresar" />
				</html:submit>
			</td>
		</tr>
	</table>
		<html:hidden property="preId"  />
		<html:hidden property="rpeId"  />
				<html:hidden property="perId" />
		<html:hidden property="estadoPrestamo" styleId="estadoPrestamo"/>
</html:form>