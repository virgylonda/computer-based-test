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

	$scope.question = {};
	$scope.answers = [];
	var answersKey = [];
	
	$scope.onLoad = function(){
		// update status and time begin
		// UserServices.beginTest(testId).then(function(res){
		// });

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
		// handle answer key
		insertAnswerKey($scope.answers.option, counter);
		console.log(answersKey);

		$state.go("homeuser.listtest");
	};

	function getAnswer(idQuestion){
		if(idQuestion == null){
			window.alert("Question invalid");
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
			window.alert("No answer selected");
			answersKey[counter] = '';
		}
		else{
			answersKey[counter] = idAnswer;
		}
	};
}])