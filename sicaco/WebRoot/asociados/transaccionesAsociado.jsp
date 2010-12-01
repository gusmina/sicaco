<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript">

	$(document).ready(function() {
		ajaxCallSincrono('${pageContext.request.contextPath}/asociados${_accion}.do','accion=cargarListaCuentasAsociado&ascId='+$('#ascId').val()+'&tipoCuenta='+$('#tipoCuenta').val(),'cuentas','comboCuentas')
	});
	
	function imprimir(num,rep){
    	$('#comprobante').val(num);
    	
  		if(rep ==1){
  			$('#accionId').val('Comprobante Retiro');
  		}
  		if(rep ==2){
  			$('#accionId').val('Comprobante Ingreso');
  		}
  		if(rep ==3){
  			$('#accionId').val('Transferencia o Correccion');
  		}
		$('#formId')[0].submit();
  };
</script>
<html:form action="${_accion}" method="post" styleId="formId">
	<table border="0">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.txc.tipoCuenta" />
				</label>
			</td>
			<td>
			<html:select property="tipoCuenta"
					styleClass="obligatorio" styleId="tipoCuenta"
			onchange="ajaxCallSincrono('${pageContext.request.contextPath}/asociados${_accion}.do','accion=cargarListaCuentasAsociado&ascId='+$('#ascId').val()+'&tipoCuenta='+$('#tipoCuenta').val(),'cuentas','comboCuentas')">
					 	<html:option value="Z">Seleccione un tipo de cuenta</html:option>
					 	<html:option value="B"><bean:message key="lbl.txc.ahorro" /></html:option>
					 	<html:option value="A"><bean:message key="lbl.txc.aportacion" /></html:option>
					 	<html:option value="C"><bean:message key="lbl.txc.prestamo" /></html:option>
					 	<html:option value="D"><bean:message key="lbl.txc.seguro" /></html:option>
				</html:select>
			</td>
			<td>
				<label>
					<bean:message key="lbl.txc.cuentas" />
				</label>
			</td>
			<td>
				<div id="cuentas" >
				<html:select property="ctaCasCuentaAsociado.casCuenta"  
					styleClass="obligatorio" styleId="comboCuentas">
					<html:option value=" "></html:option>
				</html:select> 
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.txc.fechaIni" />
				</label>
			</td>
			<td>
				<html:text style="float:left;" styleId="fechaInicio" styleClass="obligatorio"
						onkeyup="mask(this);" 
						property="fechaInicio" maxlength="10" size="10" />
					<input style="height: 18px;" id="button_fechaInicio"
							type="button" value="..." />
					<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
			</td>
			<td>
				<label>
					<bean:message key="lbl.txc.fechaFin" />
				</label>
			</td>
			<td align="left">
				<html:text style="float:left;" styleId="fechaFin" styleClass="obligatorio"
						onkeyup="mask(this);" 
						property="fechaFin" maxlength="10" size="10" />
					<input style="height: 18px;" id="button_fechaFin"
							type="button" value="..." />
					<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
			</td>
	</table>
			<script type="text/javascript">
				Calendar.setup({
		              inputField	: "fechaInicio",
		              button		: "button_fechaInicio",
		              align			: "Br"
		            })
		            Calendar.setup({
		              inputField	: "fechaFin",
		              button		: "button_fechaFin",
		              align			: "Br"
		            })
		            function handlerCombo(){
				            	$(fechaInicio).toggle();
				            }
				    function handlerCombo(){
				            	$(fechaFin).toggle();
				            }
    	</script>
	<table border="3">
		<html:submit property="accion">
			<bean:message key="cmd.txc.buscar" />
		</html:submit>
		<html:submit property="accion">
			<bean:message key="cmd.txc.nueva" />
		</html:submit>
		<html:submit property="accion">
			<bean:message key="cmd.txc.regresar" />
		</html:submit>
	</table>
	<html:hidden property="ascId" value="${form.ascId}" styleId="ascId"/>
	<html:hidden property="tipoCuenta" value="${form.tipoCuenta}"/>
	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
	<input class="exclude" type="hidden" name="comprobante" id="comprobante" value=""/>
	
	
</html:form>