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
		<td colspan="2">
			<label><bean:message key="lbl.planilla.cargarArchivo" /></label>:
		</td>
	</tr>
	<tr>
		<td>
			<html:file property="archivo" styleId="archivoId" />
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<input type="button" value="<bean:message key="lbl.cambiaCodEmp.cargar" />" onclick="handlerCambiarButton();">
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">

	<input class="exclude" type="hidden" name="tisId2" id="tisId2" value=""/>
  	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>

 <script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerCambiarButton(){
		var msgText = '<h3 style="color: red;">!! Cambio de Codigos de Empleados !!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea realizar el cambio de los Codigos de Empleado ?'+'<br>'+'</p>';	
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
