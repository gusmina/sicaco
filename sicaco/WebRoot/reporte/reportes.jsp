<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<link rel="stylesheet" type="text/css" href="../css/zebra.css" >
<script type="text/javascript">
	var newTitulo = '';
</script>
<script type="text/javascript">
	var direcciones = new Array()
	direcciones[0]='http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/reporte/servicios.do?accion=<bean:message key="cmd.repGen.lin"/>'
	direcciones[1]='http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/reporte/servicios.do?accion=<bean:message key="cmd.repGen.tipoAhorro"/>'
	direcciones[2]='http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/reporte/servicios.do?accion=<bean:message key="cmd.repGen.empresaTrabajo"/>'
	direcciones[3]='http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/reporte/servicios.do?accion=<bean:message key="cmd.repGen.tipoCuenta"/>'
	direcciones[4]='http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/reporte/servicios.do?accion=<bean:message key="cmd.repGen.cuentaContable"/>'
	direcciones[5]='http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/reporte/servicios.do?accion=<bean:message key="cmd.repGen.tipoSeguro"/>'
	direcciones[6]='http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/reporte/servicios.do?accion=<bean:message key="cmd.repGen.estadoAsociado"/>'
	direcciones[7]='http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/reporte/servicios.do?accion=<bean:message key="cmd.repGen.tipoTransaccion"/>' //Ya no se utiliza
	direcciones[8]='http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/reporte/servicios.do?accion=<bean:message key="cmd.repGen.tipoPrestamo"/>'    //Ya no se utiliza
	direcciones[9]='http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/reporte/servicios.do?accion=<bean:message key="cmd.repGen.tipoGarantia"/>'
	direcciones[10]='http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/reporte/servicios.do?accion=<bean:message key="cmd.repGen.lineaPrestamo"/>'  //Ya no se utiliza
	
	function loadXMLToCombo(xml,text) {
		var comboObjectivo = '.'+$('identificador',xml).attr('valor')
		
		$('opcion',xml).each(function() {
			$this = $(this)
			var opcion = '<option value="'+$this.attr('valor')+'">'+$this.text()+'</option>'
			$(comboObjectivo).each(function() {
				$(this).append(opcion)
			})
		})
	}
	
	function clickChildCombo(combo) {
		var valores = $(combo).attr('class').split(' ')
		var keyDir = valores[1]/1
		var padre = '#' + valores[2]
		var llave = $(padre).val()
		var newDireccion = direcciones[keyDir]+'&idPadre='+llave
		//Limpiamos el hijo
		$(combo).empty()
		$.ajax({
				dataType:'xml',
				type:'POST',
				url:newDireccion,
				success: loadXMLToCombo
			})
	}
	
	$(document).ready(function() {
			$('#lineaAhorro').change(function(){
				ajaxCallSincrono('${pageContext.request.contextPath}/cuentaCorriente/tipoAhorro.do','accion=cargarTipoAhorros&tahId='+$(this).val(),'tipoAhorro');
			});
			
			
		//Obtenemos todos los combos que se cargan automatico
		$('select.fillajax').each(function(i) {
			var valores = $(this).attr('class').split(' ')
			var keyDir = valores[1]/1
			$(this).empty()
			$.ajax({
				dataType:'xml',
				type:'POST',
				url:direcciones[keyDir],
				success: loadXMLToCombo
			})
		})
		
		//Hacemos que los combox que tengan padres se carguen de forma automatica
		$('select.fillajaxparent').focus(function() {
			clickChildCombo(this)
		})
		
		//Cambiamos el titulo del reporte
		$('fieldset dl dt label').html(newTitulo)
		
		$("#anioSocioId").numeric();
		$('#anioOtP').numeric();
			
	})
	
	
	function saveSeleccionC(valor) {
		valores = valor.split(';');
		$('#codCli').val(valores[0]);
		$('#perNombreId').val(valores[1]);
		$('#perApellido').val(valores[2]);
		$('#ascId2').val(valores[3]);
		$('#resultadoCli').hide('slow')
	};
	
	function llamadaAjax(nombre,apellido,ascEmp,asociadoId){
ajaxCallSincrono('${pageContext.request.contextPath}/reporte/reportesOrden.do','accion=cargarAsociado&perNombre='+nombre+'&perApellido='+apellido+'&ascEmp='+ascEmp+'&asociadoId='+asociadoId,'asociados');
	};
	
	function seleccion(){
		var chk = $("#chkbx")[0];
		if(chk.checked == true){
			$("#ascEmpId").val(2);
		}else{
			$("#ascEmpId").val(1);
		}
	};
	
		function limpiarAsoc(){
		$('#asociadoIdId').val('');
		$('#perNombreId').val('');
		$('#perApellido').val('');
		$('#ascId2').val('');
	}
	
	
</script>

<html:form action="/reportes" target="_blank">
	<table>
		<tbody>
			<%-- Seleccionamos los campos a mostrar --%>

			<%-- R E P O R T E S   D E   A S O C I A D O S --%>

			<%-- REPORTE DE ASOCIADOS --%>
			<logic:equal value="reporte_asociados" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							C&oacute;digo Asociado
						</label>
					</td>
					<td>
						<input name="ascCodigoAsociado" type="text" />

						<script type="text/javascript">
							newTitulo = 'Reporte Registro de Asociados';
						</script>
					</td>
				</tr>
			</logic:equal>


			<%-- REPORTE AFILIADOS DURANTE EL MES --%>

			<logic:equal value="reporte_afiliados" parameter="p76e3123r">
			<tr>
					<td colspan="2">
						<script type="text/javascript">
							newTitulo = 'Afiliados durante el mes reportes.jsp';
						</script>
					</td>
				</tr>
				
		<table
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 70%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra"> 
		<tr><th colspan="4"> Par&aacute;metros de reporte </th> </tr>
				<tr class="odd">
					<td>
						<label>
							Mes :
						</label>
					</td>
					<td>
						<select name="mes">
							<option value="1">
								Enero
							</option>
							<option value="2">
								Febrero
							</option>
							<option value="3">
								Marzo
							</option>
							<option value="4">
								Abril
							</option>
							<option value="5">
								Mayo
							</option>
							<option value="6">
								Junio
							</option>
							<option value="7">
								Julio
							</option>
							<option value="8">
								Agosto
							</option>
							<option value="9">
								Septiembre
							</option>
							<option value="10">
								Octubre
							</option>
							<option value="11">
								Noviembre
							</option>
							<option value="12">
								Diciembre
							</option>
						</select>
					</td>
					<td>
						<label>
							A&ntilde;o
						</label>
					</td>
					<td>

					<select name="anio" style="width: 100px">
						<%
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
						String anio = sdf.format(new Date());		
						Integer anioActual = new Integer(anio);
						Integer inicial = anioActual-4;
						
						while(anioActual > inicial){
							out.print("<option value='"+anioActual.toString()+"'>"+anioActual.toString()+"</option>");
							anioActual--;
						}
						 %>
					</select>
					</td>

				</tr>
				
				<tr>
					<td>
						<label>
							Nombre Gerente
						</label>
					</td>
					<td>
						<input name="gerente">
					</td>
					<td>
						<label>
							Nombre Promotora Social
						</label>
					</td>
					<td>
						<input name="promotora">
					</td>
				</tr>
				<tr class="odd">
			    <td colspan="4">
				    <div align="center" style="text-align: center;">
									<input  type="checkbox" name="excel" /> Exportar a excel
					</div>
			    </td>
			  </tr>
			</table>
			</logic:equal>



			<%-- REPORTE BENEFICIARIOSXASOCIADOS --%>
			<logic:equal value="Reporte_BeneficiariosXAsociados"
				parameter="p76e3123r">
				<tr>
					<td>
						<label>
							C&oacute;digo de Empresa:
						</label>
					</td>
					<td colspan="3">
						<select name="codEmpresa" id="empresaTrabajo"
							class="fillajax 2 empresaTrabajo">
							</select>

						<%-- CAMBIO DE TITULO --%>
						<script type="text/javascript">
							newTitulo = 'Beneficiarios por Asociados';
						</script>
					</td>
				</tr>
			</logic:equal>



			<%-- REPORTE PLANILLAS POR EMPRESA--%>
		

			<logic:equal value="report_planilla_empresa" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							C&oacute;digo de Empresa:
						</label>
					</td>
					<td>
						<html:select styleId="empresaTrabajo" property="etrId">
							<html:options collection="listaEmpresa" property="etrId"
								labelProperty="etrNombre" />
						</html:select>

						<%-- CAMBIO DE TITULO --%>
						<script type="text/javascript">
							newTitulo = 'Reporte de Planillas por Empresa';
						</script>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Fecha:
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fecha"
							onkeyup="JavaScript:mask(this);" id="fechaZ" />
						<input id="calendarZ" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fechaZ",
				              button        : "calendarZ",
				              align         : "Br"
				            });
				         </script>
					</td>
				</tr>
				<html:hidden property="idTTR" styleId="tipoTransaccion" value="12" />
			</logic:equal>



			<%-- REPORTE ACTUALIZACION DE PLANILLA --%>

			<logic:equal value="reporte_actualizacion_planilla"
				parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Fecha :
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fecha"
							onkeyup="JavaScript:mask(this);" id="dia001" />
						<input id="calendar" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "dia001",
				              button        : "calendar",
				              align         : "Br"
				            });
				         </script>

						<script type="text/javascript">
							newTitulo = 'REPORTE ACTUALIZACION DE PLANILLA';
						</script>
					</td>
				</tr>
			</logic:equal>


			<%-- REPORTE DE DEDUCCION QUINCENAL --%>
			<logic:equal value="reporte_deduccion_quincenal"
				parameter="p76e3123r">
				<tr>
					<td>
						<label>
							IDEmpresa :
						</label>
					</td>
					<td colspan="3">
						<select name="empresa" id="empresaTrabajo"
							class="fillajax 2 empresaTrabajo"></select>
					</td>
				</tr>
				<tr>
					<td>
						<%-- CAMBIO DE TITULO --%>
						<script type="text/javascript">
							newTitulo = 'Deduccion Quincenal';
						</script>
					</td>
				</tr>
			</logic:equal>



			<%-- RENTA DE ASOCIADOS --%>
			<logic:equal value="renta_asociados" parameter="p76e3123r">
				<tr>
					<td>

						
						<script type="text/javascript">
							newTitulo = 'Renta de Asociados';
						</script>
					</td>
				</tr>
			</logic:equal>




			<%-- REPORTE DE SOCIOS INSAFOCOOP --%>

			<logic:equal value="reporte_socios" parameter="p76e3123r">
			<tr>
					<td>
						<script type="text/javascript">
							newTitulo = 'Reporte Socios INSAFOCOP';
						</script>
					</td>
				</tr>
			
			<table
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 70%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra">
				
				<tr>
					<th colspan="4"> 
							Reporte Socios INSAFOCOP
					</th>
				</tr>
				<tr class = "odd">
					<td>
						<label>
							Empresa :
						</label>
					</td>
					<td colspan="3">
						<html:select styleId="empresaTrabajo" property="etrId" style="width: 200px;">
						<html:option value="-1">Consolidado</html:option>
							<html:options collection="listaEmpresa" property="etrId"
								labelProperty="etrNombre" />
						</html:select>
					</td>
				</tr>
				<tr >
					<td>
						<label>
							Fecha :
						</label>
					</td>
					<td>
					<input type="text" maxlength="10" size="10" name="fecha"
							onkeyup="JavaScript:mask(this);" id="dia001" />
						<input id="calendar" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "dia001",
				              button        : "calendar",
				              align         : "Br"
				            });
				         </script>
					</td>
				</tr>
			  <tr class="odd">
			    <td colspan="4">
				    <div align="center" style="text-align: center;">
									<input  type="checkbox" name="excel" /> Exportar a excel
					</div>
			    </td>
			  </tr>
			</table>
			</logic:equal>


			<%-- REPORTE DE SOCIO LIQUIDADO --%>

			<logic:equal value="socios_liquidados" parameter="p76e3123r">
				<!-- <tr>
					<td>
						<label>
							C&oacute;digo Asociado
						</label>
					</td>
					<td>
						<input name="ascId" type="text" />
					</td>
					<td>
						<script type="text/javascript">
							newTitulo = 'Reporte de socio liquidado';
						</script>
					</td>
				</tr> -->
				<tr>
					<td>
						<label>
							Mes :
						</label>
					</td>
					<td>
						<select name="mes">
							<option value="1">Enero</option>
							<option value="2">Febrero</option>
							<option value="3">Marzo</option>
							<option value="4">Abril</option>
							<option value="5">Mayo</option>
							<option value="6">Junio</option>
							<option value="7">Julio</option>
							<option value="8">Agosto</option>
							<option value="9">Septiembre</option>
							<option value="10">Octubre</option>
							<option value="11">Noviembre</option>
							<option value="12">Diciembre</option>
						</select>
					</td>
					<td>
						<label>
							A&ntilde;o
						</label>
					</td>
					<td>
						<input name="anio" maxlength="4" id="anioSocioId"/>
					</td>
					<td>
						<script type="text/javascript">
							newTitulo = 'Reporte de socio liquidado';
						</script>
					</td>					
				</tr>				
			</logic:equal>


			<%-- R E P O R T E S   D E   A H O R R O  Y  A P O R T A C I O N E S --%>

			<%-- REPORTE DE SALDOS DE APORTACIONES --%>

			<logic:equal value="SaldosDeAportaciones2" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Empresa :
						</label>
					</td>
					<td colspan="3">
						<select name="IDEmpresa" id="empresaTrabajo"
							class="fillajax 2 empresaTrabajo"></select>

						<%-- CAMBIO DE TITULO --%>
						<script type="text/javascript">
							newTitulo = 'Saldos de Aportaciones';
						</script>
					</td>
				</tr>
			</logic:equal>


			<%-- REPORTE DE MOVIMIENTO CUENTA CONTABLE --%>

			<logic:equal value="reporteMovPorCttaContable" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Fecha Inicio :
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fechaIni"
							onkeyup="JavaScript:mask(this);" id="fecha11" />
						<input id="calendar11" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fecha11",
				              button        : "calendar11",
				              align         : "Br"
				            });
				         </script>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Fecha Fin :
						</label>
					</td>
					<td>

						<input type="text" maxlength="10" size="10" name="fechaFin"
							onkeyup="JavaScript:mask(this);" id="fecha21" />
						<input id="calendar21" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fecha21",
				              button        : "calendar21",
				              align         : "Br"
				            });			            
				         </script>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Linea :
						</label>
					</td>
					<td>
						<select name="linea" class="fillajax 0 linea" id="linea1"></select>
					</td>
					<td>
						<label>
							Tipo Ahorro :
						</label>
					</td>
					<td>
						<select name="tahId" id="tipoAhorro"></select>

						<script type="text/javascript">
							newTitulo = 'Intereses Capitalizados Por Ahorrante';
						</script>
					</td>
				</tr>
				<%-- CAMBIO DE TITULO --%>
				<script type="text/javascript">
							newTitulo = 'Reporte de Movimientos Cuenta Contable';
						</script>
			</logic:equal>




			<%-- REPORTE DE INTERES CAPITALIZADO POR AHORRANTE --%>

			<logic:equal value="InteresCapitalizadoPorAhorrante"
				parameter="p76e3123r">
				<table
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 70%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra">
				<tr>  
					<th colspan="4">Reporte de capitalizacion de ahorros</th> 
				</tr>
				<tr class= "odd">
					<td>
						<label>
							Empresa :
						</label>
					</td>
					<td colspan="3">
						<html:select styleId="empresaTrabajo" property="etrId">
						<html:option value="-1">- - Consolidado - -</html:option>
							<html:options collection="listaEmpresa" property="etrId"
								labelProperty="etrNombre" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Mes :
						</label>
					</td>
					<td>
						<select name="mes">
							<option value="1">
								Enero
							</option>
							<option value="2">
								Febrero
							</option>
							<option value="3">
								Marzo
							</option>
							<option value="4">
								Abril
							</option>
							<option value="5">
								Mayo
							</option>
							<option value="6">
								Junio
							</option>
							<option value="7">
								Julio
							</option>
							<option value="8">
								Agosto
							</option>
							<option value="9">
								Septiembre
							</option>
							<option value="10">
								Octubre
							</option>
							<option value="11">
								Noviembre
							</option>
							<option value="12">
								Diciembre
							</option>
						</select>
					</td>

					
				<td align="center">
					A&ntilde;o:	
				</td>
				<td align="center">
				<select name="anio" style="width: 100px">
				<%
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				String anio = sdf.format(new Date());		
				Integer anioActual = new Integer(anio);
				Integer inicial = anioActual-4;
				
				while(anioActual > inicial){
					out.print("<option value='"+anioActual.toString()+"'>"+anioActual.toString()+"</option>");
					anioActual--;
				}
				 %>
				</select>
				
				</td>
				</tr>
				<tr class= "odd">
					<td>
						<label>
							Linea :
						</label>
					</td>
					<td>
						<html:select styleId="lineaAhorro" property="lahId">
						<html:option value="-1">- - Todas las lineas - -</html:option>
							<html:options collection="listaLineasAhorro" property="lahId"
								labelProperty="lahNombre" />
								
						</html:select>
					</td>
					<td>
						<label>
							Tipo Ahorro :
						</label>
					</td>
					<td>
					<div id="tipoAhorro">
						<html:select property="tahId" styleId="tipoAhorroId">
							<html:option value="-1">- - Todos los tipos - -</html:option>
							</html:select>
					</div>

						<script type="text/javascript">
							newTitulo = 'Intereses Capitalizados Por Ahorrante';
						</script>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label>
							Mostrar Columna de Interes :
						</label>
					</td>
					<td colspan="2">
						<html:select property="ver_intereses">
							<option value="1">
								Si
							</option>
							<option value="0">
								No
							</option>
						</html:select>

					</td>

				</tr>
				<tr class= "odd">
				<th colspan="4">Asociado</th>
				</tr>
				<tr>
						<td>
							<label><bean:message key="lbl.desembolso.asociadoId"/></label>:
						</td>
						<td>
							<html:text styleClass="obligatorio" size="15" maxlength="13" property="asociadoId" value="${form.asociadoId}" styleId="asociadoIdId" /> 
						</td>
						<td colspan="2">
							<input type="checkbox" name="chkbx" id="chkbx" onclick="seleccion();" /> 
							<label><bean:message key="lbl.ordcom.codEmp" /></label>
							<html:hidden property="ascEmp" styleId="ascEmpId" value="1" />

							<input type="button" value="..." id="botonLoad"
							onclick="llamadaAjax($('#perNombreId').val(),$('#perApellido').val(),$('#ascEmpId').val(),$('#asociadoIdId').val());"/>
							<input type="button" value="X" id="botonClear"
							onclick="limpiarAsoc();"/>
					</td>
					</tr>
					<tr>
						<td colspan="2">
							<label><bean:message key="lbl.orden.primerNombre"/></label>:
						
							<html:text styleClass="obligatorio" size="15" maxlength="25" property="perNombre" value="${form.perNombre}" styleId="perNombreId" /> 
						</td>
						<td colspan="2">
							<label><bean:message key="lbl.orden.primerApellido"/></label>:

							<html:text styleClass="obligatorio" size="15" maxlength="25" property="perApellido" value="${form.perApellido}" styleId="perApellido" /> 
							<html:hidden property="ascId2" value="${form.ascId2}" styleId="ascId2"/>
						</td>
						
					</tr>
					<tr>
					
					</tr>
					<tr>
						<td colspan="4" align="center">
							<div id="asociados">
							</div>
					    </td>
					</tr>
					<tr class="odd">
						<td colspan="4">
						<div align="center" style="text-align: center;">
						<input  type="checkbox" name="excel" /> Exportar a excel
						</div>
					</td>
					</tr>
				</table>
			</logic:equal>



			<%-- REPORTE DE SALDOS DE AHORRO --%>

			<logic:equal value="reporteSaldosAhorro" parameter="p76e3123r">
				<table
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 70%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra">
				<tr>
				<th colspan="4"> <b>Empresa </b></th>
				</tr>
				<tr>
					<td>
						<label>
							Empresa :
						</label>
					</td>
					<td colspan="3">
						<html:select styleId="empresaTrabajo" property="etrId">
						<html:option value="-1">- - Consolidado - -</html:option>
							<html:options collection="listaEmpresa" property="etrId"
								labelProperty="etrNombre" />
						</html:select>
					</td>
				</tr>
				<tr>
				<th colspan="4"><b>Ahorro</b> </th>
				</tr>
				<tr>
					<td>
						<label>
							Linea Ahorro:
						</label>
					</td>
					<td>
						<html:select styleId="lineaAhorro" property="lahId">
						<html:option value="-1">- - Todas las lineas - -</html:option>
							<html:options collection="listaLineasAhorro" property="lahId"
								labelProperty="lahNombre" />
						</html:select>

					</td>
						
					<td>
						<label>
							Tipo Ahorro :
						</label>
					</td>
					
				<td>
					<div id="tipoAhorro">
						<html:select property="tahId" styleId="tipoAhorroId">
							<html:option value="-1">- - Todos los tipos - -</html:option>
							</html:select>
					</div>
				</td>
				<td align="right">
				</td>
				</tr>
				<tr>
				<th colspan="4"><b>Asociado</b></th>
				</tr>
				<tr>
						<td>
							<label><bean:message key="lbl.desembolso.asociadoId"/></label>:
						</td>
						<td>
							<html:text styleClass="obligatorio" size="15" maxlength="13" property="asociadoId" value="${form.asociadoId}" styleId="asociadoIdId" /> 
						</td>
						<td colspan="2">
							<input type="checkbox" name="chkbx" id="chkbx" onclick="seleccion();" /> 
							<bean:message key="lbl.ordcom.codEmp" />
							<html:hidden property="ascEmp" styleId="ascEmpId" value="1" />

							<input type="button" value="..." id="botonLoad"
							onclick="llamadaAjax($('#perNombreId').val(),$('#perApellido').val(),$('#ascEmpId').val(),$('#asociadoIdId').val());"/>
							<input type="button" value="X" id="botonClear"
							onclick="limpiarAsoc();"/>
					</td>
					</tr>
					<tr>
						<td colspan="2">
							<label><bean:message key="lbl.orden.primerNombre"/></label>:
						
							<html:text styleClass="obligatorio" size="15" maxlength="25" property="perNombre" value="${form.perNombre}" styleId="perNombreId" /> 
						</td>
						<td colspan="2">
							<label><bean:message key="lbl.orden.primerApellido"/></label>:

							<html:text styleClass="obligatorio" size="15" maxlength="25" property="perApellido" value="${form.perApellido}" styleId="perApellido" /> 
							<html:hidden property="ascId2" value="${form.ascId2}" styleId="ascId2"/>
						</td>
						
					</tr>
					<tr>
					
					</tr>
					<tr>
						<td colspan="4" align="center">
							<div id="asociados">
							</div>
					    </td>
					</tr>
					<tr>
					<td colspan="4">
					<div align="center" style="text-align: center;">
					<input  type="checkbox" name="excel" /> Exportar a excel
					</div>
					</td>
					</tr>
				</table>
				<%-- CAMBIO DE TITULO --%>
				<script type="text/javascript">
					newTitulo = 'Reporte de Saldos de Ahorro';
				</script>
				<tr>
				
				</tr>
			</logic:equal>


<%--  --%>
	<logic:equal value="libroDiarioGeneral" parameter="p76e3123r">
	<script type="text/javascript">
					newTitulo = 'Libro Diario General';
				</script>
	<table
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 200px;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra">
		<tr>
		<th colspan="2">
			Fecha para la generaci&oacute;n
		</th>
		</tr>
		<tr class="odd">
		<td align="center">
			Mes:	
		</td>
		<td align="center">
<%
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
		String mes = sdf2.format(new Date());
 %>
						<select name="mes" style="width: 100px;">
							<option value="01" <%if(mes.equals("01")) out.write("selected = \"selected\"");%> >  
								Enero
							</option>
							<option value="02" <%if(mes.equals("02")) out.write("selected = \"selected\"");%>>
								Febrero
							</option>
							<option value="03" <%if(mes.equals("03")) out.write("selected = \"selected\"");%>>
								Marzo
							</option>
							<option value="04" <%if(mes.equals("04")) out.write("selected = \"selected\"");%>>
								Abril
							</option>
							<option value="05" <%if(mes.equals("05")) out.write("selected = \"selected\"");%>>
								Mayo
							</option>
							<option value="06" <%if(mes.equals("06")) out.write("selected = \"selected\"");%>>
								Junio
							</option>
							<option value="07" <%if(mes.equals("07")) out.write("selected = \"selected\"");%>>
								Julio
							</option>
							<option value="08" <%if(mes.equals("08")) out.write("selected = \"selected\"");%>>
								Agosto
							</option>
							<option value="09" <%if(mes.equals("09")) out.write("selected = \"selected\"");%>>
								Septiembre
							</option>
							<option value="10" <%if(mes.equals("10")) out.write("selected = \"selected\"");%>>
								Octubre
							</option>
							<option value="11" <%if(mes.equals("11")) out.write("selected = \"selected\"");%>>
								Noviembre
							</option>
							<option value="12" <%if(mes.equals("12")) out.write("selected = \"selected\"");%>>
								Diciembre
							</option>
						</select>
		</td>
		</tr>
		<tr>
		<td align="center">
			A&ntilde;o:	
		</td>
		<td align="center">
		<select name="anio" style="width: 100px">
		<%
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anio = sdf.format(new Date());		
		Integer anioActual = new Integer(anio);
		Integer inicial = anioActual-4;
		
		while(anioActual > inicial){
			out.print("<option value='"+anioActual.toString()+"'>"+anioActual.toString()+"</option>");
			anioActual--;
		}
		 %>
		</select>
		
		</td>
		</tr>
		</table>
	
	</logic:equal>




<%-- qen1 --%>
	<logic:equal value="q1" parameter="p76e3123r">
				<table
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 70%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra">
				<tr>
				<th colspan="4"><b><input type="radio" name="tipo" value="1" checked="checked"/> Aportaciones</b> </th>
				</tr>
				<tr>
				<th colspan="4"><b><input type="radio" name="tipo" value="2"/> Ahorro</b> </th>
				</tr>
				<tr>
					<td>
						<label>
							Linea Ahorro:
						</label>
					</td>
					<td>
						<html:select styleId="lineaAhorro" property="lahId">
						<html:option value="-1">- - Todas las lineas - -</html:option>
							<html:options collection="listaLineasAhorro" property="lahId"
								labelProperty="lahNombre" />
						</html:select>

					</td>
						
					<td>
						<label>
							Tipo Ahorro :
						</label>
					</td>
					
				<td>
					<div id="tipoAhorro">
						<html:select property="tahId" styleId="tipoAhorroId">
							<html:option value="-1">- - Todos los tipos - -</html:option>
							</html:select>
					</div>
				</td>
				<td align="right">
				</td>
				</tr>
				<tr>
				<th colspan="4"><b><input type="radio" name="tipo" value="3"/>Prestamos</b> </th>
				</tr>
				<tr>
					<td>
						<label>
							L&iacute;nea de Prestamo:
						</label>
					</td>
					<td colspan="3">

						<html:select styleId="lineaPrestamo" property="lprId">
							<html:option value="-1">------</html:option>
							<html:options collection="lineaPre" property="lprId"
								labelProperty="lprNombre" />
						</html:select>
					</td>
				</tr>
				<th colspan="4"><b><input type="radio" name="tipo" value="4"/>Seguros</b> </th>
				<tr>
					<td>
						<label>
							Tipo de seguro:
						</label>
					</td>
					<td colspan="4">
						<html:select styleId="tipoSeguro" property="tisId" style="width: 200px;">
						<html:option value="-1">Consolidado</html:option>
							<html:options collection="lineaSeg" property="tisId"
								labelProperty="tisNombre" />
						</html:select>
					</td>
				</tr>
				</table>
				<%-- CAMBIO DE TITULO --%>
				<script type="text/javascript">
					newTitulo = 'Reporte de movimientos de cuentas corrientes';
				</script>
				<tr>
				
				</tr>
				<table
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 70%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra">
		<th colspan="4"><b> Fecha</b> </th>
			<tr>
					<td>
						<label>
							Fecha de Inicio:
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fecha1"
							onkeyup="JavaScript:mask(this);" id="FechaIniId" />
						<input id="calendarR" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "FechaIniId",
				              button        : "calendarR",
				              align         : "Br"
				            });
				         </script>
					</td>
			
					<td>
						<label>
							Fecha final:
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fecha2"
							onkeyup="JavaScript:mask(this);" id="FechaFinId" />
						<input id="calendarM" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "FechaFinId",
				              button        : "calendarM",
				              align         : "Br"
				            });
				         </script>
					</td>
				</tr>
		</table>
		<table
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 70%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra">
		<tr>
		<th colspan="4"><b> Transaccion</b> </th>
		</tr>
		<tr>
		<td style="border-left: 1px dashed #69c"><label>Transacci&oacute;n</label></td>
		<td colspan="3">
					<html:select styleId="tipoTran" property="ttrId" style="width: 350px">
						<html:option value="-1">- - Consolidado - -</html:option>
							<html:options collection="listaTipoTran" property="ttrId"
								labelProperty="ttrNombre" />
						</html:select>
					
					
					</td>
				</tr>
		</table>
			</logic:equal>
<%-- saldos Al --%>


<logic:equal value="saldosAl" parameter="p76e3123r">
<table
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 70%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra">
		<th colspan="4"><b>Empresa: </b> </th>
			<tr>
					<td colspan="2">
						<label>
							Empresa :
						</label>
					</td>
					<td>
					<html:select styleId="empresaTrabajo" property="etrId">
						<html:option value="-1">- - Consolidado - -</html:option>
							<html:options collection="listaEmpresa" property="etrId"
								labelProperty="etrNombre" />
						</html:select>
					</td>
				</tr>
		</table>


				<table
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 70%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra">
				<tr>
				<th colspan="4"><b><input type="radio" name="tipo" value="1" checked="checked"/> Aportaciones</b> </th>
				</tr>
				<tr>
				<th colspan="4"><b><input type="radio" name="tipo" value="2"/> Ahorro</b> </th>
				</tr>
				<tr>
					<td>
						<label>
							Linea Ahorro:
						</label>
					</td>
					<td>
						<html:select styleId="lineaAhorro" property="lahId">
						<html:option value="-1">- - Todas las lineas - -</html:option>
							<html:options collection="listaLineasAhorro" property="lahId"
								labelProperty="lahNombre" />
						</html:select>

					</td>
						
					<td>
						<label>
							Tipo Ahorro :
						</label>
					</td>
					
				<td>
					<div id="tipoAhorro">
						<html:select property="tahId" styleId="tipoAhorroId">
							<html:option value="-1">- - Todos los tipos - -</html:option>
							</html:select>
					</div>
				</td>
				<td align="right">
				</td>
				</tr>
				<tr>
				<th colspan="4"><b><input type="radio" name="tipo" value="3"/>Prestamos</b> </th>
				</tr>
				<tr>
					<td>
						<label>
							L&iacute;nea de Prestamo:
						</label>
					</td>
					<td colspan="3">

						<html:select styleId="lineaPrestamo" property="lprId">
							<html:option value="-1">------</html:option>
							<html:options collection="lineaPre" property="lprId"
								labelProperty="lprNombre" />
						</html:select>
					</td>
				</tr>
				<th colspan="4"><b><input type="radio" name="tipo" value="4"/>Seguros</b> </th>
				<tr>
					<td>
						<label>
							Tipo de seguro:
						</label>
					</td>
					<td colspan="4">
						<html:select styleId="tipoSeguro" property="tisId" style="width: 200px;">
						<html:option value="-1">Consolidado</html:option>
							<html:options collection="lineaSeg" property="tisId"
								labelProperty="tisNombre" />
						</html:select>
					</td>
				</tr>
				</table>
				<%-- CAMBIO DE TITULO --%>
				<script type="text/javascript">
					newTitulo = 'Saldos de cuentas a una fecha';
				</script>
				<tr>
				
				</tr>
				<table
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 70%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra">
		<th colspan="4"><b>Fecha: </b> </th>
			<tr>
					<td colspan="2">
						<label>
							Fecha:
						</label>
					</td>
					<td colspan="2">
						<input type="text" maxlength="10" size="10" name="fecha"
							onkeyup="JavaScript:mask(this);" id="FechaIniId" />
						<input id="calendarR" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "FechaIniId",
				              button        : "calendarR",
				              align         : "Br"
				            });
				         </script>
					</td>
				</tr>
		</table>
	</logic:equal>
<%-- saldos al --%>









			<%-- REPORTE DE MOVMIENTOS DE AHORRO --%>

			<logic:equal value="movimientoDeAhorros" parameter="p76e3123r">
				<table
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 70%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra">
				<tr>
				<th colspan="2"> <b>Empresa</b> </th>
				<th colspan="2" style="border-left: 1px solid #69c"> <b>Transacci&oacute;n </b> </th>
				</tr>
				<tr>
					<td>
						<label>
							Empresa :
						</label>
					</td>
					<td>
					<html:select styleId="empresaTrabajo" property="etrId">
						<html:option value="-1">- - Consolidado - -</html:option>
							<html:options collection="listaEmpresa" property="etrId"
								labelProperty="etrNombre" />
						</html:select>
						
					</td>
					<td style="border-left: 1px dashed #69c"><label>Transacci&oacute;n</label></td>
					<td>
					<html:select styleId="tipoTran" property="ttrId" style="width: 150px">
						<html:option value="-1">- - Consolidado - -</html:option>
							<html:options collection="listaTipoTran" property="ttrId"
								labelProperty="ttrNombre" />
						</html:select>
					
					
					</td>
				</tr>
				<tr>
				<th colspan="4"><b>Ahorro</b> </th>
				</tr>
				<tr>
					<td>
						<label>
							Linea Ahorro:
						</label>
					</td>
					<td>
						<html:select styleId="lineaAhorro" property="lahId">
						<html:option value="-1">- - Todas las lineas - -</html:option>
							<html:options collection="listaLineasAhorro" property="lahId"
								labelProperty="lahNombre" />
						</html:select>

					</td>
						
					<td>
						<label>
							Tipo Ahorro :
						</label>
					</td>
					<td>
					<div id="tipoAhorro">
						<html:select property="tahId" styleId="tipoAhorroId">
							<html:option value="-1">- - Todos los tipos - -</html:option>
							</html:select>
					</div>
					</td>
					<td align="right">
				</td>
				</tr>
				<tr>
				<th colspan="4"><b>Asociado</b></th>
				</tr>
				<tr>
						<td>
							<label><bean:message key="lbl.desembolso.asociadoId"/></label>:
						</td>
						<td>
							<html:text styleClass="obligatorio" size="15" maxlength="13" property="asociadoId" value="${form.asociadoId}" styleId="asociadoIdId" /> 
						</td>
						<td colspan="2">
							<input type="checkbox" name="chkbx" id="chkbx" onclick="seleccion();" /> 
							<label><bean:message key="lbl.ordcom.codEmp" /></label>
							<html:hidden property="ascEmp" styleId="ascEmpId" value="1" />

							<input type="button" value="..." id="botonLoad"
							onclick="llamadaAjax($('#perNombreId').val(),$('#perApellido').val(),$('#ascEmpId').val(),$('#asociadoIdId').val());"/>
							<input type="button" value="X" id="botonClear"
							onclick="limpiarAsoc();"/>
					</td>
					</tr>
					<tr>
						<td colspan="2">
							<label>Nombres</label>:
						
							<html:text styleClass="obligatorio" size="15" maxlength="25" property="perNombre" value="${form.perNombre}" styleId="perNombreId" /> 
						</td>
						<td colspan="2">
							<label>Apellidos</label>:

							<html:text styleClass="obligatorio" size="15" maxlength="25" property="perApellido" value="${form.perApellido}" styleId="perApellido" /> 
							<html:hidden property="ascId2" value="${form.ascId2}" styleId="ascId2"/>
						</td>
						
					</tr>
					<tr>
					
					</tr>
					<tr>
						<td colspan="4" align="center">
							<div id="asociados">
							</div>
					    </td>
					</tr>
					<tr>
					<th colspan="4"><b>Opciones</b></th>
					</tr>
					<tr>
					<td colspan="2">
					<div align="center" style="text-align: center;">
					<input  type="checkbox" name="excel" /> Exportar a excel
					</div>
					</td>
					<td>
						<label>
							Fecha :
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="dia"
							onkeyup="JavaScript:mask(this);" id="dia002" />
						<input id="calendar" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "dia002",
				              button        : "calendar",
				              align         : "Br"
				            });
				         </script>

						<script type="text/javascript">
							newTitulo = 'Movimientos de Ahorro';
						</script>
					</td>
					</tr>
				</table>
			
				<tr>
					
				</tr>
			</logic:equal>

			<%-- REPORTE DE MOVIMIENTOS DE APORTACIONES --%>

			<logic:equal value="movimientoDeAportaciones" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Fecha :
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="dia"
							onkeyup="JavaScript:mask(this);" id="dia003" />
						<input id="calendar" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "dia003",
				              button        : "calendar",
				              align         : "Br"
				            });
				         </script>

						<script type="text/javascript">
							newTitulo = 'Movimientos de Aportaciones';
						</script>
					</td>
				</tr>
			</logic:equal>




			<%-- R E P O R T E S   D E   P R E S T A M O S --%>

			<%-- REPORTE DE saldos de  prestamos --%>

			<logic:equal value="ReporteSaldosDePrestamos" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Empresa :
						</label>
					</td>
					<td>
						<html:select styleId="empresaTrabajo" property="etrId">
							<html:option value="-1">------</html:option>
							<html:options collection="listaEmpresa" property="etrId"
								labelProperty="etrNombre" />
						</html:select>

						<%-- CAMBIO DE TITULO --%>

						<script type="text/javascript">
							newTitulo = 'Reporte de saldos de prestamos';
						</script>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							L&iacute;nea de Prestamo:
						</label>
					</td>
					<td>

						<html:select styleId="lineaPrestamo" property="lprId">
							<html:option value="-1">------</html:option>
							<html:options collection="lineaPre" property="lprId"
								labelProperty="lprNombre" />
						</html:select>
					</td>
				</tr>
				<html:hidden property="TipoPrestamoID" value="-1" />

			</logic:equal>


			<%-- REPORTE de prestamos por tipo de prestamo  --%>

			<logic:equal value="ReportePrestamosPorTipoPrestamo"
				parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Empresa :
						</label>
					</td>
					<td>
						<html:select styleId="empresaTrabajo" property="etrId" style="width: 250px;">
							<html:option value="-1">------</html:option>
							<html:options collection="listaEmpresa" property="etrId"
								labelProperty="etrNombre" />
						</html:select>

						<%-- CAMBIO DE TITULO --%>

						<script type="text/javascript">
							newTitulo = 'Reporte de saldos de prestamos';
						</script>
					</td>
				</tr>				
				<tr>
					<td>
						<label>
							L&iacute;nea de Prestamo:
						</label>
					</td>
					<td>

						<html:select styleId="lineaPrestamo" property="lprId" style="width: 250px;">
							<html:option value="-1">------</html:option>
							<html:options collection="lineaPre" property="lprId"
								labelProperty="lprNombre" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Mes:
						</label>
					</td>
					<td>
						<select name="mes" style="width: 100px;">
							<option value="-1">								
							</option>						
							<option value="1">
								Enero
							</option>
							<option value="2">
								Febrero
							</option>
							<option value="3">
								Marzo
							</option>
							<option value="4">
								Abril
							</option>
							<option value="5">
								Mayo
							</option>
							<option value="6">
								Junio
							</option>
							<option value="7">
								Julio
							</option>
							<option value="8">
								Agosto
							</option>
							<option value="9">
								Septiembre
							</option>
							<option value="10">
								Octubre
							</option>
							<option value="11">
								Noviembre
							</option>
							<option value="12">
								Diciembre
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							A&ntilde;o:
						</label>
					</td>
					<td>
						<input name="anio" maxlength="4" id="anioOtP" style="width: 100px;"/>
					</td>
				</tr>
				<tr>
					<td>
						<script type="text/javascript">
							newTitulo = 'Reporte de Otorgamiento de Pr&eacute;stamos';
						</script>
					</td>
				</tr>
			</logic:equal>


			<%-- REPORTE DE MOVIMIENTOS PRESTAMOS DEL DIA --%>

			<logic:equal value="MovimientoPrestamosDelDia" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Fecha :
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fecha"
							onkeyup="JavaScript:mask(this);" id="dia001" />
						<input id="calendar" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "dia001",
				              button        : "calendar",
				              align         : "Br"
				            });
				         </script>

						<script type="text/javascript">
							newTitulo = 'Movimientos Pr&eacute;stamos del D&iacute;a';
						</script>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Tipo Transacci&oacute;n :
						</label>
					</td>
					<td>
						<html:select styleId="tipoTransaccion" property="ttrId">
							<html:option value="-1">------</html:option>
							<html:options collection="tipoTran" property="ttrId"
								labelProperty="ttrNombre" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							L&iacute;nea Prestamo :
						</label>
					</td>
					<td>
						<html:select styleId="lineaPrestamo" property="lprId">
							<html:option value="-1">------</html:option>
							<html:options collection="lineaPre" property="lprId"
								labelProperty="lprNombre" />
						</html:select>
					</td>
					<html:hidden property="tprId" value="-1" />
				</tr>
				<tr>
					<td>
						<label>
							Nombre Empresa :
						</label>
					</td>
					<td>
						<input name="nombreEmpresa" type="text" />
					</td>
				</tr>

			</logic:equal>



			<%-- REPORTE DE LIQUIDACION SALDOS --%>

			<logic:equal value="cuentas_slave" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Fecha :
						</label>
					</td>
					<td>
						<input type="text" size="2" maxlength="2" title="Dia" />
						<select name="mes">
							<option value="1">
								Enero
							</option>
							<option value="2">
								Febrero
							</option>
							<option value="3">
								Marzo
							</option>
							<option value="4">
								Abril
							</option>
							<option value="5">
								Mayo
							</option>
							<option value="6">
								Junio
							</option>
							<option value="7">
								Julio
							</option>
							<option value="8">
								Agosto
							</option>
							<option value="9">
								Septiembre
							</option>
							<option value="10">
								Octubre
							</option>
							<option value="11">
								Noviembre
							</option>
							<option value="12">
								Diciembre
							</option>
						</select>
						<input type="text" size="4" maxlength="4" title="A&ntilde;o" />
					</td>
				</tr>
				<tr>
					<td>
						<label>
							C&oacute;digo Asociado
						</label>
					</td>
					<td>
						<input name="ascId" type="text" />
					</td>
					<td>
						<label>
							Fecha :
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fecha"
							onkeyup="JavaScript:mask(this);" id="dia007" />
						<input id="calendar3" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "dia007",
				              button        : "calendar3",
				              align         : "Br"
				            });
				         </script>

						<script type="text/javascript">
							newTitulo = 'Liquidaci&oacute;n Saldos';
						</script>
					</td>
				</tr>
			</logic:equal>



			<%-- REPORTE DE CLIENTES DE CUENTAS INACTIVAS RESUMEN --%>

			<logic:equal value="clientesCuentasInactivasResumen"
				parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Fecha :
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fecha"
							onkeyup="JavaScript:mask(this);" id="fecha" />
						<input id="calendar3" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fecha",
				              button        : "calendar3",
				              align         : "Br"
				            });
				         </script>

						<%-- CAMBIO DE TITULO --%>
						<script type="text/javascript">
							newTitulo = 'Cuadro Resumen de Cuentas de Asociados Inactivos';
						</script>
					</td>
				</tr>
			</logic:equal>






			<%-- REPORTE DE ESTADOS DE CUENTA --%>
			<logic:equal value="estadosCuentas" parameter="p76e3123r">
				<tr>
					<td>
						<bean:parameter id='c' name='ascId' />
						<input name="ascId" type="hidden" value="${c}" />
						<script type="text/javascript">
									newTitulo = 'Reporte de Estados de Cuenta';
								</script>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Fecha Inicio :
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fecha1"
							onkeyup="JavaScript:mask(this);" id="fecha1" />
						<input id="calendar1" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fecha1",
				              button        : "calendar1",
				              align         : "Br"
				            });
				         </script>
					</td>

					<td>
						<label>
							Fecha Fin :
						</label>
					</td>
					<td>

						<input type="text" maxlength="10" size="10" name="fecha2"
							onkeyup="JavaScript:mask(this);" id="fecha2" />
						<input id="calendar2" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fecha2",
				              button        : "calendar2",
				              align         : "Br"
				            });
				         </script>
					</td>
				</tr>
			</logic:equal>

			<%-- Desembolso de fondos --%>
<logic:equal value="abonosXcaja" parameter="p76e3123r">
</logic:equal>


			<%-- CIERRE DE ABONOS Por caja--%>
			<logic:equal value="abonosXcaja" parameter="p76e3123r">
				<tr>
					<td>
						<script type="text/javascript">
							newTitulo = 'Reporte de liquidacion de remesa diaria';
						</script>
					</td>
				</tr>
				<table
							style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
					font-size: 13px;
					margin-bottom: 20px;
					margin-top:20px;
					margin-left: auto;
					margin-right: auto;
					text-align: left;
					width: 90%;
					border-collapse: collapse;
					padding: 20px 20px 20px 20px;" id="hor-zebra">
				<tr>
					<th colspan="2">
						Liquidaci&oacute;n de remesa diaria
					</th>
				</tr>
				
					<tr style="margin: 10px 10px 10px 10px;" class="odd">
						<td>
							Fecha de reporte:
						</td>
						<td>
							<input type="text" maxlength="10" size="10" name="fecha"
								onkeyup="JavaScript:mask(this);" id="fechaZ" />
							<input id="calendarZ" type="button" value="...." />
							<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fechaZ",
				              button        : "calendarZ",
				              align         : "Br"
				            });
				         </script>
						</td>
					</tr>
					<tr style="margin: 10px 10px 10px 10px">
						<td>
							Sucursal:
						</td>
						<td>
							<html:select property="sucId">
								<html:option value="-1"> - - - Seleccione una sucursal - - - </html:option>
								<html:options collection="listaSuc" labelProperty="sucNombre"
									property="sucId" />
							</html:select>
						</td>
					</tr>
					<tr style="margin: 10px 10px 10px 10px">

					</tr>
					<td>
					</td>
					<tr style="margin: 10px 10px 10px 10px" class="odd"  >
						<td colspan="2">
							No. comprobante:
						</td>
					</tr>

					<tr style="margin: 10px 10px 10px 10px">
						<td align="right">
							<i>Inicio :</i>
						</td>
						<td align="left">
							<input type="text" name="c1">
						</td>
					</tr>
					<tr style="margin: 10px 10px 10px 10px">
						<td align="right">
							<i>Fin :</i>
						</td>
						<td align="left">
							<input type="text" name="c2">
						</td>
					</tr>
					<tr style="margin: 10px 10px 10px 10px">
						<td colspan="2">
						</td>
					</tr>
					<tr>
						<td>
						</td>
					<tr>
					<tr style="margin: 10px 10px 10px 10px" class="odd">
						<td>
							Elaborado por:
						</td>
						<td>
							<input type="text" name="solicita">
						</td>
					</tr>
					<tr style="margin: 10px 10px 10px 10px">
						<td>
							Autorizado por:
						</td>
						<td>
							<input type="text" name="autoriza">
						</td>
					</tr>
				</table>
	
			</logic:equal>

			<%-- REPORTE de cuentas inactivas por cliente --%>

			<logic:equal value="ReporteCuentasInactivasPorCliente"
				parameter="p76e3123r">
				<tr>
					<td colspan="2">
						<script type="text/javascript">
							newTitulo = 'Reporte de cuentas Inactivas de Asociados';
						</script>
					</td>

				</tr>

			</logic:equal>

			<%-- reporte por rango de cuentas--%>

			<logic:equal value="ReportePorRangoDeCuentas" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Empresa:
						</label>
					</td>
					<td>
						<html:select styleId="empresaTrabajo" property="etrId">
							<html:options collection="listaEmpresa" property="etrId"
								labelProperty="etrNombre" />
						</html:select>
					</td>			         
				</tr>
				<tr>
					<td>
						<label>
							Codigo inicial:
						</label>
					</td>
					<td>
						<html:text maxlength="15" size="15" property="rangoCuentaIni" />
					</td>
					<td>
						<label>
							Fecha Inicio:
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fecha1"
							onkeyup="JavaScript:mask(this);" id="fecha1" />
						<input id="calendar1" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fecha1",
				              button        : "calendar1",
				              align         : "Br"
				            });
				         </script>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Codigo final:
						</label>
					</td>					
					<td>
						<html:text maxlength="15" size="15" property="rangoCuentaFin" />
						<script type="text/javascript">
							newTitulo = 'Reporte por Rango de Cuentas';
						</script>							
					</td>
					<td>
						<label>
							Fecha Fin:
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fecha2"
							onkeyup="JavaScript:mask(this);" id="fecha2" />
						<input id="calendar2" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fecha2",
				              button        : "calendar2",
				              align         : "Br"
				            });
				         </script>
					</td>
				</tr>

			</logic:equal>


			<%-- reporte por tipo de garantia --%>

			<logic:equal value="ReportePorTipoGarantia" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Tipo de garantia:
						</label>
					</td>
					<td>
						<select name="tipoGarantiaId" id="tipoGarantia"
							class="fillajax 9 tipoGarantia">
						</select>
						<script type="text/javascript">
							newTitulo = 'Reporte por tipo de garant&iacute;a';
						</script>
					</td>
				</tr>
				<tr>
				<th colspan="4">Asociado</th>
				</tr>
				<tr>
						<td>
							<label><bean:message key="lbl.desembolso.asociadoId"/></label>:
						</td>
						<td>
							<html:text styleClass="obligatorio" size="15" maxlength="13" property="asociadoId" value="${form.asociadoId}" styleId="asociadoIdId" /> 
						</td>
						<td colspan="2">
							<input type="checkbox" name="chkbx" id="chkbx" onclick="seleccion();" /> 
							<label><bean:message key="lbl.ordcom.codEmp" /></label>
							<html:hidden property="ascEmp" styleId="ascEmpId" value="1" />

							<input type="button" value="..." id="botonLoad"
							onclick="llamadaAjax($('#perNombreId').val(),$('#perApellido').val(),$('#ascEmpId').val(),$('#asociadoIdId').val());"/>
							<input type="button" value="X" id="botonClear"
							onclick="limpiarAsoc();"/>
					</td>
					</tr>
					<tr>
						<td colspan="2">
							<label><bean:message key="lbl.orden.primerNombre"/></label>:
						
							<html:text styleClass="obligatorio" size="15" maxlength="25" property="perNombre" value="${form.perNombre}" styleId="perNombreId" /> 
						</td>
						<td colspan="2">
							<label><bean:message key="lbl.orden.primerApellido"/></label>:

							<html:text styleClass="obligatorio" size="15" maxlength="25" property="perApellido" value="${form.perApellido}" styleId="perApellido" /> 
							<html:hidden property="ascId2" value="${form.ascId2}" styleId="ascId2"/>
						</td>
						
					</tr>
					<tr>
					
					</tr>
					<tr>
						<td colspan="4" align="center">
							<div id="asociados">
							</div>
					    </td>
					</tr>
			</logic:equal>

			<%-- REPORTE DE PRESTAMOS EN MORA  --%>

			<logic:equal value="ReportePrestamosEnMora" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Numero de meses:
						</label>
					</td>
					<td>
						<input type="text" size="10" maxlength="10" title="Cuenta Inicio"
							name="NumMeses" />
						<script type="text/javascript">
							newTitulo = 'Reporte de prestamos en mora';
						</script>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							L&iacute;nea Prestamo :
						</label>
					</td>
					<td>
						<select name="lprId" id="lineaPrestamo"
							class="fillajax 10 lineaPrestamo">
						</select>
					</td>
				</tr>				

			</logic:equal>


			<%-- REPORTE DE CREDITOS CANCELADOS EN  EL MES --%>

			<logic:equal value="ReporteCreditosCanceladosEnMes"
				parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Empresa :
						</label>
					</td>
					<td>
						<select name="IDEmpresa" id="empresaTrabajo"
							class="fillajax 2 empresaTrabajo"></select>


						<%-- CAMBIO DE TITULO --%>
						<script type="text/javascript">
							newTitulo = 'Cr&eacute;ditos cancelados en el mes';
						</script>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							L&iacute;nea de Pr&eacute;stamo :
						</label>
					</td>
					<td>
						<select name="LineaPrestamo" id="lineaPrestamo"
							class="fillajax 10 lineaPrestamo">
						</select>
					</td>
				</tr>

				<tr>
					<td>
						<label>
							Fecha de Inicio:
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="FechaIni"
							onkeyup="JavaScript:mask(this);" id="FechaIniId" />
						<input id="calendarR" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "FechaIniId",
				              button        : "calendarR",
				              align         : "Br"
				            });
				         </script>
					</td>

				</tr>

				<tr>
					<td>
						<label>
							Fecha final:
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="FechaFin"
							onkeyup="JavaScript:mask(this);" id="FechaFinId" />
						<input id="calendarM" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "FechaFinId",
				              button        : "calendarM",
				              align         : "Br"
				            });
				         </script>
					</td>
				</tr>
			</logic:equal>




			<%-- R E P O R T E  S E G U R O S  --%>

			<%-- REPORTE RENOVACION SEGUROS --%>
			<logic:equal value="Reporte_Renovacion_Seguros" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Empresa:
						</label>
					</td>
					<td colspan="3">
						<html:select styleId="empresaTrabajo" property="etrId" style="width: 200px;">
						<html:option value="-1">Consolidado</html:option>
							<html:options collection="listaEmpresa" property="etrId"
								labelProperty="etrNombre" />
						</html:select>

						<%-- CAMBIO DE TITULO --%>
						<script type="text/javascript">
							newTitulo = 'Renovación de Seguros';
						</script>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Tipo de seguro:
						</label>
					</td>
					<td colspan="3">
						<html:select styleId="tipoSeguro" property="tisId" style="width: 200px;">
						<html:option value="-1">Consolidado</html:option>
							<html:options collection="lineaSeg" property="tisId"
								labelProperty="tisNombre" />
						</html:select>
					</td>
				</tr>
			</logic:equal>

			<%-- REPORTE SALDOS SEGUROS --%>
			<logic:equal value="Reporte_Saldo_Seguros" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Empresa:
						</label>
					</td>
					<td colspan="3">
						<html:select styleId="empresaTrabajo" property="etrId" style="width: 200px;">
						<html:option value="-1">Consolidado</html:option>
							<html:options collection="listaEmpresa" property="etrId"
								labelProperty="etrNombre" />
						</html:select>

						<%-- CAMBIO DE TITULO --%>
						<script type="text/javascript">
							newTitulo = 'Saldos de Seguros';
						</script>
					</td>
				</tr>
								<tr>
					<td>
						<label>
							Tipo de seguro:
						</label>
					</td>
					<td colspan="3">
						<html:select styleId="tipoSeguro" property="tisId" style="width: 200px;">
						<html:option value="-1">Consolidado</html:option>
							<html:options collection="lineaSeg" property="tisId"
								labelProperty="tisNombre" />
						</html:select>
					</td>
				</tr>
			</logic:equal>


			<%-- R E P O R T E   C O N T A B I L I D A D  --%>


			<%-- REPORTE LIBRO AUXILIAR MAYOR --%>

			<logic:equal value="libro_auxiliar_mayor" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Cuenta Contable Inicio :
						</label>
					</td>
					<td>
						<select name="CUENTA_INI" id="idCuenta"
							class="fillajax 4 idCuenta"></select>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Cuenta Contable Fin :
						</label>
					</td>
					<td>
						<select name="CUENTA_FIN" class="idCuenta"></select>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Fecha Fin:
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="FECHA_FIN_MOV"
							onkeyup="JavaScript:mask(this);" id="FECHA_FIN_M" />
						<input id="calendar2" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "FECHA_FIN_M",
				              button        : "calendar2",
				              align         : "Br"
				            });
				         </script>

						<script type="text/javascript">
							newTitulo = 'Reporte Libro Auxiliar Mayor';
						</script>
					</td>
				</tr>
			</logic:equal>


			<%-- REPORTE DE BALANZA DE COMPROBACION--%>

			<logic:equal value="balanceComprobacion" parameter="p76e3123r">
			 
			
				<tr>
					<td>
						<label>
							Seleccion el mes y a&ntilde;o:
						</label>
					</td>
					<td>
						<select name="mes">
							<option value="1">
								Enero
							</option>
							<option value="2">
								Febrero
							</option>
							<option value="3">
								Marzo
							</option>
							<option value="4">
								Abril
							</option>
							<option value="5">
								Mayo
							</option>
							<option value="6">
								Junio
							</option>
							<option value="7">
								Julio
							</option>
							<option value="8">
								Agosto
							</option>
							<option value="9">
								Septiembre
							</option>
							<option value="10">
								Octubre
							</option>
							<option value="11">
								Noviembre
							</option>
							<option value="12">
								Diciembre
							</option>
						</select>
						<input type="text" name="anio" maxlength="4" size="4" />
					</td>
				</tr>
				<tr>
					<td>
						<%-- CAMBIO DE TITULO --%>
						<script type="text/javascript">
							newTitulo = 'Balanza General de Comprobacion';
						</script>
					</td>
				</tr>
			</logic:equal>

			<%-- REPORTE DE CATALOGO DE CUENTAS --%>

			<logic:equal value="catalogo_cuentas" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Tipo de Cuenta :
						</label>
					</td>
					<td>

						<html:select styleId="ticId" property="tic_id">
							<html:option value="-1">Acumulado</html:option>
							<html:options collection="tipoCue" property="ticId"
								labelProperty="ticNombre" />
						</html:select>
						<%-- CAMBIO DE TITULO --%>
						<script type="text/javascript">
							newTitulo = 'Cat&aacute;logo de Cuentas';
						</script>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Mostrar Saldos
						</label>
					</td>
					<td>
						<select name="saldo" id="saldoId">
							<option value="1">
								Si
							</option>
							<option value="0">
								No
							</option>
						</select>

					</td>
				</tr>
			</logic:equal>
			
			

			<%-- REPORTE DE MOVIMIENTOS EN CUENTA CONTABLE --%>

			<logic:equal value="reporteMovEnCuentaContable" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Cuenta Contable Inicio :
						</label>
					</td>
					<td>
						<select name="CUENTA_INI" id="idCuenta"
							class="fillajax 4 idCuenta"></select>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Cuenta Contable Fin :
						</label>
					</td>
					<td>
						<select name="CUENTA_FIN" class="idCuenta"
							class="fillajax 4 idCuenta"></select>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Fecha Fin:
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="FECHA_FIN_MOV"
							onkeyup="JavaScript:mask(this);" id="FECHA_FIN_MOV" />
						<input id="calendarMakF" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "FECHA_FIN_MOV",
				              button        : "calendarMakF",
				              align         : "Br"
				            });
				         </script>

					</td>
				</tr>
			</logic:equal>

			<%-- REPORTE DE LIBRO MAYOR --%>

			<logic:equal value="libroMayor" parameter="p76e3123r">

				<tr>
					<td>
						<label>
							Fecha:
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fecha"
							onkeyup="JavaScript:mask(this);" id="FECHAmak" />
						<input id="calendarmak" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "FECHAmak",
				              button        : "calendarmak",
				              align         : "Br"
				            });
				         </script>

						<script type="text/javascript">
							newTitulo = 'LIBRO MAYOR';
						</script>
					</td>
				</tr>
			</logic:equal>


			<tr>
				<td align="center">
					<input name="accion" value="Reporte" type="submit" />
				</td>
			</tr>
		</tbody>
	</table>
	<bean:parameter id='a' name='p76e3123r' />
	<bean:parameter id='b' name='m009o8765d' />
	<input type="hidden" name="p76e3123r" value="${a }" />
	<input type="hidden" name="m009o8765d" value="${b }" />

</html:form>