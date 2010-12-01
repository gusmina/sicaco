<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
$(document).ready(function(){
	//esconde o muestra el contenido del div
	$(".msg_head").click(function(){
		$(this).next(".msg_body").slideToggle(425);
	});
	
   	$("#cahCuota").numeric({allow:"."});
	$("#casValorApertura").numeric({allow:"."});
});		
</script>
<style type="text/css">
p {
	padding: 0 0 1em;
}

.msg_head {
	padding: 5px 10px;
	cursor: pointer;
	position: relative;
	background-color: #CACBDF;
	margin: 1px;
}

.msg_body {
	padding: 5px 10px 15px;
	background-color: auto;
}
</style>
<html:form action="${_accion}" method="post"  styleId="formId">
	<p class="msg_head">
		Datos propios de Cuenta de Ahorro
	</p>
	<div class="msg_body">
		<table align="center">
	
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
				<td>
					<label>
						<bean:write name="referencia" property="cahId"/>		
					</label>
				</td>
				<td>
					<label><bean:message key="lbl.cap.capFechaActual" /></label>:
				</td>
				<td>
					<label>
						${form.casFechaApertura}		
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.cah.TipoAhorro" /></label>:
				</td>
				<td>
					<html:select property="ctaTahTipoAhorro.tahId" 
								 value="${form.ctaTahTipoAhorro.tahId}"
								 styleId="tipoAhorro"
								 styleClass="obligatorio" 
								 onchange="ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do','accion=cargarDatosTipoAhorro&ctaTahTipoAhorro.tahId='+$('#tipoAhorro').val(),'datosTiAh')">
		   				<html:optionsCollection  label="tahNombre" name="lstTahTipoAhorro" value="tahId"/>     					
					</html:select>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
				<td colspan="2">
					<div id="datosTiAh">
						<logic:notPresent parameter="mdf" scope="request">
							<label></label>
						</logic:notPresent>
						<logic:present parameter="mdf" scope="request">
							<label>Plan : </label>
							<label>${form.ctaTahTipoAhorro.ctaPlmPlanMeses.plmNombre} ;  </label>
							<label>Tasa de Interes : </label>
							<label>${form.ctaTahTipoAhorro.ctaTinTasaInteres.tinTasa }</label>
						</logic:present>
					</div>
				</td>
			</tr>
							
			<tr>			
				<td>
					<label><bean:message key="lbl.cap.capCuota" /></label>:
				</td>
				<td>
					<html:text  style="numeric" maxlength="15" size="10" property="cahCuota" value="${form.cahCuota}" styleId="cahCuota"/>
				</td>
				<td>
					<label><bean:message key="lbl.cap.capCasValorApertura" /></label>:
				</td>
				<td>
					<html:text  style="numeric" maxlength="15" size="10" property="casValorApertura" value="${form.casValorApertura}" styleId="casValorApertura" readonly="${form.mdf}"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="4">
					<html:submit property="accion" onclick="$('#pageId').val('0');" disabled="${form.apertura}">
						<bean:message key="cmd.cah.guardar" />
					</html:submit> 
					<html:submit property="accion" onclick="$('#pageId').val('0');" >
						<bean:message key="cmd.cah.cerrarRegresar" />
					</html:submit>
					<input type="button" value="Limpiar" onclick="cleanFields('formId');">
					<logic:equal value="true" name="form" property="apertura">
						<html:submit property="accion" onclick="$('#pageId').val('0');" >
						<bean:message key="cmd.cah.generarComprobante" />
					</html:submit>
					<html:submit property="accion" onclick="$('#pageId').val('0');" >
						<bean:message key="cmd.cah.continuarBeneficiarios" />
					</html:submit>
					</logic:equal> 
				</td>
			</tr>
		</table> 
	</div>
	<p class="msg_head">
		Busqueda de Movimientos
	</p>
	<div class="msg_body">
		<table align="center">
			<tr>
				<td>
					<label><bean:message key="lbl.cap.capFechaIni" /></label>: 
				</td>
				<td>
					<logic:equal value="1" name="nuevoAhorro">
					<html:text onkeyup="mask(this);" property="fechaIni" value="${form.fechaIni}" styleId="fechaIni"
								styleClass="obligatorio"	maxlength="10" size="10" disabled="true"/>
					<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaIni" type="button" value="...." readonly="readonly"/>
					</logic:equal>
					<logic:notEqual value="1" name="nuevoAhorro">
					<html:text onkeyup="mask(this);" property="fechaIni" value="${form.fechaIni}" styleId="fechaIni"
								styleClass="obligatorio"	maxlength="10" size="10"/> 
					<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaIni" type="button" value="...." />
					</logic:notEqual>
				</td>
				<td>
					<label><bean:message key="lbl.cap.capFechaFin" /></label>:
					
				</td>
				<td>
					<logic:equal value="1" name="nuevoAhorro">
					<html:text onkeyup="mask(this);" property="fechaFin" value="${form.fechaFin}" styleId="fechaFin"
								styleClass="obligatorio"	maxlength="10" size="10" disabled="true"/>
					<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaFin" type="button" value="...." readonly="readonly"/>
					</logic:equal>
					<logic:notEqual value="1" name="nuevoAhorro">
					<html:text onkeyup="mask(this);" property="fechaFin" value="${form.fechaFin}" styleId="fechaFin"
								styleClass="obligatorio"	maxlength="10" size="10"/>
					<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaFin" type="button" value="...." />
					</logic:notEqual>
				</td>
			</tr>
			<script type="text/javascript">
					Calendar.setup({
					            inputField    : "fechaIni",
						        button        : "button_fechaIni",
						        align         : "Br"
						           });
						       function handlerCombo(){
						   	$(fechaIni).toggle();
						            }
		   </script>
		   <script type="text/javascript">
					Calendar.setup({
					            inputField    : "fechaFin",
						        button        : "button_fechaFin",
						        align         : "Br"
						           });
						       function handlerCombo(){
						   	$(fechaFin).toggle();
						            }
		   </script>
		  	<tr>
				<td align="center" colspan="4">
					<logic:equal value="1" name="nuevoAhorro">
						<html:submit property="accion" disabled="true">
							<bean:message key="cmd.cah.buscarMovimientos" />
						</html:submit> 
					</logic:equal>
					<logic:notEqual value="1" name="nuevoAhorro">
						<html:submit property="accion">
							<bean:message key="cmd.cah.buscarMovimientos" />
						</html:submit> 
					</logic:notEqual>
				</td>
			</tr>
		</table>
	</div>

<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="cahId" value="${form.cahId}"/>
<html:hidden property="ctaAscAsociadoH.ascId" name="ascId" value="${form.ctaAscAsociadoH.ascId}"/>
<html:hidden property="mdf" value="${form.mdf}"/>
<html:hidden property="casFechaApertura" value="${form.casFechaApertura}"/>
<html:hidden property="casValorApertura" value="${form.casValorApertura}"/> 
<html:hidden property="estId" value="${form.estId}"/> 
<html:hidden property="cahSaldoActual" value="${form.cahSaldoActual}"/> 
<html:hidden property="cahCuota" value="${form.cahCuota}"/>
<html:hidden property="apertura"/>
<html:hidden property="comprobante"/>
<html:hidden property="casCuenta"/>  
</html:form>

