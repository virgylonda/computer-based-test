/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('AdminEditTesterController', ['$scope', '$state','AdminServices', function($scope, $state, AdminServices){
	
	$scope.userData = $state.params.dataUser;
	var dataUser = $scope.userData;
	var id = $scope.userData.userId;
	
	var confirmResult = confirm("Is this good ?")
	if(confirmResult == true){
		AdminServices.editTester(id, dataUser).then(function(res){
			var alert = "Tester has been updated";
			$state.go("home.testerlist", {alert});
		})
	}
	else{
		$state.go("home.testerlist");
	}
}]);