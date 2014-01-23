$( document ).ready(function() {
	$("button#btn-create-form").click(function(){
		$.ajax({
			url: 'findAllRoomsJson.action',
			type: 'get',
			dataType: 'json',
			data: {},
			success: self.listRoomsSuccess,
			error: self.listRoomsError
		});
	});
});