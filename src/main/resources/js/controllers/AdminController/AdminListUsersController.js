/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('AdminListUsersController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){

	$scope.alert = $state.params.alert;

	AdminServices.getAllUser().then(function(res){
		$scope.arrayUsers = res.data;
	})

	$scope.toDelete = function(id) {
		var valid = confirm("Are you sure want to delete user ?");
		if(valid == true){
			
			AdminServices.deleteUser(id).then(function(res){
				var alert = "User has been deleted";
				$state.go("home.userlist", {alert});
				AdminServices.getAllUser().then(function(res){
					$scope.arrayUsers = res.data;
				});
			});
		}
		else{
			$state.go("home.userlist");
		}
    }
}])