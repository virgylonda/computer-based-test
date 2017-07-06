cbtApp.controller('AdminEditTesterController', ['$scope', '$state','AdminServices', function($scope, $state, AdminServices){
	
	$scope.userData = $state.params.dataUser;
	var dataUser = $scope.userData;
	var id = $scope.userData.userId;
	console.log(dataUser);
	
	var confirmResult = confirm("Is this good ?")
	if(confirmResult == true){
		console.log("Panggil API edit tester");
		AdminServices.editTester(id, dataUser).then(function(res){
			$state.go("home.testerlist");
		})
	};

	AdminServices.getAllTester().then(function(res){
		$scope.arrayTester = res.data;
	})

}]);