/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.factory('AdminServices', ['$http','$q', function($http,$q){
	return	{

		// Untuk Tester
		addTester: function(formData, roles) {
			return $http.post('http://initest.com:9091/secure/createuser', formData, roles).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
			
		},

		getAllTester: function(){
			return $http.get('http://initest.com:9091/secure/getalltester').then(function success(res){
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

		editTester: function(id, formData){
			return $http.put('http://initest.com:9091/secure/updateuser/'+id, formData).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		deleteTester: function(id){
			return $http.delete('http://initest.com:9091/secure/deleteuserbyid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		// Untuk tester

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

		//Untuk Admin sendiri
		editAdmin: function(id, formData){
			return $http.put('http://initest.com:9091/secure/updateuser/'+id, formData).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		getAdmin: function(id){
			return $http.get('http://initest.com:9091/secure/getuserbyid/'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		editAdminPass: function(id, formData){
			return $http.put('http://initest.com:9091/secure/updatepassword/'+id, formData).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},
		//Untuk Admin sendiri
	};
}])