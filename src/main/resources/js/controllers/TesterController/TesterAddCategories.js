cbtApp.controller('TesterAddCategories', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){

	$scope.addCategory = function() {

		var valid = confirm("Is this good ?");
		if(valid == true){
			var formCategory = {
				"questionCategory" : $scope.category.name,
				"description" : $scope.category.description,
				"questionType" : $scope.category.type,
				"timeLimit" : $scope.category.limit
			};
			console.log(formCategory);
			TesterServices.addCategory(formCategory).then(function(res){
				$state.go("hometester.listcategories");
			});
		};
    };
}])