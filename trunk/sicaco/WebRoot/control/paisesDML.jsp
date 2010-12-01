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
			<label><bean:message key="lbl.pais.paiNombre"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" size="15" maxlength="30" property="paiNombre" value="${form.paiNombre}" styleId="paiNombreId"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label>
				<bean:message key="lbl.pais.estNombre" />
			</label>
		</td>
		<td>
			<html:select property="ctrEstEstado.estId" styleClass="obligatorio">
				<html:optionsCollection name="listEstados" value="estId"
					label="estNombre" />
			</html:select>
			<span><bean:message key="msg.obligatorio" /> </span>
		</td>
	</tr>
	<tr>
		<logic:present name="edit">
			<logic:equal value="0" name="edit">
				<td>
					<html:submit property="accion" >
						<bean:message key="cmd.pais.guardar" />
					</html:submit> 
				</td>
				<td>
					<input type="button" value="Limpiar" onclick="cleanFields('formId');">
				</td>
			</logic:equal>
			<logic:equal value="1" name="edit">
				<td>
					<html:submit property="accion" >
						<bean:message key="cmd.pais.salvar" />
					</html:submit> 
				</td>
				<td>
					<html:submit property="accion" onclick="$('#pageId').val('0');">
						<bean:message key="cmd.pais.cancelar" />
					</html:submit> 
				</td>
			</logic:equal>
		</logic:present>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="paiId" value="${form.paiId}"/>
<html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
<html:hidden property="audFechaModificacion" value="${form.audFechaModificacion}"/>
<html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
<html:hidden property="audUsuarioModificacion" value="${form.audUsuarioModificacion}"/>

	<input class="exclude" type="hidden" name="paiId2" id="paiId2" value=""/>
  	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>

 <script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerDeleteButton(id){
		$('#paiId2').val(id);
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n de un Pais!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea eliminar el Pais seleccionado ?'+'<br>'+'</p>';	
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
