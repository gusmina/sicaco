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
    $("button:gt(0)").attr("disabled","disabled");
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

$(document).ready(function(){
    $("#ascRentaDomicilio").numeric({allow:"."});
	$("#ascSalario").numeric({allow:"."});
	$("#ascCodigo").alphanumeric();
	$("#ascProfesion").alpha({allow:" "});
	$("#ascRazon").alpha({allow:" "});
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
<html:form action="${_accion}" method="post" styleId="formId">
	<p class="msg_head">
		<bean:message key="lbl.asc.ascInformacion" />
	</p>
	<div class="msg_body">
		<table border="0">			
		<tr>
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
					<html:text maxlength="50" size="20" property="ascCodigo"
						styleClass="obligatorio" value="${form.ascCodigo}"
						styleId="ascCodigo" />
					<span><bean:message key="msg.obligatorio" /> </span>
				</td>
			</tr>
			<%--Fin  Fila del codigo de asociado y tipo de asociado --%>
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
					<span><bean:message key="msg.obligatorio" />
					</span>
				</td>
				<td>
					<label>
						<bean:message key="lbl.asc.ascProfesion" />
					</label>
				</td>
				<td>
					<html:text maxlength="50" size="20" property="ascProfesion"
						styleClass="obligatorio" value="${form.ascProfesion}"
						styleId="ascProfesion" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascLugarTrabajoInterno" />
					</label>
				</td>
				<%-- 
					onchange="ajaxCallNormal('${pageContext.request.contextPath}/asociados${_accion}.do','accion=cargarListaDeptTrab&ctaDptDepartamentoTrabajo.ctaEtrEmpresaTrabajo.etrId='+$('#lugTrab').val(),'comboDepto')"
					
					<div id="comboDepto" style="">
						<html:select property="ctaDptDepartamentoTrabajo.dptId"
							styleClass="obligatorio" 
							value="${form.ctaDptDepartamentoTrabajo.dptId}"
							styleId="deptoTrab">
							<logic:notPresent parameter="mdf" scope="request">
								<html:option value=" "></html:option>
							</logic:notPresent>
							<logic:present parameter="mdf" scope="request">
								<html:option value="${form.ctaDptDepartamentoTrabajo.dptId}">${form.ctaDptDepartamentoTrabajo.dptNombre}</html:option>
							</logic:present>
						</html:select>
						<span><bean:message key="msg.obligatorio" />
						</span>
					</div>
				--%>
				<td>
					<html:select
						property="ctaDptDepartamentoTrabajo.ctaEtrEmpresaTrabajo.etrId"
						styleClass="obligatorio"
						value="${form.ctaDptDepartamentoTrabajo.ctaEtrEmpresaTrabajo.etrId}"
						styleId="lugTrab">
						<html:option value="null">----</html:option>
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
						<bean:message key="lbl.asc.ascSalario" />
					</label>
				</td>
				<td>
					<html:text maxlength="50" size="20" property="ascSalario"
						styleClass="obligatorio" value="${form.ascSalario}"
						styleId="ascSalario" />
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
						styleId="deptoTrab">
						<html:optionsCollection name="tcoList" label="tcoNombre"
							value="tcoId" />
					</html:select>
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
						styleClass="obligatorio" value="${form.ascJefeInmediato}" />
				</td>
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
		</table>
	</div>
	<%-- Datos Personales --%>
	<p class="msg_head">
		<bean:message key="lbl.asc.ascPersonales" />
	</p>
	<!--
		
		 -->
	<div class="msg_body">
		<table border="0">
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascDuiLugarExp" />
					</label>
				</td>
				<td>
					<html:text maxlength="100" size="20" property="ascDuiLugarExp"
						styleClass="obligatorio" value="${form.ascDuiLugarExp}" />
					<span><bean:message key="msg.obligatorio" />
					</span>
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
						maxlength="10" size="10" /><span><bean:message key="msg.obligatorio" /></span>
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
					<label>
						<bean:message key="lbl.asc.ascNombreNit" />
					</label>
				</td>
				<td>
					<html:text maxlength="200" size="20" property="ascNombreNit"
						styleClass="obligatorio" value="${form.ascNombreNit}" />
					<span><bean:message key="msg.obligatorio" />
					</span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascIsss" />
					</label>
				</td>
				<td>
					<html:text maxlength="15" size="20" property="ascIsss"
						value="${form.ascIsss}" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.asc.ascNacionalidad" />
					</label>
				</td>
				<td>
					<html:text maxlength="20" size="20" property="ascNacionalidad"
						styleClass="obligatorio" value="${form.ascNacionalidad}" />
					<span><bean:message key="msg.obligatorio" />
					</span>
				</td>
			</tr>
		</table>
	</div>
	<p class="msg_head">
		<bean:message key="lbl.asc.ascDomiciliares" />
	</p>
	<div class="msg_body">
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
						styleId="ascRentaDomicilio" />
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
		<table>
			<tr>
				<td>
					<label class="obligatorio"><bean:message key="lbl.asc.cuotaAfiliacionCancelada"/></label>
				</td>
				<td>
					<html:select property="ascPagoIngreso" styleClass="obligatorio">
						<html:option value="N">NO</html:option>
						<html:option value="S">SI</html:option>
					</html:select>
				</td>
			</tr>
		</table>
	</div>
	<input id="pageId" type="hidden" name="page" value="2"/>
	<html:hidden property="ascId" value="${form.ascId}" />
	<html:hidden property="perId" value="${form.perId}" />
	<html:hidden property="usr" value="${form.usr}" />
</html:form>