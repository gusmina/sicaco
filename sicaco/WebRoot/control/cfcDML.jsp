<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#cfcSerieId").alphanumeric();
    	$("#cfcCorrIniId").numeric();
    	$("#cfcCorrFinId").numeric();
    	$("#cfcDigitosId").numeric();
  });
</script>

<html:form action="${_accion}" method="post" focus="cfcSerie"
	styleId="formId">
	<table border="0">
	<logic:present name="filtro">
	<logic:equal name="filtro" value="0" >
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlFactura.cfcSerie" />:
				</label>
			</td>
			<td>
				<html:text property="cfcSerie" styleId="cfcSerieId" size="25" maxlength="50" styleClass="obligatorio" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlFactura.cfcCorrIni" />:
				</label>
			</td>
			<td>
				<html:text size="15" maxlength="15" styleId="cfcCorrIniId" property="cfcCorrIni" styleClass="obligatorio" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlFactura.cfcCorrFin" />:
				</label>
			</td>
			<td>
				<html:text size="15" maxlength="15" styleId="cfcCorrFinId" property="cfcCorrFin" styleClass="obligatorio" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlFactura.cfcDigitos" />:
				</label>
			</td>
			<td>
				<html:text size="15" maxlength="15" styleId="cfcDigitosId" property="cfcDigitos" styleClass="obligatorio" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		</logic:equal>
		<logic:equal name="filtro" value="1" >
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlFactura.cfcSerie"/>:
				</label>
			</td>
			<td>
				<html:text property="cfcSerie" maxlength="50" size="25" styleId="cfcSerieId" styleClass="obligatorio" disabled="true" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlFactura.cfcCorrIni" />:
				</label>
			</td>
			<td>
				<html:text size="15" maxlength="15" styleId="cfcCorrIniId" property="cfcCorrIni" styleClass="obligatorio" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlFactura.cfcCorrFin" />:
				</label>
			</td>
			<td>
				<html:text size="15" maxlength="15" styleId="cfcCorrFinId" property="cfcCorrFin" styleClass="obligatorio" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlFactura.cfcDigitos" />:
				</label>
			</td>
			<td>
				<html:text size="15" maxlength="15" styleId="cfcDigitosId" property="cfcDigitos" styleClass="obligatorio" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		</logic:equal>
		</logic:present>
		<tr>
			<td colspan="2" align="center">
				<logic:present name="filtro">
					<%-- Solo acciones de busqueda un boton nada mas --%>
					<logic:equal name="filtro" value="1">
						<input type="hidden" id="pageId" name="page" value="3" />
						<table align="center">
							<tr>
								<td>
									<html:submit property="accion">
										<bean:message key="cmd.controlFactura.editar" />
									</html:submit>
								</td>
								<td>
									<html:submit property="accion" onclick="$('#pageId').val('0');">
										<bean:message key="cmd.controlFactura.cancelar" />
									</html:submit>
								</td>
							</tr>
						</table>
					</logic:equal>
					<logic:equal name="filtro" value="0">
						<input type="hidden" id="pageId" name="page" value="3" />
						<table align="center">
							<tr>
								<td>
									<html:submit property="accion" onclick="$('#pageId').val('0');">
										<bean:message key="cmd.controlFactura.buscar" />
									</html:submit>
								</td>
								<td>
									<html:submit property="accion">
										<bean:message key="cmd.controlFactura.guardar" />
									</html:submit>
								</td>
								<td>
									<input type="button" value="Limpiar"
										onclick="cleanFields('formId');">
								</td>
							</tr>
						</table>
					</logic:equal>
				</logic:present></tr>
	</table>
	<html:hidden property="ctrEmpEmpresa.empId" value="1" />
	<html:hidden property="cfcSerie" value="${form.cfcSerie}" />
	<html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}" />
	<html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}" />
</html:form>
