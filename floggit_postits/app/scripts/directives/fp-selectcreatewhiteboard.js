'use strict';

/**
 * @ngdoc directive
 * @name floggitPostitsApp.directive:fpSelectCreateWhiteboard
 * @description
 * # fpSelectCreateWhiteboard
 */
angular.module('floggitPostitsApp')
	.directive('fpSelectCreateWhiteboard', function () {
		return {
			templateUrl: 'views/createselectwhiteboard.html',
			restrict: 'E',
			controller: function ($scope, $http, $route, $location, sockets, currentWhiteboard, dataStorage) {

				$scope.allWhiteboards = [];

				var getAllWhiteboards = function () {
					dataStorage.getAllWhiteboards().then(function (data) {
						$scope.allWhiteboards = data;
					});
				};
				getAllWhiteboards();

				$scope.createNewWhiteboard = function (newWhiteboard) {
					var whiteboard = {
						name: newWhiteboard
					};
					dataStorage.createWhiteboard(whiteboard).then(function () {
						sockets.sendSocketMessage(whiteboard);
						getAllWhiteboards();
					});
				};

				$scope.choosenWhiteboard = function (boardName) {
					console.log(boardName);
					currentWhiteboard.setName(boardName);
					$location.path('/whiteboards/' + currentWhiteboard.getName());
				};

				$scope.deleteWhiteboard = function (boardId) {
					var answer = confirm('Are you sure about deleting this whiteboard with all content?');
					if (answer === true) {
						dataStorage.deleteWhiteboard(boardId).then(function () {

						});
					}
				};
				$scope.$on('newWhiteboard', getAllWhiteboards);
			}
		};
	});