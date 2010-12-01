<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post"  styleId="formId" >
	<table border="0">
		<tr>
			<td><label><bean:message key="lbl.encabezado.fenSerieFactura" />:</label></td>
			<td>
				<html:text property="fenSerieFactura" styleClass="obligatorio" value="${form.fenSerieFactura}" size="15" maxlength="15" />
			</td>
			<td><label><bean:message key="lbl.encabezado.fenNumeroFactura" />:</label></td>
			<td>
				<html:text property="fenNumeroFactura" styleClass="obligatorio" value="${form.fenNumeroFactura}" size="15" maxlength="15" />
			</td>
		</tr>
		<tr>
			<td>
				<label><bean:message key="lbl.listaFac.fenFechaFactura" />:</label>
			</td>
		</tr>
		<tr>
			<td>
				<label><bean:message key="lbl.facturaManual.fechaIni" />:</label>
			</td>
			<td>
				<html:text style="float:left;" styleId="fechaIniId" styleClass="obligatorio"
					onkeyup="mask(this);" value="${form.fechaIni}"
					property="fechaIni" maxlength="10" size="10" />
				<input style="height: 18px;" id="button_fechaIniId"
					type="button" value="..." />
				<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
				<script type="text/javascript">
        				   Calendar.setup({
          					inputField    : "fechaIniId",
            				button        : "button_fechaIniId",
           				align         : "Br"
           			   });
   				</script>
			</td>				
			<td>
				<label><bean:message key="lbl.facturaManual.fechaFin" />:</label>
			</td>
			<td>
				<html:text style="float:left;" styleId="fechaFinId" styleClass="obligatorio"
					onkeyup="mask(this);" value="${form.fechaFin}"
					property="fechaFin" maxlength="10" size="10" />
				<input style="height: 18px;" id="button_fechaFinId"
					type="button" value="..." />
				<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
				<script type="text/javascript">
        				   Calendar.setup({
          					inputField    : "fechaFinId",
            				button        : "button_fechaFinId",
           				align         : "Br"
           			   });
   				</script>
			</td>				
		</tr>
		<tr>
			<logic:present name="ack">
				<logic:equal value="v" name="ack">
					<td><label><bean:message key="lbl.facturaManual.clientes" /></label></td>
				</logic:equal>
				<logic:equal value="c" name="ack">
					<td><label><bean:message key="lbl.facturaManual.proveedor" /></label></td>
				</logic:equal>
			</logic:present>
		</tr>
		<tr>
			<td><label><bean:message key="lbl.encabezado.codigo" />:</label></td>
			<td>
				<html:text property="codCli" styleClass="obligatorio" value="${form.codCli}" size="15" maxlength="25" />
			</td>
			<td><label><bean:message key="lbl.encabezado.nomCli" />:</label></td>
			<td>
				<html:text property="nombreCli" styleClass="obligatorio" value="${form.nombreCli}" size="15" maxlength="300"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<html:submit property="accion">
					<bean:message key="cmd.facturaManual.buscar"/>
				</html:submit>
				&nbsp;
				<input type="button" value="Limpiar" onclick="cleanFields('formId');">
			</td>
		</tr>
	</table>
  	<html:hidden property="ack" value="${form.ack}"/>  
</html:form>