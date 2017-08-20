/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('UserEditProfileController', ['$scope', '$state','UserServices', function($scope, $state, UserServices){
	
	$scope.userData = $state.params.dataUser;
	var dataUser = $scope.userData;
	var id = $scope.userData.userId;

	var confirmResult = confirm("Is this good ?")
	if(confirmResult == true){
		UserServices.editProfileUser(id, dataUser).then(function(res){
			var alert = "User has been updated";
			window.alert('User has been updated');
			$state.go("homeuser", {alert});
		})
	}
	else{
		$state.go("homeuser");
	}
}]);