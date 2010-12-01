
function ajaxCallRemover(url,objectParam,idDiv){
		$.post(url,parameters,
			function(response){
				var domObject = $(response);
				alert('se obtuvo el dom');
				var idObjectDomServer = domObject.find('div#'+idDiv);
				alert('se econtro el elemento');
				var htmlString = idObjectDomServer.html();
				alert('se econtro la porcion de html');
				$('#'+idDiv).html(htmlString);
				alert('se realizo la insercion');
			}
		);
}

function ajaxCallNormal(url,parameters,idDiv){
		$.post(url,parameters,
			function(data){
				//beginEffect(idDiv);
				$('#'+idDiv).toggle(500,
						function(){
								$('#'+idDiv).html(data);
								$('#'+idDiv).show();
						});
			}
		);
		
	}
	
function ajaxCallSincrono(url,parameters,idDiv) {
	$.ajax({
				url:	    url
			,	type:		'POST'
			,	dataType:	'html'
			,	async:		false
			,   data:       parameters
			,	success:	function(data) {
								$('#'+idDiv).toggle(500,
									function(){
											$('#'+idDiv).html(data);
											$('#'+idDiv).show();
									}
								);
							}
		});
}

function ajaxCallSincrono2(url,parameters,idDiv) {
	$.ajax({
				url:	    url
			,	type:		'POST'
			,	dataType:	'html'
			,	async:		false
			,   data:       parameters
			,	success:	function(data) {
								$('#'+idDiv).toggle(300,
									function(){
											$('#'+idDiv).html(data);
											$('#'+idDiv).show();
									}
								);
							}
		});
}

function ajaxCallNormalAndReplace(url,parameters,idDiv,toReplace){
		$.post(url,parameters,
			function(data){
				beginEffect(idDiv);
				$('#'+idDiv).toggle(1500,
						function(){
								$('#'+toReplace).replaceWith(data);
								$('#'+idDiv).show();
						});
			}
		);
	}
	
function beginEffect(idDiv){
			$('#'+idDiv).html('<label id="loadingStyle">.....Cargando</label>');
			
}
function cleanFields(idform){
		$("#"+ idform+' :input').each(function() {
			if($(this).attr('type')!='button' &&
			   $(this).attr('type')!='submit' &&
			   $(this).attr('type')!='hidden' 
			  ) {
			  	if($(this).attr('class')!='exclude'){
			  		$(this).val('');
				}
			}
		});
}
function cleanFieldsExclude(idform){
	$("#"+ idform).find('input:text').add('input:hidden').not('.exclude').val('');
}
function beginEventMessage(idDiv,msg){
		$('#'+idDiv).html('<label class="loadingStyleClass">.....'+ msg + '</label>');
		$('#'+idDiv).toggle(1500,
						function(){
							$('#'+idDiv).hide();
		});
}
