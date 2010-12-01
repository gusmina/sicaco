<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
	<html:form action="${_accion}" method="post"  styleId="formId">
      <table border="0" align="center">
      	<tr>
          <td><LABEL>
          	
          <bean:message key="lbl.tga.nombre" />:</LABEL></td>
          <td>
          	<html:text property="tgaNombre" styleClass="obligatorio" maxlength="25" value="${form.tgaNombre}"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
        </tr>
        <tr>
          <td><LABEL><bean:message key="lbl.tga.descripcion" />:</LABEL></td>
          <td>
          	<html:text property="tgaDescripcion" styleClass="obligatorio" maxlength="300" value="${form.tgaDescripcion}"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
        </tr>
        <tr>
       		<td></td>
       	   <td>
       	   		<html:submit property="accion" onclick="$('#pageId').val('4');">
		      		<bean:message key="cmd.tga.guardar" />
		       	</html:submit>
			    <input type="button" value="Limpiar" onclick="cleanFields('formId');">
			   
       	   </td>
      </tr>
      </table>
       <input id="pageId" type="hidden" name="page" value="3"/>
      <html:hidden property="tgaId" value="${form.tgaId}"/>
      <html:hidden property="mdf" value="${form.mdf}"/> 
    </html:form>
