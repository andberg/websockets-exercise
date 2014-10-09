'use strict';

/**
 * @ngdoc service
 * @name floggitPostitsApp.dataStorage
 * @description
 * # dataStorage
 * Factory in the floggitPostitsApp.
 */

angular.module('floggitPostitsApp')
  .factory('dataStorage', function ($rootScope, currentWhiteboard) {

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
      if (jsonResponse.type === 'create-whiteboard') {
        $rootScope.$broadcast('create-delete-whiteboard');
      }
      if (jsonResponse.type === 'delete-whiteboard') {
        $rootScope.$broadcast('create-delete-whiteboard');
      }

      if (jsonResponse.whiteboard === parseInt(currentWhiteboard.getId())) {
        if (jsonResponse.type === 'get-current-whiteboard') {
          var categories = [];
          var postits = [];
          categories = jsonResponse.dataArray[0];
          postits = jsonResponse.dataArray[1];
          var categoriesWithPostits = sortPostitsIntoCategories(categories, postits);
          $rootScope.$broadcast('set-current-whiteboard', categoriesWithPostits);
        }
        if (jsonResponse.type === 'delete-category' || jsonResponse.type === 'create-category' || jsonResponse.type === 'update-category' || jsonResponse.type === 'create-postit' || jsonResponse.type === 'update-postit' || jsonResponse.type === 'delete-postit') {
          if (jsonResponse.type === 'create-postit') {
            $rootScope.$broadcast('reset-form');
          }

          $rootScope.$broadcast('data-updated', 'trigger refresh');
        }
      }
    };

    function sendSocketMessage(type, data) {
      var message;
      if (type === 'get-all-whiteboards' ||  type === 'delete-whiteboard' ||  type === 'create-whiteboard') {
        message = {
          whiteboard: 0,
          type: type,
          dataArray: [data]
        };
      } else {
        message = {
          whiteboard: parseInt(currentWhiteboard.getId()),
          type: type,
          dataArray: [data]
        };
      }
      if (open) {
        websocket.send(JSON.stringify(message));
      }
    }

    websocket.onclose = function () {
      console.log('closed');
      open = false;
    };

    function createPostit(postit) {
      return sendSocketMessage('create-postit', postit);
    }

    function createCategory(whiteboardId) {
      var category = {
        name: 'new Category',
        whiteboard: whiteboardId
      };
      console.log(whiteboardId);
      return sendSocketMessage('create-category', category);
    }

    function updatePostit(postit) {
      return sendSocketMessage('update-postit', postit);
    }

    function updateCategory(category) {
      return sendSocketMessage('update-category', category);
    }

    function deletePostit(postit) {
      return sendSocketMessage('delete-postit', postit);
    }

    function deleteCategory(category) {
      return sendSocketMessage('delete-category', category);
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

    function getAll(whiteboardId) {
      var currentWhiteboard = {
        id: parseInt(whiteboardId)
      };
      return sendSocketMessage('get-current-whiteboard', currentWhiteboard);
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

    function deleteWhiteboard(id) {
      return sendSocketMessage('delete-whiteboard', id);
    }

    return {
      createPostit: createPostit,
      createCategory: createCategory,
      updatePostit: updatePostit,
      updateCategory: updateCategory,
      deletePostit: deletePostit,
      deleteCategory: deleteCategory,
      getAll: getAll,
      createWhiteboard: createWhiteboard,
      getAllWhiteboards: getAllWhiteboards,
      deleteWhiteboard: deleteWhiteboard
    };
  });