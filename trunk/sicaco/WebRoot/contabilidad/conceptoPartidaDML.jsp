<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page  import="com.cetia.sicaco.contabilidad.struts.form.ConceptoPartidaForm"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
<!--
	$(document).ready(function() {
		<%
		ConceptoPartidaForm cpaForm = (ConceptoPartidaForm)request.getAttribute("form");
		if(new Integer(cpaForm.getFuente())!= null){
		
			if(cpaForm.getFuente() == 0){
				out.print("$('#cpaConcepto').removeAttr('disabled');");
				out.print("$('#combo').attr('disabled','disabled');");
			}
			if(cpaForm.getFuente() == 1){
				out.print("$('#combo').removeAttr('disabled');");
				out.print("$('#cpaConcepto').attr('disabled','disabled');");
			}
		}else{
				out.print("$('#combo').removeAttr('disabled');");
				out.print("$('#cpaConcepto').attr('disabled','disabled');");
		}
		%>
		$("#ttr").click(function() {
 				$("#combo").removeAttr("disabled");
       			$("#cpaConcepto").attr("disabled","disabled");
       	});	
		
		$("#concept").click(function() {
				$("#cpaConcepto").removeAttr("disabled");
       			$("#combo").attr("disabled","disabled");
		});
			
	});
//-->
</script>

<html:form action="${_accion}" method="post" styleId="formId">
	<table border="0" align="center">
		<tr>
			<td colspan="2"><label>
				<bean:message key="lbl.cpa.seleccion" /></label>
			</td>
		</tr>
		<tr>
			<td>
				<html:radio property="fuente" value="1" styleId="ttr" styleClass="obligatorio" >
				<label><bean:message key="lbl.cpa.transaccion" /></label>
				</html:radio>
			</td>
			<td>
				<html:radio property="fuente" value="0" styleId="concept" styleClass="obligatorio" >
					<label><bean:message key="lbl.cpa.concepto" /></label>
				</html:radio>
			</td>
		</tr>
	</table>
	<table align="center">
			<tr>
				<td>
					<label>
						<bean:message key="lbl.cpa.tipoTransaccion" />
					</label>
				</td>
				<td>
					<html:select property="ctaTtrTipoTransaccion.ttrId" name="conceptoPartidaForm"
						value="${form.ctaTtrTipoTransaccion.ttrId}" styleId="combo">
						<html:optionsCollection name="ttrList" value="ttrId"
							label="ttrNombre" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.cpa.concepto" />
					</label>
				</td>
				<td>
					<html:textarea property="cpaConcepto" 
						styleId="cpaConcepto" />
				</td>
			</tr>
		<tr>
			<td>
				<label>
					<bean:message key="lbl.cpa.tipoConcepto" />
				</label>
			</td>
			<td>
				<html:select property="cpaDescripcionConcepto" 
					styleId="cpaDescripcionConcepto" styleClass="obligatorio">
					<html:option value="0">Partida</html:option>
					<html:option value="1">Libro Auxiliar</html:option>
				</html:select>
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<logic:equal value="false" name="form" property="mdf">
				<td>
					<html:submit property="accion">
						<bean:message key="cmd.cpa.guardar" />
					</html:submit>
				</td>
			</logic:equal>
			<logic:equal value="true" name="form" property="mdf">
				<td>
					<html:submit property="accion">
						<bean:message key="cmd.cpa.modificar" />
					</html:submit>
				</td>
			</logic:equal>
			<td>
				<input type="button" value="Limpiar"
					onclick="cleanFields('formId');$('#id').val('');$('#mdf').val('false');$('#ttrId').val('');$('#ttr').val('1')">
			</td>
		</tr>
	</table>
	<html:hidden property="cpaId" styleId="id" />
	<input id="pageId" type="hidden" name="page" value="3">
	<html:hidden property="mdf" styleId="mdf" />
	<html:hidden property="fuente"/>
</html:form>