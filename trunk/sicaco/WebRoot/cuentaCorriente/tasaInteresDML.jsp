<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<script type="text/javascript">
<!--
		$(document).ready(function(){
		$("#tinNombre").alphanumeric({allow:".,%"});
		$("#tinTasa").numeric({allow:"."});
	});
//-->
</script>
<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.tin.tinNombre"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="50" size="20" property="tinNombre" value="${form.tinNombre}" styleId="tinNombre"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.tin.tinDescripcion" /></label>:
		</td>
		<td>
			<html:textarea styleClass="obligatorio" rows="3" cols="25" property="tinDescripcion" value="${form.tinDescripcion}" styleId="tinDescripcion"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.tin.tinTasa" /></label>:
		</td>
		<td>
			<logic:present name="disableTasa">
				<logic:equal value="1" name="disableTasa">
					<html:text styleClass="obligatorio" style="numeric" maxlength="10" size="10" 
						property="tinTasa" value="${form.tinTasa}" styleId="tinTasa" readonly="true"/> 
					<span><bean:message key="msg.obligatorio"/></span>
				</logic:equal>
				<logic:equal value="0" name="disableTasa">
					<html:text styleClass="obligatorio" style="numeric" maxlength="10" size="10" 
						property="tinTasa" value="${form.tinTasa}" styleId="tinTasa"/> 
					<span><bean:message key="msg.obligatorio"/></span>
				</logic:equal>
			</logic:present>
		</td>
	</tr>
	<logic:present name="mdf">
		<logic:equal value="false" name="mdf">
			<tr>
				<td>
					<html:submit property="accion" >
						<bean:message key="cmd.tin.guardar" />
					</html:submit> 
				</td>
				<td>
					<input type="button" value="Limpiar" onclick="cleanFields('formId');">
				</td>
			</tr>
		</logic:equal>
		<logic:equal value="true" name="mdf">
			<tr>
				<td>
					<html:submit property="accion" >
						<bean:message key="cmd.tin.salvar" />
					</html:submit> 
				</td>
				<td>
					<html:submit property="accion" >
						<bean:message key="cmd.tin.cancelar" />
					</html:submit> 
				</td>
			</tr>
		</logic:equal>
	</logic:present>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="tinId" value="${form.tinId}"/>
</html:form>
