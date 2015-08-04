angular.module('demo', ['cat', 'cat.template'])
    .config(['catViewServiceProvider', function(catViewServiceProvider) {
        window.cat.util.defaultModelResolver = function() {
            return function(data) {
                _.extend(this, data);
            };
        };

        catViewServiceProvider.listAndDetailView('', 'Book', {});
        catViewServiceProvider.listAndDetailView('', 'Manufacturer');
        catViewServiceProvider.listAndDetailView('', 'CarModel');
    }])
    .config(['catSelectConfigServiceProvider', function(catSelectConfigServiceProvider) {
        catSelectConfigServiceProvider.config('manufacturer', {
            endpoint: 'manufacturer'
        });
    }])
    .run(['$rootScope', '$globalMessages', 'catBreadcrumbsService', function ($rootScope, $globalMessages, catBreadcrumbsService) {
        $rootScope.messages = $globalMessages;
        $rootScope.breadcrumbs = catBreadcrumbsService;
    }]);