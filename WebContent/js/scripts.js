$(document).ready(function(){
	/*** Page Containers ***/
	var $nav = $("#lookup_nav");
	var $login = $("#login");
	var $top_search = $("#top_search");
	var $mid_search = $("#mid_search");
	var $search_results = $("#search_results");
	var $add_cat_item = $("#add_cat_item");
	var $add_review = $("#add_review");
	var $error_msg = $("#error_msg");
	var $show_review = $("#show_review");
	/*** Page Containers default view ***/
	$nav.hide();
	$login.show();
	$top_search.hide();
	$search_results.hide();
	$mid_search.hide();
	$error_msg.hide();
	$add_cat_item.hide();
	$add_review.hide();
	$show_review.hide();

	$(".rlitem").click(function(){
		alert("hi");
	});
	
	/*** Handler for login button - start ***/
	$("#login_btn_1").click(function(e){
		e.preventDefault();
		var $login_form = $("#login_form");
		var jsObj = $login_form.serializeObject()
		, ajaxObj = {};
		$("#login_form_response").remove();
		$login_form.append("<div id='login_form_response' style='text-align: center;' class='alert alert-warning' role='alert'></div>");
		console.dir(jsObj);
		
		ajaxObj = {
				type: "POST",
				url: "http://localhost:8080/lookup/rest/user/",
				data: JSON.stringify(jsObj),
				contentType:"application/json",
				error: function(jqXHR, textStatus, errorThrown) {
					//console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
				},
				success: function(data) { 
					//console.log(data);
					if(data[0].HTTP_CODE == 200) {
						if(data[0].MSG=="true"){
							getUserData();
							$login.hide();
							$mid_search.show();
							$nav.show();
						}else{
							$('#login_form_response').text( "Incorrect Email or Password" );
							$("#login_form_response").fadeOut(5000,"swing");
						}
						$("#password").val('');
					}
				},
				complete: function(XMLHttpRequest) {
					//console.log( XMLHttpRequest.getAllResponseHeaders() );
				}, 
				dataType: "json"
		};
		
		$.ajax(ajaxObj);
		
	});
	/*** Handler for login button - end ***/
	
	/*** Function to get User Data - start ***/
	var getUserData = function(){

		$.ajax({
			type: 'GET',
			url: 'http://localhost:8080/lookup/rest/user/loginid/'+$("#loginid").val(),
			async: true,
			contentType: "application/json",
			dataType: 'json',
			success: function(data)
			{
				$.each(data, function(key, value){
					//console.log(value.id);
					//console.log(value.name);
					$("#user_name").empty();
					$("#user_name").text(value.name);
					
				});	
			},
			error: function(e)
			{
			   console.dir(e);
			}
		});
		
		
	};
	/*** Function to get User Data - end ***/
	
	/*** Handler for logout button - start ***/
	$("#log_out_link").click(function(e){
		e.preventDefault();
		$("#user_name").empty();
		$nav.hide();
		$login.show();
		$top_search.hide();
		$search_results.hide();
		$mid_search.hide();
		$error_msg.hide();
		$add_cat_item.hide();
		$add_review.hide();
		$show_review.hide();
		
		$('#login_form_response').hide();
	});
	/*** Handler for logout button - end ***/
	
	/*** Add Category Item - start ***/
	$("#add_cat_item_form").hide();
	$("#add_cat_item_link").fancybox({
		'scrolling'		: 'no',
		'titleShow'		: false
	});
	
	
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/lookup/rest/category/',
		async: true,
		contentType: "application/json",
		dataType: 'json',
		success: function(data)
		{
			var $add_cat_select =  $("#add_cat_select");
			var $add_review_cat_select =  $("#add_review_cat_select");
			
			$add_cat_select.find('option')
						    .remove()
						    .end();
			$add_review_cat_select.find('option')
						    .remove()
						    .end();
			
			$.each(data, function(key, value){
				/*console.log(value.id);
				console.log(value.name);
				console.log(value.userid);*/
				
				$('#add_cat_select')
		         .append($("<option></option>")
		         .attr("id",value.id)
		         .attr("name",value.id)
		         .attr("userid",value.userid)
		         .text(value.name));
				
				$('#add_review_cat_select')
		         .append($("<option></option>")
		         .attr("id",value.id)
		         .attr("name",value.id)
		         .attr("userid",value.userid)
		         .text(value.name));
				
			});	
		},
		error: function(e)
		{
		   console.dir(e);
		}
	});
	
	$("#add_btn_2").click(function(e){
		e.preventDefault();
		var $add_cat_item_form = $("#add_cat_item_form");
		var jsObj = $add_cat_item_form.serializeObject()
		, ajaxObj = {};
		$("#add_cat_item_response").remove();
		$("#add_cat_item_form").append("<div id='add_cat_item_response' style='text-align: center;'></div>");
		console.dir(jsObj);
		
		ajaxObj = {
				type: "POST",
				url: "http://localhost:8080/lookup/rest/categoryitem/",
				data: JSON.stringify(jsObj),
				contentType:"application/json",
				error: function(jqXHR, textStatus, errorThrown) {
					//console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
				},
				success: function(data) { 
					//console.log(data);
					if(data[0].HTTP_CODE == 200) {
						$('#add_cat_item_response').text( data[0].MSG );
						$("#add_cat_item_response").fadeOut(2000,"swing");
						$("#nameVal").val('');
					}
					$("#search_btn_2").trigger("click");
				},
				complete: function(XMLHttpRequest) {
					//console.log( XMLHttpRequest.getAllResponseHeaders() );
				}, 
				dataType: "json"
		};
		
		$.ajax(ajaxObj);
		
		/*** refresh category lists - start ***/
		$.ajax({
			type: 'GET',
			url: 'http://localhost:8080/lookup/rest/category/',
			async: false,
			contentType: "application/json",
			dataType: 'json',
			success: function(data)
			{
				var $add_cat_select =  $("#add_cat_select");
				var $add_review_cat_select =  $("#add_review_cat_select");
				
				$add_cat_select.find('option')
							    .remove()
							    .end();
				$add_review_cat_select.find('option')
							    .remove()
							    .end();
				
				$.each(data, function(key, value){
					/*console.log(value.id);
					console.log(value.name);
					console.log(value.userid);*/
					
					$('#add_cat_select')
			         .append($("<option></option>")
			         .attr("id",value.id)
			         .attr("name",value.id)
			         .attr("userid",value.userid)
			         .text(value.name));
					
					$('#add_review_cat_select')
			         .append($("<option></option>")
			         .attr("id",value.id)
			         .attr("name",value.id)
			         .attr("userid",value.userid)
			         .text(value.name));
					
				});	
			},
			error: function(e)
			{
			   console.dir(e);
			}
		});
		/*** refresh category lists - end ***/
		
		/*** Refresh Category Item List - start ***/
		$.ajax({
			type: 'GET',
			url: 'http://localhost:8080/lookup/rest/categoryitem/',
			async: false,
			contentType: "application/json",
			dataType: 'json',
			success: function(data)
			{
				var $add_review_item_select =  $("#add_review_item_select");
				
				$add_review_item_select.find('option')
							    .remove()
							    .end();
				
				$.each(data, function(key, value){
					console.log(value.id);
					console.log(value.categoryid);
					console.log(value.name);
					console.log(value.userid);
					
					$add_review_item_select
			         .append($("<option></option>")
			         .attr("id",value.id)
			         .attr("categoryid",value.categoryid)
			         .attr("name",value.name)
			         .attr("userid",value.userid)
			         .text(value.name));
					
				});
				
			},
			error: function(e)
			{
			   console.dir(e);
			}
		});
		/*** Refresh Category Item List - end ***/
		
	});
	/*** Add Category Item - end ***/
	
	/*** Add Review  - start ***/
	$("#add_review_form").hide();
	$("#add_review_link").fancybox({
		'scrolling'		: 'no',
		'titleShow'		: false
	});
	
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/lookup/rest/categoryitem/',
		async: true,
		contentType: "application/json",
		dataType: 'json',
		success: function(data)
		{
			var $add_review_item_select =  $("#add_review_item_select");
			
			$add_review_item_select.find('option')
						    .remove()
						    .end();
			
			$.each(data, function(key, value){
				console.log(value.id);
				console.log(value.categoryid);
				console.log(value.name);
				console.log(value.userid);
				
				$add_review_item_select
		         .append($("<option></option>")
		         .attr("id",value.id)
		         .attr("categoryid",value.categoryid)
		         .attr("name",value.name)
		         .attr("userid",value.userid)
		         .text(value.name));
				
			});
			
		},
		error: function(e)
		{
		   console.dir(e);
		}
	});
	
	$("#add_btn_3").click(function(e){
		e.preventDefault();
		var $add_review_form = $("#add_review_form");
		var jsObj = $add_review_form.serializeObject()
		, ajaxObj = {};
		$("#add_review_response").remove();
		$("#add_review_form").append("<div id='add_review_response' style='text-align: center;'></div>");
		console.dir(jsObj);
		
		ajaxObj = {
				type: "POST",
				url: "http://localhost:8080/lookup/rest/review/",
				data: JSON.stringify(jsObj),
				contentType:"application/json",
				error: function(jqXHR, textStatus, errorThrown) {
					//console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
				},
				success: function(data) { 
					//console.log(data);
					if(data[0].HTTP_CODE == 200) {
						$('#add_review_response').text( data[0].MSG );
						$("#add_review_response").fadeOut(2000,"swing");
						$("#add_review_comment").val('');
						parent.$.fancybox.close();
					}
					$("#search_btn_2").trigger("click");
				},
				complete: function(XMLHttpRequest) {
					//console.log( XMLHttpRequest.getAllResponseHeaders() );
				}, 
				dataType: "json"
		};
		
		$.ajax(ajaxObj);
		
		/*** Refresh Review List - start ***/
		$.ajax({
			type: 'GET',
			url: 'http://localhost:8080/lookup/rest/categoryitem/',
			async: false,
			contentType: "application/json",
			dataType: 'json',
			success: function(data)
			{
				var $add_review_item_select =  $("#add_review_item_select");
				
				$add_review_item_select.find('option')
							    .remove()
							    .end();
				
				$.each(data, function(key, value){
					console.log(value.id);
					console.log(value.categoryid);
					console.log(value.name);
					console.log(value.userid);
					
					$add_review_item_select
			         .append($("<option></option>")
			         .attr("id",value.id)
			         .attr("categoryid",value.categoryid)
			         .attr("name",value.name)
			         .attr("userid",value.userid)
			         .text(value.name));
					
				});
				
			},
			error: function(e)
			{
			   console.dir(e);
			}
		});
		/*** Refresh Review List - end ***/
		
	});
	
	/*** Add Review - end ***/
	
	
	/*** Handler for Search Button in mid_search ***/
	$("#search_btn_1").click(function(e){
		if($.trim($('#search_1').val()) == ''){
			$error_msg.show();
			$("#error_msg").fadeOut(6000,"swing");
		   }else{
			   search(e);
		   }
		
	});
	
	/*** Handler for Search Button in top_search ***/
	$("#search_btn_2").click(function(e){
		if($.trim($('#search_2').val()) == ''){
			$(".category_list").empty();
			$(".item_list").empty();
			$(".review_list").empty();
		   }else{
			   search(e);
		   }
		
	});
	

	/*** Common Search Handler function - start ***/
	var search = function(e){
		e.preventDefault();
		$top_search.show();
		$search_results.show();
		if($.trim($('#search_1').val()) != ''){
			$("#search_2").val($("#search_1").val());
			$("#search_1").val('');
		}
		$mid_search.hide();	
		
		var searchText = $("#search_2").val();
		/*** Get the Category Data and populate it ***/
				$.ajax({
					type: 'GET',
					url: 'http://localhost:8080/lookup/rest/category/name/'+searchText,
					async: true,
					contentType: "application/json",
					dataType: 'json',
					success: function(data)
					{
						var $category_list =  $(".category_list");
						$category_list.empty();
						
						$.each(data, function(key, value){
							/*console.log(value.id);
							console.log(value.name);
							console.log(value.userid);*/
							var $li = $('<li/>')
												.appendTo($category_list)
												.attr('id',value.id)
												.attr('name',value.name)
												.attr('userid',value.userid)
												.addClass('clitem');
							var $div = $('<div/>')
									        .addClass('element-custom')
									        .text(value.name)
									        .appendTo($li);
						});
						
						$(".clitem").hide().slideDown(500);
					},
					error: function(e)
					{
					   console.dir(e);
					}
				});
				
		/*** Get the Item Data and populate it ***/	
				$.ajax({
					type: 'GET',
					url: 'http://localhost:8080/lookup/rest/categoryitem/name/'+searchText,
					async: true,
					contentType: "application/json",
					dataType: 'json',
					success: function(data)
					{
						var $item_list =  $(".item_list");
						$item_list.empty();
						
						$.each(data, function(key, value){
							/*console.log(value.id);
							console.log(value.name);
							console.log(value.userid);*/
							var $li = $('<li/>')
												.appendTo($item_list)
												.attr('id',value.id)
												.attr('categoryid',value.categoryid)
												.attr('name',value.name)
												.attr('userid',value.userid)
												.addClass('ilitem');
							var $div = $('<div/>')
									        .addClass('element-custom')
									        .text(value.name)
									        .appendTo($li);
						});
						
						$(".ilitem").hide().slideDown(500);
					},
					error: function(e)
					{
					   console.dir(e);
					}
				});
		/*** Get the Review Data and populate it ***/	
				$.ajax({
					type: 'GET',
					url: 'http://localhost:8080/lookup/rest/review/keywords/'+searchText,
					async: true,
					contentType: "application/json",
					dataType: 'json',
					success: function(data)
					{
						var $review_list =  $(".review_list");
						$review_list.empty();
						
						$.each(data, function(key, value){

							
							var $li = $('<li/>')
												.appendTo($review_list)
												.attr('id',value.id)
												.attr('categoryitemid',value.categoryitemid)
												.attr('rating',value.rating)
												.attr('comment',value.comment)
												.attr('userid',value.userid)
												.attr('createddate',value.createddate)
												.addClass('rlitem');
							
							var $div = $('<div/>')
									        .addClass('element-custom')
									        .text("Rating: "+value.rating +" at "+ value.createddate)
									        .appendTo($li);
							
							$div.append('&nbsp;&nbsp;<a href="#" id="ri_link_'+key+'"><i class="glyphicon glyphicon-eye-open"></i></a>');
							
							$("#ri_link_"+key).click(function(e){
								e.preventDefault();
								var $parent = $(this).parent().parent();
								
								$("#r_rating").val($parent.attr('rating'));
								$('#r_date').val($parent.attr('createddate'));
								$('#r_comment').val($parent.attr('comment'));
								
								/*** Get Item name - start ***/
								$.ajax({
									type: 'GET',
									url: 'http://localhost:8080/lookup/rest/categoryitem/id/'+$parent.attr('categoryitemid'),
									async: false,
									contentType: "application/json",
									dataType: 'json',
									success: function(data)
									{
										
										$.each(data, function(key, value){
											/*console.log(value.id);
											console.log(value.name);
											console.log(value.userid);*/
											$("#r_itemname").val(value.name);
											
											/*** Get category name - start ***/
											$.ajax({
												type: 'GET',
												url: 'http://localhost:8080/lookup/rest/category/id/'+value.categoryid,
												async: false,
												contentType: "application/json",
												dataType: 'json',
												success: function(data)
												{
													
													$.each(data, function(key1, value1){
														/*console.log(value.id);
														console.log(value.name);
														console.log(value.userid);*/
														$("#r_category").val(value1.name);
													});
													
												},
												error: function(e)
												{
												   console.dir(e);
												}
											});
											/*** Get category name - end ***/
										});
										
									},
									error: function(e)
									{
									   console.dir(e);
									}
								});
								/*** Get Item name - end ***/
								
								
								$nav.show();
								$login.hide();
								$mid_search.hide();
								$error_msg.hide();
								$add_cat_item.hide();
								$add_review.hide();
								$show_review.show();
								
								$search_results.slideUp();
								$top_search.slideUp();
								
							});
							
						});
						
						$(".rlitem").hide().slideDown(500);
						
						
						
					},
					error: function(e)
					{
					   console.dir(e);
					}
				});
				
				
				
				
	};
	
	/*** handler function for back button ***/
	$("#back_btn_1").click(function(e){
		e.preventDefault();
		$nav.show();
		$login.hide();
		$top_search.slideDown();
		$search_results.slideDown();
		$mid_search.hide();
		$error_msg.hide();
		$add_cat_item.hide();
		$add_review.hide();
		$show_review.hide();
	});
	
});
