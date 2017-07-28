/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterListUsersController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){

	$scope.alert = $state.params.alert;

	TesterServices.getAllUser().then(function(res){
		$scope.arrayUsers = res.data;
	})

	$scope.toDelete = function(id) {
		var valid = confirm("Are you sure want to delete user ?");
		if(valid == true){
			TesterServices.deleteUser(id).then(function(res){
				var alert = "User has been deleted";
				$state.go("hometester.userlist", {alert});
				TesterServices.getAllUser().then(function(res){
					$scope.arrayUsers = res.data;
				});
			});
		};
    }
}])