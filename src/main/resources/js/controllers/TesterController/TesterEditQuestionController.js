/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterEditQuestionController', ['$scope', '$state','TesterServices', function($scope, $state, TesterServices){
	
	var dataQuestion = $state.params.dataQuestion;
	var dataAnswers = $state.params.dataAnswers;

	var idQuestion = dataQuestion.idQuestion;
	var idCategory = dataQuestion.category.idCategory;

	var confirmResult = confirm("Is this good ?")
	if(confirmResult == true){

		for (var i = 0; i < dataAnswers.length; i++) {
			var correctAnswer = false;
			if(dataAnswers[i].correctAnswer === 1){
				correctAnswer = true;
			}

			var formAnswer = {
				"idAnswer" : dataAnswers[i].idAnswer,
				"questionAnswer" : {
					"idQuestion" : idQuestion
				},
				"orderingAnswer" : i+1,
				"answer" : dataAnswers[i].answer,
				"correctAnswer" : correctAnswer
			};
			TesterServices.editAnswers(dataAnswers[i].idAnswer, formAnswer).then(function(res){

			});
		};

		TesterServices.editQuestion(idQuestion, dataQuestion).then(function(res){
			$state.go("hometester.listcategories.listquestion");
		});
	};

	TesterServices.getQuestionList(idCategory).then(function(res){
		$scope.arrayQuestion = res.data;
	})

}]);