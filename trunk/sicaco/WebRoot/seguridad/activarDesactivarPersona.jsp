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
#loadingStyle{
	font-size: 1.4em;
	font-weight: bold;
	font-style: italic;
}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		$("#perDuiId").numeric();
    	$("#perPrimerNombreId").alpha();
    	$("#perSegundoNombreId").alpha();
    	$("#perPrimerApellidoId").alpha();
    	$("#perSegundoApellidoId").alpha();
  });
</script>

	<html:form action="${_accion}" method="post"  focus="perId" styleId="formId">
		<script>
      		var patternDui = new Array(${tamDui}-1,1);
      	</script>
	  <div id="idPerForm" align="center">
      <table align="center">
      	<tr> 
      		<td> 
      			<label><bean:message key="lbl.usuario.perDui" /></label> 
      		</td> 
      		<td> 
      			<html:text styleClass="condicional"   maxlength="25" value="${form.perDui}" size="${tamDui}" property="perDui" styleId="perDuiId"></html:text> 
      		</td> 
      		<td> 
      			<label><bean:message key="lbl.usuario.perNit" /></label> 
      		</td> 
      		<td> 
      			<html:text styleClass="condicional" onkeyup="maskNit(this);" maxlength="17" size="17" value="${form.perNit}" property="perNit" ></html:text> 
      		</td> 
      	</tr>
      	<tr> 
          <td><label><bean:message key="lbl.persona.perPrimerNombre" />:</label></td> 
          <td><html:text styleId="perPrimerNombreId" property="perPrimerNombre" value="${form.perPrimerNombre}" size="15" maxlength="25" ></html:text> 
          </td> 
          <td> 
          	<label><bean:message key="lbl.persona.perSegundoNombre" />:</label> 
          </td> 
          <td> 
          		<html:text styleId="perSegundoNombreId" property="perSegundoNombre" value="${form.perSegundoNombre}" size="15" maxlength="25"></html:text> 
          </td> 
        </tr> 
         
        <tr> 
          <td> 
          	<label><bean:message key="lbl.persona.perPrimerApellido" />:</label> 
          </td> 
          <td> 
          	<html:text styleId="perPrimerApellidoId" property="perPrimerApellido" value="${form.perPrimerApellido}" size="15" maxlength="25">></html:text> 
          	
          </td> 
          <td> 
          	<label><bean:message key="lbl.persona.perSegundoApellido" />:</label> 
          </td> 
          <td> 
          	<html:text styleId="perSegundoApellidoId" property="perSegundoApellido" value="${form.perSegundoApellido}" size="15" maxlength="25">></html:text> 
          	<html:hidden styleId="perId" property="perId" value="${form.perId}"/>
      		<input class="exclude" type="hidden" name="page" id="pageId" value="3">
      		<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
          </td> 
        </tr>
	</table>
	</div>
	<table align="center">
	        <tr> 
	      	  <td align="center" style="position: relative; " colspan="4">
	      	  		<table> 
		      	  		<tr>
		      	  			<td>
		          				<input type="button" id="findByExampleButtonId" onclick="handlerFindByExampleButton();" value='<bean:message key="cmd.dml.buscar"/>'> 
		           			</td>
		           			<td>
		          				<input type="button" id="activeButtonId" onclick="handlerActiveButton();" value='<bean:message key="lbl.ai.persona.activar"/>'> 
		          			</td>
		          			<td>	
		          			 	<input type="button" id="inactiveButtonId" onclick="handlerInActiveButton();" value='<bean:message key="lbl.ai.persona.desactivar"/>'> 
		          			</td>
		          			<td>
		          				<input type="button" onclick="activeButtons();" value='<bean:message key="lbl.ai.persona.cancelar"/>'> 
		          			</td>
		          			<td>
		          				<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		          			</td>
		          		</tr>
		          	</table>
	          </td> 
	        </tr> 
	        <tr>
	      		<td  align="center" colspan="3">
	      			<div id="msgId">
	      			
	      			</div>
	      		</td>
	      	</tr> 
	      </table>
 </html:form>
<%--
<td>
		<input type="button" id="deleteButtonId" onclick="handlerDeleteButton();" value='<bean:message key="lbl.ai.persona.eliminar"/>'> 
</td>
 --%>


   <script type="text/javascript">
	 var findByExampleButton = 1;
	 var activeButton = 1;
	 var inactiveButton = 1;
	 var deleteButton = 1;
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
	 
	function handlerFindByExampleButton(){
		if(findByExampleButton == 1){
			if(estadoModo == 0){
				/*ocular a los botones entrando modo buscar */
				enableFieldsTexts();
				inactiveButtonExcept(1);
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
	function handlerActiveButton(){
		if(activeButton == 1){
			if(estadoModo == 0){
				disableFieldTexts();
				inactiveButtonExcept(2);
				estadoModo = 1;
				beginEventMessage('msgId','Modo Activar');
			}
			else{
				$('#accionId').val('activePerson');
				$('#formId')[0].submit();
			}
		}
	}
	function handlerInActiveButton(){
		if(inactiveButton == 1){
			if(estadoModo == 0){
				disableFieldTexts();
				inactiveButtonExcept(3);
				estadoModo = 1;
				beginEventMessage('msgId','Modo Inactivar');
				
			}
			else{
				$('#accionId').val('inactivePerson');
				$('#formId')[0].submit();
			}
		}
	}
	function handlerDeleteButton(){
		if(deleteButton == 1){
			if(estadoModo == 0){
				disableFieldTexts();
				inactiveButtonExcept(4);
				estadoModo = 1;
				beginEventMessage('msgId','Modo Eliminar');
			}
			else{
				 var msgText = '<h3 style="color: red;">!!Eliminacion Usuario!!</h3>'+
     				 '<p style="font-size: 1.4em; font-weight: bold;">Esta completamente seguro que desea eliminar a la persona del sistema ?'+'<br>'+
     				 'Esto implica eliminacion de sesiones,historial de sesiones y datos del Usuario!!!</p>';	
						$.prompt(msgText,{
		      				buttons:{Ok:true,Cancel:false}, 
		      				prefix:'brownJqi',
		      				callback : function(v,m){
		      					if(v == true){
		      						$('#accionId').val('deletePerson');
									$('#formId')[0].submit();
		      					}
		      				}
						});
				
			}
		}
	}
	
	function inactiveButtonExcept(id){
		if(id != 1){findByExampleButton = 0; wrapperSet =  wrapperSet.add('#findByExampleButtonId');}
		if(id != 2){activeButton = 0;  wrapperSet =  wrapperSet.add('#activeButtonId'); }
		if(id != 3){inactiveButton = 0; wrapperSet =  wrapperSet.add('#inactiveButtonId');}
		if(id != 4){deleteButton = 0; wrapperSet =  wrapperSet.add('#deleteButtonId');}
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
		}
		
	}
	
	function cancelEditionMode(){
		enableFieldsTexts();
		findByExampleButton = 1;
		activeButton = 1;
		inactiveButton = 1;
		deleteButton = 1;
		estadoModo = 0;
	}
	
	function enableFieldsTexts(){
		$('input.condicional').attr('readonly','');
	}
	function disableFieldTexts(){
		$('input.condicional').attr('readonly','readonly');
	}
	
	
</script>
   
