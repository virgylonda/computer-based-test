cbtApp.controller('AdminEditUserController', ['$scope', '$state','AdminServices', function($scope, $state, AdminServices){
	
	console.log("Panggil API edit");
	// AdminServices.editUser(dataUser).then(function(res){
	// 	$state.go("home.userlist");
	// })

	AdminServices.getAllUser().then(function(res){
		$scope.arrayTester = res.data;
	})

	$scope.userData = $state.params.dataUser;
	var dataUser = $scope.userData;

}]);