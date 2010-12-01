<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
	$(document).ready(function(){
	$("#ascRentaDomicilio").val(round_number( $("#cueSaldo").val(),2));
    	$("#tisNombreId").alphanumeric({allow:" "});
    	$("#tisPolizaId").alphanumeric({allow:" -"});
    	$("#tisDescripcionId").alphanumeric({allow:" -.;,()"});
    	$("#cueSaldo").alphanumeric({allow:" -.;,()"});
  });
  
	function guardaCP(valor){
		$('#cpId').val(Math.abs(valor));
		ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad${_accion}.do','accion=cargaCodigo&padreId='+Math.abs(valor),'cueCodDiv');
		ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad${_accion}.do','accion=cargaCodigoCue&padreId='+Math.abs(valor),'cueCodCueDiv');
		$('#cueCodigoCuentaId').val(valor);
	};
	
	function actualizaCodigo(){
		$('#cueCodigoCuentaId').val();
	}
</script>

<html:form action="${_accion}" method="post"  styleId="formId">
<table align="center">
	<logic:present name="edit">
		<logic:equal name="edit" value="0">
			<tr>
				<td>
					<label><bean:message key="lbl.cue.cueCodigoCuenta"/></label>:
				</td>
				<td>
					<table>
						<tr>
							<td>
								<div id="cueCodDiv">
								</div>
							</td>
							<td>
								<div id="cueCodCueDiv"> 
									<html:text styleClass="obligatorio" size="1" maxlength="1" property="cueCodCue"
											value="${form.cueCodCue}" styleId="cueCodCueId" /> 
									<span><bean:message key="msg.obligatorio"/></span>
								</div>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<label><bean:message key="lbl.cue.cueNombre" /></label>:
				</td>
				<td>
					<html:text styleClass="obligatorio" size="15" maxlength="100" property="cueNombre" value="${form.cueNombre}" styleId="cueNombreId"/> 
					<span><bean:message key="msg.obligatorio"/></span>
				</td>

			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.cue.conTicTipoCuenta.ticNombre" /></label>:
				</td>
				<td>
		          	<html:select property="tipoId" value="${form.tipoId}"  
						styleClass="obligatorio" styleId="ticId">
						<html:optionsCollection  label="ticNombre" name="lstTic" value="ticId"/>     					
					</html:select>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div id="reservaId">
						<logic:present name="reserva">
							<label><bean:message key="lbl.cue.cuePorcentaje"/></label>:
							<html:text styleClass="obligatorio" size="15" maxlength="30" property="cuePorcentaje"
									value="${form.cuePorcentaje}" styleId="cuePorcentajeId" /> 
							<span><bean:message key="msg.obligatorio"/></span>
						</logic:present>
					</div>
				</td>
				<logic:present name="reserva">
					<td>
						<label><bean:message key="lbl.cue.cuePorcentaje"/></label>:
					</td>
					<td>
						<html:text styleClass="obligatorio" size="15" maxlength="30" property="cuePorcentaje"
								value="${form.cuePorcentaje}" styleId="cuePorcentajeId" /> 
						<span><bean:message key="msg.obligatorio"/></span>
					</td>
				</logic:present>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.cue.cueDescripcion" /></label>:
				</td>
				<td colspan="3">
		          	<html:textarea  value="${form.cueDescripcion}" property="cueDescripcion" cols="35" rows="3"/>
				 </td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.cue.retroactiva"/></label>:
				</td>
				<td colspan="3">
					<html:select property="retroactiva" value="${form.retroactiva}"  
						styleClass="obligatorio" styleId="retroactivaId"
						onchange="ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad${_accion}.do','accion=muestraPorcentaje&tipoId2='+$('#retroactivaId').val(),'reservaId')"
						>
						<html:option value="0">Ninguna</html:option>
						<html:option value="1">Retroactiva</html:option>    
						<html:option value="2">Reservas</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.cue.cuentaPadre" /></label>:
				</td>
				<td colspan="3">
		          	<html:select property="cuentaPadre" value="${form.cuentaPadre}"  
						styleClass="obligatorio" styleId="cuentaPadreId"
						onchange="guardaCP($('#cuentaPadreId').val());
							ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad${_accion}.do','accion=cargarHijos&nivel=1&padreId='+$('#cuentaPadreId').val(),'hijos')">
						<html:option value="-1">---</html:option>
						<html:optionsCollection  label="cueNombre" name="lstCues" value="cueId"/>     					
					</html:select>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="hijos">
					</div>
			    </td>
			</tr>
		</logic:equal>
		
		
		<logic:equal name="edit" value="1">
			<tr class="obligatorio">
				<td colspan="4" align="center">
					<p></p>
					<bean:message key="lbl.cue.modificarCuenta" />
				</td>
			</tr>
			<tr class="obligatorio">
				<td>
					<label>
						<bean:message key="lbl.cue.cueCodigoCuenta" />
						:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<bean:write name="cueCuenta" property="cueCodigoCuenta" />
					</label>
				</td>
			</tr>
			<tr class="obligatorio">
				<td>
					<label>
						<bean:message key="lbl.cue.cueNombre" />
						:
					</label>
				</td>
				<td>
					<html:text styleClass="obligatorio" size="15" maxlength="100" property="cueNombreP" value="${form.cueNombreP}" styleId="cueNombreIdP"/> 
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
								<td>
					<label><bean:message key="lbl.cue.saldo" /></label>:
				</td>
				<td>
					<html:text styleClass="obligatorio" size="15" maxlength="100" property="cueSaldoActual" styleId="cueSaldo" readonly="true"/> 
				</td>
			</tr>
			<tr class="obligatorio">
				<td>
					<label>
						<bean:message key="lbl.cue.conTicTipoCuenta.ticNombre" />
						:
					</label>
				</td>
				<td>
					<bean:write name="cueCuenta" property="conTicTipoCuenta.ticNombre"/>
		          	<html:select property="tipoId" value="${form.tipoId}" 
						styleClass="obligatorio" styleId="ticId">
						<html:optionsCollection  label="ticNombre" name="lstTic" value="ticId"/>     					
					</html:select>
				</td>
				<td>
					<label>
						<bean:message key="lbl.cue.cuePosteable" />
						:
					</label>
				</td>
				<td>
					<label style="color: black;">
						<logic:equal name="cueCuenta" property="cuePosteable" value="1">
							<bean:message key="lbl.no"/>
						</logic:equal>
						<logic:equal name="cueCuenta" property="cuePosteable" value="0">
							<bean:message key="lbl.si"/>
						</logic:equal>
					</label>
				</td>
			</tr>
			<tr class="obligatorio">
				
				<logic:present name="reserva">
					<td colspan="2">
						<label>
							<bean:message key="lbl.cue.cuePorcentaje" />
							:
						</label>
					</td>
					<td>
						<html:text styleClass="obligatorio" size="15" maxlength="30" property="cuePorcentajeP"
								value="${form.cuePorcentajeP}" styleId="cuePorcentajeId" /> 
						<span><bean:message key="msg.obligatorio"/></span>
					</td>
				</logic:present>
			</tr>
			<tr class="obligatorio">
			<td>
				<label>
						<bean:message key="lbl.cue.cueDescripcion" />
						:
					</label>
				</td>
				<td>
		          	<html:textarea  value="${form.cueDescripcionP}" property="cueDescripcionP" cols="35" rows="3"/>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.cue.retroactiva"/></label>:
				</td>
				<td colspan="3">
					<html:select property="retroactivaP" value="${form.retroactivaP}"  
						styleClass="obligatorio" styleId="retroactivaPId"
						onchange="ajaxCallNormal('${pageContext.request.contextPath}/contabilidad${_accion}.do','accion=muestraPorcentaje&tipoId2='+$('#retroactivaPId').val(),'reservaId')"
						>
						<html:option value="0">Ninguna</html:option>
						<html:option value="1">Retroactiva</html:option>    
						<html:option value="2">Reservas</html:option>
					</html:select>
					
				</td>
			</tr>
		
			<tr>
				<td>
					<label><bean:message key="lbl.cue.cueEstado"/></label>:
				</td>
				<td colspan="3">
					<html:select property="cueEstadoP" value="${form.cueEstadoP}"  
						styleClass="obligatorio" styleId="cueEstadoId">
						<html:option value="1">Activa</html:option>    
						<html:option value="0">Inactiva</html:option>
					</html:select>
					<input type="button" value="<bean:message key="lbl.cue.salvar" />" onclick="handlerEditButton($('#cueId').val())" />
				</td>
			</tr>
			<tr class="obligatorio">
				<td colspan="4" align="center">
					<p></p><p></p>
					<bean:message key="lbl.cue.agregarNuevaCuenta" />
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.cue.cueCodigoCuenta"/></label>:
				</td>
				<td>
					<table>
						<tr>
							<td>
								<div id="cueCodDiv">
									<html:text styleClass="obligatorio" size="13" property="cueCodigoCuenta"
											value="${form.cueCodigoCuenta}" styleId="cueCodigoCuentaId" readonly="true"/> 
								</div>
							</td>
							<td>
								<div id="cueCodCueDiv"> 
									<html:text styleClass="obligatorio" size="1" maxlength="1" property="cueCodCue"
											value="${form.cueCodCue}" styleId="cueCodCueId" /> 
									<span><bean:message key="msg.obligatorio"/></span>
								</div>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<label><bean:message key="lbl.cue.cueNombre" /></label>:
				</td>
				<td>
					<html:text styleClass="obligatorio" size="15" maxlength="100" property="cueNombre" value="${form.cueNombre}" styleId="cueNombreId"/> 
					<span><bean:message key="msg.obligatorio"/></span>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.cue.conTicTipoCuenta.ticNombre" /></label>:
				</td>
				<td>
		          	<html:select property="tipoId" value="${form.tipoId}"  
						styleClass="obligatorio" styleId="ticId"
						onchange="ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad${_accion}.do','accion=muestraPorcentaje&tipoId2='+$('#ticId').val(),'reservaId')" >
						<html:optionsCollection  label="ticNombre" name="lstTic" value="ticId"/>     					
					</html:select>
				</td>
			</tr>
			<tr>				
				<td colspan="3">
					<div id="reservaId">
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.cue.cueDescripcion" /></label>:
				</td>
				<td colspan="3">
		          	<html:textarea  value="${form.cueDescripcion}" property="cueDescripcion" cols="35" rows="3"/>
				 </td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.cue.retroactiva"/></label>:
				</td>
				<td colspan="3">
					<html:select property="retroactiva" value="${form.retroactiva}"  
						styleClass="obligatorio" styleId="retroactivaId"
						onchange="ajaxCallNormal('${pageContext.request.contextPath}/contabilidad${_accion}.do','accion=muestraPorcentaje&tipoId2='+$('#retroactivaId').val(),'reservaId')"
						>
						<html:option value="0">Ninguna</html:option>
						<html:option value="1">Retroactiva</html:option>    
						<html:option value="2">Reservas</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<td>
					<label><bean:message key="lbl.cue.cuentaPadre" /></label>:
				</td>
				<td colspan="3">
		          	<html:select property="cuentaPadre" value="${form.cuentaPadre}"  
						styleClass="obligatorio" styleId="cuentaPadreId"
						onchange="guardaCP($('#cuentaPadreId').val());
							ajaxCallSincrono('${pageContext.request.contextPath}/contabilidad${_accion}.do','accion=cargarHijos&nivel=1&padreId='+$('#cuentaPadreId').val(),'hijos')">
						<html:option value="$('#cueIdId').val()">---</html:option>
						<html:optionsCollection  label="cueNombre" name="lstCues" value="cueId"/>     					
					</html:select>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="hijos">
					</div>
			    </td>
			</tr>
		</logic:equal>
		<logic:equal value="0" name="edit">
			<tr>
				<td colspan="3">
					<html:submit property="accion" >
						<bean:message key="cmd.cue.guardar" />
					</html:submit> 
					<html:submit property="accion" >
						<bean:message key="cmd.cue.cancelar" />
					</html:submit> 
				</td>
			</tr>
		</logic:equal>
		<logic:equal value="1" name="edit">
			<tr>
				<td colspan="3">
					<html:submit property="accion" >
						<bean:message key="cmd.cue.guardar" />
					</html:submit> 
					<html:submit property="accion" >
						<bean:message key="cmd.cue.cancelar" />
					</html:submit> 
				</td>
			</tr>
		</logic:equal>
	</logic:present>
</table> 
	<input id="pageId" type="hidden" name="page" value="3">

	<input class="exclude" type="hidden" name="accion" id="accionId" value=""/>
	<html:hidden property="cp" styleId="cpId" value="-1"/>
	<html:hidden property="cueId" styleId="cueIdId" value="${form.cueId}"/>
	<html:hidden property="audFechaCreacion" value="${form.audFechaCreacion}" />
	<html:hidden property="audUsuarioCreacion" value="${form.audUsuarioCreacion}" />
</html:form>

 <script type="text/javascript">
	 var deleteButton = 1;
	
	
	function handlerEditButton(id){
		$('#cueId2').val(id);
		var msgText = '<h3 style="color: red;">!!Modificaci&oacute;n de Cuenta!!</h3>'+
    		'<p style="font-size: 1.4em; font-weight: bold;">¿Esta completamente seguro que desea realizar la modificaci&oacute;n ?'+'<br>'+'</p>';	
			$.prompt(msgText,{
		    buttons:{Ok:true,Cancel:false}, 
		    prefix:'brownJqi',
		    callback : function(v,m){
		    	if(v == true){
		    		$('#accionId').val('salvar');
		    		$('#pageId').val('0');
					$('#formId')[0].submit();
		    	}
		    }
		});
	}
	
</script>
