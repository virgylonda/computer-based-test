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
						templateUrl: 'views/datatester.html'
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
						templateUrl: 'views/datausers.html'
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
});

