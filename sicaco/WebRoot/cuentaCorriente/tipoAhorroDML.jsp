<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<script type="text/javascript">
<!--funcion para radio button para definir que seleccionara.//-->
$(document).ready(function(){
	var maki = $("#plmPlanMeses")[0]; 
	maki.checked=true;
 		  $("#plmPlanMeses").click(function() {
 			$("#tahFechaFinInput").attr("disabled","disabled");
 			$("#tahFechaFinInput").val(" ");
 			$("#plmCombo").removeAttr("disabled");	
		});	
		
		$("#tahFechaFin").click(function() {
			$("#plmCombo").attr("disabled","disabled");
			$("#plmCombo").val("-1");
 			$("#tahFechaFinInput").removeAttr("disabled");
		});	
	});
</script>
<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.tah.tahNombre"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="50" size="10" property="tahNombre" value="${form.tahNombre}" styleId="tahNombre"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.tah.tahDescripcion" /></label>:
		</td>
		<td>
			<html:textarea styleClass="obligatorio" rows="3" cols="25" property="tahDescripcion" value="${form.tahDescripcion}" styleId="tahDescripcion"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.tah.tahLineaAhorro" /></label>:
			
		</td>
		<td>
   			<html:select property="ctaLahLineaAhorro.lahId" value="${form.ctaLahLineaAhorro.lahId}" styleClass="obligatorio">
   				
				<html:optionsCollection  label="lahNombre" name="lstLahLineaAhorro" value="lahId"/>     					
			</html:select>
			<span><bean:message key="msg.obligatorio"/></span>
     	</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.tah.tahTasaInteres" /></label>:
			
		</td>
		<td>
   			<html:select property="ctaTinTasaInteres.tinId" value="${form.ctaTinTasaInteres.tinId}" styleClass="obligatorio">
   				
				<html:optionsCollection  label="tinNombre" name="lstTinTasaInteres" value="tinId"/>     					
			</html:select>
			<span><bean:message key="msg.obligatorio"/></span>
     	</td>
	</tr>
	<tr>
		<td>
			<html:radio property="duracion" value="true" styleId="plmPlanMeses" >
				<label><bean:message key="lbl.tah.tahPlanMeses" />:</label></html:radio>
		</td>
		<td>
   			<html:select property="ctaPlmPlanMeses.plmId" value="${form.ctaPlmPlanMeses.plmId}" styleId="plmCombo">
   				<html:option value="-1"  >------ </html:option>
				<html:optionsCollection  label="plmNombre" name="lstPlmPlanMes" value="plmId"/>     					
			</html:select>
     	</td>
	</tr>
	<tr>
		<td>
			<html:radio property="duracion" value="false" styleId="tahFechaFin">
				<label><bean:message key="lbl.tah.tahFechaFin" />:</label></html:radio>
		</td>
		<td>
   			<html:text onkeyup="mask(this);" property="tahFechaFin" value="${form.tahFechaFin}" styleId="tahFechaFinInput"
						maxlength="10" size="10" />
			<input style="height: 18px; position: relative; left: -0.2em;"
						id="button_tahFechaFin" type="button" value="...." />
     	</td>
	</tr>
	<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "tahFechaFinInput",
				              button        : "button_tahFechaFin",
				              align         : "Br"
				            });
				            function handlerCombo(){
				            	$(tahFechaFinInput).toggle();
				            }

   </script>
	<tr>
		<td>
			<html:submit property="accion" >
				<bean:message key="cmd.tah.guardar" />
			</html:submit> 
		</td>
		<td>
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="tahId" value="${form.tahId}"/>
<html:hidden property="mdf" value="${form.mdf}"/>
<html:hidden property="plmId" value="${form.ctaPlmPlanMeses.plmId}"/>
<html:hidden property="tahFechaFin" value="${form.tahFechaFin}"/> 
</html:form>
