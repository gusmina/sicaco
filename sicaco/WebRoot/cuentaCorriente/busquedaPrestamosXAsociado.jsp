<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
 

<script type="text/javascript">
<!--
	$(document).ready(function(){	
	$("#ascCodigoAsociado").alphanumeric();
	$("#preId").alphanumeric();
	$("#ascCodigo").alphanumeric();
	$("#refCodigo").alphanumeric();
	$("#casFechaApertura").numeric();
	$("#primerNombre").alpha();
	$("#segundoNombre").alpha();
	$("#primerApellido").alpha();
	$("#segundoApellido").alpha();
  });
  
//-->
</script>

<html:form action="${_accion}" method="post" styleId="formId">
	<script>
      		var patternDui = new Array(${tamDui}-1,1);
      </script>
	<table border="0" align="center">
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascCodigo" />
					:
				</label>
			</td>
			<td>
				<html:text property="ascCodigoAsociado" styleClass="obligatorio"
					 styleId="ascCodigoAsociado" />
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascCodigoTrabajo" />
					:
				</label>
			</td>
			<td>
				<html:text property="ascCodigo" styleClass="obligatorio" styleId="ascCodigo"/>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascNit" />
					:
				</label>
			</td>
			<td>
				<html:text property="secPerPersona.perNit" styleClass="obligatorio"
					onkeyup="maskNit(this);" />
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascDui" />
					:
				</label>
			</td>
			<td>
				<html:text property="secPerPersona.perDui" styleClass="obligatorio"
					size="${tamDui}" onkeyup="maskDui(this);"
					 />
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascPrimerNombre" />
					:
				</label>
			</td>
			<td>
				<html:text property="secPerPersona.perPrimerNombre"
					styleClass="obligatorio" styleId="primerNombre" />
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascSegundoNombre" />
					:
				</label>
			</td>
			<td>
				<html:text property="secPerPersona.perSegundoNombre"
					styleClass="obligatorio"
					
					styleId="segundoNombre" />
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.asc.ascPrimerApellido" />
					:
				</label>
			</td>
			<td>
				<html:text property="secPerPersona.perPrimerApellido"
					styleClass="obligatorio" styleId="primerApellido" />
			</td>
			<td>
				<label>
					<bean:message key="lbl.asc.ascSegundoApellido" />
					:
				</label>
			</td>
			<td>
				<html:text property="secPerPersona.perSegundoApellido"
					styleClass="obligatorio" styleId="segundoApellido" />
			</td>
		</tr>
		<tr>
<%-- 			<td>
				<label>
					<bean:message key="lbl.cas.casReferencia" />
				</label>
				:
			</td>
			<td>
				<html:text property="referenciaCuenta" maxlength="13" size="20"
					styleId="refCodigo" styleClass="obligatorio" />

			</td> --%>
			<td>
				<label>
					<bean:message key="lbl.cas.casFechaApertuta" />
					:
				</label>
			</td>
			<td>
				<html:text onkeyup="mask(this);" property="casFechaApertura"
					 styleId="casFechaApertura"
					maxlength="10" size="10" styleClass="obligatorio" />
				<input style="height: 18px; position: relative; left: -0.2em;"
					id="button_casFechaApertura" type="button" value="...." />
				<script type="text/javascript">
				            Calendar.setup({
				              inputField    : "casFechaApertura",
				              button        : "button_casFechaApertura",
				              align         : "Br"
				            });
				            function handlerCombo(){
				            	$(casFechaApertura).toggle();
				            }
   				</script>
			</td>
		</tr>
		<tr>
			<td>
			<label>
					<bean:message key="lbl.pre.referencia" />
					:
				</label>
			</td>
			<td>
				<html:text property="preId" styleClass="obligatorio"
					 styleId="preId" />
			</td>
			<td>
				<label>
					<bean:message key="lbl.cas.casEstado" />
					:
				</label>
			</td>
			<td>
				<html:select property="estado" styleClass="obligatorio">
					<html:option value="-1">Seleccione un Estado</html:option>
					<html:optionsCollection label="estNombre" value="estId" name="estados"/>
				</html:select>
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.pre.buscarPrestamos" />
				</html:submit>
			</td>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.pre.forwardToNuevaSolicitud" />
				</html:submit>
			</td>
			<td>
				<input type="button" value="Limpiar"
					onclick="cleanFields('formId');">
			</td>
		<tr>
	</table>
	<html:hidden property="nueva" value="1" />
	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>	
</html:form>
<script type="text/javascript">
	function handlerDeleteButton(preId){
		$('#preId').val(preId);
		var msgText = '<h3 style="color: red;">Eliminaci&oacute;n de Pr&eacute;stamo</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Est&aacute; completamente seguro que desea eliminar este pr&eacute;stamo ?'+'<br>'+'</p>';	
			$.prompt(msgText,{
		    buttons:{Ok:true,Cancel:false}, 
		    prefix:'brownJqi',
		    callback : function(v,m){
		    	if(v == true){
		    		$('#accionId').val('eliminarPrestamo');
					$('#formId')[0].submit();
		    	}
		    }
		});
	}
</script>
