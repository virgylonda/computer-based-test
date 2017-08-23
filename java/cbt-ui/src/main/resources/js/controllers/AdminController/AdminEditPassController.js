/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('AdminEditPassController', ['$scope', '$state','AdminServices', function($scope, $state, AdminServices){
	
	var dataUser = $state.params.user;
	var id = $state.params.userId;
	
	var confirmResult = confirm("Is this good ?")
	if(confirmResult == true){
		AdminServices.editAdminPass(id, dataUser).then(function(res){
			if(res.status == 200){
				$scope.alert = "Update Password Success";
			}
			else{
				$scope.alert = "Update Password Failed";
			}
		})
	};

	AdminServices.getAdmin(id).then(function(res){
		$scope.userObject = res.data;
	});

}]);