$('#listSession').change(
		function(event) {
			event.preventDefault();
			var url = 'http://localhost:8080/CentreFormation/rest/session/';
			var method = 'GET';
			var formData = $(this).val();
			if (formData) {
				$.ajax({
					method : method,
					url : url + "idSession/" + formData,
					dataType : 'json'
				}).done(
						function(reponse) {
							$('#listStagiaire > tbody').empty();
							$('#listStagiaire').show();
							jQuery.each(reponse, function(i, val) {
								$('#listStagiaire > tbody').append(
										'<tr><td>' + val.id + '</td><td>'
												+ val.nom + '</td><td>'
												+ val.prenom + '</td><td>'
												+ val.email + '</td><td>'
												+ val.dateDeNaissance
												+ '</td><td></tr>');
							});
						}).fail(function(error) {
					console.log(error);
				}).always(function() {
					console.log("fin");
				});
			}

		});