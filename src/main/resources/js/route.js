cbtApp.config(function($stateProvider) {
	$stateProvider
	.state('home', {
		url: 'admin-dashboard',
		views: {
					'main@': {
						templateUrl: 'views/admin-dashboard.html'
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
						templateUrl: 'views/datatester.html',
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
						templateUrl: 'views/datausers.html',
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
						templateUrl: 'views/editprofileadmin.html'
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
						templateUrl: 'views/formtester.html',
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
						templateUrl: 'views/formusers.html',
						controller :"AdminAddUserController"
					}
				},
		data: {
					displayName: 'Add New User'
			  }
	})
	.state('home.testerlist.edittester', {
		url: '/edittester/?id',
		views: {
					'main@': {
						templateUrl: 'views/editprofiletester.html',
						controller : "AdminEditTesterController"
					}
				},
		data: {
					displayName: 'Tester Edit'
			  }
	})
});

