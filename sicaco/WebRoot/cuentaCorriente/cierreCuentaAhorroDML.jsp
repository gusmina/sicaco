<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr><td>Datos de la cuenta de Ahorro</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.cap.capAsociado"/></label>:
			
		</td>
		<td colspan="3">
			<label>
				<bean:write name="asociadoNombre" property="ascCodigo"/>
				&nbsp;
				<bean:write name="asociadoNombre" property="ascNombreNit" />
				&nbsp;				
			</label>
		</td>
		
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.cap.capReferenciaCuenta" /></label>:
		</td>
		<td colspan="3">
			<label>${form.cahId }	;    ${form.ctaTahTipoAhorro.tahNombre }</label>
		</td>
	</tr>	
	<tr>
		<td>
			<label><bean:message key="lbl.cah.cahFechaApertura" /></label>:
		</td>
		<td>
			<label>	${form.casFechaApertura }		</label>
		</td>
		<td>
			<label><bean:message key="lbl.cah.cahFechaCierre" /></label>:
		</td>
		<td>
			<label>	${form.casFechaCierre}			</label>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.cah.cahSaldoActual" /></label>:
		</td>
		<td >
			<label style="color: red">	<u>$ ${form.cahSaldoActual }</u>	</label>
		</td>
		<logic:present name="pre" scope="request">
			<logic:equal value="2" name="pre">
				<td>
					<label><bean:message key="lbl.cah.cahPenalidad" /></label>:
				</td>
				<td>
					<label>
						${form.penalidad}		
					</label>
				</td>
				
			</logic:equal>
			<logic:notEqual value="1" name="pre">
				<td /> <td />
			</logic:notEqual>
		</logic:present>
	</tr>
	<tr>
		<td colspan="4" align="left">
			<html:checkbox property="opPenalidad" value="true">
				<label>Aplicar Penalidad</label>
			</html:checkbox>
		</td>
	</tr>
	<tr>

		<td colspan="4" align="right">
			<label>Penalidad: </label>	
			<input type="text" id="penalidad" value="$ ${penalidad}" readonly="readonly"  size="10"/>		
		</td>
	</tr>
	<tr>			
		<td>
			<label><bean:message key="lbl.cah.cahCuentaAbonar" /></label>:
		</td>
		<td colspan="3">
			<html:select property="ctaCasCuentaAsociado.casCuenta" value="${form.ctaCasCuentaAsociado.casCuenta}" styleClass="obligatorio">
   				<html:optionsCollection  label="nombreCuenta" name="lstCuentas" value="id"/>     					
			</html:select>
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
		
	</tr>
	
	<tr>
		<td align="center" colspan="4">
			<html:submit property="accion" >
				<bean:message key="cmd.cah.cierreCuentaAhorro" />
			</html:submit> 
			<html:submit property="accion" onclick="$('#pageId').val('0');" >
				<bean:message key="cmd.cah.cerrarRegresar"/>
			</html:submit>
		</td>
	</tr>
</table> 
	
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="cahId" value="${form.cahId}"/>
<html:hidden property="ctaAscAsociadoH.ascId" name="ascId" value="${form.ctaAscAsociadoH.ascId}"/>
<html:hidden property="estId" value="${form.estId}"/> 
<html:hidden property="cahSaldoActual" value="${form.cahSaldoActual}"/> 
</html:form>
