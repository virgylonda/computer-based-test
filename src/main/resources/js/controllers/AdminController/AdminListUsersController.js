cbtApp.controller('AdminListUsersController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){

	AdminServices.getAllUser().then(function(res){
		$scope.arrayUsers = res.data;
	})

	$scope.toDelete = function(id) {
		var valid = confirm("Are you sure want to delete user ?");
		if(valid == true){
			console.log(id);
			console.log("Panggil API delete");
			
			AdminServices.deleteUser(id).then(function(res){
				console.log("berhasil");
				$state.go("home.userlist");
				AdminServices.getAllUser().then(function(res){
					$scope.arrayUsers = res.data;
				});
			});
		};
    }
}])