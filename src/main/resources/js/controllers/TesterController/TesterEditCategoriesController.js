/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterEditCategoriesController', ['$scope', '$state','TesterServices', function($scope, $state, TesterServices){
	
	$scope.dataCategory = $state.params.dataCategory;
	var dataCategory = $scope.dataCategory;
	var idCategory = $scope.dataCategory.idCategory;
	console.log(dataCategory);

	var confirmResult = confirm("Is this good ?")
	if(confirmResult == true){
		console.log("Panggil API edit categories");
		TesterServices.editCategory(idCategory, dataCategory).then(function(res){
			$state.go("hometester.listcategories");
		})
	};

	TesterServices.getAllCategories().then(function(res){
		$scope.arrayCategories = res.data;
	})

}]);