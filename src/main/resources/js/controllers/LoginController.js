/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('LoginController', ['$scope', '$state', 'AuthService', function($scope, $state, AuthService){

	$scope.login = function() {
		var user = {
			"username" : $scope.user.username,
			"password" : $scope.user.password
		};
		let header = new Headers({'Content-Type': 'application/json'});
		header.append('Authorization','Bearer ');
		let options = new RequestOptions({headers: headers});
		console.log(user);

		AuthService.login(user, options).then(function(res){
			var token = res;
			var decoded = jwt_decode(token);
			console.log(decoded);
		});
    }
}])