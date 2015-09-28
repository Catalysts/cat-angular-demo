(function () {
    var app = angular.module('cat.angular.demo.samples.form.Controller', []);

    function SamplesFormController($scope, catBreadcrumbsService, catValidationService) {

        // Object with field errors as returned from backend after submitting a form.
        // Field errors get mapped to input fields and an error message gets displayed.
        var obj = {data: {"globalErrors": [], "fieldErrors": [{"field": "name", "message": "may not be empty"}]}};

        // Error response gets passed into catValidationService, now simulated by a manual call.
        // CatFieldErrors get updated.
        // <cat-field-errors> inside <cat-input-group> template stays either empty or displays message.
        catValidationService.updateFromRejection(obj);

        catBreadcrumbsService.clear();
        catBreadcrumbsService.push({
            title: 'cat-form',
            key: 'cc.catalysts.samples.catform'
        });

        $scope.user = {};

        $scope.reset = function () {
            $scope.user = {};
        };
    }

    app.controller('SamplesFormController', ['$scope', 'catBreadcrumbsService', 'catValidationService', SamplesFormController]);
})();