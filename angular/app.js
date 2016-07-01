require('angular');
require('angular-route');

require('./style.css');

angular.module('tasksApp', ['ngRoute'])
.service('tasksService', ['$http', tasksService])
.config(['$routeProvider', routeConfig]);

function routeConfig($routeProvider) {
	$routeProvider
	.when('/', {
		template: require('./views/list.html'),
		controller: 'tasksViewCtrl',
		controllerAs: 'tasksViewCtrl'
	})
	.when('/:id', {
		template: require('./views/card.html'),
		controller: 'taskViewCtrl'
	})
	.otherwise('/');
}

function mockTaskService($q) {
	var tasks = [{
		id: 1,
		text: 'Подключить bower',
		checked: false
	}, {
		id: 2,
		text: 'Включить минификатор',
		checked: false
	}, {
		id: 3,
		text: 'Включить eslint',
		checked: false
	}, {
		id: 4,
		text: 'Включить в общую сборку с сервером',
		checked: false
	}, {
		id: 5,
		text: 'Реализовать добавление и обновление задач',
		checked: false
	}];

	return {
		getTasks: function() {
			return $q.resolve(tasks);
		},
		get: function(id) {
			for (var i = 0; i < tasks.length; i++) {
				if (tasks[i].id === id) {
					return $q.resolve(tasks[i]);
				}
			}
			return $q.reject('Not found: 404');
		},
		changeState: function(task) {
			task.checked = !task.checked;
		}
	}
}

function tasksService($http) {
	return {
		getTasks: function() {
			return $http.get('rest/tasks/').then(function (res) {
			    return res.data;
			});
		},
		//changeState: function(task) {
		//	task.checked = !task.checked;
		//},
		//addTask: function(text) {
		//	var task = {
		//		id: tasks[tasks.length - 1].id + 1,
		//		text: text,
		//		checked: false
		//	};
		//	tasks.push(task);
		//},
		get: function(id) {
		    return $http.get('rest/tasks/' + id).then(function (res) {
                return res.data;
            });
		}
	};
}

require('./views');
require('./components');