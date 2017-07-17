/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('IndexController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){

	$state.go("loginauthentification");

}])