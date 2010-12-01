<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
$(document).ready(function(){
   	$("#benTelefono").numeric();
   	$("#porcentaje").numeric({allow:"."});
	$("#benPrimerNombre").alpha({allow:" "});
	$("#benPrimerApellido").alpha({allow:" "});
	$("#benSegundoNombre").alpha({allow:" "});
	$("#benSegundoApellido").alpha({allow:" "});
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
				<bean:write name="asociadoNombre" property="ascNombreNit" />
				&nbsp;				
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
		</td>
	</tr>
   <tr>
		<td>
			<label><bean:message key="lbl.ben.benTelefono" /></label>:
		</td>
		<td>
			<html:text  maxlength="15" size="10" property="benTelefono" value="${form.benTelefono}" styleId="benTelefono"/>
		</td>
		<logic:present name="mdf" scope="request">
			<logic:equal value="true" name="mdf">
				<td><label><bean:message key="lbl.ben.benEstado" /></label>:</td>
				<td>
					<html:select property="benEstado" styleId="benEstado" styleClass="obligatorio">
						<html:option value="A">  Activo  </html:option>
						<html:option value="I">  Inactivo  </html:option>
					</html:select>
				</td>	
			</logic:equal>
			<logic:notEqual value="true" name="mdf">
				<td /> <td /> <td /> <td />
			</logic:notEqual>
		</logic:present>
	</tr>
   	<logic:present name="porcent" scope="request">
		<logic:equal value="1" name="porcent">
			<tr>
   				<td>
					<label><bean:message key="lbl.ben.benPorcentaje"/></label>:
				</td>
   				<td>
   					<html:text  style="numeric" maxlength="10" size="10" property="porcentaje" styleClass="obligatorio" value="${form.porcentaje}" styleId="porcentaje"/>
					<span><bean:message key="msg.obligatorio"/></span>
					<label style="color: red;"><b> %</b> </label>
   				</td>
   			</tr>	
		</logic:equal></logic:present>
   	
	<tr>
		<td align="right">
			<html:submit property="accion" onclick="$('#pageId').val('0');">
				<bean:message key="cmd.ben.guardar" />
			</html:submit> 
		</td>
		<td align="center">
			<logic:present name="porcent" scope="request">
				<logic:equal value="1" name="porcent">
					<html:submit property="accion" onclick="$('#pageId').val('0');" >
						<bean:message key="cmd.ben.toCuentasAsociado" />
					</html:submit>				
				</logic:equal>
			</logic:present>
			<logic:present name="porcent" scope="request">
				<logic:equal value="0" name="porcent">
					<html:submit property="accion" onclick="$('#pageId').val('0');" >
						<bean:message key="cmd.ben.toAsociadosBenefactor" />
					</html:submit>				
				</logic:equal>
			</logic:present>
		</td>
		<td align="center">
			<html:submit property="accion" onclick="$('#pageId').val('0');">
				<bean:message key="cmd.ben.eliminar" />
			</html:submit> 
		</td>
		<td align="left">
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
		
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="benId" value="${form.benId}"/>
<html:hidden property="ctaAscAsociado.ascId" value="${form.ctaAscAsociado.ascId}"/>
<html:hidden property="cuentaX" value="${form.cuentaX}"/> 
<html:hidden property="porcentaje" value="${form.porcentaje}"/> 
<html:hidden property="mdf" value="${form.mdf}"/> 
</html:form>
