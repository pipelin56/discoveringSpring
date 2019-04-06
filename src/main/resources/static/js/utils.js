
$(document).ready(function(){
    $("#idButtonCleanFieldsForm").click(function(){
    	clearFieldsForm($('#formNewUser'));
    });
    
	$("#languageSelector").change(function () {
		addParamToUrl('languageSelector','lang');
    });
	
	$("#rowsPerPageSelector").change(function () {
		var params = 'page=0&rowsPerPage=' + $(this).val();
		createUrlWithNewParams(params);
    });
	
	$("a.page-link").click(function() {
		var url = addParamToUrl('rowsPerPageSelector','rowsPerPage', $(this).attr('href'));
		$(this).attr('href', url);
	})
});


/**
 * Add new param to current url keeping its params 
 * @param idItemSelected
 * @param paramName
 */
function addParamToUrl(idItemSelected, paramName, url = window.location.href){
	var selectedOption = $('#'+idItemSelected).val();
	var position = url.indexOf("?");
	var paramsConcats = "";
    if (position != -1){
		var listParams = (url.substring(position + 1, url.length)).split("&");
		var firstParam = true;
		var paramNameExist = false;
		listParams.forEach(function(element){
			if(element.indexOf(paramName) != -1){
				element = paramName + '=' + selectedOption;
				paramNameExist = true;
			}
			if(firstParam){
				firstParam = false;
				paramsConcats += element;
			}
			else{
				paramsConcats += '&' + element;
			}
		});
		if(!paramNameExist){
			paramsConcats += '&' + paramName + '=' + selectedOption ;
		}
	}
	return createUrlWithNewParams(paramsConcats != "" ? paramsConcats :  paramName + '=' + selectedOption)
}

/**
 * TODO
 * @param params
 * @param url
 * @returns
 */
function createUrlWithNewParams(params, url = window.location.href){
	var indexParams = url.indexOf('?');
	if(indexParams > 0){
		url = url.substring(0, indexParams + 1) + params;
	}else{
		url = url + '?' + params;
	}
	window.location.replace(url);	
	return url;
}

/**
 * Clear form's fields
 * @param form
 */
function clearFieldsForm(form){
	if(form != null && form != ""){
		form.find('input').each(function(){
			switch(this.type){
			case 'text': 
			case 'password':
			case 'email':
			case 'number':
				$(this).val('');
				break;
			case 'checkbox':
			case 'radio':
				$(this).checked=false;
				break;
			}
		});
	}
}

function closeModal(modal){
	$('#'+modal).modal('toggle');
}

function openModal(modal){
	$('#'+modal).modal('show');
}

function openCustomModal(modal, title, description){	
	$('#idModalInfoTitle').text(title);
	$('#idModalInfoDescription').text(description);
	$('#'+modal).modal('show');
}