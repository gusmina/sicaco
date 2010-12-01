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
			<label><bean:message key="lbl.nseg.notCampo"/></label>:
		</td>
		<td>
		<%-- 
			<html:select property="notCampo" value="${form.notCampo}" disabled="true" styleClass="obligatorio" styleId="notCampoId" >
				<html:option value="">...</html:option>
				<html:optionsCollection  label="notCampo" name="lstCampos" value="notId"/>     					
			</html:select>
		--%>
			<html:text styleClass="obligatorio" size="20" maxlength="100" property="notCampo" value="${form.notCampo}" styleId="notCampoId"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.nseg.notNota" /></label>:
		</td>
		<td>
			<html:textarea property="notNota" cols="27" styleClass="obligatorio" rows="3" value="${form.notNota}" styleId="notNotaId" />
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<logic:present name="edit">
			<logic:equal value="0" name="edit">
				<td>
					<html:submit property="accion" >
						<bean:message key="cmd.nseg.guardar" />
					</html:submit> 
				</td>
				<td>
					<html:submit property="accion" >
						<bean:message key="cmd.nseg.regresar" />
					</html:submit> 
					<input type="button" value="Limpiar" onclick="cleanFields('formId');">
				</td>
			</logic:equal>
			<logic:equal value="1" name="edit">
				<td>
					<html:submit property="accion" >
						<bean:message key="cmd.nseg.salvar" />
					</html:submit> 
				</td>
				<td>
					<html:submit property="accion" onclick="$('#pageId').val('0');">
						<bean:message key="cmd.nseg.cancelar" />
					</html:submit> 
				</td>
			</logic:equal>
		</logic:present>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="notId" value="${form.notId}"/>
<html:hidden property="cuentaAsc" value="${form.cuentaAsc}"/>

	<input class="exclude" type="hidden" name="notId2" id="notId2" value=""/>
  	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>

 <script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerDeleteButton(id){
		$('#notId2').val(id);
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n de Nota de Seguro!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea eliminar la nota seleccionada ?'+'<br>'+'</p>';	
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
