angular.module('tasksApp')
.controller('tasksViewCtrl', ['tasksService', '$location', 'facebookService', tasksViewCtrl]);

function tasksViewCtrl(tasksService, $location, facebookService) {

	var self = this;

	tasksService.getTasks().then(function(data) {
	    self.tasks = data;
	});
	
	facebookService.getName().then(function(data) {
		self.facebookName = data.username;
	});

}