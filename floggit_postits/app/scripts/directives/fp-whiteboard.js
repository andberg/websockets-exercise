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
      controller: function ($scope, $timeout, dataStorage, currentWhiteboard) {

        $scope.name = currentWhiteboard.getName();
        var getAllOnce = true;

        function getAllData() {
          dataStorage.getAll(currentWhiteboard.getId());
        }

        if (getAllOnce) {
          getAllData();
          getAllOnce = false;
        }

        $scope.$on('set-current-whiteboard', function (event, data) {
          currentWhiteboard.setCategories(data);
          $timeout(function () {
            $scope.$apply();
          });
        });

        $scope.$on('data-updated', function () {
          getAllData();
        });
      }
    };
  });