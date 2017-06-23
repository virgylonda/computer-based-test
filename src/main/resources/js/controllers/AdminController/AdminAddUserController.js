cbtApp.controller('AdminAddUserController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){


	$scope.addUser = function(){
		// var status = AdminServices.getTester($scope.newtester.username);
		$scope.errorAllert = null;
		if(status = true){
			var formDataTester = {
				"username" : $scope.newuser.username,
				"password" : $scope.newuser.password,
				"name"	   : $scope.newuser.name,
				"email"	   : $scope.newuser.email,
				"roles" :{
					"roleId"	: $scope.newuser.roleId
				}
			}
			var addStatus = AdminServices.addUser(formDataTester);
			if(addStatus = true){
				$state.go("home.userlist");
			}
		}
		else{
			$scope.errorAllert = "Username already used";
		}
	}
}])