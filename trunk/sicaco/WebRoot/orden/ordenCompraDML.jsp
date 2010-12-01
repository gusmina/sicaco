<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	function saveSeleccionA(valor) {
		valores = valor.split(';')
		$('#ascCodigo3Id').val(valores[0])
		$('#ascNombreId').val(valores[1])
		$('#codice').val(valores[2])
		$('#creditoId').val(valores[3])
		//$('#idPrestamo').val(valores[4])
		$('#resultadoAsc').hide('slow')
	} 
	
	function saveSeleccionP(valor) {
		valores = valor.split(';')
		$('#proCodigo').val(valores[0])
		$('#proNombre').val(valores[1])
		$('#proId').val(valores[2])
		$('#refCuentaId').val(valores[3])
		$('#refIdId').val(valores[4])
		$('#resultadoProv').hide('slow')
	}; 
	
	function obtenParam(){
		ajaxCallNormal('${pageContext.request.contextPath}/orden${_accion}.do','accion=cargaParam&don='+$('#donId').val(),'divParam');
		setTimeout("handlerCleanButton($('#paramId').val())",750);
	};
	
	function selected(){
		var chk = $("#chkbx")[0];
		if(chk.checked == true){
			$("#ascEmpId").val(2);
		}else{
			$("#ascEmpId").val(1);
		}
	}
	
</script>
<script type="text/javascript">
	$(document).ready(function(){
    	$("#ascCodigo3Id").alphanumeric();
    	$("#ascNombreId").alpha({allow:" ,"});
    	$("#ocoMontoId").numeric({allow:"."});
    	$("#proCodigo").alphanumeric();
    	$("#proNombre").alphanumeric({allow:" "});
  });
</script>

<html:form action="${_accion}" method="post" focus="invProProveedor.proCodigo" styleId="formId" >
  <table border="0">
  <%-- 	<logic:present name="filtro" >
    	<logic:equal name="filtro" value="1"> --%>
    		<tr>
		      <td><label><bean:message key="lbl.ordcom.ocoCodigo" />:</label></td>
		      <td>
		      	<html:text size="15" property="ocoCodigo" styleClass="exclude" readonly="true" styleId="ocoCodigo"/>
		      </td>
		    </tr>
  <%--   	</logic:equal>
    </logic:present>  --%>
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
			<logic:present name="enabled">
				<input type="button" value="..." 
					onclick="ajaxCallNormal('${pageContext.request.contextPath}/orden${_accion}.do','accion=cargarListaProveedor&proNombre='+$('#proNombre').val()+'&proCodigo='+$('#proCodigo').val(),'proveedores')" />
			</logic:present>
		</td>
	</tr>
	<tr>
      <td><label><bean:message key="lbl.ordcom.ordRefCuentaReferencia.refId" />:</label></td>
      <td>
      	<html:text styleId="refCuentaId" size="15" property="ordRefCuentaReferencia.refCuenta" styleClass="obligatorio" readonly="true" value="${form.ordRefCuentaReferencia.refCuenta}" />
      </td>
    </tr>
	<tr>
		<td colspan="5" align="center">
			<div id="proveedores" >
			</div>
		</td>
	</tr>
<%--  	<logic:notEqual value="1" name="don"> --%>
		<tr>
	    	<td colspan="2"><label>
				<bean:message key="lbl.ordcom.asociado" />:</label>
				<%-- <logic:present name="filtro">
					<logic:equal value="0" name="filtro"> --%>
						<input type="checkbox" name="chkbx" id="chkbx" onclick="selected();" /> 
						<label><bean:message key="lbl.ordcom.codEmp" /></label>
						<html:hidden property="ascEmp" styleId="ascEmpId" value="1" />
		<%-- 			</logic:equal>
				</logic:present>  --%>
			</td>
    	</tr>
   <%--   </logic:notEqual> --%>
    <logic:equal value="1" name="don">
		<tr>
	    	<td><label>
				<bean:message key="lbl.ordcom.asociadoCliente" />:</label>
			</td>
    	</tr>
    </logic:equal>
	<tr>
		<td><label>
			<bean:message key="lbl.ordcom.secAscAsociado.Id.ascCodigo" />:</label>
		</td>
		<td>
			<html:text property="ascCodigo3" size="15" maxlength="12" styleClass="obligatorio" value="${form.ascCodigo3}" readonly="${form.dis}" styleId="ascCodigo3Id"/>
			<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
		<td><label>
			<bean:message key="lbl.ordcom.ascNombre" />:</label>
		</td>
		<td>
			<html:hidden property="codigo" styleId="codice" value="${form.codigo}"/>
			<html:text property="ascNombre" size="15" maxlength="100" styleClass="obligatorio" value="${form.ascNombre}" readonly="${form.dis}" styleId="ascNombreId"/>
			<logic:present name="enabled">
				<input type="button" value="..." 
					onclick="ajaxCallNormal('${pageContext.request.contextPath}/orden${_accion}.do',
							'accion=cargarListaAsociados&ascCodigo3='+$('#ascCodigo3Id').val()+
							'&ascNombre='+$('#ascNombreId').val() + '&don='+$('#donId').val()+
							'&ascEmp='+$('#ascEmpId').val(),'asociados')"/>
			</logic:present>
		</td>
	</tr>
	<logic:notEqual value="1" name="don">
	<tr>
      <td><label><bean:message key="lbl.ordcom.credito" />:</label></td>
      <td>
       	<html:text size="15" property="credito" styleId="creditoId" styleClass="obligatorio" value="${form.credito}" readonly="true"/>
      </td>
    </tr>
    </logic:notEqual>
	<tr>
		<td colspan="4" align="center">
			<div id="asociados">
			</div>
	    </td>
	</tr>
    <tr>
      <td><label><bean:message key="lbl.ordcom.ocoMonto" />:</label></td>
      <td>
      	<html:text size="15" maxlength="15" styleId="ocoMontoId" property="ocoMonto" styleClass="obligatorio" readonly="${form.dis}"/>
      </td> 
    </tr>
    <logic:present name="filtro" >
    	<logic:equal name="filtro" value="1">
		    <tr>
		      <td><label><bean:message key="lbl.ordcom.ocoSaldo" />:</label></td>
		      <td>
		      	<html:text size="15" property="ocoSaldo" styleClass="obligatorio" readonly="${form.dis}"/>
		      </td>
		    </tr>
    		<tr>
		      <td><label><bean:message key="lbl.ordcom.ocoEmision" />:</label></td>
		      <td>
		      	<html:text size="15" property="ocoEmision" styleClass="obligatorio" readonly="${form.dis}"/>
		      </td>
		    </tr>
		    <tr>
		      <td><label><bean:message key="lbl.ordcom.ocoVencimiento" />:</label></td>
		      <td>
		      	<html:text size="15" property="ocoVencimiento" styleClass="obligatorio" readonly="${form.dis}"/>
		      </td>
		    </tr>
    	</logic:equal>
    </logic:present>
    <logic:present name="filtro" >
    	<logic:equal name="filtro" value="1">
    		<tr>
		      <td><label><bean:message key="lbl.ordcom.ocoElaborado" />:</label></td>
		      <td>
		      	<html:text size="15" property="ocoElaborado" styleClass="obligatorio" readonly="${form.dis}"/>
		      </td>
		    </tr>
    	</logic:equal>
    </logic:present>
    <logic:present name="calendar">
	 	<tr>
			<td>
				<label><bean:message key="lbl.ordcom.fechaEmision" /></label>:
			</td>
			<td>
	          	<html:text style="float:left;" styleId="fechaEmisionId" onkeyup="mask(this);" value="${form.fechaEmision}" property="fechaEmision" maxlength="10" size="10"/>
	        	<input  style="height: 18px;" id="button_fechaEmisionId" type="button"  value="..."/>
	        	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d">
	        	<script type="text/javascript">
			            Calendar.setup({
			              inputField    : "fechaEmisionId",
			              button        : "button_fechaEmisionId",
			              align         : "Br"
			            });
			    </script>
			</td>
		</tr>
    </logic:present>
    <tr> 
      <td colspan="4" align="center">
        <logic:present name="boton">
      		<%-- Solo acciones de busqueda un boton nada mas --%>
      		<logic:equal name="boton" value="1">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	        	<input type="button" id="deleteButtonId" onclick="handlerDeleteButton();" value='<bean:message key="lbl.ordcom.anular"/>'>
	        	<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.ordcom.cancelar" />
	        	</html:submit>
        	</logic:equal>
        	<logic:equal name="boton" value="0">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<html:submit property="accion" >
	        		<bean:message key="cmd.ordcom.guardar"/>
	        	</html:submit>
	        	&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="Limpiar"
					onclick="cleanFields('formId');">
				<logic:equal name="admin" value="ADMINISTRADOR">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="Reiniciar"
						onclick="$('#restart').val(1);$('#ocoCodigo').val(1);">
					<logic:present name="don">
						<logic:equal value="1" name="don">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="<bean:message key="lbl.ordcom.clean"/>"
								onclick="obtenParam();">
							<div id="divParam">
								<html:hidden property="param" styleId="paramId" value=""/>
							</div>
						</logic:equal>
					</logic:present>
				</logic:equal>
	        </logic:equal>
	        <logic:equal name="boton" value="3">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<logic:present name="imp">
	     			<html:submit property="accion" onclick="$('#pageId').val('0');">
		        		<bean:message key="cmd.ordcom.imprimeOrden" />
		        	</html:submit>
	        	</logic:present>
	      		<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.ordcom.cancelar" />
	        	</html:submit>
	        </logic:equal>
	        <logic:equal name="boton" value="4">
	      		<input type="hidden" id="pageId" name="page" value="3" />
	      		<logic:present name="x">
      				<html:submit property="accion" onclick="$('#pageId').val('0');">
		        		<bean:message key="cmd.ordcom.imprimeOrden" />
		        	</html:submit>
		        </logic:present>
		        <logic:present name="emitida" >
		        	<html:submit property="accion" onclick="$('#pageId').val('0');">
	        			<bean:message key="cmd.ordcom.cargar" />
	        		</html:submit>
		        </logic:present>
		        <logic:notPresent name="x">
      				<input type="button" id="reimpresionButtonId" onclick="handlerReimpresionButton();" value='<bean:message key="lbl.ordcom.reimpresion"/>'>
	      		</logic:notPresent>
	      		<html:submit property="accion" onclick="$('#pageId').val('0');">
	        		<bean:message key="cmd.ordcom.cancelar" />
	        	</html:submit>
	        </logic:equal>
        </logic:present>
      </td>
    </tr>
  </table>
  
  <html:hidden property="ini" value="${form.ini}"/>
  <html:hidden property="don" styleId="donId" value="${form.don}"/>
  <html:hidden property="ascCodigo" value="${form.ascCodigo}"/>
  <html:hidden property="nombreAsoc" value="${form.nombreAsoc}"/>
  <html:hidden property="credito" value="${form.credito}"/>
  <html:hidden property="ocoEstado" value="${form.ocoEstado}"/>
  <html:hidden property="ocoMonto" value="${form.ocoMonto}"/>
  <html:hidden property="ocoCodigo" value="${form.ocoCodigo}"/>
  <html:hidden property="ocoEmision" value="${form.ocoEmision}"/>
  <html:hidden property="ocoElaborado" value="${form.ocoElaborado}"/>
  <html:hidden property="ocoId" value="${form.ocoId}"/>
  <html:hidden property="ordRefCuentaReferencia.refId" value="${form.ordRefCuentaReferencia.refId}" styleId="refIdId"/>
  <html:hidden property="invProProveedor.proId" value="${form.invProProveedor.proId}" styleId="proId"/>
  <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
  <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
  <html:hidden property="prestamoId" value="${form.prestamoId}" styleId="idPrestamo"/>
  <input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
  <html:hidden property="restart" styleId="restart" value="0"/>
  
  <html:hidden property="idOrden" value="${form.idOrden}"/>
  <html:hidden property="regalo" value="${form.regalo}"/>
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
	
	function handlerReimpresionButton(){
	var msgText = '<h3 style="color: darkblue;">!!Reimpresión de Orden!!</h3>'+
   				 '<p style="font-size: 1.4em; font-weight: bold;">Esta completamente seguro que desea reimprimir esta orden ?';	
				$.prompt(msgText,{
      				buttons:{Ok:true,Cancel:false}, 
      				prefix:'brownJqi',
      				callback : function(v,m){
      					if(v == true){
      						$('#accionId').val('reimpresion');
      						$('#pageId').val('0');
							$('#formId')[0].submit();
      					}
      				}
				});
		
	};
	
	function handlerCleanButton(){
	var param = $('#paramId').val();
	var msgText = '<h3 style="color: red;">!!Limpieza de Base de Datos!!</h3>'+
   				 '<p style="font-size: 1.4em; font-weight: bold;">Esta completamente seguro que desea las ordenes con ' + param + ' meses sin ser cobradas ?';	
				$.prompt(msgText,{
      				buttons:{Ok:true,Cancel:false}, 
      				prefix:'brownJqi',
      				callback : function(v,m){
      					if(v == true){
      						$('#accionId').val('clean');
      						$('#pageId').val('0');
							$('#formId')[0].submit();
      					}
      				}
				});
		
	}
	
</script>
