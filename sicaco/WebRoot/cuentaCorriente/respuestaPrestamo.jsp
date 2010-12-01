<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	function generar(){
	    $('#accionId').val('generarComprobante');
		$('#formId')[0].submit();
		$('#gen').attr('disabled','disabled');
	}
</script>

<html:form action="${_accion}" method="post" styleId="formId">
<table align="center">
	<tr>
		<logic:notPresent name="denegado">
			<td>
				<input type="button" value="Generar Comprobante de Aprobacion"  onclick="generar();" id="gen"> 
			</td>
		</logic:notPresent>
		<td>
			<html:submit property="accion" >
				<bean:message key="cmd.pre.finalizar"/>
			</html:submit>
		</td>
		
	</tr>
</table>
<html:hidden property="preId" styleId="preId"/>
<html:hidden property="estado" value="${estado}"/>
<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>

