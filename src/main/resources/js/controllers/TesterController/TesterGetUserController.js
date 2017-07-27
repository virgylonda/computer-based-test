/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterGetUserController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){
	
	var userId = $state.params.id;
	
	TesterServices.getUser(userId).then(function(res){
		$scope.testerObject = res.data;
	});

	$scope.toConfirm = function() {

        if ($scope.testerObject.name == null) {
			$scope.status = false;
		} else {
			var dataUser = {
				"userId" : $scope.testerObject.userId,
				"username" : $scope.testerObject.username,
				"name" : $scope.testerObject.name,
				"email" : $scope.testerObject.email,
				"roles"    : {
					"roleId"	: $scope.testerObject.roles.roleId
				}
			};

			$state.go("hometester.userlist.edituser.confirmuser", {dataUser});	
		}
    }
}])