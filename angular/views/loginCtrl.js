angular.module('tasksApp')
.controller('loginCtrl', ['$location', 'facebookService', 'googleService', loginCtrl]);

function loginCtrl($location, facebookService, googleService) {

	var self = this;
	
	self.loginWithFacebook = function () {
		$location.url('/server/facebook/login');
	};
	
	facebookService.getName().then(function(data) {
		self.facebookName = data.username;
	});
	
	googleService.getName().then(function(data) {
		self.googleName = data.username;
	});
}