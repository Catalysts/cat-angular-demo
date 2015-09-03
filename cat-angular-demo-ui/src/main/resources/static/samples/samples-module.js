(function () {
    var app = angular.module('cat.angular.demo.samples.SamplesModule', [
        'cat.angular.demo.samples.select.SelectModule'
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
    }]);
})();