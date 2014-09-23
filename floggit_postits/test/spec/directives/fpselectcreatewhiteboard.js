'use strict';

describe('Directive: fpSelectCreateWhiteboard', function () {

  // load the directive's module
  beforeEach(module('floggitPostitsApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<fp-select-create-whiteboard></fp-select-create-whiteboard>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the fpSelectCreateWhiteboard directive');
  }));
});
