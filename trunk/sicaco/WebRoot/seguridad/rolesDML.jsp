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

#lista-datos .menuSeleccion {
	border: 1px solid grey;
}

#lista-datos .menuSeleccion ul {
	list-style: none;
}

#lista-datos .menuSeleccion .parent-menu {
	background-image: url(/cetia/imagenes/menu/add.gif);
	background-repeat: no-repeat;
	background-position: left top;
	padding: 0px;
	padding-left: 17px;
	cursor: pointer;
}

#lista-datos .menuSeleccion .opcion-menu {
	background-image: url(/cetia/imagenes/menu/arrowRight.gif);
	background-repeat: no-repeat;
	background-position: left top;
	padding: 0px;
	padding-left: 17px;
	cursor: pointer;
}

</style>
<script type="text/javascript">
	$(document).ready(function(){
    	$("#rolNombreId").alpha();
    	
    	$('.parent-menu').each(function(i) {
    		$(this).next('ul').hide();
    		$(this).click(function() {
    			$(this).next('ul').slideToggle('slow');
    		});
    	});
  });
</script>
<script type="text/javascript">
	$(document).ready(function(){
    	$("#rolNombreId").alphanumeric({allow:" "});
    	$("#rolDescripcionId").alphanumeric({allow:" (),;."});
  });
</script>
<div id="rolesIdDml">

 	<html:form action="${_accion}" method="post"  styleId="formId">
      <table border="0">
      	<logic:present name="filtro">
      	<logic:equal name="filtro" value="1">
      		
      				<tr>
			      		<td>
			      			<label><bean:message key="lbl.roles.rolNombre"/></label>
			      		</td>
			      		<td>
			      			<html:text styleId="rolNombreId" property="rolNombre" maxlength="15" size="15" styleClass="obligatorio" />
			      		</td>
			      	</tr>
		<tr>
      		<td>
      			<label><bean:message key="lbl.roles.rolTipoSesion"/></label>
      		</td>
      		<td>
      			
      			<html:select  property="rolTipoSesion" styleClass="obligatorio" styleId="_rolTipoSesion">
      				<html:option value="">
      					---
      				</html:option>
      				<html:option value="A">
      					<bean:message key="lbl.roles.rolTipoSesionA"/>
      				</html:option>
      				<html:option value="C">
      					<bean:message key="lbl.roles.rolTipoSesionC"/>
      				</html:option>
      			</html:select>
      			<span><bean:message key="msg.obligatorio"/></span>
      		</td>
      	</tr>
      	<tr>
      		<td>
      			<label><bean:message key="lbl.roles.rolDescripcion"/></label>
      		</td>
      		<td>
      			<html:textarea cols="25" rows="4" property="rolDescripcion" styleClass="obligatorio" styleId="rolDescripcionId"/>
      			<span><bean:message key="msg.obligatorio"/></span>
      		</td>
      	</tr>
      	</logic:equal>
      	<logic:equal name="filtro" value="0">
      	<logic:present name="boton">
      	<logic:equal name="boton" value="0">
	      	<tr>
				      		<td>
				      			<label><bean:message key="lbl.roles.rolNombre"/></label>
				      		</td>
				      		<td>
				      			<html:text styleId="rolNombreId" property="rolNombre" maxlength="15" size="15" styleClass="obligatorio" 
				      				onblur="ajaxCallNormal('${pageContext.request.contextPath}/seguridad${_accion}.do',{page : '0',accion : 'ajaxRequestWriterValidator',rolNombre : $('#rolNombreId').val()},'findRolById');"/>
				      			<span><bean:message key="msg.obligatorio"/></span>
				      		</td>
			</tr>
			<tr>
	      		<td colspan="2">
	      			<div style="position: relative; left: 7.6em;" id="findRolById">
	      			</div>
	      		</td>
      		</tr>
      	</logic:equal>
      	<logic:equal name="boton" value="1">
      	<tr>
      		<td>
      			<label><bean:message key="lbl.roles.rolNombre"/></label>
      		</td>
      		<td>
      			<html:text maxlength="15" size="15" property="rolNombre" styleClass="obligatorio" disabled="true"/>
      			<span><bean:message key="msg.obligatorio"/></span>
      		</td>
      	</tr>
      	</logic:equal>
      	</logic:present>
      	<tr>
      		<td>
      			<label><bean:message key="lbl.roles.rolTipoSesion"/></label>
      		</td>
      		<td>
      			<html:select property="rolTipoSesion" styleClass="obligatorio" styleId="_rolTipoSesion">
      				<html:option value="A">
      					<bean:message key="lbl.roles.rolTipoSesionA"/>
      				</html:option>
      				<html:option value="C">
      					<bean:message key="lbl.roles.rolTipoSesionC"/>
      				</html:option>
      			</html:select>
      			<span><bean:message key="msg.obligatorio"/></span>
      		</td>
      	</tr>
      	<tr>
      		<td>
      			<label><bean:message key="lbl.roles.rolDescripcion"/></label>
      		</td>
      		<td>
      			<html:textarea cols="25" rows="4" property="rolDescripcion" styleClass="obligatorio"/>
      			<span><bean:message key="msg.obligatorio"/></span>
      		</td>
      	</tr>
      	</logic:equal>
      	</logic:present>
		<tr>
          <td colspan="2" align="center">
          	<logic:present name="filtro">
          		<%-- Cuando no tiene filtro van a ser acciones de DML --%>
          		<logic:equal name="filtro" value="0">
          			<logic:present name="boton">
          				<%-- Si el parametro es igual a cero servira para agregar un nuevo usuario --%>
          				<logic:equal name="boton" value="0">
          					<html:submit property="accion" >
				          		<bean:message key="cmd.roles.agregar" />
				          	</html:submit>
				          	<input id="pageId" type="hidden" name="page" value="3"/>
          				</logic:equal>
          				<%-- Si el parametro es uno indiciara que se va a editar el usuario --%>
          				<logic:equal name="boton" value="1">
          					<html:submit property="accion">
				          		<bean:message key="cmd.roles.modificar" />
				          	</html:submit>
				          	<html:submit property="accion">
				          		<bean:message key="cmd.roles.eliminar" />
				          	</html:submit>
				          	<script type="text/javascript">
				          	$('#_rolTipoSesion').attr('disabled','disabled')
				          	$('#_rolTipoSesion').before('<input type="hidden" name="'+$('#_rolTipoSesion').attr('name')+'" value="'+$('#_rolTipoSesion').val()+'" />')
				          	</script>
          				</logic:equal>
          				
          			</logic:present>
          			<html:submit property="accion" onclick="$('#pageId').val('0');">
		          		<bean:message key="cmd.roles.cancelar" />
		          	</html:submit>
          		</logic:equal>
          		<%-- Solo acciones de busqueda un boton nada mas --%>
          		<logic:equal name="filtro" value="1">
          			<input type="hidden" id="pageId" name="page" value="3"/>
          			<html:submit property="accion" onclick="$('#pageId').val('0');">
		          		<bean:message key="cmd.roles.buscar" />
		          	</html:submit>
		          	<html:submit property="accion" >
		          		<bean:message key="cmd.roles.agregar" />
		          	</html:submit>
		          	<input type="button" value="Limpiar" onclick="cleanFields('formId');">
          		</logic:equal>
          	</logic:present>
          </td>
        </tr>
      </table>
      <logic:equal name="filtro" value="0">
      		<logic:present name="boton">
      			<logic:equal name="boton" value="1">
      				<html:hidden property="rolNombre"/>
      			</logic:equal>
      		</logic:present>
      </logic:equal>
      <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
      <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
    </html:form>
 </div>