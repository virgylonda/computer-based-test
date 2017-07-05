cbtApp.controller('AdminEditTesterController', ['$scope', '$state','AdminServices', function($scope, $state, AdminServices){
	
	console.log("Panggil API edit");
	// AdminServices.editTester(dataUser).then(function(res){
	// 	$state.go("home.testerlist");
	// })

	AdminServices.getAllTester().then(function(res){
		$scope.arrayTester = res.data;
	})

	$scope.userData = $state.params.dataUser;
	var dataUser = $scope.userData;
	
}]);