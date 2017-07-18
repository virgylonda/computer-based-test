/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('IndexController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){

	//cek token masih ada atau tidak pake API, bila tidak ada : 
	$state.go("loginauthentification");

}])