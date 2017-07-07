cbtApp.factory('TesterServices', ['$http','$q', function($http,$q){
	return	{

		getAllCategories: function(){
			return $http.get('http://localhost:9091/rest/tester/category/list').then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
	};
}])