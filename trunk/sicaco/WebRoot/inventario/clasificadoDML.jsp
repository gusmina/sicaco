<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post" styleId="formId">
<table border="0">
   	<tr>
   		<td>
   			<label><bean:message key="lbl.clasificado.id.invProProveedor.proNombre"/></label>
   		</td>
   		<td>
			<html:select property="id.invProProveedor.proId" styleClass="obligatorio">
				<html:optionsCollection  label="proNombre" name="listProv" value="proId"/>
			</html:select>
			<span><bean:message key="msg.obligatorio"/></span>
   		</td>
   	</tr>
   	
   	<tr>
   		<td>
   			<label><bean:message key="lbl.clasificado.id.invTclTipoClasificacion.tclClasificacion"/></label>
   		</td>
   		<td>
   			<html:select property="id.invTclTipoClasificacion.tclClasificacion"  styleClass="obligatorio">
					<html:optionsCollection  label="tclClasificacion" name="listTcl" value="tclClasificacion"/>
				</html:select>
				<span><bean:message key="msg.obligatorio"/></span>
   		</td>
   	</tr>
   
   <tr>
        <td colspan="2" align="center">
   			
       		<html:submit property="accion">
       			<bean:message key="cmd.clasificado.guardar" />
       		</html:submit>
       </td>
     </tr>
   </table>
   <html:hidden property="id.invProProveedor.proNombre"/>
   <html:hidden property="id.invTclTipoClasificacion.tclClasificacion"/>
   <input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
	<input class="exclude" type="hidden" name="hiddenProId" id="hiddenProId" value=""/>
	<input class="exclude" type="hidden" name="hiddenTclClasificacion" id="hiddenTclClasificacion" value=""/>
 </html:form>
<script type="text/javascript">
var deleteButton = 1;
	function handlerDeleteButton(id1,id2){
	
	$('#hiddenProId').val(id1);
	$('#hiddenTclClasificacion').val("\'"+id2+"\'");
	var msgText = '<h3 style="color: red;">!!Eliminacion de Clasificacion!!</h3>'+
   				 '<p style="font-size: 1.4em; font-weight: bold;">Esta completamente seguro que desea eliminar esta clasificacion del sistema ?';	
		$.prompt(msgText,{
	  				buttons:{Ok:true,Cancel:false}, 
	  				prefix:'brownJqi',
	  				callback : function(v,m){
	  					if(v == true){
	  						$('#accionId').val('eliminar');
							$('#formId')[0].submit();
	  					}
	  				}
		});

	}
	
</script>