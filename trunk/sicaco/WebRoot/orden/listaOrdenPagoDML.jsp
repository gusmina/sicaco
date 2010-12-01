<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#opaCodigoId").numeric();
    	$("#proCodigoId").alphanumeric();
    	$("#opaTotalId").numeric();
    	$("#opaDescuentoId").numeric({allow:"."});
    	$("#totalDescuentoId").numeric({allow:"."});
  });
  
  function loadRepo(){
  	ajaxCallNormal('${pageContext.request.contextPath}/orden${_accion}.do',
  		'accion=cargarRepositorio&banId='+$('#banId').val()
  		,'repositorios');
  	ajaxCallNormal('${pageContext.request.contextPath}/orden${_accion}.do',
  		'accion=cargarCorrelativo&banId='+$('#banId').val()
  		,'correlativos');
  }
  
  function loadCorrelativo(){
  	ajaxCallNormal('${pageContext.request.contextPath}/orden${_accion}.do',
  		'accion=cargarCorrelativo&banId='+$('#banId').val()
  		+ '&rckId=' + $('#rckId').val(),'correlativos');
  }
</script>

<html:form action="${_accion}" method="post" styleId="formId" >
  <table border="0" align="center">
 	<tr>
 			<td><label><bean:message key="lbl.ordpago.opaCodigo" />:</label></td>
	    <td>
	    	<html:text property="opaCodigo" styleClass="obligatorio" size="15" maxlength="15" styleId="opaCodigoId" disabled="${form.dis}" value="${form.opaCodigo}"/>
	    </td>
	</tr>
    <tr>
      <td><label><bean:message key="lbl.ordpago.invProProveedor.proId" />:</label></td>
      <td>
       	<html:text property="invProProveedor.proCodigo" size="15" maxlength="15" styleId="proCodigoId" styleClass="obligatorio" disabled="${form.dis}" value="${form.invProProveedor.proCodigo}"/>
      </td>
    </tr>
    <tr>
	      <td><label><bean:message key="lbl.ordpago.opaFechaPago" />:</label></td>
	      <td>
	       	<%--<html:text property="opaFechaPago" styleClass="obligatorio" value="${form.opaFechaPago}"/>--%>
	       	<html:text style="float:left;" styleId="opaFechaPagoId" onkeyup="mask(this);" disabled="${form.dis}" value="${form.opaFechaPago}" property="opaFechaPago" maxlength="10" size="10"/>
        	<input  style="height: 18px;" id="button_opaFechaPagoId" type="button"  value="..." ${form.dis2}/>
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
    <logic:present name="filtro">
       	<logic:greaterEqual name="filtro" value="1">
	   	<tr>
	      <td><label><bean:message key="lbl.ordpago.opaTotal" />:</label></td>
	      <td>
	       	<html:text property="opaTotal" size="15" maxlength="15" styleId="opaTotalId" styleClass="obligatorio" disabled="${form.dis}" value="${form.opaTotal}"/>
	      </td>
	    </tr>
	   	<tr>
	      <td><label><bean:message key="lbl.ordpago.opaDescuento" />:</label></td>
	      <td>
	       	<html:text property="opaDescuento" size="15" maxlength="15" styleId="opaDescuentoId" styleClass="obligatorio" disabled="${form.dis}" value="${form.opaDescuento}"/>
	      </td>
	    </tr>
	   	<tr>
	      <td><label><bean:message key="lbl.ordpago.totalDescuento" />:</label></td>
	      <td>
	       	<html:text property="totalDescuento" size="15" maxlength="15" styleId="totalDescuentoId" styleClass="obligatorio" disabled="${form.dis}" value="${form.totalDescuento}"/>
	      </td>
	    </tr>
    	</logic:greaterEqual>
    </logic:present>
    <logic:present name="filtro">
    	<logic:equal name="filtro" value="1">
    		<logic:present name="permitePago">
	    		<tr>
		    		<td>
						<label>
							<bean:message key="lbl.ordpago.infoPago" />
						</label>
					</td>
	    		</tr>
	    		<tr>
					<td colspan="4">
						<table>
							<tr>
								<td>
									<label>
										<bean:message key="lbl.ordpago.tipoPago" />
									</label>							
								</td>
								<td>
									<html:select property="opaTipoPago"  value="${form.opaTipoPago}" styleId="opaTipoPagoId"  
										onchange="ajaxCallNormal('${pageContext.request.contextPath}/orden${_accion}.do','accion=cargarHtml&opaTipoPago='+$('#opaTipoPagoId').val()+'&proveedorId='+$('#proId').val(),'valoresFuente','vfuente');">
										<html:option value="-1">...</html:option>
										<html:option value="E">Efectivo</html:option>
										<html:option value="C">Cheque</html:option>
										<html:option value="N">Pago Electr&oacute;nico</html:option>				
									</html:select>
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<div id="valoresFuente">
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<logic:notPresent name="estadoD">
										<html:submit property="accion" >
											<bean:message key="cmd.lordp.realizarPago"/>
										</html:submit>
									</logic:notPresent>
									<logic:present name="estadoD">
										<html:submit property="accion" disabled="true" >
											<bean:message key="cmd.lordp.realizarPago"/>
										</html:submit>
									</logic:present>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</logic:present>
    	</logic:equal>
    	<logic:equal name="filtro" value="2">
    		<tr>
    			<td colspan="3">
    				<u style="color:#E95316;"><label><bean:message key="msg.ordenPago.pagado"/></label></u>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<label><bean:message key="lbl.ordenPago.pago"/>:</label>
    			</td>
    			<td colspan="2">
					<html:textarea property="opaNota" cols="25" rows="3" readonly="true" />
    			</td>
    		</tr>
    	</logic:equal>
    </logic:present>
    <tr> 
      <td colspan="2" align="center">
      	<logic:present name="filtro">
      		<%-- Solo acciones de busqueda un boton nada mas --%>
      		<logic:greaterEqual name="filtro" value="1">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<logic:equal name="form" property="opaEstado" value="C">
	      			<input type="button" id="deleteButtonId" onclick="handlerDeleteButton();" value='<bean:message key="lbl.lordp.anular"/>'>
	      		</logic:equal>
	      		<logic:present name="permitePago">
		        	<html:submit property="accion" >
						<bean:message key="cmd.lordp.imprimirReporte"/>
					</html:submit>
					<html:submit property="accion">
						<bean:message key="cmd.lordp.quedan"/>
					</html:submit>
				</logic:present>
	        	<html:submit property="accion" >
	        		<bean:message key="cmd.lordp.cancelar"/>
	        	</html:submit>
        	</logic:greaterEqual>
        	<logic:equal name="filtro" value="0">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" >
	        		<bean:message key="cmd.lordp.buscar"/>
	        	</html:submit>
	      		<input type="button" value="Limpiar" onclick="cleanFields('formId');">
	        </logic:equal>
        </logic:present>
      </td>
    </tr>
  </table>

  <html:hidden property="don" value="${form.don}"/>
  <html:hidden property="lordpId" value="${form.lordpId}"/>
  <html:hidden property="opaCodigo" value="${form.opaCodigo}"/>
  <html:hidden property="invProProveedor.proId" value="${form.invProProveedor.proId}" styleId="proId"/>
  <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
  <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
  <input class="exclude" type="hidden" name="accion" id="accionId" value=""/>

</html:form>

<script type="text/javascript">
var deleteButton = 1;
	function handlerDeleteButton(){
	var msgText = '<h3 style="color: red;">!!Anulacion de Orden!!</h3>'+
   				 '<p style="font-size: 1.4em; font-weight: bold;">Esta completamente seguro que desea anular esta orden ?';	
				$.prompt(msgText,{
      				buttons:{Ok:true,Cancel:false}, 
      				prefix:'brownJqi',
      				callback : function(v,m){
      					if(v == true){
      						$('#accionId').val('anular');
      						$('#pageId').val('0');
							$('#formId')[0].submit();
      					}
      				}
				});
		
	}
	
</script>