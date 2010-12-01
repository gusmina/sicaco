<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post" focus="rfcNombre" styleId="formId" >
  <table border="0">
  	 <tr>
      <td><label><bean:message key="lbl.rck.ctrCfcControlFacturacion.cfcSerie" />:</label></td>
      <td>
       	<html:select property="ctrCckControlCheques.cckId" styleClass="obligatorio" styleId="serie"
       		onchange="ajaxCallNormal('${pageContext.request.contextPath}/control${_accion}.do','accion=cargarTablaUsados&controlId='+$('#serie').val(),'tablaUsados');">
			<html:option value=" ">---</html:option>
			<html:optionsCollection  label="cckSerie" name="lstCck" value="cckId"/>     					
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
      <td><label><bean:message key="lbl.rck.rckNombre" />:</label></td>
      <td>
       	<html:text property="rckNombre" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.rck.rckCorrIni" />:</label></td>
      <td>
       	<html:text property="rckCorrIni" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <logic:present name="filtro">
	    <logic:equal name="filtro" value="1">
	    	<tr>
		      <td><label><bean:message key="lbl.rck.rckCorrActual" />:</label></td>
		      <td>
		       	<html:text property="rckCorrActual" styleClass="obligatorio"/>
		      	<span><bean:message key="msg.obligatorio"/></span>
		      </td>
		    </tr>
	    </logic:equal>
    </logic:present>
    <tr>
      <td><label><bean:message key="lbl.rck.rckCorrFin" />:</label></td>
      <td>
       	<html:text property="rckCorrFin" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr> <%-- 
    <tr>
      <td><label><bean:message key="lbl.rck.rckFechaIni" />:</label></td>
      <td>
      	<html:text style="float:left;" styleId="rckFechaIniId" onkeyup="mask(this);" value="${form.rckFechaIni}" property="rckFechaIni" maxlength="10" size="10"/>
       	<input  style="height: 18px;" id="button_rckFechaIniId" type="button"  value="..."/>
       	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d">
       	<script type="text/javascript">
	            Calendar.setup({
	              inputField    : "rckFechaIniId",
	              button        : "button_rckFechaIniId",
	              align         : "Br"
	            });
	    </script>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.rck.rckFechaFin" />:</label></td>
      <td>
      	<html:text style="float:left;" styleId="rckFechaFinId" onkeyup="mask(this);" value="${form.rckFechaFin}" property="rckFechaFin" maxlength="10" size="10"/>
       	<input  style="height: 18px;" id="button_rckFechaFinId" type="button"  value="..."/>
       	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d">
       	<script type="text/javascript">
	            Calendar.setup({
	              inputField    : "rckFechaFinId",
	              button        : "button_rckFechaFinId",
	              align         : "Br"
	            });
	    </script>
      </td>
    </tr>	--%>
    <tr>
      <td><label><bean:message key="lbl.rck.sucId" />:</label></td>
      <td>
      	<html:select property="secSucSucursal.sucId" styleClass="obligatorio">
      		<html:optionsCollection label="sucNombre" name="suc" value="sucId"/>
      	</html:select>
      </td>
      <td><label><bean:message key="lbl.rck.rckEstado" />:</label></td>
      <td>
      	<html:select property="rckEstado" styleClass="obligatorio">
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
	        		<bean:message key="cmd.rck.salvar"/>
	        	</html:submit>
	        	<input type="button" id="deleteButtonId" onclick="handlerDeleteButton();" value='<bean:message key="lbl.rck.eliminar"/>'>
	        	<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.rck.cancelar" />
	        	</html:submit>
        	</logic:equal>
        	<logic:equal name="filtro" value="0">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" >
	        		<bean:message key="cmd.rck.guardar"/>
	        	</html:submit>
	        	&nbsp;&nbsp;&nbsp;&nbsp;
	        	<input type="button" value="Limpiar" onclick="cleanFields('formId');">
	        </logic:equal>
        </logic:present>
      </td>
    </tr>
  </table>
  <html:hidden property="rckId" value="${form.rckId}"/>
  <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
  <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
  <html:hidden property="rckCorrActual" value="${form.rckCorrActual}"/>
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