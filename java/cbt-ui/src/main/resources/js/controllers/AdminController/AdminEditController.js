/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('AdminEditController', ['$scope', '$state','AdminServices', function($scope, $state, AdminServices){
	
	var dataUser = $state.params.dataUser;
	var id = $state.params.userId;
	
	var confirmResult = confirm("Is this good ?")
	if(confirmResult == true){
		AdminServices.editAdmin(id, dataUser).then(function(res){
			$state.go("home");
		})
	};

	AdminServices.getAdmin(id).then(function(res){
		$scope.userObject = res.data;
	});

}]);