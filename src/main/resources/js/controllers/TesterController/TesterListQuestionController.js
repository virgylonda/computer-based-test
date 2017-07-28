/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterListQuestionController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){

	var idCategory = $state.params.idCategory;
	TesterServices.getQuestionList(idCategory).then(function(res){
		$scope.arrayQuestion = res.data;
	})

	$scope.idCategory = $state.params.idCategory;

	$scope.toDelete = function(id) {
		var valid = confirm("Are you sure want to delete question ?");
		if(valid == true){
			TesterServices.deleteQuestion(id).then(function(res){
				$state.go("hometester.listcategories.listquestion");
				TesterServices.getQuestionList(idCategory).then(function(res){
					$scope.arrayQuestion = res.data;
				});
			});
		};
    }
}])