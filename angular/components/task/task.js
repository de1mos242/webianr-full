angular.module('tasksApp')
.component('taskComponent', {
	template: require('./task.html'),
	controller: ['$location', 'tasksService', taskController],
	controllerAs: 'taskCtrl',
	bindings: {
		task: '<'
	}
});

function taskController($location, tasksService) {
    var self = this;
    self.open = function() {
        $location.path(self.task.id);
    };
    self.changeState = function() {
        tasksService.changeState(self.task);
    };
}