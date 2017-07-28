/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterEditCategoriesController', ['$scope', '$state','TesterServices', function($scope, $state, TesterServices){
	
	$scope.dataCategory = $state.params.dataCategory;
	var dataCategory = $scope.dataCategory;
	var idCategory = $scope.dataCategory.idCategory;

	var confirmResult = confirm("Is this good ?")
	if(confirmResult == true){
		TesterServices.editCategory(idCategory, dataCategory).then(function(res){
			var alert = "Category updated";
			$state.go("hometester.listcategories", {alert});
		})
	};

	TesterServices.getAllCategories().then(function(res){
		$scope.arrayCategories = res.data;
	})

}]);