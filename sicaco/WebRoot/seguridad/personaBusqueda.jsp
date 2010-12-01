<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<style type="text/css">
table#tablaIdAjax{
	border-style: dotted;
	border-width: 1.0px;
	border-color: blue;
	font-size: 0.7em;
	font-weight: bold;
	
}
#loadingStyle{
	font-size: 0.8em;
	font-weight: bold;
	font-style: italic;
}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		$("#idPerDui").numeric();
    	$("#perPrimerNombreId").alpha();
    	$("#perSegundoNombreId").alpha();
    	$("#perTercerNombreId").alpha();
    	$("#perPrimerApellidoId").alpha();
    	$("#perSegundoApellidoId").alpha();
    	$("#perApellidoCasadaId").alpha();
  });
</script>

<div id="idPerForm">
	
 	<html:form action="${_accion}" method="post" focus="perId" styleId="formId">
 		<script>
      		var patternDui = new Array(${tamDui}-1,1);
      	</script>
      <table border="0">
      	<tr>
      		<td>
      			<label><bean:message key="lbl.persona.codigoUsuario"/></label>	
      		</td>
      		<td>
      			<input type="button" value=".." onclick="loadByCodigoUsuario();">
      		</td>
      	</tr>
      	<tr>
      		<td>
      			<label><bean:message key="lbl.usuario.perDui"/></label>
      		</td>
      		<td>
      			<html:text  maxlength="25" size="${tamDui}" styleId="idPerDui" property="perDui" styleClass="opcional"
      			onblur="ajaxCallNormal('${pageContext.request.contextPath}/seguridad${_accion}.do',{page : '0',accion : 'ajaxRequestWriter',perDui : $('#idPerDui').val()},'findPersonaById');"/>
      		</td>
      		<td>
      			<label><bean:message key="lbl.usuario.perNit"/></label>
      		</td>
      		<td>
      			<html:text onkeyup="maskNit(this);"  maxlength="17" size="17" property="perNit" styleClass="condicional"/>
      		</td>
      	</tr>
      	<tr>
      		<td colspan="2" align="right">
      			<div id="findPersonaById">
      			</div>
      		</td>
      	</tr>
      	<tr>
          <td><LABEL><bean:message key="lbl.persona.perPrimerNombre" />:</LABEL></td>
          <td><html:text property="perPrimerNombre" styleClass="obligatorio" /><span><bean:message key="msg.obligatorio"/></span>
          </td>
          <td>
          	<LABEL><bean:message key="lbl.persona.perSegundoNombre" />:</LABEL>
          </td>
          <td>
          		<html:text property="perSegundoNombre"/>
          </td>
        </tr>
        
        <tr>
          <td>
          	<LABEL><bean:message key="lbl.persona.perPrimerApellido" />:</LABEL>
          </td>
          <td>
          	<html:text property="perPrimerApellido" styleClass="obligatorio"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
          <td>
          	<LABEL><bean:message key="lbl.persona.perSegundoApellido" />:</LABEL>
          </td>
          <td>
          	<html:text property="perSegundoApellido"/>
          </td>
        </tr>
		<tr align="center">
			<td>
			</td>
			<td align="center">
          		<html:submit property="accion">
		    		<bean:message key="cmd.persona.buscar" />
		    	</html:submit>
		    	<html:submit property="accion">
		        	<bean:message key="cmd.persona.nuevo" />
		        </html:submit>
          	</td>
          	<td align="center">
          		<input type="button" value="Limpiar" onclick="cleanFields('formId');">
          	</td>
        </tr>
        <tr>
        	<td colspan="4" align="left">
        		<ul>
        			<li 
        				style="list-style-type: square; color: red;
        				font-size: 0.8em;
						font-weight: bold;">
        				:&nbsp;Usuario Inactivo
        				
        			</li>
        		</ul>
        	</td>
        </tr>
      </table>
     
      <html:hidden property="perId"/>
    </html:form>
 </div>
<script type="text/javascript">
	//convertFormToAjaxCallback('formId');
	dibujarFormularios();
	function loadByCodigoUsuario(){
		var msgText = "<table>"+
      		'<tr><td align="center" style="color: blue; font-size: 0.9em;">Busqueda C&oacute;digo Usuario</td></tr>' +
      		'<tr><td style="font-size: 0.8em; font-weight: bold;" align="left"> Ingrese el c&oacute;digo del usuario:</td>'+
      		'</tr>' +
      		'<tr>'+
      			'<td align="center">' +
      				'<input maxlength="25" size="25" type="text" id="codigoId" name="codigoName"/>' +
      			'</td>' +
      		'</tr>' +
      		'</table>';	
						$.prompt(msgText,{
		      				buttons:{Ok:true,Cancel:false}, 
		      				prefix:'brownJqi',
		      				callback : function(v,m){
		      					if(v == true){
		      					    var codigoValue = m.find('#codigoId').val();
		      					 	ajaxCallNormal('${pageContext.request.contextPath}/seguridad${_accion}.do',{page : '0',accion : 'ajaxRequestWriterCodigoUsuario',codigoUsuario : codigoValue},'lista-datos');
								}
		      				}
		});
	}
</script>
 