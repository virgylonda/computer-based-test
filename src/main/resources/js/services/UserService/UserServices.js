/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.factory('UserServices', ['$http','$q', function($http,$q){
	return	{

		// UNTUK CATEGORY
		getAssignedCategory: function(id){
			return $http.get('http://localhost:9091/test/list/'+ id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getAllQuestion: function(id){
			return $http.get('http://localhost:9091/question/getallquestionbycategoryid/'+ id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getAllAnswersFromQuestion: function(id){
			return $http.get('http://localhost:9091/answer/getanswerbyid/'+ id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
	};
}])