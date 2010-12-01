<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<div>
	<html:form action="/verComentarios" method="post" styleId="formId">
		<table border="0">
			<tr>
				<td>
					<label>
						<bean:message key="lbl.vcomentario.fechaInicio"/>
					</label>
				</td>
				<td>
					<html:text property="fechaInicio" maxlength="10" size="10" />
				</td>
				<td>
					<label>
						<bean:message key="lbl.vcomentario.fechaFin"/>
					</label>
				</td>
				<td>
					<html:text property="fechaFin" maxlength="10" size="10" />
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<html:submit property="accion">
						<bean:message key="cmd.vcomentario.buscar"/>
					</html:submit>
				</td>
			</tr>
		</table>
	</html:form>
</div>