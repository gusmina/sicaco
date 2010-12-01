<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<link rel="stylesheet" type="text/css" href="../css/zebra.css" >
<script type="text/javascript">
$(document).ready(function(){
   	$("#anio").numeric();
});   	   			

	
	function saveSeleccionC(valor) {
		valores = valor.split(';');
		$('#codCli').val(valores[0]);
		$('#perNombreId').val(valores[1]);
		$('#perApellido').val(valores[2]);
		$('#ascId2').val(valores[3]);
		$('#resultadoCli').hide('slow')
	};
	
	function llamadaAjax(nombre,apellido,ascEmp,asociadoId){
ajaxCallNormal('${pageContext.request.contextPath}/reporte/reportesOrden.do','accion=cargarAsociado&perNombre='+nombre+'&perApellido='+apellido+'&ascEmp='+ascEmp+'&asociadoId='+asociadoId,'asociados');
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
<div>
	<html:form action="${_accion}" method="post" styleId="formId" >

		<table border="0" id="tabla">
			<tr>
				<td colspan="2" align="center">
					<label  ><b><u>		${form.nombre}		</u></b></label>
				</td>
			</tr>
			<logic:present  name="filtro3" scope="request"><!-- CAMPOS= textAuxTitulo -->
				<logic:equal value="1" name="filtro3">
					<!-- CAMPOS= textAuxTitulo  -->
					<tr>
						<td>
							<label>
								<bean:message key="lbl.reportes.textAuxTitulo" />
							</label>
						</td>
						<td>
							<html:textarea property="txtAuxiliar"  rows="2" />
						</td>
					</tr>
				</logic:equal>
				<logic:equal value="2" name="filtro3">
					<!-- CAMPOS= lista de lineas  -->
					<tr>
					<td>
						<label>
								<bean:message key="lbl.reportes.linea" />
							</label>
					</td>
					<td>
					<html:select property="linId" value="${form.linId}">
								<!-- styleClass="obligatorio" -->
								<html:option value="-1">Consolidado</html:option>
								<html:optionsCollection label="linNombre"
									name="listaLinea" value="linId" />
					</html:select>
					</td>
					</tr>
				</logic:equal>
				<logic:equal value="3" name="filtro3">
					<!-- CAMPOS= lista de bodegas  -->
					<tr>
					<td>
						<label>
								<bean:message key="lbl.reportes.bodega" />
							</label>
					</td>
					<td>
					<html:select property="bodId" value="${form.bodId}">
								<!-- styleClass="obligatorio" -->
								<html:option value="-1">Todas las bodegas</html:option>
								<html:optionsCollection label="bodNombre"
									name="listaBodegas" value="bodId" />
					</html:select>
					</td>
					</tr>
				</logic:equal>
				<logic:equal value="4" name="filtro3">
				
					<!-- CAMPOS= Empresa  -->
					<tr>
						<td>
							<label>
								Empresa:
							</label>
						</td>
						<td>
							<html:select property="etrId" value="${form.etrId}">
								<html:optionsCollection label="etrNombre"
									name="listaEmpresa" value="etrId" style="width: 200px"/>
							</html:select>
						</td>
					</tr>
				</logic:equal>
			</logic:present>
			<logic:equal value="filtroRubro" name="filtroRubro" >
			<tr>
						<td>
							<label>
								Rubro de la cuenta:
							</label>
						</td>
						<td>
							<select style="width: 200px" name="rubro">
								<option value="-1"> - - Todos los rubros - - </option>
								<option value="1"> Aportaciones </option>
								<option value="2"> Ahorros</option>
								<option value="3"> Seguros</option>
							</select>
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
				<th colspan="4"><b>Asociado</b></th>
				</tr>
				<tr>
						<td>
							<label><bean:message key="lbl.desembolso.asociadoId"/></label>:
						</td>
						<td>
							<html:text styleClass="obligatorio" size="15" maxlength="13" property="asociadoId" value="${form.asociadoId}" styleId="asociadoIdId" /> 
						</td>
						<td colspan="2"><label>
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
					<tr>
					</tr>
				</table>
			</logic:equal>
			<logic:present name="filtro" scope="request">
				<logic:equal value="1" name="filtro"><!-- CAMPOS= fecha, sumario -->
					<tr>
						<td><label>
							<bean:message key="lbl.reportes.fechaReporte" />
							</label>
						</td>
						<td>
							<html:text onkeyup="mask(this);" property="fechaReporte" value="${form.fechaReporte}"
								styleClass="obligatorio" maxlength="10" size="10" styleId="fechaReporteId"/>
								
							<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaReporteD" type="button" value="...." />
								<script type="text/javascript">
						            Calendar.setup({
						              inputField    : "fechaReporteId",
						              button        : "button_fechaReporteD",
						              align         : "Br"
						            });
						         </script>
						</td>
					</tr>
					<tr>
						<td>
							<label>
								<bean:message key="lbl.reportes.textSumario" />
							</label>
						</td>
						<td>
							<html:textarea property="txtSumario" rows="2"/>
						</td>
					</tr>
				</logic:equal>
				<logic:equal value="2" name="filtro"><!-- CAMPOS= fecha_ini,fecha_fin -->
					<tr>
						<td>
							<label>
								<bean:message key="lbl.reportes.fechaIni" />
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
								<bean:message key="lbl.reportes.fechaFin" />
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
				<logic:equal value="3" name="filtro">
					<!-- CAMPOS= mes, anio, fecha_de_presentacion -->
					<tr>
						<td>
							<label>
								<bean:message key="lbl.reportes.mes" />
							</label>
						</td>
						<td>
							<html:select property="mes" styleClass="obligatorio"
								value="${form.mes}">
								<html:option value="1">ENERO</html:option>
								<html:option value="2">FEBRERO</html:option>
								<html:option value="3">MARZO</html:option>
								<html:option value="4">ABRIL</html:option>
								<html:option value="5">MAYO</html:option>
								<html:option value="6">JUNIO</html:option>
								<html:option value="7">JULIO</html:option>
								<html:option value="8">AGOSTO</html:option>
								<html:option value="9">SEPTIEMBRE</html:option>
								<html:option value="10">OCTUBRE</html:option>
								<html:option value="11">NOVIEMBRE</html:option>
								<html:option value="12">DICIEMBRE</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td>
							<label>
								<bean:message key="lbl.reportes.anio" />
							</label>
						</td>
						<td>
							<html:text property="anio" styleClass="obligatorio" size="5"
								maxlength="4" />
						</td>
					</tr>
					<tr>
						<td>
							<label>
								<bean:message key="lbl.reportes.fechaPresentacion" />
							</label>
						</td>
						<td>
							<html:text property="fechaDePresentacion" value="${form.fechaDePresentacion}"
								styleClass="obligatorio"  styleId="fechaPres"/>
							<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaPreset" type="button" value="...." />
						</td>
					</tr>
					<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fechaPres",
				              button        : "button_fechaPreset",
				              align         : "Br"
				            });
				            function handlerCombo(){
				            	$(fechaPres).toggle();
				            }

    				</script>
				</logic:equal>
				<logic:equal value="4" name="filtro"><!-- CAMPOS= fechaIni, fechaFin, tipoDocumento -->
					<tr>
						<td>
							<label>
								<bean:message key="lbl.reportes.fechaIni" />
							</label>
						</td>
						<td>
							<html:text onkeyup="mask(this);" property="fechaIni" value="${form.fechaIni}"
								styleClass="obligatorio" maxlength="10" size="10"  styleId="fechaIni4"/>
							<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaIni4" type="button" value="...." />
						</td>
					</tr>
					<tr>
						<td>
							<label>
								<bean:message key="lbl.reportes.fechaFin" />
							</label>
						</td>
						<td>
							<html:text onkeyup="mask(this);" property="fechaFin" value="${form.fechaFin}"
								styleClass="obligatorio" maxlength="10" size="10"  styleId="fechaFin4"/>
							<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaFin4" type="button" value="...." />
						</td>
						<script type="text/javascript">
           	    			Calendar.setup({
		             		 inputField	: "fechaIni4",
		            		  button		: "button_fechaIni4",
		        		      align			: "Br"
		            		})
		           			Calendar.setup({
		              			inputField	: "fechaFin4",
		              			button		: "button_fechaFin4",
		              			align			: "Br"
		            		})
		            		function handlerCombo(){
				            	$(fechaIni4).toggle();
				            }
				    		function handlerCombo(){
				            	$(fechaFin4).toggle();
				            }
                		</script>
					</tr>
					<tr>
						<td>
							<label>
								<bean:message key="lbl.reportes.tipoDocumento" />
							</label>
						</td>
						<td>
							<html:select property="tipoDeDocumento" styleClass="obligatorio"
								value="${form.tipoDeDocumento}">
								<html:option value="AC">Consolidado</html:option>
								<html:option value="CO">Consumidor Final</html:option>
								<html:option value="VC">Cr&eacute;dito Fiscal</html:option>
							</html:select>
						</td>
					</tr>
						
				</logic:equal>
				<logic:equal value="777" name="filtro">
				
				<table
					style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
					font-size: 13px;
					margin-bottom: 20px;
					margin-top:20px;
					margin-left: auto;
					margin-right: auto;
					text-align: left;
					width: auto;
					border-collapse: collapse;
					padding: 20px 20px 20px 20px;" id="hor-zebra"
				>
			  <tr>
			    <th colspan="2" align="center">Fecha de ejecucion de la planilla</th>
			  </tr>
			  <tr class="odd">
			    <td align="left">Fecha: </td>
			    <td align="right">
			    	<html:text onkeyup="mask(this);" property="fechaReporte" value="${form.fechaReporte}"
							   styleClass="obligatorio" maxlength="10" size="10" styleId="fechaReporteId"/>
						<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaReporteD" type="button" value="...." />
								<script type="text/javascript">
							            Calendar.setup({
							              inputField    : "fechaReporteId",
							              button        : "button_fechaReporteD",
							              align         : "Br"
							            });
						         </script>
			    </td>
			  </tr>
			  
			  <tr>
						<td>
							<label>
								Empresa:
							</label>
						</td>
						<td>
							<html:select property="etrId" value="${form.etrId}">
								<html:optionsCollection label="etrNombre"
									name="listaEmpresa" value="etrId" style="width: 200px"/>
							</html:select>
						</td>
					</tr>
					<tr class="odd">
						<td colspan="2" align="center">
						<input type="checkbox" checked="checked" name="excel">Generar unicamente resumenes</input>
						</td>
					</tr>
			  
			</table>

				</logic:equal>
				
				<logic:equal value="5" name="filtro">
				<!-- Libro Auxiliar  -->
				<table>

					<tr>
						<td>
						<label>
							<bean:message key="lbl.reportes.fechaReporte" />
						</label>
						</td>
						<td>
							<html:text onkeyup="mask(this);" property="fechaReporte" value="${form.fechaReporte}"
								styleClass="obligatorio" maxlength="10" size="10" styleId="fechaReporteId"/>
								
							<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaReporteD" type="button" value="...." />
								<script type="text/javascript">
						            Calendar.setup({
						              inputField    : "fechaReporteId",
						              button        : "button_fechaReporteD",
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
			</logic:present>
			<logic:present name="filtro2" scope="request"><!-- CAMPOS= sucursal -->
				<logic:equal value="1" name="filtro2">
					<tr>
						<td>
							<label>
								<bean:message key="lbl.reportes.sucursal" />
							</label>
						</td>
						<td>
							<html:select property="sucursal" value="${form.sucursal}">
								<!-- styleClass="obligatorio" -->
								<html:option value="-1">Consolidado</html:option>
								<html:optionsCollection label="sucNombre"
									name="listaSecSucSucursal" value="sucId" />
							</html:select>
						</td>
					</tr>
				</logic:equal>
			</logic:present>
			<logic:equal value="balanceComprobacion" parameter="p76e3123r">
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
			
			<logic:equal value="ESTADO_RESULTADOS" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Generar estado de resultado acumulado:
						</label>
					</td>
					<td>
						<select name="mes">
							<option value="1">Si</option>
							<option value="0">No</option>
						</select>
					</td>
				</tr> 
				<tr>
					<td>
						<label>
							Fecha:
						</label>
					</td>
					<td>
						<html:text property="fechaIni" value="${form.fechaIni}"
								styleClass="obligatorio"  styleId="fechaEstado"/>
						<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaEstado" type="button" value="...." />
					</td>
				</tr> 
					<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fechaEstado",
				              button        : "button_fechaEstado",
				              align         : "Br"
				            });
				            function handlerCombo(){
				            	$(fechaPres).toggle();
				            }

    				</script>
			</logic:equal>
			<logic:equal value="balance_general" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							A la Fecha:
						</label>
					</td>
					<td>
						<html:text property="fechaIni" value="${form.fechaIni}"
								styleClass="obligatorio"  styleId="fechaBalance"/>
						<input style="height: 18px; position: relative; left: -0.2em;"
								id="button_fechaBalance" type="button" value="...." />
					</td>
				</tr> 
					<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fechaBalance",
				              button        : "button_fechaBalance",
				              align         : "Br"
				            });
				            function handlerCombo(){
				            	$(fechaPres).toggle();
				            }

    				</script>
			</logic:equal>
			
			<logic:equal value="reporteMovEnCuentaContable" parameter="p76e3123r">
				<tr>
					<td>
						<label>
							Cuenta Contable Inicio :
						</label>
					</td>
					<td>
						<html:select property="cueId1" value="${form.cueId1}">
							<!-- styleClass="obligatorio" -->
							<html:optionsCollection label="cueNombre"
									name="listaConCueCuenta" value="cueCodigoCuenta" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Cuenta Contable Fin :
						</label>
					</td>
					<td>
						<html:select property="cueId2" value="${form.cueId2}">
							<!-- styleClass="obligatorio" -->
							<html:optionsCollection label="cueNombre"
									name="listaConCueCuenta" value="cueCodigoCuenta" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Fecha Inicio:
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fechaIni"
							onkeyup="JavaScript:mask(this);" id="fechaIni" style="obligatorio"/>
						<input id="calendarMakI" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fechaIni",
				              button        : "calendarMakI",
				              align         : "Br"
				            });
				         </script>

					</td>
				</tr>				
				<tr>
					<td>
						<label>
							Fecha Fin:
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fechaReporte"
							onkeyup="JavaScript:mask(this);" id="fechaReporte" style="obligatorio"/>
						<input id="calendarMakF" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fechaReporte",
				              button        : "calendarMakF",
				              align         : "Br"
				            });
				         </script>

					</td>
				</tr>
			</logic:equal>
			<logic:equal value="libro_auxiliar_mayor" parameter="p76e3123r">
				
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
		<th colspan= "2">
		Libro Auxiliar 
		</th>
		</tr>
				<tr>
					<td>
						<label>
							Fecha Fin:
						</label>
					</td>
					<td>
						<input type="text" maxlength="10" size="10" name="fechaReporte"
							onkeyup="JavaScript:mask(this);" id="fechaReporte" />
						<input id="calendarMakF" type="button" value="...." />
						<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "fechaReporte",
				              button        : "calendarMakF",
				              align         : "Br"
				            });
				         </script>

					</td>
				</tr>
			</table>
			</logic:equal>
			
<logic:equal value="libro_mayor_gral" parameter="p76e3123r">
	<script type="text/javascript">
					newTitulo = 'Libro Diario Mayor General';
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
		Integer inicial = anioActual-5;
		
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
			
			<tr>
				<td colspan="2" align="center">
					<html:submit property="accion" >
						<bean:message key="cmd.reportes.generarReporte" />
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