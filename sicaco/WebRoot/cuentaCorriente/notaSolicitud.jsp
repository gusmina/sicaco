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
			$("#nota").attr("disabled","disabled");
		}
	});
	
	function inhabilitar(){
			$("#tipoGarantia").attr("disabled","disabled");
		var variable = $("#estadoPrestamo").val();
		if(variable != 14){
			$("#guardar").attr("disabled","disabled");
			$("#nota").attr("disabled","disabled");
		}else{
			$("#guardar").removeAttr("disabled","disabled");
			$("#nota").removeAttr("disabled","disabled");
		}
	}
</script>
<html:form action="${_accion}" method="post" styleId="formId">
<logic:present name="asociado">
			<table>
				<tr>
					<td>
						<label class="obligatorio">
							<bean:message key="lbl.pre.asocInfo" />:
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
<table border="0" align="center" >
	<!-- tabla de los campos del fiador. -->
		<tr>
			<td>
				<label>
					<bean:message key="lbl.notPre.nota" />:
				</label>
			</td>
			<td>
				<html:textarea property="notNota"styleClass="obligatorio" rows="5" cols="50"
					styleId="nota" disabled="${notaGenericForm.mdf}" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
</table>
<table align="center">
		<tr>
				<td>
				<html:submit property="accion" styleId="guardar" disabled="${notaGenericForm.mdf}" onclick="$('#pageId').val('3');">
					<bean:message key="cmd.notPre.guardar"/>
				</html:submit>
			</td>
			<td>
					<input type="button" value="Limpiar"
						onclick="cleanFields('formId');inhabilitar();">
				</td>
			<td>
				<html:submit property="accion"  onclick="$('#pageId').val('0')">
					<bean:message key="cmd.notPre.regresar"/>
				</html:submit>
			</td>
		</tr>
	</table>
	<html:hidden property="preId" styleId="preId"/>
	<input id="pageId" type="hidden" name="page" value="0">
	<html:hidden property="perId" />
	<html:hidden property="estadoPrestamo" styleId="estadoPrestamo" />
</html:form>