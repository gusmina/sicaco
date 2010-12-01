<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#pcbNumeroCuentaId").alphanumeric({allow:"-"});
  });
</script>

<html:form action="${_accion}" method="post" focus="id.ctrBanBanco.banId" styleId="formId" >
  <table border="0">
    <tr>
      <td><label><bean:message key="lbl.pcb.id.ctrBanBanco.banId" />:</label></td>
      <td>
       	<html:select property="id.ctrBanBanco.banId" styleClass="obligatorio">
			<html:optionsCollection  label="banNombre" name="bank" value="banId"/>     					
		</html:select>
		<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
     <tr>
      <td><label><bean:message key="lbl.pcb.ctaTcuTipoCuenta.tcuId" />:</label></td>
      <td>
       	<html:select property="ctaTcuTipoCuenta.tcuId" styleClass="obligatorio">
			<html:optionsCollection  label="tcuNombre" name="tipoCuentas" value="tcuId"/>     					
		</html:select>
		<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.pcb.id.pcbNumeroCuenta" />:</label></td>
      <td>
       	<html:text property="id.pcbNumeroCuenta" styleId="pcbNumeroCuentaId" size="15" maxlength="25" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
      	<logic:present name="filtro">
      		<%-- Solo acciones de busqueda un boton nada mas --%>
        	<logic:equal name="filtro" value="0">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" >
	        		<bean:message key="cmd.pcb.guardar"/>
	        	</html:submit>
	        	<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.pcb.redirectProveedores" />
	        	</html:submit>
<%--       	&nbsp;&nbsp;&nbsp;&nbsp;
	        	<input type="button" value="Limpiar" onclick="cleanFields('formId');">	--%>
	        </logic:equal>
        </logic:present>
      </td>
    </tr>
  </table>
  <html:hidden property="proId" value="${form.proId}"/>
  <html:hidden property="id.ctrBanBanco.banId" value="${form.id.ctrBanBanco.banId}"/>
  <html:hidden property="id.pcbNumeroCuenta" value="${form.id.pcbNumeroCuenta}"/>
  <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
  <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
  <input class="exclude" type="hidden" name="cuenta" id="cuenta" value=""/>
  <input class="exclude" type="hidden" name="idBan" id="idBan" value=""/>
  <input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>
 <script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerDeleteButton(id1,id2){
		$('#cuenta').val(id1);
		$('#idBan').val(id2);
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n de Cuenta Bancaria!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea eliminar esta Cuenta Bancaria ?'+'<br>'+'</p>';	
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

