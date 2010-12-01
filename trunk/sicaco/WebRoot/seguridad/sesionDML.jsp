<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<style type="text/css">
	#loadingStyle{
		font-size: 0.8em;
		font-weight: bold;
		font-style: italic;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
    	$("#iseNombreUsuarioId").alphanumeric();
  });
</script>
<div>
	<html:form action="${_accion}" method="post" styleId="formId" styleId="formId">
		
      <table align="center">
      	<logic:notPresent name="filtro">
	    	<tr>
	          <td><bean:message key="lbl.sesion.iseNombreUsuario"  />:</td>
	          <td><input type="text" maxlength="25" size="25" disabled="disabled" name="usuarioNombre" value="${form.iseNombreUsuario}"></td>
	        </tr>
	    </logic:notPresent>
	    <logic:present name="filtro">
	    	<tr>
	          <td><bean:message key="lbl.persona.codigoUsuario" />:</td>
	          <td><html:text styleId="iseNombreUsuarioId" disabled="false" property="iseNombreUsuario" maxlength="25" size="25" /></td>
	        </tr>
	    </logic:present>    
	    <tr>
          <td><bean:message key="lbl.sesion.secRolRoles.rolNombre" />:</td>
          <td>
          
		        <table>	
		        	<tr>
		        		<td>
		        			<div id="rolesListDivId" >
					          	<logic:present name="rolesList" >
					          		<html:select property="rol2" value="${form.rol2}" >
					          			<html:optionsCollection name="rolesList" label="rolNombre" value="rolNombre"/>
					          		</html:select>
					          	</logic:present>
				         	</div>
				         </td>
          			</tr>
          		</table>
         </td>
        </tr>
        <logic:notPresent name="filtro">
	        <tr>
	          <td><bean:message key="lbl.sesion.isePorqueInactivacion" />:</td>
	          <td><html:textarea  property="isePorqueInactivacion" cols="25" rows="7"/></td>
	        </tr>
	    </logic:notPresent>
       <tr>
          <td colspan="2" align="center">
          	<logic:present name="filtro">
          		<%-- Cuando existe filtro entonces se guardara --%>
          		
          				<%-- Si el parametro es igual a cero servira para agregar un nuevo usuario --%>
          				
          					<html:submit property="accion" onclick="$('#pageId').val('3');">
				          		<bean:message key="cmd.sesion.agregar" />
				          	</html:submit>
				          	<html:submit property="accion" styleId="accionId" onclick="$('#pageId').val('0');$('#accionId').val('redirectLista');">
				          		<bean:message key="cmd.correo.return" />
				    		</html:submit>
				    		<input type="button" value="Limpiar" onclick="cleanFields('formId');">
				          	<input id="pageId" type="hidden" name="page" value="3"/>
          </logic:present>	          	
          				<%-- Si el parametro es uno indiciara que se va a editar el usuario --%>
		  <logic:notPresent name="filtro">
          					<html:submit property="accion" onclick="$('#pageId').val('0');">
				          		<bean:message key="cmd.sesion.editar"  />
				          	</html:submit>
				          	<html:submit property="accion">
				          		<bean:message key="cmd.sesion.eliminar" />
				          	</html:submit>
				          	<html:submit property="accion" onclick="$('#pageId').val('0');">
				          		<bean:message key="cmd.sesion.generaPass" />
				          	</html:submit>
				          	<html:submit property="accion" onclick="$('#pageId').val('0');">
		          				<bean:message key="cmd.sesion.cancelar" />
		          			</html:submit>
				          	<input id="pageId" type="hidden" name="page" value="4"/>
    	  </logic:notPresent>	
          	</td>
        </tr>
      </table>

      <html:hidden property="iseNombreUsuario" value="${form.iseNombreUsuario}"/>
      <html:hidden property="perId" value="${form.perId}"/>
      <html:hidden property="iseContrasenia" value="${form.iseContrasenia}"/>
      <html:hidden property="iseTipoSesion" value="${form.iseTipoSesion}"/>
      <html:hidden property="iseFechaActivacion" value="${form.iseFechaActivacion}"/>
      <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
      <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
      <html:hidden property="audFechaModificacion" value="${form.audFechaModificacion}"/>
      <html:hidden property="audUsuarioModificacion" value="${form.audUsuarioModificacion}"/>
      <html:hidden property="iseVecesUtilizado" value="${form.iseVecesUtilizado}"/>
      <html:hidden property="iseFechaInactivacion" value="${form.iseFechaInactivacion}"/>
      <html:hidden property="isePorqueInactivacion" value="${form.isePorqueInactivacion}"/>
      <html:hidden property="user" value="${form.user}"/>
   </html:form>
   <script type="text/javascript">
   		function loadNewRoles(){
   			var msgText = '<h3 style="color: red;">!!Cambio Tipo Sesion!!</h3>'+
     				 '<p style="font-size: 1.4em; font-weight: bold;">Esta  seguro que desea cambiar el Tipo de Sesion a la persona ?</p>';	
						$.prompt(msgText,{
		      				buttons:{Ok:true,Cancel:false}, 
		      				prefix:'brownJqi',
		      				callback : function(v,m){
		      					if(v == true){
		      						ajaxCallNormal('${pageContext.request.contextPath}/seguridad${_accion}.do',{page : '0',accion : 'ajaxRequestChangeSession',iseNombreUsuario : '${form.iseNombreUsuario}'},'rolesListDivId');
								}
		      				}
			});
   		}
   </script>
</div>
