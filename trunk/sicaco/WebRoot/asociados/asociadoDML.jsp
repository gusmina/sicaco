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
	$("#ascRentaDomicilio").val(round_number( $("#ascRentaDomicilio").val(),2));
	$("#ascSalario").val(round_number( $("#ascSalario").val(),2));
    $("#ascRentaDomicilio").numeric({allow:"."});
	$("#ascSalario").numeric({allow:",."});
	$("#ascCodigo").alphanumeric();
	$("#ascProfesion").alpha({allow:" "});
	$("#ascRazon").alpha({allow:" "});
	$("#primerNombre").alpha({allow:" "});
	$("#segundoNombre").alpha({allow:" "});
	$("#tercerNombre").alpha({allow:" "});
	$("#primerApellido").alpha({allow:" "});
	$("#segundoApellido").alpha({allow:" "});
	$("#apellidoCasada").alpha({allow:" "});
	$("#nombreNit").alpha({allow:" "});
	$("#jefeInmediato").alpha({allow:" "});
	$("#isss").numeric();
	$("#lugarDui").alpha({allow:" "});
	$("#lugarNacimiento").alpha({allow:" "});
	$("#nacionalidad").alpha({allow:" "});
	$("#municipio").alpha({allow:" "});
	$("#calle").alphanumeric({allow:" /&#-"});
	$("#colonia").alphanumeric({allow:" /&#-.,"});
	$("#casa").alphanumeric({allow:" /&#-"});
	$("#combo123").attr("disabled","disabled");
	$("#appse").removeAttr("disabled");
  });
   	
 $(document).ready(function(){
 		$("#otrTrab").click(function() {
        var chk = $("#otrTrab")[0];
        var val = chk.checked;
       	if (val){
       		$("#ascDirTrabajo").removeAttr("readonly");
       		$("#lugTrab").attr("disabled","disabled");
       		$("#dptNombreId").attr("disabled","disabled");
       	}else{
       		$("#ascDirTrabajo").attr("readonly","true");
       		$("#lugTrab").removeAttr("disabled");
       		$("#dptNombreId").removeAttr("disabled");
       	}
	});	
 }); 
 
 function saveSeleccionDpt(valor) {
		valores = valor.split(';')
		$('#dptIdId').val(valores[0])
		$('#dptNombreId').val(valores[1])
		$('#centroCostoId').val(valores[2])
		$('#resultadoDpt').hide('slow')
	} 
 
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
<html:form action="${_accion}" method="post" styleId="formId" >
	<script>
      		var patternDui = new Array(${tamDui}-1,1);
      </script>
	<p class="msg_head">
		<bean:message key="lbl.asc.ascInformacion" />
	</p>
	<div class="msg_body" align="center">
		<table border="0" >
<logic:present name="asociadoPadre">
				<tr>
					<td>
						<label class="obligatorio">
							<bean:message key="lbl.asociado.dependeDe" />
							:
						</label>
					</td>
					<td>
						<label>
							<bean:write name="asociadoPadre"
								property="secPerPersona.perPrimerNombre" />
						</label>
						&nbsp;
						<label>
							<bean:write name="asociadoPadre"
								property="secPerPersona.perSegundoNombre" />
						</label>
						&nbsp;
						<label>
							<bean:write name="asociadoPadre"
								property="secPerPersona.perPrimerApellido" />
						</label>
					</td>
					<td>
						<label class="obligatorio">
							<bean:message key="lbl.asc.ascCodigoAsociado" />
						</label>
					</td>
					<td>
						&nbsp;
						<label>
							<bean:write name="asociadoPadre" property="ascCodigoAsociado" />
						</label>
					</td>
				</tr>
				<tr>
				<td colspan="4">
				<html:checkbox property="ascPagaraPadre" value="S" styleId="checkboxPadrePaga"><label><bean:message key="lbl.dependiente.pagaPadre" /></label></html:checkbox>
				</td>
			</tr>
			</logic:present>
			<tr>
				<%-- Inicio fila del codigo de asociado y tipo de asociado --%>
				<td>
					<label><bean:message key="lbl.asc.ascCodigoAsociado" /></label>
				</td>
				<td>
					<html:text property="ascCodigoAsociado" styleClass="obligatorio"
						readonly="true" styleClass="obligatorio"/>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascCodigoTrabajo" />
					</label>
				</td>
				<td>
					<logic:present name="mdf">
						<html:text maxlength="50" size="20" property="ascCodigo"
							styleClass="obligatorio" value="${form.ascCodigo}"
							styleId="ascCodigo" readonly="true"/>					
					</logic:present>
					<logic:notPresent name="mdf">
						<html:text maxlength="50" size="20" property="ascCodigo"
							styleClass="obligatorio" value="${form.ascCodigo}"
							styleId="ascCodigo"/>					
					</logic:notPresent>									
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<logic:present name="mdf">
				<tr>
					<td>
						<label>
							<bean:message key="lbl.asc.ascEstado" />
						</label>
					</td>
					<td>
						<html:select property="estId" styleClass="obligatorio"
							value="${form.estId}" styleId="estId">
							<html:optionsCollection name="estList" label="estNombre"
								value="estId" />
						</html:select>
					</td>
					<td>
						<label>
							<bean:message key="lbl.asc.ascRazonEstatus" />
						</label>
					</td>
					<td>
						<html:textarea property="ctaNotNotas.notNota"
							styleClass="obligatorio" value="${form.ctaNotNotas.notNota}"
							styleId="razonEstado" />
					</td>
					</tr>
				</logic:present>
			<tr>
						<td>
					<label>
						<bean:message key="lbl.asc.ascTipo" />
					</label>
				</td>
				<td>
					<html:select property="ctaTasTipoAsociado.tasId"
						styleClass="obligatorio" value="${form.ctaTasTipoAsociado.tasId}">
						<html:optionsCollection label="tasNombre" name="tasList"
							value="tasId" />
					</html:select>
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascProfesion" />
					</label>
				</td>
				<td>
					<html:text maxlength="50" size="20" property="ascProfesion"
						 value="${form.ascProfesion}"
						styleId="ascProfesion" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascSalario" />
					</label>
				</td>
				<td>
					<html:text maxlength="50" size="20" property="ascSalario"
						styleClass="obligatorio" value="${form.ascSalario}"
						styleId="ascSalario" onkeyup="dosDecimales($('#ascSalario').val(),'ascSalario');"/>
					<span><bean:message key="msg.obligatorio" />
					</span>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascTipoContrato" />
					</label>
				</td>
				<td>
					<html:select property="ctaTcoTipoContrato.tcoId"
						styleClass="obligatorio"
						value="${form.ctaDptDepartamentoTrabajo.dptId}"
						styleId="ctaTcoTipoContrato">
						<html:optionsCollection name="tcoList" label="tcoNombre"
							value="tcoId" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascLugarTrabajoInterno" />
					</label>
				</td>
				<td>
					<html:select
						property="ctaDptDepartamentoTrabajo.ctaEtrEmpresaTrabajo.etrId"
						styleClass="obligatorio"
						value="${form.ctaDptDepartamentoTrabajo.ctaEtrEmpresaTrabajo.etrId}"
						styleId="lugTrab">
						<html:option value="-1">----</html:option>
						<html:optionsCollection label="etrNombre" name="trabList"
							value="etrId" />
					</html:select>
					<span><bean:message key="msg.obligatorio" />
					</span>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.centroCosto" />
					</label>
				</td>
				<td>
					<html:text property="centroCosto" styleId="centroCostoId" value="${form.centroCosto}"/>
					<input type="button" value="..." 
					onclick="ajaxCallSincrono('${pageContext.request.contextPath}/asociados${_accion}.do','accion=cargarListaDepartamentos&centroCosto='+$('#centroCostoId').val()+'&etr='+$('#lugTrab').val(),'departamentosDiv')"/>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="departamentosDiv" align="center">
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.dptNombre" />
					</label>
				</td>
				<td colspan="3">
					<html:hidden property="ctaDptDepartamentoTrabajo.dptId" styleId="dptIdId" value="${form.ctaDptDepartamentoTrabajo.dptId}" />
					<html:text property="dptNombre" styleId="dptNombreId" 
					 size="70" readonly="true" ></html:text>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascDirTrab" />
					</label>
				</td>
				<td>
					<html:checkbox property="otrTrab" value="true" styleId="otrTrab"></html:checkbox>
					<!-- <input type="checkbox" checked="${form.otrTrab}" value="true" id="otrTrab" name="otrTrab"> -->
				</td>
				<td colspan="2">
					<html:textarea property="ascDirTrabajo" rows="1"
						readonly="${!form.otrTrab}" styleId="ascDirTrabajo" />
					&#42;
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascJefeInmediato" />
					</label>
				</td>
				<td>
					<html:text maxlength="100" size="20" property="ascJefeInmediato"
						 value="${form.ascJefeInmediato}"
						styleId="jefeInmediato" />
				</td>
				<logic:notPresent name="asociadoPadre">
					<td>
						<label>
							<bean:message key="lbl.asc.ascFechaIngresoCia" />
						</label>
					</td>
					<td>
						<html:text style="float:left;" styleId="ascIngresoCia"
							styleClass="obligatorio" onkeyup="mask(this);"
							value="${form.ascIngresoCia}" property="ascIngresoCia"
							maxlength="10" size="10" />
						<span><bean:message key="msg.obligatorio" />
						</span>
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
				</logic:notPresent>
			</tr>
			<logic:equal  name="form" property="mdf" value="false">
			<tr>
				<td>
					<label>
						<bean:message key="lbl.correo.id.celCorreoElectronico" />
						:
					</label>
				</td>
				<td>
					<html:text property="correoElectronico" styleClass="obligatorio"
						 />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			</logic:equal>
		</table>
	</div>
	<%-- Datos Personales --%>
	<p class="msg_head">
		<bean:message key="lbl.asc.ascPersonales" />
	</p>
	<!--
		
		 -->
	<div class="msg_body" align="center">
		<table border="0">
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascPrimerNombre" />
					</label>
				</td>
				<td>
					<html:text maxlength="25" size="20"
						property="secPerPersona.perPrimerNombre" styleClass="obligatorio"
						value="${form.secPerPersona.perPrimerNombre}"
						styleId="primerNombre" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascSegundoNombre" />
					</label>
				</td>
				<td>
					<html:text maxlength="25" size="20"
						property="secPerPersona.perSegundoNombre" 
						value="${form.secPerPersona.perSegundoNombre}"
						styleId="segundoNombre" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascTercerNombre" />
					</label>
				</td>
				<td>
					<html:text maxlength="25" size="20"
						property="secPerPersona.perTercerNombre" 
						value="${form.secPerPersona.perTercerNombre}"
						styleId="tercerNombre" />
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascPrimerApellido" />
					</label>
				</td>
				<td>
					<html:text maxlength="25" size="20"
						property="secPerPersona.perPrimerApellido"
						styleClass="obligatorio"
						value="${form.secPerPersona.perPrimerApellido}"
						styleId="primerApellido" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascSegundoApellido" />
					</label>
				</td>
				<td>
					<html:text maxlength="25" size="20"
						property="secPerPersona.perSegundoApellido"
						
						value="${form.secPerPersona.perSegundoApellido}"
						styleId="segundoApellido" />
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascApellidoCasada" />
					</label>
				</td>
				<td>
					<html:text maxlength="25" size="20"
						property="secPerPersona.perApellidoCasada"
						
						value="${form.secPerPersona.perApellidoCasada}"
						styleId="apellidoCasada" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascDui" />
					</label>
				</td>
				<td>
					<html:text maxlength="25" size="${tamDui}" onkeyup="maskDui(this);"
						size="20" property="secPerPersona.perDui" styleClass="obligatorio"
						value="${form.secPerPersona.perDui}"  />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascDuiLugarExp" />
					</label>
				</td>
				<td>
					<html:text maxlength="100" size="20" property="ascDuiLugarExp"
						styleClass="obligatorio" value="${form.ascDuiLugarExp}"
						styleId="lugarDui" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
				</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascDuiFechaExp" />
					</label>
				</td>
				<td>
					<html:text style="float:left;" styleId="ascDuiFechaExp"
						styleClass="obligatorio" onkeyup="mask(this);"
						value="${form.ascDuiFechaExp}" property="ascDuiFechaExp"
						maxlength="10" size="10" /><span><bean:message key="msg.obligatorio" /> </span>
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
				<td>
					<label>
						<bean:message key="lbl.asc.ascIsss" />
					</label>
				</td>
				<td>
					<html:text maxlength="15" size="20" property="ascIsss"
						 value="${form.ascIsss}" styleId="isss" />
					<!-- styleClass="obligatorio" <span><bean:message key="msg.obligatorio" /> </span> -->
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascNit" />
					</label>
				</td>
				<td>
					<html:text onkeyup="maskNit(this);" maxlength="17" size="17"
						property="perNit" styleClass="obligatorio" value="${form.perNit}"
						 />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascNombreNit" />
					</label>
				</td>
				<td>
					<html:text maxlength="200" size="20" property="ascNombreNit"
						styleClass="obligatorio" value="${form.ascNombreNit}"
						styleId="nombreNit" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>

			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascGenero" />
					</label>
				</td>
				<td>
					<html:select property="secPerPersona.perGenero"
						styleClass="obligatorio">
						<html:option value="M">
							<bean:message key="lbl.asc.masculino" />
						</html:option>
						<html:option value="F">
							<bean:message key="lbl.asc.femenino" />
						</html:option>
					</html:select>
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascFechaNacimiento" />
					</label>
				</td>
				<td>
					<html:text style="float:left;"
						styleId="secPerPersonaFechaNacimiento" styleClass="obligatorio"
						onkeyup="mask(this);" value="${form.secPerPersonaFechaNacimiento}"
						property="secPerPersonaFechaNacimiento" maxlength="10" size="10" />
						<span><bean:message key="msg.obligatorio" /> </span>
					<input style="height: 18px;"
						id="button_secPerPersonaFechaNacimiento" type="button" value="..." />
					<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
					<script type="text/javascript">
		         		 Calendar.setup({
		           			inputField    : "secPerPersonaFechaNacimiento",
		             		button        : "button_secPerPersonaFechaNacimiento",
		            		align         : "Br"
		            	 });
		    		</script>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascLugarNacimiento" />
					</label>
				</td>
				<td>
					<html:text maxlength="100" size="20"
						property="secPerPersona.perLugarNacimiento"
						styleClass="obligatorio"
						value="${form.secPerPersona.perLugarNacimiento}"
						styleId="lugarNacimiento" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascNacionalidad" />
					</label>
				</td>
				<td>
					<html:text maxlength="20" size="20" property="ascNacionalidad"
						styleClass="obligatorio" value="${form.ascNacionalidad}"
						styleId="nacionalidad" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
		</table>
	</div>
	<p class="msg_head">
		<bean:message key="lbl.asc.ascDomiciliares" />
	</p>
	<div class="msg_body" align="center">
		<table border="0">
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascTipoDomicilio" />
					</label>
				</td>
				<td>
					<html:select property="ctaDomDomicilio.domId"
						styleClass="obligatorio" value="${form.ctaDomDomicilio.domId}">
						<html:optionsCollection name="tDomList" value="domId"
							label="domNombre" />
					</html:select>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascRentaDomicilio" />
					</label>
				</td>
				<td>
					<html:text maxlength="20" size="20" property="ascRentaDomicilio"
						styleClass="obligatorio" value="${form.ascRentaDomicilio}"
						styleId="ascRentaDomicilio" onkeyup="dosDecimales($('#ascRentaDomicilio').val(),'ascRentaDomicilio');"/>
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascDepartamento" />
					</label>
				</td>
				<td>
					<html:select property="secPerPersona.secDppDepartamentoPais.dppId"
						styleClass="obligatorio"
						value="${form.secPerPersona.secDppDepartamentoPais.dppId}">
						<html:optionsCollection name="dppList" value="dppId"
							label="dppNombre" />
					</html:select>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascMunicipio" />
					</label>
				</td>
				<td>
					<html:text maxlength="50" size="20"
						property="secPerPersona.perMunicipio" styleClass="obligatorio"
						value="${form.secPerPersona.perMunicipio}" styleId="municipio" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascCalle" />
					</label>
				</td>
				<td>
					<html:text maxlength="50" size="20"
						property="secPerPersona.perCalle" styleClass="obligatorio"
						value="${form.secPerPersona.perCalle}" styleId="calle" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascColoniaBarrio" />
					</label>
				</td>
				<td>
					<html:text maxlength="50" size="20"
						property="secPerPersona.perColoniaBarrio" styleClass="obligatorio"
						value="${form.secPerPersona.perColoniaBarrio}" styleId="colonia" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascCodigoPostal" />
					</label>
				</td>
				<td>
					<html:text maxlength="50" size="20"
						property="secPerPersona.perCodigoPostal" styleClass="obligatorio"
						styleId="casa" value="${form.secPerPersona.perCodigoPostal}" />
					<span><bean:message key="msg.obligatorio" />
					</span>
				</td>
			</tr>
		</table>
	</div>
	<p class="msg_head">
		<bean:message key="lbl.asc.ascAfiliacion" />
	</p>
	<div class="msg_body" align="center">
		<label><bean:message key="lbl.asc.mensajeAfiliacion"/>:<bean:write name="cuotaAfiliacion"/></label>
		<table align="center">
			<tr>
				<td>
					<label class="obligatorio"><bean:message key="lbl.asc.cuotaAfiliacionCancelada"/></label>
				</td>
				<td>
					<logic:notEqual name="form" property="ascPagoIngreso" value="S">
					<html:select property="ascPagoIngreso" styleClass="obligatorio" styleId="appse">
						<html:option value="N">NO</html:option>
						<html:option value="S">SI</html:option>
					</html:select>
					<span><bean:message key="msg.obligatorio" /> </span>
					</logic:notEqual>
					<logic:equal name="form" property="ascPagoIngreso" value="S">
					<html:select property="ascPagoIngreso" styleClass="obligatorio" styleId="combo123">
						<html:option value="N">NO</html:option>
						<html:option value="S">SI</html:option>
					</html:select>
					</logic:equal>
				</td>
			</tr>
		</table>
	</div>
		<logic:equal value="false" property="usr" name="form">
		<table align="center">
		<tr>
			<td>
				<html:submit property="accion"  disabled="${form.entrada}">
					<bean:message key="cmd.asc.guardar" />
				</html:submit>
			</td>
			<td>
				<html:submit property="accion" onclick="$('#pageId').val('0');cleanFields('formId');">
					<bean:message key="cmd.asc.cancelar" />
				</html:submit>
			</td>
									<logic:equal value="true" name="form" property="entrada">
						<td>
				<html:submit property="accion" onclick="$('#pageId').val('0');" >
					<bean:message key="cmd.asc.generarComprobante" />
				</html:submit></td><td>
				<html:submit property="accion" onclick="$('#pageId').val('0');" >
					<bean:message key="cmd.asc.forwardToAport" />
				</html:submit></td>
			</logic:equal> 
		</tr>
	</table>
	</logic:equal>
	<logic:equal value="true" property="usr" name="form">
		<table align="center">
		<tr>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.asc.save" />
				</html:submit>
			</td>
			<td>
				<html:submit property="accion" onclick="$('#pageId').val('0');cleanFields('formId');">
					<bean:message key="cmd.asc.cancel" />
				</html:submit>
			</td>

		</tr>
	</table>
	</logic:equal>
	<html:hidden property="mdf" value="${form.mdf}" />
	<html:hidden property="ascAsociadoPadre"
		value="${form.ascAsociadoPadre}" />
	<html:hidden property="viejoCodigo" value="${form.viejoCodigo}" />
	<html:hidden property="ctrEstEstado.estId"
		value="${form.ctrEstEstado.estId}" />
	<html:hidden property="ascPagoIngreso" styleId="prop123" />
	<html:hidden property="ctaNotNotas.notId"
		value="${form.ctaNotNotas.notId}" />
	<html:hidden property="notFecha" value="${form.notFecha}" />
	<html:hidden property="notaTemp" value="${form.ctaNotNotas.notNota}" />
	<html:hidden property="ascDividendoAcumulado"
		value="${form.ascDividendoAcumulado}" />
	<html:hidden property="secPerPersonaAudFechaCreacion"
		value="${form.secPerPersonaAudFechaCreacion}" />
	<html:hidden property="secPerPersona.audUsuarioCreacion"
		value="${form.secPerPersona.audUsuarioCreacion}" />
	<html:hidden property="secPerPersona.perEstado"
		value="${form.secPerPersona.perEstado}" />
	<html:hidden property="perId" value="${form.perId}" />
	<html:hidden property="ascId" value="${form.ascId}" />
	<html:hidden property="ascIngresoCoope" value="${form.ascIngresoCoope}" />
	<html:hidden property="ascRetiroCoope" value="${form.ascRetiroCoope}" />
	<html:hidden property="usr" value="${form.usr}" />
	<html:hidden property="entrada" />
	<input id="pageId" type="hidden" name="page" value="3">
</html:form>
