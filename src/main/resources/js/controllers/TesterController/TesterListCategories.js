/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterListCategories', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){

	$scope.alert = $state.params.alert;
	TesterServices.getAllCategories().then(function(res){
		$scope.arrayCategories = res.data;
	})

	$scope.toDelete = function(id) {
		var valid = confirm("Are you sure want to delete category ?");
		if(valid == true){
			TesterServices.deleteCategory(id).then(function(res){
				var alert = "Category deleted";
				$state.go("hometester.listcategories", {alert});
				TesterServices.getAllCategories().then(function(res){
					$scope.arrayCategories = res.data;
				})
			});
		}
    }
}])