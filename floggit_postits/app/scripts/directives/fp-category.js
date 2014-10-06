'use strict';

/**
 * @ngdoc directive
 * @name floggitPostitsApp.directive:fpCategory
 * @description
 * # fpCategory
 */
angular.module('floggitPostitsApp')
  .directive('fpCategory', function () {
    return {
      templateUrl: 'views/category.html',
      restrict: 'E',
      scope: {
        category: '='
      },
      controller: function ($scope, $route, dataStorage) {
        var name = $scope.category.name;
        var category = $scope.category;

        $scope.$watch('category', function () {
          category = $scope.category;
        });

        $scope.updateCategoryName = function () {
          if ($scope.category.name !== name) {
            dataStorage.updateCategory($scope.category);
            name = $scope.category.name;

          }
        };

        $scope.deleteCategory = function () {
          var answer = confirm('Are you sure about deleting this category with its postits?');
          if (answer === true) {
            dataStorage.deleteCategory($scope.category);
          }
        };
      }
    };
  });