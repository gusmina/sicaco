<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
$(document).ready(function(){
   	$("#benTelefono").numeric();
   	$("#porcentaje").numeric({allow:"."});
	$("#benPrimerNombre").alpha();
	$("#benPrimerApellido").alpha();
	$("#benSegundoNombre").alpha();
	$("#benSegundoApellido").alpha();
	$("#benApellidoCasadaId").alpha({allow:" "});
  });
   	

   			
</script>

<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td>
			<u><label><bean:message key="lbl.ben.benCodigoAsociado"/></label></u>:
			
		</td>
		<td colspan="4">
			<u><label>
				<bean:write name="asociadoNombre" property="ascCodigoAsociado"/>
				&nbsp;
				<bean:write name="asociadoNombre" property="secPerPersona.perPrimerNombre" />
				&nbsp;
				<bean:write name="asociadoNombre" property="secPerPersona.perSegundoNombre" />
				&nbsp;				
				<bean:write name="asociadoNombre" property="secPerPersona.perPrimerApellido" />
				&nbsp;				
				<bean:write name="asociadoNombre" property="secPerPersona.perSegundoApellido" />
			</label></u>
		</td>
	</tr>
	<tr>
		<td>
			<u><label><bean:message key="lbl.agregaBen.cuenta"/></label></u>:
			
		</td>
		<td colspan="4">
			<u><label>
				<bean:write name="cuenta" property="ctaCahCuentaAhorro.cahId"/>
				&nbsp;
				<logic:present name="cuenta">
					<logic:empty name="cuenta" property="ctaCahCuentaAhorro.ctaTahTipoAhorro">
						<bean:message key="lbl.agregaBen.aportacion"/>
					</logic:empty>
					<logic:notEmpty name="cuenta" property="ctaCahCuentaAhorro.ctaTahTipoAhorro">
						<bean:write name="cuenta" property="ctaCahCuentaAhorro.ctaTahTipoAhorro.ctaLahLineaAhorro.lahNombre"/>, 
						<bean:write name="cuenta" property="ctaCahCuentaAhorro.ctaTahTipoAhorro.tahNombre"/>
					</logic:notEmpty>
				</logic:present>
			</label></u>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.ben.benPrimerNombre" /></label>:
		</td>
		<td>
			<html:text  maxlength="50" size="10" property="benPrimerNombre" styleClass="obligatorio" value="${form.benPrimerNombre}" styleId="benPrimerNombre"/>
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
		<td>
			<label><bean:message key="lbl.ben.benSegundoNombre" /></label>:
		</td>
		<td>
			<html:text  maxlength="50" size="10" property="benSegundoNombre" value="${form.benSegundoNombre}" styleId="benSegundoNombre"/>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.ben.benPrimerApellido" /></label>:
		</td>
		<td>
			<html:text  maxlength="50" size="10" property="benPrimerApellido" styleClass="obligatorio" value="${form.benPrimerApellido}" styleId="benPrimerApellido"/>
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
		<td>
			<label><bean:message key="lbl.ben.benSegundoApellido" /></label>:
		</td>
		<td>
			<html:text  maxlength="50" size="10" property="benSegundoApellido" value="${form.benSegundoApellido}" styleId="benSegundoApellido"/>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.ben.benApellidoCasada" /></label>:
		</td>
		<td>
   			<html:text property="benApellidoCasada" value="${form.benApellidoCasada}" size="10" maxlength="50" styleId="benApellidoCasadaId" />
     	</td>
     	<td>
			<label><bean:message key="lbl.ben.benParentesco"/></label>:
		</td>
   		<td>
   			<html:select property="secParParentesco.parId" value="${form.secParParentesco.parId}">
   				<html:optionsCollection  label="parDescripcion" name="lstParentesco" value="parId"/>     					
			</html:select>
   		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.ben.benFechaNacimiento" /></label>:
			
		</td>
		<td>
			<html:text style="float:left;" styleId="benFechaNacimientoId" onkeyup="mask(this);" value="${form.benFechaNacimiento}" property="benFechaNacimiento" maxlength="10" size="10"/>
        	<input  style="height: 18px;" id="button_benFechaNacimientoId" type="button"  value="..."/>
        	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d">
        	<script type="text/javascript">
		            Calendar.setup({
		              inputField    : "benFechaNacimientoId",
		              button        : "button_benFechaNacimientoId",
		              align         : "Br"
		            });
		    </script>
		</td>
		<td>
			<label><bean:message key="lbl.ben.benSexo" /></label>:
			
		</td>
		<td>
			<html:select property="benSexo" styleId="benSexo" styleClass="obligatorio">
				<html:option value="F">Femenino</html:option>
				<html:option value="M">Masculino</html:option>
			</html:select>
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
   <tr>
		<td>
			<label><bean:message key="lbl.ben.benTelefono" /></label>:
		</td>
		<td>
			<html:text  maxlength="15" size="10" property="benTelefono" value="${form.benTelefono}" styleId="benTelefono"/>
		</td>
	</tr>
	<tr>
   		<td colspan="4" align="center">
			<html:submit property="accion" >
				<bean:message key="cmd.agregaBen.agregar" />
			</html:submit> 
			&nbsp;
			<html:submit property="accion" >
				<bean:message key="cmd.agregaBen.salvar" />
			</html:submit> 
			&nbsp;
			<html:submit property="accion" >
				<bean:message key="cmd.agregaBen.regresar" />
			</html:submit>
			&nbsp; 
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 
<div id="lista-datos">
	${_lista2}
</div>
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="ascId" value="${form.ascId}"/>
<html:hidden property="cuentaX" value="${form.cuentaX}"/> 
</html:form>
