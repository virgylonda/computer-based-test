/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterGetCategoriesController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){
	
	var idCategory = $state.params.id;
	
	TesterServices.getCategory(idCategory).then(function(res){
		$scope.categoryObject = res.data;
		if (res.data.questionType == 'Multiple Choice') {
			$scope.multipleChoice = true;
		};
	});

	$scope.toConfirm = function() {
		var confirmResult = confirm("Is this good ?")
		if(confirmResult == true){
			var dataCategory = {
				"description" : $scope.categoryObject.description,
				"idCategory" : $scope.categoryObject.idCategory,
				"questionCategory" : $scope.categoryObject.questionCategory,
				"questionType" : $scope.categoryObject.questionType,
				"timeLimit" : $scope.categoryObject.timeLimit,
			};
			$state.go("hometester.listcategories.editcategory.confirmcategory", {dataCategory});
		}
		else{
			$state.go("hometester.listcategories");
		}	
    }
}])