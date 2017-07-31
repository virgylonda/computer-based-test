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
						var counter = 0;
						while(answers[counter] != null){
							var status = false;
							if(answers[counter].key == '1'){
								status = true;
							};

							formAnswers = {
								"questionAnswer" : {
									"idQuestion" : question.idQuestion
								},
								"orderingAnswer" : counter + 1,
								"answer" : answers[counter].answer,
								"correctAnswer" : status
							};

							TesterServices.addAnswers(formAnswers).then(function(res){
							});
							counter++;
						}
						var alert = "New question added";
						$state.go("hometester.listcategories.listquestion", {idCategory, alert});
					});
			});
		};
    };
}])