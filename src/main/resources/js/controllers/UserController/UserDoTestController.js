/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('UserDoTestController', ['$scope', '$state', 'UserServices', function($scope, $state, UserServices){

	var listQuestion = $state.params.listQuestion;
	var timeLimit = $state.params.timeLimit;
	var counter = 0;
	$scope.question = {};
	$scope.answers = [];
	
	$scope.onLoad = function(){
		// this for handling question
		$scope.question = listQuestion[counter];
		$scope.question.counter = counter;
		$scope.question.length = listQuestion.length;
		// this for handling answers
		getAnswer($scope.question.idQuestion);
	};

	$scope.timerBegin = function(){
		startTimer(timeLimit);
	}

	$scope.doNext = function(){
		// handle answer
		console.log($scope.answers.option);

		counter++;
		$scope.question = listQuestion[counter];
		$scope.question.counter = counter;
		// this for handling answers
		getAnswer($scope.question.idQuestion);
	};

	// $scope.doBack = function(){
	// };

	$scope.doFinnish = function(){
	};

	function getAnswer(idQuestion){
		UserServices.getAllAnswersFromQuestion(idQuestion).then(function(res){
			$scope.answers = res.data;
			console.log($scope.answers);
		});
	};

	function startTimer(duration) 
	{
	    $scope.timer = duration;
	    setInterval(function () {
	    	$scope.timer--;
	    	console.log($scope.timer);
	    }, 1000);
	}

}])