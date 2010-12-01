<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<style type="text/css">
.loadingStyleClass{
	font-size: 1.0em;
	font-weight: bold;
	font-style: italic;
}
</style>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#idTipoId").numeric();
    	$("#tarNombreId").alphanumeric({allow:" "});
    	$("#tarDescripcionId").alphanumeric({allow:" .;,-()"});
  });
</script>

<html:form action="${_accion}" method="post" focus="tarNombre" styleId="formId" >
  <table border="0">
  	<tr>
  	   <td>
      <label><bean:message key="lbl.tipoarticulo.tarId" />:</label></td>
      <td>
       	<html:text property="idTipo" styleId="idTipoId" size="10" maxlength="10" styleClass="exclude" readonly="true" value="${form.idTipo}"/>
      </td>
    </tr>
  	<tr>
      <td><label><bean:message key="lbl.tipoarticulo.tarNomnre" />:</label></td>
      <td>
       	<html:text size="20" styleId="tarNombreId" maxlength="30" property="tarNombre" styleClass="obligatorio"
       		value="${form.tarNombre}"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
        <tr>
      <td><label><bean:message key="lbl.tipoarticulo.tarDescripcion" />:</label></td>
      <td>
      	<html:textarea styleId="tarDescripcionId" styleClass="obligatorio" rows="3" cols="20"  property="tarDescripcion" 
       		value="${form.tarDescripcion}"/>
      </td>
    </tr>
  	<tr>
      <td colspan="2" align="center">
      	<logic:present name="filtro">
      		<%-- Solo acciones de busqueda un boton nada mas --%>
      		<logic:equal name="filtro" value="1">
	      		<input type="hidden" id="pageId" name="page" value="3" />
		      		<table align="center">
		      			<tr>
		      				<td>
		      					<html:submit property="accion" >
		      						<bean:message key="cmd.tipoarticulo.editar"/>
		      					</html:submit>
		      				</td>
		      				<td>
		      					<html:submit property="accion" onclick="$('#pageId').val('0');">
		      						<bean:message key="cmd.tipoarticulo.cancelar"/>
		      					</html:submit>
		      				</td>
		      			</tr>
			      	</table>
		    </logic:equal>
	        <logic:equal name="filtro" value="0">
	        		<input type="hidden" id="pageId" name="page" value="3" />
		      		<table align="center">
		      			<tr>
		      				<td>
		      					<html:submit property="accion">
		      						<bean:message key="cmd.tipoarticulo.guardar"/>
		      					</html:submit>
		      				</td>
		      				<td>
		      					<html:submit property="accion" onclick="$('#pageId').val('0');">
		      						<bean:message key="cmd.tipoarticulo.buscar"/>
		      					</html:submit>
		      				</td>
		      				<td>
		      					<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		      				</td>
		      			</tr>	
				    </table>
		      	</logic:equal>
		</logic:present>
      </td>
    </tr>
  </table>
  <html:hidden styleClass="exclude" property="tarIdHidden" styleId="tarIdHiddenId"/>
  <input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
  <input class="exclude" type="hidden" name="redirectOnError" id="redirectOnErrorId" value=""/>
  <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
  <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
</html:form>
 <script type="text/javascript">
	 var findByExampleButton = 1;
	 var saveButton = 1;
	 var estadoModo = 0;
	 var wrapperSet = $('<div></div>');
	 
	 
	 function ajaxCallNormalInvalidateFields(url,parameters,idDiv){
		$.post(url,parameters,
			function(data){
				beginEffect(idDiv);
				$('#'+idDiv).toggle(1500,
						function(){
								$('#'+idDiv).html(data);
								$('#'+idDiv).show();
								if(estadoModo == 1)disableFieldTexts();
								
						});
			}
		);
	}
	
	function disableFormComponents(){
			$('.includeFade').attr('readonly','readonly').fadeTo('slow',0.3,
				function(){
			});
			
	}
	function enableFormComponents(){
			$('.includeFade').attr('readonly','').fadeTo('fast',1,
			function(){
			});
	}
	 
	function handlerFindByExampleButton(){
		if(findByExampleButton == 1){
			if(estadoModo == 0){
				/*ocular a los botones entrando modo buscar */
				enableFieldsTexts();
				inactiveButtonExcept(1);
				disableFormComponents();
				estadoModo = 1;
				beginEventMessage('msgId','Modo Busqueda');
			}
			else{
				/*darle submit*/
				$('#accionId').val('findByExample');
				$('#pageId').val('0');
				$('#formId')[0].submit();
			}
		}
	}
	
	function handlerSaveButton(){
		if(saveButton == 1){
			if(estadoModo == 0){
				disableFieldTexts();
				inactiveButtonExcept(2);
				estadoModo = 1;
				beginEventMessage('msgId','Modo Guardar');
			}
			else{
				$('#redirectOnErrorId').val('buscar')
				$('#accionId').val('guardar');
				alert($('#accionId').val());
				$('#formId')[0].submit();
			}
		}
	}
	
	function handlerDeleteButton(id){
		$('#tarIdHiddenId').val(id);
		var msgText = '<h3 style="color: red;">!!Eliminacion Tipo Art&iacute;culo!!</h3>'+
     				 '<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea eliminar el Tipo de Art&iacute;culo ?'+'<br>'+'</p>';	
						$.prompt(msgText,{
		      				buttons:{Ok:true,Cancel:false}, 
		      				prefix:'brownJqi',
		      				callback : function(v,m){
		      					if(v == true){
		      						$('#pageId').val('0');
		      						$('#accionId').val('eliminar');
									$('#formId')[0].submit();
		      					}
		      				}
		});
	}
	
	
	
	function inactiveButtonExcept(id){
		if(id != 1){findByExampleButton = 0; wrapperSet =  wrapperSet.add('#findByExampleButtonId');}
		if(id != 2){saveButton = 0;  wrapperSet =  wrapperSet.add('#saveButtonId'); }
		wrapperSet.fadeTo('slow',0.3,
			function(){
		});
	}
	function activeButtons(){
		if(estadoModo == 1){
			cancelEditionMode();
			wrapperSet.fadeTo('fast',1,
			function(){
				wrapperSet = $('<div></div>');
			});
			enableFormComponents();
		}
		
	}
	
	function cancelEditionMode(){
		enableFieldsTexts();
		findByExampleButton = 1;
		saveButton = 1;
		estadoModo = 0;
	}
	
	function enableFieldsTexts(){
		$('input.obligatorio').attr('readonly','');
	}
	function disableFieldTexts(){
		$('input.obligatorio').attr('readonly','readonly');
	}
	
	
</script>

