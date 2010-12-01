<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	function saveSeleccionA(valor) {
		valores = valor.split(';')
		$('#ascCodigo3Id').val(valores[0])
		$('#ascNombreId').val(valores[1])
		$('#ascId').val(valores[2])
		$('#resultadoAsc').hide('slow')
	} 
	
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
    	$("#ocoCodigo").numeric();
  });
</script>

<html:form action="${_accion}" method="post" styleId="formId">
	<table border="0" align="center">
		<tr>
			<td colspan="2">
				<label>
					<bean:message key="lbl.ordcom.asociado" />
					:
				</label>
				<input type="checkbox" name="chkbx" id="chkbx" onclick="selected();" />
				<label>
					<bean:message key="lbl.ordcom.codEmp" />
				</label>
				<html:hidden property="ascEmp" styleId="ascEmpId" value="1" />
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.ordcom.secAscAsociado.Id.ascCodigo" />
					:
				</label>
			</td>
			<td>
				<html:text property="ascCodigo3" size="15" maxlength="12"
					styleClass="obligatorio" value="${form.ascCodigo3}"
					readonly="${form.dis}" styleId="ascCodigo3Id" />
				<span><bean:message key="msg.obligatorio" /> </span> &nbsp;
			</td>
			<td>
				<label>
					<bean:message key="lbl.ordcom.ascNombre" />
					:
				</label>
			</td>
			<td>
				<html:text property="ascNombre" size="40" maxlength="100"
					styleClass="obligatorio" value="${form.ascNombre}"
					readonly="${form.dis}" styleId="ascNombreId" />
				<input type="button" value="..."
					onclick="ajaxCallNormal('${pageContext.request.contextPath}/orden${_accion}.do',
							'accion=cargarListaAsociados&ascCodigo3='+$('#ascCodigo3Id').val()+
							'&ascNombre='+$('#ascNombreId').val() + '&don='+$('#donId').val()+
							'&ascEmp='+$('#ascEmpId').val(),'asociados')" />
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<div id="asociados">
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.ordcom.ocoCodigo" />
					:
				</label>
			</td>
			<td>
				<html:text size="15" property="ocoCodigo" 
					 styleId="ocoCodigo" />
			</td>
			<td>
				<label>
					<bean:message key="cmd.ordcom.cargaPro" />
					:
				</label>
			</td>
			<td>
				<html:select property="invProProveedor.proId">
				<html:option value="-1">-Seleccion un Proveedor-</html:option>
					<html:optionsCollection name="provs" value="proId"
						label="proNombre" />
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<LABEL><bean:message key="lbl.ordcom.ocoEmision"/></LABEL>
			</td>
			<td>
				<html:text style="float:left;" styleId="ocoEmision"
					styleClass="obligatorio" onkeyup="mask(this);"
					property="ocoEmision" maxlength="10" size="10" />
				<input style="height: 18px;" id="button_ocoEmision" type="button"
					value="..." />
				<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
				<script type="text/javascript">
		         		 Calendar.setup({
		           			inputField    : "ocoEmision",
		             		button        : "button_ocoEmision",
		            		align         : "Br"
		            	 });
		    		</script>

			</td>
			<td>
							<label>
					<bean:message key="lbl.ordcom.donacion" />
					:
				</label>
			</td>
			<td>
				<html:select property="ocoDonacion">
					<html:option value="-1">----</html:option>
					<html:option value="0">No</html:option>
					<html:option value="1">Si</html:option>
				</html:select>
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td>
				<html:submit property="accion" onclick="$('#pageId').val('0');">
					<bean:message key="cmd.ordcom.buscar" />
				</html:submit>
			</td>
			<td>
				<input type="button" value="Limpiar"
					onclick="cleanFields('formId');$('#ascId').val('')">
			</td>
		</tr>
	</table>
	<html:hidden property="ascCodigo" styleId="ascId" />
</html:form>