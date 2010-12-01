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
	};
  
  	function selected2(){
  		$("#empresaId").attr("disabled","disabled");
		$("#empleadoId").removeAttr("disabled");
		$("#asociadoIdId").attr("disabled","disabled");
		$("#perNombreId").attr("disabled","disabled");
		$("#perApellidoId").attr("disabled","disabled");
	};
  
  	function selected3(){
  		$("#empresaId").attr("disabled","disabled");
  		$("#empleadoId").attr("disabled","disabled");
		$("#asociadoIdId").removeAttr("disabled");
		$("#perNombreId").attr("disabled","disabled");
		$("#perApellidoId").attr("disabled","disabled");
	};
	
	function selected4(){
		$("#empresaId").attr("disabled","disabled");
		$("#empleadoId").attr("disabled","disabled");
		$("#asociadoIdId").attr("disabled","disabled");
		$("#perNombreId").removeAttr("disabled");
		$("#perApellidoId").removeAttr("disabled");
	};
	
	function selected5(){
		$("#empresaId").attr("disabled","disabled");
		$("#empleadoId").attr("disabled","disabled");
		$("#asociadoIdId").attr("disabled","disabled");
		$("#perNombreId").attr("disabled","disabled");
		$("#perApellidoId").attr("disabled","disabled");
	};
	
	function selected6(){
		$("#empresaId").attr("disabled","disabled");
		$("#empleadoId").attr("disabled","disabled");
		$("#asociadoIdId").attr("disabled","disabled");
		$("#perNombreId").attr("disabled","disabled");
		$("#perApellidoId").attr("disabled","disabled");
	};
	
	function activa(aof){
		if(aof==1){
			$("#inicioId").removeAttr("disabled");
			$("#finId").removeAttr("disabled");
		}else{
			$("#inicioId").attr("disabled","disabled");
			$("#finId").attr("disabled","disabled");
		}
	};
	
	function llamadaBuscar(aof){
		if(aof == 1){
			$('#accionId').val('buscar');
			$('#formId')[0].submit();
		}else{
			ajaxCallNormal('${pageContext.request.contextPath}/procesosEspeciales${_accion}.do',
				'accion=descEmpleadoFiador&codEmpId='+$('#empleadoId').val()+
				'&empNombre='+$('#perNombreId').val()+
				'&empApelldo='+$('#perApellidoId').val(),'divDescuento');
		}
	};
	
	function cambiaFin(aof){
		if(aof == 1){
			ajaxCallNormal('${pageContext.request.contextPath}/procesosEspeciales${_accion}.do','accion=cargaFin&empId='+$('#empresaId').val(),'divFin');
		}
	}
	
</script>

<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td colspan="2">
			<label><bean:message key="lbl.planilla.aof" /></label>:
		</td>
		<td colspan="2">
			<html:select property="aof" value="${form.aof}" styleId="aofId" 
			onchange="activa($('#aofId').val());">
				<html:option value="1"><bean:message key="lbl.planilla.aofAsociado" /></html:option>
				<html:option value="2"><bean:message key="lbl.planilla.aofFiadores" /></html:option>
			</html:select>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<label><bean:message key="lbl.planilla.asociado" /></label>:
		</td>
	</tr>
	<tr>
		<td>
			<html:radio property="selected" value="3" styleId="selectedId" onclick="selected3();"/>
		</td>
		<td>
			<label><bean:message key="lbl.planilla.asociadoId"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" size="15" maxlength="13" property="ctaAscAsociado.ascCodigoAsociado" value="${form.ctaAscAsociado.ascCodigoAsociado}" styleId="asociadoIdId" disabled="true"/> 
		</td>
	</tr>
	<tr>
		<td>
			<html:radio property="selected" value="2" styleId="selectedId" onclick="selected2();"/>
		</td>
		<td>
			<label><bean:message key="lbl.planilla.ascCodigo"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" size="15" maxlength="13" property="ctaAscAsociado.ascCodigo" 
				value="${form.ctaAscAsociado.ascCodigo}" styleId="empleadoId" disabled="true"/> 
		</td>
	</tr>
	<tr>
		<td>
			<html:radio property="selected" value="4" styleId="selectedId" onclick="selected4();"/>
		</td>
		<td>
			<label><bean:message key="lbl.planilla.perNombre"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" size="15" maxlength="25" property="perNombre" 
				value="${form.perNombre}" styleId="perNombreId" disabled="true"/> 
		</td>
		<td>
			<label><bean:message key="lbl.planilla.perApellido"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" size="15" maxlength="25" property="perApellido" 
				value="${form.perApellido}" styleId="perApellidoId" disabled="true"/> 
		</td>
	</tr>
	<logic:present name="tipoDesc">
		<logic:equal name="tipoDesc" value="3">
			<tr>
				<td>
					
				</td>
				<td>
					<label><bean:message key="lbl.planilla.tipoCuenta"/></label>:
				</td>
				<td>
					<html:select property="tipoCuenta" styleClass="obligatorio" styleId="tipoCuentaId" >
						<html:option value="A"><bean:message key="opt.planilla.aportacion"/></html:option>
						<html:option value="B"><bean:message key="opt.planilla.ahorros"/></html:option>
						<html:option value="C"><bean:message key="opt.planilla.prestamos"/></html:option>
						<html:option value="D"><bean:message key="opt.planilla.seguros"/></html:option>
					</html:select>
				</td>
			</tr>
		</logic:equal>
		<logic:equal name="tipoDesc" value="4">
			<tr>
				<td>
					
				</td>
				<td>
					<label><bean:message key="lbl.planilla.cuentaPar"/></label>:
				</td>
				<td colspan="4">
					<html:select property="cuentaPar" styleClass="obligatorio" styleId="cuentaParId" >
						<html:option value="E"><bean:message key="opt.planilla.apor-ahoYpres-Seg"/></html:option>
						<html:option value="F"><bean:message key="opt.planilla.aporYahoYpres-Seg"/></html:option>
						<html:option value="G"><bean:message key="opt.planilla.apor-ahoYpresYSeg"/></html:option>
					</html:select>
				</td>
			</tr>
		</logic:equal>
	</logic:present>
<%--	<tr>
		<td>
		</td>
		<td>
			<label><bean:message key="lbl.planilla.inicio"/></label>:
		</td>
		<td>
			<html:text property="inicio" styleClass="obligatorio" size="15" maxlength="15" styleId="inicioId"/>
		</td>
		<td>
			<label><bean:message key="lbl.planilla.fin"/></label>:
		</td>
		<td>
			<div id="divFin">
				<html:text property="fin" styleClass="obligatorio" size="15" maxlength="15" styleId="finId" value="${form.fin}"/>
			</div>
		</td>
	</tr>  --%>
	<tr>
		<td>
		</td>
		<td>
			<label><bean:message key="lbl.planilla.empresa"/></label>:
		</td>
		<td>
			<html:select property="empresa" styleClass="obligatorio" value="${form.empresa}" styleId="empresaId"
				onchange="cambiaFin($('#aofId').val());">
				<html:option value="-1">---</html:option>
				<html:optionsCollection label="etrNombre" value="etrId" name="lemp"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<logic:present name="edit">
			<logic:equal value="0" name="edit">
				<td colspan="4">
					<input type="button" value="<bean:message key="lbl.planilla.buscar" />"
						onclick="llamadaBuscar($('#aofId').val());"> 
					<html:submit property="accion" >
						<bean:message key="cmd.planilla.generar" />
					</html:submit> 
					<input type="button" value="Limpiar" onclick="cleanFields('formId');">
				</td>
			</logic:equal>
			<logic:equal value="1" name="edit">
				<td>
					<html:submit property="accion" >
						<bean:message key="cmd.tis.salvar" />
					</html:submit> 
				</td>
				<td>
					<html:submit property="accion" onclick="$('#pageId').val('0');">
						<bean:message key="cmd.tis.cancelar" />
					</html:submit> 
				</td>
			</logic:equal>
		</logic:present>
	</tr>
	<tr>
		<td colspan="5">
			<div id="divDescuento">
			</div>
		</td>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">

	<input class="exclude" type="hidden" name="tisId2" id="tisId2" value=""/>
  	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
  	<html:hidden property="tipoDesc" value="${form.tipoDesc}"/>
</html:form>

 <script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerDeleteButton(id){
		$('#tisId2').val(id);
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n de Tipo de Seguro!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea eliminar el Tipo seleccionado ?'+'<br>'+'</p>';	
			$.prompt(msgText,{
		    buttons:{Ok:true,Cancel:false}, 
		    prefix:'brownJqi',
		    callback : function(v,m){
		    	if(v == true){
		    		$('#accionId').val('eliminar');
		    		$('#pageId').val('0');
					$('#formId')[0].submit();
		    	}
		    }
		});
	}
	
</script>
