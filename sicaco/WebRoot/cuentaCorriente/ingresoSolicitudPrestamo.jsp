<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
<!--  

	$(document).ready(function() {
		var wizardStartByStepNmbr		= 1;									// Define by which step the wizard should start
		var hideAllStepApartCurrent		= true;									// Define if you want to hide steps, that are not currently selected
		var showCurrentStepNmbr			= true; 								// Define if the current step number should be shown 
		var showCurrentStepTitle		= true;									// Define wheter you want to show the step title or not
		var attrToGetStepTitle			= "title";								// Define in which attribute you store the step title
		var showCurrentStepText_Title	= "-"; 									// [Text] used to make this: Step 1 of 3 [Text] Steptitle
		var elementToTransformToWizard	= ".stepByStepWizard";					// Define the class into which you packed your single steps (use jQuery Syntax)
		var elementToAddNavi			= ".stepByStepWizardNavigation";		// Define to which element you want to add the navigation
		var showCurrentStepText_step	= "Paso&nbsp;"; 							// Text used for "Step"
		var showCurrentStepText_of		= "&nbsp;de&nbsp;"; 								// Text used for "/"
		var showCurrentStepText_forward	= "<img height='15' width='15' src='../imagenes/varios/next_orange.gif'></img>"; 	// Text used for "Forward"
		var showCurrentStepText_back	= "<img height='15' width='15' src='../imagenes/varios/back_orange.gif'></img>"; 	// Text used for "Back"
		wizardConfig(wizardStartByStepNmbr,hideAllStepApartCurrent,showCurrentStepNmbr,showCurrentStepTitle,attrToGetStepTitle,showCurrentStepText_Title,elementToTransformToWizard,elementToAddNavi,showCurrentStepText_step,showCurrentStepText_of,showCurrentStepText_forward,showCurrentStepText_back);
   
 		$('#cheque').click(function() {
 			$('#hidFuente').val("true");
 			$('#casCuenta').attr("disabled","disabled");
		});	
		
		$('#cuenta').click(function() {
 			$('#hidFuente').val("false");
 			$('#casCuenta').removeAttr("disabled");
 		});	
		
		$("#lineaCombo").change(function() {
			ajaxCallSincrono('${pageContext.request.contextPath}/cuentaCorriente/tipoPrestamo.do','accion=<bean:message key="cmd.tpr.cargarListaTiposPrestamo"/>&lprId='+$(this).val(),'tiposPrestamoDiv');			
 		});
		
		$("#eliminarRpe").click(function() {
 			var jpos="";
			$(".posicionRpe").each(function(){
					if(this.checked){
						jpos+= "&posicionRpe[]=" + this.value;
					}
			});
			ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente/referenciaPersonal.do','accion=eliminarRpe'+jpos,'listaReferenciasPersonales');
 		});
 		
		$("#eliminarNotas").click(function() {
 			var jpos="";
			$(".posicionNotas").each(function(){
					if(this.checked){
						jpos+= "&posicionNotas[]=" + this.value;
					}
			});
			ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente/nota.do','accion=eliminarNota'+jpos,'listaAnotaciones');
 		});	
 		
 		$("#eliminarRco").click(function() {
 			var jpos="";
			$(".posicionRco").each(function(){
					if(this.checked){
						jpos+= "&posicionRco[]=" + this.value;
					}
			});
			ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente/referenciaComercial.do','accion=eliminarRco'+jpos,'listaReferenciasComerciales');
 		});
 		
 		$("#eliminarFxp").click(function() {
 			var jpos="";
			$(".posicionFxp").each(function(){
					if(this.checked){
						jpos+= "&posicionFxp[]=" + this.value;
					}
			});
			ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente/fiadorPrestamo.do','accion=eliminar'+jpos,'listaFiadores');
 		});	
 		
 		$("#eliminarGxp").click(function() {
 			var jpos="";
			$(".posicionGxp").each(function(){
					if(this.checked){
						jpos+= "&posicionGxp[]=" + this.value;
					}
			});
			ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente/garantiaPrestamo.do','accion=eliminar'+jpos,'listaGarantias');
 		});
 		
 		$("#eliminarDxp").click(function() {
 			var jpos="";
			$(".posicionDex").each(function(){
					if(this.checked){
						jpos+= "&posicionDex[]=" + this.value;
					}
			});
			ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente/descuentosExternos.do','accion=eliminar'+jpos,'listaDescuentos');
 		});
 		$("#preMontoSolicitado").val(round_number( $("#preMontoSolicitado").val(),2));
 		$("#garValor").val(round_number( $("#garValor").val(),2));
 		$("#rcoMonto").val(round_number( $("#rcoMonto").val(),2));
 		$("#dexMonto").val(round_number( $("#dexMonto").val(),2));
 		$("#garValor").numeric({allow:"."});
 		$("#pxtSalario").val(round_number( $("#pxtSalario").val(),2));
 		$("#dexNombreDescuento").alphanumeric({allow:" "});
 		$("#dexMonto").numeric({allow:"."});
 		$("#preMontoSolicitado").numeric({allow:"."});
 		$("#otrasDeducId").numeric({allow:"."});
 		$("#rpeNombres").alpha({allow:" "});
 		$("#rpeApellidos").alpha({allow:" "});
 		$("#rpeDireccion").alphanumeric({allow:" "});
 		$("#rpeTelefono").numeric();
 		$("#rcoReferencia").alphanumeric({allow:" "});
 		$("#rcoSucursal").alphanumeric({allow:" "});
 		$("#rcoMonto").numeric({allow:"."});
 		$("#rcoConcepto").alphanumeric({allow:" "});
 		$("#ascCodigo").alphanumeric();
 		$("#codEmp").alphanumeric();
		$("#primerNombre").alpha();
		$("#segundoNombre").alpha();
		$("#primerApellido").alpha();
		$("#segundoApellido").alpha();
		
		//validaciones para el formulario de busqueda del asociado
		$("#ascCodigof").alphanumeric();
		$("#primerNombref").alpha();
		$("#segundoNombref").alpha();
		$("#primerApellidof").alpha();
		$("#codEmpf").alphanumeric();
		$("#segundoApellidof").alpha();
		//validaciones para el formulario del fiador
		$("#pxtPrimerApellido").alpha();
		$("#pxtTelefonoCasa").numeric();
		$("#pxtTelefonoOficina").numeric();
		$("#pxtJefeInmediato").alpha({allow:" "});
		$("#pxtSalario").numeric({allow:"."});
		$("#pxtSegundoApellido").alpha();
		$("#pxtNombres").alpha({allow:" "});
		$("#pxtDireccion").alphanumeric({allow:" "});
		$("#pxtTrabajo").alpha({allow:" "});
		$("#pxtTelefonoOficina").alphanumeric();
		$("#pxtEmail").alphanumeric({allow:"._-@"});
		//inhabilitamos los campos en la busqueda del fiador
			
 		$("#ascCodigof").attr("disabled","disabled");
 		$("#codEmpf").attr("disabled","disabled");
 		$("#duif").attr("disabled","disabled");
 		$("#nitf").attr("disabled","disabled");;
		$("#primerNombref").attr("disabled","disabled");
		$("#segundoNombref").attr("disabled","disabled");
		$("#primerApellidof").attr("disabled","disabled");
		$("#segundoApellidof").attr("disabled","disabled");
		$("#buscarf").attr("disabled","disabled");
		//inhabilitamos los campos del formulario de los fiadores			
       	$("#pxtPrimerApellido").attr("disabled","disabled");
       	$("#pxtTelefonoCasa").attr("disabled","disabled");
       	$("#pxtTelefonoOficina").attr("disabled","disabled");
       	$("#pxtJefeInmediato").attr("disabled","disabled");
       	$("#pxtSalario").attr("disabled","disabled");
       	$("#pxtSegundoApellido").attr("disabled","disabled");
       	$("#pxtNombres").attr("disabled","disabled");
       	$("#pxtDireccion").attr("disabled","disabled");
       	$("#pxtTrabajo").attr("disabled","disabled");
       	$("#pxtTelefonoOficina").attr("disabled","disabled");
       	$("#pxtDui").attr("disabled","disabled");
       	$("#pxtCodigoEmpleado").attr("disabled","disabled");
       	$("#pxtEmail").attr("disabled","disabled");
       	$("#asociado")[0].checked=false;
        $("#externo")[0].checked=false;
        $("#asociado").val("");
        $("#externo").val("");
		
		$("#asociado").click(function() {//si se selecciona que el beneficiario es del tipo asociado
 			//habilitamos todos los campos de busqueda del asociado
 			$("#ascCodigof").removeAttr("disabled");
 			$("#codEmpf").removeAttr("disabled");
 			$("#duif").removeAttr("disabled");
 			$("#nitf").removeAttr("disabled");
			$("#primerNombref").removeAttr("disabled");
			$("#segundoNombref").removeAttr("disabled");
			$("#primerApellidof").removeAttr("disabled");
			$("#segundoApellidof").removeAttr("disabled");
			$("#buscarf").removeAttr("disabled");
 			//inhabilitamos los campos del formulario de los fiadores			
       		$("#pxtPrimerApellido").attr("disabled","disabled");
       		$("#pxtTelefonoCasa").attr("disabled","disabled");
       		$("#pxtTelefonoOficina").attr("disabled","disabled");
       		$("#pxtJefeInmediato").attr("disabled","disabled");
       		$("#pxtSalario").attr("disabled","disabled");
       		$("#pxtSegundoApellido").attr("disabled","disabled");
       		$("#pxtNombres").attr("disabled","disabled");
       		$("#pxtDireccion").attr("disabled","disabled");
       		$("#pxtTrabajo").attr("disabled","disabled");
       		$("#pxtTelefonoOficina").attr("disabled","disabled");
       		$("#pxtDui").attr("disabled","disabled");
       		$("#pxtCodigoEmpleado").attr("disabled","disabled");
       		$("#pxtEmail").attr("disabled","disabled");
       		$("#fiador").val("asociado");
       		});	
		
		$("#externo").click(function() {//si se selecciona que el beneficiario es del tipo externo
 			//inhabilitamos todos los campos de busqueda del asociado
 			$("#ascCodigof").attr("disabled","disabled");
 			$("#codEmpf").attr("disabled","disabled");
 			$("#duif").attr("disabled","disabled");
 			$("#nitf").attr("disabled","disabled");;
			$("#primerNombref").attr("disabled","disabled");
			$("#segundoNombref").attr("disabled","disabled");
			$("#primerApellidof").attr("disabled","disabled");
			$("#segundoApellidof").attr("disabled","disabled");
			$("#buscarf").attr("disabled","disabled");
 			//habilitamos los campos del formulario de los fiadores			
       		$("#pxtPrimerApellido").removeAttr("disabled");
       		$("#pxtTelefonoCasa").removeAttr("disabled");
       		$("#pxtTelefonoOficina").removeAttr("disabled");
       		$("#pxtJefeInmediato").removeAttr("disabled");
       		$("#pxtSalario").removeAttr("disabled");
       		$("#pxtSegundoApellido").removeAttr("disabled");
       		$("#pxtNombres").removeAttr("disabled");
       		$("#pxtDireccion").removeAttr("disabled");
       		$("#pxtTrabajo").removeAttr("disabled");
       		$("#pxtTelefonoOficina").removeAttr("disabled");
       		$("#pxtDui").removeAttr("disabled");
       		$("#pxtCodigoEmpleado").removeAttr("disabled");
       		$("#pxtEmail").removeAttr("disabled");
       		$("#pageId").val("3");
       		$("#fiador").val("externo");
       		$('#resultadoCli2').hide('slow');
		});
		
	});
   
//-->
	function guardaPlazo(valor){
		$('#plmId').val(valor);
	};
			
	function saveSeleccion(valor) {
		valores = valor.split(';');
		$('#ascCodigo').val(valores[0]);
		$('#nit').val(valores[1]);
		$('#dui').val(valores[2]);
		$('#primerNombre').val(valores[3]);
		$('#segundoNombre').val(valores[4]);
		$('#primerApellido').val(valores[5]);
		$('#segundoApellido').val(valores[6]);
		$('#ascId').val(valores[7]);
		$('#codEmp').val(valores[8]);
		$('#salario').val(valores[9]);
		ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do','accion=cargarListaCuentas&ascId='+$('#ascId').val(),'cuentas');
		$('#resultadoCli').hide('slow');
	} 
	
	function saveSeleccion2(valor) {
		valores = valor.split(';');
		$('#ascCodigof').val(valores[0]);
		$('#nitf').val(valores[1]);
		$('#duif').val(valores[2]);
		$('#primerNombref').val(valores[3]);
		$('#segundoNombref').val(valores[4]);
		$('#primerApellidof').val(valores[5]);
		$('#segundoApellidof').val(valores[6]);
		$('#ascIdf').val(valores[7]);
		$('#codEmpf').val(valores[8]);
		$('#resultadoCli2').hide('slow');
	} 
	//Realizar la suma
	//preSaldoActualT, nuevoMontoSolicitado, montoRecibir
	function calculaMontoARecibir(){
		var solicitado = $("#preMontoSolicitado").val();
		var otrasDeducciones = $("#otrasDeducId").val();
		var aportaciones = $("#aportacionesId").val();
		var iva = $("#ivaId").val();
		solicitado = dosDecimales(solicitado, "preMontoSolicitado");
		otrasDeducciones = dosDecimales(otrasDeducciones, "otrasDeducId");
		aportaciones = dosDecimales(aportaciones, "aportacionesId");
		iva = dosDecimales(iva, "ivaId");
		var montoARecibir = solicitado - otrasDeducciones - aportaciones - iva;
		$("#montoRecibir").val(round_number(montoARecibir,2));		
	}
	function calculaIva(){
		var ivap=${iva}/100;
		var otrasDeducciones = $("#otrasDeducId").val();
		otrasDeducciones = dosDecimales(otrasDeducciones, "otrasDeducId");
		var iva = otrasDeducciones*ivap;
		$("#ivaId").val(round_number(iva,2));		
	}
	
	function generarComprobante(idAsociado,salario,idPrestamo){
		$("#asociadoId").val(idAsociado);
 		$("#ascSalario").val(salario);
 		$("#prestamoId").val(idPrestamo);
 		$('#accionId').val('generarDescuentos');
 		$('#PreId')[0].submit();
	}
	function finalizar(idPrestamo){
 		$("#prestamoId").val(idPrestamo);
	}

</script>

<html:form action="${_accion}" method="post" styleId="formId">
	<div class="stepByStepWizard" title="Informacion General">
		<script>
      		var patternDui = new Array(${tamDui}-1,1);
      </script>
		<div id="ascDiv">
			<table border="0" align="center">
				<tr>
					<td>
						<label>
							<bean:message key="lbl.asc.ascCodigo" />
						</label>
					</td>
					<td>
						<html:text property="ascCodigoAsociado" styleClass="obligatorio"
							 styleId="ascCodigo" />
					</td>
					<td>
						<label>
							<bean:message key="lbl.asc.ascCodigoTrabajo" />
						</label>
					</td>
					<td>
						<html:text property="ascCodigo" styleClass="obligatorio"
							 styleId="codEmp" />
					</td>
				</tr>
				<tr>
					<td>
						<label>
							<bean:message key="lbl.asc.ascDui" />
						</label>
					</td>
					<td>
						<html:text property="secPerPersona.perDui"
							styleClass="obligatorio" size="${tamDui}"
							onkeyup="maskDui(this);" 
							styleId="dui" />
					</td>
					<td>
						<label>
							<bean:message key="lbl.asc.ascNit" />
						</label>
					</td>
					<td>
						<html:text property="secPerPersona.perNit"
							styleClass="obligatorio" onkeyup="maskNit(this);"
							 styleId="nit" />
					</td>
				</tr>
				<tr>
					<td>
						<label>
							<bean:message key="lbl.asc.ascPrimerNombre" />
						</label>
					</td>
					<td>
						<html:text property="secPerPersona.perPrimerNombre"
							styleClass="obligatorio"
							styleId="primerNombre" />
					</td>
					<td>
						<label>
							<bean:message key="lbl.asc.ascSegundoNombre" />
						</label>
					</td>
					<td>
						<html:text property="secPerPersona.perSegundoNombre"
							styleClass="obligatorio"
							styleId="segundoNombre" />
					</td>
				</tr>
				<tr>
					<td>
						<label>
							<bean:message key="lbl.asc.ascPrimerApellido" />
						</label>
					</td>
					<td>
						<html:text property="secPerPersona.perPrimerApellido"
							styleClass="obligatorio"
							styleId="primerApellido" />
					</td>
					<td>
						<label>
							<bean:message key="lbl.asc.ascSegundoApellido" />
						</label>
					</td>
					<td>
						<html:text property="secPerPersona.perSegundoApellido"
							styleClass="obligatorio"
							styleId="segundoApellido" />
					</td>

				</tr>
				<tr>
					<td>
						<label>
							<bean:message key="lbl.asc.salario" />
						</label>
					</td>
					<td>
						<html:text property="salario"
							styleClass="obligatorio"
							styleId="salario" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Buscar"
							onclick="ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do','accion=cargarListaAsociados&secPerPersona.PerNit='+$('#nit').val()+'&ascCodigoAsociado='+$('#ascCodigo').val()+'&secPerPersona.perDui='+$('#dui').val()+'&secPerPersona.perPrimerNombre='+$('#primerNombre').val()+'&secPerPersona.perSegundoNombre='+$('#segundoNombre').val()+'&secPerPersona.perPrimerApellido='+$('#primerApellido').val()+'&secPerPersona.perSegundoApellido='+$('#segundoApellido').val()+'&ascCodigo='+$('#codEmp').val(),'ascList')" />
					</td>
					<td>
						<input type="button" value="Limpiar"
							onclick="cleanFields('ascDiv');$('#ascId').val('');">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div id="ascList">
						</div>
					</td>
				</tr>
			</table>
		</div>
		<table align="center">
			<!--<tr>
				<td colspan="2">
					<label>
						<bean:message key="lbl.pre.formaDesembolso" />
						:
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<html:radio property="fuente" value="true" styleId="cheque"
						styleClass="obligatorio">
						<label>
							<bean:message key="lbl.pre.cheque" />
						</label>
					</html:radio>
				</td>
				<td>
					<html:radio property="fuente" value="false" styleId="cuenta"
						styleClass="obligatorio">
						<label>
							<bean:message key="lbl.pre.cuenta" />
						</label>
					</html:radio>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pre.cuentaADepositar" />
					</label>
				</td>
				<td>
					<div id="cuentas" >
						<logic:notEmpty name="cuentas">
							<html:select property="casCuenta" styleId="casCuenta" 
								styleClass="obligatorio">
								<html:optionsCollection label="nombreCuenta" name="cuentas"
									value="id" />
							</html:select>
						</logic:notEmpty>
						<logic:empty name="cuentas">
							<bean:message key="lbl.pre.noHayCuentas" />
						</logic:empty>
					</div>
				</td>
			</tr>-->
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pre.lineaPrestamo" />
					</label>
				</td>
				<td>
					<select class="obligatorio" id="lineaCombo" name="lprId">
						<logic:notEmpty name="listaLineas">
							<logic:iterate id="linea" name="listaLineas">
								<option value="<bean:write  name="linea" property="lprId"/>"><bean:write  name="linea" property="lprNombre"/></option>
							</logic:iterate>
						</logic:notEmpty>
					</select>
				</td>
			</tr>				
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pre.plazoSolicitado" />
					</label>
				</td>
				<td>
					<div id="plazoDiv">
						<html:select property="plmId" styleId="plmId"
							styleClass="obligatorio">
							<html:optionsCollection name="listaPlanes" value="plmId" label="plmId"/>
						</html:select>					
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pre.tasaInteres" />
					</label>
				</td>
				<td>
					<div id="tasaInteresDiv">
						<html:select property="tinPlazoId" styleId="tinPlazoId"
							styleClass="obligatorio">
							<html:optionsCollection label="tinNombre" name="listaTasas"
								value="tinId" />							
						</html:select>					
					</div>
				</td>
			</tr>			
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pre.montoSolicitado" />
					</label>
					:
				</td>
				<td>
					<html:text styleClass="obligatorio" maxlength="50" size="10"
						property="preMontoSolicitado" styleId="preMontoSolicitado" onkeyup="dosDecimales($('#preMontoSolicitado').val(),'preMontoSolicitado');calculaMontoARecibir();"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pre.aportaciones" />
					</label>
					:
				</td>
				<td>
					<html:text styleClass="obligatorio" maxlength="50" size="10"
						property="aportaciones" styleId="aportacionesId" 
						onkeyup="calculaMontoARecibir();"/>						
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label>
						<bean:message key="lbl.pre.otrasDeducciones" />
					</label>					
					:
					<html:text styleClass="obligatorio" maxlength="50" size="10"
						property="preOtrasDeducciones" styleId="otrasDeducId" 
						onkeyup="calculaIva();calculaMontoARecibir();"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pre.montoRecibir" />
					</label>
					:
				</td>
				<td>
					<html:text styleClass="obligatorio" maxlength="50" size="10"
						property="montoRecibir" styleId="montoRecibir" readonly="true" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label>
						<bean:message key="lbl.pre.iva" />
					</label>
					:
					<html:text styleClass="obligatorio" maxlength="50" size="10"
						property="preIvaDeducciones" styleId="ivaId" readonly="true"/>						
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pre.tasaInteresMora" />
					</label>
					:
				</td>
				<td>
					<html:select property="ctaTinTasaInteres.tinId" styleId="tinId"
						styleClass="obligatorio">
						<html:optionsCollection label="tinNombre" name="tinList"
							value="tinId" />
					</html:select>
				</td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td>
					<html:submit property="accion">
						<bean:message key="cmd.prestamo.cancelar" />
					</html:submit>
				</td>
			</tr>
		</table>
	</div>
	<html:hidden property="ascId" styleId="ascId" />	
</html:form>
<html:form action="/referenciaPersonal" method="post" styleId="rpeForm">
	<div class="stepByStepWizard" title="Referencias Personales"
		id="rpeDiv">
		<table align="center">
			<tr>
				<td>
					<label>
						<bean:message key="lbl.rpe.nombres" />
					</label>
					:
				</td>
				<td>
					<html:text maxlength="100" size="20" property="rpeNombres"
						styleClass="obligatorio" styleId="rpeNombres" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.rpe.apellidos" />
					</label>
					:
				</td>
				<td>
					<html:text maxlength="100" size="20" property="rpeApellidos"
						styleClass="obligatorio" styleId="rpeApellidos" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.rpe.parentesco" />
					</label>
					:
				</td>
				<td>
					<html:select property="parId" styleClass="obligatorio"
						styleId="parId">
						<html:optionsCollection name="parList" label="parDescripcion"
							value="parId" />
					</html:select>
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.rpe.direccion" />
					</label>
					:
				</td>
				<td>
					<html:textarea property="rpeDireccion" styleClass="obligatorio"
						styleId="rpeDireccion" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.rpe.telefonos" />
					</label>
					:
				</td>
				<td>
					<html:text maxlength="15" size="20" property="rpeTelefono"
						styleClass="obligatorio" styleId="rpeTelefono" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td>
					<input type="button" value="Guardar"
						onclick="ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente/referenciaPersonal.do','accion=guardarRefPersonal&rpeNombres='+$('#rpeNombres').val()+'&rpeApellidos='+$('#rpeApellidos').val()+'&parId='+$('#parId').val()+'&rpeDireccion='+$('#rpeDireccion').val()+'&rpeTelefono='+$('#rpeTelefono').val(),'listaReferenciasPersonales')" />
				</td>
				<td>
					<input type="button" value="Eliminar" id="eliminarRpe" />
				</td>
				<td>
				<td>
					<input type="button" value="Limpiar"
						onclick="cleanFields('rpeDiv');">
				</td>
				<td>
					<html:submit property="accion">
						<bean:message key="cmd.rpe.cancelar" />
					</html:submit>
				</td>
			</tr>
		</table>
		<div id="listaReferenciasPersonales">
			<div class="scrollPane">
				<table summary="" class="tableone">
					<caption>
						<bean:message key="lbl.rpeTbl.titulo" />
					</caption>
					<thead>
						<tr>
							<th scope="col" class="th1">
								  &nbsp;&nbsp;
							</th>
							<th scope="col" class="th1">
								<bean:message key="lbl.rpe.nombres" />
							</th>
							<th scope="col" class="th1">
								<bean:message key="lbl.rpe.apellidos" />
							</th>
							<th scope="col" class="th1">
								<bean:message key="lbl.rpe.parentesco" />
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5">
								<div class="innerb">
									<table class="tabletwo">
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</html:form>
<html:form action="/referenciaComercial" method="post" styleId="rcoForm">
	<div class="stepByStepWizard" title="Referencias Comerciales"
		id="rcoDiv">

		<table align="center">
			<tr>
				<td>
					<label>
						<bean:message key="lbl.rco.nombreEmpresa" />
					</label>
					:
				</td>
				<td>
					<html:text maxlength="20" size="20" property="rcoReferencia"
						styleClass="obligatorio" styleId="rcoReferencia" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.rco.sucursal" />
					</label>
					:
				</td>
				<td>
					<html:text maxlength="25" size="20" property="rcoSucursal"
						styleClass="obligatorio" styleId="rcoSucursal" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.rco.monto" />
					</label>
					:
				</td>
				<td>
					<html:text maxlength="20" size="20" property="rcoMonto"
						styleClass="obligatorio" styleId="rcoMonto" onkeyup="dosDecimales($('#rcoMonto').val(),'rcoMonto');"/>
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.rco.concepto" />
					</label>
					:
				</td>
				<td>
					<html:textarea property="rcoConcepto" styleClass="obligatorio"
						styleId="rcoConcepto" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td>
					<input type="button" value="Guardar"
						onclick="ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente/referenciaComercial.do','accion=guardarRefComercial&rcoReferencia='+$('#rcoReferencia').val()+'&rcoSucursal='+$('#rcoSucursal').val()+'&rcoMonto='+$('#rcoMonto').val()+'&rcoEstado='+$('#rcoEstado').val()+'&rcoConcepto='+$('#rcoConcepto').val(),'listaReferenciasComerciales')" />
				</td>
				<td>
					<input type="button" value="Eliminar" id="eliminarRco" />
				</td>
				<td>
					<input type="button" value="Limpiar"
						onclick="cleanFields('rcoDiv');">
				</td>
				<td>
					<html:submit property="accion">
						<bean:message key="cmd.rco.cancelar" />
					</html:submit>
				</td>
			</tr>
		</table>
		<div id="listaReferenciasComerciales">
			<div class="scrollPane">
				<table summary="" class="tableone">
					<caption>
						<bean:message key="lbl.rcoTbl.titulo" />
					</caption>
					<thead>
						<tr>
							<th scope="col" class="th1">
								 &nbsp;&nbsp;
							</th>
							<th scope="col" class="th1">
								<bean:message key="lbl.rco.nombreEmpresa" />
							</th>
							<th scope="col" class="th1">
								<bean:message key="lbl.rco.sucursal" />
							</th>
							<th scope="col" class="th1">
								<bean:message key="lbl.rco.monto" />
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5">
								<div class="innerb">
									<table class="tabletwo">
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</html:form>
<html:form action="/fiadorPrestamo" method="post" styleId="fxpForm">
	<div class="stepByStepWizard" title="Fiadores" id="fxpDiv">
		<table border="0" align="center">
		<tr>
		<td>
			<html:radio property="fiador" value="asociado" styleId="asociado">
				<label><bean:message key="lbl.fxp.asociado" /></label>
			</html:radio>
			<html:radio property="fiador" value="externo" styleId="externo">
				<label><bean:message key="lbl.fxp.personaExterna" /></label>
			</html:radio>
			</td>
			</tr>
		</table>
		<table border="0" align="center">
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascCodigo" />
					</label>
				</td>
				<td>
					<html:text property="ctaAscAsociado.ascCodigoAsociado"
						styleClass="obligatorio" styleId="ascCodigof" />
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascCodigoTrabajo" />
					</label>
				</td>
				<td>
					<html:text property="ctaAscAsociado.ascCodigo"
						styleClass="obligatorio" styleId="codEmpf" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascDui" />
					</label>
				</td>
				<td>
					<html:text property="ctaAscAsociado.secPerPersona.perDui"
						styleClass="obligatorio" size="${tamDui}" onkeyup="maskDui(this);"
						styleId="duif" />
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascNit" />
					</label>
				</td>
				<td>
					<html:text property="ctaAscAsociado.secPerPersona.perNit"
						styleClass="obligatorio" onkeyup="maskNit(this);" styleId="nitf" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascPrimerNombre" />
					</label>
				</td>
				<td>
					<html:text property="ctaAscAsociado.secPerPersona.perPrimerNombre"
						styleClass="obligatorio" styleId="primerNombref" />
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascSegundoNombre" />
					</label>
				</td>
				<td>
					<html:text property="ctaAscAsociado.secPerPersona.perSegundoNombre"
						styleClass="obligatorio" styleId="segundoNombref" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascPrimerApellido" />
					</label>
				</td>
				<td>
					<html:text
						property="ctaAscAsociado.secPerPersona.perPrimerApellido"
						styleClass="obligatorio" styleId="primerApellidof" />
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascSegundoApellido" />
					</label>
				</td>
				<td>
					<html:text
						property="ctaAscAsociado.secPerPersona.perSegundoApellido"
						styleClass="obligatorio" styleId="segundoApellidof" />
				</td>

			</tr>
			<tr>
				<td>
					<input type="button" value="Buscar"
						onclick="ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente/fiadorPrestamo.do','accion=cargarListaAsociados&ctaAscAsociado.secPerPersona.PerNit='+$('#nitf').val()+'&ctaAscAsociado.ascCodigoAsociado='+$('#ascCodigof').val()+'&ctaAscAsociado.secPerPersona.perDui='+$('#duif').val()+'&ctaAscAsociado.secPerPersona.perPrimerNombre='+$('#primerNombref').val()+'&ctaAscAsociado.secPerPersona.perSegundoNombre='+$('#segundoNombref').val()+'&secPerPersona.perPrimerApellido='+$('#primerApellidof').val()+'&ctaAscAsociado.secPerPersona.perSegundoApellido='+$('#segundoApellidof').val()+'&ctaAscAsociado.ascCodigo='+$('#codEmpf').val()+'&ascId='+$('#ascId').val(),'asocList');" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="asocList">
					</div>
				</td>
			</tr>
			<!-- tabla de los campos del fiador. -->
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pxt.dui" />
						:
					</label>
				</td>
				<td>
					<html:text maxlength="15" size="18"
						property="ctaPxtPersonaExterna.pxtDui" styleClass="obligatorio"
						styleId="pxtDui" onkeyup="maskDui(this);" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
				<td>
					<label>
						<bean:message key="lbl.pxt.nombres" />
						:
					</label>
				</td>
				<td>
					<html:text maxlength="100" size="18"
						property="ctaPxtPersonaExterna.pxtNombres"
						styleClass="obligatorio" styleId="pxtNombres" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pxt.primerApellido" />
						:
					</label>
				</td>
				<td>
					<html:text maxlength="50" size="18"
						property="ctaPxtPersonaExterna.pxtPrimerApellido"
						styleClass="obligatorio" styleId="pxtPrimerApellido" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
				<td>
					<label>
						<bean:message key="lbl.pxt.segundoApellido" />
						:
					</label>
				</td>
				<td>
					<html:text maxlength="50" size="18"
						property="ctaPxtPersonaExterna.pxtSegundoApellido"
						styleClass="obligatorio" styleId="pxtSegundoApellido" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pxt.direccion" />
						:
					</label>
				</td>
				<td>
					<html:textarea property="ctaPxtPersonaExterna.pxtDireccion"
						styleClass="obligatorio" styleId="pxtDireccion" cols="16" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
				<td>
					<label>
						<bean:message key="lbl.pxt.telefonoCasa" />
						:
					</label>
				</td>
				<td>
					<html:text maxlength="15" size="18"
						property="ctaPxtPersonaExterna.pxtTelefonoCasa"
						styleClass="obligatorio" styleId="pxtTelefonoCasa" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pxt.trabajo" />
						:
					</label>
				</td>
				<td>
					<html:text maxlength="30" size="18"
						property="ctaPxtPersonaExterna.pxtTrabajo"
						styleClass="obligatorio" styleId="pxtTrabajo" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
				<td>
					<label>
						<bean:message key="lbl.pxt.pxtCodigoEmpleado" />:
					</label>
				</td>
				<td>
					<html:text maxlength="50" size="18" property="ctaPxtPersonaExterna.pxtCodigoEmpleado"
						styleClass="obligatorio" styleId="pxtCodigoEmpleado" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pxt.jefeInmediato" />:
					</label>
				</td>
				<td>
					<html:text maxlength="100" size="18" 
						property="ctaPxtPersonaExterna.pxtJefeInmediato"
						styleClass="obligatorio" 
						styleId="pxtJefeInmediato" />
				</td>
				<td>
					<label>
						<bean:message key="lbl.pxt.pxtEmail" />:
					</label>
				</td>
				<td>
					<html:text maxlength="100" size="18" 
						property="ctaPxtPersonaExterna.pxtEmail"
						styleClass="obligatorio" 
						styleId="pxtEmail" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pxt.salario" />
						:
					</label>
				</td>
				<td>
					<html:text maxlength="30" size="18"
						property="ctaPxtPersonaExterna.pxtSalario"
						styleClass="obligatorio" styleId="pxtSalario" onkeyup="dosDecimales($('#pxtSalario').val(),'pxtSalario');"/>
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
				<td>
					<label>
						<bean:message key="lbl.pxt.telefonoOficina" />
						:
					</label>
				</td>
				<td>
					<html:text maxlength="15" size="18"
						property="ctaPxtPersonaExterna.pxtTelefonoOficina"
						styleClass="obligatorio" styleId="pxtTelefonoOficina" />
				</td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td>
					<input type="button" value="Guardar"
						onclick="ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente/fiadorPrestamo.do',
							'accion=guardar&ctaPxtPersonaExterna.pxtDui='+$('#pxtDui').val()+
							'&ctaPxtPersonaExterna.pxtNombres='+$('#pxtNombres').val()+
							'&ctaPxtPersonaExterna.pxtPrimerApellido='+$('#pxtPrimerApellido').val()+
							'&ctaPxtPersonaExterna.pxtSegundoApellido='+$('#pxtSegundoApellido').val()+
							'&ctaPxtPersonaExterna.pxtDireccion='+$('#pxtDireccion').val()+
							'&ctaPxtPersonaExterna.pxtTelefonoCasa='+$('#pxtTelefonoCasa').val()+
							'&ctaPxtPersonaExterna.pxtTrabajo='+$('#pxtTrabajo').val()+
							'&ctaPxtPersonaExterna.pxtCodigoEmpleado='+$('#pxtCodigoEmpleado').val()+
							'&ctaPxtPersonaExterna.pxtJefeInmediato='+$('#pxtJefeInmediato').val()+
							'&ctaPxtPersonaExterna.pxtEmail='+$('#pxtEmail').val()+
							'&ctaPxtPersonaExterna.pxtSalario='+$('#pxtSalario').val()+
							'&ctaPxtPersonaExterna.pxtTelefonoOficina='+$('#pxtTelefonoOficina').val()+
							'&fiador='+$('#fiador').val()+'&ctaAscAsociado.ascId='+$('#ascIdf').val()+
							'&fiador='+$('#fiador').val(),'listaFiadores')" />
				</td>
				<td>
					<input type="button" value="Eliminar" id="eliminarFxp" />
				</td>
				<td>
					<input type="button" value="Limpiar"
						onclick="cleanFields('fxpDiv');$('#ascIdf').val('');">
				</td>
				<td>
					<html:submit property="accion">
						<bean:message key="cmd.fxp.cancelar" />
					</html:submit>
				</td>
			</tr>
		</table>
		<div id="listaFiadores">
			<div class="scrollPane">
				<table summary="" class="tableone">
					<caption>
						<bean:message key="lbl.tbl.fiadores" />
					</caption>
					<thead>
						<tr>
							<th scope="col" class="th1">
								 &nbsp;&nbsp;
							</th>
							<th scope="col" class="th1">
								<bean:message key="lbl.fxp.Nombre" />
							</th>
							<th scope="col" class="th1">
								<bean:message key="lbl.fxp.salario" />
							</th>
							<th scope="col" class="th1">
								<bean:message key="lbl.fxp.tipoFiador" />
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5">
								<div class="innerb">
									<table class="tabletwo">
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<html:hidden property="fiador" styleId="fiador" />
	<html:hidden property="ctaAscAsociado.ascId" styleId="ascIdf" />
</html:form>
<html:form action="/garantiaPrestamo" method="post" styleId="garForm">
	<div class="stepByStepWizard" title="Garantias">
		<table align="center">
			<tr>
				<td>
					<label>
						<bean:message key="lbl.gar.nombreGarantia" />
						:
					</label>
				</td>
				<td>
					<html:text maxlength="50" size="15" property="garNombreInmueble"
						styleClass="obligatorio" styleId="garNombreInmueble" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.gar.tipoGarantia" />
					</label>
				</td>
				<td>
					<html:select property="ctaTgaTipoGarantia.tgaId"
						styleId="tipoGarantia" styleClass="obligatorio">
						<html:optionsCollection label="tgaNombre" name="tipoGarantias"
							value="tgaId" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.gar.valorGarantia" />
						:
					</label>
				</td>
				<td>
					<html:text property="garValor" styleClass="obligatorio"
						maxlength="50" size="15" styleId="garValor" onkeyup="dosDecimales($('#garValor').val(),'garValor');"/>
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.gar.descripcionGarantia" />
						:
					</label>
				</td>
				<td>
					<html:textarea property="garDescripcionInmueble"
						styleClass="obligatorio" styleId="garDescripcionInmueble" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.gar.ubicacionGarantia" />
						:
					</label>
				</td>
				<td>
					<html:textarea property="garUbicacion" styleClass="obligatorio"
						styleId="garUbicacion" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.gar.inspeccionadaGarantia" />
						:
					</label>
				</td>
				<td>
					<html:select property="garInspeccion" styleClass="obligatorio"
						styleId="garInspeccion">
						<html:option value="S">
							<bean:message key="lbl.gar.si" />
						</html:option>
						<html:option value="N">
							<bean:message key="lbl.gar.no" />
						</html:option>
					</html:select>
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td>
					<input type="button" value="Guardar"
						onclick="ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente/garantiaPrestamo.do','accion=guardar&garNombreInmueble='+$('#garNombreInmueble').val()+'&ctaTgaTipoGarantia.tgaId='+$('#tipoGarantia').val()+'&garValor='+$('#garValor').val()+'&garDescripcionInmueble='+$('#garDescripcionInmueble').val()+'&garUbicacion='+$('#garUbicacion').val()+'&garInspeccion='+$('#garInspeccion').val(),'listaGarantias')" />
				</td>
				<td>
					<input type="button" value="Eliminar" id="eliminarGxp" />
				</td>
				<td>
					<input type="button" value="Limpiar"
						onclick="cleanFields('garForm');">
				</td>
				<td>
					<html:submit property="accion">
						<bean:message key="cmd.gar.cancelar" />
					</html:submit>
				</td>
			</tr>
		</table>
		<div id="listaGarantias">
			<div class="scrollPane">
				<table summary="" class="tableone">
					<caption>
						<bean:message key="lbl.garTbl.titulo" />
					</caption>
					<thead>
						<tr>
							<th scope="col" class="th1">
								 &nbsp;&nbsp;
							</th>
							<th scope="col" class="th1">
								<bean:message key="lbl.gar.nombreGarantia" />
							</th>
							<th scope="col" class="th1">
								<bean:message key="lbl.gar.valorGarantia" />
							</th>
							<th scope="col" class="th1">
								<bean:message key="lbl.gar.inspeccionadaGarantia" />
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5">
								<div class="innerb">
									<table class="tabletwo">
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</html:form>
<html:form action="/descuentosExternos" method="post" styleId="dxpForm">
	<div class="stepByStepWizard" title="Descuentos Externos">
		<table align="center">
			<tr>
				<td>
					<label>
						<bean:message key="lbl.descuentosExternos.dexNombreDescuento" />
					</label>
				</td>
				<td>
					<html:text property="dexNombreDescuento" maxlength="100" size="30" styleClass="obligatorio" styleId="dexNombreDescuento"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.descuentosExternos.dexMonto" />
					</label>
				</td>
				<td>
					<html:text property="dexMonto" maxlength="13" size="13" styleClass="obligatorio" styleId="dexMonto" onkeyup="dosDecimales($('#dexMonto').val(),'dexMonto');"/>
				</td>
			</tr>
			</table>
			<table align="center">
			<tr>
				<td>
					<input type="button" value="Guardar"
						onclick="ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente/descuentosExternos.do','accion=guardar&dexNombreDescuento='+$('#dexNombreDescuento').val()+'&dexMonto='+$('#dexMonto').val(),'listaDescuentos')" />
				</td>
				<td>
					<input type="button" value="Eliminar" id="eliminarDxp" />
				</td>
				<td>
					<input type="button" value="Limpiar"  	onclick="cleanFields('dxpForm');">
				</td>
				<td>
					<html:submit property="accion">
						<bean:message key="cmd.prestamo.cancelar" />
					</html:submit>
				</td>
			</tr>
		</table>
				<div id="listaDescuentos">
			<div class="scrollPane">
				<table summary="" class="tableone">
					<caption>
						<bean:message key="lbl.dexTbl.titulo" />
					</caption>
					<thead>
						<tr>
							<th scope="col" class="th1">
								 &nbsp;&nbsp;
							</th>
							<th scope="col" class="th1">
								<bean:message key="lbl.descuentosExternos.dexMonto" />
							</th>
							<th scope="col" class="th1">
								<bean:message key="lbl.descuentosExternos.dexNombreDescuento" />
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5">
								<div class="innerb">
									<table class="tabletwo">
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</html:form>
<html:form action="/nota" method="post" styleId="notForm">
	<div class="stepByStepWizard" title="Anotaciones">
		<table align="center">
			<tr>
				<td><label>
					<bean:message key="lbl.pre.nota" /></label>
				</td>
				<td>
					<html:textarea property="notNota" styleClass="obligatorio"
						styleId="nota" />
				</td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td>
					<input type="button" value="Guardar"
						onclick="ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente/nota.do','accion=guardarNota&notNota='+$('#nota').val(),'listaAnotaciones')" />
				</td>
				<td>
					<input type="button" value="Eliminar" id="eliminarNotas" />
				</td>
				<td>
					<input type="button" value="Limpiar"
						onclick="cleanFields('notForm');">
				</td>
				<td>
					<html:submit property="accion">
						<bean:message key="cmd.notPre.cancelar" />
					</html:submit>
				</td>
			</tr>
		</table>
		<div id="listaAnotaciones">
			<div class="scrollPane">
				<table summary="" class="tableone">
					<caption>
						<bean:message key="lbl.notPreTbl.titulo" />
					</caption>
					<thead>
						<tr>
							<th scope="col" class="th1">
								 &nbsp;&nbsp;
							</th>
							<th scope="col" class="th1">
								<bean:message key="lbl.pre.nota" />
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="2">
								<div class="innerb">
									<table class="tabletwo">
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</html:form>
<html:form action="${_accion}" method="post" styleId="PreId">
<div class="stepByStepWizard" title="Verificacion de Informacion">
		<div id="respuesta">
			<table align="center">
				<tr>
					<td>
						<input type="button" value="Verificar Informacion"
							onclick="ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do',
								'accion=forwardToVerificacion&ascId='+$('#ascId').val()+
								'&fuente='+$('#fuente').val()+
								'&lprId='+$('#lineaCombo').val()+
								'&plmId='+$('#plmId').val()+
								'&tinPlazoId='+$('#tinPlazoId').val()+
								'&preMontoSolicitado='+$('#preMontoSolicitado').val()+
								'&casCuenta='+$('#casCuenta').val()+
								'&ctaTinTasaInteres.tinId='+$('#tinId').val()+
								'&montoRecibir='+$('#montoRecibir').val()+
								'&aportaciones='+$('#aportacionesId').val()+
								'&preOtrasDeducciones='+$('#otrasDeducId').val()+
								'&preIvaDeducciones='+$('#ivaId').val(),'respuesta')" />
					</td>
				</tr>
			</table>
		</div>
	</div>
<html:hidden property="ascId" styleId="asociadoId"/>
<html:hidden property="ascSalario" styleId="ascSalario"/>
<html:hidden property="preId" styleId="prestamoId"/>
<html:hidden property="estado" value="14"/>
<html:hidden property="hidFuente" styleId="hidFuente" value="false"/>
<input class="exclude" type="hidden" name="accion" id="accionId"/>
</html:form>

