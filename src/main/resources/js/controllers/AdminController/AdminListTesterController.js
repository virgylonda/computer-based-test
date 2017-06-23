cbtApp.controller('AdminListTesterController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){


	AdminServices.getAllTester().then(function(res){
		$scope.arrayTester = res.data;
	})
}])