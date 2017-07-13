/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterListQuestionController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){

	console.log($state.params.idCategory);
	var idCategory = $state.params.idCategory;
	TesterServices.getQuestionList(idCategory).then(function(res){
		console.log(res);
		$scope.arrayQuestion = res.data;
	})

	$scope.idCategory = $state.params.idCategory;

	$scope.toDelete = function(id) {
		console.log(id);
		var valid = confirm("Are you sure want to delete question ?");
		if(valid == true){
			console.log(id);
			console.log("Panggil API delete");
			
			TesterServices.deleteQuestion(id).then(function(res){
				console.log("berhasil");
				$state.go("hometester.listcategories.listquestion");
				TesterServices.getQuestionList(idCategory).then(function(res){
					$scope.arrayQuestion = res.data;
				});
			});
		};
    }
}])