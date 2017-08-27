/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterEditCategoriesController', ['$scope', '$state','TesterServices', function($scope, $state, TesterServices){
	
	$scope.dataCategory = $state.params.dataCategory;
	var dataCategory = $scope.dataCategory;
	var idCategory = $scope.dataCategory.idCategory;

	TesterServices.editCategory(idCategory, dataCategory).then(function(res){
		var alert = "Test updated";
		$state.go("hometester.listcategories", {alert});
	})

	TesterServices.getAllCategories().then(function(res){
		$scope.arrayCategories = res.data;
	})

}]);