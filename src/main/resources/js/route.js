cbtApp.config(function($stateProvider) {
	$stateProvider
	.state('home', {
		url: 'admin-dashboard',
		views: {
					'main@': {
						templateUrl: 'views/admin/admin-dashboard.html'
					}
				},
		data: {
					displayName: 'Dashboard'
			  }
	})
	.state('home.testerlist', {
		url: '/testerlist',
		views: {
					'main@': {
						templateUrl: 'views/admin/datatester.html',
						controller : "AdminListTesterController"
					}
				},
		data: {
					displayName: 'Tester List'
			  }
	})
	.state('home.userlist', {
		url: '/userlist',
		views: {
					'main@': {
						templateUrl: 'views/admin/datausers.html',
						controller : "AdminListUsersController"
					}
				},
		data: {
					displayName: 'User List'
			  }
	})
	.state('home.editprofileadmin', {
		url: '/editprofileadmin',
		views: {
					'main@': {
						templateUrl: 'views/admin/editprofileadmin.html'
					}
				},
		data: {
					displayName: 'Profile Admin'
			  }
	})
	.state('home.testerlist.addtester', {
		url: '/addtester',
		views: {
					'main@': {
						templateUrl: 'views/admin/formtester.html',
						controller :"AdminAddTesterController"
					}
				},
		data: {
					displayName: 'Add New Tester'
			  }
	})
	.state('home.userlist.adduser', {
		url: '/adduser',
		views: {
					'main@': {
						templateUrl: 'views/admin/formusers.html',
						controller :"AdminAddUserController"
					}
				},
		data: {
					displayName: 'Add New User'
			  }
	})
	.state('home.testerlist.edittester', {
		url: '/edittester/?id',
		params : {
			dataUser : {

			}
		},
		views: {
					'main@': {
						templateUrl: 'views/admin/editprofiletester.html',
						controller : "AdminGetTesterController"
					}
				},
		data: {
					displayName: 'Tester Edit'
			  }
	})
	.state('home.testerlist.edittester.confirmtester', {
		url: '/confirmtester',
		views: {
					'main@': {
						templateUrl: 'views/admin/datatester.html',
						controller : "AdminEditTesterController"
					}
				},
		data: {
					displayName: 'Tester List'
			  }
	})
	.state('home.userlist.edituser', {
		url: '/edituser/?id',
		params : {
			dataUser : {

			}
		},
		views: {
					'main@': {
						templateUrl: 'views/admin/editprofileuser.html',
						controller : "AdminGetUserController"
					}
				},
		data: {
					displayName: 'User Edit'
			  }
	})
	.state('home.userlist.edituser.confirmuser', {
		url: '/confirmuser',
		views: {
					'main@': {
						templateUrl: 'views/admin/datausers.html',
						controller : "AdminEditUserController"
					}
				},
		data: {
					displayName: 'User List'
			  }
	})
});

