'use strict';

/**
 * @ngdoc directive
 * @name floggitPostitsApp.directive:whiteboard
 * @description
 * # whiteboard
 */
angular.module('floggitPostitsApp')
  .directive('fpWhiteboard', function () {
    return {
      templateUrl: 'views/whiteboard.html',
      restrict: 'E',
      scope: {
        name: '='
      },
      controller: function ($scope, dataStorage, currentWhiteboard) {

        $scope.name = currentWhiteboard.getName();

        function getAllData() {
          dataStorage.getAll(currentWhiteboard.getId());
        }
        getAllData();
        $scope.$on('get-current-whiteboard', function (event, data) {
          console.log(data);
          currentWhiteboard.setCategories(data);
          $scope.$apply();
        });
      }
    };
  });