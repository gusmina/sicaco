
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


    <html:form action="/autenticacion" method="post" focus="nombreUsuario" styleId="formId">
   		<table >
        <tr>
          <td><label>Login:</label></td>
          <td>
          		<html:text property="nombreUsuario" styleClass="obligatorio" errorStyleClass="error"/>
          </td>
        </tr>
        <tr>
          <td><label>Password:</label></td>
          <td><html:password property="contrasenia" styleClass="obligatorio" errorStyleClass="error" />
         
          </td>
        </tr>
        <tr>
          <td colspan="2" align="left"><html:submit property="accion">
          <bean:message key="cmd.inises.entrar"/>
          </html:submit>
          </td>
        </tr>
      </table>

		<input type="hidden" name="page" value="3">
    </html:form>
    
  <script>
  $(document).ready(function(){
	  //Desaparecemos el menu
	  $('#menu-vertical').hide();
	  $('#contenido-v').attr('style', 'float:left;width:100%;height:300px;background-image:url(/cetia/imagenes/tema/ornamento2.jpg);background-repeat:no-repeat;background-position:right bottom;')
	  $('fieldset').attr('style', 'width:300px;height:240px;padding:0;padding-left:30px;background-image:url(/cetia/imagenes/tema/ornamento.jpg);background-repeat:no-repeat;background-position:left top;border:0;')
	  $('table').removeAttr('style').attr('style','border: 1px solid grey;width:260px;height:150px;')
  });
  </script>
