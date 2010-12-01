<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<script type="text/javascript">
   			function saveSeleccionP(valor) {
   				valores = valor.split(';')
   				$('#proCodigo').val(valores[0])
   				$('#proNombre').val(valores[1])
   				$('#proId').val(valores[2])
   				$('#refCuentaId').val(valores[3])
   				$('#refIdId').val(valores[4])
   				$('#resultadoProv').hide('slow')
   			} 
</script>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#opaDescuentoId").numeric({allow:"."});
    	$("#ascNombreId").alpha({allow:" "});
    	$("#ocoMontoId").numeric({allow:"."});
    	$("#proCodigo").alphanumeric();
    	$("#proNombre").alphanumeric({allow:" "});
    	$("#opaCodigoId").numeric();
  });
</script>

<html:form action="${_accion}" method="post" focus="invProProveedor.proCodigo" styleId="formId" >
  <table border="0">
  	<logic:present name="filtro">
  		<logic:equal name="filtro" value="1">
  		<tr>
  			<td><label><bean:message key="lbl.ordpago.opaCodigo" />:</label></td>
		    <td>
		    	<html:text property="opaCodigo" styleId="opaCodigoId" size="15" styleClass="obligatorio" disabled="${form.dis}" value="${form.opaCodigo}"/>
		    </td>
		</tr>
  		
    <tr>
      <td><label><bean:message key="lbl.ordpago.invProProveedor.proId" />:</label></td>
      <td>
       	<html:text property="invProProveedor.proCodigo" size="15" maxlength="25" styleClass="obligatorio" disabled="${form.dis}" value="${form.invProProveedor.proCodigo}"/>
      </td>
    </tr>
    </logic:equal>
  	</logic:present>
    <logic:present name="filtro">
    	<logic:equal name="filtro" value="0">
	    	<tr>
		    	<td>
				<label>
					<bean:message key="lbl.ordcom.cargaProveedor" />:
					</label>
				</td>
		    </tr>
		    <tr>
				<td>
				<label>
					<bean:message key="lbl.encabezado.invProProveedor.proCodigo" />:
					</label>
				</td>
				<td>
					<html:text property="proCodigo"
						styleId="proCodigo" size="15" maxlength="25" styleClass="obligatorio"
						value="${form.proCodigo}" readonly="${form.dis}" />
				<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
				<td>
				<label>
					<bean:message key="lbl.encabezado.nombre" />:
					</label>
				</td>
				<td>
					<html:text property="proNombre" readonly="${form.dis}"
						styleId="proNombre" value="${form.proNombre}"
						size="15" maxlength="300"/>
				</td>
			</tr>
			<tr>	
				<td>
					<input type="button" value="Cargar proveedor" 
						onclick="ajaxCallSincrono('${pageContext.request.contextPath}/orden${_accion}.do','accion=cargarListaProveedor&proNombre='+$('#proNombre').val()+'&proCodigo='+$('#proCodigo').val(),'proveedores')" />
				</td>
			</tr>
			<tr>
				<td colspan="5" align="center">
					<div id="proveedores" >
					</div>
				</td>
			</tr>
       	
       	<tr><td colspan="2">
	       	<html:submit property="accion" >
	       		<bean:message key="cmd.ordpago.cargaPro"/>
	       	</html:submit>
	    </td></tr>
   		</logic:equal>
   		<logic:equal name="filtro" value="1">
	   	<tr>
	      <td><label><bean:message key="lbl.ordpago.opaDescuento" />:</label></td>
	      <td>
	       	<html:text property="opaDescuento" size="15" maxlength="15" styleId="opaDescuentoId" styleClass="obligatorio" value="${form.opaDescuento}"/>
	      </td>
	    </tr>
	   	<tr>
	      <td><label><bean:message key="lbl.ordpago.opaFechaPago" />:</label></td>
	      <td>
	       	<%--<html:text property="opaFechaPago" styleClass="obligatorio" value="${form.opaFechaPago}"/>--%>
	       	<html:text style="float:left;" styleId="opaFechaPagoId" onkeyup="mask(this);" value="${form.opaFechaPago}" property="opaFechaPago" maxlength="10" size="10"/>
        	<input  style="height: 18px;" id="button_opaFechaPagoId" type="button"  value="..."/>
        	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d">
        	<script type="text/javascript">
		            Calendar.setup({
		              inputField    : "opaFechaPagoId",
		              button        : "button_opaFechaPagoId",
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
      		<logic:equal name="filtro" value="3">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" >
	        		<bean:message key="cmd.ordcom.salvar"/>
	        	</html:submit>
	        	<input type="button" id="deleteButtonId" onclick="handlerDeleteButton();" value='<bean:message key="lbl.ordcom.anular"/>'>
	        	<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.ordcom.cancelar" />
	        	</html:submit>
        	</logic:equal>
        	<logic:equal name="filtro" value="1">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" >
	        		<bean:message key="cmd.ordDet.lista"/>
	        	</html:submit>
	      		
	        </logic:equal>
        </logic:present>
      </td>
    </tr>
  </table>

  <html:hidden property="ini" value="${form.ini}"/>
  <html:hidden property="don" value="${form.don}"/>
  <html:hidden property="opaFechaPago" value="${form.opaFechaPago}"/>
  <html:hidden property="opaDescuento" value="${form.opaDescuento}"/>
  <html:hidden property="opaCodigo" value="${form.opaCodigo}"/>
  <html:hidden property="invProProveedor.proId" value="${form.invProProveedor.proId}" styleId="proId"/>
  <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
  <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>

</html:form>

