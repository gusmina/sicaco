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
});

	$(document).ready(function(){
    	$("#tisNombreId").alphanumeric({allow:" "});
    	$("#tisPolizaId").alphanumeric({allow:" -"});
    	$("#tisDescripcionId").alphanumeric({allow:" -.;,()"});
    //	var chk = $("#noBasico")[0];
	//	chk.checked = false;
  });
/*
 $(document).ready(function(){
 		$("#noBasico").click(function() {
	        var chk = $("#noBasico")[0];
	        var val = chk.checked;
	       	if (val){
	       		$("#segMonto2Id").removeAttr("disabled");
	       		$("#segMonto").attr("disabled","disabled");
	       	}else{
	       		$("#segMonto2Id").attr("disabled","disabled");
	       		$("#segMonto").removeAttr("disabled");
       	}
	});	
 }); 
 */
 $(document).ready(function(){
   	$("#benTelefono").numeric();
   	$("#porcentaje").numeric({allow:"."});
	$("#benPrimerNombre").alpha();
	$("#benPrimerApellido").alpha();
	$("#benSegundoNombre").alpha();
	$("#benSegundoApellido").alpha();
	$("#benApellidoCasadaId").alpha();
	$("#costoAnualId").numeric({allow:"."});

	if($('#tisId').val() != 2 && $('#tisId').val() != ""){
  		for(i =0;i<25;i++){
  			$("#porcentajeId"+i).removeAttr("disabled");
  		}
  	}else{
  		for(i =0;i<25;i++){
  			$("#porcentajeId"+i).attr("disabled","disabled");
  		}
  	}
  });
  
  function llamados(){
  	$('#tisId').val($('#tipoId').val());
  	if($('#tisId').val() != 2){
  		for(i =0;i<25;i++){
  			$("#porcentajeId"+i).removeAttr("disabled");
  		}
  	}else{
  		for(i =0;i<25;i++){
  			$("#porcentajeId"+i).attr("disabled","disabled");
  		}
  	}
  	ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do','accion=cargarPoliza&tipo='+$('#tipoId').val(),'divPol','polizaId');
	ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do','accion=cargarVencimiento&tipo='+$('#tipoId').val(),'divVen','vencimientoId');
	ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do','accion=cargarMeses&tipo='+$('#tipoId').val(),'divMeses','mesesId');
	//ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do','accion=cargarMontoAsegurado&tipo='+$('#tipoId').val(),'divMonto','segMontoId')
	//ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do','accion=cargarCuota&tipo='+$('#tipoId').val(),'divCuota','segCuotaId')
  }
  
  function recalculaCuota(){
  	var meses = $('#mesesBetween').val();
  	var cuota = ($('#costoAnualId').val())/meses;
	$('#segCuotaId').val(round_number(cuota,2));
  }
  
  function dDec(){
  	dosDecimales($('#costoAnualId').val(),'costoAnualId');
  }
  
  function recalcularBetween(){
  	var mesInt = parseInt($('#mesesId').val());
  	var mesFinInt = parseInt($('#mesFin').val());
  	var mesesBetween = -1;
  	//alert('mesInt = ' + mesInt + ' ; mesFinInt = ' + mesFinInt);
  	if(mesFinInt > mesInt){
		mesesBetween = mesFinInt - mesInt;
		//alert('mesesBetween 1: ' + mesesBetween);
	}else{
		if(mesFinInt==mesInt){
			mesesBetween = 12;
			//alert('mesesBetween 2: ' + mesesBetween);
		}else{
			mesesBetween = (12 - mesFinInt) + mesInt;
			//alert('mesesBetween 3: ' + mesesBetween);
		}
	}
	$('#mesesBetween').val(mesesBetween);
	recalculaCuota();
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

<html:form action="${_accion}" method="post"  styleId="formId">
	<p class="msg_head">
		Encabezado de la Solicitud
	</p>
	<div class="msg_body">
		<table align="center">
			<tr>
				<td>
					<label><bean:message key="lbl.seg.lineaSeguro" /></label>:
				</td>
				<td>
					<logic:present name="edit">
						<logic:equal value="1" name="edit">  
				   			<html:select property="tisId2" value="${form.tisId2}" disabled="true" styleClass="obligatorio" styleId="tipoId" size="1"
				   				onchange="llamados();">
								<html:option value="">...</html:option>
								<html:optionsCollection  label="tisNombre" name="lstTis" value="tisId"/>     					
							</html:select>
						</logic:equal>
					</logic:present>
					<logic:present name="edit">
						<logic:equal value="0" name="edit">  
				   			<html:select property="tisId2" value="${form.tisId2}"  styleClass="obligatorio" styleId="tipoId" size="1"
				   				onchange="llamados();">
								<html:option value="">...</html:option>
								<html:optionsCollection  label="tisNombre" name="lstTis" value="tisId"/>     					
							</html:select>
						</logic:equal>
					</logic:present>
					<html:hidden property="ctaTisTipoSeguro.tisId" value="${form.ctaTisTipoSeguro.tisId}" styleId="tisId" />
		     	</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.seg.poliza" /></label>:
				</td>
				<td>
					<div id="divPol">
		   				<html:text property="poliza" styleId="polizaId" value="${form.poliza}" styleClass="obligatorio" readonly="true" size="15" maxlength="15"/>
					</div>
			    </td>
				<td>
					<label><bean:message key="lbl.seg.vencimiento" /></label>:
				</td>
				<td>
					<div id="divVen">
		   				<html:text property="vencimiento" styleId="vencimientoId" value="${form.vencimiento}" styleClass="obligatorio" readonly="true" size="15" maxlength="15"/>
		     		</div>
		     	</td>
			</tr>
			<tr>
				<td colspan="2">
					<label><bean:message key="lbl.seg.asociadoInfo" /></label>:
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.seg.ascCodigoAsociado" /></label>:
				</td>
				<td>
		   			<html:text property="ascCodigoAsociado" value="${form.ascCodigoAsociado}" styleClass="obligatorio" readonly="true" size="15" />
		     	</td>
		     	<td>
					<label><bean:message key="lbl.seg.codigoAsociado" /></label>:
				</td>
				<td>
		   			<html:text property="codigoAsociado" value="${form.codigoAsociado}" styleClass="obligatorio" readonly="true" size="15" />
		     	</td>
		     </tr>
		     <tr>
		     	<td>
					<label><bean:message key="lbl.seg.asociado" /></label>:
				</td>
				<td align="left" colspan="3">
					<bean:write name="form" property="asociado" />
		     	</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.seg.segMonto" /></label>:
				</td>
				<td>
	   				<html:text property="segMonto" value="${form.segMonto}" styleId="segMontoId" styleClass="obligatorio" size="15" maxlength="15"/>
		     	</td>
		     	<td>
					<label><bean:message key="lbl.seg.inicioCobro" /></label>:
				</td>
				<td>
					<logic:present name="edit">
						<logic:equal value="0" name="edit" >
							<div id="divMeses">
								<html:hidden property="mesesBetween" styleId="mesesBetween" value="12"/>
								<html:hidden property="mesFin" styleId="mesFin" value=""/>
				   				<html:select property="meses" styleId="mesesId" value="${form.meses}">
				   					<html:option value="-1">Escoja el mes</html:option>
				   				</html:select>
				     		</div>
		     			</logic:equal>
		     			<logic:equal value="1" name="edit" >
								<html:hidden property="mesesBetween" styleId="mesesBetween" value="${form.mesesBetween}"/>
								<bean:write property="mes" name="form"/>
		     			</logic:equal>
		     		</logic:present>
		     	</td>
		    </tr>
		    <tr>
				<td>
					<label><bean:message key="lbl.seg.costoAnual" /></label>:
				</td>
				<td>
	   				<html:text property="segSaldoIni" value="${form.segSaldoIni}" styleId="costoAnualId" 
	   					styleClass="obligatorio" size="15" maxlength="15" onkeyup="recalculaCuota();dDec();"/>
		     	</td>
		     	<td>
					<label><bean:message key="lbl.seg.segCuota" /></label>:
				</td>
				<td>
					<div id="divCuota">
		   				<html:text property="segCuota" value="${form.segCuota}" styleId="segCuotaId" styleClass="obligatorio" size="15" maxlength="15"/>
		     		</div>
		     	</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.seg.segCertificado" /></label>:
				</td>
				<td>
		   			<html:text property="segCertificado" value="${form.segCertificado}" styleId="segCertificadoId" styleClass="obligatorio" size="15" maxlength="15"/>
		     	</td>
		     	<td>
					<label><bean:message key="lbl.seg.segCarnet" /></label>:
				</td>
				<td>
		   			<html:text property="segCarnet" value="${form.segCarnet}" styleId="segCarnetId" styleClass="obligatorio" size="15" maxlength="15"/>
		     	</td>
			</tr>
		</table>
	</div>
	<p class="msg_head">
		Agregar Beneficiarios
	</p>
	<div class="msg_body">
		<table align="center">
			<tr>
				<td>
					<label><bean:message key="lbl.ben.benPrimerNombre" /></label>:
				</td>
				<td>
		   			<html:text property="benPrimerNombre" value="${form.benPrimerNombre}" size="15" maxlength="50" styleId="benPrimerNombreId" styleClass="obligatorio"/>
		   			<span><bean:message key="msg.obligatorio"/></span>
		     	</td>
		     	<td>
					<label><bean:message key="lbl.ben.benSegundoNombre" /></label>:
				</td>
				<td>
		   			<html:text property="benSegundoNombre" value="${form.benSegundoNombre}" size="15" maxlength="50" styleId="benSegundoNombreId" />
		     	</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.ben.benPrimerApellido" /></label>:
				</td>
				<td>
		   			<html:text property="benPrimerApellido" value="${form.benPrimerApellido}" size="15" maxlength="50" styleId="benPrimerApellidoId" styleClass="obligatorio"/>
		   			<span><bean:message key="msg.obligatorio"/></span>
		     	</td>
		     	<td>
					<label><bean:message key="lbl.ben.benSegundoApellido" /></label>:
				</td>
				<td>
		   			<html:text property="benSegundoApellido" value="${form.benSegundoApellido}" size="15" maxlength="50" styleId="benSegundoApellidoId" />
		     	</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.ben.benApellidoCasada" /></label>:
				</td>
				<td>
		   			<html:text property="benApellidoCasada" value="${form.benApellidoCasada}" size="15" maxlength="50" styleId="benApellidoCasadaId" />
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
			</tr>
			<tr>
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
					<html:submit property="accion" >
						<bean:message key="cmd.seg.agregarBen" />
					</html:submit> 
				</td>
		   	</tr>
		</table>
	</div>
	<p class="msg_head">
		Controles
	</p>
	<div class="msg_body">
		<table>
			<tr>
				<logic:present name="edit">
					<logic:equal value="0" name="edit">
						<td>
							<html:submit property="accion" >
								<bean:message key="cmd.seg.guardar" />
							</html:submit> 
						</td>
						<td>
							<html:submit property="accion" >
								<bean:message key="cmd.seg.cancelar2" />
							</html:submit> 
						</td>
					</logic:equal>
					<logic:equal value="1" name="edit">
						<td>
							<html:submit property="accion" >
								<bean:message key="cmd.seg.salvar" />
							</html:submit> 
						</td>
						<td>
							<html:submit property="accion" >
								<bean:message key="cmd.seg.agregaNotas" />
							</html:submit> 
						</td>
						<td>
							<html:submit property="accion" onclick="$('#pageId').val('0');">
								<bean:message key="cmd.seg.cancelar2" />
							</html:submit> 
						</td>
					</logic:equal>
				</logic:present>
			</tr>
		</table>
	</div> 
	<input id="pageId" type="hidden" name="page" value="3">
	<html:hidden property="ascId" value="${form.ascId}"/>
	<html:hidden property="segId" value="${form.segId}" />
	<div id="lista-datos">
		${_lista2}
	</div>
</html:form>

