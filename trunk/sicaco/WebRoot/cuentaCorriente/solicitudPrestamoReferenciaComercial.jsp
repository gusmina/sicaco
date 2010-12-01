<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript">
	$(document).ready(function(){
	$("#tipoGarantia").attr("disabled","disabled");
		var variable = $("#estadoPrestamo").val();
		if(variable != 14){
			$("#guardar").attr("disabled","disabled");
			$("#rcoEstado").attr("disabled","disabled");
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
						<bean:message key="lbl.asc.ascCodigoAsociado" />:
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
					<label class="obligatorio" >
					<bean:message key="lbl.pre.referencia" />:
					</label>
				</td>
				<td>
					&nbsp;
					<label>
						<bean:write name="prestamo"  property="preId" />
					</label>
				</td>
				<td >
				<label class="obligatorio">
					<bean:message key="lbl.pre.montoSolicitado" />:
					</label>
				</td>
				<td>
					&nbsp;
					<label>
						<bean:write name="prestamo"  property="preMontoSolicitado" format="'$'#,###,###.00" />
					</label>
				</td>
			</tr>
			</table>
		</logic:present>
	<table align="center">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.rco.nombreEmpresa" />
				</label>
				:
			</td>
			<td>
				<html:text maxlength="20" size="20" property="rcoReferencia"
					styleClass="obligatorio"
					styleId="rcoReferencia" readonly="true" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
			<td>
				<label>
					<bean:message key="lbl.rco.sucursal" />
				</label>
				:
			</td>
			<td>
				<html:text maxlength="25" size="20" property="rcoSucursal"
					styleClass="obligatorio" 
					styleId="rcoSucursal" readonly="true" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.rco.monto" />
				</label>
				:
			</td>
			<td>
				<html:text maxlength="20" size="20" property="rcoMonto"
					styleClass="obligatorio" 
					styleId="rcoMonto" readonly="true" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td> 
			<td>
				<label>
					<bean:message key="lbl.rco.concepto" />
				</label>
				:
			</td>
			<td>
				<html:textarea  property="rcoConcepto"
					styleClass="obligatorio" 
					styleId="rcoConcepto" readonly="true" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td> 
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.rco.estadoCredito" />
				</label>
				:
			</td>
			<td>
				<html:select property="rcoEstado" styleClass="obligatorio" styleId="rcoEstado">
					<html:option value="A">
						<bean:message key="lbl.rco.activo" />
					</html:option>
					<html:option value="I">
						<bean:message key="lbl.rco.inactivo" />
					</html:option>
				</html:select>
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td>
				<html:submit property="accion" disabled="${!referenciaComercialForm.mdf}" styleId="guardar">
					<bean:message key="cmd.rco.modificar" />
				</html:submit>
			</td>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.rco.regresar" />
				</html:submit>
			</td>
		</tr>
	</table>
	<html:hidden property="preId" />
	<html:hidden property="rcoId" />
			<html:hidden property="perId" />
		<html:hidden property="estadoPrestamo" styleId="estadoPrestamo"/>
</html:form>