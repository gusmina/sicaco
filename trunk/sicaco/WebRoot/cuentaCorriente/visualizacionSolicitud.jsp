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
		var showCurrentStepText_forward	= "&nbsp;&gt;&gt;&nbsp;"; 	// Text used for "Forward"
		var showCurrentStepText_back	= "&nbsp;&lt;&lt;&nbsp;"; 	// Text used for "Back"
		wizardConfig(wizardStartByStepNmbr,hideAllStepApartCurrent,showCurrentStepNmbr,showCurrentStepTitle,attrToGetStepTitle,showCurrentStepText_Title,elementToTransformToWizard,elementToAddNavi,showCurrentStepText_step,showCurrentStepText_of,showCurrentStepText_forward,showCurrentStepText_back);
  			
  		$(".msg_head").click(function(){
			$(this).next(".msg_body").slideToggle("fast");
		 });
  		
  		$("#cheque").click(function() {
 			$("#casCuenta").attr("disabled","disabled");
 			$("#fuente").val(true);
		});	
		
		$("#cuenta").click(function() {
 			$("#casCuenta").removeAttr("disabled");
 			$("#fuente").val(false);
		});
  		
  		
  			$("#tipoGarantia").attr("disabled","disabled");
		var variable = $("#estado").val();
		if(variable != 14){
			$("#id").attr("disabled","disabled");
			$("#casCuenta").attr("disabled","disabled");
			$("#cuenta").attr("disabled","disabled");
			$("#cheque").attr("disabled","disabled");
		}
  		
		});
 	
//-->
</script>

<style type="text/css">
p {
	padding: 0 0 1em;
}

div.scroll {
	height: 100px;
	width: 250px;
	overflow: auto;
	border: 1px solid #666;
	background-color: #FFFF;
	padding: 8px;
}


</style>

<html:form action="${_accion}" method="post" styleId="formId"
	readonly="true">
	<div class="stepByStepWizard" title="Informacion General">
		<script>
      		var patternDui = new Array(${tamDui}-1,1);
      </script>

		<table class="obligatorio" align="center">
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascCodigo" />
						:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="asociado" property="ascCodigoAsociado" />
					</label>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascNit" />
						:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="asociado" property="secPerPersona.perNit" />
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascDui" />
						:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="asociado" property="secPerPersona.perDui" />
					</label>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascPrimerNombre" />
						:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="asociado"
							property="secPerPersona.perPrimerNombre" />
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascSegundoNombre" />
						:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="asociado"
							property="secPerPersona.perSegundoNombre" />
					</label>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascPrimerApellido" />
						:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="asociado"
							property="secPerPersona.perPrimerApellido" />
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascSegundoApellido" />
						:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="asociado"
							property="secPerPersona.perSegundoApellido" />
					</label>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascApellidoCasada" />
						:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="asociado"
							property="secPerPersona.perApellidoCasada" />
					</label>
				</td>
			</tr>
			<!--<tr>
				<td colspan="2">
					<label>
						<bean:message key="lbl.pre.formaDesembolso" />
						:
					</label>
				</td>
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
			</tr>-->
			<tr>
				<!-- <td>
					<label>
						<bean:message key="lbl.pre.cuentaADepositar" />
					</label>
				</td>
				<td>
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
				</td> -->
				<td>
					<label>
						<bean:message key="lbl.pre.montoSolicitado" />
						:
					</label>
				</td>
				<td>
					<label style="color: black;">
						
						<bean:write name="form" property="preMontoSolicitado"
							format="'$'#,###,###.00" />
					</label>
				</td>
				<td>
					<label>
						<bean:message key="lbl.pre.referencia" />
						:
					</label>
				</td>
				<td>
					<label style="color: black;">						
						<bean:write name="form" property="preReferencia"/>
					</label>
				</td>				
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pre.tipoPrestamo" />
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="form" property="ctaTprTipoPrestamo.tprNombre" />
					</label>
				</td>
				<td>
					<label>
						<bean:message key="lbl.pre.lprNombre" />
						:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="form"
							property="ctaTprTipoPrestamo.ctaLprLineaPrestamo.lprNombre" />
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pre.plmDuracion" />
						:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="form"
							property="ctaTprTipoPrestamo.ctaPlmPlanMeses.plmDuracion" />
					</label>
				</td>
				<td>
					<label style="">
						<bean:message key="lbl.pre.tasaInteres" />:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="form"
							property="ctaTprTipoPrestamo.ctaTinTasaInteres.tinTasa"
							format="##.##" />
						&nbsp;&permil;
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pre.cuota" />:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="form" property="preCuota" format="'$'#,###,###.00" />
					</label>
				</td>
				<td>
					<label>
						<bean:message key="lbl.pre.tasaInteresMora" />:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="form" property="tasaMora"
							format="##.##" />
						&nbsp;&permil;
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.pre.cuotaPlanilla" />:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="form" property="cuotaPlanilla" format="'$'#,###,###.00" />
					</label>
				</td>
				<td>
					<label>
						<bean:message key="lbl.pre.cuotasPendientes" />:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="form" property="cuotasPendientes"  />
					</label>
				</td>
			</tr>
			<tr>
				<td>
				
					<label>
						<bean:message key="lbl.pre.vencimiento" />:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="form" property="vencimiento" />
					</label>
				</td>
			</tr>
		</table>
	</div>
	<table align="center">
		<tr>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.prestamo.verRco" />
					<%--  Link para dirijirse hacia las referencias comerciales --%>
				</html:submit>
			</td>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.prestamo.verRpe" />
					<%--  Link para dirijirse hacia las referencias personales --%>
				</html:submit>
			</td>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.prestamo.verGxp" />
					<%--  Link para dirijirse hacia las  garantias --%>
				</html:submit>
				<html:submit property="accion">
					<bean:message key="cmd.prestamo.verFxp" />
					<%--  Link para dirigirse hacia los fiadores --%>
				</html:submit>
			</td>
		</tr>
		<tr>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.prestamo.verDex" />
					<%--  Link para dirigirse hacia los descuentos externos --%>
				</html:submit>
			</td>
			<%-- - <td> 
				<html:submit property="accion">
					<bean:message key="cmd.prestamo.verDexCoop" />
					Link para dirigirse hacia los descuentos de cooperativa 
				</html:submit>  
			</td>--%>
			<td>
				<logic:present name="rol">					
					<logic:equal value="14" name="form" property="estado">
						<html:submit property="accion">
							<bean:message key="cmd.prestamo.aprobar" />
						</html:submit>
						<html:submit property="accion">
							<bean:message key="cmd.prestamo.denegar" />
						</html:submit>
					</logic:equal>
				</logic:present>
				<logic:equal value="13" name="form" property="estado">
					<logic:present name="refinanciar" scope="request">
						<html:submit property="accion">
							<bean:message key="cmd.pre.loadPrestamoExistente" />
						</html:submit>
					</logic:present>
				</logic:equal>
				<!--<logic:present name="refinanciado">
					<html:submit property="accion">
						<bean:message key="cmd.pre.chequePrestamo" />
					</html:submit>
				</logic:present>-->
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
		<td>
						<html:submit property="accion">
					<bean:message key="cmd.prestamo.verNxp" />
				</html:submit>&nbsp;
		</td>
		<td>
							<html:submit property="accion">
					<bean:message key="cmd.prestamo.cancelar" />
				</html:submit>
		<td>
		</td>
		</tr>
		</table>
	<html:hidden property="preId" />
	<html:hidden property="estado" styleId="estado"/>
</html:form>