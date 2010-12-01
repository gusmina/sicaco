<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#codigoAsociadoId").numeric({allow:"ASC"});
    	$("#ascNombreId").alpha({allow:" ,"});
    	$("#razonId").alphanumeric({allow:" -.;,()"});
    	$("#montoId").numeric({allow:"."});
    	$("#cantidadId").numeric();
    	$("#correlativoId").numeric();
  });
  
	function obtenerArchivo(){
		var filename;
		filename = $("#archivoId").val();
		$('#accionId').val('cargar');
   		$('#pageId').val('0');
		$('#formId')[0].submit();
	};
	
	function recalcularTotal(){
		var monto = $('#montoId').val();
		var cantidad = $('#cantidadId').val();
		$('#montoTotalId').val(monto*cantidad);
	};
	
	function saveSeleccionA(valor) {
		valores = valor.split(';')
		$('#codigoAsociadoId').val(valores[0])
		$('#ascNombreId').val(valores[1])
		$('#ascApellidoId').val(valores[2])
		$('#codice').val(valores[3])
		$('#resultadoAsc').hide('slow')
	};
	
	function calculaMontos(saldoPendiente,tamLista){
		var selected = 0;
		for(i=0;i<tamLista;i++){
			var texto = "#posi" + i;
			var chk = $(texto)[0];
			//var texto2 = "#montoId" + i;
        	if(chk.checked == true){
        		selected = selected + 1;
        	};
		};
		var montos = saldoPendiente/selected;
		montos = round_number(montos, 2);
		for(i=0;i<tamLista;i++){
			var texto = "#posi" + i;
			var chk = $(texto)[0];
			var texto2 = "#montoId" + i;
        	if(chk.checked == true){
        		$(texto2).val(montos);
        	}else{
        		$(texto2).val(0.00);
        	};
		};
	};
	
</script>

<html:form action="${_accion}" method="post"  styleId="formId"  enctype="multipart/form-data">

<table align="center" >

	<tr>
    	<td colspan="4" >
    	<div align="left" >
			<label style="font-size: 13px"><B><I><br>1.&nbsp;<bean:message key="lbl.cargoFiadores.asociado" />:</I></B></label>
			</div>
		</td>
   	</tr>

	<tr>
		<td>
			<label class="obligatorio"><bean:message key="lbl.cargoFiadores.ascCodigoAsociado" />:</label>
		</td>
		<td>
			<label><bean:write property="ascCodigoAsociado" name="asociado"/></label>
		</td>
	</tr>
	<tr>
		<td>
			<label  class="obligatorio"><bean:message key="lbl.cargoFiadores.ascNombre" />:</label>
		</td>
		<td>
			<label><bean:write property="secPerPersona.perPrimerApellido" name="asociado"/>, 
			<bean:write property="secPerPersona.perPrimerNombre" name="asociado"/></label>
		</td>
	</tr>

	<tr>
		<td style="padding-bottom: 0px">
		<div align="left">
			<label style="font-size: 13px">
			<b><i><br>2.&nbsp;<bean:message key="lbl.cargoFiadores.prestamo" />:</i></b></label>
			</div>
		</td>
	</tr>

	<tr>
		<td>
			<label  class="obligatorio"><bean:message key="lbl.cargoFiadores.preId" />:</label>
		</td>
		<td>
			<label><bean:write property="preId" name="prestamo"/></label>
		</td>
		<td>
			<label  class="obligatorio"><bean:message key="lbl.cargoFiadores.tprId" />:</label>
		</td>
		<td>
			<label><bean:write property="ctaTprTipoPrestamo.tprNombre" name="prestamo"/></label>
		</td>
	</tr>
	<tr>
		<td>
			<label  class="obligatorio" ><bean:message key="lbl.cargoFiadores.preMontoSolicitado" />:</label>
		</td>
		<td>
			<label><bean:write property="preMontoSolicitado" name="prestamo"/></label>
		</td>
		<td>
			<label  class="obligatorio" ><bean:message key="lbl.cargoFiadores.preSaldoActualTotal" />:</label>
		</td>
		<td>
			<label><bean:write property="preSaldoActualT" name="prestamo"/></label>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="4" style="padding-bottom: 0px">
		<div align="left">
			<b><i><label style="font-size: 13px"><br>3.&nbsp;<bean:message key="lbl.cargoFiadores.datosPrestamoFiadores" />:</label></i></b>
		</div>
			
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
			<label><bean:message key="lbl.cAPre.tprId" /></label>:
		</td>
		<td colspan="2">
			<html:select style="width: 300px" property="ctaTprTipoPrestamo.tprId" value="${form.ctaTprTipoPrestamo.tprId}" styleClass="obligatorio">
				<html:optionsCollection  label="tprNombre" name="lstTpr" value="tprId"/>     					
			</html:select>
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<div id="asociados">
			</div>
	    </td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<html:submit property="accion">
				<bean:message key="cmd.cargoFiadores.cargarAFiadores" />
			</html:submit>
			<html:submit property="accion">
				<bean:message key="cmd.cargoFiadores.regresar" />
			</html:submit>
		</td>
	</tr>

</table> 
<div id="lista-datos">
	${lista2}
</div>
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="preId" value="${form.preId}"/>
  	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>

 <script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerCargarButton(){
		var msgText = '<h3 style="color: red;">!! Cargo Automatico de Prestamos !!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea el siguiente Prestamo a las cuentas del Asociado ?'+'<br>'+'</p>';	
			$.prompt(msgText,{
		    buttons:{Ok:true,Cancel:false}, 
		    prefix:'brownJqi',
		    callback : function(v,m){
		    	if(v == true){
		    		$('#accionId').val('cargar2');
		    		$('#pageId').val('0');
					$('#formId')[0].submit();
		    	}
		    }
		});
	};
	
	function handlerCargarTodosButton(){
		var msgText = '<h3 style="color: red;">!! Cargo Automatico a Todos los Asociados !!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta seguro que desea cargar un Prestamo a cada uno de los Asociados ? '+'<br>(Este prestamo no guardara correlativo de documento)'+'</p>';	
			$.prompt(msgText,{
		    buttons:{Ok:true,Cancel:false}, 
		    prefix:'brownJqi',
		    callback : function(v,m){
		    	if(v == true){
		    		$('#accionId').val('cargarTodos');
		    		$('#pageId').val('0');
					$('#formId')[0].submit();
		    	}
		    }
		});
	}
	
</script>
