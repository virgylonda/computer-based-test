/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('LoginController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){

	$scope.login = function(id) {
		var user = {
			"username" : $scope.user.username,
			"password" : $scope.user.password
		};

		var token = 'admin';

		$state.go("/", {token}, {reload: true});
    }
}])