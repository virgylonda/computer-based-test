/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.factory('UserServices', ['$http','$q', function($http,$q){
	return	{

		// UNTUK CATEGORY
		getAllCategories: function(){
			return $http.get('http://localhost:9091/rest/tester/category/list').then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		deleteCategory: function(id){
			return $http.delete('http://localhost:9091/rest/tester/testers/deletecategory/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getCategory: function(id){
			return $http.get('http://localhost:9091/rest/tester/testers/category/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		editCategory: function(id, formCategory){
			return $http.put('http://localhost:9091/rest/tester/testers/updatecategory/'+id, formCategory).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
	};
}])