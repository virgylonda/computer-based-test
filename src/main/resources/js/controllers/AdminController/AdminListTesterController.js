/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('AdminListTesterController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){

	$scope.alert = $state.params.alert;
	
	AdminServices.getAllTester().then(function(res){
		$scope.arrayTester = res.data;
	})

	$scope.toDelete = function(id) {
		var valid = confirm("Are you sure want to delete user ?");
		if(valid == true){

			AdminServices.deleteTester(id).then(function(res){
				var alert = "Tester has been deleted";
				$state.go("home.testerlist", {alert});
				AdminServices.getAllTester().then(function(res){
					$scope.arrayTester = res.data;
				})
			});
		}
		else{
			$state.go("home.testerlist");
		}
    }

}])