(function () {
    var app = angular.module('cat.angular.demo.samples.SamplesModule', [
        'cat.angular.demo.samples.select.SelectModule',
        'cat.angular.demo.samples.form.FormModule'
    ]);

    app.config(['$stateProvider', function ($stateProvider) {

        $stateProvider.state('samples', {
            url: '/samples',
            templateUrl: '/samples/index.tpl.html'
        });

        $stateProvider.state('samples-select', {
            url: '/samples/select',
            templateUrl: '/samples/select/index.tpl.html'
        });

        $stateProvider.state('samples-form', {
            url: '/samples/form',
            templateUrl: '/samples/form/index.tpl.html'
        });
    }]);
})();