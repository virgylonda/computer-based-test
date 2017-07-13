/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.factory('TesterServices', ['$http','$q', function($http,$q){
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

		// UNTUK QUESTION
		getQuestionList: function(id){
			return $http.get('http://localhost:9091/rest/tester/question/list/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		deleteQuestion: function(id){
			return $http.delete('http://localhost:9091/rest/tester/testers/deletequestion/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		addQuestion: function(formQuestion){
			return $http.post('http://localhost:9091/rest/tester/testers/addquestion/', formQuestion).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getQuestion: function(id){
			return $http.get('http://localhost:9091/rest/tester/testers/question/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		editQuestion: function(id, formCategory){
			return $http.put('http://localhost:9091/rest/tester/testers/updatequestion/'+id, formCategory).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		addCategory: function(formCategory){
			return $http.post('http://localhost:9091/rest/tester/testers/addcategory', formCategory).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		//	UNTUK ASSIGNMENT USER
		getAllUsers: function(){
			return $http.get('http://localhost:9091/rest/tester/testers/assignment').then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getListAssignment: function(id){
			return $http.get('http://localhost:9091/rest/tester/testers/assignment/list/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		addAssignment: function(formAssignment){
			return $http.post('http://localhost:9091/rest/tester/testers/assignment/save', formAssignment).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		//	UNTUK SCORE
		getListUsersScore: function(){
			return $http.get('http://localhost:9091/rest/tester/usertest/list').then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getUserDetailScore: function(id){
			return $http.get('http://localhost:9091/rest/tester/user/score/detail/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
	};
}])