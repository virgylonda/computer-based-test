cbtApp.controller('TesterEditQuestionController', ['$scope', '$state','TesterServices', function($scope, $state, TesterServices){
	
	var dataQuestion = $state.params.dataQuestion;
	var idQuestion = dataQuestion.idQuestion;
	var idCategory = dataQuestion.category.idCategory;

	var confirmResult = confirm("Is this good ?")
	if(confirmResult == true){
		console.log("Panggil API edit question");
		TesterServices.editQuestion(idQuestion, dataQuestion).then(function(res){
			$state.go("hometester.listcategories.listquestion");
		})
	};

	TesterServices.getQuestionList(idCategory).then(function(res){
		$scope.arrayQuestion = res.data;
	})

}]);