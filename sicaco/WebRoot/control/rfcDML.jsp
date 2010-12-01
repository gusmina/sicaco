<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post" focus="rfcNombre" styleId="formId" >
  <table border="0">
  	 <tr>
      <td><label><bean:message key="lbl.rfc.ctrCfcControlFacturacion.cfcSerie" />:</label></td>
      <td>
       	<html:select property="ctrCfcControlFacturacion.cfcSerie" styleClass="obligatorio" styleId="serie"
       		onchange="ajaxCallNormal('${pageContext.request.contextPath}/control${_accion}.do','accion=cargarTablaUsados&serie='+$('#serie').val(),'tablaUsados');">
			<html:option value=" ">---</html:option>
			<html:optionsCollection  label="cfcSerie" name="controlFact" value="cfcSerie"/>     					
		</html:select>
		<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
		<td colspan="5">
			<div id="tablaUsados">
				${listaT}
			</div>
		</td>
	</tr>
  	<tr>
      <td><label><bean:message key="lbl.rfc.rfcNombre" />:</label></td>
      <td>
       	<html:text property="rfcNombre" styleClass="obligatorio" maxlength="25" size="25"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.rfc.rfcCorrIni" />:</label></td>
      <td>
       	<html:text property="rfcCorrIni" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <logic:present name="filtro" >
		<logic:equal value="1" name="filtro" >
			<tr>
		      <td><label><bean:message key="lbl.rfc.rfcCorrActual" />:</label></td>
		      <td>
		       	<html:text property="rfcCorrActual" styleClass="obligatorio"/>
		      	<span><bean:message key="msg.obligatorio"/></span>
		      </td>
		    </tr>
		</logic:equal>    
    </logic:present>
    <tr>
      <td><label><bean:message key="lbl.rfc.rfcCorrFin" />:</label></td>
      <td>
       	<html:text property="rfcCorrFin" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr> <%-- 
    <tr>
      <td><label><bean:message key="lbl.rfc.rfcFechaIni" />:</label></td>
      <td>
      	<html:text style="float:left;" styleId="rfcFechaIniId" onkeyup="mask(this);" value="${form.rfcFechaIni}" property="rfcFechaIni" maxlength="10" size="10"/>
       	<input  style="height: 18px;" id="button_rfcFechaIniId" type="button"  value="..."/>
       	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d">
       	<script type="text/javascript">
	            Calendar.setup({
	              inputField    : "rfcFechaIniId",
	              button        : "button_rfcFechaIniId",
	              align         : "Br"
	            });
	    </script>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.rfc.rfcFechaFin" />:</label></td>
      <td>
      	<html:text style="float:left;" styleId="rfcFechaFinId" onkeyup="mask(this);" value="${form.rfcFechaFin}" property="rfcFechaFin" maxlength="10" size="10"/>
       	<input  style="height: 18px;" id="button_rfcFechaFinId" type="button"  value="..."/>
       	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d">
       	<script type="text/javascript">
	            Calendar.setup({
	              inputField    : "rfcFechaFinId",
	              button        : "button_rfcFechaFinId",
	              align         : "Br"
	            });
	    </script>
      </td>
    </tr>	--%>
    <tr>
      <td><label><bean:message key="lbl.rfc.sucId" />:</label></td>
      <td>
      	<html:select property="sucID" styleClass="obligatorio">
      		<html:optionsCollection label="sucNombre" name="suc" value="sucId"/>
      	</html:select>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.rfc.tipoFactCont" />:</label></td>
      <td>
      	<html:select property="tipoFactCont" styleClass="obligatorio">
      		<html:option value="CO">Consumidor Final</html:option>
      		<html:option value="VC">Crédito Fiscal</html:option>
      		<html:option value="ND">Nota de Cr&eacute;dito por devolucion a Cetia</html:option>
      		<html:option value="NC">Nota de Cr&eacute;dito por devolucion a Proveedores</html:option>
      	</html:select>
      </td>
      <td><label><bean:message key="lbl.rfc.rfcEstado" />:</label></td>
      <td>
      	<html:select property="rfcEstado" styleClass="obligatorio">
      		<html:option value="A">Activo</html:option>
      		<html:option value="I">Inactivo</html:option>
      	</html:select>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
      	<logic:present name="filtro">
      		<%-- Solo acciones de busqueda un boton nada mas --%>
      		<logic:equal name="filtro" value="1">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" >
	        		<bean:message key="cmd.rfc.salvar"/>
	        	</html:submit>
	        	<input type="button" id="deleteButtonId" onclick="handlerDeleteButton();" value='<bean:message key="lbl.rfc.eliminar"/>'>
	        	<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.rfc.cancelar" />
	        	</html:submit>
        	</logic:equal>
        	<logic:equal name="filtro" value="0">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" >
	        		<bean:message key="cmd.rfc.guardar"/>
	        	</html:submit>
	        	&nbsp;&nbsp;&nbsp;&nbsp;
	        	<input type="button" value="Limpiar" onclick="cleanFields('formId');">
	        </logic:equal>
        </logic:present>
      </td>
    </tr>
  </table>
  <html:hidden property="rfcId" value="${form.rfcId}"/>
  <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
  <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
  <html:hidden property="rfcCorrActual" value="${form.rfcCorrActual}"/>
  <input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>

<script type="text/javascript">
var deleteButton = 1;
	function handlerDeleteButton(){
	var msgText = '<h3 style="color: red;">!!Eliminacion de Repositorio!!</h3>'+
   				 '<p style="font-size: 1.4em; font-weight: bold;">Esta completamente seguro que desea eliminar este repositorio ?';	
				$.prompt(msgText,{
      				buttons:{Ok:true,Cancel:false}, 
      				prefix:'brownJqi',
      				callback : function(v,m){
      					if(v == true){
      						$('#accionId').val('eliminar');
      						$('#pageId').val('0');
							$('#formId')[0].submit();
      					}
      				}
				});
		
	}
	
</script>