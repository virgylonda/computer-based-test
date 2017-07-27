/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('AdmiGetDetailController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){

	var userId = $state.params.userId;
	
	AdminServices.getAdmin(userId).then(function(res){
		$scope.userObject = res.data;
	});

	$scope.toConfirm = function() {

        if ($scope.userObject.name == null) {
			$scope.status = false;
		} else {
			var dataUser = {
				"userId" : $scope.userObject.userId,
				"username" : $scope.userObject.username,
				"name" : $scope.userObject.name,
				"email" : $scope.userObject.email,
				"roles"    : {
					"roleId"	: $scope.userObject.roles.roleId
				}
			};

			$state.go("home.editprofileadmin.confirmed", {dataUser});	
		}
    }

    $scope.toConfirmPass = function() {
    	var userId = $state.params.userId;
    	var oldpass = $scope.dataUser.oldpassword;
    	var pass = $scope.dataUser.password;
    	var newpass = $scope.dataUser.newpassword;

    	if(newpass == pass){
    		var user = {
    			"userId" : userId,
    			"username" : '',
    			"password" : $scope.dataUser.password,
    			"name" : '',
    			"email" : '',
    			"role_id" : '',
    			"roles" : {
    				"roleId" : ''
    			}
    		};

    		$state.go("home.editprofileadmin.confirmedpass", {userId, user});
    	}
    	else{
    		$scope.alert = "Update password failed. Please check again";
    	}
    }
}])