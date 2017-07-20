/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('UserGetTestController', ['$scope', '$state', 'UserServices', function($scope, $state, UserServices){

	var detailTest = $state.params.categoryTest;
	var testId = $state.params.testId;

	UserServices.getAllQuestion(detailTest.categories.idCategory).then(function(res){
		var listQuestion = res.data;
		$state.go("homeuser.dotest", {listQuestion});
	})
}])