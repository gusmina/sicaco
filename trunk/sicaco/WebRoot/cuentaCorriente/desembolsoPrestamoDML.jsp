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
		$("#prestamoIdId").removeAttr("disabled");
		$("#lineaPrestamoIdId").attr("disabled","disabled");
		$("#asociadoIdId").attr("disabled","disabled");
		$("#perNombreId").attr("disabled","disabled");
		$("#perApellidoId").attr("disabled","disabled");
	};
	
	function selected2(){
		$("#prestamoIdId").attr("disabled","disabled");
		$("#lineaPrestamoIdId").removeAttr("disabled");
		$("#asociadoIdId").attr("disabled","disabled");
		$("#perNombreId").attr("disabled","disabled");
		$("#perApellidoId").attr("disabled","disabled");
	};
	
	function selected3(){
		$("#prestamoIdId").attr("disabled","disabled");
		$("#lineaPrestamoIdId").attr("disabled","disabled");
		$("#asociadoIdId").removeAttr("disabled");
		$("#perNombreId").attr("disabled","disabled");
		$("#perApellidoId").attr("disabled","disabled");
	};
	
	function selected4(){
		$("#prestamoIdId").attr("disabled","disabled");
		$("#lineaPrestamoIdId").attr("disabled","disabled");
		$("#asociadoIdId").attr("disabled","disabled");
		$("#perNombreId").removeAttr("disabled");
		$("#perApellidoId").removeAttr("disabled");
	};
	
	function selectAll(){
		var max = $("#tldesemboslo").val();
		if($("#selButtonId").val() == "Seleccionar Todos"){
			$("#selButtonId").val("Deseleccionar Todos");
			for(i=0;i<max;i++){
				var texto = "#pos" + i;
				var chk = $(texto)[0];
	        	chk.checked = true;
			}
		}else{
			$("#selButtonId").val("Seleccionar Todos");
			for(i=0;i<max;i++){
				var texto = "#pos" + i;
				var chk = $(texto)[0];
	        	chk.checked = false;
			}
		}
		desembolso(max);
	}
	
	function desembolso(tamLista){
		calculaDesembolso(tamLista);
		calculaDesembolso2(tamLista);
	};
	
	function calculaDesembolso(tamLista){
		var totalcc = parseFloat(0);
		for(i=0;i<tamLista;i++){
			var texto = "#pos" + i;
			var chk = $(texto)[0];
			var texto2 = "#cc" + i;
        	if(chk.checked == true){
        		totalcc += parseFloat($(texto2).val());
        	};
        	$("#totalccId").val(round_number(totalcc,2));
		}
	};
	
	function calculaDesembolso2(tamLista){
		var totalsc = parseFloat(0);
		for(i=0;i<tamLista;i++){
			var texto3 = "#pos" + i;
			var chk2 = $(texto3)[0];
			var texto4 = "#sc" + i;
        	if(chk2.checked == true){
        		totalsc += parseFloat($(texto4).val());
        	};
        	$("#totalscId").val(round_number(totalsc,2));
		}
	};
	
	function loadRepo(){
  	ajaxCallSincrono('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do',
  		'accion=cargarRepositorio&banId='+$('#bancoId').val()
  		,'repositorios');
  	ajaxCallSincrono('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do',
  		'accion=cargarCorrelativo&banId='+$('#bancoId').val()
  		,'correlativos');
  }
  
  function loadCorrelativo(){
  	ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do',
  		'accion=cargarCorrelativo&banId='+$('#bancoId').val()
  		+ '&rckId=' + $('#rckId').val(),'correlativos');
  }
  
</script>

<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td colspan="2">
			<label><bean:message key="lbl.desembolso.prestamo"/></label>:
		</td>
	</tr>
	<tr>
		<td>
			<html:radio property="selected" value="1" styleId="selectedId" onclick="selected1();"/>
		</td>
		<td>
			<label><bean:message key="lbl.desembolso.prestamoId"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" size="15" maxlength="15" property="prestamoId" value="${form.prestamoId}" styleId="prestamoIdId" disabled="true"/> 
		</td>
	</tr>
	<tr>
		<td>
			<html:radio property="selected" value="2" styleId="selectedId" onclick="selected2();"/>
		</td>
		<td>
			<label><bean:message key="lbl.desembolso.lineaPrestamo"/></label>:
		</td>
		<td>
			<html:select property="lineaPrestamoId" value="${form.lineaPrestamoId}" styleClass="obligatorio" styleId="lineaPrestamoIdId" disabled="true"
				onchange="ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do','accion=cargarTipoPrestamo&linea='+$('#lineaPrestamoIdId').val(),'divTipo','tipoPrestamoIdId');">
				<html:option value="-1">...</html:option>
				<html:optionsCollection  label="lprNombre" name="lstLineaPre" value="lprId"/>     					
			</html:select>
		</td>
		<td>
			<label><bean:message key="lbl.desembolso.tipoPrestamo"/></label>:
		</td>
		<td>
			<div id="divTipo">
				<html:select property="tipoPrestamoId" value="${form.tipoPrestamoId}" styleClass="obligatorio" styleId="tipoPrestamoIdId">
					<html:option value="-1">...</html:option>
				</html:select>
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<label><bean:message key="lbl.desembolso.asociado" /></label>:
		</td>
	</tr>
	<tr>
		<td>
			<html:radio property="selected" value="3" styleId="selectedId" onclick="selected3();"/>
		</td>
		<td>
			<label><bean:message key="lbl.desembolso.asociadoId"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" size="15" maxlength="13" property="asociadoId" value="${form.asociadoId}" styleId="asociadoIdId" disabled="true"/> 
		</td>
	</tr>
	<tr>
		<td>
			<html:radio property="selected" value="4" styleId="selectedId" onclick="selected4();"/>
		</td>
		<td>
			<label><bean:message key="lbl.desembolso.perNombre"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" size="15" maxlength="25" property="perNombre" value="${form.perNombre}" styleId="perNombreId" disabled="true"/> 
		</td>
		<td>
			<label><bean:message key="lbl.desembolso.perApellido"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" size="15" maxlength="25" property="perApellido" value="${form.perApellido}" styleId="perApellidoId" disabled="true"/> 
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<label><bean:message key="lbl.desembolso.totalcc"/></label>:
		</td>
		<td>
			<input type="text" id="totalccId" size="15" value="0.0" readonly="readonly"> 
		</td>
		<td>
			<label><bean:message key="lbl.desembolso.totalsc"/></label>:
		</td>
		<td>
			<input type="text" id="totalscId" size="15" value="0.0" readonly="readonly"> 
		</td>
	</tr>
	<!-- <tr>
		<td colspan="3">
			<label><bean:message key="lbl.desembolso.bancoCheque"/></label>:
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<label><bean:message key="lbl.chk.banco"/></label>:
		</td>
		<td>
			<html:select property="banId" styleId="bancoId" 
				onchange="loadRepo();">
				<html:optionsCollection label="banNombre" name="lban" value="banId"/>
			</html:select>
		</td>
		<td>
			<label><bean:message key="lbl.chk.repositorio"/></label>:
		</td>
		<td>
			<div id="repositorios">
				<html:select property="rckId" styleId="rckId" 
					onchange="loadCorrelativo();">
					<html:optionsCollection label="rckNombre" name="lrck" value="rckId"/>
				</html:select>
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<label><bean:message key="lbl.chk.correlativoActual"/></label>:
		</td>
		<td>
			<div id="correlativos" >
				<html:text styleClass="obligatorio" size="15" maxlength="100" property="chkCorrelativoCheque"  
					styleClass="exclude" styleId="chkCorrelativoId" readonly="true"/>
			</div> 
		</td>
	</tr>-->
	<tr>
		<td colspan="5" align="left">
			<html:submit property="accion" >
				<bean:message key="cmd.desembolso.buscar" />
			</html:submit> 
			&nbsp;&nbsp;&nbsp;
			<input type="button" value="<bean:message key="lbl.desembolso.desembolsar"/>" onclick="handlerAddComision();">
			&nbsp;&nbsp;&nbsp;
			<input type="button" value="Seleccionar Todos" id="selButtonId" onclick="selectAll();">
			&nbsp;&nbsp;&nbsp;
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
			&nbsp;&nbsp;&nbsp;
			<input type="button" value="<bean:message key="lbl.desembolso.limpieza"/>" onclick="handlerEliminar();" >
		</td>
	</tr>
</table> 
<p/><p/>
	<div id="lista-datos">
		${_lista2}
	</div>

	<html:hidden property="tamListaDesembolso" value="${form.tamListaDesembolso}" styleId="tldesemboslo"/>
	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>

 <script type="text/javascript">
	 var deleteButton = 1;
	
	function handlerAddComision(){
		//$('#tisId2').val(id);
		var msgText = '<h3 style="color:#25A2DE;">!!Desembolso de Prestamos!!</h3>'+
    		'<p style="font-size: 1.4em; color:#86949B ; font-weight: bold;">¿Desea agregar el porcentaje de comision?</p>';	
			$.prompt(msgText,{
		      buttons:{ Si:true,No:false,Cancel:'x' },
		      prefix:'colsJqi',
		      show:'slideDown',
		      callback : function(v,m){
		    	if(v == true){
		    		$('#accionId').val('desembolsarComision');
		    		$('#pageId').val('0');
					$('#formId')[0].submit();
		    	}if(v == false){
		    		$('#accionId').val('desembolsar');
		    		$('#pageId').val('0');
					$('#formId')[0].submit();
				}
		      }
			}).children('#colsJqi').corner(); 
	}
	
	function handlerEliminar(){
		var msgText = '<h3 style="color:#25A2DE;">!!Limpieza!!</h3>'+
    		'<p style="font-size: 1.4em; color:#86949B ; font-weight: bold;">¡¡La limpieza elminar&aacute; todos aquellos pr&eacute;stamos aprobados que no hayan sido desembolsados!!</p>';	
			$.prompt(msgText,{
		      buttons:{ OK:true,Cancel:false},
		      prefix:'colsJqi',
		      show:'slideDown',
		      callback : function(v,m){
		    	if(v == true){
		    		$('#accionId').val('limpieza');
		    		$('#pageId').val('0');
					$('#formId')[0].submit();
		    	}
		      }
			}).children('#colsJqi').corner(); 
	}
	
</script>


