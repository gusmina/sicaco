<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#medId").alphanumeric({allow:"."});
    	$("#medNombreMedidaId").alphanumeric({allow:" .-"});
    	$("#medComentarioId").alphanumeric({allow:" .-,;"});
  });
</script>

<html:form action="${_accion}" method="post" focus="medId" styleId="formId" >
  <table border="0">
  	<logic:present name="filtro">
  	<logic:equal name="filtro" value="0">
  	<tr>
  	   <td>
      <label><bean:message key="lbl.medida.medId" />:</label></td>
      <td>
       	<html:text property="medId" styleId="medIdId" size="15" maxlength="20" styleClass="obligatorio" value="${form.medId}"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    </logic:equal>
    <logic:equal name="filtro" value="1">
  	<tr>
  	   <td>
      <label><bean:message key="lbl.medida.medId" />:</label></td>
      <td>
       	<html:text property="medId" size="15" styleClass="obligatorio" disabled="true" value="${form.medId}"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    </logic:equal>
    </logic:present>
  	<tr>
      <td><label><bean:message key="lbl.medida.tipomedida" />:</label></td>
      <td>
   		<html:select property="invTmeTipoMedida.tmeId" styleClass="obligatorio">
			<html:optionsCollection  label="tmeNombre" name="listaTipoMed" value="tmeId"/>     					
		</html:select>
      </td>
    </tr> 
    <tr>
  	   <td>
      <label><bean:message key="lbl.medida.medNombreMedida" />:</label></td>
      <td>
       	<html:text  property="medNombreMedida" styleId="medNombreMedidaId" size="15" maxlength="80" styleClass="obligatorio" value="${form.medNombreMedida}"/>
      </td>
    </tr> 
    <tr>
  	   <td>
      <label><bean:message key="lbl.medida.medComentario" />:</label></td>
      <td>
       	<html:textarea  property="medComentario" cols="30" rows="3" styleId="medComentarioId"/>
      </td>
    </tr>
  </table>
      	<logic:present name="filtro">
      		<logic:equal name="filtro" value="1">
		      		<table >
		      			<tr>
		      				<td>
			      				<html:submit property="accion" >
		        					<bean:message key="cmd.medida.editar"/>
		        				</html:submit>
			      			</td>
			      			<td>
			      				<input type="button" id="deleteButtonId" onclick="handlerDeleteButton();" 
			      				value='<bean:message key="lbl.medida.eliminar"/>'>
			      			</td>
			      			<td>
			      				<html:submit property="accion" >
		        					<bean:message key="cmd.medida.cancelar"/>
		        				</html:submit>
			      			</td>
		      			</tr>
			      	</table>
		    </logic:equal>
	        <logic:equal name="filtro" value="0">
	        		<input type="hidden" id="pageId" name="page" value="3" />
		      		<table >
		      			<tr>
		      				<td>
		      					
			      				<html:submit property="accion" >
		        					<bean:message key="cmd.medida.guardar"/>
		        				</html:submit>
			      			</td>
		      				<td>
		      					<html:submit property="accion" onclick="$('#pageId').val('0');">
		        					<bean:message key="cmd.medida.findByExample"/>
		        				</html:submit>
		      				</td>
		      				<td>
		      					<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		      				</td>
		      			</tr>	
				    </table>
		      	</logic:equal>
		</logic:present>
 <%--     <tr>
	      <td  align="center" colspan="3">:
	      			<div id="msgId">
	     				 			
	      			</div>
	     </td>
	 </tr>  --%> 
	 
  
  <input class="exclude" type="hidden" name="mediId" id="mediId" value=""/>
  <input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>
 <script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerDeleteButton(id){
		$('#mediId').val(id);
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n Medida!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea eliminar la medida ?'+'<br>'+'</p>';	
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

