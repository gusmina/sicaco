<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#tisNombreId").alphanumeric({allow:" "});
    	$("#tisPolizaId").alphanumeric({allow:" -"});
    	$("#tisDescripcionId").alphanumeric({allow:" -.;,()"});
  });
  
  	function selected1(){
		$("#empresaId").removeAttr("disabled");
		$("#empleadoId").attr("disabled","disabled");
		$("#asociadoIdId").attr("disabled","disabled");
		$("#perNombreId").attr("disabled","disabled");
		$("#perApellidoId").attr("disabled","disabled");
	//	$("#tipoCuentaId").attr("disabled","disabled");
	//	$("#cuentaParId").attr("disabled","disabled");
	};
  
  	function selected2(){
  		$("#empresaId").attr("disabled","disabled");
		$("#empleadoId").removeAttr("disabled");
		$("#asociadoIdId").attr("disabled","disabled");
		$("#perNombreId").attr("disabled","disabled");
		$("#perApellidoId").attr("disabled","disabled");
	//	$("#tipoCuentaId").attr("disabled","disabled");
	//	$("#cuentaParId").attr("disabled","disabled");
	};
  
  	function selected3(){
  		$("#empresaId").attr("disabled","disabled");
  		$("#empleadoId").attr("disabled","disabled");
		$("#asociadoIdId").removeAttr("disabled");
		$("#perNombreId").attr("disabled","disabled");
		$("#perApellidoId").attr("disabled","disabled");
	//	$("#tipoCuentaId").attr("disabled","disabled");
	//	$("#cuentaParId").attr("disabled","disabled");
	};
	
	function selected4(){
		$("#empresaId").attr("disabled","disabled");
		$("#empleadoId").attr("disabled","disabled");
		$("#asociadoIdId").attr("disabled","disabled");
		$("#perNombreId").removeAttr("disabled");
		$("#perApellidoId").removeAttr("disabled");
	//	$("#tipoCuentaId").attr("disabled","disabled");
	//	$("#cuentaParId").attr("disabled","disabled");
	};
	
	function selected5(){
		$("#empresaId").attr("disabled","disabled");
		$("#empleadoId").attr("disabled","disabled");
		$("#asociadoIdId").attr("disabled","disabled");
		$("#perNombreId").attr("disabled","disabled");
		$("#perApellidoId").attr("disabled","disabled");
	//	$("#tipoCuentaId").removeAttr("disabled");
	//	$("#cuentaParId").attr("disabled","disabled");
	};
	
	function selected6(){
		$("#empresaId").attr("disabled","disabled");
		$("#empleadoId").attr("disabled","disabled");
		$("#asociadoIdId").attr("disabled","disabled");
		$("#perNombreId").attr("disabled","disabled");
		$("#perApellidoId").attr("disabled","disabled");
	//	$("#tipoCuentaId").attr("disabled","disabled");
	//	$("#cuentaParId").removeAttr("disabled");
	};
	
	function obtenerArchivo(){
		var filename;
		filename = $("#archivoId").val();
		$('#accionId').val('cargar');
   		$('#pageId').val('0');
		$('#formId')[0].submit();
	}
	
	
</script>

<html:form action="${_accion}" method="post"  styleId="formId"  enctype="multipart/form-data">
<table align="center">
	<tr>
		<td >
			<label><bean:message key="lbl.cAPre.empleadoOAsociado" /></label>:
		</td>
		<td colspan="2">
			<html:select property="empOAsoc" styleClass="obligatorio">
				<html:option value="e">Empleado</html:option>
				<html:option value="a">Asociado</html:option>						
			</html:select>
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td >
			<label><bean:message key="lbl.cAPre.razon" /></label>:
		</td>
		<td colspan="2">
			<html:textarea property="razon" styleClass="obligatorio" styleId="razonId" rows="3" cols="25" />
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<%-- 
	<tr>
		<td >
			<label><bean:message key="lbl.cAPre.cuotas" /></label>:
		</td>
		<td colspan="2">
			<html:text property="monto" styleClass="obligatorio" styleId="montoId" maxlength="15" size="15" />
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr> --%>
	<tr>
		<td><label><bean:message key="lbl.proveedorOrden.lineaPrestamo"/>:</label></td>
		<td colspan="2">
			<html:select property="lprId" styleClass="obligatorio" styleId="lprId"
				onchange="ajaxCallNormal('${pageContext.request.contextPath}/procesosEspeciales${_accion}.do',
					'accion=cargarTipos&lprId='+$('#lprId').val(),'tipos')">
				<html:optionsCollection name="lineas" label="lprNombre" value="lprId"/>
			</html:select>
		</td> 
	</tr>
	<tr>
		<td><label><bean:message key="lbl.proveedorOrden.tipoPrestamo"/>:</label></td>
		<td>
			<div id="tipos">
				<html:select property="tprId" styleClass="obligatorio" styleId="tprId">
					<html:optionsCollection name="lstTpr" label="tprNombre" value="tprId"/>
				</html:select>
			</div>
		</td> 
	</tr>
	<tr>
		<td >
			<label><bean:message key="lbl.planilla.cargarArchivo" /></label>:
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<html:file property="archivo" styleId="archivoId" size="30" />
		</td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<input type="button" value="<bean:message key="lbl.cAPre.cargar" />" onclick="handlerCargarButton();">
			&nbsp;&nbsp;
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
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea cargar los pr&eacute;stamos a las cuentas de los asociados ?'+'<br>'+'</p>';	
			$.prompt(msgText,{
		    buttons:{Ok:true,Cancel:false}, 
		    prefix:'brownJqi',
		    callback : function(v,m){
		    	if(v == true){
		    		$('#accionId').val('cargar');
		    		$('#pageId').val('0');
					$('#formId')[0].submit();
		    	}
		    }
		});
	}
	
</script>
