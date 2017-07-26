/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterGetQuestionController', ['$scope', '$state','TesterServices', function($scope, $state, TesterServices){
	
	var idQuestion = $state.params.id;
	$scope.categoryId = $state.params.idCategory;

	TesterServices.getQuestion(idQuestion).then(function(res){
		$scope.questionObject = res.data;
		console.log($scope.questionObject);
	});

	TesterServices.getAllAnswersFromQuestion(idQuestion).then(function(res){
		$scope.answerObject = res.data;
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
		var dataAnswers = $scope.answerObject;
		for (var i = 0; i < dataAnswers.length; i++) {
			if(dataAnswers[i].key == '1'){
				$state.go("hometester.listcategories.listquestion.confirmquestion", {dataQuestion, dataAnswers});
			}
		}
		window.alert("No key option selected");
    }

}]);