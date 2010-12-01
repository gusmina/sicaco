<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<logic:notPresent parameter="ajax">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" type="text/css" href="../css/zebra.css" >
<html:html lang="true">
  <head>
    <link rel="shortcut icon" href="../imagenes/favicon.ico">
    <title>
    	[<tiles:getAsString name="titulo"/>] :: <tiles:getAsString name="nombre-pantalla"/> ::
    </title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="description" content="<tiles:getAsString name='descripcion-pagina'/>">
	<meta http-equiv="author" content="Mauricio Jovel">
	
	<%--=========================================================================================--%>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jmesa.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar-setup.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar-es.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/utilities.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-impromptu.1.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/forms-v1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/menuDropdown.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-alphanumeric.js"></script>
	<script type="text/javascript">
		var pattern = new Array(2,2,4);
		var patternDui = new Array(8,1);
		var patterNit = new Array(4,6,3,1);
		
		function mask(ob){
			mascara(ob,'-',pattern,true);
		}
		function maskDui(ob){
			mascara(ob,'-',patternDui,true);
		}
		function maskNit(ob){
			mascara(ob,'-',patterNit,true);
		}

		function mascara(d,sep,pat,nums){
							if(d.valant != d.value){ 
							val = d.value 
							largo = val.length 
							val =  val.split(sep) 
							val2 = '' 
							for(r=0;r<val.length;r++){ 
								val2 += val[r] 
							}
							if(nums){ 
								for(z=0;z<val2.length;z++){ 
									if(isNaN(val2.charAt(z))){
										letra = new RegExp(val2.charAt(z),"g") 
										val2 = val2.replace(letra,"") 
									}
								} 
							} 
							val = '' 
							val3 = new Array() 
							for(s=0; s<pat.length; s++){ 
								val3[s] = val2.substring(0,pat[s]) 
								val2 = val2.substr(pat[s]) 
							}
							for(q=0;q<val3.length; q++){ 
								if(q ==0){ val = val3[q]}
								else{
									if(val3[q] != ""){ 
										val += sep + val3[q] 
									} 
								} 
							} 
							d.value = val 
							d.valant = val 
					}
			}
	</script>
	
	<script type="text/javascript">
		function onInvokeAction(id) {
			$('#pageId').val('0');/*se desactiva la validacion cuando el jmesa tiene el control*/
			setExportToLimit(id,'');
			var parameterString = createParameterStringForLimit(id);
			 var url = '${pageContext.request.contextPath}<tiles:getAsString name="accion"/>&ajax=true&' + parameterString;
		    	url+= ('&' + $('#formId').formSerialize());
			$.post(url, function(data) {
			       		$("#lista-datos").html(data)
			});
			$('#pageId').val('3');/*se activa de nuevo la validacion cuando se generan eventos DML*/
		}
		
		function onInvokeExportAction(id) {
			$('#pageId').val('0');
		    var parameterString = createParameterStringForLimit(id);
		   	var  url= ('&' + $('#formId').formSerialize());
		    location.href = '${pageContext.request.contextPath}<tiles:getAsString name="accion"/>&' + parameterString+url;
		}

		function dosDecimales(valor, id){
			var sTrim = new Array();
			sTrim = valor.toString().split('.');
			if(sTrim.length >1 && sTrim.length < 3){
				if(sTrim[1].length >2){
					var decimales = sTrim[1].substring(0,2);
					var temp = sTrim[0] + '.' + decimales;
					$("#" + id).val(temp);
					valor = parseFloat(temp);
				}
			}else{
				if(sTrim.length>2){
					var temp = sTrim[0] + '.' + sTrim[1];
					$("#" + id).val(temp);
					valor = parseFloat(temp);
				}
			}
			return valor;
		}		
		

	</script>
	
  	<tiles:getAsString name="scripts" ignore="true"/>
  	
  	<%--=========================================================================================--%>
  	
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tema001.css" type="text/css"></link>
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jmesa.css" type="text/css"></link>
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jmesa-pdf.css" type="text/css"></link>
  	<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/theme.css" title="Aqua"></link>
	<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/impromptu.css" title="Aqua"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuDropdown.css" type="text/css"></link>
	
 	<tiles:getAsString name="estilos" ignore="true"/>
  	
  </head>
  
  <body>
  	<center>
		<div id="documento2">
			<%-- 
				El sub-encabezado que puede estar arriba o abajo, sirve para poder poner alguna accion especial
				de acceso directo, fecha, o alguna monada del programador.
			 --%>
			<div id="sub-encabezado">
				<div style="float: right;font-size: 9px;">
					<tiles:insert attribute="sub-encabezado"/>
				</div>
			</div>
			<%-- 
				El encabezado es para poner banner, flash o algun tipo de anuncio comercial para la empresa. 
			--%>
			<div id="encabezado">
				<html:img srcKey="img.logo.empresa" altKey="msg.logo.empresa" height="95px" width="800px" border="0px"/>
			</div>
			<%-- 
				Banda que sirve para visualizar el menu de acceso a la aplicacion, este puede ser vertical
				u horizontal, esto cambiando lo que dice en el id de vertical a horizontal. 
			--%>
			<div  align="left">
				<div id="topMenu">
					<tiles:insert attribute="menu"/>
				</div>
				<%-- 
					Aqui se encuentra el contenido propiamente dicho de la pagina.
				--%>
				<DIV id="head" align="center"></DIV>
			
				
				<div id="contenido-v2" class="contbox" align="center">
					<%-- Mostramos el formulario o contenido --%>
					<tiles:insert attribute="contenido"/>
					
					<%-- Mostramos los errores --%>
					<logic:messagesPresent message="false">
						<div style="display: none;" id="errors">
						<html:messages id="error" message="false">
							<span style="color:red;font-size: 12px;"> <bean:write name="error" filter="false"/> </span><br/>
						</html:messages>
						</div>
					</logic:messagesPresent>
					
					<%-- Mostramos los mensajes --%>
					<logic:messagesPresent message="true">
						<div style="clear: both;" id="messages" class="info">
						<html:messages id="mensaje" message="true">
							 <bean:write name="mensaje" filter="false"/> <br/>
						</html:messages>
						</div>
					</logic:messagesPresent>
					
					<script type="text/javascript">
					<!--
						$('#errors').show('slow');
						$('#messages').show('slow');
					-->
					</script>		
				</div>
			<DIV id="footer" align="center"></DIV>				
			
			
			</div>
			
			</div>
			
			
			<%-- 
				En el pie de pagina se pondra como quienes son el nombre de la empresa
				version del aplicativo, y otra informacion de importancia para el usuario.
			--%>
			
			<div id="pie-pagina">
				<tiles:insert attribute="pie-pagina"/>
			</div>
	</center>
	
  </body>
</html:html>
</logic:notPresent>
<logic:present parameter="ajax">
	${_lista}
</logic:present>