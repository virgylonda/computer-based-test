/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.factory('AuthService', ['$http','$q', function($http,$q){
	return	{

		// Untuk Tester
		login: function(user) {
			return $http.post('http://initest.com:9091/authentication/login', user).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
			
		}
	};
}])