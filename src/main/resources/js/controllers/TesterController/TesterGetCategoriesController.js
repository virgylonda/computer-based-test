cbtApp.controller('TesterGetCategoriesController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){
	
	var idCategory = $state.params.id;
	
	TesterServices.getCategory(idCategory).then(function(res){
		$scope.categoryObject = res.data;
		if (res.data.questionType == 'Multiple Choice') {
			$scope.multipleChoice = true;
		};
		console.log(res);
	});

	$scope.toConfirm = function() {

		var dataCategory = {
			"description" : $scope.categoryObject.description,
			"idCategory" : $scope.categoryObject.idCategory,
			"questionCategory" : $scope.categoryObject.questionCategory,
			"questionType" : $scope.categoryObject.questionType,
			"timeLimit" : $scope.categoryObject.timeLimit,
		};
		console.log(dataCategory);
		$state.go("hometester.listcategories.editcategory.confirmuser", {dataCategory});	
    }
}])