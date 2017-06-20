cbtApp.config(function($stateProvider) {
	$stateProvider
	.state('home', {
		url: '/',
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
});

