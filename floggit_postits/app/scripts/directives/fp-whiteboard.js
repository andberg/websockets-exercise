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
          $scope.$apply();
        }
        getAllData();
        $scope.$on('set-current-whiteboard', function (event, data) {
          currentWhiteboard.setCategories(data);
          $scope.$apply();
        });

        $scope.$on('data-updated', function () {
          getAllData();
        });
      }
    };
  });