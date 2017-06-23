cbtApp.controller('AdminListUsersController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){


	AdminServices.getAllUser().then(function(res){
		$scope.arrayUsers = res.data;
	})
}])