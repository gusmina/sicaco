<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jmesa.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu-v1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/forms-v1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/utilities.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar-setup.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar-es.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-impromptu.1.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-numeric.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-alphanumeric.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tooltip.pack.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/facebox/facebox.css" type="text/css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/facebox/facebox.js"></script>
	
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
	
	function round_number(number, dec_places){
	  //(c) Copyright 2008, Russell Walker, Netshine Software Limited. www.netshinesoftware.com
	  var new_number = '';
	    var i = 0; //Just used in loops
	    number = number.toString(); //We need to operate on and return a string, not a number
	    dec_places = dec_places * 1; //We need an integer
	    dec_point_pos = number.lastIndexOf(".");
	    //If there is nothing before the decimal point, prefix with a zero
	    if (dec_point_pos == 0){
	        number = "0" + number;
	        dec_point_pos = 1;
	    }
	    //Has an integer been passed in?
	    if (dec_point_pos == -1 || dec_point_pos == number.length - 1){
	        if (dec_places > 0){
	            new_number = number + ".";
	            for(i=0; i<dec_places; i++){
	                new_number += "0";
	            }
	            return new_number;
	        }
	        else{
	            return number;
	        }
	    }
	    //Do we already have the right number of decimal places?
	    var existing_places = (number.length - 1) - dec_point_pos;
	    if (existing_places == dec_places){
	        return number; //If so, just return the input value
	    }
	    //Do we already have less than the number of decimal places we want?
	    if (existing_places < dec_places){
	        //If so, pad out with zeros
	        new_number = number;
	        for(i=existing_places; i<dec_places; i++){
	            new_number += "0";
	        }
	        return new_number;
	    }
	    //Work out whether to round up or not
	    var end_pos = (dec_point_pos * 1) + dec_places;
	    var round_up = false; //Whether or not to round up (add 1 to) the next digit along
	    if ((number.charAt(end_pos + 1) * 1) > 4){
	        round_up = true;
	    }
	    //Record each digit in an array for easier manipulation
	    var digit_array = new Array();
	    for(i=0; i<=end_pos; i++){
	        digit_array[i] = number.charAt(i);
	    }
	    //Round up the last digit if required, and continue until no more 9's are found
	    var stop = false;
	    for(i=digit_array.length; i>dec_point_pos; i--){
	        if (digit_array[i] == "."){
	            stop = true;
	            continue;
	        }
	        if (round_up){
	            digit_array[i]++;
	            if (digit_array[i] < 10){
	                break;
	            }
	        }
	        else{
	            break;
	        }
	        if (stop){
	            break;
	        }
	    }
	
	    for (i=0; i<=end_pos; i++){
	        if (digit_array[i] == "." || digit_array[i] < 10){
	            new_number += digit_array[i];
	        }else{
	        	new_number += "0";
	        }
	    }
	    if (dec_places == 0){
	        new_number = new_number.replace(".", "");
	    }
	    return new_number;
	}
	
	//Funcion para no permitir mas de 2 decimales en un textbox
	//Creada por: Paco
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
	
	function roundNumber(number,decimal_points)
	{ 
	    if(isNaN(parseFloat(number))) return "";
	    if(!decimal_points) return Math.round(number);
	    if(number == 0) {
	        var decimals = "";
	        for(var i=0;i<decimal_points;i++) decimals += "0";
	        return "0."+decimals;
	    }
	    var exponent = Math.pow(10,decimal_points);
	    var num = Math.round((number * exponent))/exponent;
	    num = num.toString();
	    var postindex = num.lastIndexOf(".") +1;
	    if(postindex == 0){
	        num+=".";
	        postindex=num.length;
	    }
	   for(var i=num.length - postindex;i<decimal_points; i++) num += "0";
	    return num.slice(0,-1*decimal_points) + num.slice(-1*decimal_points);
	}	
	
	</script>
	
	
  	<tiles:getAsString name="scripts" ignore="true"/>
  	
 	<%--=========================================================================================--%>
  	
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tema001.css" type="text/css"></link>
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu-jq001.css" type="text/css"></link>
  	<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/theme.css" title="Aqua"></link>
  	<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/impromptu.css" title="Aqua"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jmesa.css" type="text/css"></link>
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jmesa-pdf.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/impromptu.css" title="Aqua"></link>
	<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/tooltip.css"></link>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/wizardStyle.css"></link>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/scrollPane.css" />
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/wizard.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/asmselect.css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.asmselect.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/masks.js"></script>
	<script type="text/javascript">
		var widthNow = 0;
		function redimensionar() {
			var widthTotal = $('body').width()
			var widthTrabajo = widthTotal
			var widthMenu = widthTrabajo*0.2 
			var widthContenido = widthTrabajo*0.8-30
			
			$('#documento').width(widthTrabajo)
			$('#menu-vertical').width(widthMenu)
			$('#contenido-v').width(widthContenido)
			
			widthNow = $('#contenido-v').width() 
			
			
		}
		
		var showPanel = false
		
		function slideDownPanel() {
			$('#contenido-v fieldset').wrap('<div id="_panelout"></div>')
			$('#contenido-v fieldset').before('<div id="slideDownPanel" class="normal">Show</div>')
			$('#slideDownPanel').height('10')
			$('#slideDownPanel').css('background-color','red')
			$('#slideDownPanel').click(function() {
				var className = $(this).attr('class')
				if(className=='normal') {
					$(this).removeClass('normal')
					$(this).addClass('dock')
					$(this).next().slideUp('slow')
					$('#_panelout').mouseover(function() {
						alert($(this).attr('id'))
						$('#slideDownPanel').next().slideDown('slow')
					})
					$('#_panelout').mouseout(function() {
						alert($(this).attr('id'))
						$('#slideDownPanel').next().slideUp('slow')
					})
				}else {
					$(this).removeClass('dock')
					$(this).addClass('normal')
					$(this).next().slideDown('slow')
					$('#_panelout').unbind('mouseover')
					$('#_panelout').unbind('mouseout')
				}
			})
		}
		
		$(document).ready(function() {
			redimensionar()
			$(window).resize(redimensionar)
			
			//Centramos el contenido
			//$('#contenido-v fieldset').wrapInner('<center></center>')
			$('#contenido-v fieldset').css('text-align','left')
			
			//Alineamos los label a la derecha
			$('label').parent('td').attr('align','right')
			
			//slideDownPanel()
		})
	</script>
  	<tiles:getAsString name="estilos" ignore="true"/>
  	
  </head>
  
  <body>
  	<center>
		<div id="documento">
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
				<div id="menu-vertical">
					<tiles:insert attribute="menu"/>
				</div>
				<%-- 
					Aqui se encuentra el contenido propiamente dicho de la pagina.
				--%>
				<div id="contenido-v" >
					<fieldset>
						<dl>
								<dt><label style="font-size: 1.8em;font-family: Arial;font-weight: bold; 
										     		color: black;">
									<tiles:getAsString name="nombre-pantalla"/>
									</label>
								</dt>
								<dd >
									<logic:present name="persona">
										     <label style="font-size: 1.0em;font-family: Arial;font-weight: bold; 
										     		color:  black;border-width : 2px;border-bottom-style: dotted;
										     		position: relative; left: 30em;"
										     		>
										     	${persona.perPrimerNombre} ${persona.perSegundoNombre} ${persona.perPrimerApellido} ${persona.perSegundoApellido}
										     </label>
									</logic:present>
								</dd>
							</dl>
						<tiles:insert attribute="contenido" />
					</fieldset>
					<%-- Mostramos los errores 
					<logic:messagesPresent message="false">
							<fieldset style="border-color: red; color: red;font-size: 10px">
								<legend><bean:message key="errors.finders" bundle="errors"/></legend>	
									<UL>
										<html:messages id="error" message="true">
											<LI><bean:write  name="error"/></LI>
										</html:messages>
									</UL>
							</fieldset>
					</logic:messagesPresent> --%>
					
					<%-- Mostramos los mensajes --%>
					<logic:messagesPresent message="true">
						<fieldset style="border-color: grey; color: green;font-size: 12px">	
									<UL>
										<html:messages id="mensaje" message="true">
											<LI><bean:write  name="mensaje"/></LI>
										</html:messages>
									</UL>
							</fieldset>
					</logic:messagesPresent>
				</div>
			</div>
			<%-- 
				En el pie de pagina se pondra como quienes son el nombre de la empresa
				version del aplicativo, y otra informacion de importancia para el usuario.
			--%>
			<div id="pie-pagina">
				<tiles:insert attribute="pie-pagina"/>
			</div>
		</div>
	</center>
  </body>
</html:html>
