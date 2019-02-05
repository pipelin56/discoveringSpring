
$(document).ready(function(){
    $("#idButtonCleanFieldsForm").click(function(){
    	clearFieldsForm($('#formNewUser'));
    });
    
	$("#languageSelector").change(function () {
		        var selectedOption = $('#languageSelector').val();
				var url = window.location.href;
		        if (selectedOption != ''){
					var pos = url.search("lang=");
					if(pos > 0){
						url = url.substring(0,pos+5) + selectedOption + url.substring(pos+7,url.length);						
						window.location.replace(url);
					}
					else
			           window.location.replace(url+'?lang=' + selectedOption);
		        }
    });
});


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