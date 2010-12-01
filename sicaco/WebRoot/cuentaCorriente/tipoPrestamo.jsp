<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<tr>
		<td>
			<label><bean:message key="lbl.tpr.tprNombre"/></label>:
		</td>
		<td>
			<html:text styleClass="obligatorio" maxlength="100" size="25" property="tprNombre" value="${form.tprNombre}" styleId="tprNombre"/> 
			<span><bean:message key="msg.obligatorio"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.tpr.tprDescripcion" /></label>:
		</td>
		<td>
			<html:textarea rows="3" cols="25" property="tprDescripcion" value="${form.tprDescripcion}" styleId="tprDescripcion"/> 
			
		</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.tpr.tprPlanMeses" /></label>:
			
		</td>
		<td>
   			<html:select property="ctaPlmPlanMeses.plmId" value="${form.ctaPlmPlanMeses.plmId}" styleClass="obligatorio">
   				
				<html:optionsCollection  label="plmNombre" name="lstPlmPlanMes" value="plmId"/>     					
			</html:select>
			<span><bean:message key="msg.obligatorio"/></span>
     	</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.tpr.tprLineaPrestamo" /></label>:
			
		</td>
		<td>
   			<html:select property="ctaLprLineaPrestamo.lprId" value="${form.ctaLprLineaPrestamo.lprId}" styleClass="obligatorio">
   				
				<html:optionsCollection  label="lprNombre" name="lstLprLineaPrestamo" value="lprId"/>     					
			</html:select>
			<span><bean:message key="msg.obligatorio"/></span>
     	</td>
	</tr>
	<tr>
		<td>
			<label><bean:message key="lbl.tpr.tprTasaInteres" /></label>:
			
		</td>
		<td>
   			<html:select property="ctaTinTasaInteres.tinId" value="${form.ctaTinTasaInteres.tinId}" styleClass="obligatorio">
   				
				<html:optionsCollection  label="tinNombre" name="lstTinTasaInteres" value="tinId"/>     					
			</html:select>
			<span><bean:message key="msg.obligatorio"/></span>
     	</td>
	</tr>
	<tr>
		<td>
			<html:submit property="accion" >
				<bean:message key="cmd.tpr.guardar" />
			</html:submit> 
		</td>
		<td>
			<input type="button" value="Limpiar" onclick="cleanFields('formId');">
		</td>
	</tr>
</table> 
<input id="pageId" type="hidden" name="page" value="3">
<html:hidden property="tprId" value="${form.tprId}"/>
<html:hidden property="mdf" value="${form.mdf}"/> 
</html:form>
