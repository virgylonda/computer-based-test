/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.factory('TesterServices', ['$http','$q', function($http,$q){
	return	{

		// UNTUK CATEGORY
		getAllCategories: function(){
			return $http.get('http://initest.com:9091/secure/getallcategory').then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		deleteCategory: function(id){
			return $http.delete('http://initest.com:9091/secure/deletecategory/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getCategory: function(id){
			return $http.get('http://initest.com:9091/secure/getCategoryById/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		editCategory: function(id, formCategory){
			return $http.put('http://initest.com:9091/secure/updatecategory/'+id, formCategory).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		addCategory: function(formCategory){
			return $http.post('http://initest.com:9091/secure/createcategory', formCategory).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		// UNTUK QUESTION
		getQuestionList: function(id){
			return $http.get('http://initest.com:9091/secure/getallquestionbycategoryid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		deleteQuestion: function(id){
			return $http.delete('http://initest.com:9091/secure/deletequestion/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		addQuestion: function(formQuestion){
			return $http.post('http://initest.com:9091/secure/createquestion/', formQuestion).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getQuestion: function(id){
			return $http.get('http://initest.com:9091/secure/getdetailquestion/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		editQuestion: function(id, formCategory){
			return $http.put('http://initest.com:9091/secure/updatequestion/'+id, formCategory).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		addAnswers: function(formAnswers){
			return $http.post('http://initest.com:9091/secure/createanswer/', formAnswers).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getAllAnswersFromQuestion: function(id){
			return $http.get('http://initest.com:9091/secure/getanswerbyid/'+ id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		editAnswers: function(id, formAnswer){
			return $http.put('http://initest.com:9091/secure/updateanswer/'+id, formAnswer).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		//	UNTUK ASSIGNMENT USER
		getAllUsers: function(){
			return $http.get('http://initest.com:9091/secure/getallassignedtest').then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getListAssignment: function(id){
			return $http.get('http://initest.com:9091/secure/gettesthaveassignbyuserid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		addAssignment: function(formAssignment){
			return $http.post('http://initest.com:9091/secure/assignment/save', formAssignment).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		//	UNTUK SCORE
		getListUsersScore: function(){
			return $http.get('http://initest.com:9091/secure/getalluserscore').then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getUserDetailScore: function(id){
			return $http.get('http://initest.com:9091/secure/getallscorebyuserid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		//Untuk User
		addUser: function(formData, roles) {
			return $http.post('http://initest.com:9091/secure/createuser', formData, roles).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		getAllUser: function(){
			return $http.get('http://initest.com:9091/secure/getallUser').then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		getUser: function(id){
			return $http.get('http://initest.com:9091/secure/getuserbyid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		editUser: function(id, formData){
			return $http.put('http://initest.com:9091/secure/updateuser/'+id, formData).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		deleteUser: function(id){
			return $http.delete('http://initest.com:9091/secure/deleteuserbyid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		//Untuk User

		//Untuk Tester sendiri
		editTester: function(id, formData){
			return $http.put('http://initest.com:9091/secure/updateuser/'+id, formData).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getTester: function(id){
			return $http.get('http://initest.com:9091/secure/getuserbyid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		editTesterPass: function(id, formData){
			return $http.put('http://initest.com:9091/secure/updatepassword/'+id, formData).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		//Untuk Admin sendiri
	};
}])