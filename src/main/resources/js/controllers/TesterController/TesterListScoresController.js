/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterListScoresController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){

	TesterServices.getListUsersScore().then(function(res){
		$scope.arrayUser = res.data;
		var arrayUsersScore = [];
		for (var i = 0 ; i < $scope.arrayUser.length; i++) {
				if ($scope.arrayUser[i].ended != null){
					arrayUsersScore.push($scope.arrayUser[i]);
				}
		}
		console.log(arrayUsersScore);
		$scope.usersScore = arrayUsersScore;
	})

}])