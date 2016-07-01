angular.module('tasksApp')
.controller('tasksViewCtrl', ['tasksService', '$location', tasksViewCtrl]);

function tasksViewCtrl(tasksService, $location) {

	var self = this;

	tasksService.getTasks().then(function(data) {
	    self.tasks = data;
	});

}