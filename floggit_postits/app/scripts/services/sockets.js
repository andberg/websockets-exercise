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

    /* WEBSOCKET CONNECTION */

    var url = 'ws://localhost:8080/angular-websockets-server/whiteboards';
    var websocket = new WebSocket(url, ['textarea']);
    var open = false;

    websocket.onopen = function () {
      console.log('Open');
      open = true;
    };

    websocket.onmessage = function (response) {
      console.log(JSON.parse(response.data));
      $rootScope.$broadcast('ws-message', JSON.parse(response.data).serverText);
    };

    return {
      send: function (text) {
        if (open) {
          websocket.send(JSON.stringify({
            clientText: text
          }));
        }

      }
    };
  });