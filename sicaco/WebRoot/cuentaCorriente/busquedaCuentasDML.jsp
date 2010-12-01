<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>



<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.cas.casCuenta"/></label>:
		</td>
		<td>
			<html:select property="tipoCuentaMadre" value="${form.tipoCuentaMadre}" styleId="tipoCuentaMadre" styleClass="obligatorio">
				<html:option value="Ap">Aportaciones</html:option>
				<html:option value="Ah">Ahorros</html:option>
				<html:option value="Se">Seguros</html:option>
			</html:select>
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	
	<tr>
		<td>
			<label><bean:message key="lbl.cas.casReferencia" /></label>:
		</td>
		<td>
			<html:text property="referenciaCuenta"  maxlength="13" size="20"/>
			
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.cas.ascId" /></label>:
		</td>
		<td>
			<label>
				<bean:write name="asociadoNombre" property="ascCodigoAsociado"/>
				&nbsp;
				<bean:write name="asociadoNombre" property="ascNombreNit" />
				&nbsp;				
			</label>
			<!-- <html:text property="ascCodigo" maxlength="50" readonly="true" size="20"/> --> 
     	</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.cas.casFechaApertuta" /></label>:
			
		</td>
		<td>
   			
			<html:text onkeyup="mask(this);" property="casFechaApertura" value="${form.casFechaApertura}" styleId="casFechaApertura"
						maxlength="10" size="10" />
		</td>
		<td align="left">
			<input style="height: 18px; position: relative; left: -0.2em;"
						id="button_casFechaApertura" type="button" value="...." />		
		</td>
	</tr>
	<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "casFechaApertura",
				              button        : "button_casFechaApertura",
				              align         : "Br"
				            });
				            function handlerCombo(){
				            	$(casFechaApertura).toggle();
				            }

   </script>
	
	<tr>
		<td align="center" colspan="3">
			<html:submit property="accion" >
				<bean:message key="cmd.cas.buscar" />
			</html:submit>
			<html:submit property="accion" onclick="$('#pageId').val('0');" >
				<bean:message key="cmd.cas.nuevaCuenta" />
			</html:submit> 
			<html:submit  property="accion" onclick="$('#pageId').val('0');" >
				<bean:message key="cmd.cas.toBusquedaAsociados" />
			</html:submit>
			<input align="left" type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="ascId" value="${form.ascId}"/>
<html:hidden property="casCuenta" value="${form.casCuenta}"/>
<html:hidden property="casFechaApertura" value="${form.casFechaApertura}"/>
<html:hidden property="mdf" value="${form.mdf}"/> 
</html:form>