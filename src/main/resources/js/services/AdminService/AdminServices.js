/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.factory('AdminServices', ['$http','$q', function($http,$q){
	return	{

		// Untuk Tester
		addTester: function(formData, roles) {
			return $http.post('http://localhost:9091/rest/user/createUser', formData, roles).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
			
		},

		getAllTester: function(){
			return $http.get('http://localhost:9091/rest/tester/getalltester').then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		getTester: function(id){
			return $http.get('http://localhost:9091/rest/user/getuserbyid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		editTester: function(id, formData){
			return $http.put('http://localhost:9091/rest/user/updateuser/'+id, formData).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		deleteTester: function(id){
			return $http.delete('http://localhost:9091/rest/user/deleteuserbyid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		// Untuk tester

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

		editAdmin: function(id, formData){
			return $http.post('rest/admin/editprofile'+id, formData).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		}
	};
}])