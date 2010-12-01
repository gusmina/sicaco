<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<link rel="stylesheet" type="text/css" href="../css/zebra.css" >
<script type="text/javascript">
	function verDescuadres(){
		//alert($('#fechaCierre').val());
		ajaxCallNormal('${pageContext.request.contextPath}/contabilidad/cierreMensual.do',
		'accion=cargarDescuadres&fechaCierre='+$('#fechaCierre').val(),'war');
	}

</script>
<html:form action="${_accion}" method="post" styleId="formId">
	<table align="center">
	<tr>
		<td>
			<label class="obligatorio">
							<bean:message key="lbl.cierreMensual.instruccion" />:
			</label>
		</td>
		<td>
			<html:text style="float:left;"
						styleId="fechaCierre" styleClass="obligatorio"	onkeyup="mask(this);" 
						property="fechaCierre" maxlength="10" size="10"  onchange="verDescuadres()"/>
					<input style="height: 18px;"
						id="button_fechaCierre" type="button" value="..." />
					<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
					<script type="text/javascript">
		         		 Calendar.setup({
		           			inputField    : "fechaCierre",
		             		button        : "button_fechaCierre",
		            		align         	 : 	"Br"
		            	 });
		    		</script>
		</td>
	</tr>
		<tr>
		<td>
			<label class="obligatorio">
				<bean:message key="lbl.cierreMensual.acum" />:
			</label>
		</td>
		<td>
			<html:select property="acum">
				<html:option value="1">Si</html:option>
				<html:option value="0">No</html:option>
			</html:select>
		</td>
	</tr>
	<tr>
		<logic:present name="x">
			<td align="right">
				<html:submit property="accion">
					<bean:message key="cmd.cierreMensual.abrirMes" />
				</html:submit>
			</td>
		</logic:present>
			
			<td align="left">
				<html:submit property="accion">
					<bean:message key="cmd.cierreMensual.generarCierreMensual" />
				</html:submit>
			</td>
			
		</tr>
		<tr>
			<td colspan="2">
				<div class="warning" style="width: 100%; display:none; text-align: center;" id="war" >
					<label>Buscando...</label>
				</div>
			</td>
		</tr>
		
	</table>
</html:form>