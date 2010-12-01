<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<script type="text/javascript">
	$(document).ready(function(){
    	$("#empNombreId").alphanumeric({allow:" ."});
    	$("#empRegistroId").numeric({allow:"-"});
    	$("#empGiroId").alphanumeric({allow:" .-,;"});
    	$("#empDireccionId").alphanumeric({allow:" .-,;()"});
    	$("#empTelId").numeric({allow:"-"});
  });
</script>

<html:form action="${_accion}" method="post" focus="empNombre" styleId="formId" >
  <table border="0">
  <logic:present name="filtro">
  <logic:equal name="filtro" value="0" >
  	<tr>
      <td><label><bean:message key="lbl.empresa.empNombre" />:</label></td>
      <td>
       	<html:text size="15" property="empNombre" styleClass="obligatorio" disabled="true"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.empresa.empNit" />:</label></td>
      <td>
       	<html:text property="empNit" maxlength="14" size="15" styleClass="obligatorio" disabled="true"/>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.empresa.empRegistro" />:</label></td>
      <td>
       	<html:text size="15" property="empRegistro" styleClass="obligatorio" disabled="true"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.empresa.empGiro" />:</label></td>
      <td>
      	<html:textarea property="empGiro" styleClass="obligatorio" cols="30" rows="3" disabled="true"></html:textarea>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.empresa.empDireccion" />:</label></td>
      <td>
      	<html:textarea property="empDireccion" styleClass="obligatorio" cols="30" rows="3" disabled="true"></html:textarea>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.empresa.empTel" />:</label></td>
      <td>
       	<html:text size="15" property="empTel" styleClass="obligatorio" disabled="true"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
    	<td><label><bean:message key="lbl.empresa.empIniOp" />:</label></td>
    	<td>
          	<html:text style="float:left;" styleId="empIniOp" onkeyup="mask(this);" value="${form.empIniOp}" property="empIniOp" maxlength="10" size="10" disabled="true"/>
        	<input  style="height: 18px;" id="button_empIniOp" type="button"  value="..." disabled="disabled"/>
        	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d">
        	<script type="text/javascript">
		            Calendar.setup({
		              inputField    : "empIniOp",
		              button        : "button_empIniOp",
		              align         : "Br"
		            });
		    </script>
		  </td>
    </tr>
  </logic:equal>
  <logic:equal name="filtro" value="1">
  	<tr>
      <td><label><bean:message key="lbl.empresa.empNombre" />:</label></td>
      <td>
       	<html:text maxlength="50" property="empNombre" styleId="empNombreId" styleClass="obligatorio" disabled="false"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.empresa.empNit" />:</label></td>
      <td>
       	<html:text property="empNit" maxlength="14" size="15" styleClass="obligatorio" disabled="false"/>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.empresa.empRegistro" />:</label></td>
      <td>
       	<html:text property="empRegistro" styleId="empRegistroId" size="15" maxlength="200" styleClass="obligatorio" disabled="false"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.empresa.empGiro" />:</label></td>
      <td>
      	<html:textarea property="empGiro" styleId="empGiroId" styleClass="obligatorio" cols="30" rows="3" disabled="false"></html:textarea>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.empresa.empDireccion" />:</label></td>
      <td>
      	<html:textarea property="empDireccion" styleId="empDireccionId" styleClass="obligatorio" cols="30" rows="3" disabled="false"></html:textarea>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.empresa.empTel" />:</label></td>
      <td>
       	<html:text property="empTel" styleId="empTelId" size="15" maxlength="15" styleClass="obligatorio" disabled="false"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
    	<td><label><bean:message key="lbl.empresa.empIniOp" />:</label></td>
    	<td>
          	<html:text style="float:left;" styleId="empIniOp" onkeyup="mask(this);" value="${form.empIniOp}" property="empIniOp" maxlength="10" size="10" disabled="false"/>
        	<input  style="height: 18px;" id="button_empIniOp" type="button"  value="..." />
        	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d">
        	<script type="text/javascript">
		            Calendar.setup({
		              inputField    : "empIniOp",
		              button        : "button_empIniOp",
		              align         : "Br"
		            });
		    </script>
		  </td>
    </tr>
    </logic:equal>
    </logic:present>
    <tr>
      <td colspan="2" align="center">
      	<logic:present name="filtro">
      		<%-- Solo acciones de busqueda un boton nada mas --%>
      		<logic:equal name="filtro" value="1">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" >
	        		<bean:message key="cmd.empresa.salvar"/>
	        	</html:submit>
	        	<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.empresa.cancelar" />
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
  </table>
  <html:hidden property="empId" value="${form.empId}"/>
  <html:hidden property="empNombre" value="${form.empNombre}"/>
  <html:hidden property="empNit" value="${form.empNit}"/>
  <html:hidden property="empDireccion" value="${form.empDireccion}"/>
  <html:hidden property="empTel" value="${form.empTel}"/>
  <html:hidden property="empIniOp" value="${form.empIniOp}"/>
  <html:hidden property="empRegistro" value="${form.empRegistro}"/>
  <html:hidden property="empGiro" value="${form.empGiro}"/>
  <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
  <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
</html:form>