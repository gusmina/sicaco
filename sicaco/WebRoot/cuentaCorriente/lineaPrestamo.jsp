<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">

	$(document).ready(function(){
	    	$("#lprComisionId").numeric({allow:"."});
	    	$("#lprSueldoMinimo").val(round_number( $("#lprSueldoMinimo").val(),2));
  			$("#lprSueldoMinimo").numeric({allow:"."});
  			$("#lprMinRefComerciales").numeric();
  			$("#lprMinRefPersonales").numeric();
  			$("#lprMinGarantias").numeric();
  			$("#lprMinFiadores").numeric();
	  });
</script>

<div>
	<html:form action="${_accion}" method="post" styleId="formId">
		<table align="center">
			<tr>
				<td>
					<label>
						<bean:message key="lbl.lineaPrestamo.lprNombre" />
					</label>
				</td>
				<td>
					<html:text property="lprNombre" styleClass="obligatorio" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.lineaPrestamo.lprDescripcion" />
					</label>
				</td>
				<td>
					<html:text property="lprDescripcion" styleClass="obligatorio" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.lineaPrestamo.lprDesde" />
					</label>
				</td>
				<td>
					<html:text onkeyup="JavaScript:mask(this);" property="lprDesde"
						maxlength="10" size="10" styleId="lprDesde"
						styleClass="obligatorio" />
					<span><bean:message key="msg.obligatorio" /> </span>
					<input type="button" name="calendario" value=" ..."
						class="calendario" id="calendarioDesde" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.lineaPrestamo.lprHasta" />
					</label>
				</td>
				<td>
					<html:text onkeyup="JavaScript:mask(this);" property="lprHasta"
						maxlength="10" size="10" styleId="lprHasta"
						styleClass="obligatorio" />
					<input type="button" name="calendario" value=" ..."
						class="calendario" id="calendarioHasta" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.lineaPrestamo.lprComision" />
					</label>
				</td>
				<td>
					<html:text property="lprComision" styleClass="obligatorio"
						value="${form.lprComision}" styleId="lprComisionId" size="15" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.lineaPrestamo.lprMinFiadores" />
					</label>
				</td>
				<td>
					<html:text property="lprMinFiadores" styleId="lprMinFiadores" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.lineaPrestamo.lprMinGarantias" />
					</label>
				</td>
				<td>
					<html:text property="lprMinGarantias" styleId="lprMinGarantias" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.lineaPrestamo.lprSueldoMinimo" />
					</label>
				</td>
				<td>
					<html:text property="lprSueldoMinimo" styleId="lprSueldoMinimo" />
				</td>
			</tr>
						<tr>
				<td>
					<label>
						<bean:message key="lbl.lineaPrestamo.lprMinRefPersonales" />
					</label>
				</td>
				<td>
					<html:text property="lprMinRefPersonales" styleId="lprMinRefPersonales" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.lineaPrestamo.lprMinRefComerciales" />
					</label>
				</td>
				<td>
					<html:text property="lprMinRefComerciales" styleId="lprMinRefComerciales" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<html:submit property="accion" disabled="${form.mdf}">
						<bean:message key="cmd.lineaPrestamo.guardar" />
					</html:submit>
					<html:submit property="accion" disabled="${!form.mdf}"> 
						<bean:message key="cmd.lineaPrestamo.modificar" />
					</html:submit>
					<input type="button" value="Limpiar"
						onclick="cleanFields('formId');">

					<html:submit property="accion" onclick="cleanFields('formId');$('#pageId').val('0');">
						<bean:message key="cmd.lineaPrestamo.cancelar" />
					</html:submit>
				</td>
			</tr>
		</table>
		<html:hidden property="lprId" />
		<html:hidden property="lprOrdenAprov" styleId="orden" />
		<html:hidden property="mdf" styleId="mdf" />
		<input id="pageId" type="hidden" name="page" value="3">
	</html:form>

	<script type="text/javascript">
            Calendar.setup({
              inputField    : "lprDesde",
              button        : "calendarioDesde",
              align         : "Br"
            });
            Calendar.setup({
              inputField    : "lprHasta",
              button        : "calendarioHasta",
              align         : "Br"
            });
    </script>
</div>