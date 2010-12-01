<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
    	$("#artNombreId").alphanumeric({allow:" "});
    	$("#artPrecioMinimoId").numeric({allow:"."});
    	$("#artPorcUtilId").numeric({allow:"."});
    	$("#artPrecioSugeridoId").numeric({allow:"."});
  });
</script>

<html:form action="${_accion}" method="post" focus="artCodigo" styleId="formId" >
  <table border="0">
  <logic:present name="filtro">
  <logic:equal name="filtro" value="0">
    <tr>
      <td><label><bean:message key="lbl.articulo.invMedMedida.medNombreMedida" />:</label></td>
      <td>
       	<html:select property="invMedMedida.medId" styleClass="obligatorio">
       		<html:option value="" > </html:option> 
			<html:optionsCollection  label="medNombreMedida" name="medida" value="medId"/>     					
		</html:select>
		<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.articulo.invTarTipoArticulo.tarNombre" />:</label></td>
      <td>
       	<html:select property="invTarTipoArticulo.tarId" styleClass="obligatorio">
       		<html:option value="" > </html:option> 
			<html:optionsCollection  label="tarNombre" name="tipoArt" value="tarId"/>     					
		</html:select>
		<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.articulo.invLinLinea.linNombre" />:</label></td>
      <td>
       	<html:select property="invLinLinea.linId" styleClass="obligatorio" styleId="lineaId"
       		onchange="ajaxCallNormal('${pageContext.request.contextPath}/inventario${_accion}.do','accion=cargarUtilidad&edit=1&lineaId=' + $('#lineaId').val() ,'textUtilidad','artPorcUtilId');">
       		<html:option value="" > </html:option> 
			<html:optionsCollection  label="linNombre" name="linea" value="linId"/>     					
		</html:select>
		<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.articulo.artPorcentajeUtilidad" />:</label></td>
      <td>
      	<div id="textUtilidad">
	       	<html:text property="artPorcentajeUtilidad" styleId="artPorcUtilId" readonly="true" size="15" maxlength="15" styleClass="obligatorio"/>
      	</div>
      </td>
    </tr>
  	<tr>
      <td><label><bean:message key="lbl.articulo.artNombre" />:</label></td>
      <td>
       	<html:text property="artNombre" styleId="artNombreId" size="15" maxlength="150" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    <tr>
      <td><label><bean:message key="lbl.articulo.artPrecioMinimo" />:</label></td>
      <td>
       	<html:text property="artPrecioMinimo" size="15" maxlength="15" styleId="artPrecioMinimoId" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.articulo.artExcentoIva" />:</label></td>
      <td>
      	<html:select property="artExcentoIva">
      		<html:option value="0"><bean:message key="lbl.articulo.artExcentoIva0"/></html:option>
      		<html:option value="1"><bean:message key="lbl.articulo.artExcentoIva1"/></html:option>
      	</html:select>
      </td>
    </tr>
    </logic:equal>
    <logic:equal name="filtro" value="1">
  	<tr>
      <td><label><bean:message key="lbl.articulo.artCodigo" />:</label></td>
      <td>
       	<html:text property="artCodigo" size="15" styleClass="obligatorio" disabled="true"/>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.articulo.invMedMedida.medNombreMedida" />:</label></td>
      <td>
       	<html:select property="invMedMedida.medId" styleClass="obligatorio" disabled="true">
			<html:optionsCollection  label="medNombreMedida" name="medida" value="medId"/>     					
		</html:select>
		<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.articulo.invTarTipoArticulo.tarNombre" />:</label></td>
      <td>
       	<html:select property="invTarTipoArticulo.tarId" styleClass="obligatorio" disabled="true">
			<html:optionsCollection  label="tarNombre" name="tipoArt" value="tarId"/>     					
		</html:select>
		<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.articulo.invLinLinea.linNombre" />:</label></td>
      <td>
       	<html:select property="invLinLinea.linId" styleClass="obligatorio" disabled="true">
			<html:optionsCollection  label="linNombre" name="linea" value="linId"/>     					
		</html:select>
		<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
  	<tr>
      <td><label><bean:message key="lbl.articulo.artNombre" />:</label></td>
      <td>
       	<html:text property="artNombre" styleId="artNombreId" size="15" maxlength="150" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.articulo.artPorcentajeUtilidad" />:</label></td>
      <td>
       	<html:text property="artPorcentajeUtilidad" styleId="artPorcUtilId" size="15" maxlength="15" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
      </td>
    </tr>
    <logic:present name="modificaCosto">
    	<logic:equal value="1" name="modificaCosto">
		    <tr>
		      <td><label><bean:message key="lbl.articulo.artPrecioMinimo" />:</label></td>
		      <td>
		       	<html:text property="artPrecioMinimo" size="15" maxlength="15" styleId="artPrecioMinimoId" styleClass="obligatorio"/>
		      	<span><bean:message key="msg.obligatorio"/></span>
		      </td>
		    </tr>
    	</logic:equal>
    	<logic:equal value="0" name="modificaCosto">
		    <tr>
		      <td><label><bean:message key="lbl.articulo.artPrecioMinimo" />:</label></td>
		      <td>
		       	<html:text property="artPrecioMinimo" size="15" maxlength="15" styleId="artPrecioMinimoId" styleClass="obligatorio" readonly="true"/>
		      	<span><bean:message key="msg.obligatorio"/></span>
		      </td>
		    </tr>
    	</logic:equal>
    </logic:present>
    <tr>
      <td><label><bean:message key="lbl.articulo.artExcentoIva" />:</label></td>
      <td>
      	<html:select property="artExcentoIva">
      		<html:option value="0"><bean:message key="lbl.articulo.artExcentoIva0"/></html:option>
      		<html:option value="1"><bean:message key="lbl.articulo.artExcentoIva1"/></html:option>
      	</html:select>
      </td>
    </tr>
    </logic:equal>
    <logic:equal name="filtro" value="2">
    <tr>
      <td><label><bean:message key="lbl.articulo.artCodigo" />:</label></td>
      <td>
       	<html:text property="artCodigo" size="15" styleClass="obligatorio" disabled="true"/>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.articulo.artNombre" />:</label></td>
      <td>
       	<html:text property="artNombre" styleId="artNombreId" size="15" maxlength="150" styleClass="obligatorio" disabled="true"/>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.articulo.artPorcentajeUtilidad" />:</label></td>
      <td>
       	<html:text property="artPorcentajeUtilidad" styleId="artPorcUtilId" size="15" maxlength="15" styleClass="obligatorio" disabled="true"/>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.articulo.artPrecioMinimo" />:</label></td>
      <td>
       	<html:text property="artPrecioMinimo" size="15" maxlength="15" styleId="artPrecioMinimoId" styleClass="obligatorio" disabled="true"/>
      </td>
    </tr>
    <tr>
      <td><label><bean:message key="lbl.articulo.artPrecioSugerido" />:</label></td>
      <td>
       	<html:text property="artPrecioSugerido" size="15" maxlength="15" styleId="artPrecioSugeridoId" styleClass="obligatorio"/>
      	<span><bean:message key="msg.obligatorio"/></span>
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
	        		<bean:message key="cmd.articulo.salvar"/>
	        	</html:submit>
	        	<input type="button" id="deleteButtonId" onclick="handlerDeleteButton();" value='<bean:message key="lbl.articulo.eliminar"/>'>
	        	<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.articulo.cancelar" />
	        	</html:submit>
        	</logic:equal>
        	<logic:equal name="filtro" value="0">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" >
	        		<bean:message key="cmd.articulo.guardar"/>
	        	</html:submit>
	        	&nbsp;&nbsp;&nbsp;&nbsp;
	        	<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.articulo.buscar" />
	        	</html:submit>
	        	&nbsp;&nbsp;&nbsp;&nbsp;
	        	<input type="button" value="Limpiar" onclick="cleanFields('formId');">
	        </logic:equal>
	        <logic:equal name="filtro" value="2">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<input type="button" id="mergeButtonId" onclick="handlerMergeButton();" value='<bean:message key="lbl.articulo.cambio"/>'>
	        	<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.articulo.cancelar" />
	        	</html:submit>
        	</logic:equal>
        </logic:present>
      </td>
    </tr>
  </table>
 
  <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
  <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
  <html:hidden property="artCodigo" value="${form.artCodigo}"/>
  <html:hidden property="invMedMedida.medId" value="${form.invMedMedida.medId}"/>
  <html:hidden property="invTarTipoArticulo.tarId" value="${form.invTarTipoArticulo.tarId}"/>
  <html:hidden property="invLinLinea.linId" value="${form.invLinLinea.linId}"/>
  <html:hidden property="artNombre" value="${form.artNombre}"/>
  <html:hidden property="artPorcentajeUtilidad" value="${form.artPorcentajeUtilidad}"/>
  <html:hidden property="artPrecioMinimo" value="${form.artPrecioMinimo}"/>
  <html:hidden property="artExcentoIva" value="${form.artExcentoIva}"/>
  <input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>

<script type="text/javascript">
var deleteButton = 1;
	function handlerDeleteButton(){
	var msgText = '<h3 style="color: red;">!!Eliminacion de Articulo!!</h3>'+
   				 '<p style="font-size: 1.4em; font-weight: bold;">Esta completamente seguro que desea eliminar este articulo ?';	
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
	
	function handlerMergeButton(){
	var msgText = '<h3 style="color: red;">!!Cambio de porcentaje de utilidad!!</h3>'+
   				 '<p style="font-size: 1.4em; font-weight: bold;">El cambio de precio sugerido implica un cambio de porcentaje de utilidad, seguro que desea continuar ?';	
				$.prompt(msgText,{
      				buttons:{Ok:true,Cancel:false}, 
      				prefix:'brownJqi',
      				callback : function(v,m){
      					if(v == true){
      						$('#accionId').val('salvar2');
      						$('#pageId').val('0');
							$('#formId')[0].submit();
      					}
      				}
				});
		
	}
	
</script>