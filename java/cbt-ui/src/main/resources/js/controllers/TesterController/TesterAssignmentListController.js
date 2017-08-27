/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('TesterAssignmentListController', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){

	var userId = $state.params.userId;
	TesterServices.getListAssignment(userId).then(function(res){
		$scope.arrayList = res.data;
	})
	
	$scope.toAddCategory = function() {

		var confirmResult = confirm("Assign test to user ?")
		if(confirmResult == true){
			$scope.idCategoriesArray = [];
			angular.forEach($scope.arrayList, function(category){
				if(!!category.categories.selected){
					$scope.idCategoriesArray.push(category.categories.idCategory);
				}
			});
			if($scope.idCategoriesArray.length == 0){
				window.alert("No category selected");
				$state.go("hometester.userassign");
			}
			else{
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
					TesterServices.addAssignment(formAssignment).then(function(res){
						var alert = "Assign to user success";
						$state.go("hometester.userassign", {alert});
					})
				}
			}
		};
    }
}])