/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('LoginController', ['$scope', '$state', 'AuthService', '$http', function($scope, $state, AuthService, $http){

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
            $http.defaults.headers.common.Authorization = 'Bearer ' + token;
			console.log(decode);
			$scope.checkRoles(decode);
		}).catch(function (res){
			console.log(res);
			window.alert("Error: Login Failed");
		});
    }

    $scope.checkRoles = function(decode) {
    	var userId = decode.userId;
        if(decode.roleId == '1'){
        	$state.go("home", {userId});
        }
        else if(decode.roleId == '2'){
        	$state.go("hometester", {userId});
        }
        else if(decode.roleId == '3'){
        	$state.go("homeuser", {userId});
        }
    }

    $scope.logout = function() {
        $scope.userName = '';
        $scope.token = null;
        $http.defaults.headers.common.Authorization = '';
        $state.go("/");
    }

    $scope.loggedIn = function() {
        return $scope.token !== null;
    }
}])