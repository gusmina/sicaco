<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<link rel="stylesheet" type="text/css" href="../css/zebra.css" >
<center>
<div>
	<html:form action="/comentarios" method="post" styleId="formId">
		<table 
		style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;
		font-size: 13px;
		margin-bottom: 20px;
		margin-top:20px;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 70%;
		border-collapse: collapse;
		padding: 20px 20px 20px 20px;" id="hor-zebra"
		>
		<tr>
		<th colspan="2"> Comentarios
		</th>
		</tr>
		
			<tr class="odd">
				<td><label><bean:message key="lbl.comentario.comentario"/> :</label></td>
				<td>
					<html:textarea property="comComentario" rows="10" cols="25"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<html:submit property="accion">
						<bean:message key="cmd.comentario.guardar"/>
					</html:submit>
					<html:submit property="accion">
						<bean:message key="cmd.comentario.cancelar"/>
					</html:submit>
				</td>
			</tr>
		</table>

	</html:form>
</div>
</center>