'use strict';

describe('Directive: fpWhiteboards', function () {

  // load the directive's module
  beforeEach(module('floggitPostitsApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<fp-whiteboards></fp-whiteboards>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the fpWhiteboards directive');
  }));
});
