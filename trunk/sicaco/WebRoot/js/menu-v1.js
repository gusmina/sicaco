$(document).ready(function(){
	//Escondemos las opciones
	$('ul > li > a').each(function(i) {
		//Ocultamos las opciones
		$(this).next().hide();
		$(this).attr('id',(i+1));
		//Agregamos estilo a todos los link del menu
		//$(this).addClass('menu-link');
		
		if($(this).attr('href')=='javascript:void(0)') {
			$(this).addClass('menu-link-padre');
			//Mostramos las opciones cuando se hace click sobre el padre
			$(this).click(function(){
				//Si las opciones estan ocultas las mostramos si no las ocultamos
				$(this).next().slideToggle('fast');
				
				if($(this).next().is(':visible')) {
					var co=$.cookie('menuAbierto')
					//$.cookie('menuAbierto',null);
					co=co?co:''
					if(co=='')
						co=$(this).attr('id')
					else
						co=co+','+$(this).attr('id')
					$.cookie('menuAbierto',co, { expires: 7, path: '/cetia' });
					//alert($.cookie('menuAbierto'))
				}
				
				return false;
			}).mouseover(function() {
				//Agregamos un poco de estilo
				$(this).addClass('menu-element-over')
				$(this).parent('li').addClass('menu-element-over')
			}).mouseout(function() {
				$(this).removeClass('menu-element-over')
				$(this).parent('li').removeClass('menu-element-over')
			})
		}else {
			$(this).addClass('menu-link');
			//Agregamos un poco de estilo a la opcion
			$(this).mouseover(function() {
				$(this).addClass('menu-option-over')
				$(this).parent('li').addClass('menu-element-over')
			}).mouseout(function() {
				$(this).removeClass('menu-option-over')
				$(this).parent('li').removeClass('menu-element-over')
			}).click(function() {
				var link = $(this).attr('href')
				if(link.indexOf('?')>0) {
					link = link + '&ajaxLoad=false';
				}else {
					link = link + '?ajaxLoad=false';
				}
				valueMenuAbierto = $.cookie('menuAbierto');
				if(!valueMenuAbierto||valueMenuAbierto=='') {
					$.cookie('menuAbierto',menusAnteriores, { expires: 7, path: '/cetia' });
				}

				$(this).unbind('click');
				$(this).bind('click',function(){
					return false;
				});
				//Para convertir el menu en ajax eliminar los dos comentarios de abajo
				//linkAjaxCall(link)
				//return false;
			})
		}
	});
	abrirUltimoMenu();
	
});

function openAllMenus() {
	//$('.menu-link-padre').next().slideToggle('slow');
	$('.menu-link-padre').next().show('slow');
}

function closeAllMenus() {
	$('.menu-link-padre').next().hide('slow');
}
var menusAnteriores;
function abrirUltimoMenu() {
	//Abrimos el ultimo menu que fue seleccionado
	valueMenuAbierto = $.cookie('menuAbierto');
	menusAnteriores=valueMenuAbierto
	//alert('Menu Abierto: '+valueMenuAbierto)
	if(valueMenuAbierto&&valueMenuAbierto!='') {
		var splitMenus = valueMenuAbierto.split(',')
		for(i=0;i<splitMenus.length;i=i+1) {
			seleccionMenu = '#'+splitMenus[i];
			/*$parent=$(seleccionMenu).parent().parent()
			$parent.show()*/
			$(seleccionMenu).next().show();
		}
		
		//Eliminamos la seleccion
		$.cookie('menuAbierto',null,{ expires: -1, path: '/cetia' })
	}
}
