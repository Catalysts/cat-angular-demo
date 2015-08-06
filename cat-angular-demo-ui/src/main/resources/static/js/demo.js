angular.module('demo', ['cat', 'cat.template',
    'cat.angular.demo.manufacturer.carmodel.ManufacturerCarmodel',
    'cat.angular.demo.manufacturer.industry.ManufacturerIndustry',
    'cat.angular.demo.manufacturer.carmodel.variation.ManufacturerCarmodelVariation',
    'cat.demo.manufacturer.ManufacturerDetailsController'])
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
        window.cat.util.defaultModelResolver = function (name) {
            if (name === 'ManufacturerCarModel') {
                return function ManufacturerCarModel(data) {
                    var that = this;
                    _.extend(this, data);

                    this.setParent = function (parent) {
                        that.manufacturer = parent;
                    }
                };
            }
            else if (name === 'ManufacturerIndustry') {
                return function ManufacturerIndustry(data) {
                    var that = this;
                    _.extend(this, data);

                    this.setParent = function (parent) {
                        that.manufacturer = parent;
                    }
                };
            }

            return function (data) {
                _.extend(this, data);
            };
        };

        catViewServiceProvider.listAndDetailView('', 'Book', {});
        //catViewServiceProvider.builder()
        //    .name('Manufacturer')
        //    .child('CarModel')
        //    .icon('map-marker')
        //    .parent()
        //    .build();

        catViewServiceProvider.listAndDetailView('', 'Manufacturer', {
            endpoint: {
                children: {
                    carmodel: {
                        url: 'carmodels',
                        children : {
                            variation: {
                                url: 'variations'
                            }
                        }
                    },
                    industry: {
                        url: 'industries'
                    }
                }
            },
            details: {
                additionalViewTemplate: 'tabs',
                additionalViewTemplateTabs: [
                    {
                        name: 'carmodel',
                        icon: 'map-marker'
                    }, {
                        name: 'industry',
                        icon: 'map-marker'
                    }
                ]
            }
        });

        catViewServiceProvider.listAndDetailView('', 'CarModel'); /*{

            endpoint: {
                children: {
                    variation: {
                        url: 'variations'
                    }
                }
            },
            details: {
                additionalViewTemplate: 'tabs',
                additionalViewTemplateTabs: [
                    {
                        name: 'variation',
                        icon: 'map-marker'
                    }
                ]
            }
        }); */
        catViewServiceProvider.listAndDetailView('', 'Industry');
        catViewServiceProvider.listAndDetailView('', 'Variation');
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