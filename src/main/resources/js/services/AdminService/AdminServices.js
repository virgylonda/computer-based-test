cbtApp.factory('AdminServices', ['$http','$q' function($http,$q){
	return	{

		// Untuk Tester
		addTester: function(formData) {
			return $http.post('masukan API addTester disini', formData).then(function success(res){
				res = true;
			}, function error(err){
				res = false;
				return $q.reject(err.data);
			}.bind(this));
			return res;
		},

		getAllTester: function(){
			return $http.get('masukan API getAllTester disini').then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		}

		editTester: function(id, formData){
			return $http.post('masukan API editTester disini'+id, formData).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		}

		deleteTester: function(id){
			return $http.put('masukan API deleteTester disini'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		}

		getTester: function(username){
			return $http.get('masukan API getTester disini'+username).then(function success(res){
				res = true;
			}, function error(err){
				res = false;
			}.bind(this));
			return res;
		}
		// Untuk tester

		//Untuk User
		addUser: function(formData) {
			return $http.post('masukan API addUser disini', formData).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		},

		getAllUser: function(){
			return $http.get('masukan API getAllUser disini').then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		}

		editUser: function(id, formData){
			return $http.post('masukan API editUser disini'+id, formData).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		}

		deleteUser: function(id){
			return $http.put('masukan API deleteUser disini'+id).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		}
		//Untuk User

		editAdmin: function(id, formData){
			return $http.post('masukan API editAdmin disini'+id, formData).then(function success(res){
				return res;
			}, function error(err){
				return $q.reject(err.data);
			}.bind(this));
		}
	};
}])