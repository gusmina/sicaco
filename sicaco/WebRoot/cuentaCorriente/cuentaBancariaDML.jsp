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
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="ascId" value="${form.ascId}"/>
<html:hidden property="cbaId" value="${form.cbaId}"/>
<html:hidden property="preId" value="${form.preId}"/>
<html:hidden property="estado" value="${form.estado}"/>
</html:form>
