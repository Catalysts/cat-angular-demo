angular.module('demo', ['cat', 'cat.template'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider.state('index', {
            url: '/index',
            template: '<h1>HOME</h1>'
        });
    }])
    .config(['$urlRouterProvider', function ($urlRouterProvider) {
        $urlRouterProvider.when('', '/index');
        $urlRouterProvider.otherwise('/index');
    }])
    .config(['catViewServiceProvider', function (catViewServiceProvider) {
        window.cat.util.defaultModelResolver = function () {
            return function (data) {
                _.extend(this, data);
            };
        };

        catViewServiceProvider.listAndDetailView('', 'Book', {});
        catViewServiceProvider.listAndDetailView('', 'Manufacturer');
        catViewServiceProvider.listAndDetailView('', 'CarModel');
    }])
    .config(['catSelectConfigServiceProvider', function (catSelectConfigServiceProvider) {
        catSelectConfigServiceProvider.config('manufacturer', {
            endpoint: 'manufacturer'
        });
    }])
    .run(['$rootScope', '$globalMessages', 'catBreadcrumbsService', function ($rootScope, $globalMessages, catBreadcrumbsService) {
        $rootScope.messages = $globalMessages;
        $rootScope.breadcrumbs = catBreadcrumbsService;
    }]);