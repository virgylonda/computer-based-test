cbtApp.controller('TesterListCategories', ['$scope', '$state', 'TesterServices', function($scope, $state, TesterServices){

	TesterServices.getAllCategories().then(function(res){
		$scope.arrayCategories = res.data;
	})

}])