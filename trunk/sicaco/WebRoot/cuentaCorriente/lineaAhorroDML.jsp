<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.lah.lahNombre"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="50" size="25" property="lahNombre" value="${form.lahNombre}" styleId="lahNombre"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.lah.lahDescripcion" /></label>:
		</td>
		<td>
			<html:textarea styleClass="obligatorio" rows="3" cols="24" property="lahDescripcion" value="${form.lahDescripcion}" styleId="lahDescripcion"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.lah.lahDesde" /></label>:
			
		</td>
		<td>
			<html:text onkeyup="mask(this);" property="lahDesde" value="${form.lahDesde}" styleId="lahDesde"
						styleClass="obligatorio"	maxlength="10" size="20" />
			<input style="height: 18px; position: relative; left: -0.2em;"
						id="button_lahDesde" type="button" value="...." />
		</td>
		
	</tr>
	<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "lahDesde",
				              button        : "button_lahDesde",
				              align         : "Br"
				            });
				            function handlerCombo(){
				            	$(lahDesde).toggle();
				            }

   </script>
   <tr>
		<td>
			<label><bean:message key="lbl.lah.lahHasta" /></label>:
			
		</td>
		<td>
			<html:text onkeyup="mask(this);" property="lahHasta" value="${form.lahHasta}" styleId="lahHasta"
						styleClass="obligatorio"	maxlength="10" size="20" />
		
			<input style="height: 18px; position: relative; left: -0.2em;"
						id="button_lahHasta" type="button" value="...." />
		</td>
		
	</tr>
	<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "lahHasta",
				              button        : "button_lahHasta",
				              align         : "Br"
				            });
				            function handlerCombo(){
				            	$(lahHasta).toggle();
				            }

   </script>
	<tr>
		<td align="right">
			<html:submit property="accion" >
				<bean:message key="cmd.lah.guardar" />
			</html:submit> 
		</td>
		<td align="left">
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="lahId" value="${form.lahId}"/>
<html:hidden property="mdf" value="${form.mdf}"/> 
</html:form>
