cbtApp.controller('TesterAssignmentListController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){

	var userId = $state.params.userId;
	TesterServices.getListAssignment(userId).then(function(res){
		console.log(res);
		$scope.arrayList = res.data;
	})
	
	$scope.toAddCategory = function() {

		var confirmResult = confirm("Assign category to user ?")
		if(confirmResult == true){
			$scope.idCategoriesArray = [];
			angular.forEach($scope.arrayList, function(category){
				if(!!category.categories.selected){
					$scope.idCategoriesArray.push(category.categories.idCategory);
				}
			});
			console.log($scope.idCategoriesArray);

			for (var i = 0 ; i < $scope.idCategoriesArray.length; i++) {
				var formAssignment = {
						"testId" : '',
						"started" : new Date(),
						"ended" : '',
						"score" : '',
						"status" : 'Not Yet',
						"users" :{
							"userId"	: userId
						},
						"categories" :{
							"idCategory"	: $scope.idCategoriesArray[i]
					}
				};
				console.log(formAssignment);
				TesterServices.addAssignment(formAssignment).then(function(res){
					$state.go("hometester.userassign");
				})
			}
		};
    }
}])