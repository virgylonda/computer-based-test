/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('AdminAddTesterController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){


	$scope.addTester = function(){
		// var status = AdminServices.getTester($scope.newtester.username);
		if(status = true){
			var formDataTester = {
				"username" : $scope.newtester.username,
				"password" : $scope.newtester.password,
				"name"	   : $scope.newtester.name,
				"email"	   : $scope.newtester.email,
				"roles"    : {
					"roleId"	: $scope.newtester.roleId
				}
			};

			var roles = {
				"roles"    : {
					"roleId"	: $scope.newtester.roleId
				}
			}

			AdminServices.addTester(formDataTester, roles).then(function(res){
				var alert = "New tester added";
				$state.go("home.testerlist", {alert});
			});
		}
		else{
			window.alert("Username already used");
		}

		AdminServices.getAllTester().then(function(res){
			$scope.arrayTester = res.data;
		})
	}
}])