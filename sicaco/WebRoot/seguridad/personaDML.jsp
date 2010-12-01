<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
		$("#perDuiId").numeric();
    	$("#perPrimerNombreId").alpha();
    	$("#perSegundoNombreId").alpha();
    	$("#perTercerNombreId").alpha();
    	$("#perPrimerApellidoId").alpha();
    	$("#perSegundoApellidoId").alpha();
    	$("#perApellidoCasadaId").alpha();
    	$("#perMunicipioId").alpha({allow:" "});
    	$("#perCalleId").alphanumeric({allow:"., #"});
    	$("#perColoniaBarrioId").alpha({allow:"., #"});
    	$("#perCodigoPostalId").alphanumeric({allow:"., #"});
    	$("#correElectronicoId").alphanumeric({allow:".@"});
    	$("#perLugarNacimientoId").alpha({allow:" "});
  });
</script>

<logic:notPresent parameter="ajax">
<div id="idPerForm">
 	<html:form action="${_accion}" method="post" focus="perId" styleId="formId">
      <script type="text/javascript">
      	$(document).ready(function(){
      		var patternDui = new Array(${tamDui}-1,1);
      	});
      	</script>
      <table border="0" cellspacing="7">
      	
      	<tr>
      		<td>
      			<label><bean:message key="lbl.usuario.perDui"/></label>
      		</td>
      		<td>
      			<logic:present name="boton">
      				<logic:equal name="boton" value="1">
      					<html:text disabled="true"  maxlength="25" size="${tamDui}" styleId="perDuiId" property="perDui" styleClass="obligatorio"/>
						<input type="hidden" name="perDui" value="${form.perDui}"/>      				
      					<span><bean:message key="msg.obligatorio"/></span>
      				</logic:equal>
      			</logic:present>
      			<logic:present name="boton">
      				<logic:equal name="boton" value="0">
      					<html:text   maxlength="${tamDui}" size="${tamDui}" property="perDui" styleClass="obligatorio"/>
      				</logic:equal>
      			</logic:present>
      		</td>
      		<td>
      			<label><bean:message key="lbl.usuario.perNit"/></label>
      		</td>
      		<td>
      			<logic:present name="boton">
      				<logic:equal name="boton" value="1">
      					<html:text disabled="true" onkeyup="maskNit(this);" maxlength="17" size="17" property="perNit" styleClass="condicional"/>
      					<input type="hidden" name="perNit" value="${form.perNit}"/>
      					<span><bean:message key="msg.obligatorio"/></span>
      				</logic:equal>
      			</logic:present>
      			<logic:present name="boton">
      				<logic:equal name="boton" value="0">
      					<html:text  onkeyup="maskNit(this);" maxlength="17" size="17" property="perNit" styleClass="condicional"/>
					</logic:equal>
      			</logic:present>
      		</td>
      	</tr>
        <tr>
          <td><LABEL><bean:message key="lbl.persona.perPrimerNombre" />:</LABEL></td>
          <td><html:text styleId="perPrimerNombreId" property="perPrimerNombre" maxlength="25" size="15" styleClass="obligatorio" /><span><bean:message key="msg.obligatorio"/></span>
          </td>
          <td>
          	<LABEL><bean:message key="lbl.persona.perSegundoNombre" />:</LABEL>
          </td>
          <td>
          		<html:text styleId="perSegundoNombreId" property="perSegundoNombre" maxlength="25" size="15"/>
          </td>
        </tr>
        <tr>
          <td>
          	<LABEL><bean:message key="lbl.persona.perTercerNombre" />:</LABEL>
          </td>
          <td>
          	<html:text styleId="perTercerNombreId" property="perTercerNombre" maxlength="25" size="17"/>
          </td>
        </tr>
        <tr>
          <td>
          	<LABEL><bean:message key="lbl.persona.perPrimerApellido" />:</LABEL>
          </td>
          <td>
          	<html:text styleId="perPrimerApellidoId" property="perPrimerApellido" maxlength="25" size="15" styleClass="obligatorio"/>
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
          <td>
          	<LABEL><bean:message key="lbl.persona.perSegundoApellido" />:</LABEL>
          </td>
          <td>
          	<html:text styleId="perSegundoApellidoId" property="perSegundoApellido" maxlength="25" size="15"/>
          </td>
        </tr>
        <tr> 
          <td>
          	<LABEL><bean:message key="lbl.persona.perApellidoCasada"/>:</LABEL>
          </td>
          <td>
          	<html:text styleId="perApellidoCasadaId" property="perApellidoCasada" maxlength="25" size="17"/>
          </td>
        </tr>
        <tr>
          <td><LABEL><bean:message key="lbl.persona.perGenero" />:</LABEL></td>
          <td>
        	<html:select property="perGenero"  styleClass="obligatorio">
          		<html:option value="M">
          			<bean:message key="lbl.persona.perGeneroM"/>
          		</html:option>
          		<html:option value="F">
          			<bean:message key="lbl.persona.perGeneroF"/>
          		</html:option>
          	</html:select>	
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
        </tr>
        <tr>
          <td><LABEL><bean:message key="lbl.persona.perFechaNacimiento" />:</LABEL></td>
          <td><html:text style="float:left;" styleId="perFechaNacimientoId" onkeyup="mask(this);"  property="perFechaNacimiento" maxlength="10" size="10"/>
        	<input  style="height: 18px;" id="button_perFechaNacimientoId" type="button"  value="..."/>
		  </td>
          <td><LABEL><bean:message key="lbl.persona.perLugarNacimiento" />:</LABEL></td>
          <td><html:text styleId="perLugarNacimientoId" property="perLugarNacimiento"  maxlength="50" size="20"/></td>
        </tr>
        <tr>
        	<td>
        		<LABEL><bean:message key="lbl.persona.secSucSucursal.surId" />:</LABEL>
        	</td>
        	<td>
        		<html:select property="secSucSucursal.sucId" styleClass="obligatorio" value="${form.secSucSucursal.sucId}">
					<html:optionsCollection  label="sucNombre" name="sucList" value="sucId"/>     					
				</html:select>
        	</td>
        </tr>
        <tr>
        	<td>
        		<LABEL><bean:message key="lbl.persona.zonId" />:</LABEL>
        	</td>
        	<td>
        		<html:select property="zonId" styleClass="obligatorio">
					<html:optionsCollection  label="zonNombre" name="zonaList" value="zonId"/>     					
				</html:select>
        	</td>
        </tr>
        <tr>
          <td><LABEL><bean:message key="lbl.persona.perDepartamento" />:</LABEL></td>
          <td>
       		<html:select property="secDppDepartamentoPais.dppId" styleClass="obligatorio"
       			value="${form.secDppDepartamentoPais.dppId}">
       			<html:option value="-1"> --- </html:option>
				<html:optionsCollection  label="dppNombre" name="dppList" value="dppId"/>     					
			</html:select>
       	  </td>
          <td><LABEL><bean:message key="lbl.persona.perMunicipio" />:</LABEL></td>
          <td><html:text styleId="perMunicipioId" property="perMunicipio" size="20" maxlength="50"  /></td>
        </tr>
        <tr>
          <td><LABEL><bean:message key="lbl.persona.perCalle" />:</LABEL></td>
          <td><html:text styleId="perCalleId" property="perCalle"  maxlength="50" size="20"/></td>
          <td><LABEL><bean:message key="lbl.persona.perColoniaBarrio" />:</LABEL></td>
          <td><html:text styleId="perColoniaBarrioId" property="perColoniaBarrio" maxlength="50" size="20" /></td>
        </tr>
      
		        <tr>
			          <td><LABEL><bean:message key="lbl.persona.perCodigoPostal" />:</LABEL></td>
			          <td><html:text styleId="perCodigoPostalId" property="perCodigoPostal" /></td>
			    <logic:present name="filtro">
        			<logic:present name="boton">
        				<logic:equal name="boton" value="0">
			          		<td><label><bean:message key="lbl.correo.id.celCorreoElectronico" />:</label></td>
			          		<td>
				          	<html:text styleId="correElectronicoId" property="correElectronico" styleClass="obligatorio"/>
				         	<span><bean:message key="msg.obligatorio"/></span>
			          		</td>
			          
		        		</logic:equal>
        			</logic:present>
        		</logic:present>
		        </tr>
		<tr>
          <td colspan="4" style="padding: 5">
          	<logic:present name="filtro">
          		<%-- Cuando no tiene filtro van a ser acciones de DML --%>
          		<logic:equal name="filtro" value="0">
          			<logic:present name="boton">
          				<%-- Si el parametro es igual a cero servira para agregar un nuevo usuario --%>
          				<logic:equal name="boton" value="0">
          						<table align="center">
          								<tr>
          									<td>
					          					<html:submit   property="accion" >
									          		<bean:message key="cmd.persona.guardarAdministrador" />
									          	</html:submit>
								      		</td>
											<td>
												<html:submit onclick="$('#pageId').val('0')"  property="accion">
										       		<bean:message key="cmd.persona.cancelar" />
										       	</html:submit>						
											</td>
										</tr>
								</table>	          				
          				</logic:equal>
          				<%-- Si el parametro es uno indiciara que se va a editar el usuario --%>
          				<logic:equal name="boton" value="1">
          						<table align="center" >
          							<tr>
          								<td>
          									<html:submit property="accion">
				          						<bean:message key="cmd.persona.modificar" />
				          					</html:submit>
          								</td>	
          							
          								<td>
											<html:submit onclick="$('#pageId').val('0')" property="accion">
										       		<bean:message key="cmd.persona.cancelar" />
										    </html:submit>						
										</td>
          							</tr>
          						</table>
          				</logic:equal>
          					<input id="pageId" type="hidden" name="page" value="3"/>
          			</logic:present>
          		</logic:equal>
          		<%-- Solo acciones de busqueda un boton nada mas --%>
          		<logic:equal name="filtro" value="1">
          			<html:submit property="accion">
		          		<bean:message key="cmd.persona.buscar" />
		          	</html:submit>
		          	
          		</logic:equal>
          	</logic:present>
          </td>
        </tr>
      </table>
      <html:hidden property="perId"/>
      <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
      <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
      <html:hidden property="perEstado" value="${form.perEstado}"/>
    </html:form>
    <script type="text/javascript">
            Calendar.setup({
              inputField    : "perFechaNacimientoId",
              button        : "button_perFechaNacimientoId",
              align         : "Br"
            });
    </script>
 </div>
 <script>
 	function ajaxCall(link){
 		$('#idPerForm').load(link,{ajax:true});
 		dibujarFormularios();
 	}
 
 </script>
  </logic:notPresent>
  
  <logic:present parameter="ajax">
   	<html:form action="${_accion}" method="post" focus="perId">
      <table border="0">
      	<tr>
      		<td>
      			<label><bean:message key="lbl.usuario.perDui"/></label>
      		</td>
      		
      		<td>
      			<html:text property="perDui" maxlength="${tamDui}" size="${tamDui}" styleClass="opcional"/>
      		</td>
      		<td>
      			<label><bean:message key="lbl.usuario.perNit"/></label>
      		</td>
      		<td>
      			<html:text property="perNit" styleClass="condicional"/>
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
          	<LABEL><bean:message key="lbl.persona.perTercerNombre" />:</LABEL>
          </td>
          <td>
          	<html:text property="perTercerNombre"/>
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
        <tr> 
          <td>
          	<LABEL><bean:message key="lbl.persona.perApellidoCasada" />:</LABEL>
          </td>
          <td>
          	<html:text property="perApellidoCasada"/>
          </td>
        </tr>
        <tr>
          <td><LABEL><bean:message key="lbl.persona.perGenero" />:</LABEL></td>
          <td>
        	<html:select property="perGenero"  styleClass="obligatorio">
          		<html:option value="M">
          			<bean:message key="lbl.persona.perGeneroM"/>
          		</html:option>
          		<html:option value="F">
          			<bean:message key="lbl.persona.perGeneroF"/>
          		</html:option>
          	</html:select>	
          	<span><bean:message key="msg.obligatorio"/></span>
          </td>
        </tr>
        <tr>
          <td><LABEL><bean:message key="lbl.persona.perFechaNacimiento" />:</LABEL></td>
          <td><html:text property="perFechaNacimiento" /></td>
          <td><LABEL><bean:message key="lbl.persona.perLugarNacimiento" />:</LABEL></td>
          <td><html:text property="perLugarNacimiento" /></td>
        </tr>
        <tr>
          <td><LABEL><bean:message key="lbl.persona.perDepartamento" />:</LABEL></td>
          <td>
       		<html:select property="secDppDepartamentoPais.dppId" styleClass="obligatorio">
				<html:optionsCollection  label="dppNombre" name="dppList" value="dppId"/>     					
			</html:select>
       	  </td>
          <td><LABEL><bean:message key="lbl.persona.perMunicipio" />:</LABEL></td>
          <td><html:text property="perMunicipio" /></td>
        </tr>
        <tr>
          <td><LABEL><bean:message key="lbl.persona.perCalle" />:</LABEL></td>
          <td><html:text property="perCalle" /></td>
          <td><LABEL><bean:message key="lbl.persona.perColoniaBarrio" />:</LABEL></td>
          <td><html:text property="perColoniaBarrio" /></td>
        </tr>
        <tr>
          <td><LABEL><bean:message key="lbl.persona.perCodigoPostal" />:</LABEL></td>
          <td><html:text property="perCodigoPostal" /></td>
        </tr>
		<tr>
          <td colspan="2" align="center">
          	<logic:present name="filtro">
          		<%-- Cuando no tiene filtro van a ser acciones de DML --%>
          		<logic:equal name="filtro" value="0">
          			<logic:present name="boton">
          				<%-- Si el parametro es igual a cero servira para agregar un nuevo usuario --%>
          				<logic:equal name="boton" value="0">
          					<html:submit property="accion">
				          		<bean:message key="cmd.persona.agregar" />
				          	</html:submit>
          				</logic:equal>
          				<%-- Si el parametro es uno indiciara que se va a editar el usuario --%>
          				<logic:equal name="boton" value="1">
          					<html:submit property="accion">
				          		<bean:message key="cmd.persona.modificar" />
				          	</html:submit>
				          	<html:submit property="accion">
				          		<bean:message key="cmd.persona.eliminar" />
				          	</html:submit>
          				</logic:equal>
          				
          			</logic:present>
          			<html:submit property="accion">
		          		<bean:message key="cmd.persona.cancelar" />
		          	</html:submit>
          		</logic:equal>
          		<%-- Solo acciones de busqueda un boton nada mas --%>
          		<logic:equal name="filtro" value="1">
          			<html:submit property="accion">
		          		<bean:message key="cmd.persona.buscar" />
		          	</html:submit>
          			<html:submit property="accion">
		          		<bean:message key="cmd.persona.mostrar" />
		          	</html:submit>
          		</logic:equal>
          		<logic:equal name="filtro" value="2">
          			<logic:present name="boton">
          				<logic:equal name="boton" value="2">
          					<html:submit property="accion">
				       			<bean:message key="cmd.persona.busqueda" />
				       		</html:submit>
				       		<html:submit property="accion">
		          				<bean:message key="cmd.persona.cancelar" />
		          			</html:submit>
				    	</logic:equal>
				    </logic:present>
          		</logic:equal>
          	</logic:present>
          </td>
        </tr>
      </table>
      <html:hidden property="perId"/>
      <html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}"/>
      <html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}"/>
      <html:hidden property="perEstado" value="${form.perEstado}"/>
    </html:form>
  </logic:present>