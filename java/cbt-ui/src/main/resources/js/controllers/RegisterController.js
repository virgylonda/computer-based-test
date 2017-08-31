/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('RegisterController', ['$scope', '$state', 'RegisterService', function($scope, $state, RegisterService){


	$scope.registerTester = function(){
		// var status = AdminServices.getTester($scope.newtester.username);
		if(status = true){
			var formDataTester = {
				"username" : $scope.newtester.username,
				"password" : $scope.newtester.password,
				"name"	   : $scope.newtester.name,
				"email"	   : $scope.newtester.email,
				"organization" : {
					"name" : $scope.newtester.orgName,
					"orgType" : $scope.newtester.orgType
				},
				"roles"    : {
					"roleId"	: $scope.newtester.roleId
				}
			};

			var roles = {
				"roles"    : {
					"roleId"	: $scope.newtester.roleId
				}
			}

			RegisterService.addTester(formDataTester, roles).then(function(res){
				var alert = "New tester added, please login";
				$state.go("login", {alert});
			});
		}
		else{
			window.alert("Username already used");
		}

	}
}])