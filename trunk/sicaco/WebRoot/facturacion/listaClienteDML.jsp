<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	$(document).ready(function(){
		if($("#chequeadoId").val() == 1){
			var chk = $("#contribuyente")[0];
			chk.checked = true;
			$("#cliContribuyenteId").removeAttr("disabled");
	       		$("#cliDeclaraIvaId").removeAttr("disabled");
	       		$("#cliNumRegistroId").removeAttr("disabled");
		}else{
			var chk = $("#contribuyente")[0];
			chk.checked = false;
		}
		
	});
	
	$(document).ready(function(){	
		$("#contribuyente").click(function() {
	        var chk = $("#contribuyente")[0];
	        var val = chk.checked;
	       	if (val){
	       		$("#cliContribuyenteId").removeAttr("disabled");
	       		$("#cliDeclaraIvaId").removeAttr("disabled");
	       		$("#cliNumRegistroId").removeAttr("disabled");
	       	}else{
	       		$("#cliContribuyenteId").attr("disabled","disabled");
	       		$("#cliDeclaraIvaId").attr("disabled","disabled");
	       		$("#cliNumRegistroId").attr("disabled","disabled");
       		}
       	});
	});
</script>

<div>
<html:form action="${_accion}" method="post" styleId="formId">
	<table border="0">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.cliente.codigo" />
					:
				</label>
			</td>
			<td>
				<html:text property="cliCodigo" styleId="cliCodigo" styleClass="obligatorio" size="25"
					maxlength="12" value="${form.cliCodigo}" readonly="${form.modi}" />
				<span><bean:message key="msg.obligatorio" /> </span> &nbsp;
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.cliente.nombre" />
					:
				</label>
			</td>
			<td>
				<html:text property="cliNombre" styleClass="obligatorio" size="25"
					maxlength="60" value="${form.cliNombre}" />
				<span><bean:message key="msg.obligatorio" /> </span> &nbsp;
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.cliente.direccion" />
					:
				</label>
			</td>
			<td>
				<html:textarea property="cliDireccion" styleClass="obligatorio"
					value="${form.cliDireccion}" cols="25"/>
				<span><bean:message key="msg.obligatorio" /> </span>&nbsp;
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.cliente.departamento" />
					:
				</label>
			</td>
			<td>
				<html:text property="cliDepartamento" styleClass="obligatorio"
					size="25" maxlength="25" value="${form.cliDepartamento}" />
				<span><bean:message key="msg.obligatorio" /> </span> &nbsp;
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.cliente.municipio" />
					:
				</label>
			</td>
			<td>
				<html:text property="cliMunicipio" styleClass="obligatorio"
					size="25" maxlength="25" value="${form.cliMunicipio}" />
				<span><bean:message key="msg.obligatorio" /> </span> &nbsp;
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.cliente.giro" />
					:
				</label>
			</td>
			<td>
				<html:text property="cliGiro" size="25" maxlength="200" styleClass="obligatorio"
					value="${form.cliGiro}" />
			</td>
		</tr>
		<tr>
			<td>
	   			<html:checkbox property="contribuyente" styleClass="obligatorio" value="${form.contribuyente}" styleId="contribuyente">
					<label>
						<bean:message key="lbl.cliente.contribuyente" />
					</label>
				</html:checkbox>
				<html:hidden property="chequeado" value="${form.chequeado}" styleId="chequeadoId"/>
	     	</td>
	    </tr>
	    <tr>
			<td>
				<label>
					<bean:message key="lbl.cliente.numRegistro" />
					:
				</label>
			</td>
			<td>
				<html:text property="cliNumRegistro" size="25" maxlength="25" styleClass="obligatorio"
					value="${form.cliNumRegistro}" styleId="cliNumRegistroId" disabled="true"/>
			</td>
		</tr>
	    <tr>
	     	<td>
	     		<label>
	     			<bean:message key="lbl.cliente.nit" />:
	     		</label>
	     	</td>
	     	<td>
	   			<html:text disabled="true" onkeyup="maskNit(this);" maxlength="17" 
					size="17" property="cliContribuyente" styleClass="condicional"
					styleId="cliContribuyenteId" value="${form.cliContribuyente}"/>
	     	</td>
	     	<td>
	     		<label>
	     			<bean:message key="lbl.cliente.declaraIva" />?
	     		</label>
	     	</td>
	     	<td>
	   			<html:select property="cliDeclaraIva" styleClass="obligatorio" value="${form.cliDeclaraIva}" disabled="true" styleId="cliDeclaraIvaId">
	   				<html:option value="1">S&iacute;</html:option>
	   				<html:option value="0">No</html:option>
	   			</html:select>
	     	</td>
		</tr>
	</table>
	<table align="center" border="0">
		<tr>
		<td>
				<html:submit property="accion">
					<bean:message key="cmd.cliente.buscar" />
				</html:submit>
			</td>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.cliente.guardar" />
				</html:submit>
			</td>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.cliente.cancelar" />
				</html:submit>
			</td>
		</tr>
	</table>
	  	<html:hidden property="modi" value="${form.modi}"/>
	  	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>
</div>
<script type="text/javascript">
var deleteButton = 1;
	function handlerDeleteButton(id){
	$('#cliCodigo').val(id);
	var msgText = '<h3 style="color: red;">Eliminación De Cliente</h3>'+
   				 '<p style="font-size: 1.4em; font-weight: bold;">Esta completamente seguro que desea eliminar este Cliente del Sistema ?'
   				 +'<p style="font-size: 1.4em; font-weight: bold;">Recuerde que, solo puede eliminar un Cliente si no esta asociado a ninguna factura.';	
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