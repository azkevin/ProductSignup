$(document).ready(function(){
	function getFormData($form){
	    var unindexed_array = $form.serializeArray();
	    var indexed_array = {};

	    $.map(unindexed_array, function(n, i){
	        indexed_array[n['name']] = n['value'];
	    });

	    return indexed_array;
	}
	
	$("#userForm").submit(function(e) {
		e.preventDefault();
		var url = "app/addUser";
		var $form = $("#userForm");
		var formData = getFormData($form);
		$.ajax({
			type : "POST",
			url : url,
			crossDomain: true,
			dataType: 'json',
			data : formData,
			success: function(data){
			    alert('successful');
			  },
		});

	});
	
});