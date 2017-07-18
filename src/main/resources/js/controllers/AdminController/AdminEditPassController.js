/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('AdminEditPassController', ['$scope', '$state','AdminServices', function($scope, $state, AdminServices){
	
	var dataUser = $state.params.dataUser;
	var id = $state.params.userId;
	console.log(dataUser);
	
	var confirmResult = confirm("Is this good ?")
	if(confirmResult == true){
		console.log("Panggil API edit pass admin");
		AdminServices.editAdminPass(id, dataUser).then(function(res){
			$state.go("home");
		})
	};

	AdminServices.getAdmin(id).then(function(res){
		$scope.userObject = res.data;
		console.log(res);
	});

}]);