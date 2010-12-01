<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<script type="text/javascript">

		$(document).ready(function() {
			$("#combo").asmSelect({
				listType: 'ul',
				addItemTarget: 'bottom',
				removeLabel: 'Eliminar',					
				highlightAddedLabel: 'Agregando: ',				
				highlightRemovedLabel: 'Eliminando: ',	
				animate: true,
				highlight: true,
				sortable: true
			});
			
		}); 

			function cargar(){
		    		$('#accionId').val('cargar');
					$('#formId')[0].submit();
		    	}
	</script>
<html:form action="${_accion}" method="post" styleId="formId">
	<table>
		<tr>
			<td>
				<label class="obligatorio">
					<bean:message key="lbl.estadoResult.banda" />:
				</label>
			</td>
		</tr>
		<tr>
			<td>
			<html:select property="banda" onchange="cargar();">
				<html:option value="0">-   (Seleccione un rubro) - </html:option>
				<html:option value="1">1.1 Ingresos (Productos) </html:option>
				<html:option value="2">1.2 Ingresos (Ventas)</html:option>
				<html:option value="3">2.  Costos</html:option>
				<html:option value="4">3.  Gastos</html:option>
				<html:option value="5">4.1 Otros Ingresos (+) </html:option>
				<html:option value="6">4.2 Otros Ingresos (-) </html:option>
				<html:option value="7">5.  Reservas</html:option>
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<label class="obligatorio">
					<bean:message key="lbl.estadoResult.instruccion" />:
				</label>
			</td>
		</tr>
	</table>
	<html:select property="posiciones" multiple="multiple"
		title="Seleccione una cuenta" styleId="combo">
		<html:optionsCollection name="cuentas" label="cueNombre" value="cueId" />
	</html:select>
	<table align="center">
		<tr>
			<td>
				<html:submit property="accion">
					<bean:message key="cmd.conf.guardar" />
				</html:submit>
			</td>
		</tr>
	</table>
	<html:hidden property="financ" />
	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
</html:form>