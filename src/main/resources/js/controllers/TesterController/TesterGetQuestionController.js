/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterGetQuestionController', ['$scope', '$state','TesterServices', function($scope, $state, TesterServices){
	
	var idQuestion = $state.params.id;

	TesterServices.getQuestion(idQuestion).then(function(res){
		$scope.questionObject = res.data;
		console.log(res);
	});

	TesterServices.getAllAnswersFromQuestion(idQuestion).then(function(res){
		$scope.answerObject = res.data;
		console.log(res);
	});

	$scope.toUpdate = function() {

		var dataQuestion = {
			"idQuestion" : $scope.questionObject.idQuestion,
			"orderingQuestion" : $scope.questionObject.orderingQuestion,
			"question" : $scope.questionObject.question,
			"category" :{
				"idCategory"	: $scope.questionObject.category.idCategory
			}
		};
		var dataAnswers = {
			
		};
		console.log(dataQuestion);
		$state.go("hometester.listcategories.listquestion.confirmquestion", {dataQuestion});	
    }

}]);