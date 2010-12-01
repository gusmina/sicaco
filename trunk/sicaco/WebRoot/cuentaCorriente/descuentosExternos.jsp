<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<div>
	<html:form action="${_accion}" method="post" styleId="formId">
		<logic:present name="asociado">
			<table align="center">
				<tr>
					<td>
						<label class="obligatorio">
							<bean:message key="lbl.pre.asocInfo" />
							:
						</label>
					</td>
					<td>
						<label>
							<bean:write name="asociado"
								property="secPerPersona.perPrimerNombre" />
						</label>
						&nbsp;
						<label>
							<bean:write name="asociado"
								property="secPerPersona.perSegundoNombre" />
						</label>
						&nbsp;
						<label>
							<bean:write name="asociado"
								property="secPerPersona.perPrimerApellido" />
						</label>
					</td>
					<td>
						<label class="obligatorio">
							<bean:message key="lbl.asc.ascCodigoAsociado" />
							:
						</label>
					</td>
					<td>
						&nbsp;
						<label>
							<bean:write name="asociado" property="ascCodigoAsociado" />
						</label>
					</td>
				</tr>
				<tr>
					<td>
						<label class="obligatorio">
							<bean:message key="lbl.pre.referencia" />
							:
						</label>
					</td>
					<td>
						&nbsp;
						<label>
							<bean:write name="prestamo" property="preId" />
						</label>
					</td>
					<td>
						<label class="obligatorio">
							<bean:message key="lbl.pre.montoSolicitado" />
							:
						</label>
					</td>
					<td>
						&nbsp;
						<label>
							<bean:write name="prestamo" property="preMontoSolicitado" format="'$'#,###,###.00"/>
						</label>
					</td>
				</tr>
			</table>
		</logic:present>
		<table align="center">
			<tr>
				<td>
					<label>
						<bean:message key="lbl.descuentosExternos.dexNombreDescuento" />
					</label>
				</td>
				<td>
					<html:text property="dexNombreDescuento" maxlength="100" size="30" readonly="true"  />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.descuentosExternos.dexMonto" />
					</label>
				</td>
				<td>
					<html:text property="dexMonto" maxlength="13" size="13" readonly="true"/>
				</td>
			</tr>
			<tr>
		</table>
		<table align="center">
			<tr>
				<td>
					<html:submit property="accion" styleId="guardar">
						<bean:message key="cmd.dex.regresar" />
					</html:submit>
				</td>
			</tr>
		</table>
		<%-- Campos ocultos --%>
		<html:hidden property="preId"  />
		<html:hidden property="dexId"  />
		<html:hidden property="perId" />
		<html:hidden property="estadoPrestamo" styleId="estadoPrestamo"/>
	</html:form>
</div>