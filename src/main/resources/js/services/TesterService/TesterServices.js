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
			return $http.get('http://localhost:9091/category/getCategoryById/'+id).then(function success(res){
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
		addAnswers: function(formAnswers){
			return $http.post('http://localhost:9091/answer/createanswer/', formAnswers).then(function success(res){
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
		editAnswers: function(id, formAnswer){
			return $http.put('http://localhost:9091/answer/updateanswer/'+id, formAnswer).then(function success(res){
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
			return $http.post('http://localhost:9091/alltester/assignment/save', formAssignment).then(function success(res){
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

		//Untuk User
		addUser: function(formData, roles) {
			return $http.post('http://localhost:9091/rest/user/createUser', formData, roles).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		getAllUser: function(){
			return $http.get('http://localhost:9091/rest/user/getallUser').then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		getUser: function(id){
			return $http.get('http://localhost:9091/rest/user/getuserbyid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		editUser: function(id, formData){
			return $http.put('http://localhost:9091/rest/user/updateuser/'+id, formData).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		deleteUser: function(id){
			return $http.delete('http://localhost:9091/rest/user/deleteuserbyid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		//Untuk User
	};
}])