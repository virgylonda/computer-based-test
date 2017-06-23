cbtApp.controller('AdminEditTesterController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){
	
	var testerId = $state.params.id;
	
	AdminServices.getTester(testerId).then(function(res){
		$scope.testerObject = res.data;
		console.log(res);
	});
}])