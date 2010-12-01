<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
   <head>
    <html:base />
    	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/jmesa.css" type="text/css"></link>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/jmesa-pdf.css" type="text/css"></link>
		<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/impromptu.css"></link>
		<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/tooltip.css"></link>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/jmesa.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/jquery-1.2.1.pack.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/calendar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar-setup.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar-es.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/date-mask.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/utilities.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-impromptu.1.4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.blockUI.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tooltip.pack.js"></script>
		<script type="text/javascript">
			function onInvokeAction(id) {
				setExportToLimit(id, '');
				createHiddenInputFieldsForLimitAndSubmit(id);
			}
			function onInvokeExportAction(id) {
				alert(id)
				var parameterString = createParameterStringForLimit(id);
				alert(parameterString)
				location.href = '${pageContext.request.contextPath}<tiles:getAsString name="accion" />&' + parameterString;
			}
		</script>
		<script type="text/javascript">
		var pattern = new Array(2,2,4);
		var patterNit = new Array(4,6,3,1);
		
		function mask(ob){
			mascara(ob,'-',pattern,true);
		}
		function maskDui(ob){
			mascara(ob,'',patternDui,true);
		}
		function maskNit(ob){
			alert('Entro al nit')
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
						$('#slideDownPanel').next().slideDown('slow')
					})
					$('#_panelout').mouseout(function() {
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
  </head>
  <body>
    
    <table align="center">
    	<tr>
    		<td>
    			<tiles:insert attribute="dml"/>
    		</td>
    	</tr>
    	
    </table>
  
  </body>
</html:html>
