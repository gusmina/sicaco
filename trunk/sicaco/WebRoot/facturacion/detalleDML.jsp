<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<html:form action="${_accion}" method="post"
	focus="id.invArtArticulo.artCodigo" styleId="formId">
	<table border="0">
			<tr>
				<logic:present name="filtroIngreso">
				<logic:equal name="filtroIngreso" value="0">
					<td>
						<label>
							<bean:message key="lbl.detalle.id.invArtArticulo.artCodigo" />
							:
						</label>
					</td>
					<td>
						<html:text property="id.invArtArticulo.artCodigo"
							styleClass="obligatorio" size="10" maxlength="10" />
						<span><bean:message key="msg.obligatorio" />
						</span>
					</td>

					<td>
						<html:submit property="accion">
							<bean:message key="cmd.detalle.cargaArticulo" />
						</html:submit>
					</td>
				</logic:equal>
				</logic:present>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.detalle.dfaCantidad" />
						:
					</label>
				</td>
				<td>
					<html:text property="dfaCantidad" styleClass="obligatorio"
						size="15" maxlength="15" />
						<html:select property="factorConversion" styleClass="obligatorio" value="${form.factorConversion}">
			<html:optionsCollection  label="cnvNombreMedida" name="conv" value="cnvFactor"/>     					
		</html:select>
					<span><bean:message key="msg.obligatorio" />
					</span>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.detalle.dfaDescripcion" />
						:
					</label>
				</td>
				<td>
					<html:textarea cols="30" rows="5" property="dfaDescripcion"
						styleClass="obligatorio" />
					<span><bean:message key="msg.obligatorio" />
					</span>
				</td>

			</tr>
			<tr>
				<logic:present name="filtroIngreso">
				<logic:equal name="filtroIngreso" value="0">
					<td>
						<label>
							<bean:message key="lbl.detalle.dfaPrecioUnitario" />
							:
						</label>
					</td>
					<td>
						<html:text  property="dfaPrecioUnitario" styleClass="obligatorio"
							size="10" maxlength="10" />
						<span><bean:message key="msg.obligatorio" />
						</span>
					</td>
					<logic:present name="filtroV">
				<logic:equal name="filtroV" value="0">
					<td>
						<label>
							<bean:message key="lbl.articulo.artPorcentajeUtilidad" />
							:
						</label>
					</td>
					<td>
						<html:text  property="porcentajeU" styleClass="obligatorio"
							size="2" maxlength="2" />
						<span><bean:message key="msg.obligatorio" />
						</span>
					</td>
					</logic:equal>
				</logic:present>
				</logic:equal>
				</logic:present>
				<logic:present name="filtroIngreso">
				<logic:equal name="filtroIngreso" value="1">
					<td>
						<label>
							<bean:message key="lbl.detalle.dfaPrecioTotal" />
							:
						</label>
					</td>
					<td>
						<html:text property="dfaPrecioTotal" styleClass="obligatorio"
							size="10" maxlength="10" />
						<span><bean:message key="msg.obligatorio" />
						</span>
					</td>
				</logic:equal>
				</logic:present>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="lbl.detalle.dfaExento" />
						:
					</label>
				</td>
				<td>
					<html:select property="dfaExento">
						<html:option value="1">
							<bean:message key="lbl.detalle.dfaExentoS" />
						</html:option>
						<html:option value="0">
							<bean:message key="lbl.detalle.dfaExentoN" />
						</html:option>
					</html:select>
				</td>

			</tr>

			<tr>
				<td>
					<input type="hidden" id="pageId" name="page" value="3" />
					<html:submit property="accion">
						<bean:message key="cmd.detalle.agregar" />
					</html:submit>
				</td>
				<td>
					<html:submit property="accion">
						<bean:message key="cmd.detalle.regresar" />
					</html:submit>
				</td>
				<td>
					<input type="button" value="Limpiar"
						onclick="cleanFields('formId');">
				</td>
			</tr>
	</table>
	<%-- =
  <input type="hidden" id="factura" name="factura" value=""/>
  <html:hidden property="fenId" value="${form.fenId}"/>
  <html:hidden property="fenTipoFactura" value="${form.fenTipoFactura}"/>
  <html:hidden property="moa" value="${form.moa}"/>
  <html:hidden property="fenTipoPago" value="${form.fenTipoPago}"/>
  <html:hidden property="fenSerieFactura" value="${form.fenSerieFactura}"/>
  <html:hidden property="fenNumeroFactura" value="${form.fenNumeroFactura}"/>
  <html:hidden property="fenRegistro" value="${form.fenRegistro}"/>
  <html:hidden property="fenFechaFactura" value="${form.fenFechaFactura}"/>
  <html:hidden property="invProProveedor.proId" value="${form.invProProveedor.proId}"/>
  <html:hidden property="invBodBodegas.bodId" value="${form.invBodBodegas.bodId}"/>
  <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
  <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>  
  --%>
	<html:hidden property="dfaId" value="${formD.dfaId}" />
	<html:hidden property="porcentajeU" value="${form.porcentajeU}"/>
	<logic:present name="filtroIngreso">
	<logic:equal name="filtroIngreso" value="1">
	<html:hidden property="id.invArtArticulo.artCodigo" value="falso"/>
	</logic:equal>
	</logic:present> 
	<html:hidden property="dfaPrecioTotal" value="${formD.dfaPrecioTotal}" />
	<html:hidden property="audFechaCreacion"
		value="${formD.audFechaCreacion}" />
	<html:hidden property="audUsuarioCreacion"
		value="${formD.audUsuarioCreacion}" />
	<html:hidden property="moa" value="${form.moa}" />
</html:form>