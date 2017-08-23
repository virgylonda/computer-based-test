/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('AdminController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){
	
	var userId = $state.params.userId;
	$scope.userId = userId;
}])