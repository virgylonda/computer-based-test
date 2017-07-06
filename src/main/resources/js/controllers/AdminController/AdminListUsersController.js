cbtApp.controller('AdminListUsersController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){

	$scope.toDelete = function(id) {

		var confirmResult;
		var valid = confirm("Are you sure want to delete user ?");
		if(valid == true){
			console.log(id);
			console.log("Panggil API delete");
			
			AdminServices.deleteUser(id).then(function(res){
				$state.go("home.userlist");
			});
		}

		AdminServices.getAllUser().then(function(res){
			$scope.arrayUsers = res.data;
		})
    }
}])