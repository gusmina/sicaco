<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
	<html:form action="${_accion}" method="post"  styleId="formId">
      <table border="0">
      	<tr>
          <td><LABEL>
          	
          <bean:message key="lbl.tfi.nombre" />:</LABEL></td>
          <td>
          	<html:text property="tfiNombre" styleClass="obligatorio"  value="${form.tfiNombre}"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
        </tr>
        <tr>
          <td><LABEL><bean:message key="lbl.tfi.des" />:</LABEL></td>
          <td>
          	<html:text property="tfiDescripcion" styleClass="obligatorio" value="${form.tfiDescripcion}"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
        </tr>
       <tr>
       		<td></td>
       	   <td>
       	   		<html:submit property="accion" onclick="$('#pageId').val('4');">
		      		<bean:message key="cmd.tfi.guardar" />
		       	</html:submit>
			    <input type="button" value="Limpiar" onclick="cleanFields('formId');">
			   
       	   </td>
      </tr>
      </table>
       <input id="pageId" type="hidden" name="page" value="3"/>
      <html:hidden property="tfiId" value="${form.tfiId}"/>
      <html:hidden property="mdf" value="${form.mdf}"/> 
    </html:form>
