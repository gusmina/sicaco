<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript">
$(document).ready(function(){
	//esconde o muestra el contenido del div
	$(".msg_head").click(function(){
		$(this).next(".msg_body").slideToggle(425);
	});
});
$(document).ready(function(){
	$("#tipoAsociado").attr("disabled","disabled");
	$("#estado").attr("disabled","disabled");
	$("#genero").attr("disabled","disabled");
	$("#tipoDomicilio").attr("disabled","disabled");
	$("#departamento").attr("disabled","disabled");
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


<html:form action="${_accion}" method="post" styleId="formId"
	readonly="true">
	<p class="msg_head">
		<bean:message key="lbl.asc.ascInformacion" />
	</p>
	<div class="msg_body">
		<table border="0">
			<tr>
				<td>
					<label>
						Asociado Padre:
					</label>
				</td>
				<td>
					<label>
						<bean:write name="asociadoPadre" property="perPrimerNombre" />
						&nbsp;
						<bean:write name="asociadoPadre" property="perSegundoNombre" />
						&nbsp;
						<bean:write name="asociadoPadre" property="perPrimerApellido" />
					</label>
				</td>
			</tr>
			<tr><%-- Inicio fila del codigo de asociado y tipo de asociado --%>
				<td>
					<label><bean:message key="lbl.asc.ascCodigo"/></label>
				</td>
				<td>
					<html:text  maxlength="50" size="20" property="ascCodigo" styleClass="obligatorio" value="${form.ascCodigo}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
				<td>
					<label><bean:message key="lbl.asc.ascTipo"/></label>
				</td>
				<td>
				   <html:select property="ctaTasTipoAsociado.tasId" styleClass="obligatorio" value="${form.ctaTasTipoAsociado.tasId}" styleId="tipoAsociado">
						<html:optionsCollection  label="tasNombre" name="tasList" value="tasId"/>     					
				   </html:select>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr><%--Fin  Fila del codigo de asociado y tipo de asociado --%>
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascProfesion"/></label>
				</td>
				<td>
					<html:text  maxlength="50" size="20" property="ascProfesion" styleClass="obligatorio" value="${form.ascProfesion}"/>
				</td>
				<td>
					<label><bean:message key="lbl.asc.ascEstado"/></label>
				</td>
				<td>
				   <html:select property="estId" styleClass="obligatorio" value="${form.estId}" styleId="estado">
						<html:optionsCollection  label="estNombre" name="estList" value="estId"/>     					
				   </html:select>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascSalario"/></label>
				</td>
				<td>
					<html:text  maxlength="50" size="20" property="ascSalario" styleClass="obligatorio" value="${form.ascSalario}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
				<td>
					<label><bean:message key="lbl.asc.ascRazonEstatus"/></label>
				</td>
				<td>
				<html:text  maxlength="10000" size="20" property="ctaNotNotas.notNota" styleClass="obligatorio" value="${form.ctaNotNotas.notNota}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascDirTrab"/></label>
				</td>
				<td>
					<html:checkbox property="otrTrab" value="true"></html:checkbox>
				</td>
				<td colspan="2">
					<html:textarea property="ascDirTrabajo" rows="1" disabled="true"/>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascJefeInmediato"/></label>
				</td>
				<td>
					<html:text  maxlength="100" size="20" property="ascJefeInmediato" styleClass="obligatorio" value="${form.ascJefeInmediato}"/>
				</td>
				<td>
					<label><bean:message key="lbl.asc.ascFechaIngresoCia"/></label>
				</td>
				<td>
					<html:text style="float:left;" styleId="ascIngresoCia" styleClass="obligatorio"
						onkeyup="mask(this);" value="${form.ascIngresoCia}"
						property="ascIngresoCia" maxlength="10" size="10" />
					<input style="height: 18px;" id="button_ascIngresoCia"
							type="button" value="..." />
					<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
					<script type="text/javascript">
		         		 Calendar.setup({
		           			inputField    : "ascIngresoCia",
		             		button        : "button_ascIngresoCia",
		            		align         : "Br"
		            	 });
		    		</script>
		    	</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascIsss"/></label>
				</td>
				<td>
					<html:text  maxlength="15" size="20" property="ascIsss" value="${form.ascIsss}"/>
				</td>
			</tr>
			</table> 
			</div>
			<%-- Datos Personales --%>
		<p class="msg_head">
				<bean:message key="lbl.asc.ascPersonales"/>
		</p>
		<!--
		
		 -->
			<div class="msg_body">
			<table border="0">
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascPrimerNombre"/></label>
				</td>
				<td>
					<html:text  maxlength="25" size="20" property="secPerPersona.perPrimerNombre" styleClass="obligatorio" value="${form.secPerPersona.perPrimerNombre}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
				<td>
					<label><bean:message key="lbl.asc.ascDui"/></label>
				</td>
				<td>
					<html:text  maxlength="25" size="20" property="secPerPersona.perDui" styleClass="obligatorio" value="${form.secPerPersona.perDui}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascSegundoNombre"/></label>
				</td>
				<td>
					<html:text  maxlength="25" size="20" property="secPerPersona.perSegundoNombre" styleClass="obligatorio" value="${form.secPerPersona.perSegundoNombre}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
				<td>
					<label><bean:message key="lbl.asc.ascDuiLugarExp"/></label>
				</td>
				<td>
					<html:text  maxlength="100" size="20" property="ascDuiLugarExp" styleClass="obligatorio" value="${form.ascDuiLugarExp}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascTercerNombre"/></label>
				</td>
				<td>
					<html:text  maxlength="25" size="20" property="secPerPersona.perTercerNombre" styleClass="obligatorio" value="${form.secPerPersona.perTercerNombre}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
				<td>
					<label><bean:message key="lbl.asc.ascDuiFechaExp"/></label>
				</td>
				<td>
					<html:text style="float:left;" styleId="ascDuiFechaExp" styleClass="obligatorio"
						onkeyup="mask(this);" value="${form.ascDuiFechaExp}"
						property="ascDuiFechaExp" maxlength="10" size="10" />
					<input style="height: 18px;" id="button_ascDuiFechaExp"
							type="button" value="..." />
					<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
					<script type="text/javascript">
		         		 Calendar.setup({
		           			inputField    : "ascDuiFechaExp",
		             		button        : "button_ascDuiFechaExp",
		            		align         : "Br"
		            	 });
		    		</script>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascPrimerApellido"/></label>
				</td>
				<td>
					<html:text  maxlength="25" size="20" property="secPerPersona.perPrimerApellido" styleClass="obligatorio" value="${form.secPerPersona.perPrimerApellido}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
				<td>
					<label><bean:message key="lbl.asc.ascNit"/></label>
				</td>
				<td>
					<html:text  maxlength="14" size="20" property="secPerPersona.perNit" styleClass="obligatorio" value="${form.secPerPersona.perNit}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascSegundoApellido"/></label>
				</td>
				<td>
					<html:text  maxlength="25" size="20" property="secPerPersona.perSegundoApellido" styleClass="obligatorio" value="${form.secPerPersona.perSegundoApellido}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
				<td>
					<label><bean:message key="lbl.asc.ascNombreNit"/></label>
				</td>
				<td>
					<html:text  maxlength="200" size="20" property="ascNombreNit" styleClass="obligatorio" value="${form.ascNombreNit}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascApellidoCasada"/></label>
				</td>
				<td>
					<html:text  maxlength="25" size="20" property="secPerPersona.perApellidoCasada" styleClass="obligatorio" value="${form.secPerPersona.perApellidoCasada}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
				<td>
					<label><bean:message key="lbl.asc.ascFechaNacimiento"/></label>
				</td>
				<td>
					<html:text style="float:left;" styleId="secPerPersona.perFechaNacimiento" styleClass="obligatorio"
						onkeyup="mask(this);" value="${form.secPerPersona.perFechaNacimiento}"
						property="perFechaNacimiento" maxlength="10" size="10" />
					<input style="height: 18px;" id="button_perFechaNacimiento"
							type="button" value="..." />
					<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
					<script type="text/javascript">
		         		 Calendar.setup({
		           			inputField    : "perFechaNacimiento",
		             		button        : "button_perFechaNacimiento",
		            		align         : "Br"
		            	 });
		    		</script>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascGenero"/></label>
				</td>
				<td>
					<html:select property="secPerPersona.perGenero"  styleClass="obligatorio" styleId="genero">
          				<html:option value="M">
          					<bean:message key="lbl.asc.masculino"/>
          				</html:option>
          				<html:option value="F">
          					<bean:message key="lbl.asc.femenino"/>
          				</html:option>
          			</html:select>	
				</td>
				<td>
					<label><bean:message key="lbl.asc.ascNacionalidad"/></label>
				</td>
				<td>
					<html:text  maxlength="20" size="20" property="ascNacionalidad" styleClass="obligatorio" value="${form.ascNacionalidad}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascLugarNacimiento"/></label>
				</td>
				<td>
					<html:text  maxlength="100" size="20" property="perLugarNacimiento" styleClass="obligatorio" value="${form.secPerPersona.perLugarNacimiento}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr>
			</table>
			</div>
			<p class="msg_head">
				<bean:message key="lbl.asc.ascDomiciliares"/>
			</p>
			<div class="msg_body">
				<table border="0" >
				<tr>
				<td>
					<label><bean:message key="lbl.asc.ascTipoDomicilio"/></label>
				</td>
				<td>
					<html:select property="ctaDomDomicilio.domId"  styleClass="obligatorio" value="${form.ctaDomDomicilio.domId}" styleId="tipoDomicilio">
						<html:optionsCollection name="tDomList" value="domId" label="domNombre"/>
          			</html:select>	
				</td>
				<td>
					<label><bean:message key="lbl.asc.ascRentaDomicilio"/></label>
				</td>
				<td>
					<html:text  maxlength="20" size="20" property="ascRentaDomicilio" styleClass="obligatorio" value="${form.ascRentaDomicilio}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascDepartamento"/></label>
				</td>
				<td>
					<html:select property="secPerPersona.secDppDepartamentoPais.dppId"  styleClass="obligatorio" value="${form.secPerPersona.secDppDepartamentoPais.dppId}" styleId="departamento">
						<html:optionsCollection name="dppList" value="dppId" label="dppNombre"/>
          			</html:select>	
				</td>
				<td>
					<label><bean:message key="lbl.asc.ascMunicipio"/></label>
				</td>
				<td>
					<html:text  maxlength="50" size="20" property="secPerPersona.perMunicipio" styleClass="obligatorio" value="${form.secPerPersona.perMunicipio}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascCalle"/></label>
				</td>
				<td>
					<html:text  maxlength="50" size="20" property="secPerPersona.perCalle" styleClass="obligatorio" value="${form.secPerPersona.perCalle}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
				<td>
					<label><bean:message key="lbl.asc.ascColoniaBarrio"/></label>
				</td>
				<td>
					<html:text  maxlength="50" size="20" property="secPerPersona.perColoniaBarrio" styleClass="obligatorio" value="${form.secPerPersona.perColoniaBarrio}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.asc.ascCodigoPostal"/></label>
				</td>
				<td>
					<html:text  maxlength="50" size="20" property="secPerPersona.perCodigoPostal" styleClass="obligatorio" styleId="perCodigoPostal" value="${form.secPerPersona.perCodigoPostal}"/>
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr>
			</table>
			</div>
	<table>
		<tr>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.asc.regresarListaDep" />
				</html:submit>
			</td>
		</tr>
	</table>
	<html:hidden property="ascAsociadoPadre" value="${form.ascAsociadoPadre}"/>
</html:form>
