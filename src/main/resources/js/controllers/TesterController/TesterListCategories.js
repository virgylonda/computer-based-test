cbtApp.controller('TesterListCategories', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){

	TesterServices.getAllCategories().then(function(res){
		console.log(res);
		$scope.arrayCategories = res.data;
	})

	$scope.toDelete = function(id) {
		var valid = confirm("Are you sure want to delete category ?");
		if(valid == true){
			console.log(id);
			console.log("Panggil API delete");

			TesterServices.deleteCategory(id).then(function(res){
				console.log("success");
				$state.go("hometester.listcategories");
				TesterServices.getAllCategories().then(function(res){
					$scope.arrayCategories = res.data;
				})
			});
		}
    }
}])