<%@ page language="java" pageEncoding="ISO-8859-1"%>
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
		$('#codProv').val(valores[0]);
		$('#provNombre').val(valores[1]);
		$('#proRegistro').val(valores[2]);
		//$('#proId').val(valores[3]);
		$('#codice').val(valores[3]);
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
	v = venta  -->
	<logic:present name="ack">
	<logic:equal name="ack" value="v">	<!--   factura consumidor final -->
		<logic:present name="tf">
			<logic:equal value="CO" name="tf">
				<p class="msg_head">
					Factura de Consumidor Final
				</p>
			</logic:equal>
			<logic:equal value="VC" name="tf">
				<p class="msg_head">
					Venta a Contribuyentes
				</p>
			</logic:equal>
		</logic:present>
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
							<html:text property="fusNombre" size="13" readonly="true"
								styleClass="obligatorio" value="${form.fusNombre}" />
							<span><bean:message key="msg.obligatorio" /></span>
							<html:hidden property="fusId" value="${form.fusId}" styleId="fusId"/> 
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenTipoPago" />:</label>
						</td>
						<td colspan="2">
							<html:select property="tipoPagoId" value="${form.tipoPagoId}" styleClass="obligatorio" >								
								<html:option value="E" >Contado</html:option>
								<html:option value="C" >Cr&eacute;dito</html:option>
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
							<logic:present name="tf">
								<logic:equal value="CO" name="tf">
									<input type="button" value="" 
									onclick="ajaxCallSincrono('${pageContext.request.contextPath}/facturacion${_accion}.do',
										'accion=cargarListaClientes&nombreCli='+$('#nombreCli').val()+
										'&codCli='+$('#codCli').val(),'clientes')"/>
								</logic:equal>
								<logic:equal value="VC" name="tf">
									<input type="button" value="" 
									onclick="ajaxCallSincrono('${pageContext.request.contextPath}/facturacion${_accion}.do',
										'accion=cargarListaContribuyentes&nombreCli='+$('#nombreCli').val()+
										'&codCli='+$('#codCli').val(),'clientes')"/>
								</logic:equal>
							</logic:present>
						</td>
					</tr>
					<logic:present name="tf">
						<logic:equal value="VC" name="tf">
							<tr>
								<td><label>
									<bean:message key="lbl.facturaManual.registroContribuyente" />:</label>
								</td>
								<td>
									<html:text property="registroContribuyente" styleClass="obligatorio" size="20"
										maxlength="45" value="${form.registroContribuyente}" 
										styleId="registroContribuyenteId" readonly="true"/>
									<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
								<td><label>
									<bean:message key="lbl.facturaManual.numeroContribuyente" />:</label>
								</td>
								<td>
									<html:text property="numeroContribuyente" styleClass="obligatorio"
										size="20" maxlength="150" styleId="numeroContribuyenteId"
										value="${form.numeroContribuyente}" readonly="true"/>
								</td>
							</tr>
						</logic:equal>
					</logic:present>
					<tr>
						<td colspan="5" align="center">
							<div id="clientes">
							</div>
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
							<bean:message key="lbl.facturaManual.monto" />:</label>
						</td>
						<td>
							<html:text property="monto" styleClass="obligatorio"
								size="15" styleId="montoId" maxlength="15"
								value="${form.monto}" />
							
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.facturaManual.descripcion" />:</label>
						</td>
						<td>
							<html:textarea property="descripcion" styleClass="obligatorio"
								styleId="descripcionId" cols="30" rows="3"  value="${form.descripcion}" />
						</td>
					</tr>
				</table>
			</div>
		<table align="center" border="0">
			<tr>
				<td>
					<logic:present name="repo">
						<html:submit property="accion">
							<bean:message key="cmd.encabezado.imprimir" />
						</html:submit>
					</logic:present>
					<logic:notPresent name="repo">
						<html:submit property="accion" disabled="true">
							<bean:message key="cmd.encabezado.imprimir" />
						</html:submit>
					</logic:notPresent>
				</td>
				<td>
					<html:submit property="accion">
						<bean:message key="cmd.encabezado.cancelar" />
					</html:submit>
				</td>
			</tr>
		</table>
	</logic:equal>
	<logic:equal name="ack" value="c">	<!--   factura consumidor final -->
		<p class="msg_head">
			Compra a Proveedor
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
								size="10" maxlength="10" 
								value="${form.fenSerieFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenNumeroFactura" />:</label>
						</td>
						<td>
							<html:text property="fenNumeroFactura" styleClass="obligatorio"
								size="15" maxlength="15" styleId="fenNumeroFacturaId"
								value="${form.fenNumeroFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.usoFactura" />:</label>
						</td>
						<td>
							<html:text property="fusNombre" size="13" readonly="true"
								styleClass="obligatorio" value="${form.fusNombre}" />
							<span><bean:message key="msg.obligatorio" /></span>
							<html:hidden property="fusId" value="${form.fusId}" styleId="fusId"/> 
						</td>
					</tr>
					<tr>
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
				Proveedor
			</p>
			<div class="msg_body">
				<table>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.codigo" />:</label>
						</td>
						<td>
							<html:text property="codCli" styleClass="obligatorio" size="20"
								maxlength="12" value="${form.codCli}" styleId="codProv"/>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.nomCli" />:</label>
						</td>
						<td>
							<html:hidden property="codigo" styleId="codice" value="${form.codigo}"/>
							<html:text property="nombreCli" styleClass="obligatorio"
								size="20" maxlength="150" styleId="provNombre"
								value="${form.nombreCli}" />
							<input type="button" value="" 
							onclick="ajaxCallSincrono('${pageContext.request.contextPath}/facturacion${_accion}.do',
								'accion=cargarListaProv&provNombre='+$('#provNombre').val()+
								'&codProv='+$('#codProv').val(),'clientes')"/>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.facturaManual.proRegistro" />:</label>
						</td>
						<td>
							<html:text property="proRegistro" styleClass="obligatorio" size="20"
								maxlength="45" value="${form.proRegistro}" styleId="proRegistro" readonly="true"/>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.facturaManual.proNit" />:</label>
						</td>
						<td>
							<html:text property="proNit" styleClass="obligatorio"
								size="20" maxlength="150" styleId="proNit"
								value="${form.proNit}" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<div id="clientes">
							</div>
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
							<bean:message key="lbl.facturaManual.monto" />:</label>
						</td>
						<td>
							<html:text property="monto" styleClass="obligatorio"
								size="15" styleId="montoId" maxlength="15"
								value="${form.monto}" />
							
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.facturaManual.descripcion" />:</label>
						</td>
						<td>
							<html:textarea property="descripcion" styleClass="obligatorio"
								styleId="descripcionId" cols="30" rows="3"  value="${form.descripcion}" />
						</td>
					</tr>
				</table>
			</div>
		<table align="center" border="0">
			<tr>
				<td>
					<html:submit property="accion">
						<bean:message key="cmd.facturaManual.guardar" />
					</html:submit>
				</td>
				<td>
					<html:submit property="accion">
						<bean:message key="cmd.encabezado.cancelar" />
					</html:submit>
				</td>
			</tr>
		</table>
	</logic:equal>
</logic:present>
	
	<logic:present name="view">
		<logic:present name="tf">
			<logic:equal value="CO" name="tf">
				<p class="msg_head">
					Factura de Consumidor Final
				</p>
			</logic:equal>
			<logic:equal value="VC" name="tf">
				<p class="msg_head">
					Venta a Contribuyentes
				</p>
			</logic:equal>
			<logic:equal value="" name="tf">
				<p class="msg_head">
					Compra a Proveedor
				</p>
			</logic:equal>
		</logic:present>
		<logic:notPresent name="tf">
			<p class="msg_head">
				Compra a Proveedor
			</p>
		</logic:notPresent>
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
						<td><label>
							<bean:message key="lbl.encabezado.fenSerieFactura" />:</label>
						</td>
						<td>
							<html:text property="fenSerieFactura" styleClass="obligatorio"
								size="10" maxlength="10" readonly="true"
								value="${form.fenSerieFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenNumeroFactura" />:</label>
						</td>
						<td>
							<html:text property="fenNumeroFactura" styleClass="obligatorio"
								size="15" maxlength="15" readonly="true" styleId="fenNumeroFacturaId"
								value="${form.fenNumeroFactura}" />
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.usoFactura" />:</label>
						</td>
						<td>
							<html:text property="fusNombre" size="13" readonly="true"
								styleClass="obligatorio" value="${form.fusNombre}" />
							<span><bean:message key="msg.obligatorio" /></span>
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.encabezado.fenTipoPago" />:</label>
						</td>
						<td colspan="2">
							<html:select property="tipoPagoId" value="${form.tipoPagoId}" styleClass="obligatorio" 
								disabled="true">
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
								maxlength="12" value="${form.codCli}" styleId="codCli" readonly="true"/>
							<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
						<td><label>
							<bean:message key="lbl.encabezado.nomCli" />:</label>
						</td>
						<td>
							<html:text property="nombreCli" styleClass="obligatorio"
								size="20" maxlength="150" styleId="nombreCli" readonly="true"
								value="${form.nombreCli}" />
						</td>
					</tr>
					<logic:present name="tf">
						<logic:notEqual value="CO" name="tf">
							<tr>
								<td><label>
									<bean:message key="lbl.facturaManual.registroContribuyente" />:</label>
								</td>
								<td>
									<html:text property="registroContribuyente" styleClass="obligatorio" size="20"
										maxlength="45" value="${form.registroContribuyente}" 
										styleId="registroContribuyenteId" readonly="true"/>
									<span><bean:message key="msg.obligatorio" /> </span> &nbsp;</td>
								<td><label>
									<bean:message key="lbl.facturaManual.numeroContribuyente" />:</label>
								</td>
								<td>
									<html:text property="numeroContribuyente" styleClass="obligatorio"
										size="20" maxlength="150" styleId="numeroContribuyenteId"
										value="${form.numeroContribuyente}" readonly="true"/>
								</td>
							</tr>
						</logic:notEqual>
					</logic:present>
					<tr>
						<td colspan="5" align="center">
							<div id="clientes">
							</div>
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
							<bean:message key="lbl.facturaManual.monto" />:</label>
						</td>
						<td>
							<html:text property="monto" styleClass="obligatorio"
								size="15" styleId="montoId" maxlength="15" readonly="true"
								value="${form.monto}" />
							
						</td>
					</tr>
					<tr>
						<td><label>
							<bean:message key="lbl.facturaManual.descripcion" />:</label>
						</td>
						<td>
							<html:textarea property="descripcion" styleClass="obligatorio" readonly="true"
								styleId="descripcionId" cols="30" rows="3"  value="${form.descripcion}" />
						</td>
					</tr>
				</table>
			</div>
		<table align="center" border="0">
			<tr>
				<td>
					<logic:present name="factura">
						<html:submit property="accion">
							<bean:message key="cmd.facturaManual.imprimeFactura" />
						</html:submit>
						&nbsp;
					</logic:present>
					<html:submit property="accion">
						<bean:message key="cmd.encabezado.cancelar" />
					</html:submit>
				</td>
			</tr>
		</table>
	</logic:present>
	<html:hidden property="tf" styleId="tfId" value="${form.tf}"/>
	<html:hidden property="ack" styleId="ackId" value="${form.ack}"/>
	<html:hidden property="fenId" styleId="fenId" value="${form.fenId}"/>
</html:form>