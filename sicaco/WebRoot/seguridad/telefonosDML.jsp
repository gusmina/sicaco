<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#telId").numeric();
    	$("#telExtId").numeric();
  });
</script>

	<html:form action="${_accion}" method="post" focus="id.telTelefono" styleId="formId">
      <table border="0" align="center">
      	<tr>
          <td><LABEL><bean:message key="lbl.telefonos.id.telTelefono" />:</LABEL></td>
          <td>
          	<html:text styleId="telId" property="id.telTelefono" styleClass="obligatorio" size="15" maxlength="15"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
          <td><LABEL><bean:message key="lbl.telefonos.telExt" />:</LABEL></td>
          <td>
          	<html:text styleId="telExtId" property="telExt" size="15" maxlength="8" styleClass="obligatorio"/>
          </td>
        </tr>
        <tr>
          <td><LABEL><bean:message key="lbl.telefonos.secTteTipoTelefono.tteId" />:</LABEL></td>
          <td>
          	<html:select property="secTteTipoTelefono.tteId" styleClass="obligatorio">
				<html:optionsCollection  label="tteDescripcion" name="tipoTelefono" value="tteId"/>     					
			</html:select>
			<span><bean:message key="msg.obligatorio"/></span>
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
          	<logic:present name="filtro">
          		<%-- Cuando no tiene filtro van a ser acciones de DML --%>
          		<logic:equal name="filtro" value="0">
          			<logic:present name="boton">
          				<%-- Si el parametro es igual a cero servira para agregar un nuevo usuario --%>
          				<logic:equal name="boton" value="0">
          					
          				</logic:equal>
          				<%-- Si el parametro es uno indiciara que se va a editar el usuario --%>
          				<logic:equal name="boton" value="1">
          					<html:submit property="accion">
				          		<bean:message key="cmd.telefonos.modificar" />
				          	</html:submit>
				          	<html:submit property="accion">
				          		<bean:message key="cmd.telefonos.eliminar" />
				          	</html:submit>
          				</logic:equal>
          				<input id="pageId" type="hidden" name="page" value="3"/>
          			</logic:present>
          			<html:submit property="accion" onclick="$('#pageId').val('0');">
		          		<bean:message key="cmd.telefonos.cancelar" />
		          	</html:submit>
          		</logic:equal>
          		<%-- Solo acciones de busqueda un boton nada mas --%>
          		<logic:equal name="filtro" value="1">
          			<html:submit property="accion" onclick="$('#pageId').val('4');">
		          		<bean:message key="cmd.telefonos.agregar" />
		          	</html:submit>
		          	<logic:equal name="asoc" value="false" scope="session">
		          				          	<html:submit property="accion" styleId="accionId" onclick="$('#pageId').val('0');$('#accionId').val('redirectLista');">
				          <bean:message key="cmd.telefonos.return" />
				    </html:submit>
		          	</logic:equal>
		          	<logic:equal name="asoc" value="true" scope="session">
		          		<html:submit property="accion" styleId="accionId" onclick="$('#pageId').val('0');$('#accionId').val('redirectListaAsoc');">
				          <bean:message key="cmd.telefonos.return" />
				    </html:submit>
		          	</logic:equal>
				    <input type="button" value="Limpiar" onclick="cleanFields('formId');">
          		</logic:equal>
          		
          				<input id="pageId" type="hidden" name="page" value="3"/>
          	</logic:present>
          </td>
        </tr>
      </table>
      <html:hidden property="perId" value="${form.perId}"/>
      <html:hidden property="asoc" value="${form.asoc}"/>
      <input class="exclude" type="hidden" name="accion" id="accionId2" value=""/>
    </html:form>
<script type="text/javascript">
	 var deleteButton = 1;
	function handlerDeleteButton(tel,per){
		$('#telId').val(tel);
		$('#perId').val(per);
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n de Telef&oacute;no!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea eliminar este telef&oacute;no ?'+'<br>'+'</p>';	
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