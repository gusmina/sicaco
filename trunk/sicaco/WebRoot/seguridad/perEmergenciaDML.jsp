<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#pemPrimerNombreId").alpha();
    	$("#pemSegundoNombreId").alpha();
    	$("#pemTercerNombreId").alpha();
    	$("#pemPrimerApellidoId").alpha();
    	$("#pemSegundoApellidoId").alpha();
    	$("#pemApellidoCasadaId").alpha();
    	$("#pemTelefonoId").numeric();
  });
</script>

<div>
  <html:form action="${_accion}" method="post" focus="pemId" styleId="formId">
      <table border="0" align="center">
      <logic:present name="filtro">
      <logic:equal name="filtro" value="0">
        <tr>
          <td><label><bean:message key="lbl.emergencia.pemPrimerNombre" />:</label></td>
          <td>
          	<html:text property="pemPrimerNombre" styleClass="obligatorio" size="15" maxlength="25" styleId="pemPrimerNombreId"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
          <td><label><bean:message key="lbl.emergencia.pemSegundoNombre" />:</label></td>
          <td><html:text property="pemSegundoNombre" styleId="pemSegundoNombreId" size="15" maxlength="25"/></td>
        </tr>
        <tr>
          <td><label><bean:message key="lbl.emergencia.pemTercerNombre" />:</label></td>
          <td><html:text property="pemTercerNombre" styleId="pemTercerNombreId" size="15" maxlength="25"/></td>
        </tr>
        <tr>
          <td><label><bean:message key="lbl.emergencia.pemPrimerApellido" />:</label></td>
          <td>
          	<html:text property="pemPrimerApellido" styleClass="obligatorio" styleId="pemPrimerApellidoId" size="15" maxlength="25"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
          <td><label><bean:message key="lbl.emergencia.pemSegundoApellido" />:</label></td>
          <td><html:text property="pemSegundoApellido" styleId="pemSegundoApellidoId" size="15" maxlength="25"/></td>
        </tr>
        <tr>
          <td><label><bean:message key="lbl.emergencia.pemApellidoCasada" />:</label></td>
          <td><html:text property="pemApellidoCasada" styleId="pemApellidoCasadaId" size="15" maxlength="25"/></td>
        </tr>
        <tr>
          <td><label><bean:message key="lbl.emergencia.secParParentesco" />:</label></td>
          <td>
          	<html:select property="secParParentesco.parId" styleClass="obligatorio">
				<html:optionsCollection  label="parDescripcion" name="parentesco" value="parId"/>     					
		  	</html:select>
          </td>          
        </tr>
        <tr>
          <td><label><bean:message key="lbl.emergencia.pemTelefono" />:</label></td>
          <td><html:text property="pemTelefono" size="15" maxlength="15" styleId="pemTelefonoId" styleClass="obligatorio"/>
          <span><bean:message key="msg.obligatorio"/></span></td>
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
          					<html:submit property="accion" onclick="$('#pageId').val('4');">
				          		<bean:message key="cmd.emergencia.agregar" />
				          	</html:submit>
				        </logic:equal>
          				<%-- Si el parametro es uno indiciara que se va a editar el usuario --%>
          				<logic:equal name="boton" value="1">
          					<html:submit property="accion">
				          		<bean:message key="cmd.emergencia.modificar" />
				          	</html:submit>
				          	<html:submit property="accion">
				          		<bean:message key="cmd.emergencia.eliminar" />
				          	</html:submit>
          				</logic:equal>
          				<input id="pageId" type="hidden" name="page" value="3">
          			</logic:present>
          			<html:submit property="accion" onclick="$('#pageId').val('0');">
		          		<bean:message key="cmd.emergencia.cancelar" />
		          	</html:submit>
          		</logic:equal>
          		<%-- Solo acciones de busqueda un boton nada mas --%>
          		<logic:equal name="filtro" value="1">
          			<html:submit property="accion">
		          		<bean:message key="cmd.emergencia.mostrar" />
		          	</html:submit>
		          	<logic:equal name="asoc" value="false" >
		          		          	<html:submit property="accion" styleId="accionId" onclick="$('#accionId').val('redirectLista');">
				          <bean:message key="cmd.emergencia.return" />
				    </html:submit>
		          	</logic:equal>
		          	<logic:equal name="asoc" value="true" >
		          		<html:submit property="accion" styleId="accionId" onclick="$('#pageId').val('0')">
				          <bean:message key="cmd.emergencia.redirectListaAsc" />
				    	</html:submit>
		          	</logic:equal>
          		</logic:equal>
          	</logic:present>
          </td>
        </tr>
      </table>
      <html:hidden property="asoc"/>
      <html:hidden property="perId" value="${form.perId}"/>
      <html:hidden property="pemId" value="${form.pemId}"/>
      <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
      <html:hidden property="audFechaModificacion" value="${form.audFechaModificacion}"/>
      <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
      <html:hidden property="audUsuarioModificacion" value="${form.audUsuarioModificacion}"/>
    </html:form>
</div>