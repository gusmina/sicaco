 <%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

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
			</table>
			<table align="center">
				<tr>
				<td>
					<html:submit property="accion">
					<bean:message key="cmd.fxp.regresarToDeudores"/>
				</html:submit>
				</td>
				</tr>
			</table>
		</logic:present>
		<html:hidden property="fxpId"/>
		<html:hidden property="ctaAscAsociado.ascId"/>
</html:form>