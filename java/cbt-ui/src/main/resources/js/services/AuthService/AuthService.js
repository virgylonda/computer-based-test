/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.factory('AuthService', ['$http','$q','$localStorage', function($http,$q,$localStorage){
	return	{

		// Untuk Tester
		login: function(user) {
			return $http.post('http://initest.com:9091/authentication/login', user).then(function success(response){
				
	              if (response.token) {
                      // store username and token in local storage to keep user logged in between page refreshes
                      $localStorage.currentUser = { username: user.username, token: response.token };

                      // add jwt token to auth header for all requests made by the $http service
                      $http.defaults.headers.common.Authorization = 'Bearer ' + response.token;

                      // execute callback with true to indicate successful login
                      //callback(true);
                  } else {
                      // execute callback with false to indicate failed login
                      //callback(false);
                  }
				return response;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
			
		}
	};
}])