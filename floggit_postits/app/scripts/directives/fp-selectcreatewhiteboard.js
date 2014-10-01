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
					$scope.allWhiteboards = dataStorage.getAllWhiteboards();
				};

				$scope.createNewWhiteboard = function (newWhiteboard) {
					var whiteboard = {
						name: newWhiteboard
					};
					dataStorage.createWhiteboard(whiteboard);
				};

				$scope.choosenWhiteboard = function (boardName) {
					currentWhiteboard.setName(boardName);
					$location.path('/whiteboards/' + currentWhiteboard.getName());
				};

				$scope.deleteWhiteboard = function (boardName) {
					var answer = confirm('Are you sure about deleting this whiteboard with all content?');
					if (answer === true) {
						var whiteboard = {
							name: boardName
						};
						dataStorage.deleteWhiteboard(whiteboard);
					}
				};
				$scope.$on('websocket-open', getAllWhiteboards);
				$scope.$on('create-delete-whiteboard', getAllWhiteboards);
				$scope.$on('get-all-whiteboards', function (event, data) {
					console.log(data);
					$scope.allWhiteboards = data;
					$scope.$apply();
				});
			}
		};
	});