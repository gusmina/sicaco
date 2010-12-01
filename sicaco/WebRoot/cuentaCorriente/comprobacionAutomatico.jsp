<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

	 <script language="javascript" type="text/javascript">
     <!--
     window.setTimeout('window.location="${link}";',4000);
     // -->
 </script>

<html:form action="${_accion}" method="post" styleId="formId">
	<label>
		<bean:message key="lbl.pre.comprobacionTipoMensaje"/>
	</label>
	<html:link href="${link}"><bean:message key="lbl.pre.clickAqui"/></html:link>
</html:form>