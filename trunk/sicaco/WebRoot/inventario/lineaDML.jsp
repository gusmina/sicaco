<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#linNombreId").alphanumeric({allow:" "});
    	$("#linDescripcionId").alphanumeric({allow:" .,;"});
    	$("#linUtilidadId").numeric({allow:"."});
  });
</script>

<html:form action="${_accion}" method="post"  focus="linNombre" styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.linea.linCodigo" /></label>
		</td>
		<td>
			<html:text styleClass="exclude" maxlength="100" size="10" property="linCodigo" readonly="true"/> 
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.linea.linNombre" /></label>
		</td>
		<td>
			<html:text styleId="linNombreId" styleClass="obligatorio" maxlength="100" size="15" property="linNombre" /> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.linea.linUtilidad" /></label>
		</td>
		<td>
			<html:text styleId="linUtilidadId" styleClass="obligatorio" maxlength="15" size="15" property="linUtilidad" /> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.linea.linDescripcion" /></label>
		</td>
		<td>
			<html:textarea styleId="linDescripcionId" styleClass="opcional" cols="25" rows="3" property="linDescripcion" ></html:textarea> 
		</td>
	</tr>
	<tr>
		<logic:present name="filtro">
			<logic:equal value="1" name="filtro">
				<td>
					<input id="pageId" type="hidden" name="page" value="3">
					<html:submit property="accion" >
						<bean:message key="cmd.linea.guardar" />
					</html:submit> 
				</td>
				<td>
					<html:submit property="accion" onclick="$('#pageId').val('0');">
						<bean:message key="cmd.linea.redirectArticulo" />
					</html:submit> 
				</td>
				<td>
					<input type="button" value="Limpiar" onclick="cleanFields('formId');">
				</td>
			</logic:equal>
			<logic:equal value="2" name="filtro">
				<td>
					<input id="pageId" type="hidden" name="page" value="3">
					<html:submit property="accion" >
						<bean:message key="cmd.linea.salvar" />
					</html:submit> 
				</td>
				<td>
					<html:submit property="accion" onclick="$('#pageId').val('0');">
						<bean:message key="cmd.linea.cancelar" />
					</html:submit> 
				</td>
			</logic:equal>
		</logic:present>
	</tr>
</table> 
	<html:hidden property="linId" value="${form.linId}"/>
	<html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
	<html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
  <input class="exclude" type="hidden" name="linId2" id="linId2" value=""/>
  <input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>
 <script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerDeleteButton(id){
		$('#linId2').val(id);
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n de L&iacute;nea!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea eliminar la l&iacute;nea ?'+'<br>'+'</p>';	
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
