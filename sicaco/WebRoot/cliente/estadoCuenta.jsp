<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<link rel="stylesheet" type="text/css" href="../css/zebra.css" >
<script type="text/javascript">
<!--
	$(document).ready(function(){
		$("#fecha1").numeric({allow:"-"});
		$("#fecha2").numeric({allow:"-"});
	});

function compareDate(dateA, dateB){
 	var timeDifference = dateA - dateB;
   	if (timeDifference > 0)
      return 1;
   else if (timeDifference < 0)
      return -1;
   else
      return 0;
	}

	function handlerDeleteButton(id){
		var error = 0; 
		if($('#fecha1').val() == '' && $('#fecha2').val() != '' ){
		error=1;
			var msgText = '<h3 style="color: #900000;">Error</h3>'+
    		'<p style="font-size: 1 em; font-weight: bold;">Debe ingresar una fecha de inicio.<br></p>';
    		$.prompt(msgText,{  prefix:'brownJqi'});
		}
		if($('#fecha2').val() == '' && $('#fecha1').val() != ''){
			error=1;
			var msgText = '<h3 style="color: #900000;">Error</h3>'+
    		'<p style="font-size: 1 em; font-weight: bold;">Debe ingresar una fecha de fin.<br></p>';
    		$.prompt(msgText,{  prefix:'brownJqi'});
		}
				if($('#fecha1').val() == '' && $('#fecha2').val() != '' ){
		error=1;
			var msgText = '<h3 style="color: #900000;">Error</h3>'+
    		'<p style="font-size: 1 em; font-weight: bold;">La fecha de inicio no puede ser vacia<br></p>';
    		$.prompt(msgText,{  prefix:'brownJqi'});
		}
		if($('#fecha2').val() == '' && $('#fecha1').val() == ''){
			error=1;
			var msgText = '<h3 style="color: #900000;">Error</h3>'+
    		'<p style="font-size: 1 em; font-weight: bold;">Debe ingresar una fecha de inicio y una fecha de fin.<br></p>';
    		$.prompt(msgText,{  prefix:'brownJqi'});
		}
	var arrayFecha1 = $('#fecha1').val().split('-');
	var arrayFecha2 = $('#fecha2').val().split('-');
	
	var d1 = arrayFecha1[0]/1;
	var m1 = (arrayFecha1[1]/1)-1;//los meses inician en 0
	
	var y1 = arrayFecha1[2]/1;
	var f1 = new Date(y1,m1,d1);
	var d2 = arrayFecha2[0]/1;
	var m2 = (arrayFecha2[1]/1)-1;//los meses inician en 0
	var y2 = (arrayFecha2[2]/1);
	var f2 = new Date(y2,m2,d2);
	
	  if(compareDate(f1,f2)==1){
			error=1;
			var msgText = '<h3 style="color: #900000  ;">Error</h3>'+
    		'<p style="font-size: 1 em; font-weight: bold;">Debe seleccionar una fecha de inicio anterior o igual a la fecha de fin.<br></p>';
    		$.prompt(msgText,{  prefix:'brownJqi'});
		}
		if(error==0){
				    $('#accionId').val('generarEstadoCuenta');
					$('#formId')[0].submit();
		}
	}
//-->
</script>

<center>
	<div>
		<html:form action="${_accion}" method="post" styleId="formId">
			<table 
			style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 60%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra"
			
			>
			<tr>
			<th colspan="4">Estado de cuentas</th>
			</tr>
				<tr class="odd">
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
			</table>
			<table align="center">
				<tr>
					<td>
						<input type="button" onclick="handlerDeleteButton(this);" value="Generar Estado de Cuenta">
					</td>
				<td>
					<input type="button" value="Limpiar" onclick="cleanFields('formId');">
				</td>
				</tr>
			</table>
			<html:hidden property="ascId"/>
			<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
		</html:form>
	</div>
</center>