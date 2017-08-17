/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterEditUserController', ['$scope', '$state','TesterServices', function($scope, $state, TesterServices){
	
	$scope.userData = $state.params.dataUser;
	var dataUser = $scope.userData;
	var id = $scope.userData.userId;

	TesterServices.editUser(id, dataUser).then(function(res){
		var alert = "User has been updated"
		$state.go("hometester.userlist", {alert});
	})

	TesterServices.getAllUser().then(function(res){
		$scope.arrayTester = res.data;
	});

}]);