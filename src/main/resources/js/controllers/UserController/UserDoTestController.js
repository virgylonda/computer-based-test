/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.controller('UserDoTestController', ['$scope', '$state', 'UserServices', function($scope, $state, UserServices){

	console.log("cek masuk");
	var listQuestion = $state.params.listQuestion;
	var counter = 0;
	$scope.question = {};

	$scope.onLoad = function(){
		// this for handling question
		$scope.question.counter = counter;
		$scope.question.question = listQuestion[counter].question;
		$scope.question.length = listQuestion.length
		counter++;

		// this for handling answers
		UserServices.getAllAnswersFromQuestion().then(function(res){

		});
	};

	$scope.doNext = function(){
		$scope.question.counter = counter;
		$scope.question.question = listQuestion[counter].question;
		counter++;
	};

	// $scope.doBack = function(){
	// };

	$scope.doFinnish = function(){
	};
}])