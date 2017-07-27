/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterEditUserController', ['$scope', '$state','TesterServices', function($scope, $state, TesterServices){
	
	$scope.userData = $state.params.dataUser;
	var dataUser = $scope.userData;
	var id = $scope.userData.userId;

	var confirmResult = confirm("Is this good ?")
	if(confirmResult == true){
		TesterServices.editUser(id, dataUser).then(function(res){
			$state.go("hometester.userlist");
		})
	};

	TesterServices.getAllUser().then(function(res){
		$scope.arrayTester = res.data;
	});

}]);