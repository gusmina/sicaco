<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<style>
	.mydata{
		font-size:12px;
	}
	.mydata thead {
		font-weight: bolder;
		font-size:11px;
	}
	.mydata tbody {
		font-size:10px;
	}
	
	.mydata tr {
		border: 1px solid black;
	}
</style>

<html:form action="/distribucionDividendos" method="POST" styleClass="ajaxForm" styleId="formId">
	<logic:present name="cuentas">
		<center>
		<table class="mydata" border="1px" cellpadding="5" cellspacing="1">
			<thead>
				<tr>
					<td align="center" valign="top">
						<bean:message key="lbl.distribucionDividendos.seleccionar"/>
					</td>
					<td align="center" valign="top">
						<bean:message key="lbl.distribucionDividendos.nombreCuenta" />
					</td>
					<td align="center" valign="top">
						<bean:message key="lbl.distribucionDividendos.descripcionCuenta" />
					</td>
					<td align="center" valign="top">
						<bean:message key="lbl.distribucionDividendos.distribucion" />
					</td>
				</tr>
			</thead>
			<tbody>
			<logic:iterate id="tipoCuenta" name="cuentas" indexId="i">
				<tr>
					<td align="center">
						<input type="checkbox" value="${ i}" name="selectedItems"/>
						<input type="hidden" value="${tipoCuenta.tahId}" name="ticIds" />
					</td>
					<td align="left">
						${tipoCuenta.tahNombre}
					</td>
					<td align="center">
						${tipoCuenta.tahDescripcion}
					</td>
					<td>
						<input type="text" size="8" maxlength="8" name="porcentaje" />
					</td>
				</tr>
			</logic:iterate>
			</tbody>
		</table>
		</center>
	</logic:present>
	<center>
	<br/>
	<table>
		<tbody>
			<tr>
				<td align="right">
					<label for="totalExcedentes">
						<bean:message key="lbl.distribucionDividendos.totalExcedentes"/> :
					</label>
				</td>
				<td colspan="2">
					<html:text property="totalExcedentes" maxlength="10" size="10" styleId="totalExcedentes" />
				</td>
			</tr>
			<tr>
				<td align="right">
					<label for="fechaDistribucion"><bean:message key="lbl.distribucionDividendos.fecha"/> :</label>
				</td>
				<td colspan="2">
					<html:text onkeyup="JavaScript:mask(this);" property="fechaDistribucion" maxlength="10" size="10" styleId="fechaDistribucion" />
					<input id="fechaDistribucionButton" type="button" value="...." />
					<script type="text/javascript">
			            Calendar.setup({
			              inputField    : "fechaDistribucion",
			              button        : "fechaDistribucionButton",
			              align         : "Br"
			            });
			         </script>
				</td>
			</tr>
			<tr>
				<td align="right">
					<html:radio property="tipoDistribucion" value="1"/>
				</td>
				<td>
					<label><bean:message key="lbl.distribucionDividendos.distribuirAportaciones"/> </label>
				</td>
				<td align="right">
					<html:radio property="tipoDistribucion" value="2"/>
				</td>
				<td>
					<label><bean:message key="lbl.distribucionDividendos.distribuirAportacionesIntereses"/> </label>
				</td>
			</tr>
		</tbody>
	</table>
	</center>
	<br/>
	<center>
	  <html:submit property="accion" styleId="distribucion">
			<bean:message key="cmd.distribucionDividendos.distribucion"/>
		</html:submit>
		<html:button property="accion" onclick="JavaScript:imprimirReporte(this);">
			<bean:message key="cmd.distribucionDividendos.reporteDistribucion"/>
		</html:button>
		<html:submit property="accion">
			<bean:message key="cmd.distribucionDividendos.asignacion"/>
		</html:submit>
	</center>
	<script type="text/javascript">
		$(document).ready(function() {
			//convertimos los formularios a formularios ajax
			var v = $('.ajaxForm').ajaxForm(opcionesAjaxForm)
			$('input[name="porcentaje"]').blur(function(){
				
				if($(this).val()!='') {
					$(this).parent().prev().prev().prev().children('input[type="checkbox"]').attr('checked','checked')
				}else {
					$(this).parent().prev().prev().prev().children('input[type="checkbox"]').removeAttr('checked')
				}
			})
			
			$('input[type="checkbox"]').click(function() {
				if(!($(this).attr('checked')=='false')) {
					$(this).parent().next().next().next().children('input[type="text"]').val('')
				}
			})
		})
	</script>
</html:form>

<script type="text/javascript">
	
	var opcionesAjaxForm = {
		beforeSubmit:antesDeEnviar,
		success:retorno,
		error:errorf,
		dataType:'xml'
	}
	
	function antesDeEnviar(formData, jqForm, options) {
		var d = false
		
		//Verificamos que la sumatoria de todos los costos sea de 100%
		var suma = 0
		$('input[name="porcentaje"]').each(function() {
			suma = suma+($(this).val()/1)
		})
		
		if(suma==100) {
			bloquearPantalla()
			d = true
		}else {
			alert('La sumatoria de porcentajes debe de ser igual a 100% ahorita es de: ' + suma+'%')
		}
		
		return d
	}
	
	function eliminarMensaje() {
		$('#mesaje').hide('slow',function() {
			$('#mesaje').remove()
		})
	}
	
	function retorno(responseText, statusText) {
		$('#formId').after('<div id="mesaje"></div>')
		$('#mesaje').hide()
		$('#mesaje').addClass($('class',responseText).text())
		$('#mesaje').append($('texto',responseText).text())
		$('#mesaje').show('slow')
		
		//Bloqueamos la opcion de volver a generar la distribucion si fue exitosa
		
		if($('class',responseText).text()=='exito')
			$('#distribucion').attr('disabled','disabled')
		
		//Eliminamos el mensaje despues de cierto tiempo
		setTimeout(eliminarMensaje, 6000); 
	}		
	
	function errorf(XMLHttpRequest, textStatus, errorThrown) {
		
	}
	
	function bloquearPantalla() {
		$.blockUI({ css: { 
			            border: 'none', 
			            padding: '15px', 
			            backgroundColor: '#000', 
			            '-webkit-border-radius': '10px', 
			            '-moz-border-radius': '10px', 
			            opacity: '.5', 
			            color: '#fff' 
			        } ,message: '<h1 style="color:#FFFFFF">Por Favor espere mientras se procesa su petici&oacute;n...</h1>'});
	}
	
	function desbloquearPantalla() {
		$.unblockUI
	}
	
	function selectCheckbox(control) {
		$control = $(control)
		$control.parent().parent().next('input:checkbox').attr('checked','checked')
	}
	$().ajaxStop($.unblockUI);
	
	function imprimirReporte(boton) {
		var $form = $('#formId')
		var parametros = $form.formSerialize()+'&'+$(boton).attr('name')+'='+$(boton).val()
		var direc = $form.attr('action')+'?'+parametros
		document.location.href=direc
	}
</script>