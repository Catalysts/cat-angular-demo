(function () {

    var app = angular.module('cat.angular.demo.manufacturer.ManufacturerModule', [
        'cat.angular.demo.manufacturer.carmodel.ManufacturerCarmodel',
        'cat.angular.demo.manufacturer.carmodel.variation.ManufacturerCarmodelVariation',
        'cat.angular.demo.manufacturer.industry.ManufacturerIndustry',
        'cat.angular.demo.manufacturer.ManufacturerDetailsController'
    ]);

    app.config(['catViewServiceProvider', function (catViewServiceProvider) {

        catViewServiceProvider.listAndDetailView('', 'Manufacturer', {
            endpoint: {
                children: {
                    carmodel: {
                        url: 'carmodels',
                        children: {
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
                        icon: 'road'
                    }, {
                        name: 'industry',
                        icon: 'globe'
                    }
                ]
            }
        });
    }]);

    app.config(['catSelectConfigServiceProvider', function (catSelectConfigServiceProvider) {
        catSelectConfigServiceProvider.config('manufacturer', {
            endpoint: 'manufacturer'
        });
    }]);

})();