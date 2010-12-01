$(document).ready(function(){
dibujarFormularios();
});


function dibujarFormularios() {
	//Obtenemos todos los Objetos formularios que van a ser formateados
	$('fieldset').each(function(i){
		var msgLegend = $('legend', this).remove().text();
		var msgLegend2 = '<span class="titulo">' + msgLegend + '</span>';
		$(this).prepend(msgLegend2);
	});
	
	//Declaramos las leyendas de obligatoriedad y condicional
	var msgRequeridos = ' *';
	var msgRequeridosKey = $('input.obligatorio:first').next('span').text();
	msgRequeridosKey = msgRequeridos +' ' +msgRequeridosKey.replace(/^\((.+)\)$/,"$1");
	
	var msgCondicional = ' **';
	var msgCondicionalKey = $('input.condicional:first').next('span').text();
	msgCondicionalKey = msgCondicional +' ' +msgCondicionalKey.replace(/^\((.+)\)$/,"$1");
	
	var msgOpcional = ' ***';
	var msgOpcionalKey = $('input.opcional:first').next('span').text();
	msgOpcionalKey = msgOpcional +' ' +msgOpcionalKey.replace(/^\((.+)\)$/,"$1");
	
	var mensajeKey = '';
	if($.trim(msgRequeridosKey)!='*') {
		mensajeKey = msgRequeridosKey;
	}
	
	if($.trim(msgCondicionalKey) != '**' && mensajeKey=='') {
		mensajeKey = msgCondicionalKey;
	} else if($.trim(msgCondicionalKey) != '**'){
		mensajeKey += '<br/>'+msgCondicionalKey;
	}
	
	if($.trim(msgOpcionalKey) != '***' && mensajeKey=='') {
		mensajeKey = msgOpcionalKey;
	} else if($.trim(msgOpcionalKey) != '***'){
		mensajeKey += '<br/>'+msgOpcionalKey;
	}
	
	//Ponemos la leyenda
	if($.trim(mensajeKey)!='') {
		$('<p></p>').addClass('field-keys').append(mensajeKey).insertAfter('fieldset');
	}
	
	//Ponemos los key en vez de la leyenda
	$('form :input').filter('.obligatorio').next('span').text(msgRequeridos).end().parent('td').prev('td').children('label').addClass('obligatorio')
	$('form :input').filter('.condicional').next('span').text(msgCondicional).end().parent('td').prev('td').children('label').addClass('condicional')
	$('form :input').filter('.opcional').next('span').text(msgOpcional).end().parent('td').prev('td').children('label').addClass('opcional')
}