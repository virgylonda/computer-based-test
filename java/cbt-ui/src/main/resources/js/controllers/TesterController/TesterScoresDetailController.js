/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterScoresDetailController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){

	var userId = $state.params.userId;

	TesterServices.getUserDetailScore(userId).then(function(res){
		$scope.detailObject = res.data;
	})

}])