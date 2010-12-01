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
		$("#verRep").hide()    	
  });
  
  	function seleccionar(){
	  	if($('#chk:checked').is(':checked')){
	  			$('#verRep').fadeIn()
	  	}else{
				$('#verRep').fadeOut()
		}
  	};
  	
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
		<td>
			<label><bean:message key="lbl.planilla.empresa" /></label>:
		</td>
		<td>
			<html:select property="empresa" styleId="empresaId" >
				<html:optionsCollection label="etrNombre" value="etrId" name="empresas" />
			</html:select>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.planilla.cargarArchivo" /></label>:
		</td>
		<td>
			<html:file property="archivo" styleId="archivoId" />
		</td>
	</tr>
	<logic:present name="edit">
		<logic:equal name="edit" value="1">
			<tr>
				<td>
					<label>
						Fecha:
					</label>
				</td>
				<td>
			
					<input type="text" maxlength="10" size="10" name="fecha"
						onkeyup="JavaScript:mask(this);" id="fecha" />
					<input id="calendar" type="button" value="...." />
					<script type="text/javascript">
			            Calendar.setup({
			              inputField    : "fecha",
			              button        : "calendar",
			              align         : "Br"
			            });
			         </script>
				</td>
			</tr>
		</logic:equal>
	</logic:present>
	<tr>
	
		<td colspan="2" align="center">
			<html:submit property="accion" >
				<bean:message key="cmd.planilla.cargar" />
			</html:submit> 
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
			<logic:present name="edit">
				<logic:equal name="edit" value="1">
					<html:submit property="accion" >
						<bean:message key="cmd.planilla.actualizar" />
					</html:submit> 
				</logic:equal>
			</logic:present>

		</td>
	</tr>
	<logic:notPresent name="edit">
		<tr style="color:#1E1E1E;
		font-family:Verdana,Helvetica,Arial,Geneva,Helvetica,sans-serif;
		font-size:0.75em;
		font-size-adjust:none;
		font-style:normal;
		font-variant:normal;
		font-weight:normal;
		line-height:1.7em;">
		
		<td style="font-size: 10px" colspan="2">
			<html:checkbox property="finalizado" styleId="chk" onclick="seleccionar();">He terminado de realizar los descuentos de esta quincena.</html:checkbox>
		</td>
	</tr>
	</logic:notPresent>
	
</table>
<div id="verRep">
	<table align="center">

		<tr>
			<td colspan="2">  
				<html:submit property="accion" >
					<bean:message key="cmd.planilla.verReporte" />
				</html:submit>
			</td>
		</tr>
			
	</table>
</div>
<logic:present name="errores">
<div align="center">
	<%=request.getAttribute("respuesta")%>
</div>
</logic:present> 
<input id="pageId" type="hidden" name="page" value="3">
	<input class="exclude" type="hidden" name="tisId2" id="tisId2" value=""/>
  	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>
