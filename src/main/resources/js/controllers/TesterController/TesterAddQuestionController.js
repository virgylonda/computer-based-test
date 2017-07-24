/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterAddQuestionController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){
	
	var idCategory = $state.params.idCategory;
	
	$scope.toAdd = function(id) {
		var orderingQuestion;

		var valid = confirm("Is this good ?");
		if(valid == true){

			TesterServices.getQuestionList(idCategory).then(function(res){
					$scope.arrayQuestion = res.data;
					orderingQuestion = res.data.length + 1;
					var formQuestion = {
						"orderingQuestion" : orderingQuestion,
						"question"	   : $scope.question.question,
						"category" :{
							"idCategory"	: idCategory
						}
					};
					TesterServices.addQuestion(formQuestion).then(function(res){
						var question = res.data;
						var answers = $scope.question.answer;
						for (var i = 0; i < answers.length; i++) {
							var status = false;
							if(answers[i].key == '1'){
								status = true;
							};

							formAnswers = {
								"questionAnswer" : {
									"idQuestion" : question.idQuestion
								},
								"orderingAnswer" : i,
								"answer" : answers.answer,
								"correctAnswer" : status
							};

							TesterServices.addAnswers(formAnswers).then(function(res){	
							});
						};
						
						$state.go("hometester.listcategories.listquestion", {idCategory});
					});
			});
		};
    };
}])