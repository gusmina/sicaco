<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post" styleId="formId">
	<table>
		<tr>
			<td><label><bean:message key="lbl.mov.fechaFin"/>:</label></td>
			<td>
	          	<html:text style="float:left;" styleId="fechaIniId" onkeyup="mask(this);" value="${form.fechaIni}" property="fechaIni" maxlength="10" size="10"/>
	        	<input  style="height: 18px;" id="button_fechaIniId" type="button"  value="..."/>
	        	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d">
	        	<script type="text/javascript">
			            Calendar.setup({
			              inputField    : "fechaIniId",
			              button        : "button_fechaIniId",
			              align         : "Br"
			            });
			    </script>
			  </td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td>
	          	<html:text style="float:left;" styleId="fechaFinId" onkeyup="mask(this);" value="${form.fechaFin}" property="fechaFin" maxlength="10" size="10"/>
	        	<input  style="height: 18px;" id="button_fechaFinId" type="button"  value="..."/>
	        	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d">
	        	<script type="text/javascript">
			            Calendar.setup({
			              inputField    : "fechaFinId",
			              button        : "button_fechaFinId",
			              align         : "Br"
			            });
			    </script>
			  </td> 
		</tr>
		<tr>
			<td colspan="2">
				<html:submit property="accion">
	  				<bean:message key="cmd.mov.buscar" />
	  			</html:submit>
	  			<html:submit property="accion">
	  				<bean:message key="cmd.mov.return" />
	  			</html:submit>
	  		</td>
  		</tr>
	</table>
	<html:hidden property="bodega" value="${form.bodega}"/>
	<html:hidden property="artCod" value="${form.artCod}"/>
	<html:hidden property="fechaIni" value="${form.fechaIni}"/>
	<html:hidden property="fechaFin" value="${form.fechaFin}"/>
</html:form>
