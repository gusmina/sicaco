<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#chkEmitidoAId").alphanumeric({allow:" "});
    	$("#chkMontoEmitidoId").numeric({allow:"."});
    	$("#chkRazonId").alphanumeric({allow:" -.;,()"});
  });
  
  function calcularMonto(){
  	var disponible = $("#montoDispId").val();
  	var montoEmitido = $("#chkMontoEmitidoId").val();
  	$("#chkMontoEmitidoId").val(dosDecimales(montoEmitido,"chkMontoEmitidoId"));
  	$("#montoDisponibleId").val(round_number(disponible - montoEmitido,2));
  };
  
  function loadRepo(){
  	ajaxCallSincrono('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do',
  		'accion=cargarRepositorio&ctrBanBanco.banId='+$('#bancoId').val()
  		,'repositorios');
  	ajaxCallSincrono('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do',
  		'accion=cargarCorrelativo&ctrBanBanco.banId='+$('#bancoId').val()
  		,'correlativos');
  }
  
  function loadCorrelativo(){
  	ajaxCallNormal('${pageContext.request.contextPath}/cuentaCorriente${_accion}.do',
  		'accion=cargarCorrelativo&ctrBanBanco.banId='+$('#bancoId').val()
  		+ '&rckId=' + $('#rckId').val(),'correlativos');
  }
</script>

<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.chk.banco"/></label>:
		</td>
		<td>
			<html:select property="ctrBanBanco.banId" styleId="bancoId" 
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
		<td>
			<label><bean:message key="lbl.chk.correlativoActual"/></label>:
		</td>
		<td>
			<div id="correlativos" >
				<html:text styleClass="obligatorio" size="15" maxlength="100" property="chkCorrelativoCheque"  
					styleClass="exclude" styleId="chkCorrelativoId" readonly="true"/>
			</div> 
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.chk.chkEmitidoA"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" size="15" maxlength="100" property="chkEmitidoA"  styleId="chkEmitidoAId"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.chk.montoDisponible" /></label>:
		</td>
		<td>
			<html:text  styleClass="exclude" size="15" maxlength="15" 
				property="montoDisponible"  readonly="true" styleId="montoDisponibleId"/> 
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.chk.chkMontoEmitido" /></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" size="15" maxlength="15" property="chkMontoEmitido"  
				styleId="chkMontoEmitidoId" onkeyup="calcularMonto();"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.chk.chkRazon" /></label>:
		</td>
		<td>
			<html:textarea styleClass="obligatorio" rows="3" cols="25" property="chkRazon"  styleId="chkRazonId"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<html:submit property="accion" >
				<bean:message key="cmd.chk.guardar" />
			</html:submit> 
		</td>
		<td>
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="chkId" />
<html:hidden property="prestamoId" />

	<input class="exclude" type="hidden" name="chkId2" id="chkId2" value=""/>
  	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
  	<html:hidden property="montoDisp" styleId="montoDispId"/>
</html:form>

 <script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerDeleteButton(id){
		$('#chkId2').val(id);
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n de un Cheque!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea eliminar el cheque seleccionado?'+'<br>'+'</p>';	
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
