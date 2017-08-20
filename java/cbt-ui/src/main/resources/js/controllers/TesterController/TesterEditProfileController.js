/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterEditProfileController', ['$scope', '$state','TesterServices', function($scope, $state, TesterServices){
	
	$scope.userData = $state.params.dataUser;
	var dataUser = $scope.userData;
	var id = $scope.userData.userId;

	var confirmResult = confirm("Is this good ?")
	if(confirmResult == true){
		TesterServices.editTester(id, dataUser).then(function(res){
			var alert = "User has been updated";
			window.alert('User has been updated');
			$state.go("hometester", {alert});
		})
	}
	else{
		$state.go("hometester");
	}
}]);