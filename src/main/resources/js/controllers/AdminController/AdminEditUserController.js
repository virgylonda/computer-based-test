cbtApp.controller('AdminEditUserController', ['$scope', '$state','AdminServices', function($scope, $state, AdminServices){
	
	$scope.userData = $state.params.dataUser;
	var dataUser = $scope.userData;
	var id = $scope.userData.userId;
	console.log(dataUser);

	var confirmResult = confirm("Is this good ?")
	if(confirmResult == true){
		console.log("Panggil API edit user");
		AdminServices.editUser(id, dataUser).then(function(res){
			$state.go("home.userlist");
		})
	};

	AdminServices.getAllUser().then(function(res){
		$scope.arrayTester = res.data;
	})

}]);