/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('UserShowTestController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){

	// TesterServices.getAllUsers().then(function(res){
	// 	$scope.arrayUsers = res.data;
	// })
	console.log("cobain aja");
	var id = $state.params.userId;
	console.log(id);
}])