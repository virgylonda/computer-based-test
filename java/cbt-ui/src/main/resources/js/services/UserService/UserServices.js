/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.factory('UserServices', ['$http','$q', function($http,$q){
	return	{

		// UNTUK CATEGORY
		getAssignedCategory: function(id){
			return $http.get('http://initest.com:9091/secure/list/'+ id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getAllQuestion: function(id){
			return $http.get('http://initest.com:9091/secure/getallquestionbycategoryid/'+ id).then(function success(res){
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
		beginTest: function(idTest){
			return $http.put('http://initest.com:9091/secure/begintest/'+ idTest).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getAnswerDetail: function(id){
			return $http.get('http://initest.com:9091/secure/detail/'+ id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		saveTestScore: function(idTest, listAnswers){
			return $http.put('http://initest.com:9091/secure/submit/'+ idTest, listAnswers).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		//Untuk User sendiri
		editProfileUser: function(id, formData){
			return $http.put('http://initest.com:9091/secure/updateuser/'+id, formData).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getUserDetail: function(id){
			return $http.get('http://initest.com:9091/secure/getuserbyid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		editUserPass: function(id, formData){
			return $http.put('http://initest.com:9091/secure/updatepassword/'+id, formData).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		//Untuk User sendiri
	};
}])