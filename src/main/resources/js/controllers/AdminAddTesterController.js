cbtApp.controller('AdminAddTesterController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){


	$scope.addTester = function(){
		// var status = AdminServices.getTester($scope.newtester.username);
		$scope.errorAllert = null;
		if(status = true){
			var formDataTester = {
				"username" : $scope.newtester.username,
				"password" : $scope.newtester.password,
				"name"	   : $scope.newtester.name,
				"email"	   : $scope.newtester.email,
				"roles" :{
					"roleId"	: $scope.newtester.roleId
				}
			}
			var addStatus = AdminServices.addTester(formDataTester);
			if(addStatus = true){
				$state.go("home.testerlist");
			}
		}
		else{
			$scope.errorAllert = "Username already used";
		}
	}
}])