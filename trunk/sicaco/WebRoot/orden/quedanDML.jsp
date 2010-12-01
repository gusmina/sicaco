<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<script type="text/javascript">
	$(document).ready(function(){
		//esconde o muestra el contenido del div
		$(".msg_head").click(function(){
			$(this).next(".msg_body").slideToggle(425);
		});
			

    	$("#opaDescuentoId").numeric({allow:"."});
    	$("#ascNombreId").alpha({allow:" "});
    	$("#ocoMontoId").numeric({allow:"."});
    	$("#proCodigo").alphanumeric();
    	$("#proNombre").alphanumeric({allow:" "});
    	$("#opaCodigoId").numeric();
    	
    	
  });
  
  function calculaDesembolso(tamLista){
		var totalcc = parseFloat(0);
		for(i=0;i<tamLista;i++){
			var texto = "#posi" + i;
			var chk = $(texto)[0];
			var texto2 = "#montoId" + i;
        	if(chk.checked == true){
        		totalcc += parseFloat($(texto2).val());
        	};
        	$("#opaTotalId").val(totalcc);
        	$("#totalDescuentoId").val(totalcc * (1-($("#opaDescuentoId").val()/100)));
		};
	};
	
	function saveSeleccionP(valor) {
		valores = valor.split(';');
		$('#proCodigo').val(valores[0]);
		$('#proNombre').val(valores[1]);
		$('#proId').val(valores[2]);
		$('#refCuentaId').val(valores[3]);
		$('#refIdId').val(valores[4]);
		$('#resultadoProv').hide('slow');
	}; 
	
	function maximo(pos){
		var monto = $('#montoId' + pos).val();
		var max = $('#montoHidden' + pos).val();
		if(monto > max){
			$('#montoId' + pos).val(max);
		}
	}
	
	function agregaOrden(ocoId){
		var id = '#ocoId' + ocoId;
		var texto = '#posi' + ocoId;
		var montoHidden = '#montoHidden'+ocoId;
		var ocoVal = parseFloat($(id).val());
		var montHid = parseFloat($(montoHidden).val());
		var chk = $(texto)[0];
		
		if(chk.checked == true){
			if(ocoVal > montHid	|| ocoVal <= 0.0 ){
				$(id).val(montHid);
				chk.checked = false;
			}else{
				ajaxCallNormal('${pageContext.request.contextPath}/orden${_accion}.do',
					'accion=agregaOrden&ocoId='+ocoId+
					'&opa='+$('#opa').val()+
					'&ocoVal='+$(id).val()+
					'&opaDescuento='+$('#opaDescuentoId').val()
					,'totales');
				$(pagadoHidden).val(ocoVal);
			}
		}else{
			ajaxCallNormal('${pageContext.request.contextPath}/orden${_accion}.do',
				'accion=remueveOrden&ocoId='+ocoId+
				'&opa='+$('#opa').val()+
				'&opaDescuento='+$('#opaDescuentoId').val()
				,'totales');
			$(pagadoHidden).val(montHid);
		}
	}
	
	function modificaOrden(ocoId){
		var id = '#ocoId' + ocoId;
		var texto = '#posi' + ocoId;
		var montoHidden = '#montoHidden'+ocoId;
		var pagadoHidden = '#pagadoHidden'+ocoId;
		var ocoVal = parseFloat($(id).val());
		var pagHid = parseFloat($(pagadoHidden).val());
		var montHid = parseFloat($(montoHidden).val());
		var chk = $(texto)[0];
		if(chk.checked == true){
			//alert(ocoVal + ' ' + pagHid + ' ' + montHid);
			if(ocoVal <= montHid && ocoVal > 0.0 ){
				ajaxCallNormal('${pageContext.request.contextPath}/orden${_accion}.do',
					'accion=modificaOrden&ocoId='+ocoId+
					'&opa='+$('#opa').val()+
					'&ocoVal='+ocoVal+
					'&opaDescuento='+$('#opaDescuentoId').val()
					,'totales');
				$(pagadoHidden).val(ocoVal);	
			}else{
				$(id).val(pagHid);
			}
		}
	}
	
	function modificaDescuento(){
		ajaxCallNormal('${pageContext.request.contextPath}/orden${_accion}.do',
			'accion=modificaDescuento'+
			'&opa='+$('#opa').val()+
			'&opaDescuento='+$('#opaDescuentoId').val()
			,'totales');
		/*var opaDescuento = $('#opaDescuentoId').val();
		$('#opaDescuentoId').val(dosDecimales(opaDescuento, 'opaDescuentoId');*/
	}
  
</script>

<style type="text/css">
p {
	padding: 0 0 1em;
}

.msg_head {
	padding: 5px 10px;
	cursor: pointer;
	position: relative;
	background-color: #CACBDF;
	margin: 1px;
}

.msg_body {
	padding: 5px 10px 15px;
	background-color: auto;
}
</style>

<html:form action="${_accion}" method="post" focus="invProProveedor.proCodigo" styleId="formId" >
	<p class="msg_head">
		Encabezado del Quedan
	</p>
	<div class="msg_body">
		<table border="0">
			<logic:present name="filtro">
		  		<logic:equal name="filtro" value="1">
		  		<tr>
		  			<td><label><bean:message key="lbl.ordpago.opaCodigo" />:</label></td>
				    <td>
				    	<html:text property="opaCodigo" styleId="opaCodigoId" size="15" styleClass="obligatorio" disabled="${form.dis}" value="${form.opaCodigo}"/>
				    </td>
				    <td>
				    	<input type="button" value="Reiniciar"
							onclick="$('#restart').val(1);$('#opaCodigoId').val(1);">
				    </td>
				</tr>
		  		
		    <tr>
		    	<td><label><bean:message key="lbl.ordpago.invProProveedor.proId" />:</label></td>
			    <td>
			    	<html:text property="invProProveedor.proCodigo" size="15" maxlength="25" styleClass="obligatorio" disabled="${form.dis}" value="${form.invProProveedor.proCodigo}"/>
			    </td>
			    <td>
				<label>
					<bean:message key="lbl.encabezado.nombre" />:
				</label>
				</td>
				<td>
					<html:text property="proNombre" readonly="true"
						styleId="proNombre" value="${form.proNombre}"
						size="45" maxlength="300"/>
				</td>
		    </tr>
		    </logic:equal>
		  	<logic:equal name="filtro" value="2">
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
			    <td>
				<label>
					<bean:message key="lbl.encabezado.nombre" />:
				</label>
				</td>
				<td>
					<html:text property="proNombre" readonly="true"
						styleId="proNombre" value="${form.proNombre}"
						size="15" maxlength="300"/>
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
								onclick="ajaxCallNormal('${pageContext.request.contextPath}/orden${_accion}.do','accion=cargarListaProveedor&proNombre='+$('#proNombre').val()+'&proCodigo='+$('#proCodigo').val(),'proveedores')" />
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
			       	<html:text property="opaDescuento" size="15" maxlength="15" 
			       		styleId="opaDescuentoId" styleClass="obligatorio" value="${form.opaDescuento}"
			       		onchange="modificaDescuento();"/>
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
		    	<logic:equal name="filtro" value="2">
			   	<tr>
			      <td><label><bean:message key="lbl.ordpago.opaDescuento" />:</label></td>
			      <td>
			       	<html:text property="opaDescuento" size="15" maxlength="15" styleId="opaDescuentoId" styleClass="obligatorio" readonly="true" value="${form.opaDescuento}"/>
			      </td>
			    </tr>
			   	<tr>
			      <td><label><bean:message key="lbl.ordpago.opaFechaPago" />:</label></td>
			      <td>
			       	<%--<html:text property="opaFechaPago" styleClass="obligatorio" value="${form.opaFechaPago}"/>--%>
			       	<html:text style="float:left;" styleId="opaFechaPagoId" onkeyup="mask(this);" readonly="true" value="${form.opaFechaPago}" property="opaFechaPago" maxlength="10" size="10"/>
		        	<input  style="height: 18px;" id="button_opaFechaPagoId" type="button" disabled="disabled"  value="..."/>
		        	<input type="hidden" name="tipoDato"  id="tipoDatoId" value="d" disabled="disabled">
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
		        </logic:present>
		      </td>
			</tr>
		</table>
	</div>
	<p class="msg_head">
		Totales
	</p>
	<div class="msg_body">
		<div id="totales" >
			<table border="0" align="center">
		  		<tr>
		  			<td><label><bean:message key="lbl.ordpago.opaTotal" />:</label></td>
				    <td>
				    	<html:text property="opaTotal" styleId="opaTotalId" styleClass="obligatorio" readonly="true"/>
				    </td>
				</tr>
				<tr>
		  			<td><label><bean:message key="lbl.ordpago.totalDescuento" />:</label></td>
				    <td>
				    	<html:text property="totalDescuento" styleId="totalDescuentoId" styleClass="obligatorio" readonly="true"/>
				    </td>
				</tr>
			</table>
		</div>
	</div>
	<p class="msg_head">
		Controles
	</p>
	<div class="msg_body">
		<table border="0" align="center">
			<tr>
				<td colspan="2">
					<logic:present name="filtro">
						<logic:equal value="1" name="filtro">
							<input type="hidden" id="pageId" name="page" value="3" />
							<html:submit property="accion" >
								<bean:message key="cmd.ordpago.guardar2"/>
							</html:submit>
							&nbsp;&nbsp;&nbsp;
							<html:submit property="accion" onclick="cleanFields('formId');">
								<bean:message key="cmd.ordpago.cancelar" />
							</html:submit>
						</logic:equal>
						<logic:equal value="2" name="filtro">
							<input type="hidden" id="pageId" name="page" value="3" />
							&nbsp;&nbsp;&nbsp;
							<html:submit property="accion" >
								<bean:message key="cmd.ordpago.imprimirReporte"/>
							</html:submit>
							&nbsp;&nbsp;&nbsp;
							<html:submit property="accion" onclick="cleanFields('formId');">
								<bean:message key="cmd.ordpago.cancelar" />
							</html:submit>
						</logic:equal>
					</logic:present>
				</td>
			</tr>
		</table>
	</div>
	<p class="msg_head">
		Ordenes de compra
	</p> 
	<%-- 
	<div class="msg_body">
		<div id="lista-datos">
			${_lista2}
		</div>
	</div>
	--%> 
	<html:hidden property="totalDescuento" value="${form.totalDescuento}" />
	<html:hidden property="opaTotal" value="${form.opaTotal}" />
	<html:hidden property="ini" value="${form.ini}"/>
	<html:hidden property="don" value="${form.don}"/>
	<html:hidden property="opaFechaPago" value="${form.opaFechaPago}"/>
	<html:hidden property="opaDescuento" value="${form.opaDescuento}"/>
	<html:hidden property="opaCodigo" value="${form.opaCodigo}"/>
	<html:hidden property="invProProveedor.proId" value="${form.invProProveedor.proId}" styleId="proId"/>
	<html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
	<html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
	<html:hidden property="invProProveedor.proCodigo" value="${form.invProProveedor.proCodigo}"/>
	<html:hidden property="restart" styleId="restart" value="${form.restart}"/>
	<html:hidden property="tamListaOrdenP" styleId="tamListaId" value="${form.tamListaOrdenP}"/>
	<html:hidden property="opa" styleId="opa" value="${form.opa}"/>
</html:form>

