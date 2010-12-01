<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
<!--
	$(document).ready(function(){
		//validaciones para el formulario de busqueda del asociado
		$("#ascCodigo").alphanumeric();
		$("#primerNombre").alpha();
		$("#segundoNombre").alpha();
		$("#primerApellido").alpha();
		$("#segundoApellido").alpha();
		//validaciones para el formulario del fiador
		$("#pxtPrimerApellido").alpha();
		$("#pxtTelefonoCasa").numeric();
		$("#pxtTelefonoOficina").numeric();
		$("#pxtJefeInmediato").alpha({allow:" "});
		$("#pxtSalario").numeric({allow:"."});
		$("#pxtSegundoApellido").alpha();
		$("#pxtNombres").alpha({allow:" "});
		$("#pxtDireccion").alphanumeric({allow:" "});
		$("#pxtTrabajo").alpha({allow:" "});
		$("#pxtTelefonoOficina").alphanumeric();
		$("#pxtEmail").alphanumeric({allow:".-_@"});
		var variable = $("#estadoPrestamo").val();
		if(variable != 14){
			$("#fxpEstado").attr("disabled","disabled");
			$("#guardar").attr("disabled","disabled");
		}
  });

//-->

</script>

<html:form action="${_accion}" method="post" styleId="formId">
	<script>
      		var patternDui = new Array(${tamDui}-1,1);
      </script>
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
							<bean:write name="prestamo" property="preMontoSolicitado" format="'$'#,###,###.00"/>
						</label>
					</td>
				</tr>
			</table>
		</logic:present>
	<table border="0" align="center" >
	<!-- tabla de los campos del fiador. -->
		<tr >
			<td>
				<label>
					<bean:message key="lbl.pxt.nombres" />:
				</label>
			</td>
			<td colspan="3">
				<html:text maxlength="100" size="50" property="nombreFiador"
					styleClass="obligatorio" 
					styleId="nombres" readonly="true"/>
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.pxt.primerApellido" />:
				</label>
			</td>
			<td>
				<html:text maxlength="50" size="18" property="primerApellido"
					styleClass="obligatorio" styleId="PrimerApellido" readonly="true"/>
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
			<td>
				<label>
					<bean:message key="lbl.pxt.segundoApellido" />:
				</label>
			</td>
			<td>
				<html:text maxlength="50" size="18" property="segundoApellido"
					styleClass="obligatorio" 
					styleId="SegundoApellido" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.pxt.direccion" />:
				</label>
			</td>
			<td>
				<html:textarea  property="direccion"
					styleClass="obligatorio" styleId="Direccion" cols="16" readonly="true"/>
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
						<td>
				<label>
					<bean:message key="lbl.pxt.telefonoCasa" />:
				</label>
			</td>
			<td>
				<html:text maxlength="15" size="18" property="telefono"
					styleClass="obligatorio" styleId="Telefono" readonly="true"/>
				<span><bean:message key="msg.obligatorio"/>
				</span>
			</td>
		</tr>
				<tr>
			<td>
				<label>
					<bean:message key="lbl.pxt.trabajo" />:
				</label>
			</td>
			<td>
				<html:text maxlength="30" size="18" property="lugarTrabajo"
					styleClass="obligatorio" styleId="Trabajo" readonly="true"/>
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
			<td>
				<label>
					<bean:message key="lbl.pxt.pxtCodigoEmpleado" />:
				</label>
			</td>
			<td>
				<html:text maxlength="50" size="15" property="codigo"
					styleClass="obligatorio" styleId="pxtCodigoEmpleado" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.pxt.jefeInmediato" />:
				</label>
			</td>
			<td>
				<html:text maxlength="100" size="18" property="jefeInmediato"
					styleClass="obligatorio"
					styleId="JefeInmediato" readonly="true"/>
			</td>
			<td>
				<label>
					<bean:message key="lbl.pxt.pxtEmail" />:
				</label>
			</td>
			<td>
				<html:text maxlength="100" size="18" 
					property="pxtEmail"
					styleClass="obligatorio" 
					styleId="pxtEmail" />
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.pxt.salario" />:
				</label>
			</td>
			<td>
				<html:text maxlength="30" size="18" property="salario"
					styleClass="obligatorio" styleId="salario" readonly="true"/>
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
			<td>
				<label>
					<bean:message key="lbl.pxt.telefonoOficina" />:
				</label>
			</td>
			<td>
				<html:text maxlength="15" size="18" property="telTrabajo"
					styleClass="obligatorio"
					styleId="TelefonoOficina" readonly="true"/>
			</td>
		</tr>
		<tr>
						<td>
				<label>
					<bean:message key="lbl.fxp.estado" />:
				</label>
			</td>
			<td>
				<html:select property="fxpEstado" styleClass="obligatorio" styleId="fxpEstado">
					<html:option value="A">Aprobado</html:option>
					<html:option value="V">En Verificacion</html:option>
					<html:option value="D">Denegado</html:option>
				</html:select>
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
				<td>
				<html:submit property="accion" disabled="${!fiadorForm.mdf}" styleId="guardar">
					<bean:message key="cmd.fxp.actualizar" />
				</html:submit>
			</td>
			<td>
				<html:submit property="accion"  onclick="$('#pageId').val('0')">
					<bean:message key="cmd.fxp.regresar"/>
				</html:submit>
			</td>
		</tr>
	</table>
    <html:hidden property="preId"  styleId="preId"/> 
	<html:hidden property="ascId" />
	<html:hidden property="fxpId"  />
	<html:hidden property="perId" />
	<html:hidden property="estadoPrestamo" styleId="estadoPrestamo" />
	<input id="pageId" type="hidden" name="page" value="3">
</html:form>