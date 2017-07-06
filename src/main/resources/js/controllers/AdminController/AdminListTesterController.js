cbtApp.controller('AdminListTesterController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){

	$scope.toDelete = function(id) {

		var confirmResult;
		var valid = confirm("Are you sure want to delete user ?");
		if(valid == true){
			console.log(id);
			console.log("Panggil API delete");

			AdminServices.deleteTester(id).then(function(res){
				$state.go("home.testerlist");
			});
		}
		
			AdminServices.getAllTester().then(function(res){
				$scope.arrayTester = res.data;
			})
    }

}])