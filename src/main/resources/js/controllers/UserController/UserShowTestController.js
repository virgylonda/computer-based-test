/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('UserShowTestController', ['$scope', '$state', 'UserServices', function($scope, $state, UserServices){

	var id = $state.params.userId;
	$scope.alert = $state.params.alert;

	UserServices.getAssignedCategory(id).then(function(res){
		$scope.categoryObjects = res.data;
	});

	$scope.doTest = function(categoryTest, testId, timeLimit){
		$state.go("homeuser.gettest", {categoryTest, testId, timeLimit})
	}

}])