cbtApp.controller('TesterAddQuestionController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){

	// 	console.log($scope.question.question);
	// 	console.log($scope.question.answer[0].answer);
	// 	console.log($scope.question.answer[1].answer);
	// 	console.log($scope.question.answer[2].answer);
	// 	console.log($scope.question.answer[3].answer);
	// 	console.log($scope.question.choice);
	// 	console.log($state.params.idCategory);

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
						console.log("success add question");
						$state.go("hometester.listcategories.listquestion", {idCategory});
					});
			});
		};
    };
}])