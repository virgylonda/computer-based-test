/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterAddUserController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){


	$scope.addUser = function(){
		// var status = AdminServices.getTester($scope.newtester.username);
		$scope.errorAllert = null;
		if(status = true){
			var formDataUser = {
				"username" : $scope.newuser.username,
				"password" : $scope.newuser.password,
				"name"	   : $scope.newuser.name,
				"email"	   : $scope.newuser.email,
				"roles" :{
					"roleId"	: $scope.newuser.roleId
				}
			};

			var roles = {
				"roles" :{
					"roleId"	: $scope.newuser.roleId
				}
			}

			TesterServices.addUser(formDataUser, roles).then(function(res){
				$state.go("hometester.userlist");
				$scope.statusAllert = "New User Added";
			});
		}
		else{
			$scope.errorAllert = "Username already used";
		}

		TesterServices.getAllUser().then(function(res){
			$scope.arrayUsers = res.data;
		})
	}
}])