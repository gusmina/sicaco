<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<link rel="stylesheet" type="text/css" href="../css/zebra.css" >
<script type="text/javascript">
<!--
$(document).ready(function(){
 		$("#pcoComprobantePartida").numeric();
 		$("#nuevaPartida").hide();
});

function showOrHideReportControls(){		
		cleanFields('formId');
		var showing = $("#showing").val();
		if (showing == "false" ){
			showing= "true";
			$("#nuevaPartida").slideDown("slow");
			$("#controles").slideUp("slow");
			$("#showOrHideButton").val("Busqueda de partida...");
		}else{
			showing= "false";
			$("#nuevaPartida").slideUp("slow");
			$("#controles").slideDown("slow");
			
			$("#showOrHideButton").val("Nueva Partida ...");			
		}
		$("#showing").val(showing);
	}

//-->


</script>


<html:form action="${_accion}" method="post" styleId="formId">
<input id="showing" type="hidden" value="false"/>
<div id="controles"> 
	<table align="center"
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 45%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra">
		<tr>
			<th colspan="2">
					Datos de busqueda de partidas: 
			</th>
		</tr>
		<tr>
			<td>
					<bean:message key="lbl.pco.fechaInicio"/> 
			</td>
			<td>
			<html:text onkeyup="mask(this);" property="pcoFechaIngresoPartida"  styleId="pcoFechaIngresoPartida"
						maxlength="10" size="10" styleClass="obligatorio" />
			<input style="height: 18px; position: relative; left: -0.2em;"
						id="button_pcoFechaIngresoPartida" type="button" value="...." />		
				<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "pcoFechaIngresoPartida",
				              button        : "button_pcoFechaIngresoPartida",
				              align         : "Br"
				            });
				            function handlerCombo(){
				            	$(pcoFechaIngresoPartida).toggle();
				            }
   			</script>
			</td>
		</tr>
				<tr>
			<td>
					<bean:message key="lbl.pco.fechaFin"/> 
			</td>
			<td>
			<html:text onkeyup="mask(this);" property="fechaFin"  styleId="fechaFin"
						maxlength="10" size="10" styleClass="obligatorio"/>
			<input style="height: 18px; position: relative; left: -0.2em;"
						id="button_fechaFin" type="button" value="...." />		
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
			</td>
		</tr>
		<tr>
			<td>
					<bean:message key="lbl.pco.numeroPartida"/> 
			</td>
			<td>
				<html:text  property="pcoComprobantePartida"  styleId="pcoComprobantePartida" styleClass="obligatorio"/>
			</td>
		</tr>
		<tr>
			<td>
					<bean:message key="lbl.pco.conceptoPartida"/> 
			</td>
			<td>
				<html:select property="conCpaConceptoPartida.cpaId" styleClass="obligatorio">
					<html:option value=""></html:option>
					<html:option value="-1">Otro Concepto</html:option>
					<html:optionsCollection name="conceptos" value="cpaId" label="cpaConcepto"/>
				</html:select>
			</td>
		</tr>
			<tr>
			<td>
					<bean:message key="lbl.pco.tipoPartida"/> 
			</td>
			<td>
				<html:select property="conTpaTipoPartida.tpaId">
								<html:option value="-1"> Escoja un tipo </html:option>
					<html:optionsCollection name="tipos" value="tpaId" label="tpaNombre"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:submit property="accion" >
					<bean:message key="cmd.pco.buscar" />
				</html:submit>
				
			</td>
				<td align="center">
				<input type="button" value="Limpiar"
					onclick="cleanFields('formId');">
			</td>
		</tr>
	</table>
	</div>
	
<table align="center"
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 45%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra">

	<tr>
	<td>
	<input type="button" value="Nueva partida..." onclick="showOrHideReportControls();" id="showOrHideButton"/>
	</td>
	</tr>
</table>	
<div id="nuevaPartida" >
	
		<table align="center"
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 45%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra">
		<tr>
			<th colspan="2">Datos de la nueva partida </th>
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
		<tr>
		<td  colspan="2" align="center">
				<html:submit property="accion">
					<bean:message key="cmd.pco.forwardToNuevaPartida" />
				</html:submit>
		</td>
			</tr>
		</table>
	</div>
	
	
</html:form>
