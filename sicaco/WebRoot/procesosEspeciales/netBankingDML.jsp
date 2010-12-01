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
  
  function cambiarEstado(stb){
    $('#stbId').val(stb);
  	$('#accionId').val('cambiarEstado');
	$('#formId')[0].submit();
  };
  
  function eliminar(stb){
    $('#stbId').val(stb);
  	$('#accionId').val('eliminar');
	$('#formId')[0].submit();
  };
  
  function cambiarLista(){
  	$('#accionId').val('cambiarLista');
	$('#formId')[0].submit();
  };
  
</script>

<html:form action="${_accion}" method="post"  styleId="formId" >
<table align="center">
	<tr>
		<td colspan="2" style="margin: 12px 12px 12px 12px;">&nbsp;</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.nb.banco" />:</label>
		</td>
		<td>
			<html:select property="banId" styleId="banIdId" onchange="cambiarLista()">
				<html:optionsCollection label="banNombre" name="banList" value="banId"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td>
			<label>Rubro: </label>
		</td>
		<td>
			<select name="rubro" onchange="cambiarLista()">
				<option value="-1"  >- - - Consolidado - - -</option>
				<option value="0" <%=request.getAttribute("estado1")%> >Deposito pago ahorros electronicos</option>
				<option value="1" <%=request.getAttribute("estado2")%> >Deposito pago proveedores electronico</option>
				<option value="2" <%=request.getAttribute("estado3")%> >Deposito pago prestamo electronico</option>
			</select>
		</td>
	</tr>

	<tr>
		<td colspan="2" style="margin: 12px 12px 12px 12px;">&nbsp;</td>
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<html:submit property="accion">
				<bean:message key="cmd.nb.generaXls"/>
			</html:submit>

			<html:submit property="accion">
				<bean:message key="cmd.nb.generaTxt"/>
			</html:submit>
			<html:submit property="accion">
				<bean:message key="cmd.nb.generaPdf"/>
			</html:submit>
		</td>
	</tr>
	<tr>
		<td colspan="2" style="margin: 12px 12px 12px 12px;">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<html:submit property="accion">
				<bean:message key="cmd.nb.eliminaNoEfectuado"/>
			</html:submit>
			<html:submit property="accion">
				<bean:message key="cmd.nb.eliminaEfectuados"/>
			</html:submit>
		</td>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">

	<input class="exclude" type="hidden" name="stbId" id="stbId" value=""/>
  	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
  	<input class="exclude" type="hidden" name="rubro" id="rubro" value=""/>
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
