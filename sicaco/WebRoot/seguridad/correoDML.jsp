<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<div>
	<html:form action="/correo" method="post" focus="id.celCorreoElectronico" styleId="formId">
      <table border="0" align="center">
      	<tr>
          <td><label><bean:message key="lbl.correo.id.celCorreoElectronico" />:</label></td>
          <td>
	          	<html:text size="15" maxlength="200" property="email" styleClass="obligatorio" styleId="mailId"/>
	         	<span><bean:message key="msg.obligatorio"/></span>
          		
          </td>
        </tr>
        
        <tr>
          <td><label><bean:message key="lbl.correo.celPrincipal" />:</label></td>
          <td>
          <html:select property="celPrincipal">
          	<html:option value="S">
          		<bean:message key="lbl.correo.celPrincipalS"/>
          	</html:option>
          	<html:option value="N">
          		<bean:message key="lbl.correo.celPrincipalN"/>
          	</html:option>
          </html:select>
         </td>
        </tr>
        
        <tr>
          <td colspan="2" align="center">
          	<logic:present name="filtro">
          		<%-- Cuando no tiene filtro van a ser acciones de DML --%>
          		<logic:equal name="filtro" value="0">
   					<input type="hidden" id="pageId" name="page" value="3" />
		          	<html:submit property="accion">
		          		<bean:message key="cmd.correo.eliminar" />
		          	</html:submit>
        			<html:submit property="accion">
		        		<bean:message key="cmd.correo.cancelar" />
		        	</html:submit>
		        	
          		</logic:equal> 
          		<%-- Solo acciones de busqueda un boton nada mas --%>
          		<logic:equal name="filtro" value="1">
          			<input type="hidden" id="pageId" name="page" value="3" />
          			<html:submit property="accion" >
		          		<bean:message key="cmd.correo.agregar"/>
		          	</html:submit>
		          	<logic:equal name="asoc" value="false" scope="session">
		          	<html:submit property="accion" styleId="accionId" onclick="$('#pageId').val('0');$('#accionId').val('redirectLista');">
				          <bean:message key="cmd.sesion.return" />
				    </html:submit>
		          	</logic:equal>
		          	<logic:equal name="asoc" value="true" scope="session">
		          		<html:submit property="accion" styleId="accionId" onclick="$('#pageId').val('0');$('#accionId').val('redirectListaAsociados');">
				          <bean:message key="cmd.asc.regresar" />
				    	</html:submit>
		          	</logic:equal>
	          		<input type="button" value="Limpiar" onclick="cleanFields('formId');">
          		</logic:equal>
          		
          	</logic:present>
          </td>
        </tr>
      </table>
      <html:hidden property="perId"/>
      <html:hidden property="asoc"/>
       <input class="exclude" type="hidden" name="accion" id="accionId2" value=""/>
    </html:form>
    <script type="text/javascript">
	 var deleteButton = 1;
	function handlerDeleteButton(mail,per){
		$('#mailId').val(mail);
		$('#perId').val(per);
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n de Correo Electr&oacute;nico!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea eliminar este correo electr&oacute;nico ?'+'<br>'+'</p>';	
			$.prompt(msgText,{
		    buttons:{Ok:true,Cancel:false}, 
		    prefix:'brownJqi',
		    callback : function(v,m){
		    	if(v == true){
		    	$('#accionId2').val('eliminar')
		    		$('#pageId').val('0');
					$('#formId')[0].submit();
		    	}
		    }
		});
	}
</script>
 </div>