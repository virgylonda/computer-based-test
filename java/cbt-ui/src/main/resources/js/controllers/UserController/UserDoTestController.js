/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('UserDoTestController', ['$scope', '$state', 'UserServices', '$timeout','$window','$http', function($scope, $state, UserServices, $timeout, $window, $http){

	var listQuestion = $state.params.listQuestion;
	var timeLimit = $state.params.timeLimit;
	var testId = $state.params.testId;
	var counter = 0;
	var score = 0;
	var timer = null;

	$scope.question = {};
	$scope.answers = [];
	var answersKey = [];
	
	$scope.onLoad = function(){
		clearInterval(timer);
		// update status and time begin
		UserServices.beginTest(testId).then(function(res){
		});
		
		doTimer();
		// this for handling question
		if(listQuestion == null || listQuestion.length == 0){
			window.alert("No question selected");
			$state.go("homeuser.listtest");
		}
		else{
			$scope.question = listQuestion[counter];
			$scope.question.counter = counter;
			$scope.question.length = listQuestion.length;
			// this for handling answers
			getAnswer($scope.question.idQuestion);
		}
	};

	$scope.doNext = function(){
		// handle answer key
		insertAnswerKey($scope.answers.option, counter);

		counter++;
		$scope.question = listQuestion[counter];
		$scope.question.counter = counter;
		$scope.question.length = listQuestion.length;
		// this for handling answers
		getAnswer($scope.question.idQuestion);
	};

	$scope.doBack = function(){
		counter--;
		$scope.question = listQuestion[counter];
		$scope.question.counter = counter;
		$scope.question.length = listQuestion.length;

		getAnswer($scope.question.idQuestion);

		insertAnswerKey($scope.answers.option, counter);
	};

	$scope.doFinish = function(){
		//to stop the timer
		clearInterval(timer);

		// handle answer key
		insertAnswerKey($scope.answers.option, counter);
		
		//lempar semua id answer ke back
		UserServices.saveTestScore(testId, answersKey).then(function(res){
		});

		var alert = "Test has completed";
		$state.go("homeuser.listtest", {alert});
	};

	function getAnswer(idQuestion){
		if(idQuestion == null){
			window.alert("Question is invalid");
		}
		else{
			var token = $window.localStorage['jwtToken']
			$http.defaults.headers.common.Authorization = 'Bearer ' + token;

			UserServices.getAllAnswersFromQuestion(idQuestion).then(function(res){
				$scope.answers = res.data;
			});
		}
	};

	function insertAnswerKey(idAnswer, counter){
		if(idAnswer == null){
			answersKey[counter] = '';
		}
		else{
			answersKey[counter] = idAnswer;
		}
	};

	doTimer = function(){
		$scope.countDown = timeLimit;    
	    timer = setInterval(function(){
	    	if($scope.countDown < 60){
	    		$scope.minutes = '00';
	    		$scope.seconds = $scope.countDown;
	    	}
	    	else{
	    		var minute = $scope.countDown / 60;
	    		$scope.minutes = Math.floor(minute);
	    		$scope.seconds = ($scope.countDown % 60);
	    	};
	        if($scope.countDown == -1){
	        	$scope.minutes = '00';
	        	$scope.seconds = '00';
	        	$scope.$apply();
	        	window.alert("Time is Up !");
	        	insertAnswerKey($scope.answers.option, counter);
	        	for (var i = counter+1; i < listQuestion.length; i++) {
	        		answersKey[i] = '';
	        	}

	        	//lempar semua id answer ke back
				UserServices.saveTestScore(testId, answersKey).then(function(res){
				});
				
				clearInterval(timer);
				var alert = "Test has completed";
				$state.go("homeuser.listtest", {alert});
	        }
	        $scope.countDown--;
	        $scope.$apply();
	    }, 1000);
	};
}])