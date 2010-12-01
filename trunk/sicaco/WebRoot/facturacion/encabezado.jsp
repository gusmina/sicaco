<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript">
$(document).ready(function(){
	//esconde o muestra el contenido del div
	$(".msg_head").click(function(){
		$(this).next(".msg_body").slideToggle(425);
	});
});
	function saveSeleccionP(valor) {
		valores = valor.split(';');
		$('#proCodigo').val(valores[0]);
		$('#proNombre').val(valores[1]);
		$('#fenRegistro').val(valores[2]);
		$('#proId').val(valores[3]);
		$('#idProveedor').val(valores[3]);
		$('#proNit').val(valores[4]);
		$('#resultadoProv').hide('slow')
	} 
	
	function saveSeleccionA(valor,medida,arti) {
		valores = valor.split(';');
		$('#artCod').val(valores[0]);
		$('#artNombre').val(valores[1]);
		$('#resultadoArt').hide('slow');
		ajaxCallSincrono('${pageContext.request.contextPath}/facturacion${_accion}.do','accion=cargarListaCnv&medida='+ medida + '&arti=' + arti ,'comboCnv','convert');
		ajaxCallSincrono('${pageContext.request.contextPath}/facturacion${_accion}.do','accion=cargarExento&arti=' + arti ,'comboExento','exento');
	} 
	
	function saveSeleccionC(valor) {
		valores = valor.split(';');
		$('#codCli').val(valores[0]);
		$('#nombreCli').val(valores[1]);
		$('#codice').val(valores[2]);
		$('#registroContribuyenteId').val(valores[3]);
		$('#numeroContribuyenteId').val(valores[4]);
		$('#aocId').val(valores[5]);
		$('#resultadoCli').hide('slow')
	}
	
	function selected(){
		var chk = $("#chkbx")[0];
		if(chk.checked == true){
			$("#ascEmpId").val(2);
		}else{
			$("#ascEmpId").val(1);
		}
	}	
	function limpiarArticulo(){
		$('#artNombre').val('');
		$('#artCod').val('');
	}
</script>
<script type="text/javascript">
	$(document).ready(function(){
    	$("#fenNumeroFacturaId").numeric();
    	$("#codCli").alphanumeric();
    	$("#nombreCli").alpha({allow:", "});
    	$("#artCod").numeric();
    	$("#artNombre").alphanumeric();
    	$("#precioU").numeric({allow:"."});
    	$("#dfaCantidadId").numeric({allow:"."});
    	$("#proCodigo").alphanumeric();
    	$("#proNombre").alphanumeric();
  });
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
<html:form action="${_accion}" method="post" focus="fenTipoFactura" styleId="formId">

		<!-- determina la jsp que se debe mostrar
	c = factura de consumidor final
	f = credito fiscal
	am = ajuste manual
	db = nota de debito
	cr = nota de credito
	prov = compra a provedor  -->
	<logic:present name="ack">
	<logic:equal name="ack" value="c">	<!--   factura consumidor final -->
		<p class="msg_head">
				Factura de Consumidor Final
			</p>
			<div class="msg_body">
				<table border="0">
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenFechaFactura" />:</label>
						</td>
						<td>
							<html:text style="float:left;" styleId="fenFechaFacturaId" styleClass="obligatorio"
								onkeyup="mask(this);" value="${form.fenFechaFactura}"
								property="fenFechaFactura" maxlength="10" size="10" />
							<input style="height: 18px;" id="button_fenFechaFacturaId"
								type="button" value="..." />
							<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
							<script type="text/javascript">
		         				   Calendar.setup({
		           					inputField    : "fenFechaFacturaId",
		             				button        : "button_fenFechaFacturaId",
		            				align         : "Br"
		            			   });
		    				</script></td>
						<td><label>
							<bean:message key="lbl.encabezado.fenSerieFactura" />:</label>
						</td>
						<td>
							<html:text property="fenSerieFactura" styleClass="obligatorio"
								size="10" maxlength="10" disabled="true"
								value="${form.fenSerieFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenNumeroFactura" />:</label>
						</td>
						<td>
							<html:text property="fenNumeroFactura" styleClass="obligatorio"
								size="15" maxlength="15" disabled="true" styleId="fenNumeroFacturaId"
								value="${form.fenNumeroFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.usoFactura" />:</label>
						</td>
						<td>
							<html:select property="facFusFacturaUso.fusId" size="1"
								styleClass="obligatorio" value="${facFusFacturaUso.fusId}" >
								<html:optionsCollection label="fusNombre" name="listaFacturaUso"
									value="fusId" />
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.invBodBodegas.bodNombre" />:</label>
						</td>
						<td>
							<html:select property="invBodBodegas.bodId" styleId="bodId" size="1"
								styleClass="obligatorio" value="${form.invBodBodegas.bodId}">
								<html:optionsCollection label="bodNombre" name="listaBodegas"
									value="bodId" />
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.fenTipoPago" />:</label>
						</td>
						<td colspan="2">
							<html:select property="tipoPagoId" value="${form.tipoPagoId}" styleClass="obligatorio" >
								<html:option value="C" >Cr&eacute;dito</html:option>
								<html:option value="E" >Contado</html:option>
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					</table>
					</div>
					<p class="msg_head">
				Datos De Cliente
			</p>
			<div class="msg_body">
				<table>
					<tr>
						<td colspan="2">
							<input type="checkbox" name="chkbx" id="chkbx" onclick="selected();" /> 
							<label><bean:message key="lbl.ordcom.codEmp" /></label>
							<html:hidden property="ascEmp" styleId="ascEmpId"/>
						</td>					
					</tr>				
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.codigo" />:</label>
						</td>
						<td>
							<html:text property="codCli" styleClass="obligatorio" size="20"
								maxlength="12" value="${form.codCli}" styleId="codCli"/>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.nomCli" />:</label>
						</td>
						<td>
							<html:hidden property="codigo" styleId="codice" value="${form.codigo}"/>
							<html:text property="nombreCli" styleClass="obligatorio"
								size="20" maxlength="150" styleId="nombreCli"
								value="${form.nombreCli}" />
							<input type="button" value="" 
							onclick="ajaxCallSincrono('${pageContext.request.contextPath}/facturacion${_accion}.do','accion=cargarListaClientes&nombreCli='+$('#nombreCli').val()+'&codCli='+$('#codCli').val()+'&ascEmp='+$('#ascEmpId').val(),'clientes')"/>
						</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<div id="clientes">
							</div>
					    </td>
					    <html:hidden property="aoc" styleId="aocId" value="${form.aoc}"/>
					</tr>
				</table>
			</div>	
			<p class="msg_head">
				Detalle de Factura
			</p>
			<div class="msg_body">
				<table border="0">
					<tr>
						<td><label>
							<bean:message key="lbl.detalle.id.invArtArticulo.artCodigo" />:</label>
						</td>
						<td>
							<html:text property="artCodigo" styleClass="obligatorio"
								size="15" styleId="artCod" maxlength="80" 
								value="${form.artCodigo}" />
							
						</td>
						<td><label>
							<bean:message key="lbl.articuloF.nombre" />:</label>
						</td>
						<td>
							<html:text property="artNombre" styleClass="obligatorio"
								styleId="artNombre" size="15" maxlength="150"  value="${form.artNombre}" />
							<span> </span>
							<input type="button" value="..."
								onclick="ajaxCallSincrono('${pageContext.request.contextPath}/facturacion${_accion}.do',
									'accion=cargarListaArticulos&artNombre='+$('#artNombre').val()+
									'&artCodigo='+$('#artCod').val()+
									'&bod='+$('#bodId').val()+
									'&cnvId='+$('#conversion').val(),'productos')" />
							<input type="button" value="X" onclick="limpiarArticulo();"/>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.detalle.Medida" />:</label>
						</td>
						<td>
							<div id="comboCnv">
							   <html:select property="cnvId" styleClass="obligatorio" value="${form.cnvId}" styleId="convert" >
									<html:option value=" "></html:option>
							   </html:select>
							</div>
						</td>
						<td ><label>
							<bean:message key="lbl.detalle.dfaPrecioUnitario" />:</label>
						</td>
						<td>
							<div id="tbPU">
							<html:text property="dfaPrecioUnitario" 
								size="15" maxlength="15" styleId="precioU" readonly="true"/>
							</div>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.detalle.dfaCantidad" />:</label>
						</td>
						<td>
							<html:text property="dfaCantidad" styleClass="obligatorio"
								size="15" maxlength="15" styleId="dfaCantidadId"/>
						</td>
						<td><label>
							<bean:message key="lbl.detalle.dfaExento" />:</label>
						</td>
						<td>
							<div id="comboExento">
							
								<html:select property="dfaExento" styleId="exento" disabled="disabled">
									<html:option value="0">
										No	
									</html:option>
									<html:option value="1">
										Si
									</html:option>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<div id ="productos">
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<html:submit property="accion">
								<bean:message key="cmd.detalle.agregar" />
							</html:submit>
						</td>
					</tr>
				</table>
			</div>
	<p class="msg_head">
		Totales Factura
	</p>
	<div class="msg_body">
		<table border="0">
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.totalVentasAfectas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasA" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasA}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.fenTotalVentasExentas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasE" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasE}" />
				</td>
			</tr>
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.fenIvaRetenido" />:</label>
				</td>
				<td>
					<html:text property="ivaRetenido" styleClass="obligatorio"
						disabled="true" value="${form.ivaRetenido}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.total" />:</label>
				</td>
				<td><html:text property="totalFactura" styleClass="obligatorio"
						disabled="true" value="${form.totalFactura}" /> 
				</td>
			</tr>
		</table>
	</div>
				<table align="center" border="0">
		<tr>
			<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.guardar" />
				</html:submit>
			</td>
						<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.imprimir" />
				</html:submit>
			</td>
			<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.cancelar" />
				</html:submit>
			</td>
		</tr>
		</table>
	</logic:equal>
	<logic:equal name="ack" value="f"> <!-- facturas credito fiscal -->
			<p class="msg_head">
				Crédito Fiscal
			</p>
			<div class="msg_body">
				<table border="0">
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenFechaFactura" />:</label>
						</td>
						<td>
							<html:text style="float:left;" styleId="fenFechaFacturaId" styleClass="obligatorio"
								onkeyup="mask(this);" value="${form.fenFechaFactura}"
								property="fenFechaFactura" maxlength="10" size="10" />
							<input style="height: 18px;" id="button_fenFechaFacturaId"
								type="button" value="..." />
							<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
							<script type="text/javascript">
		         				   Calendar.setup({
		           					inputField    : "fenFechaFacturaId",
		             				button        : "button_fenFechaFacturaId",
		            				align         : "Br"
		            			   });
		    				</script></td>
						<td><label>
							<bean:message key="lbl.encabezado.fenSerieFactura" />:</label>
						</td>
						<td>
							<html:text property="fenSerieFactura" styleClass="obligatorio"
								size="10" maxlength="10" disabled="true"
								value="${form.fenSerieFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenNumeroFactura" />:</label>
						</td>
						<td>
							<html:text property="fenNumeroFactura" styleClass="obligatorio"
								size="15" maxlength="15" disabled="true" styleId="fenNumeroFacturaId"
								value="${form.fenNumeroFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.usoFactura" />:</label>
						</td>
						<td>
							<html:select property="facFusFacturaUso.fusId" size="1"
								styleClass="obligatorio" value="${facFusFacturaUso.fusId}" >
								<html:optionsCollection label="fusNombre" name="listaFacturaUso"
									value="fusId" />
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.invBodBodegas.bodNombre" />:</label>
						</td>
						<td>
							<html:select property="invBodBodegas.bodId" styleId="bodId" size="1"
								styleClass="obligatorio" value="${form.invBodBodegas.bodId}">
								<html:optionsCollection label="bodNombre" name="listaBodegas"
									value="bodId" />
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.fenTipoPago" />:</label>
						</td>
						<td colspan="2">
							<html:select property="tipoPagoId" value="${form.tipoPagoId}" styleClass="obligatorio" >
								<html:option value="C" >Cr&eacute;dito</html:option>
								<html:option value="E" >Contado</html:option>
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					</table>
					</div>
					<p class="msg_head">
				Datos De Cliente
			</p>
			<div class="msg_body">
				<table>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.codigo" />:</label>
						</td>
						<td>
							<html:text property="codCli" styleClass="obligatorio" size="20"
								maxlength="12" value="${form.codCli}" styleId="codCli"/>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.nomCli" />:</label>
						</td>
						<td>
							<html:hidden property="codigo" styleId="codice" value="${form.codigo}"/>
							<html:text property="nombreCli" styleClass="obligatorio"
								size="20" maxlength="150" styleId="nombreCli"
								value="${form.nombreCli}" />
							<input type="button" value="" 
							onclick="ajaxCallNormal('${pageContext.request.contextPath}/facturacion${_accion}.do','accion=cargarListaContribuyentes&nombreCli='+$('#nombreCli').val()+'&codCli='+$('#codCli').val(),'clientes')"/>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.registroContribuyente" />:</label>
						</td>
						<td>
							<html:text property="registroContribuyente" styleClass="obligatorio"
								size="15" maxlength="15" disabled="true" styleId="registroContribuyenteId"
								value="${form.registroContribuyente}" />
						</td>
						<td><label>
							<bean:message key="lbl.encabezado.nit" />:</label>
						</td>
						<td>
							<html:text disabled="true" onkeyup="maskNit(this);" maxlength="17" 
								size="17" property="numeroContribuyente" styleClass="condicional"
								styleId="numeroContribuyenteId" value="${form.numeroContribuyente}"/>
						</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<div id="clientes">
							</div>
					    </td>
					    <html:hidden property="aoc" styleId="aocId" value="${form.aoc}"/>
					</tr>
				</table>
			</div>	
			<p class="msg_head">
				Detalle de Factura
			</p>
			<div class="msg_body">
				<table border="0">
					<tr>
						<td><label>
							<bean:message key="lbl.detalle.id.invArtArticulo.artCodigo" />:</label>
						</td>
						<td>
							<html:text property="artCodigo" styleClass="obligatorio"
								size="15" styleId="artCod" maxlength="80"
								value="${form.artCodigo}" />
							
						</td>
						<td><label>
							<bean:message key="lbl.articuloF.nombre" />:</label>
						</td>
						<td>
							<html:text property="artNombre" styleClass="obligatorio"
								styleId="artNombre" size="15" maxlength="150"  value="${form.artNombre}" />
							<span> </span>
							<input type="button" value="..."
								onclick="ajaxCallNormal('${pageContext.request.contextPath}/facturacion${_accion}.do',
									'accion=cargarListaArticulos&artNombre='+$('#artNombre').val()+
									'&artCodigo='+$('#artCod').val()+
									'&bod='+$('#bodId').val()+
									'&cnvId='+$('#conversion').val(),
									'productos');" />
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.detalle.Medida" />:</label>
						</td>
						<td>
							<div id="comboCnv">
							   <html:select property="cnvId" styleClass="obligatorio" value="${form.cnvId}" styleId="convert" >
									<html:option value=" "></html:option>
							   </html:select>
							</div>
						</td>
						<td ><label>
							<bean:message key="lbl.detalle.dfaPrecioUnitario" />:</label>
						</td>
						<td>
							<div id="tbPU">
							<html:text property="dfaPrecioUnitario" 
								size="15" maxlength="15" styleId="precioU" readonly="true"/>
							</div>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.detalle.dfaCantidad" />:</label>
						</td>
						<td>
							<html:text property="dfaCantidad" styleClass="obligatorio"
								size="15" maxlength="15" styleId="dfaCantidadId"/>
						</td>
						<td><label>
							<bean:message key="lbl.detalle.dfaExento" />:</label>
						</td>
						<td>
							<div id="comboExento">
								<html:select property="dfaExento" styleId="exento" disabled="disabled">
									<html:option value="0">
										No	
									</html:option>
									<html:option value="1">
										Si
									</html:option>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<div id ="productos">
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<html:submit property="accion">
								<bean:message key="cmd.detalle.agregar" />
							</html:submit>
						</td>
					</tr>
				</table>
			</div>
	<p class="msg_head">
		Totales Factura
	</p>
	<div class="msg_body">
		<table border="0">
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.totalVentasAfectas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasA" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasA}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.fenTotalVentasExentas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasE" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasE}" />
				</td>
			</tr>
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.fenIvaRetenido" />:</label>
				</td>
				<td>
					<html:text property="ivaRetenido" styleClass="obligatorio"
						disabled="true" value="${form.ivaRetenido}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.total" />:</label>
				</td>
				<td><html:text property="totalFactura" styleClass="obligatorio"
						disabled="true" value="${form.totalFactura}" /> 
				</td>
			</tr>
		</table>
	</div>
				<table align="center" border="0">
		<tr>
			<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.guardar" />
				</html:submit>
			</td>
						<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.imprimir" />
				</html:submit>
			</td>
			<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.cancelar" />
				</html:submit>
			</td>
		</tr>
		</table>
	</logic:equal>
	<logic:equal name="ack"value="am"> <!-- ajuste manual o factura manual -->
		<p class="msg_head">
				Factura de Consumidor Final Para Ajuste Manual
			</p>
			<div class="msg_body">
				<table border="0">
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenFechaFactura" />:</label>
						</td>
						<td>
							<html:text style="float:left;" styleId="fenFechaFacturaId" styleClass="obligatorio"
								onkeyup="mask(this);" value="${form.fenFechaFactura}"
								property="fenFechaFactura" maxlength="10" size="10" />
							<input style="height: 18px;" id="button_fenFechaFacturaId"
								type="button" value="..." />
							<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
							<script type="text/javascript">
		         				   Calendar.setup({
		           					inputField    : "fenFechaFacturaId",
		             				button        : "button_fenFechaFacturaId",
		            				align         : "Br"
		            			   });
		    				</script></td>
						<td><label>
							<bean:message key="lbl.encabezado.fenSerieFactura" />:</label>
						</td>
						<td>
							<html:text property="fenSerieFactura" styleClass="obligatorio"
								size="10" maxlength="10" disabled="true"
								value="${form.fenSerieFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenNumeroFactura" />:</label>
						</td>
						<td>
							<html:text property="fenNumeroFactura" styleClass="obligatorio"
								size="15" maxlength="15" disabled="true"
								value="${form.fenNumeroFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.usoFactura" />:</label>
						</td>
						<td>
							<html:select property="facFusFacturaUso.fusId" size="1"
								styleClass="obligatorio" value="${facFusFacturaUso.fusId}">
								<html:optionsCollection label="fusNombre" name="listaFacturaUso"
									value="fusId" />
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					</table>
					</div>	
			<p class="msg_head">
				Detalle de Factura
			</p>
			<div class="msg_body">
				<table border="0">
					<tr>
						<td><label>
							<bean:message key="lbl.detalle.dfaDescripcion" />:</label>
						</td>
						<td>
							<html:textarea property="dfaDescripcion" styleClass="obligatorio"
								styleId="dfaDescripcion"  value="${form.dfaDescripcion}" />
							<span> </span>
						</td>
					</tr>
				</table>
			</div>
	<p class="msg_head">
		Total Factura
	</p>
	<div class="msg_body">
		<table border="0">
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.fenIvaRetenido" />:</label>
				</td>
				<td>
					<html:text property="ivaRetenido" styleClass="obligatorio"
						 value="${form.ivaRetenido}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.total" />:</label>
				</td>
				<td><html:text property="totalFactura" styleClass="obligatorio"
						 value="${form.totalFactura}" /> 
				</td>
			</tr>
		</table>
	</div>
				<table align="center" border="0">
		<tr>
			<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.guardar" />
				</html:submit>
			</td>
						<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.imprimir" />
				</html:submit>
			</td>
			<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.cancelar" />
				</html:submit>
			</td>
		</tr>
		</table>
	</logic:equal>
	<logic:equal name="ack" value="ND"><!--  nota de debito -->
		<p class="msg_head">
				Nota de Cr&eacute;dito por devoluci&oacute;n a Cetia
			</p>
			<div class="msg_body">
				<table border="0">
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenFechaFactura" />:</label>
						</td>
						<td>
							<html:text style="float:left;" styleId="fenFechaFacturaId" styleClass="obligatorio"
								onkeyup="mask(this);" value="${form.fenFechaFactura}" readonly="true"
								property="fenFechaFactura" maxlength="10" size="10" />
						</td>
						<td><label>
							<bean:message key="lbl.encabezado.fenSerieFactura" />:</label>
						</td>
						<td>
							<html:text property="fenSerieFactura" styleClass="obligatorio"
								size="10" maxlength="10" disabled="true"
								value="${form.fenSerieFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenNumeroFactura" />:</label>
						</td>
						<td>
							<html:text property="fenNumeroFactura" styleClass="obligatorio"
								size="15" maxlength="15" disabled="true"
								value="${form.fenNumeroFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.facturaOriginal" />:</label>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenSerieFacturaO" />:</label>
						</td>
						<td>
							<html:text property="fenSerieFacturaO" styleClass="obligatorio"
								size="10" maxlength="10" disabled="true"
								value="${form.fenSerieFacturaO}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.fenNumeroFacturaO" />:</label>
						</td>
						<td>
							<html:text property="fenNumeroFacturaO" styleClass="obligatorio"
								size="15" maxlength="15" disabled="true"
								value="${form.fenNumeroFacturaO}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.invBodBodegas.bodNombre" />:</label>
						</td>
						<td>
							<html:select property="invBodBodegas.bodId" styleId="bodId" size="1"
								styleClass="obligatorio" value="${form.invBodBodegas.bodId}" disabled="true">
								<html:optionsCollection label="bodNombre" name="listaBodegas"
									value="bodId" />
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					</table>
					</div>
					<p class="msg_head">
				Datos De Cliente
			</p>
			<div class="msg_body">
				<table>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.codigo" />:</label>
						</td>
						<td>
							<html:text property="codCli" styleClass="obligatorio" size="20"
								maxlength="45" value="${form.codCli}" styleId="codCli" readonly="true"/>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.nomCli" />:</label>
						</td>
						<td>
							<html:text property="nombreCli" styleClass="obligatorio"
								size="20" maxlength="150" styleId="nombreCli"
								value="${form.nombreCli}" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<div id="clientes">
							</div>
					    </td>
					    <html:hidden property="aoc" styleId="aocId" value="${form.aoc}"/>
					</tr>
				</table>
			</div>	
	<p class="msg_head">
		Totales Factura
	</p>
	<div class="msg_body">
		<table border="0">
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.totalAfectas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasA" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasA}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.totalExentas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasE" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasE}" />
				</td>
			</tr>
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.fenIvaRetenido" />:</label>
				</td>
				<td>
					<html:text property="ivaRetenido" styleClass="obligatorio"
						disabled="true" value="${form.ivaRetenido}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.total" />:</label>
				</td>
				<td><html:text property="totalFactura" styleClass="obligatorio"
						disabled="true" value="${form.totalFactura}" /> 
				</td>
			</tr>
		</table>
	</div>
	</logic:equal>
	<logic:equal name="ack"value="NC"><!-- nota de credito -->
		<p class="msg_head">
				Nota de Cr&eacute;dito por devoluci&oacute;n a Proveedores
	</p>
	<div class="msg_body">
				<table border="0">
					<tr>
						<td>
							<label>
							<bean:message key="lbl.encabezado.fenFechaFactura" />
									:
							</label>
						</td>
						<td>
							<html:text style="float:left;" styleId="fenFechaFacturaId" styleClass="obligatorio"
								onkeyup="mask(this);" value="${form.fenFechaFactura}" readonly="true"
								property="fenFechaFactura" maxlength="10" size="10" />
						</td>
					</tr>
					<tr>
					<td><label>
							<bean:message key="lbl.encabezado.fenSerieFactura" />:</label>
						</td>
						<td>
							<html:text property="fenSerieFactura" styleClass="obligatorio"
								size="10" maxlength="10" disabled="true"
								value="${form.fenSerieFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					<td>
						<label>
								<bean:message key="lbl.encabezado.fenCorrelativoCredito"/>:
							</label>
					</td>
					<td>
							<html:text property="fenNumeroFactura" styleClass="obligatorio"
								size="15" maxlength="15" value="${form.fenNumeroFactura}" readonly="true"/>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					
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
								value="${form.proCodigo}" readonly="true"/>
						<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td>
						<label>
							<bean:message key="lbl.encabezado.nombre" />:
							</label>
						</td>
						<td>
							<html:text property="proNombre"
								styleId="proNombre" value="${form.proNombre}"
								size="15" maxlength="300" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenRegistro" />:
							</label>
						</td>
						<td>
							<html:text property="proRegistro"
								styleClass="obligatorio" size="15" maxlength="25"
								value="${form.proRegistro}"
								styleId="fenRegistro"     readonly="true" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.nit" />:</label>
						</td>
						<td>
							<html:text property="proNit"
								styleClass="obligatorio" size="13" maxlength="25"
								value="${form.proNit}" styleId="proNit"
								readonly="true" />
					</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<div id="proveedores" >
							</div>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.facturaOriginal" />:</label>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenNumeroFacturaO" />:</label>
						</td>
						<td>
							<html:text property="fenNumeroFacturaO" styleClass="obligatorio"
								size="15" maxlength="15" disabled="true"
								value="${form.fenNumeroFacturaO}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.invBodBodegas.bodNombre" />:</label>
						</td>
						<td>
							<html:select property="invBodBodegas.bodId" styleId="bodId" size="1"
								styleClass="obligatorio" value="${form.invBodBodegas.bodId}" disabled="true">
								<html:optionsCollection label="bodNombre" name="listaBodegas"
									value="bodId" />
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					</table>
					</div>
	<p class="msg_head">
		Totales Factura
	</p>
	<div class="msg_body">
		<table border="0">
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.totalVentasAfectas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasA" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasA}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.fenTotalVentasExentas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasE" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasE}" />
				</td>
			</tr>
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.fenIvaRetenido" />:</label>
				</td>
				<td>
					<html:text property="ivaRetenido" styleClass="obligatorio"
						disabled="true" value="${form.ivaRetenido}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.total" />:</label>
				</td>
				<td><html:text property="totalFactura" styleClass="obligatorio"
						disabled="true" value="${form.totalFactura}" /> 
				</td>
			</tr>
		</table>
	</div>
	</logic:equal>
	<logic:equal name="ack" value="prov"><!-- Factura de compra a proveedor -->
			<p class="msg_head">
				Credito Fiscal por Compra a Proveedor
		</p>
		<div class="msg_body">
				<table border="0">
					<tr>
						<td>
							<label>
									<bean:message key="lbl.encabezado.fenCorrelativoCredito"/>:
								</label>
						</td>
						<td>
							<html:text property="fenNumeroFactura" styleClass="obligatorio"
								size="15" maxlength="15" value="${form.fenNumeroFactura}"  styleId="fenNumeroFacturaId"/>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td>
							<label>
							<bean:message key="lbl.encabezado.fenFechaFactura" />
									:
							</label>
						</td>
						<td>
							<html:text style="float:left;" styleId="fenFechaFacturaId" styleClass="obligatorio"
								onkeyup="mask(this);" value="${form.fenFechaFactura}"
								property="fenFechaFactura" maxlength="10" size="10" />
							<input style="height: 18px;" id="button_fenFechaFacturaId"
								type="button" value="..." />
							<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
							<script type="text/javascript">
		         				   Calendar.setup({
		           					inputField    : "fenFechaFacturaId",
		             				button        : "button_fenFechaFacturaId",
		            				align         : "Br"
		            			   });
		    				</script>
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
								value="${form.proCodigo}" />
						<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td>
						<label>
							<bean:message key="lbl.encabezado.nombre" />:
							</label>
						</td>
						<td>
							<html:text property="proNombre"
								styleId="proNombre" value="${form.proNombre}"
								size="15" maxlength="300"/>
							<input type="button" value="..."
								onclick="ajaxCallNormal('${pageContext.request.contextPath}/facturacion${_accion}.do','accion=cargarListaProveedor&proNombre='+$('#proNombre').val()+'&proCodigo='+$('#proCodigo').val(),'proveedores')" />
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenRegistro" />:
							</label>
						</td>
						<td>
							<html:text property="proRegistro"
								styleClass="obligatorio" size="15" maxlength="25"
								value="${form.proRegistro}"
								styleId="fenRegistro"     readonly="true" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.nit" />:</label>
						</td>
						<td>
							<html:text property="proNit"
								styleClass="obligatorio" size="13" maxlength="25"
								value="${form.proNit}" styleId="proNit"
								readonly="true" />
					</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<div id="proveedores" >
							</div>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.invBodBodegas.bodNombre" />:</label>
						</td>
						<td>
							<html:select property="invBodBodegas.bodId" styleId="bodId" size="1"
								styleClass="obligatorio" value="${form.invBodBodegas.bodId}">
								<html:optionsCollection label="bodNombre" name="listaBodegas"
									value="bodId" />
							</html:select>
						<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenTipoPago" />:</label>
						</td>
						<td>
							<html:select property="tipoPagoId" value="${form.tipoPagoId}" styleClass="obligatorio">
								<html:option value="C" >Cr&eacute;dito</html:option>
								<html:option value="E" >Contado</html:option>
							</html:select>
						</td>
						<!--property="fenTipoPago"  -->
						<td><label>
							<bean:message key="lbl.encabezado.usoFactura" />:</label>
						</td>
						<td>
							<html:select property="fuso" size="1"
								styleClass="obligatorio" value="${form.fuso}" >
								<html:optionsCollection label="fusNombre" name="listaFacturaUsoC"
									value="fusId" />
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> 
						</td>
					</tr>
				</table>
			</div>
			<p class="msg_head">
				Detalle de Factura
			</p>
			<div class="msg_body">
				<table border="0">
					<tr>
						<td><label>
							<bean:message key="lbl.detalle.id.invArtArticulo.artCodigo" />:</label>
						</td>
						<td>
							<html:text property="artCodigo" styleClass="obligatorio"
								size="15" styleId="artCod" maxlength="80"
								value="${form.artCodigo}" />
							
						</td>
						<td><label>
							<bean:message key="lbl.articuloF.nombre" />:</label>
						</td>
						<td>
							<html:hidden property="idProveedor" value="${form.idProveedor}" styleId="idProveedor"/>
							<html:text property="artNombre" styleClass="obligatorio"
								styleId="artNombre" size="15" maxlength="150"  value="${form.artNombre}" />
							<span> </span>
							<input type="button" value="..."
								onclick="ajaxCallNormal('${pageContext.request.contextPath}/facturacion${_accion}.do',
									'accion=cargarListaArticulos&artNombre='+$('#artNombre').val()+
									'&artCodigo='+$('#artCod').val()+
									'&cnvId='+$('#conversion').val()+
									'&bod='+$('#bodId').val()+
									'&idProveedor='+$('#idProveedor').val(),
									'productos')" />
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.detalle.Medida" />:</label>
						</td>
						<td>
							<div id="comboCnv">
							   <html:select property="cnvId" styleClass="obligatorio" value="${form.cnvId}" styleId="convert" >
									<html:option value=" "></html:option>
							   </html:select>
							</div>
						</td>
						<td ><label>
							<bean:message key="lbl.detalle.dfaCostoUnitario" />:</label>
						</td>
						<td>
							<div id="tbPU">
							<html:text property="dfaPrecioUnitario" 
								size="15" maxlength="15" styleId="precioU" readonly="true"/>
							</div>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.detalle.dfaCantidad" />:</label>
						</td>
						<td>
							<html:text property="dfaCantidad" styleClass="obligatorio"
								size="15" maxlength="15" styleId="dfaCantidadId"/>
						</td>
						<td><label>
							<bean:message key="lbl.detalle.dfaExento" />:</label>
						</td>
						<td>
							<div id="comboExento">
								<html:select property="dfaExento" styleId="exento" disabled="disabled">
									<html:option value="0">
										No	
									</html:option>
									<html:option value="1">
										Si
									</html:option>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<div id ="productos">
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<html:submit property="accion">
								<bean:message key="cmd.detalle.agregar" />
							</html:submit>
						</td>
					</tr>
				</table>
			</div>
	<p class="msg_head">
		Totales Factura
	</p>
	<div class="msg_body">
		<table border="0">
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.totalAfectas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasA" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasA}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.totalExentas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasE" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasE}" />
				</td>
			</tr>
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.fenIvaRetenido" />:</label>
				</td>
				<td>
					<html:text property="ivaRetenido" styleClass="obligatorio"
						disabled="true" value="${form.ivaRetenido}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.total" />:</label>
				</td>
				<td><html:text property="totalFactura" styleClass="obligatorio"
						disabled="true" value="${form.totalFactura}" /> 
				</td>
			</tr>
		</table>
	</div>
				<table align="center" border="0">
		<tr>
			<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.guardar" />
				</html:submit>
			</td>
			<!-- <td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.imprimir" />
				</html:submit>
			</td> -->
			<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.cancelar" />
				</html:submit>
			</td>
		</tr>
		</table>
	</logic:equal>
	</logic:present>
	<logic:present name="view">
	<logic:equal name="view" value="c">	<!--   factura consumidor final -->
		<p class="msg_head">
				Factura de Consumidor Final
			</p>
			<div class="msg_body">
				<table border="0">
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenFechaFactura" />:</label>
						</td>
						<td>
							<html:text style="float:left;" styleId="fenFechaFacturaId" styleClass="obligatorio"
								onkeyup="mask(this);" value="${form.fenFechaFactura}" readonly="true"
								property="fenFechaFactura" maxlength="10" size="10" />
						</td>
						<td><label>
							<bean:message key="lbl.encabezado.fenSerieFactura" />:</label>
						</td>
						<td>
							<html:text property="fenSerieFactura" styleClass="obligatorio"
								size="10" maxlength="10" disabled="true"
								value="${form.fenSerieFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenNumeroFactura" />:</label>
						</td>
						<td>
							<html:text property="fenNumeroFactura" styleClass="obligatorio"
								size="15" maxlength="15" disabled="true"
								value="${form.fenNumeroFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.usoFactura" />:</label>
						</td>
						<td>
							<html:select property="facFusFacturaUso.fusId" size="1"
								styleClass="obligatorio" value="${facFusFacturaUso.fusId}" disabled="true">
								<html:optionsCollection label="fusNombre" name="listaFacturaUso"
									value="fusId" />
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.invBodBodegas.bodNombre" />:</label>
						</td>
						<td>
							<html:select property="invBodBodegas.bodId" styleId="bodId" size="1"
								styleClass="obligatorio" value="${form.invBodBodegas.bodId}" disabled="true">
								<html:optionsCollection label="bodNombre" name="listaBodegas"
									value="bodId" />
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.fenTipoPago" />:</label>
						</td>
						<td colspan="2">
							<html:select property="tipoPagoId" value="${form.tipoPagoId}"
								disabled="true" styleClass="obligatorio" >
								<html:option value="C" >Cr&eacute;dito</html:option>
								<html:option value="E" >Contado</html:option>
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					</table>
					</div>
					<p class="msg_head">
				Datos De Cliente
			</p>
			<div class="msg_body">
				<table>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.codigo" />:</label>
						</td>
						<td>
							<html:text property="codCli" styleClass="obligatorio" size="20"
								maxlength="45" value="${form.codCli}" styleId="codCli" readonly="true"/>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.nomCli" />:</label>
						</td>
						<td>
							<html:text property="nombreCli" styleClass="obligatorio"
								size="20" maxlength="150" styleId="nombreCli"
								value="${form.nombreCli}" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<div id="clientes">
							</div>
					    </td>
					    <html:hidden property="aoc" styleId="aocId" value="${form.aoc}"/>
					</tr>
				</table>
			</div>	
	<p class="msg_head">
		Totales Factura
	</p>
	<div class="msg_body">
		<table border="0">
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.totalVentasAfectas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasA" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasA}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.fenTotalVentasExentas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasE" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasE}" />
				</td>
			</tr>
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.fenIvaRetenido" />:</label>
				</td>
				<td>
					<html:text property="ivaRetenido" styleClass="obligatorio"
						disabled="true" value="${form.ivaRetenido}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.total" />:</label>
				</td>
				<td><html:text property="totalFactura" styleClass="obligatorio"
						disabled="true" value="${form.totalFactura}" /> 
				</td>
			</tr>
		</table>
	</div>
				<table align="center" border="0">
		<tr>
			<logic:present name="impresa">
				<td>
					<input type="hidden" id="pageId" name="page" value="3" />
					<html:submit property="accion">
						<bean:message key="cmd.encabezado.imprimeFactura" />
					</html:submit>
					<html:submit property="accion">
						<bean:message key="cmd.encabezado.notaDebito" />
					</html:submit>
					<logic:present name="anular">
						<logic:equal name="anular" value="1">
							<html:submit property="accion">
								<bean:message key="cmd.encabezado.cancelarFact" />
							</html:submit>
						</logic:equal>
					</logic:present>
				</td>
			</logic:present>
			<logic:notPresent name="impresa">
				<td>

				</td>
				<logic:equal name="anular" value="1">
					<td>
						<input type="hidden" id="pageId" name="page" value="3" />
						<html:submit property="accion">
							<bean:message key="cmd.encabezado.imprimirGuardado" />
						</html:submit>
					</td>
				</logic:equal>
			</logic:notPresent>
			<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.cancelar" />
				</html:submit>
			</td>
		</tr>
		</table>
	</logic:equal>
	<logic:equal name="view" value="f"> <!-- facturas credito fiscal -->
	
	</logic:equal>
	<logic:equal name="view"value="am"> <!-- ajuste manual o factura manual -->
		<p class="msg_head">
				Factura de Consumidor Final Para Ajuste Manual
			</p>
			<div class="msg_body">
				<table border="0">
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenFechaFactura" />:</label>
						</td>
						<td>
							<html:text style="float:left;" styleId="fenFechaFacturaId" styleClass="obligatorio"
								onkeyup="mask(this);" value="${form.fenFechaFactura}"
								property="fenFechaFactura" maxlength="10" size="10" />
							<input style="height: 18px;" id="button_fenFechaFacturaId"
								type="button" value="..." />
							<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
							<script type="text/javascript">
		         				   Calendar.setup({
		           					inputField    : "fenFechaFacturaId",
		             				button        : "button_fenFechaFacturaId",
		            				align         : "Br"
		            			   });
		    				</script></td>
						<td><label>
							<bean:message key="lbl.encabezado.fenSerieFactura" />:</label>
						</td>
						<td>
							<html:text property="fenSerieFactura" styleClass="obligatorio"
								size="10" maxlength="10" disabled="true"
								value="${form.fenSerieFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenNumeroFactura" />:</label>
						</td>
						<td>
							<html:text property="fenNumeroFactura" styleClass="obligatorio"
								size="15" maxlength="15" disabled="true"
								value="${form.fenNumeroFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.usoFactura" />:</label>
						</td>
						<td>
							<html:select property="facFusFacturaUso.fusId" size="1"
								styleClass="obligatorio" value="${facFusFacturaUso.fusId}">
								<html:optionsCollection label="fusNombre" name="listaFacturaUso"
									value="fusId" />
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					</table>
					</div>	
			<p class="msg_head">
				Detalle de Factura
			</p>
			<div class="msg_body">
				<table border="0">
					<tr>
						<td><label>
							<bean:message key="lbl.detalle.dfaDescripcion" />:</label>
						</td>
						<td>
							<html:textarea property="dfaDescripcion" styleClass="obligatorio"
								styleId="dfaDescripcion"  value="${form.dfaDescripcion}" />
							<span> </span>
						</td>
					</tr>
				</table>
			</div>
	<p class="msg_head">
		Total Factura
	</p>
	<div class="msg_body">
		<table border="0">
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.fenIvaRetenido" />:</label>
				</td>
				<td>
					<html:text property="ivaRetenido" styleClass="obligatorio"
						 value="${form.ivaRetenido}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.total" />:</label>
				</td>
				<td><html:text property="totalFactura" styleClass="obligatorio"
						 value="${form.totalFactura}" /> 
				</td>
			</tr>
		</table>
	</div>
				<table align="center" border="0">
		<tr>
			<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.cancelar" />
				</html:submit>
			</td>
		</tr>
		</table>
	</logic:equal>
	<logic:equal name="view" value="ND"><!--  nota de debito -->
		<p class="msg_head">
				Nota de Cr&eacute;dito por devoluci&oacute;n a Cetia
			</p>
			<div class="msg_body">
				<table border="0">
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenFechaFactura" />:</label>
						</td>
						<td>
							<html:text style="float:left;" styleId="fenFechaFacturaId" styleClass="obligatorio"
								onkeyup="mask(this);" value="${form.fenFechaFactura}" readonly="true"
								property="fenFechaFactura" maxlength="10" size="10" />
						</td>
						<td><label>
							<bean:message key="lbl.encabezado.fenSerieFactura" />:</label>
						</td>
						<td>
							<html:text property="fenSerieFactura" styleClass="obligatorio"
								size="10" maxlength="10" disabled="true"
								value="${form.fenSerieFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenNumeroFactura" />:</label>
						</td>
						<td>
							<html:text property="fenNumeroFactura" styleClass="obligatorio"
								size="15" maxlength="15" disabled="true"
								value="${form.fenNumeroFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.facturaOriginal" />:</label>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenSerieFacturaO" />:</label>
						</td>
						<td>
							<html:text property="fenSerieFacturaO" styleClass="obligatorio"
								size="10" maxlength="10" disabled="true"
								value="${form.fenSerieFacturaO}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.fenNumeroFacturaO" />:</label>
						</td>
						<td>
							<html:text property="fenNumeroFacturaO" styleClass="obligatorio"
								size="15" maxlength="15" disabled="true"
								value="${form.fenNumeroFacturaO}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.invBodBodegas.bodNombre" />:</label>
						</td>
						<td>
							<html:select property="invBodBodegas.bodId" styleId="bodId" size="1"
								styleClass="obligatorio" value="${form.invBodBodegas.bodId}" disabled="true">
								<html:optionsCollection label="bodNombre" name="listaBodegas"
									value="bodId" />
							</html:select>
						<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr >
						<td><label>
							<bean:message key="lbl.encabezado.fenTipoPago" />:</label>
						</td>
						<td>
							<html:select property="fenTipoPago" value="${form.tipoPagoId}"
								disabled="true">
								<html:option value="C" >Cr&eacute;dito</html:option>
								<html:option value="E" >Contado</html:option>
							</html:select>
						</td>
						<td><label>
							<bean:message key="lbl.encabezado.usoFactura" />:</label>
						</td>
						<td>
							<html:select property="fuso" size="1"
								styleClass="obligatorio" value="${form.fuso}" disabled="true">
								<html:optionsCollection label="fusNombre" name="listaFacturaUsoC"
									value="fusId" />
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> 
						</td>
					</tr>
				</table>
			</div>
	<p class="msg_head">
		Totales Factura
	</p>
	<div class="msg_body">
		<table border="0">
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.totalVentasAfectas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasA" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasA}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.fenTotalVentasExentas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasE" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasE}" />
				</td>
			</tr>
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.fenIvaRetenido" />:</label>
				</td>
				<td>
					<html:text property="ivaRetenido" styleClass="obligatorio"
						disabled="true" value="${form.ivaRetenido}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.total" />:</label>
				</td>
				<td><html:text property="totalFactura" styleClass="obligatorio"
						disabled="true" value="${form.totalFactura}" /> 
				</td>
			</tr>
		</table>
	</div>
				<table align="center" border="0">
		<tr>
			<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.cancelar" />
				</html:submit>
			</td>
		</tr>
		</table>
	</logic:equal>
	<logic:equal name="view"value="NC"><!-- nota de credito -->
		<p class="msg_head">
				Nota de Cr&eacute;dito por devoluci&oacute;n a Proveedores
	</p>
	<div class="msg_body">
				<table border="0">
					<tr>
						<td>
							<label>
							<bean:message key="lbl.encabezado.fenFechaFactura" />
									:
							</label>
						</td>
						<td>
							<html:text style="float:left;" styleId="fenFechaFacturaId" styleClass="obligatorio"
								onkeyup="mask(this);" value="${form.fenFechaFactura}" readonly="true"
								property="fenFechaFactura" maxlength="10" size="10" />
						</td>
					</tr>
					<tr>
					<td><label>
							<bean:message key="lbl.encabezado.fenSerieFactura" />:</label>
						</td>
						<td>
							<html:text property="fenSerieFactura" styleClass="obligatorio"
								size="10" maxlength="10" disabled="true"
								value="${form.fenSerieFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					<td>
						<label>
								<bean:message key="lbl.encabezado.fenCorrelativoCredito"/>:
							</label>
					</td>
					<td>
							<html:text property="fenNumeroFactura" styleClass="obligatorio"
								size="15" maxlength="15" value="${form.fenNumeroFactura}" readonly="true"/>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					
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
								value="${form.proCodigo}" readonly="true"/>
						<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td>
						<label>
							<bean:message key="lbl.encabezado.nombre" />:
							</label>
						</td>
						<td>
							<html:text property="proNombre"
								styleId="proNombre" value="${form.proNombre}"
								size="15" maxlength="300" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenRegistro" />:
							</label>
						</td>
						<td>
							<html:text property="proRegistro"
								styleClass="obligatorio" size="15" maxlength="25"
								value="${form.proRegistro}"
								styleId="fenRegistro"     readonly="true" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.nit" />:</label>
						</td>
						<td>
							<html:text property="proNit"
								styleClass="obligatorio" size="13" maxlength="25"
								value="${form.proNit}" styleId="proNit"
								readonly="true" />
					</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<div id="proveedores" >
							</div>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.facturaOriginal" />:</label>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenNumeroFacturaO" />:</label>
						</td>
						<td>
							<html:text property="fenNumeroFacturaO" styleClass="obligatorio"
								size="15" maxlength="15" disabled="true"
								value="${form.fenNumeroFacturaO}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.invBodBodegas.bodNombre" />:</label>
						</td>
						<td>
							<html:select property="invBodBodegas.bodId" styleId="bodId" size="1"
								styleClass="obligatorio" value="${form.invBodBodegas.bodId}" disabled="true">
								<html:optionsCollection label="bodNombre" name="listaBodegas"
									value="bodId" />
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.fenTipoPago" />:</label>
						</td>
						<td colspan="2">
							<html:select property="tipoPagoId" value="${form.tipoPagoId}"
								disabled="true" styleClass="obligatorio" >
								<html:option value="C" >Cr&eacute;dito</html:option>
								<html:option value="E" >Contado</html:option>
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					</table>
					</div>
					<p class="msg_head">
				Datos De Cliente
			</p>
			<div class="msg_body">
				<table>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.codigo" />:</label>
						</td>
						<td>
							<html:text property="codCli" styleClass="obligatorio" size="20"
								maxlength="45" value="${form.codCli}" styleId="codCli" readonly="true"/>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.nomCli" />:</label>
						</td>
						<td>
							<html:text property="nombreCli" styleClass="obligatorio"
								size="20" maxlength="150" styleId="nombreCli"
								value="${form.nombreCli}" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<div id="clientes">
							</div>
					    </td>
					    <html:hidden property="aoc" styleId="aocId" value="${form.aoc}"/>
					</tr>
				</table>
			</div>	
	<p class="msg_head">
		Totales Factura
	</p>
	<div class="msg_body">
		<table border="0">
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.totalAfectas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasA" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasA}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.totalExentas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasE" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasE}" />
				</td>
			</tr>
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.fenIvaRetenido" />:</label>
				</td>
				<td>
					<html:text property="ivaRetenido" styleClass="obligatorio"
						disabled="true" value="${form.ivaRetenido}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.total" />:</label>
				</td>
				<td><html:text property="totalFactura" styleClass="obligatorio"
						disabled="true" value="${form.totalFactura}" /> 
				</td>
			</tr>
		</table>
	</div>
				<table align="center" border="0">
		<tr>
			<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.cancelar" />
				</html:submit>
			</td>
		</tr>
		</table>
	</logic:equal>
	<logic:equal name="view" value="prov"><!-- Factura de compra a proveedor -->
			<p class="msg_head">
				Credito Fiscal para Compra a Proveedor
	</p>
	<div class="msg_body">
				<table border="0">
					<tr>
					<td>
						<label>
								<bean:message key="lbl.encabezado.fenCorrelativoCredito"/>:
							</label>
					</td>
					<td>
							<html:text property="fenNumeroFactura" styleClass="obligatorio"
								size="15" maxlength="15" value="${form.fenNumeroFactura}" readonly="true"/>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					<td>
						<label>
						<bean:message key="lbl.encabezado.fenFechaFactura" />
								:
						</label>
					</td>
					<td>
						<html:text style="float:left;" styleId="fenFechaFacturaId" styleClass="obligatorio"
							onkeyup="mask(this);" value="${form.fenFechaFactura}" readonly="true"
							property="fenFechaFactura" maxlength="10" size="10" />
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
								value="${form.proCodigo}" readonly="true"/>
						<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td>
						<label>
							<bean:message key="lbl.encabezado.nombre" />:
							</label>
						</td>
						<td>
							<html:text property="proNombre"
								styleId="proNombre" value="${form.proNombre}"
								size="15" maxlength="300" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenRegistro" />:
							</label>
						</td>
						<td>
							<html:text property="proRegistro"
								styleClass="obligatorio" size="15" maxlength="25"
								value="${form.proRegistro}"
								styleId="fenRegistro"     readonly="true" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.nit" />:</label>
						</td>
						<td>
							<html:text property="proNit"
								styleClass="obligatorio" size="13" maxlength="25"
								value="${form.proNit}" styleId="proNit"
								readonly="true" />
					</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<div id="proveedores" >
							</div>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.invBodBodegas.bodNombre" />:</label>
						</td>
						<td>
							<html:select property="invBodBodegas.bodId" styleId="bodId" size="1"
								styleClass="obligatorio" value="${form.invBodBodegas.bodId}" disabled="true">
								<html:optionsCollection label="bodNombre" name="listaBodegas"
									value="bodId" />
							</html:select>
						<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr >
						<td><label>
							<bean:message key="lbl.encabezado.fenTipoPago" />:</label>
						</td>
						<td>
							<html:select property="tipoPagoId" value="${form.tipoPagoId}"
								disabled="true">
								<html:option value="C"  >Cr&eacute;dito</html:option>
								<html:option value="E" >Contado</html:option>
							</html:select>
						</td>
						<td><label>
							<bean:message key="lbl.encabezado.usoFactura" />:</label>
						</td>
						<td>
							<html:select property="fuso" size="1"
								styleClass="obligatorio" value="${form.fuso}" disabled="true">
								<html:optionsCollection label="fusNombre" name="listaFacturaUsoC"
									value="fusId" />
							</html:select>
							<span><bean:message key="msg.obligatorio" /> </span> 
						</td>
					</tr>
				</table>
			</div>
	<p class="msg_head">
		Totales Factura
	</p>
	<div class="msg_body">
		<table border="0">
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.totalAfectas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasA" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasA}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.totalExentas" />:</label>
				</td>
				<td>
					<html:text property="totalVentasE" styleClass="obligatorio"
						disabled="true" value="${form.totalVentasE}" />
				</td>
			</tr>
			<tr>
				<td><label>
					<bean:message key="lbl.encabezado.fenIvaRetenido" />:</label>
				</td>
				<td>
					<html:text property="ivaRetenido" styleClass="obligatorio"
						disabled="true" value="${form.ivaRetenido}" />
				</td>
				<td><label>
					<bean:message key="lbl.encabezado.total" />:</label>
				</td>
				<td><html:text property="totalFactura" styleClass="obligatorio"
						disabled="true" value="${form.totalFactura}" /> 
				</td>
			</tr>
		</table>
	</div>
				<table align="center" border="0">
		<tr>
			<logic:present name="impresa">
				<td>
					<input type="hidden" id="pageId" name="page" value="3" />
					<html:submit property="accion">
						<bean:message key="cmd.encabezado.notaCredito" />
					</html:submit>
				</td>
				<td>
					<logic:present name="anular">
						<logic:equal name="anular" value="1">
							<html:submit property="accion">
								<bean:message key="cmd.encabezado.cancelarFact" />
							</html:submit>
						</logic:equal>
					</logic:present>
				</td>
			</logic:present>
			<logic:notPresent name="impresa">
				<td>
					<input type="hidden" id="pageId" name="page" value="3" />
					<html:submit property="accion">
						<bean:message key="cmd.encabezado.imprimirGuardado" />
					</html:submit>
				</td>
			</logic:notPresent>
			<td>
				<input type="hidden" id="pageId" name="page" value="3" />
				<html:submit property="accion">
					<bean:message key="cmd.encabezado.cancelar" />
				</html:submit>
			</td>
		</tr>
		</table>
	</logic:equal>
	</logic:present>
	<html:hidden property="vfa" value="${form.vfa}" styleId="vfa"/>
	<html:hidden property="invProProveedor.proId" value="${form.invProProveedor.proId}" styleId="proId"/>
	<html:hidden property="ack" value="${form.ack}" styleId="ack"/>
	<html:hidden property="fenNumeroFactura" value="${form.fenNumeroFactura}" />
	<html:hidden property="proNombre" value="${form.proNombre}" styleId="proNombre"/>
	<html:hidden property="proRegistro" value="${form.proRegistro}" styleId="proRegistro"/>
	<html:hidden property="proNit" value="${form.proNit}" styleId="proNit"/>
	<html:hidden property="invBodBodegas.bodId" value="${form.invBodBodegas.bodId}" />
	<html:hidden property="facFusFacturaUso.fusId" value="${form.facFusFacturaUso.fusId}" />
	<html:hidden property="fenTipoFactura" value="${form.fenTipoFactura}"/>
	<html:hidden property="nombreCli" value="${form.nombreCli}" />
	<html:hidden property="codCli" value="${form.codCli}" />
	<html:hidden property="view" value="${form.view}" styleId="view"/>
	<html:hidden property="fenId" value="${form.fenId}" />
	<html:hidden property="esDeProv" value="${form.esDeProv}"/>
</html:form>