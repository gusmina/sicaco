<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#proCodigoId").numeric();
    	$("#proNombreId").alphanumeric({allow:"-/&#., "});
    	$("#proRegistroId").alphanumeric({allow:"-"});
    	$("#proGiroId").alphanumeric({allow:"-., "});
    	$("#proNumeroTelefonoId").numeric();
    	$("#proDireccionId").alphanumeric({allow:" ,-;."});
    	$("#proHorarioOficinaId").numeric({allow:" -:amp"});
    	$("#proLimiteCreditoId").numeric({allow:"."});
    	$("#proNitId").numeric({allow:"-"});
    	$("#proDiasPagoId").numeric();
  });
</script>

<html:form action="${_accion}" method="post" focus="proCodigo" styleId="formId" >
  <table border="0" align="center">
  	<tr>
      <td><label><bean:message key="lbl.proveedor.proCodigo" />:</label></td>
      <td>
      	<logic:present name="filtro">
       		<logic:equal value="1" name="filtro">
       			<html:text property="proCodigo" styleId="proCodigoId" styleClass="obligatorio" readonly="true" maxlength="25" size="20"/>
      		</logic:equal>
      		<logic:equal value="2" name="filtro">
       			<html:text property="proCodigo" styleId="proCodigoId" styleClass="obligatorio" maxlength="300" size="20"/>
      		</logic:equal>
      	</logic:present>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
  	<tr>
      <td><label><bean:message key="lbl.proveedor.proNombre" />:</label></td>
      <td>
       	<html:text property="proNombre" styleId="proNombreId" styleClass="obligatorio" maxlength="300" size="20"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.proveedor.proRegistro" />:</label></td>
      <td>
       	<html:text property="proRegistro" styleId="proRegistroId" styleClass="obligatorio" maxlength="25" size="20"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.proveedor.proGiro" />:</label></td>
      <td>
       	<html:text property="proGiro" styleId="proGiroId" styleClass="obligatorio" maxlength="25" size="20"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.proveedor.proNit" />:</label></td>
      <td>
       	<html:text property="proNit" onkeyup="maskNit(this);" styleId="proNitId" styleClass="obligatorio" maxlength="17" size="17"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.proveedor.invTprTipoProveedor.tprNombre" />:</label></td>
      <td>
       	<html:select property="invTprTipoProveedor.tprId" styleClass="obligatorio">
			<html:optionsCollection  label="tprNombre" name="tipoProveedor" value="tprId"/>     					
		</html:select>
		<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.proveedor.ctrPaiPais.paiNombre" />:</label></td>
      <td>
       	<html:select property="ctrPaiPais.paiId" styleClass="obligatorio">
			<html:optionsCollection  label="paiNombre" name="pais" value="paiId"/>     					
		</html:select>
		<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <logic:present name="filtro">
    <logic:equal name="filtro" value="1">
    <tr>
      <td><label><bean:message key="lbl.proveedor.proDireccion" />:</label></td>
      <td>
       	<html:textarea property="proDireccion" styleId="proDireccionId" styleClass="obligatorio" rows="3" cols="25"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.proveedor.proNumeroTelefono" />:</label></td>
      <td>
       	<html:text property="proNumeroTelefono" styleId="proNumeroTelefonoId" styleClass="obligatorio" maxlength="15" size="20"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.proveedor.proHorarioOficina" />:</label></td>
      <td>
       	<html:text property="proHorarioOficina" styleId="proHorarioOficinaId" styleClass="obligatorio" maxlength="100" size="20"/>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.proveedor.proLimiteCredito" />:</label></td>
      <td>
       	<html:text property="proLimiteCredito" styleId="proLimiteCreditoId" styleClass="obligatorio" maxlength="300" size="20"/>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.proveedor.proDiasPago" />:</label></td>
      <td>
       	<html:text property="proDiasPago" styleId="proDiasPagoId" styleClass="obligatorio" size="20"/>
      </td>
    </tr>
    </logic:equal>
    </logic:present>
    <tr>
      <td colspan="2" align="center">
      	<logic:present name="filtro">
      		<%-- Solo acciones de busqueda un boton nada mas --%>
      		<logic:equal name="filtro" value="1">
	      		<logic:present name="boton">
	      		<logic:equal name="boton" value="1">
		      		<input type="hidden" id="pageId" name="page" value="3" />
		      		<html:submit property="accion" >
		        		<bean:message key="cmd.proveedor.salvar"/>
		        	</html:submit>
		        	<html:submit property="accion" onclick="$('#pageId').val('0');">
		        		<bean:message key="cmd.proveedor.eliminar"/>
		        	</html:submit>
		        	<html:submit property="accion" onclick="$('#pageId').val('0');">
		        		<bean:message key="cmd.proveedor.cancelar" />
		        	</html:submit>
	        	</logic:equal>
	        	<logic:equal name="boton" value="0">
		      		<input type="hidden" id="pageId" name="page" value="3" />
		      		<html:submit property="accion" >
		        		<bean:message key="cmd.proveedor.guardar"/>
		        	</html:submit>
		        	<html:submit property="accion" onclick="$('#pageId').val('0');">
		        		<bean:message key="cmd.proveedor.cancelar" />
		        	</html:submit>
		        </logic:equal>
		        </logic:present>
      		</logic:equal>
      		<logic:equal name="filtro" value="2">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.proveedor.buscar"/>
	        	</html:submit>
	        	<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.proveedor.nuevo"/>
	        	</html:submit>
	        	<input type="button" value="Limpiar" onclick="cleanFields('formId');">
      		</logic:equal>
      	</logic:present>
      </td>
    </tr>
  </table>
  <html:hidden property="proId" value="${form.proId}"/>
  <html:hidden property="proCodigo" value="${form.proCodigo}"/>
  <html:hidden property="proNombre" value="${form.proNombre}"/>
  <html:hidden property="proDireccion" value="${form.proDireccion}"/>
  <html:hidden property="proNumeroTelefono" value="${form.proNumeroTelefono}"/>
  <html:hidden property="proHorarioOficina" value="${form.proHorarioOficina}"/>
  <html:hidden property="proNit" value="${form.proNit}"/>
  <html:hidden property="proRegistro" value="${form.proRegistro}"/>
  <html:hidden property="proLimiteCredito" value="${form.proLimiteCredito}"/>
  <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
  <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
  <html:hidden property="proEstado" value="${form.proEstado}"/>
</html:form>