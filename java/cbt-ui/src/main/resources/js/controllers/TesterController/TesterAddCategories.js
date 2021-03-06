/**
Author : Edric Laksa Putra
Since : June 2017
*/
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
			TesterServices.addCategory(formCategory).then(function(res){
				var alert = "New test added";
				$state.go("hometester.listcategories", {alert});
			});
		};
    };
}])