cbtApp.controller('AdminAddTesterController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){


	$scope.addTester = function(){
		var status = AdminServices.getTester($scope.newtester.uername);
		if(status = true){
			var formDataTester = {
				"username" : $scope.newtester.username,
				"password" : $scope.newtester.password,
				"name"	   : $scope.newtester.name,
				"email"	   : $scope.newtester.email
			}
			var addStatus = AdminServices.addTester(formDataTester);
			if(addStatus = true){
				$state.go("views/datatester.html");
			}
		}
		else{
			$scope.errorAlert = "Username already used";
		}
	}
}])