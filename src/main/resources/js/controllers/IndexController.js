/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('IndexController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){

	var token = $state.params.token;
	$scope.tokenAdmin = token;
	console.log($scope.tokenAdmin);

	if(token == null){
		$state.go("loginauthentification");	
	}
	else{
		$state.go("home");
	}

}])