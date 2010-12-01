<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
		$("#cbaCuenta").numeric();
	});
</script>

<html:form action="${_accion}" method="post" styleId="formId">
<table border="0" align="center" >
				<tr>
			<td>
				<label  style="obligatorio">
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
				<label  style="obligatorio">
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
				<label  style="obligatorio">
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
				<label  style="obligatorio">
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
				<label  style="obligatorio">
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
				<label  style="obligatorio">
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
				<label  style="obligatorio">
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
				<label  style="obligatorio">
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
<table border="0" align="center">
	<tr>
				<td>
					<label><bean:message key="lbl.cba.cbaBanco"/></label>
				</td>
				<td>
					<html:select property="ctrBanBanco.banId" styleClass="obligatorio" value="${form.ctrBanBanco.banId}">
						<html:optionsCollection  label="banNombre" name="lBacs" value="banId"/>     					
				   </html:select>
				   <span><bean:message key="msg.obligatorio"/></span>
				</td>
	</tr>
	<tr>
				<td>
					<label><bean:message key="lbl.cba.cbaTipoCuenta"/></label>
				</td>
				<td>
				   <html:select property="ctaTcuTipoCuenta.tcuId" styleClass="obligatorio" value="${form.ctaTcuTipoCuenta.tcuId}">
						<html:optionsCollection  label="tcuNombre" name="lTipos" value="tcuId"/>     					
				   </html:select>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
	</tr>
	<tr>
		<td>
					<label><bean:message key="lbl.cba.cbaCuenta"/></label>
				</td>
				<td>
					<html:text  maxlength="30" size="20" property="cbaCuenta" styleClass="obligatorio" value="${form.cbaCuenta}" styleId="cbaCuenta"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
	</tr>
</table>
<table align="center">
	<tr>
		<logic:notPresent name="edit">
			<td>
				<html:submit property="accion" >
					<bean:message key="cmd.cba.guardar" />
				</html:submit> 
			</td>
			<td>
				<input type="button" value="Limpiar" onclick="cleanFields('formId');">
			</td>
					<td>
				<html:submit property="accion" onclick="$('#pageId').val('0')">
					<bean:message key="cmd.cba.regresar" />
				</html:submit> 
			</td>
		</logic:notPresent>
		<logic:present name="edit">
			<td>
				<html:submit property="accion" >
					<bean:message key="cmd.cba.salvar" />
				</html:submit> 
			</td>
			<td>
				<html:submit property="accion" >
					<bean:message key="cmd.cba.cancelar" />
				</html:submit> 
			</td>
		</logic:present>
	</tr>
</table>
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="ascId" value="${form.ascId}"/>
<html:hidden property="cbaId" value="${form.cbaId}" />
</html:form>
