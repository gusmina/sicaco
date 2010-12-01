<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post" styleId="formId" >
  <table border="0">
  <logic:present name="filtro">
  <logic:iterate id="detallado" name="detalle" >
  	<tr>
      <td><label><bean:message key="lbl.sugerido.articulo" />:</label></td>
      <td>
    	<bean:write name="detallado" property="dfaPrecioTotal"/>
    	</td>
    </tr>
      <%--  	<html:text name="detallado" property="dfaPrecioTotal" styleClass="obligatorio" disabled="false"/>
      </td>
    </tr>
    <html:hidden name="detallado" property="dfaPrecioTotal" value="" /> --%>
  </logic:iterate>
    </logic:present>
<%--    
    <tr>
      <td colspan="2" align="center">
      	<logic:present name="filtro">
      		<%-- Solo acciones de busqueda un boton nada mas --%>
 <%--     		<logic:equal name="filtro" value="1">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" >
	        		<bean:message key="cmd.empresa.salvar"/>
	        	</html:submit>
	        	<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.encabezado.cancelar" />
	        	</html:submit>
        	</logic:equal>
        	<logic:equal name="filtro" value="0">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.empresa.actualizar" />
	        	</html:submit>
	        </logic:equal>
        </logic:present>
      </td>
    </tr>
    
     --%>
  </table>
  
</html:form>