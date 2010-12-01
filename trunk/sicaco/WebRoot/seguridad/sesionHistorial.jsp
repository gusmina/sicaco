<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<script type="text/javascript">
	$(document).ready(function(){
    	$("#iseNombreUsuarioId").alphanumeric();
  });
</script>
<style type="text/css">
		#error{
	 		background-color: rgb(254,149,137);
		}
	</style>	
<div>
	<html:form action="${_accion}" method="post" styleId="formId">
		<table align="center">
			<tr>
				<td>
					<bean:message key="lbl.sesion.iseNombreUsuario"/>
				</td>
				<td>
					<html:text styleId="iseNombreUsuarioId" property="iseNombreUsuario" maxlength="25" size="25"/>
				</td>
			</tr>
			<tr>
				<td>
					<bean:message key="lbl.sesion.historial.hseIp"/>
				</td>
				<td>
					<html:text property="hseIp" maxlength="15" size="15"/>
				</td>
			</tr>
			<tr>
				<td>
					<bean:message key="lbl.sesion.historial.hseFechaAcceso"/>
				</td>
				<td>
					<html:text onkeyup="mask(this);" styleId="hseFechaAccesoIniId" property="hseFechaAccesoIni"
						maxlength="10" size="10"/>
				</td>
				<td align="left">
					<input style="height: 18px;  position: relative; left: -6.5em;" id="button_hseFechaAccesoIniId" type="button" value="..." />
				</td>
				<td>
					<html:text style="height: 18px; position: relative; left: -5.5em;" maxlength="10" size="10" styleId="hseFechaAccesoFinId" onkeyup="mask(this);" property="hseFechaAccesoFin"/>
				</td>
				<td align="left">
					<input style="height: 18px; position: relative; left: -5.5em;" id="button_hseFechaAccesoFinId" type="button" value="..." />
				</td>
			</tr>
			<tr>
				<td>
					<bean:message key="lbl.sesion.historial.hseFechaSalida"/>
				</td>
				<td>
					<html:text  maxlength="10" size="10" onkeyup="mask(this);" styleId="hseFechaSalidaIniId" property="hseFechaSalidaIni"/>
				</td>
				<td>
					<input style="height: 18px;  position: relative; left: -6.5em;" id="button_hseFechaSalidaIniId" type="button"  value="..."/>
				</td>
				<td>
					<html:text style="height: 18px; position: relative; left: -5.5em;" maxlength="10" size="10" onkeyup="mask(this);" styleId="hseFechaSalidaFinId" property="hseFechaSalidaFin"/>
				</td>
				<td align="left">
					<input style="height: 18px; position: relative; left: -5.5em;" id="button_hseFechaSalidaFinId" type="button"  value="..."/>
				</td>
			</tr>
			<tr align="center">
				<td>
					<html:submit property="accion">
		          		<bean:message key="cmd.historial.findByCriteria"/>
		          	</html:submit>
				</td>
				<td>
					<html:submit property="accion">
		          		<bean:message key="cmd.historial.eliminar"/>
		          	</html:submit>
				</td>
				<td>
					<input type="button" value="Limpiar" onclick="cleanFields('formId');">
				</td>
			</tr>
		</table>
	</html:form>
	 <script type="text/javascript">
            Calendar.setup({
              inputField    : "hseFechaAccesoIniId",
              button        : "button_hseFechaAccesoIniId",
              align         : "Br"
            });
            Calendar.setup({
              inputField    : "hseFechaAccesoFinId",
              button        : "button_hseFechaAccesoFinId",
              align         : "Br"
            });
            Calendar.setup({
              inputField    : "hseFechaSalidaIniId",
              button        : "button_hseFechaSalidaIniId",
              align         : "Br"
            });
            Calendar.setup({
              inputField    : "hseFechaSalidaFinId",
              button        : "button_hseFechaSalidaFinId",
              align         : "Br"
            });
    </script>
	
</div>