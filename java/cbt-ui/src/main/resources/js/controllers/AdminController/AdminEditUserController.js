/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('AdminEditUserController', ['$scope', '$state','AdminServices', function($scope, $state, AdminServices){
	
	$scope.userData = $state.params.dataUser;
	var dataUser = $scope.userData;
	var id = $scope.userData.userId;

	var confirmResult = confirm("Is this good ?")
	if(confirmResult == true){
		AdminServices.editUser(id, dataUser).then(function(res){
			var alert = "User has been updated";
			$state.go("home.userlist", {alert});
		})
	}
	else{
		$state.go("home.userlist");
	}
}]);