angular.module('tasksApp')
.component('taskList', {
	template: require('./taskList.html'),
	controller: ['tasksService', taskListCtrl],
	controllerAs: 'tasksCtrl',
	bindings: {
		tasks: '<'
	}
});

function taskListCtrl(tasksService) {
	var self = this;

	self.addTask = function() {
		if (self.newTaskText) {
		    tasksService.add(self.newTaskText);
			self.newTaskText = '';
		}
	};
}