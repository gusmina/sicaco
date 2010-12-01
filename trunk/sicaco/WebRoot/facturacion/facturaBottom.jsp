<%--<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post" focus="fenTotalVenta" styleId="formId" >
    <logic:present name="filtro">
	<logic:equal value="1" name="filtro">
  <table border="0" align="right">
    <tr>
    	<td align="right"><label><bean:message key="lbl.encabezado.fenTotalVenta" />:</label></td>
      <td>
       	<html:text property="fenTotalVenta" styleClass="obligatorio" disabled="true"/>
      </td>
    </tr>
    <tr>
    	<td align="right"><label><bean:message key="lbl.encabezado.fenTotalVentasExentas" />:</label></td>
      <td>
       	<html:text property="fenTotalVentasExentas" styleClass="obligatorio" disabled="true"/>
      </td>
    </tr>
    <tr>
    	<td align="right"><label><bean:message key="lbl.encabezado.fenIvaRetenido" />:</label></td>
      <td>
       	<html:text property="fenIvaRetenido" styleClass="obligatorio" disabled="true"/>
      </td>
    </tr>

  </table>
<br><br><br><br><br><br><br><br>
 <table border="0" align="center">
    <tr>
      <td >
   		<input type="hidden" id="pageId" name="page" value="3" />
   		<html:submit property="accion" >
      		<bean:message key="cmd.encabezado.calcular"/>
      	</html:submit>
     </td><td>
     <logic:present name="compra">
     <logic:equal name="compra" value="1">
   	  	<html:submit property="accion" >
      		<bean:message key="cmd.encabezado.Guardar"/>
      	</html:submit>
      </logic:equal>
      <logic:equal name="compra" value="2">
      	<html:submit property="accion" >
      		<bean:message key="cmd.encabezado.Guardar2"/>
      	</html:submit>
      </logic:equal>
      </logic:present>
	  </td><td>
   	  	<html:submit property="accion" >
      		<bean:message key="cmd.encabezado.cancelar"/>
      	</html:submit>
	  </td>
    </tr>
  </table>
  </logic:equal>
  </logic:present>
  <html:hidden property="fenTotalVenta" value="${form.fenTotalVenta}"/>
  <html:hidden property="fenTotalVentasExentas" value="${form.fenTotalVentasExentas}"/>
  <html:hidden property="fenIvaRetenido" value="${form.fenIvaRetenido}"/>
  
</html:form>

--%>