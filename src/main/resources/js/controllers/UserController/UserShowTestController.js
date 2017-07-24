/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('UserShowTestController', ['$scope', '$state', 'UserServices', function($scope, $state, UserServices){

	var id = $state.params.userId;

	UserServices.getAssignedCategory(id).then(function(res){
		$scope.categoryObjects = res.data;
		console.log($scope.categoryObjects);
	});

	$scope.doTest = function(categoryTest, testId, timeLimit){
		console.log("mulai test !");
		console.log(testId);
		$state.go("homeuser.gettest", {categoryTest, testId, timeLimit})
	}

}])