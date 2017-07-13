/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterListUserController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){

	TesterServices.getAllUsers().then(function(res){
		$scope.arrayUsers = res.data;
	})

}])