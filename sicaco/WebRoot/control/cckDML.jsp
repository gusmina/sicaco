<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#cckSerieId").alphanumeric();
    	$("#cckCorrIniId").numeric();
    	$("#cckCorrFinId").numeric();
    	$("#cckDigitosId").numeric();
  });
</script>

<html:form action="${_accion}" method="post" focus="cckSerie"
	styleId="formId">
	<table border="0">
	<logic:present name="filtro">
	<logic:equal name="filtro" value="0" >
		<tr>
			<td>
				<label><bean:message key="lbl.controlCheque.ctrBanBanco.banId" /></label>:
			</td>
			<td>
				<html:select property="ctrBanBanco.banId" value="${form.ctrBanBanco.banId}"  
					styleClass="obligatorio" styleId="banId">
					<html:optionsCollection  label="banNombre" name="lstBan" value="banId"/>     	
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlCheque.cckSerie" />:
				</label>
			</td>
			<td>
				<html:text property="cckSerie" styleId="cckSerieId" size="25" maxlength="50" styleClass="obligatorio" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlCheque.cckCorrIni" />:
				</label>
			</td>
			<td>
				<html:text size="15" maxlength="15" styleId="cckCorrIniId" property="cckCorrIni" styleClass="obligatorio" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlCheque.cckCorrFin" />:
				</label>
			</td>
			<td>
				<html:text size="15" maxlength="15" styleId="cckCorrFinId" property="cckCorrFin" styleClass="obligatorio" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlCheque.cckDigitos" />:
				</label>
			</td>
			<td>
				<html:text size="15" maxlength="15" styleId="cckDigitosId" property="cckDigitos" styleClass="obligatorio" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		</logic:equal>
		<logic:equal name="filtro" value="1" >
		<tr>
			<td>
				<label><bean:message key="lbl.controlCheque.ctrBanBanco.banId" /></label>:
			</td>
			<td>
				<html:select property="ctrBanBanco.banId" value="${form.ctrBanBanco.banId}" disabled="true"  
					styleClass="obligatorio" styleId="banId" >
					<html:optionsCollection  label="banNombre" name="lstBan" value="banId"/>     	
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlCheque.cckSerie"/>:
				</label>
			</td>
			<td>
				<html:text property="cckSerie" maxlength="50" size="25" styleId="cckSerieId" styleClass="obligatorio" readonly="true" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlCheque.cckCorrIni" />:
				</label>
			</td>
			<td>
				<html:text size="15" maxlength="15" styleId="cckCorrIniId" property="cckCorrIni" styleClass="obligatorio" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlCheque.cckCorrFin" />:
				</label>
			</td>
			<td>
				<html:text size="15" maxlength="15" styleId="cckCorrFinId" property="cckCorrFin" styleClass="obligatorio" />
				<span><bean:message key="msg.obligatorio" />
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.controlCheque.cckDigitos" />:
				</label>
			</td>
			<td>
				<html:text size="15" maxlength="15" styleId="cckDigitosId" property="cckDigitos" styleClass="obligatorio" />
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
										<bean:message key="cmd.controlCheque.editar" />
									</html:submit>
								</td>
								<td>
									<html:submit property="accion" onclick="$('#pageId').val('0');">
										<bean:message key="cmd.controlCheque.cancelar" />
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
										<bean:message key="cmd.controlCheque.buscar" />
									</html:submit>
								</td>
								<td>
									<html:submit property="accion">
										<bean:message key="cmd.controlCheque.guardar" />
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
	<html:hidden property="cckId" value="${form.cckId}" />
	<html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}" />
	<html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}" />
</html:form>
