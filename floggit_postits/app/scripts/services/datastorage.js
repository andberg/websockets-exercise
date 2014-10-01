'use strict';

/**
 * @ngdoc service
 * @name floggitPostitsApp.dataStorage
 * @description
 * # dataStorage
 * Factory in the floggitPostitsApp.
 */
angular.module('floggitPostitsApp')
  .factory('dataStorage', function ($rootScope, $http, $q) {

    var url = 'ws://localhost:8080/socket-server/whiteboards';
    var websocket = new WebSocket(url);
    var open = false;

    websocket.onopen = function () {
      console.log('Open');
      open = true;
      $rootScope.$broadcast('websocket-open');
    };

    websocket.onmessage = function (response) {
      console.log(response.data);
      var jsonResponse = JSON.parse(response.data);
      if (jsonResponse.type === 'get-all-whiteboards') {
        $rootScope.$broadcast('get-all-whiteboards', jsonResponse.dataArray);
      }
      if (jsonResponse.type === 'create-whiteboard' || jsonResponse.type === 'delete-whiteboard') {
        $rootScope.$broadcast('create-delete-whiteboard');
      }

    };

    function sendSocketMessage(type, data) {
      var message = {
        type: type,
        dataArray: [data]
      };
      console.log(message);
      if (open) {
        websocket.send(JSON.stringify(message));
      }

    }

    websocket.onclose = function () {
      console.log('closed');
      open = false;
    };

    function createPostit(whiteboard, postit) {
      return basicPost(whiteboard, 'postits', postit);
    }

    function createCategory(whiteboard, categoryName) {
      if (categoryName === undefined || categoryName === '') {
        categoryName = 'New Category';
      }
      return basicPost(whiteboard, 'categories', {
        name: categoryName
      });
    }

    function getAllCategoriesFor(whiteboard) {
      return basicGet(whiteboard, 'categories');
    }

    function getAllPostitsFor(whiteboard) {
      return basicGet(whiteboard, 'postits');
    }

    function updatePostit(whiteboard, postit) {
      return basicPut(whiteboard, 'postits', postit, postit.id);
    }

    function updateCategory(whiteboard, category) {
      var filteredCategory = {};
      filteredCategory.id = category.id;
      filteredCategory.name = category.name;
      return basicPut(whiteboard, 'categories', filteredCategory, category.id);
    }

    function deletePostit(whiteboard, id) {
      return basicDelete(whiteboard, 'postits', id);
    }

    function deleteCategory(whiteboard, id) {
      return basicDelete(whiteboard, 'categories', id);
    }

    function addPostitToCorrespondingCategory(categories, postit) {
      for (var j = 0; j < categories.length; j = j + 1) {
        if (postit.category === categories[j].id) {
          if (categories[j].postits === undefined) {
            categories[j].postits = [];
          }
          categories[j].postits.push(postit);
          break;
        }
      }
    }

    function sortPostitsIntoCategories(categories, postits) {
      for (var i = 0; i < postits.length; i = i + 1) {
        addPostitToCorrespondingCategory(categories, postits[i]);
      }
      return categories;
    }

    function getAll(whiteboard) {
      var currentWhiteboard = {
        name: whiteboard
      };
      return sendSocketMessage('get-current-whiteboard', currentWhiteboard);
      /*$q.all([
          getAllCategoriesFor(whiteboard), // res[0] === categories
          getAllPostitsFor(whiteboard) // res[1] === postits
        ])
        .then(function (res) {
          return sortPostitsIntoCategories(res[0], res[1]);
        });*/
    }

    function getAllWhiteboards() {
      var dummyData = {
        data: 'data'
      };
      var response = sendSocketMessage('get-all-whiteboards', dummyData);
      return response;
    }

    function createWhiteboard(newWhiteboard) {
      return sendSocketMessage('create-whiteboard', newWhiteboard);
    }

    function deleteWhiteboard(boardName) {
      return sendSocketMessage('delete-whiteboard', boardName);
    }

    return {
      createPostit: createPostit,
      createCategory: createCategory,
      updatePostit: updatePostit,
      updateCategory: updateCategory,
      deletePostit: deletePostit,
      deleteCategory: deleteCategory,
      getAllCategoriesFor: getAllCategoriesFor,
      getAllPostitsFor: getAllPostitsFor,
      getAll: getAll,
      createWhiteboard: createWhiteboard,
      getAllWhiteboards: getAllWhiteboards,
      deleteWhiteboard: deleteWhiteboard,
    };
  });