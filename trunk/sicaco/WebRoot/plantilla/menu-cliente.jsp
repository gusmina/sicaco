<%@page import="com.cetia.sicaco.struts.Constantes"%>
<%@ taglib uri="http://struts-menu.sf.net/tag" prefix="menu"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<logic:present name="<%=Constantes.MENU_KEY%>">
	<menu:useMenuDisplayer name="ListMenu" repository="<%=Constantes.MENU_KEY%>" id="menu">
		<logic:iterate name="<%=Constantes.MENU_KEY%>" property="topMenusNames" id="menuName" type="java.lang.String">
			<menu:displayMenu name="${menuName}" />
		</logic:iterate>
	</menu:useMenuDisplayer>
</logic:present>
<logic:notPresent name="<%=Constantes.MENU_KEY%>">
	<bean:message key="msg.nomenu"/>
</logic:notPresent>