cbtApp.controller('AdminListTesterController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){

	AdminServices.getAllTester().then(function(res){
		$scope.arrayTester = res.data;
	})

	$scope.toDelete = function(id) {
		var valid = confirm("Are you sure want to delete user ?");
		if(valid == true){
			console.log(id);
			console.log("Panggil API delete");

			AdminServices.deleteTester(id).then(function(res){
				console.log("success");
				$state.go("home.testerlist");
				AdminServices.getAllTester().then(function(res){
					$scope.arrayTester = res.data;
				})
			});
		}
    }

}])