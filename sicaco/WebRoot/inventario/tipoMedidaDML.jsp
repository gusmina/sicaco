<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#tmeNombreId").alphanumeric({allow:" "});
    	$("#tmeDescripcionId").alphanumeric({allow:" ,.;()-"});
  });
</script>

<html:form action="${_accion}" method="post" styleId="formId">
  <table border="0">
    <tr>
      <td><label><bean:message key="lbl.tmed.tmeNombre" />:</label></td>
      <td>
      	<html:text property="tmeNombre" styleId="tmeNombreId" styleClass="obligatorio"/>
      	<span></span>
      <br><br></td>
    </tr>
  	<tr>
      <td><label><bean:message key="lbl.tmed.tmeDescripcion" />:</label></td>
      <td>
      	<html:textarea styleId="tmeDescripcionId" rows="2" cols="30" property="tmeDescripcion" styleClass="obligatorio"/>
      	<span></span>
      <br><br></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
      <input type="hidden" id="pageId" name="page" value="3" />
		<html:submit property="accion">
			<bean:message key="cmd.tme.guardar" />
  </html:submit>
      </td>
    </tr>
  </table>
  <input class="exclude" type="hidden" name="tmeId" id="tmeId" value=""/>
  <input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>
 <script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerDeleteButton(id){
		$('#tmeId').val(id);
		var msgText = '<h3 style="color: red;">!!Eliminaci&oacute;n de Tipo de Medida!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea eliminar este tipo de medida ?'+'<br>'+'</p>';	
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

