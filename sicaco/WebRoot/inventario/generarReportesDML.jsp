<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<div>
	<html:form action="${_accion}" method="post" styleId="formId">
		<table border="0">
			<tr>
				<td>
					<html:select property="reporteId" onchange="handlerCombo();">
						<html:option value="1">
						Reporte Venta Del Dia
						</html:option>
					</html:select>
				</td>
			</tr>
		</table>
		<table border="0" id="tabla">
			<tr>
				<td>
					<bean:message key="lbl.reportes.fechaReporte" />
				</td>
				<td>
					<html:text onkeyup="mask(this);" property="fechaReporte"
						maxlength="10" size="10" />
				</td>
				<td align="left">
					<input style="height: 18px; position: relative; left: -4.5em;"
						id="button_fechaReporte" type="button" value="..." />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.reportes.txtAuxiliar" />
					</label>
				</td>
				<td>
					<html:text property="txtAuxiliar" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.reportes.sumarioR" />
					</label>
				</td>
				<td>
					<html:text property="txtSumario" />
				</td>
			</tr>
			<tr>
				<td>
					<html:submit property="accion">
						<bean:message key="cmd.reportes.generarReporte" />
					</html:submit>
				</td>
				<td>
					<input type="button" value="Limpiar"
						onclick="cleanFields('formId');">
						
				</td>
			</tr>
		</table>
		<html:hidden property="reporteId" value="${form.reporteId}" />
	</html:form>

	<script type="text/javascript">
            Calendar.setup({
              inputField    : "fechaReporte",
              button        : "button_fechaReporte",
              align         : "Br"
            });
            
            function handlerCombo(){
            	$(fechaReporte).toggle();
            }

    </script>
</div>
