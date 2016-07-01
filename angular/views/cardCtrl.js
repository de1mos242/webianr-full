angular.module('tasksApp')
.controller('taskViewCtrl', ['tasksService', '$routeParams', '$scope', taskViewCtrl]);

function taskViewCtrl(tasksService, $routeParams, $scope) {
	var id = parseInt($routeParams.id);
	tasksService.get(id).then(function(data) {
	    $scope.task = data;
	});
}