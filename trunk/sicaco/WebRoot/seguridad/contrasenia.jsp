<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<link rel="stylesheet" type="text/css" href="../css/zebra.css" >
<script type="text/javascript">
<!--

//-->

	$(document).ready(function(){
			$(".msghi").hide();
	});


	function verificar(id){
			var t1= window.document.getElementById(id).value;
			var l1 = t1.length;
			$(".msghi").fadeOut(70);
			if(l1 > 9){
				$(".msghi").fadeIn(70);
			} 	
		}
</script>

<div >
    <html:form action="${_accion}" method="post" focus="iseContrasenia" styleId="formId" >
      <table border="0">
      	<tr>
      		<td>
      			<bean:message key="lbl.sesion.iseNombreUsuario" />
      		</td>
      		<td>
      			<input type="text" name="iseNombreUsuario" disabled="disabled" value="${iseNombreUsuario}"/>
      		</td>
      	</tr>
      	<tr>
          <td><bean:message key="lbl.constrasenna.acontrasenna"  /></td>
          <td>
          	<html:password property="contrasenaA" maxlength="10" />
          </td>
        </tr>
        <tr>
          <td><bean:message key="lbl.constrasenna.ncontrasenna" /></td>
          <td>
          	<html:password property="contrasenaN" maxlength="10" onkeyup="verificar('pw1')" styleId="pw1"/>
          </td>
        </tr>
        <tr>
          <td><bean:message key="lbl.constrasenna.rcontrasenna" /></td>
          <td>
          	<html:password property="contrasenaV" maxlength="10" onkeyup="verificar('pw2')" styleId="pw2"/>
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
          	<html:submit property="accion">
         		<bean:message key="cmd.sesion.newPwd" />
          	</html:submit>
        </td>
        </tr>
      </table>
      <div style="width: 100%; height: 80px;">
      	<div class="msghi" style="text-align: left;">      
	      <div class="ui-state-highlight ui-corner-all" 
	      style="
	      		margin-top: 20px; padding: 0pt 0.7em;
	      		width: 350px;
	      	"
	      	>
	      	<p><span style="float: left; margin-right: 0.3em;" class="ui-icon ui-icon-info"> 
	      	</span>
	      	<strong >¡Atenci&oacute;n! </strong><br/>
					El tama&ntilde;o m&aacute;ximo para la contrase&ntilde;a es de 10 caracteres
				</p> 
	      </div>
	      
      	</div>
      
      </div>
      
      
      
  	<html:hidden property="iseNombreUsuario" value="${iseNombreUsuario}"/>
  	</html:form>
 </div>