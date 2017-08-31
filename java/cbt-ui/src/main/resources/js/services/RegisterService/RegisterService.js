/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.factory('RegisterService', ['$http','$q','$localStorage', function($http,$q,$localStorage){
	return	{

		// For Tester
		addTester: function(formData, roles) {
			return $http.post('http://initest.com:9091/rest/register', formData, roles).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
			
		}
	};
}])