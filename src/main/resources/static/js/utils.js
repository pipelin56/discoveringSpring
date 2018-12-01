
$(document).ready(function(){
    $("#idButtonCleanFieldsForm").click(function(){
    	clearFieldsForm($('#formNewUser'));
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