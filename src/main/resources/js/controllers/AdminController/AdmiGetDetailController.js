/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('AdmiGetDetailController', ['$scope', '$state', 'AdminServices', function($scope, $state, AdminServices){

	var userId = $state.params.userId;
	
	AdminServices.getAdmin(userId).then(function(res){
		$scope.userObject = res.data;
		console.log(res);
	});

	$scope.toConfirm = function() {

        if ($scope.dataUser.name == null) {
			$scope.status = false;
		} else {
			var dataUser = {
				"userId" : $scope.userObject.userId,
				"username" : $scope.userObject.username,
				"name" : $scope.dataUser.name,
				"email" : $scope.dataUser.email,
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
    	var newpass = $scope.newpassword;
    	AdminServices.getAdmin(userId).then(function(res){
			var objectUser = res.data;
			console.log(res);
			//decode password dulu
			if(objectUser.password == oldpass){
				if(pass == newpass){
					var dataUser = {
						"password" : $scope.userObject.password,
					};

					$state.go("home.testerlist.edittester.confirmtester", {dataUser});
				}
				else{
					window.alert("Please check validate password again");
				}
			}
			else{
				window.alert("Password is wrong");
			}
		});
    }
}])