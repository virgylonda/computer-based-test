/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('AdminListUsersController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){

	AdminServices.getAllUser().then(function(res){
		$scope.arrayUsers = res.data;
	})

	$scope.toDelete = function(id) {
		var valid = confirm("Are you sure want to delete user ?");
		if(valid == true){
			
			AdminServices.deleteUser(id).then(function(res){
				$state.go("home.userlist");
				AdminServices.getAllUser().then(function(res){
					$scope.arrayUsers = res.data;
				});
			});
		};
    }
}])