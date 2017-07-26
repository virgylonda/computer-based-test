/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('UserDoTestController', ['$scope', '$state', 'UserServices', '$timeout', function($scope, $state, UserServices, $timeout){

	var listQuestion = $state.params.listQuestion;
	var timeLimit = $state.params.timeLimit;
	var testId = $state.params.testId;
	var counter = 0;
	var score = 0;

	$scope.question = {};
	$scope.answers = [];
	var answersKey = {};
	
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
		countScore(score);

		$state.go("homeuser.listtest");
	};

	function getAnswer(idQuestion){
		if(idQuestion == null){
			window.alert("No answer selected");
		}
		else{
			UserServices.getAllAnswersFromQuestion(idQuestion).then(function(res){
				$scope.answers = res.data;
			});
		}
	};

	function cekAnswer(idAnswer){
		if(idAnswer == null){
			window.alert("No answer selected");
		}
		else{
			UserServices.getAnswerDetail(idAnswer).then(function(res){
				var answerDetail = res.data;
				console.log("masuk ke cek answer");
				if(answerDetail.correctAnswer == true){
					console.log("true");
					score++;
				}
			});
		}
	};

	function countScore(score){
		var endScore = (score/listQuestion.length) * 100;
		var finalScore = endScore.toFixed(2);
		console.log(finalScore);

		var userTest = {
			"testId" : testId,
			"score" : finalScore
		}
		UserServices.saveTestScore(testId, userTest).then(function(res){
		});
	};
}])