'use strict';

describe('Service: sockets', function () {

  // load the service's module
  beforeEach(module('floggitPostitsApp'));

  // instantiate service
  var sockets;
  beforeEach(inject(function (_sockets_) {
    sockets = _sockets_;
  }));

  it('should do something', function () {
    expect(!!sockets).toBe(true);
  });

});
