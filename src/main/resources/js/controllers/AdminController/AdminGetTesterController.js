/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('AdminGetTesterController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){
	
	var testerId = $state.params.id;
	
	AdminServices.getTester(testerId).then(function(res){
		$scope.testerObject = res.data;
		console.log(res);
	});

	$scope.toConfirm = function() {

        if ($scope.dataUser.name == null) {
			$scope.status = false;
		} else {
			var dataUser = {
				"userId" : $scope.testerObject.userId,
				"username" : $scope.testerObject.username,
				"name" : $scope.dataUser.name,
				"email" : $scope.dataUser.email,
				"roles"    : {
					"roleId"	: $scope.testerObject.roles.roleId
				}
			};

			$state.go("home.testerlist.edittester.confirmtester", {dataUser});	
		}
    }
}])