/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('AdminGetUserController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){
	
	var userId = $state.params.id;
	
	AdminServices.getUser(userId).then(function(res){
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

			$state.go("home.userlist.edituser.confirmuser", {dataUser});	
		}
    }
}])