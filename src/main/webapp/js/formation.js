$(document)
		.ready(
				function() {
					var $detailFormation = $('#detailFormation'), $listFormation2 = $('#listFormation2'), $loading = $('.loadingImage');
					var $linkButton =  $('#linkButton');
					console.log($linkButton);

					var url = 'http://localhost:8080/CentreFormation/rest/formation/';
					var method = 'GET';
				//	event.preventDefault();
					$.ajax({
								method : method,
								url : url,
								data : '',
								dataType : 'json'
							})
							.done(
									function(reponse) {
										$loading.show();
										$('#listFormation > tbody').empty();
										$('#listFormation').show();
										jQuery
												.each(
														reponse,
														function(i, val) {
															$loading.hide();
															$('#listFormation2')
																	.append(
																			'<div class="col-xs-6 col-lg-4">'
																					+ '<h2>'
																					+ val.intitule
																					+ '</h2>'
																					+ '<p>C\'est une formation en '
																					+ val.intitule
																					+ ' d\'une durée de '
																					+ val.duree
																					+ ' et de niveau '
																					+ val.niveau
																					+ '. Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod.</p>'
																					+ '<p><a class="btn btn-default" id="linkButton" href="#" role="button">+ de détails »</a></p>'
																					+ '</div>');
														});
									}).fail(function(error) {
										$loading.hide();
								console.log(error);
							}).always(function() {
								$loading.hide();
								console.log("fin");
							});

					$linkButton
							.delegate(
									$linkButton,
									'click',
									function handler(e) {
										$loading.show();
										e.currentTarget
										e.preventDefault();
										$el = $(e.currentTarget);
										console.log(e.currentTarget());
										$.ajax({
													method : method,
													url : url+'id/'+$el.attr('href'),
													data : '',
													dataType : 'json'
												})
												.done(
														function(reponse) {
															$loading.hide();
															$('#listFormation2').empty();
															$('#detailFormation').show();
															jQuery.each(
																			reponse,
																			function(i,val) {
																				$('#detailFormation').append(val.niveau);
																			});
														}).fail(
														function(error) {
															$loading.hide();
															console.log(error);
														}).always(function() {
													$loading.hide();
													console.log("fin");
												});

										e.preventDefault();
									});

				});
