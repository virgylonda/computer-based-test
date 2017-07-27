/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('AdminListTesterController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){

	AdminServices.getAllTester().then(function(res){
		$scope.arrayTester = res.data;
	})

	$scope.toDelete = function(id) {
		var valid = confirm("Are you sure want to delete user ?");
		if(valid == true){

			AdminServices.deleteTester(id).then(function(res){
				$state.go("home.testerlist");
				AdminServices.getAllTester().then(function(res){
					$scope.arrayTester = res.data;
				})
			});
		}
    }

}])