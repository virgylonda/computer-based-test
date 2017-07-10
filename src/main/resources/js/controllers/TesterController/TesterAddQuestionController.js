cbtApp.controller('TesterAddQuestionController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){

	// $scope.toAdd = function(){
	// 	console.log($scope.question.question);
	// 	console.log($scope.question.answer[0].answer);
	// 	console.log($scope.question.answer[1].answer);
	// 	console.log($scope.question.answer[2].answer);
	// 	console.log($scope.question.answer[3].answer);
	// 	console.log($scope.question.choice);
	// 	console.log($state.params.idCategory);
	// };

	var idCategory = $state.params.idCategory;

	$scope.toAdd = function(id) {
		var orderingQuestion;

		var valid = confirm("Is this good ?");
		if(valid == true){
			console.log("Masih lanjut");
			console.log(idCategory);
			$state.go("hometester.listcategories.listquestion", {idCategory});
			// var formQuestion = {
			// 	"orderingQuestion" : 3,
			// 	"question"	   : $scope.question.question,
			// 	"category" :{
			// 		"idCategory"	: idCategory
			// 	}
			// };

			// TesterServices.addQuestion(formQuestion).then(function(res){

			// 	TesterServices.getQuestionList(idCategory).then(function(res){
			// 		$state.go("hometester.listcategories.listquestion({ idCategory : idCategory })");
			// 		$scope.arrayQuestion = res.data;
			// 		orderingQuestion = res.data.length;
			// 		console.log(orderingQuestion);
			// 	});
			// });
		};
    }
}])