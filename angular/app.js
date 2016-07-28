require('angular');
require('angular-route');

require('./style.css');

angular.module('tasksApp', ['ngRoute'])
.service('tasksService', ['$http', tasksService])
.service('facebookService', ['$http', facebookService])
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

function tasksService($http) {
	return {
		getTasks: function() {
			return $http.get('rest/tasks/').then(function (res) {
			    return res.data;
			});
		},
		
		get: function(id) {
		    return $http.get('rest/tasks/' + id).then(function (res) {
                return res.data;
            });
		}
	};
}

function facebookService($http) {
	return {
		getName: function() {
			return $http.get('facebook/me').then(function (result) {
				return result.data;
			});
		}
	};
}

require('./views');
require('./components');