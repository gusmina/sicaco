<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<div>
	<table>
		<tr>
			<td>
				<img src="/cetia/imagenes/error.png" />
			</td>
			<td>
				<div>
				Hubo un error inesperado lo sentimos, el error fue guardado para su
				posterior correcci&oacute;n, Haga clic en el bot&oacute;n regresar
				para volver a probar, o cierre sesi&oacute;n y pruebe mas tarde
				</div>
				<input type="button" value="Regresar" onclick="JavaScript:history.back();" />
			</td>
		</tr>
	</table>
</div>