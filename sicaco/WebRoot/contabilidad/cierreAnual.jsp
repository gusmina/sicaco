<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<script  type="text/javascript">
	$(document).ready(function(){
		$("#year").numeric();
		});
</script>

<html:form action="${_accion}" method="post" styleId="formId">
	<table>
		<tr>
			<td>
				<label class="obligatorio">
							<bean:message key="lbl.cierreAnual.fecha" />:
			</label>
			</td>
			<td><!-- ${form.fechaCierre} -->
				<html:text style="float:left;" styleId="fechaCierreId" onkeyup="mask(this);" value="" property="fechaCierre" maxlength="10" size="10"/>
		        	<input  style="height: 18px;" id="button_fechaCierreId" type="button"  value="..."/>
        			<script type="text/javascript">
		        	    Calendar.setup({
		            	  inputField    : "fechaCierreId",
		              	button        : "button_fechaCierreId",
		            	  align         : "Br"
		           		});
		    		</script>
						
			</td>
		</tr>
		<tr>
			<td>
				<label class="obligatorio">
							<bean:message key="lbl.cierreAnual.perdidaGanancias" />:
			</label>
			</td>
			<td>
				<html:select property="perdidasGananciasId">
					<html:optionsCollection name="cuentas" label="cueNombre" value="cueId"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
			<label class="obligatorio">
				<bean:message key="lbl.cierreAnual.excedentes" />:
			</label>
			</td>
			<td>
				<html:select property="excedentesId">
					<html:optionsCollection name="cuentas" label="cueNombre" value="cueId"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<label class="obligatorio">
				<bean:message key="lbl.cierreAnual.deficit" />:
			</label>
			</td>
			<td>
				<html:select property="deficitId">
					<html:optionsCollection name="cuentas" label="cueNombre" value="cueId"/>
				</html:select>
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td>
					<html:submit property="accion">
					<bean:message key="cmd.cierreAnual.realizarCierre" />
				</html:submit>
			</td>
		</tr>
	</table>
</html:form>