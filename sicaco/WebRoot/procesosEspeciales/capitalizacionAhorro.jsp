<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="/capitalizacionAhorro" method="POST" styleClass="ajaxForm" styleId="formId">
	<table>
		<tr>
			<td>
				<label>Ingrese Fecha L&iacute;mite de Capitalizaci&oacute;n:</label>
			</td>
			<td>
				<html:text onkeyup="JavaScript:mask(this);" property="fechaCapitalizacion" value="${form.fechaCapitalizacion}" maxlength="10" size="10" styleId="fechaCapitalizacionId" />
				<input id="fechaCapitalizacionButton" type="button" value="...." />
					<script type="text/javascript">
			            Calendar.setup({
			              inputField    : "fechaCapitalizacionId",
			              button        : "fechaCapitalizacionButton",
			              align         : "Br"
			            });
			         </script>
			</td>
		</tr>
		<tr>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.capitalizacionAhorro.capitalizar"/>
				</html:submit>			
			</td>
		</tr>
	</table>
	
	
	<script type="text/javascript">
		$(document).ready(function() {
			//convertimos los formularios a formularios ajax
			var v = $('.ajaxForm').ajaxForm(opcionesAjaxForm)
		})
	</script>
</html:form>

<script type="text/javascript">
	
	var opcionesAjaxForm = {
		success:retorno,
		error:errorf
	}
	
	//function retorno(responseText, statusText) {
	//	var newText=$('#formId',responseText).html()
	//	$('#formId').html(newText).show()
//	}		
	
	function retorno(responseText, statusText) {
		$('#formId').after('<div id="mesaje"></div>')
		$('#mesaje').hide()
		$('#mesaje').addClass($('class',responseText).text())
		$('#mesaje').append($('texto',responseText).text())
		$('#mesaje').show('slow')
		
		
		//Eliminamos el mensaje despues de cierto tiempo
		 
	}		
	
	function eliminarMensaje() {
		$('#mesaje').hide('slow',function() {
			$('#mesaje').remove()
		})
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
	$().ajaxStop($.unblockUI); 
</script>