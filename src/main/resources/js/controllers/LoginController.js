/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('LoginController', ['$scope', '$state', 'AuthService', function($scope, $state, AuthService){

	$scope.login = function() {
		var user = {
			"username" : $scope.user.username,
			"password" : $scope.user.password,
			"name" : '',
			"email" : '',
			"role_id" : '',
			"roles" : {
				"roleId" : ''
			}
		};

		AuthService.login(user).then(function(res){
			var token = res.data.token;
			var decode = jwt_decode(token);
			console.log(decode);
			
		}).catch(function (res){
			console.log(res);
			window.alert("Error: Login Failed");
		});
    }
}])