<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#ascCodigoAsociadoId").numeric({allow:"ASC"});
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
		if(cantidad <1){
			cantidad = 1;
		}
		$('#montoTotalId').val(monto*cantidad);
	};
	
	function saveSeleccionA(valor) {
		valores = valor.split(';')
		$('#ascCodigoAsociadoId').val(valores[0])
		$('#ascNombreId').val(valores[1])
		$('#codice').val(valores[2])
		$('#resultadoAsc').hide('slow')
	} 
	
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
			<bean:message key="lbl.ordcom.asociado" />:</label>
			<input type="checkbox" name="chkbx" id="chkbx" onclick="selected();" /> 
			<label><bean:message key="lbl.ordcom.codEmp" /></label>
			<html:hidden property="ascEmp" styleId="ascEmpId" value="1" />
		</td>
   	</tr>
	<tr>
		<td><label>
			<bean:message key="lbl.ordcom.secAscAsociado.Id.ascCodigo" />:</label>
		</td>
		<td>
			<html:text property="ascCodigoAsociado" size="15" maxlength="12" styleClass="obligatorio" value="${form.ascCodigoAsociado}" styleId="ascCodigoAsociadoId"/>
			<span><bean:message key="msg.obligatorio" /> </span></td>
		<td><label>
			<bean:message key="lbl.ordcom.ascNombre" />:</label>
		</td>
		<td>
			<html:hidden property="codigo" styleId="codice" value="${form.codigo}"/>
			<html:text property="ascNombre" size="15" maxlength="100" styleClass="obligatorio" value="${form.ascNombre}" styleId="ascNombreId"/>
			<input type="button" value="..." 
				onclick="ajaxCallNormal('${pageContext.request.contextPath}/procesosEspeciales${_accion}.do',
					'accion=cargarListaAsociados&ascCodigoAsociado='+$('#ascCodigoAsociadoId').val()+
					'&ascNombre='+$('#ascNombreId').val()+
					'&ascEmp='+$('#ascEmpId').val(),'asociados')"/>
		</td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<div id="asociados">
			</div>
	    </td>
	</tr>
	<tr>
    	<td colspan="3"><label>
			<bean:message key="lbl.cAPre.infoPrestamo" />:</label>
		</td>
   	</tr>
	<tr>
		<td >
			<label><bean:message key="lbl.cAPre.razon" /></label>:
		</td>
		<td colspan="3">
			<html:text property="razon" styleClass="obligatorio" styleId="razonId" maxlength="100" size="45" />
		</td>
	</tr>
	<tr>
		<td >
			<label><bean:message key="lbl.cAPre.monto" /></label>:
		</td>
		<td >
			<html:text property="monto" styleClass="obligatorio" styleId="montoId" maxlength="15" size="15" 
				onchange="recalcularTotal();"/>
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
		<td >
			<label><bean:message key="lbl.cAPre.cantidad" /></label>:
		</td>
		<td >
			<html:text property="cantidad" styleClass="obligatorio" styleId="cantidadId" maxlength="15" size="15" 
				onchange="recalcularTotal();"/>
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td >
			<label><bean:message key="lbl.cAPre.pagos" /></label>:
		</td>
		<td >
			<html:text styleClass="obligatorio" styleId="pagosId" 
				maxlength="15" size="15" property="pagos" />
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
		<td >
			<label><bean:message key="lbl.cAPre.montoTotal" /></label>:
		</td>
		<td >
			<input type="text" class="obligatorio" id="montoTotalId" readonly="readonly" 
				maxlength="15" size="15" value="0.00" />
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td><label><bean:message key="lbl.proveedorOrden.lineaPrestamo"/>:</label></td>
		<td colspan="3">
			<html:select property="lprId" styleClass="obligatorio" styleId="lprId"
				onchange="ajaxCallNormal('${pageContext.request.contextPath}/procesosEspeciales${_accion}.do',
					'accion=cargarTipos&lprId='+$('#lprId').val(),'tipos')"
				value="${form.lprId}">
				<html:optionsCollection name="lineas" label="lprNombre" value="lprId"/>
			</html:select>
		</td> 
	</tr>
	<tr>
		<td><label><bean:message key="lbl.proveedorOrden.tipoPrestamo"/>:</label></td>
		<td colspan="3">
			<div id="tipos">
				<html:select property="tprId" styleClass="obligatorio" styleId="tprId"
					value="${form.tprId}">
					<html:optionsCollection name="lstTpr" label="tprNombre" value="tprId"/>
				</html:select>
			</div>
		</td> 
	</tr>
	<tr>
		<td colspan="4">
			<label><bean:message key="lbl.cAPre.ifDocumento" /></label>:
		</td>
	</tr>
	<tr>
		<td >
			<label><bean:message key="lbl.cAPre.correlativo" /></label>:
		</td>
		<td >
			<html:text property="correlativo" styleClass="obligatorio" styleId="correlativoId" maxlength="15" size="15" />
		</td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<input type="button" value="<bean:message key="lbl.cAPre.cargarAsociado" />" onclick="handlerCargarButton();">
			&nbsp;&nbsp;
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
			&nbsp;&nbsp;
			<input type="button" value="<bean:message key="lbl.cAPre.cargarTodos" />" onclick="handlerCargarTodosButton();">
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
