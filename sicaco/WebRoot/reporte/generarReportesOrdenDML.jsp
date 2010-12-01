<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript">
	$(document).ready(function(){
	   	$("#anio").numeric();
	   	$("#asociadoId").alphanumeric();
	   	$("#perNombreId").alpha();
		$("#perApellido").alpha();
		$("#numDias").numeric();
	});  

	function selected3(){
		$("#asociadoIdId").removeAttr("disabled");
		$("#perNombreId").attr("disabled","disabled");
		$("#perApellido").attr("disabled","disabled");
		//$("#botonLoad").attr("disabled","disabled");
	};
	
	function selected4(){
		$("#asociadoIdId").attr("disabled","disabled");
		$("#perNombreId").removeAttr("disabled");
		$("#perApellido").removeAttr("disabled");
		//$("#botonLoad").removeAttr("disabled");
	};
	
	function saveSeleccionC(valor) {
		valores = valor.split(';');
		$('#codCli').val(valores[0]);
		$('#perNombreId').val(valores[1]);
		$('#perApellido').val(valores[2]);
		$('#ascId2').val(valores[3]);
		$('#resultadoCli').hide('slow')
	};
	
	function llamadaAjax(nombre,apellido,ascEmp,asociadoId){
		ajaxCallNormal('${pageContext.request.contextPath}/reporte${_accion}.do','accion=cargarAsociado&perNombre='+nombre+'&perApellido='+apellido+'&ascEmp='+ascEmp+'&asociadoId='+asociadoId,'asociados');
	};
	
	function seleccion(){
		var chk = $("#chkbx")[0];
		if(chk.checked == true){
			$("#ascEmpId").val(2);
		}else{
			$("#ascEmpId").val(1);
		}
	};
	
</script>
<div>
	<html:form action="${_accion}" method="post" styleId="formId">
		<table border="0" id="tabla">
			<tr>
				<td colspan="2" align="center">
					<label  ><b><u>		${form.nombre}		</u></b></label>
				</td>
			</tr>
			<logic:present name="filtro" scope="request">
				<logic:equal value="1" name="filtro"><!-- CAMPOS= fecha_ini,fecha_fin,codigoAsociado -->
					<tr>
						<td>
							<label>
								<bean:message key="lbl.reportesOrd.fechaIni" />
							</label>
						</td>
						<td>
							<html:text onkeyup="mask(this);" property="fechaIni" value="${form.fechaIni}"
								styleClass="obligatorio" maxlength="10" size="10" styleId="fechaIni2"/>
							<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaIni2" type="button" value="...." />
						</td>
					</tr>
					<tr>
						<td>
							<label>
								<bean:message key="lbl.reportesOrd.fechaFin" />
							</label>
						</td>
						<td>
							<html:text onkeyup="mask(this);" property="fechaFin" value="${form.fechaFin}"
								styleClass="obligatorio" maxlength="10" size="10" styleId="fechaFin2"/>
							<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaFin2" type="button" value="...." />
						</td>
					</tr>
					<script type="text/javascript">
           	    		Calendar.setup({
		             		 inputField	: "fechaIni2",
		             		 button		: "button_fechaIni2",
		              		align			: "Br"
		            	})
		           	 	Calendar.setup({
		              		inputField	: "fechaFin2",
		              		button		: "button_fechaFin2",
		             		 align			: "Br"
		            	})
		            	
                	</script>
                	<tr>
				    	<td colspan="2"><label>
							<bean:message key="lbl.ordcom.asociado" />:</label>
							<input type="checkbox" name="chkbx" id="chkbx" onclick="seleccion();" /> 
							<label><bean:message key="lbl.ordcom.codEmp" /></label>
							<html:hidden property="ascEmp" styleId="ascEmpId" value="1" />
						</td>
			    	</tr>
<%-- 					<tr>
						<td colspan="2">
							<label><bean:message key="lbl.desembolso.asociado" /></label>:
						</td>
					</tr>	--%>
					<tr>
						<td>
							<!-- <html:radio property="selected" value="3" styleId="selectedId" onclick="selected3();"/> -->
							<label><bean:message key="lbl.desembolso.asociadoId"/></label>:
						</td>
						<td>
							<html:text styleClass="obligatorio" size="15" maxlength="13" property="asociadoId" value="${form.asociadoId}" styleId="asociadoIdId" /> 
						</td>
					</tr>
					<tr>
						<td>
							<!-- <html:radio property="selected" value="4" styleId="selectedId" onclick="selected4();"/>-->
							<label><bean:message key="lbl.orden.primerNombre"/></label>:
						</td>
						<td>
							<html:text styleClass="obligatorio" size="15" maxlength="25" property="perNombre" value="${form.perNombre}" styleId="perNombreId" /> 
						</td>
						<td>
							<label><bean:message key="lbl.orden.primerApellido"/></label>:
						</td>
						<td>
							<html:text styleClass="obligatorio" size="15" maxlength="25" property="perApellido" value="${form.perApellido}" styleId="perApellido" /> 
							<html:hidden property="ascId2" value="${form.ascId2}" styleId="ascId2"/>
						</td>
						<td>
							<input type="button" value="Buscar" id="botonLoad"
							onclick="llamadaAjax($('#perNombreId').val(),$('#perApellido').val(),$('#ascEmpId').val(),$('#asociadoIdId').val());"/>
						</td>						
					</tr>
					<tr>
					
					</tr>
					<tr>
						<td colspan="5" align="center">
							<div id="asociados">
							</div>
					    </td>
					</tr>
				</logic:equal>
				<logic:equal value="2" name="filtro"><!-- CAMPOS= fecha_ini,fecha_fin,codigoAsociado -->
				
				
				
					<tr>
						<td>
							<label>						
								<bean:message key="lbl.reportesOrd.fechaIni" />
							</label>
						</td>
						<td>
							<html:text onkeyup="mask(this);" property="fechaIni" value="${form.fechaIni}"
								styleClass="obligatorio" maxlength="10" size="10" styleId="fechaIni2"/>
							<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaIni2" type="button" value="...." />
						</td>
					</tr>
					<tr>
						<td>
							<label>
								<bean:message key="lbl.reportesOrd.fechaFin" />
							</label>
						</td>
						<td>
							<html:text onkeyup="mask(this);" property="fechaFin" value="${form.fechaFin}"
								styleClass="obligatorio" maxlength="10" size="10" styleId="fechaFin2"/>
							<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaFin2" type="button" value="...." />
						</td>
					</tr>
					<script type="text/javascript">
           	    		Calendar.setup({
		             		 inputField	: "fechaIni2",
		             		 button		: "button_fechaIni2",
		              		align			: "Br"
		            	})
		           	 	Calendar.setup({
		              		inputField	: "fechaFin2",
		              		button		: "button_fechaFin2",
		             		 align			: "Br"
		            	})
		            	
                	</script>
				</logic:equal>
				<logic:equal value="3" name="filtro"><!-- CAMPOS= fecha_ini,fecha_fin,codigoAsociado -->
					<tr>
						<td>
							<label>
								<bean:message key="lbl.reportesOrd.dias" />
							</label>
						</td>
						<td>
							<html:text property="numDias" value="${form.numDias}" styleId="numDias" />
						</td>
					</tr>				
				</logic:equal>
			</logic:present>
			<tr>
				<td colspan="2" align="center">
					<html:submit property="accion">
						<bean:message key="cmd.reportesOrd.generarReporte" />
					</html:submit>
					<input type="button" value="Limpiar"
						onclick="cleanFields('formId');">
				</td>
			</tr>
	</table>
		<html:hidden property="reporteId" value="${form.reporteId}" />
		<html:hidden property="numR" value="${form.numR}"/>
		<html:hidden property="nombre" value="${form.nombre}" />
	</html:form>
</div>