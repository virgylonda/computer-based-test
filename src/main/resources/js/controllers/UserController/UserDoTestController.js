/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('UserDoTestController', ['$scope', '$state', 'UserServices', function($scope, $state, UserServices){

	var listQuestion = $state.params.listQuestion;
	var timeLimit = $state.params.timeLimit;
	var testId = $state.params.testId;
	var counter = 0;
	var score = 0;

	$scope.question = {};
	$scope.answers = [];
	
	$scope.onLoad = function(){
		// update status and time begin
		// UserServices.beginTest(testId).then(function(res){
		// });

		// this for handling question
		$scope.question = listQuestion[counter];
		$scope.question.counter = counter;
		$scope.question.length = listQuestion.length;
		// this for handling answers
		getAnswer($scope.question.idQuestion);
	};

	$scope.doNext = function(){
		// handle answer key
		cekAnswer($scope.answers.option);

		counter++;
		$scope.question = listQuestion[counter];
		$scope.question.counter = counter;
		$scope.question.length = listQuestion.length;
		// this for handling answers
		getAnswer($scope.question.idQuestion);
	};

	// $scope.doBack = function(){
	// };

	$scope.doFinnish = function(){
		// handle answer key
		cekAnswer($scope.answers.option);
		$state.go("homeuser.listtest");
	};

	$scope.timerBegin = function(){
		$scope.timer = timeLimit;
	    setInterval(function () {
	    	$scope.timer--;
	    }, 1000);
	}

	function getAnswer(idQuestion){
		UserServices.getAllAnswersFromQuestion(idQuestion).then(function(res){
			$scope.answers = res.data;
			console.log($scope.answers);
		});
	};

	function cekAnswer(idAnswer){
		UserService.getAnswerDetail(idAnswer).then(function(res){
			var answerDetail = res.data;
			if(answerDetail.correct_answer == true){
				score++;
			}
		});
	};

}])