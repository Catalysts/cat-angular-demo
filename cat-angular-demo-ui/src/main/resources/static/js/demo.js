angular.module('demo', ['cat', 'cat.template',
    'cat.angular.demo.book.BookModule',
    'cat.angular.demo.country.CountryModule',
    'cat.angular.demo.manufacturer.ManufacturerModule',
    'cat.angular.demo.samples.SamplesModule',
    'cat.directives.pageSize'
])
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
    .config(function () {

        // we are going to extend the defaultModelResolver with the ability to return an anonymous model,
        // in case the requested one is unknown
        // NOTE: the default implementation would return undefined
        var baseImpl = window.cat.util.defaultModelResolver;
        window.cat.util.defaultModelResolver = function (name) {

            var result = baseImpl(name);

            if (!!result) {
                return result;
            }

            // fallback, return anonymous object
            return function (data) {
                _.extend(this, data);
            };
        };
    })
    .run(['$rootScope', '$globalMessages', 'catBreadcrumbsService', function ($rootScope, $globalMessages, catBreadcrumbsService) {
        $rootScope.messages = $globalMessages;
        $rootScope.breadcrumbs = catBreadcrumbsService;
    }]);