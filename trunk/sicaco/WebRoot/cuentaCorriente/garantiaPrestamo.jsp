<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript">
	$(document).ready(function(){
	$("#tipoGarantia").attr("disabled","disabled");
	$("#garValor").numeric();
		var variable = $("#estadoPrestamo").val();
		if(variable != 14){
			$("#garInspeccion").attr("disabled","disabled");
			$("#guardar").attr("disabled","disabled");
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
								property="secPerPersona.perPrimerNombre"  />
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
							<bean:write name="prestamo" property="preMontoSolicitado" format="'$'#,###,###.00"/>
						</label>
					</td>
				</tr>
			</table>
		</logic:present>
	<table align="center">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.gar.nombreGarantia" />
					:
				</label>
			</td>
			<td>
				<html:text maxlength="50" size="15" property="garNombreInmueble"
					styleClass="obligatorio" 
					styleId="garNombreInmueble" readonly="true"/>
				<span><bean:message key="msg.obligatorio" /> </span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.gar.tipoGarantia" />
				</label>
			</td>
			<td>
				<html:select property="ctaTgaTipoGarantia.tgaId"
					styleId="tipoGarantia" styleClass="obligatorio" >
					<html:optionsCollection label="tgaNombre" name="tipoGarantias"
						value="tgaId" />
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.gar.valorGarantia" />
					:
				</label>
			</td>
			<td>
				<html:text property="garValor" styleClass="obligatorio"
					maxlength="50" size="15"
					styleId="garValor" readonly="${!garantiaPrestamoForm.mdf}"/>
				<span><bean:message key="msg.obligatorio" /> </span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.gar.descripcionGarantia" />
					:
				</label>
			</td>
			<td>
				<html:textarea property="garDescripcionInmueble"
					styleClass="obligatorio" 
					styleId="garDescripcionInmueble" readonly="true"/>
				<span><bean:message key="msg.obligatorio" /> </span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.gar.ubicacionGarantia" />
					:
				</label>
			</td>
			<td>
				<html:textarea property="garUbicacion" styleClass="obligatorio"
					 styleId="garUbicacion" readonly="true"/>
				<span><bean:message key="msg.obligatorio" /> </span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.gar.inspeccionadaGarantia" />
					:
				</label>
			</td>
			<td>
				<html:select property="garInspeccion" styleClass="obligatorio"
					styleId="garInspeccion">
					<html:option value="S">
						<bean:message key="lbl.gar.si" />
					</html:option>
					<html:option value="N">
						<bean:message key="lbl.gar.no" />
					</html:option>
				</html:select>
				<span><bean:message key="msg.obligatorio" /> </span>
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td>
				<html:submit property="accion" disabled="${!garantiaPrestamoForm.mdf}" styleId="guardar">
					<bean:message key="cmd.gar.actualizar" />
				</html:submit>
			</td>
			<td>
				<html:submit property="accion"  onclick="$('#pageId').val('0')">
					<bean:message key="cmd.gar.regresar"/>
				</html:submit>
			</td>
		</tr>
	</table>
		<html:hidden property="preId"  styleId="preId"/>
	<html:hidden property="garId"  />
	<input id="pageId" type="hidden" name="page" value="3">
		<html:hidden property="perId" />
		<html:hidden property="estadoPrestamo" styleId="estadoPrestamo" />
</html:form>
