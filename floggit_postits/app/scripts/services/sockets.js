'use strict';

/**
 * @ngdoc service
 * @name floggitPostitsApp.sockets
 * @description
 * # sockets
 * Factory in the floggitPostitsApp.
 */
angular.module('floggitPostitsApp')
  .factory('sockets', function ($rootScope) {

    /* WEBSOCKETS */

    var url = 'ws://localhost:8080//socket-server/whiteboards';
    var websocket = new WebSocket(url);
    var open = false;

    websocket.onopen = function () {
      console.log('Open');
      open = true;
    };

    websocket.onmessage = function (response) {
      console.log(response.data);
      var jsonResponse = JSON.parse(response.data);
      if (jsonResponse.name === 'newWhiteboard') {
        $rootScope.$broadcast('newWhiteboard', 'newWhiteboard');
      } else if (response.data === 'newData') {
        $rootScope.$broadcast('newData', 'Some data');
      }
    };

    function sendSocketMessage(type, message) {
      if (type === 'wb') {

      }
      websocket.send(JSON.stringify(message));
    }

    websocket.onclose = function () {
      console.log('closed');
      open = false;
    };

    return {
      sendSocketMessage: sendSocketMessage
    };
  });