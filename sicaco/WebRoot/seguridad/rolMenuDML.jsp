<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%--<logic:notPresent parameter="ajax">
<div id="idPerForm"> --%>
 	<html:form action="${_accion}" method="post" styleId="formId">
	    
      <table border="0">
      <logic:present name="filtro">
      <logic:equal name="filtro" value="1">
      	<tr>
      		<td>
      			<label><bean:message key="lbl.rolMenu.id.secRolRoles.rolNombre"/></label>
      		</td>
      		<td>
      			
      				<html:select property="id.secRolRoles.rolNombre" styleClass="obligatorio">
      					<logic:iterate id="var" name="listRol">
      							<option value="${var}">${var}</option>
      					</logic:iterate>
      				</html:select>
      				<span><bean:message key="msg.obligatorio"/></span>
      		</td>
      	</tr>
      	
      	<tr>
      		<td>
      			<label><bean:message key="lbl.rolMenu.id.secMopMenuOpcion.mopName"/></label>
      		</td>
      		<td>
      			<html:select property="id.secMopMenuOpcion.mopName"  styleClass="obligatorio">
   					<logic:iterate id="var2" name="listMenu" >
   						<option value="${var2}">${var2}</option>
   					</logic:iterate>
   				</html:select>
   				<span><bean:message key="msg.obligatorio"/></span>
      		</td>
      	</tr>
    
      </logic:equal>
   	  <logic:equal name="filtro" value="0">
      	<logic:present name="boton">
      		<logic:equal name="boton" value="0">
		      	<tr>
		      		<td>
		      			<label><bean:message key="lbl.rolMenu.id.secRolRoles.rolNombre"/></label>
		      		</td>
		      		<td>
		      			<html:select property="id.secRolRoles.rolNombre"  styleClass="obligatorio">
							<html:optionsCollection  label="rolNombre" name="listRol" value="rolNombre"/>     					
					  	</html:select>
		      			<span><bean:message key="msg.obligatorio"/></span>
		      		</td>
		      	</tr>
      	<%-- 
		      	<tr>
		      		<td>
		      			<label><bean:message key="lbl.rolMenu.id.secMopMenuOpcion.mopName"/></label>
		      		</td>
		      		<td>
		      			<html:select property="id.secMopMenuOpcion.mopName"  styleClass="obligatorio">
							<html:optionsCollection  label="mopName" name="listMenu" value="mopName"/>     					
					  	</html:select>
		      			<span><bean:message key="msg.obligatorio"/></span>
		      		</td>
		      	</tr>
		--%>
      		</logic:equal>
      		<logic:equal name="boton" value="1">
		      	<tr>
		      		<td>
		      			<label><bean:message key="lbl.rolMenu.id.secRolRoles.rolNombre"/></label>
		      		</td>
		      		<td>
		      			<html:text property="id.secRolRoles.rolNombre" maxlength="15" size="15" styleClass="obligatorio" disabled="true"/>
		      			<span><bean:message key="msg.obligatorio"/></span>
		      		</td>
		      	</tr>
      	 
		      	<tr>
		      		<td>
		      			<label><bean:message key="lbl.rolMenu.id.secMopMenuOpcion.mopName"/></label>
		      		</td>
		      		<td>
		      			<html:text property="id.secMopMenuOpcion.mopName" maxlength="15" size="15" styleClass="obligatorio" disabled="true"/>
		      			<span><bean:message key="msg.obligatorio"/></span>
		      		</td>
		      	</tr>
      		</logic:equal>
      		<logic:equal name="boton" value="2">
		      	<tr>
		      		<td>
		      			<label><bean:message key="lbl.rolMenu.id.secRolRoles.rolNombre"/></label>
		      		</td>
		      		<td>
		      			<html:text property="id.secRolRoles.rolNombre" maxlength="15" size="15" styleClass="obligatorio" disabled="true"/>
		      			<span><bean:message key="msg.obligatorio"/></span>
		      		</td>
		      	</tr>
		 	</logic:equal>
      	</logic:present>
      </logic:equal>
      </logic:present>
		<tr>
          <td colspan="2" align="center">
          	<logic:present name="filtro">
          		<%-- Cuando no tiene filtro van a ser acciones de DML --%>
          		<logic:equal name="filtro" value="0">
          			<logic:present name="boton">
          				<%-- Si el parametro es igual a cero servira para agregar un nuevo usuario  --%>
          				<logic:equal name="boton" value="0">
          					<html:submit property="accion" onclick="$('#pageId').val('4');">
				          		<bean:message key="cmd.rolMenu.seleccionaRol" />
				          	</html:submit>
				          	<html:submit property="accion">
				          		<bean:message key="cmd.rolMenu.cancelar" />
				          	</html:submit>
          				</logic:equal>
          				<%-- Si el parametro es uno indiciara que se va a editar el usuario --%>
          				<logic:equal name="boton" value="1">
          					<label><bean:message key="lbl.rolMenu.seguro"/></label>
          					<br>
				          	<html:submit property="accion">
				          		<bean:message key="cmd.rolMenu.eliminarSi" />
				          	</html:submit>
				          	<html:submit property="accion">
				          		<bean:message key="cmd.rolMenu.eliminarNo" />
				          	</html:submit>
          				</logic:equal>
          				
          			</logic:present>
          			
          		</logic:equal>
          		<%-- Solo acciones de busqueda un boton nada mas --%>
          		<logic:equal name="filtro" value="1">
          			<html:submit property="accion">
		          		<bean:message key="cmd.rolMenu.buscar" />
		          	</html:submit>
		          	<html:submit property="accion">
		          		<bean:message key="cmd.rolMenu.nueva" />
		          	</html:submit>
          		</logic:equal>
          	</logic:present>
          </td>
        </tr>
      </table>
      <html:hidden property="id.secRolRoles.rolNombre"/>
    </html:form>