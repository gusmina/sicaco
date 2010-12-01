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
		alert(montos);
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
	
	function selected(){
		var chk = $("#chkbx")[0];
		if(chk.checked == true){
			$("#ascEmpId").val(2);
		}else{
			$("#ascEmpId").val(1);
		}
	}
	
</script>

<html:form action="${_accion}" method="post"  styleId="formId"  enctype="multipart/form-data">
<table align="center" >
	<tr>
    	<td colspan="2"><label>
			<bean:message key="lbl.cargoFiadores.asociado" />:</label>
			<input type="checkbox" name="chkbx" id="chkbx" onclick="selected();" /> 
			<label><bean:message key="lbl.ordcom.codEmp" /></label>
			<html:hidden property="ascEmp" styleId="ascEmpId" value="1" />
		</td>
   	</tr>
	<tr>
		<td><label>
			<bean:message key="lbl.cargoFiadores.ascCodigoAsociado" />:</label>
		</td>
		<td>
			<html:text property="codigoAsociado" size="15" maxlength="12" styleClass="obligatorio" value="${form.codigoAsociado}" styleId="codigoAsociadoId"/>
			<span><bean:message key="msg.obligatorio" /> </span></td>
	</tr>
	<tr>
		<td><label>
			<bean:message key="lbl.cargoFiadores.ascNombre" />:</label>
		</td>
		<td>
			<html:text property="ascNombre" size="15" maxlength="100" styleClass="obligatorio" value="${form.ascNombre}" styleId="ascNombreId"/>
		</td>
		<td><label>
			<bean:message key="lbl.cargoFiadores.ascApellido" />:</label>
		</td>
		<td>
			<html:hidden property="codigo" styleId="codice" value="${form.codigo}"/>
			<html:text property="ascApellido" size="15" maxlength="100" styleClass="obligatorio" value="${form.ascApellido}" styleId="ascApellidoId"/>
			<input type="button" value="..." 
				onclick="ajaxCallNormal('${pageContext.request.contextPath}/procesosEspeciales${_accion}.do','accion=cargarListaAsociados&codigoAsociado='+$('#codigoAsociadoId').val()+'&ascNombre='+$('#ascNombreId').val()
					+'&ascApellido='+$('#ascApellidoId').val()
					+'&ascEmp='+$('#ascEmpId').val(),'asociados')"/>
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
				<bean:message key="cmd.cargoFiadores.cargaPrestamos" />
			</html:submit>
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">

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
