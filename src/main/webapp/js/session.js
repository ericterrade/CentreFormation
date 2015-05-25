$(document).ready(
		function() {
			event.preventDefault();
			var url = 'http://localhost:8080/CentreFormation/rest/session/';
			var method = 'GET';
			var formData = $(this).serialize();
			$.ajax({
				method : method,
				url : url,
				data : '',
				dataType : 'json'
			}).done(
					function(reponse) {
						$('#listSession').show();
						jQuery.each(reponse, function(i, val) {
							$('#listSession').append(
									'<option value="' + val.id + '">'
											+ val.formation.intitule + ' '
											+ val.dateDeDebut + ' au '
											+ val.dateDeFin + '</option>');
						});
					}).fail(function(error) {
				console.log(error);
			}).always(function() {
				console.log("fin");
			});

		});
