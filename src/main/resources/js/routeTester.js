cbtApp.config(function($stateProvider) {
	$stateProvider
	.state('home', {
		url: 'tester-dashboard',
		views: {
					'main@': {
						templateUrl: 'views/tester/tester-dashboard.html'
					}
				},
		data: {
					displayName: 'Dashboard'
			  }
	})
}