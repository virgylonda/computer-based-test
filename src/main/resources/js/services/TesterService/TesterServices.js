/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.factory('TesterServices', ['$http','$q', function($http,$q){
	return	{

		// UNTUK CATEGORY
		getAllCategories: function(){
			return $http.get('http://localhost:9091/category/getallcategory').then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		deleteCategory: function(id){
			return $http.delete('http://localhost:9091/category/deletecategory/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getCategory: function(id){
			return $http.get('http://localhost:9091/category/getCategoryDetail/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		editCategory: function(id, formCategory){
			return $http.put('http://localhost:9091/category/updatecategory/'+id, formCategory).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		addCategory: function(formCategory){
			return $http.post('http://localhost:9091/category/createcategory', formCategory).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		// UNTUK QUESTION
		getQuestionList: function(id){
			return $http.get('http://localhost:9091/question/getallquestionbycategoryid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		deleteQuestion: function(id){
			return $http.delete('http://localhost:9091/question/deletequestion/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		addQuestion: function(formQuestion){
			return $http.post('http://localhost:9091/question/createquestion/', formQuestion).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getQuestion: function(id){
			return $http.get('http://localhost:9091/question/getdetailquestion/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		editQuestion: function(id, formCategory){
			return $http.put('http://localhost:9091/question/updatequestion/'+id, formCategory).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		//	UNTUK ASSIGNMENT USER
		getAllUsers: function(){
			return $http.get('http://localhost:9091/test/getallassignedtest').then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getListAssignment: function(id){
			return $http.get('http://localhost:9091/test/gettesthaveassignbyuserid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		addAssignment: function(formAssignment){
			return $http.post('http://localhost:9091/rest/tester/assignment/save', formAssignment).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		//	UNTUK SCORE
		getListUsersScore: function(){
			return $http.get('http://localhost:9091/test/getalluserscore').then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getUserDetailScore: function(id){
			return $http.get('http://localhost:9091/test/getallscorebyuserid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
	};
}])