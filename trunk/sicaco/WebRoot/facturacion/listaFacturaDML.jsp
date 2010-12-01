<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="${_accion}" method="post"  styleId="formId" >
	<logic:present name="compra">
		<logic:equal value="1" name="compra">
			<table border="0">
				<tr>
					<td><label><bean:message key="lbl.listaFac.fenNumeroFactura" />:</label></td>
					<td>
						<html:text property="fenNumeroFactura" styleClass="obligatorio" value="${form.fenNumeroFactura}" size="15" maxlength="15" />
					</td>
				</tr>
				<tr>
					<td>
						<label><bean:message key="lbl.listaFac.fenFechaFactura" />:</label>
					</td>
				</tr>
				<tr>
					<td>
						<label><bean:message key="lbl.listaFac.hseFechaFactIni" />:</label>
					</td>
					<td>
						<html:text style="float:left;" styleId="hseFechaFactIniId" styleClass="obligatorio"
							onkeyup="mask(this);" value="${form.hseFechaFactIni}"
							property="hseFechaFactIni" maxlength="10" size="10" />
						<input style="height: 18px;" id="button_hseFechaFactIniId"
							type="button" value="..." />
						<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
						<script type="text/javascript">
	         				   Calendar.setup({
	           					inputField    : "hseFechaFactIniId",
	             				button        : "button_hseFechaFactIniId",
	            				align         : "Br"
	            			   });
	    				</script>
					</td>				
					<td>
						<label><bean:message key="lbl.listaFac.hseFechaFactFin" />:</label>
					</td>
					<td>
						<html:text style="float:left;" styleId="hseFechaFactFinId" styleClass="obligatorio"
							onkeyup="mask(this);" value="${form.hseFechaFactFin}"
							property="hseFechaFactFin" maxlength="10" size="10" />
						<input style="height: 18px;" id="button_hseFechaFactFinId"
							type="button" value="..." />
						<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
						<script type="text/javascript">
	         				   Calendar.setup({
	           					inputField    : "hseFechaFactFinId",
	             				button        : "button_hseFechaFactFinId",
	            				align         : "Br"
	            			   });
	    				</script>
					</td>				
				</tr>
				<tr>
					<td><label><bean:message key="lbl.listaFac.proCodigo" />:</label></td>
					<td>
						<html:text property="proCodigo" styleClass="obligatorio" value="${form.proCodigo}" size="15" maxlength="25" />
					</td>
					<td><label><bean:message key="lbl.listaFac.proNombre" />:</label></td>
					<td>
						<html:text property="proNombre" styleClass="obligatorio" value="${form.proNombre}" size="15" maxlength="300"/>
					</td>
				</tr>
				<tr>
					<td><label><bean:message key="lbl.listaFac.bodId" />:</label></td>
					<td>
						<html:select property="invBodBodegas.bodId" styleId="bodId" size="1"
							styleClass="obligatorio" value="${form.invBodBodegas.bodId}">
							<html:option value="-1">n/a</html:option>
							<html:optionsCollection label="bodNombre" name="listaBodegas" value="bodId" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:submit property="accion">
							<bean:message key="cmd.listaFac.buscar"/>
						</html:submit>
						&nbsp;
						<logic:present name="voc" >
							<logic:equal value="1" name="voc">
								<html:submit property="accion">
									<bean:message key="cmd.listaFac.limpiarGuardadas"/>
								</html:submit>
							</logic:equal>
							<logic:equal value="2" name="voc">
								<html:submit property="accion">
									<bean:message key="cmd.listaFac.limpiarGuardadas"/>
								</html:submit>
							</logic:equal>
						</logic:present>
						&nbsp;
						<input type="button" value="Limpiar" onclick="cleanFields('formId');">
					</td>
				</tr>
			</table>
		</logic:equal>
		<logic:equal value="2" name="compra">
			<table border="0">
				<tr>
					<td><label><bean:message key="lbl.listaFac.fenSerie" />:</label></td>
					<td>
						<html:text property="fenSerieFactura" styleClass="obligatorio" value="${form.fenSerieFactura}" size="15" maxlength="15" />
					</td>
					<td><label><bean:message key="lbl.listaFac.fenNumeroFacturaVenta" />:</label></td>
					<td>
						<html:text property="fenNumeroFactura" styleClass="obligatorio" value="${form.fenNumeroFactura}" size="15" maxlength="15" />
					</td>
				</tr>
				<tr>
					<td>
						<label><bean:message key="lbl.listaFac.fenFechaFactura" />:</label>
					</td>
				</tr>
				<tr>
					<td>
						<label><bean:message key="lbl.listaFac.hseFechaFactIni" />:</label>
					</td>
					<td>
						<html:text style="float:left;" styleId="hseFechaFactIniId" styleClass="obligatorio"
							onkeyup="mask(this);" value="${form.hseFechaFactIni}"
							property="hseFechaFactIni" maxlength="10" size="10" />
						<input style="height: 18px;" id="button_hseFechaFactIniId"
							type="button" value="..." />
						<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
						<script type="text/javascript">
	         				   Calendar.setup({
	           					inputField    : "hseFechaFactIniId",
	             				button        : "button_hseFechaFactIniId",
	            				align         : "Br"
	            			   });
	    				</script>
					</td>				
					<td>
						<label><bean:message key="lbl.listaFac.hseFechaFactFin" />:</label>
					</td>
					<td>
						<html:text style="float:left;" styleId="hseFechaFactFinId" styleClass="obligatorio"
							onkeyup="mask(this);" value="${form.hseFechaFactFin}"
							property="hseFechaFactFin" maxlength="10" size="10" />
						<input style="height: 18px;" id="button_hseFechaFactFinId"
							type="button" value="..." />
						<input type="hidden" name="tipoDato" id="tipoDatoId" value="d">
						<script type="text/javascript">
	         				   Calendar.setup({
	           					inputField    : "hseFechaFactFinId",
	             				button        : "button_hseFechaFactFinId",
	            				align         : "Br"
	            			   });
	    				</script>
					</td>				
				</tr>
				<tr>
					<td><label><bean:message key="lbl.listaFac.bodId" />:</label></td>
					<td>
						<html:select property="invBodBodegas.bodId" styleId="bodId" size="1"
							styleClass="obligatorio" value="${form.invBodBodegas.bodId}">
							<html:option value="-1">n/a</html:option>
							<html:optionsCollection label="bodNombre" name="listaBodegas" value="bodId" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:submit property="accion">
							<bean:message key="cmd.listaFac.buscar"/>
						</html:submit>
						&nbsp;
						<logic:present name="voc">
							<logic:equal value="1" name="voc">
								<html:submit property="accion">
									<bean:message key="cmd.listaFac.limpiarGuardadas"/>
								</html:submit>
							</logic:equal>
							<logic:equal value="2" name="voc">
								<html:submit property="accion">
									<bean:message key="cmd.listaFac.limpiarGuardadas"/>
								</html:submit>
							</logic:equal>
						</logic:present>
						&nbsp;
						<input type="button" value="Limpiar" onclick="cleanFields('formId');">
					</td>
				</tr>
			</table>
		</logic:equal>
	</logic:present>
  	<html:hidden property="voc" value="${form.voc}"/>  
</html:form>