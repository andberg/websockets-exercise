'use strict';

/**
 * @ngdoc service
 * @name floggitPostitsApp.dataStorage
 * @description
 * # dataStorage
 * Factory in the floggitPostitsApp.
 */
angular.module('floggitPostitsApp')
  .factory('dataStorage', function ($rootScope) {

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
      if (jsonResponse.type === 'get-current-whiteboard') {
        var categories = [];
        var postits = [];
        categories = jsonResponse.dataArray[0];
        console.log(categories);
        postits = jsonResponse.dataArray[1];
        console.log(postits);
        var categoriesWithPostits = sortPostitsIntoCategories(categories, postits);

        $rootScope.$broadcast('get-current-whiteboard', categoriesWithPostits);
      }

    };
    // Få ut rätt info till hemsidan. 
    //

    function sendSocketMessage(type, data) {
      var message = {
        type: type,
        dataArray: [data]
      };
      if (open) {
        websocket.send(JSON.stringify(message));
      }

    }

    websocket.onclose = function () {
      console.log('closed');
      open = false;
    };

    function createPostit(whiteboard, postit) {
      return (whiteboard, 'postits', postit);
    }

    function createCategory(whiteboard, categoryName) {
      if (categoryName === undefined || categoryName === '') {
        categoryName = 'New Category';
      }
      return (whiteboard, 'categories', {
        name: categoryName
      });
    }

    function getAllCategoriesFor(whiteboard) {
      return whiteboard;
    }

    function getAllPostitsFor(whiteboard) {
      return whiteboard;
    }

    function updatePostit(whiteboard, postit) {
      return whiteboard, postit;
    }

    function updateCategory(whiteboard, category) {
      var filteredCategory = {};
      filteredCategory.id = category.id;
      filteredCategory.name = category.name;
      return null;
    }

    function deletePostit(whiteboard, id) {
      return whiteboard, id;
    }

    function deleteCategory(whiteboard, id) {
      return whiteboard, id;
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
        id: whiteboardId
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
      getAllCategoriesFor: getAllCategoriesFor,
      getAllPostitsFor: getAllPostitsFor,
      getAll: getAll,
      createWhiteboard: createWhiteboard,
      getAllWhiteboards: getAllWhiteboards,
      deleteWhiteboard: deleteWhiteboard,
    };
  });