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
</script>

<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td>
			<label>
				<bean:message key="lbl.mxp.fechaMovimiento"/>
			</label>
		</td>
		<td>
			<html:text style="float:left;" styleId="mxpFechaMovIniId" onkeyup="mask(this);" value="${form.mxpFechaMovIni}" property="mxpFechaMovIni" maxlength="10" size="10"/>
		</td>
		<td align="left">
			<input  style="height: 18px;" id="button_mxpFechaMovIniId" type="button"  value="..."/>
        	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d">
		</td>
		<td>
			<html:text style="float:left;" styleId="mxpFechaMovFinId" onkeyup="mask(this);" value="${form.mxpFechaMovFin}" property="mxpFechaMovFin" maxlength="10" size="10"/>
		</td>
		<td align="left">
			<input  style="height: 18px;" id="button_mxpFechaMovFinId" type="button"  value="..."/>
        	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d">
		</td>
	</tr>
	<tr>
		<logic:present name="edit">
			<logic:equal value="0" name="edit">
				<td>
					<html:submit property="accion" >
						<bean:message key="cmd.mxp.buscar" />
					</html:submit> 
				</td>
				<td>
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
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="prestamoId" value="${form.prestamoId}"/>
  	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
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
 <script type="text/javascript">
            Calendar.setup({
              inputField    : "mxpFechaMovIniId",
              button        : "button_mxpFechaMovIniId",
              align         : "Br"
            });
            Calendar.setup({
              inputField    : "mxpFechaMovFinId",
              button        : "button_mxpFechaMovFinId",
              align         : "Br"
            });
    </script>
